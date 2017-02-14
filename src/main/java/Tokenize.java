import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InvalidFormatException;


public class Tokenize {

	public static void main(String[] args) throws InvalidFormatException, IOException{
		InputStream is = new FileInputStream("en-token.bin");
		 
		TokenizerModel model = new TokenizerModel(is);
	 
		Tokenizer tokenizer = new TokenizerME(model);
	 
		String tokens[] = tokenizer.tokenize("Draw a brown box");
	 
		for (String a : tokens)
			System.out.println(a);
	 
		is.close();

	}

}
