package openNLPTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.InvalidFormatException;

public class SentenceDetectorDemo{

public static void main(String[] args) throws InvalidFormatException,
		IOException {
	String paragraph = "There is a blue table. There is a brown box on it. There is a red chair left to the table.";
 
	// always start with a model, a model is learned from training data
	InputStream is = new FileInputStream("en-sent.bin");
	SentenceModel model = new SentenceModel(is);
	SentenceDetectorME sdetector = new SentenceDetectorME(model);
 
	String[] sentences = sdetector.sentDetect(paragraph);
 
	for(int i =0; i<sentences.length;i++){
	System.out.println(sentences[i]);
	}
	is.close();
}

}