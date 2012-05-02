/*****************************************************************************************
Parser.java

Parent class of: Manifest Parser, Category Parser, UrlParser, TodoParser, NoteParser

	Methods:
		boolean isPresent(String, String)
		void add(String, String)
		void create(String, LineStatus)
		void readLine(String)
		void main(String[]) //unit test method		

*****************************************************************************************/

import java.util.*;
import java.io.*;
import java.util.regex.*;

public class Parser
{
	public static void initialize(String arg) throws IOException
	{
		//all calls to this method should be polymorphic/overridden (cant be abstract because it is called within this file)
	}

	public static boolean isPresent(String arg, String fileName) 
	{
		//all calls to this method should be polymorphic/overridden (cant be abstract because it is called within this file)
		return true; //dummy return value
	}

	public static boolean isDouble(String arg, String fileName)
	{
		//all calls to this method should be polymorphic/overridden (cant be abstract because it is called within this file)
		return true; //dummy return value	
	}

	public static void get() throws IOException
	{
		//all calls to this method should be polymorphic/overridden (cant be abstract because it is called within this file)	
	}

	public static void update(String line, LineStatus status, Scanner fileScan); 
	{
		//all calls to this method should be polymorphic/overridden (cant be abstract because it is called within this file)
	}

	public static void associate(ArrayList<Object> objs, ArrayList<Object> objs2)
	{
		//all calls to this method should be polymorphic/overridden (cant be abstract because it is called within this file)
	}

	public static ArrayList<String> clean(String line)
	{
		//all calls to this method should be polymorphic/overridden (cant be abstract because it is called within this file)
		return null; //dummy return value
	}

	public static void reset()
	{
		//all calls to this method should be polymorphic/overridden (cant be abstract because it is called within this file)
	}

	public static void wipe(String fileName)
	{
  		try {

	  		FileWriter fstream = new FileWriter(fileName,false);
	  		BufferedWriter out = new BufferedWriter(fstream);
	  		out.write("");
	  		out.close();

  		} catch (Exception e) {System.err.println("Error: " + e.getMessage());}	
	}

	public static void add(String arg, String fileName) 
	{
		//append successful lines to the '*_manifest'
  		try {

	  		FileWriter fstream = new FileWriter(fileName,true);
	  		BufferedWriter out = new BufferedWriter(fstream);
	  		out.write(arg + "\n");
	  		out.close();

  		} catch (Exception e) {System.err.println("Error: " + e.getMessage());}			
	}

	public static void load(String fileName) throws IOException
	{
		File f;
  		f = new File(fileName);
  		
  		if (!f.exists())
  		{
  			f.createNewFile();
  			System.out.println();//eee+
		    System.out.println("*Parser----created new file: " + fileName);//eee+
  		}
  		else//eee+
  		{//eee+
   			System.out.println();//eee+
		    System.out.println("*Parser---- " + fileName + " already exists.");//eee+	
  		}//eee+

  		try {

				Scanner fileScan = new Scanner(new File(fileName));
				System.out.println();//eee+
				System.out.println("*Parser----calls iterate");//eee+
				iterate(fileScan);

		} catch (Exception e) {System.err.println("Error: " + e.getMessage());}
	}

	protected static void iterate(Scanner fileScan)
	{
  		while (fileScan.hasNext())
  		{
  			System.out.println();//eee+
			System.out.println("*Parser----iterate begins");//eee+
			String 		line 	   = fileScan.nextLine();
			System.out.println();//eee+
			System.out.println("----line: " + line);//eee+
  			LineStatus	lineStatus = readLine(line);
  			System.out.println();//eee+
			System.out.println("*Parser----line gets sent to update with status: " + lineStatus.toString());//eee+
  			update(line, lineStatus, fileScan);
  		}		
	}

	private static LineStatus readLine(String line)
	{
		boolean blankLine 		= Pattern.matches("^$", line);
		boolean titleLine 		= Pattern.matches(".*\\s+-+\\s+.*", line);
		boolean urlLine  	    = Pattern.matches("^http.*://.*", line);
		boolean categoryLine	= Pattern.matches("^\\[\\[Category\\]\\].*", line);
		boolean todoLine		= Pattern.matches("^\\[\\[ToDo\\]\\].*", line);
		boolean noteLine 		= Pattern.matches("^\\[\\[Notes\\]\\].*", line);

		if (blankLine)
		{
			return LineStatus.BlankLine;
		}
		else if (titleLine)
		{
			return LineStatus.TitleLine;
		}
		else if (urlLine)
		{
			return LineStatus.UrlLine;					
		}
		else if (categoryLine)
		{ 
			return LineStatus.CategoryLine;					
		}
		else if (todoLine)
		{
			return LineStatus.TodoLine;			
		}
		else if (noteLine)
		{
			return LineStatus.NoteLine;					
		}
		else
			return LineStatus.OtherLine;
	}	

	//-------------------------------------------------------------------------------
	// Parser.java Unit Tests:
	//-------------------------------------------------------------------------------

	public static void main(String[] args) throws IOException
	{
		/*
		System.out.println("RUNNING: programBoilerplate(); ------------------------");
		String name; 		
		name = programBoilerplate();
		System.out.println("END: programBoilerplate(); ------------------------");

		System.out.println("RUNNING: mainMenu(); ------------------------");
		mainMenu(name);
		System.out.println("END: mainMenu(); ------------------------");
		*/

		load("Test.txt");

	}

}