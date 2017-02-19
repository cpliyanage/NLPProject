package openNLPTest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import opennlp.tools.coref.DefaultLinker;
import opennlp.tools.coref.DiscourseEntity;
import opennlp.tools.coref.Linker;
import opennlp.tools.coref.LinkerMode;
import opennlp.tools.coref.mention.DefaultParse;
import opennlp.tools.coref.mention.Mention;
import opennlp.tools.parser.Parse;
import opennlp.tools.util.InvalidFormatException;

public class CoreferenceResolution {
	
	public static void main(String[] args) throws InvalidFormatException, IOException{
		String[] a1 = {"There is a blue chair", "There is a brown table next to it"};
		String[][] a2 =new String[2][2];
		a2[0][0]= "table";
		a2[0][1]="chair";
		a2[1][0]="blue";
		a2[1][1]="red";
		findEntityMentions(a1,a2);
	}
	
	public static DiscourseEntity[] findEntityMentions(final String[] sentences,
		      final String[][] tokens) throws InvalidFormatException, IOException {
		Linker _linker = null;
		 
		try {
		   // coreference resolution linker
		   _linker = new DefaultLinker("lib/opennlp/coref", LinkerMode.TEST);
		    
		} catch (final IOException ioe) {
		   ioe.printStackTrace();
		}
		
		   // tokens should correspond to sentences
		   assert(sentences.length == tokens.length);
		    
		   // list of document mentions
		   final List<Mention> document = new ArrayList<Mention>();
		 
		   for (int i=0; i < sentences.length; i++) {
		      // generate the sentence parse tree
			   SentenceParser sentenceParser = new SentenceParser();
		      final Parse parse = sentenceParser.parseSentence(sentences[i]);
		       
		      final DefaultParse parseWrapper = new DefaultParse(parse, i);
		      final Mention[] extents = _linker.getMentionFinder().getMentions(parseWrapper);
		 
		      //Note: taken from TreebankParser source...
		      for (int ei=0, en=extents.length; ei<en; ei++) {
		         // construct parses for mentions which don't have constituents
		         if (extents[ei].getParse() == null) {
		            // not sure how to get head index, but it doesn't seem to be used at this point
		            final Parse snp = new Parse(parse.getText(), 
		                  extents[ei].getSpan(), "NML", 1.0, 0);
		            parse.insert(snp);
		            // setting a new Parse for the current extent
		            extents[ei].setParse(new DefaultParse(snp, i));
		         }
		      }
		      document.addAll(Arrays.asList(extents));
		   }
		 
		   if (!document.isEmpty()) {
		      return _linker.getEntities(document.toArray(new Mention[0]));
		   }
		   return new DiscourseEntity[0];
		}

}
