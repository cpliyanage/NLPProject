import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.parser.AbstractBottomUpParser;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.Span;

public class SentenceParser {
	Parse parseSentence(final String text) throws InvalidFormatException, IOException {
		   final Parse p = new Parse(text,
		         // a new span covering the entire text
		         new Span(0, text.length()),
		         // the label for the top if an incomplete node
		         AbstractBottomUpParser.INC_NODE,
		         // the probability of this parse...uhhh...? 
		         1,
		         // the token index of the head of this parse
		         0);
		 
		   // make sure to initialize the _tokenizer correctly
			InputStream is = new FileInputStream("en-token.bin");
			 
			TokenizerModel model = new TokenizerModel(is);
		 
			Tokenizer _tokenizer = new TokenizerME(model);
		   final Span[] spans = _tokenizer.tokenizePos(text);
		 
		   for (int idx=0; idx < spans.length; idx++) {
		      final Span span = spans[idx];
		      // flesh out the parse with individual token sub-parses 
		      p.insert(new Parse(text,
		            span,
		            AbstractBottomUpParser.TOK_NODE, 
		            0,
		            idx));
		   }
		 
		   Parse actualParse = parse(p);
		   return actualParse;
		}
	
	private Parser _parser = null;
	 
	private Parse parse(final Parse p) {
	   // lazy initializer
	   if (_parser == null) {
	      InputStream modelIn = null;
	      try {
	         // Loading the parser model
	         modelIn = getClass().getResourceAsStream("/en-parser-chunker.bin");
	         final ParserModel parseModel = new ParserModel(modelIn);
	         modelIn.close();
	          
	         _parser = ParserFactory.create(parseModel);
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
	   return _parser.parse(p);
	}
}
