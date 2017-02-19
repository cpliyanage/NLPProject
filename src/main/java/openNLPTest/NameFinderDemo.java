package openNLPTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.Span;

public class NameFinderDemo {

	public static void main(String[] args) throws InvalidFormatException, IOException {
		InputStream is = new FileInputStream("en-token.bin");
		 
		TokenizerModel model = new TokenizerModel(is);
	 
		Tokenizer tokenizer = new TokenizerME(model);
	 
		String tokens[] = tokenizer.tokenize("There is a blue table. There is a brown box on it. There is a red chair left to the table.");
		
		InputStream is2 = new FileInputStream("en-ner-person.bin");
		 
		TokenNameFinderModel model2 = new TokenNameFinderModel(is2);
		is2.close();
	 
		NameFinderME nameFinder = new NameFinderME(model2);
	 
/*		String []sentence = new String[]{
			    "Mike",
			    "Smith",
			    "is",
			    "a",
			    "good",
			    "person"
			    };*/
	 
			Span nameSpans[] = nameFinder.find(tokens);
	 
			for(Span s: nameSpans)
				System.out.println(s.toString());			

	}

}
