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
	public static void initialize(String fileName) throws IOException
	{
		Parser todo = new TodoParser();

		TodoCard.initialize();
		todo.load(fileName);
		//UiCli.neutralMessage(MessageType.CompletedParsingTodoManifest); //concider not showing this alert to user? -- instead write it to a log file with date stamp?
	}

	public File load(String fileName) throws IOException
	{
		//overwrites Parser.load() implementation: doesnt iterate, just preps file

		File f;
  		f = new File(fileName);
  		
  		if (!f.exists())
  		{
  			f.createNewFile();
  		}

  		return f;
	}

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

	private static void invoke(String title)
	{
		//unneeded?
	}

	public void associate(ArrayList<UrlCard> urlsList, ArrayList<CategoryCard> categoriesList)
	{
		//for to do list object.. need list of category, with it list of url objects and tehn the objects list of to dos

		//here i am associating the category objects to the todo list... later create the output view...
	}

	//-------------------------------------------------------------------------------
	// TodoParser.java Unit Tests:
	//-------------------------------------------------------------------------------

	public static void main(String[] args)
	{

	}
}