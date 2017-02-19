package openNLPTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InvalidFormatException;

public class TokenizerDemo {

	public static void main(String[] args) throws InvalidFormatException, IOException {
		InputStream is = new FileInputStream("en-token.bin");
		 
		TokenizerModel model = new TokenizerModel(is);
	 
		Tokenizer tokenizer = new TokenizerME(model);
	 
		String tokens[] = tokenizer.tokenize("There is a blue table. There is a brown box on it. There is a red chair left to the table.");
	 
		for (String a : tokens)
			System.out.println(a);
	 
		is.close();
	}

}
