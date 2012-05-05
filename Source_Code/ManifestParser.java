/*****************************************************************************************
ManifestParser.java

This class parses the '*_main_manifest' files.

This will load a user's saved '*_main_manifest' into working memory (creating objects, etc).  

A.K.A "Papa Parser" and "The Big Kahuna Parser" 
	--> The ManifestParser also orchestrates the work of most other parsers.

	Methods:
		void update(String, LineStatus)
		void main(String[]) //unit test method


	//be sure to check for doubles (at the right spot)... do they already exist in main manifest before adding?!
	//use the isPresent abstract Parser method and conditional logic?

*****************************************************************************************/

import java.util.*;
import java.io.*;
import java.util.regex.*;

public class ManifestParser extends Parser
{
	public static void initialize(String userName) throws IOException
	{
		Parser manifest, category, todo;
		manifest = new ManifestParser();
		category = new CategoryParser();
		todo = new TodoParser();

		//(new user)       if it doesnt exist  --> creates blank manifest
		//(returning user) if it does exist	   --> loads all '* Objects' into working memory
		
		BookmarkWorkbench.categoryManifest = userName + "_category_manifest.txt";
		CategoryParser.initialize(BookmarkWorkbench.categoryManifest);
		BookmarkWorkbench.todoManifest = userName + "_todo_manifest.txt";

		UrlCard.initialize();

		BookmarkWorkbench.mainManifest = userName + "_main_manifest.txt";
		manifest.load(BookmarkWorkbench.mainManifest);

		//UiCli.neutralMessage(MessageType.CompletedParsingManifest); //concider not showing this alert to user? -- instead write it to a log file with date stamp?
		
		category.associate(UrlCard.getAllUrls(), CategoryCard.getAllCategory());
	}

	public void update(String line, LineStatus status, Scanner fileScan) throws IOException
	{
		boolean isUrlObjectComplete = false;

		do {
					
			switch (status)
			{
				case BlankLine: 	{
										isUrlObjectComplete = true;								
									}
										break;

				case TitleLine: 	{
										allUrls.add(new UrlCard(line)); //invokes new object (should it be 'invoke' metho
										iterate(fileScan);
										isUrlObjectComplete = true; //'recursive base case' to eventually break out of loop
									}
										break;

				case UrlLine: 		{
										int i = allUrls.size() - 1;
										UrlCard url = allUrls.get(i);
										url.setUrl(line);
										iterate(fileScan);
										isUrlObjectComplete = true; //'recursive base case' to eventually break out of loop
									}
										break;

				case CategoryLine: 	{
										int i = allUrls.size() - 1;
										UrlCard url = allUrls.get(i);
										url.setCategory(CategoryParser.clean(line));
										Parser category = new CategoryParser();

										category.update(line, status, fileScan);				
										iterate(fileScan);
										isUrlObjectComplete = true; //'recursive base case' to eventually break out of loop
									}
										break;

				case TodoLine: 		{
										int i = allUrls.size() - 1;
										UrlCard url = allUrls.get(i);
										url.setTodo(TodoParser.clean(line));
										iterate(fileScan);
										isUrlObjectComplete = true; //'recursive base case' to eventually break out of loop
									}
										break;

				case NoteLine: 		{
										int i = allUrls.size() - 1;
										UrlCard url = allUrls.get(i);
										url.setNotes(NoteParser.clean(line));
										iterate(fileScan);
										isUrlObjectComplete = true; //'recursive base case' to eventually break out of loop
									}
										break;		

				case OtherLine: 	{
										Ui userInterface = new UiCli();
										userInterface.warningMessage(MessageType.ProblemParsingManifest);
										isUrlObjectComplete = true; //'recursive base case' to eventually break out of loop
									}
										break;
			}

		} while(isUrlObjectComplete==false);

		//--> concider adding variable here to feed a progress bar or increment to show how many have been entered into main memory
	}

	public static void reset()
	{
		ManifestParser.wipe(BookmarkWorkbench.mainManifest);		
		UrlCard.serialize(BookmarkWorkbench.mainManifest);
	}

	//-------------------------------------------------------------------------------
	// ManifestParser.java Unit Tests:
	//-------------------------------------------------------------------------------

	public static void main(String[] args) throws IOException
	{
		/*
		System.out.println("RUNNING: loadManifest(); ------------------------");
		String testManifest = "testUser_main_manifest.txt"; 
		load(testManifest);		
		System.out.println("END: loadManifest(); ------------------------");
		*/

		
	}
}