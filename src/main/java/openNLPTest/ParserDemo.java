package openNLPTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.util.InvalidFormatException;

public class ParserDemo {

	public static void main(String[] args) throws InvalidFormatException, IOException {
		InputStream is = new FileInputStream("en-parser-chunking.bin");
		 
		ParserModel model = new ParserModel(is);
	 
		Parser parser = ParserFactory.create(model);
	 
		String sentence = "There is a blue table. There is a box on top of it.";
		Parse topParses[] = ParserTool.parseLine(sentence, parser, 1);
	 
		for (Parse p : topParses)
			p.show();
	 
		is.close();
	 
		/*
		 * (TOP (S (NP (NN Programcreek) ) (VP (VBZ is) (NP (DT a) (ADJP (RB
		 * very) (JJ huge) (CC and) (JJ useful) ) ) ) (. website.) ) )
		 */

	}

}
