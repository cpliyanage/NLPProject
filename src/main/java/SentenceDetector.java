/*package openNLPTest;

import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.sentdetect.SentenceDetector;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

public class SentenceDetector {

	public static void main(String[] args) {
		SentenceDetectorME _sentenceDetector = null;
		 
		InputStream modelIn = null;
		try {
		   // Loading sentence detection model
		   modelIn = getClass().getResourceAsStream("/en-sent.bin");
		   final SentenceModel sentenceModel = new SentenceModel(modelIn);
		   modelIn.close();
		 
		   _sentenceDetector = new SentenceDetectorME(sentenceModel);
		 
		} catch (final IOException ioe) {
		   ioe.printStackTrace();
		} finally {
		   if (modelIn != null) {
		      try {
		         modelIn.close();
		      } catch (final IOException e) {} // oh well!
		   }
		}

	}

}
*/