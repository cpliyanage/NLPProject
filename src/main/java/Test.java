import java.io.IOException;
import java.io.PrintWriter;


public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello World!");
		try{
		    PrintWriter writer = new PrintWriter("second.wrl", "UTF-8");
		    writer.println("#VRML V2.0 utf8");
		    writer.println("Group { ");
		    writer.println("children [ ");
		    writer.println("DEF box Transform { ");
		    writer.println("translation 0 0 0 ");
		    writer.println("children [ ");
		    writer.println("Shape { ");
		    writer.println("appearance Appearance {");
		    writer.println("material Material { diffuseColor 1 1 1 } ");
		    writer.println("} ");
		    writer.println("geometry Box { } ");
		    writer.println("}");
		    writer.println("]");
		    writer.println("}");
		    writer.println("]");
		    writer.println("}");
		    writer.close();
		    System.out.println("Successfully created file!");
		} catch (IOException e) {
		   // do something
		}
	}

}


