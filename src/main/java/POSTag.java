import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;


public class POSTag {

	public static void main(String[] args)throws IOException {
		POSModel model = new POSModelLoader()	
		.load(new File("en-pos-maxent.bin"));
	PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
	POSTaggerME tagger = new POSTaggerME(model);
 
	String input = "Draw a brown table";
	ObjectStream<String> lineStream = new PlainTextByLineStream(new StringReader(input));
 
	perfMon.start();
	String line;
	while ((line = lineStream.read()) != null) {
 
		String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE
				.tokenize(line);
		String[] tags = tagger.tag(whitespaceTokenizerLine);
		
		POSSample sample = new POSSample(whitespaceTokenizerLine, tags);
		String[] taggedWords=sample.toString().split(" ");
		String[] current;
		for(String a:taggedWords){
			System.out.println(a);
			current=a.split("_");
			if(current[1].equals("NN")){
				System.out.println("noun");
				drawObject(current[0]);
			}
		}
		
		System.out.println(sample.toString());
 
		perfMon.incrementCounter();
	}
	perfMon.stopAndPrintFinalResult();

	}
	
	public static void drawObject(String obj){
		if(obj.toLowerCase().equals("table")){
			try{
			 PrintWriter writer = new PrintWriter("test.wrl", "UTF-8");
			 writer.println("#VRML V2.0 utf8");
			 writer.println("Transform { translation 0.0 0.615 0.0 children [	Shape {  appearance DEF Brown Appearance {		material Material {		    diffuseColor 0.6 0.35 0.0		}	    }	    geometry Cylinder {		radius 0.7		height 0.03	    }	}    ]}");
			 writer.println("Transform {   translation 0.0 0.3075 0.0    children [	Shape {	    appearance USE Brown	    geometry Box {		size 0.09 0.57 0.09	    }	}    ]}");			 
			 writer.println("Transform {    translation 0.0 0.015 0.0   children [	Shape {	    appearance USE Brown	    geometry Box {		size 0.5 0.03 0.5	    }	}    ]} Transform {    translation 0.0 0.045 0.0    children [	Shape {	    appearance USE Brown	    geometry Box {		size 0.35 0.03 0.35	    }	}    ]}");
			 writer.close();
			 System.out.println("Successfully created file!");
			}catch(IOException e){
				System.out.println(e);
			}
		}
	}

}
