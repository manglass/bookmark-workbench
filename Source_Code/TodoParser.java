/*****************************************************************************************
TodoParser.java

This class parses the '*_main_manifest' file and creates the '*_todo_manifest' file.

This will load a user's saved '*_todo_manifest' into working memory (creating objects, etc).

	Methods:
		boolean isPresent(String, String)
		void main(String[]) //unit test method		

*****************************************************************************************/

import java.util.*;
import java.io.*;

class TodoParser extends Parser 
{
	public static ArrayList<String> clean(String line)
	{
		Scanner lineScan;
		ArrayList<String> todos = new ArrayList<String>();
		
		lineScan = new Scanner (line);
		lineScan.useDelimiter(", ");

		while (lineScan.hasNext())
		{
			String word;

			word = lineScan.next();
			word = word.replace("[[/ToDo]]", "");
			word = word.replace("[[ToDo]]", "");
			word = word.replace("*", "* ");

			todos.add(word);
		}

		return todos;
	}

	//-------------------------------------------------------------------------------
	// TodoParser.java Unit Tests:
	//-------------------------------------------------------------------------------

	public static void main(String[] args)
	{

	}
}