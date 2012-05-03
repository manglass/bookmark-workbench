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
		System.out.println();//eee+
		System.out.println("ManifestParser----init category parser");//eee+
		BookmarkWorkbench.todoManifest = userName + "_todo_manifest.txt";
		TodoParser.initialize(BookmarkWorkbench.todoManifest);
		System.out.println();//eee+
		System.out.println("ManifestParser----init todo parser");//eee+

		UrlCard.initialize();

		BookmarkWorkbench.mainManifest = userName + "_main_manifest.txt";
		System.out.println();//eee+
		System.out.println("ManifestParser----load main manifest");//eee+
		manifest.load(BookmarkWorkbench.mainManifest);

		//UiCli.neutralMessage(MessageType.CompletedParsingManifest); //concider not showing this alert to user? -- instead write it to a log file with date stamp?
		
		category.associate(UrlCard.getAllUrls(), CategoryCard.getAllCategory());
		todo.associate(UrlCard.getAllUrls(), CategoryCard.getAllCategory());

		System.out.println();//eee+
		System.out.println("The system categories: ");//eee+
		ArrayList<CategoryCard> eArray = CategoryCard.getAllCategory();//eee+
		for (int e = 0; e<eArray.size();e++)//eee+
			System.out.println("" + e + ": " + eArray.get(e));//eee+
		System.out.println();//eee+
		System.out.println("The system urls: ");//eee+			
		ArrayList<UrlCard> e2Array = UrlCard.getAllUrls();//eee+
		for (int e2 = 0; e<e2Array.size();e2++)//eee+
			System.out.println("" + e2 + ": " + e2Array.get(e2));//eee+

	}

	public void update(String line, LineStatus status, Scanner fileScan) throws IOException
	{
		System.out.println();//eee+
		System.out.println("ManifestParser----now in update");//eee+
		ArrayList<UrlCard> allUrls = UrlCard.getAllUrls();
		System.out.println();//eee+
		System.out.println("ManifestParser----creates array for use, array is: " + allUrls.size());//eee+
		boolean isUrlObjectComplete = false;

		do {
					
			switch (status)
			{
				case BlankLine: 	{
										isUrlObjectComplete = true;
										System.out.println();//eee+
										System.out.println("ManifestParser----update -- BLANKLINE");//eee+
									}
										break;

				case TitleLine: 	{
										allUrls.add(new UrlCard(line)); //invokes new object (should it be 'invoke' method)
										System.out.println();//eee+
										System.out.println("ManifestParser----update -- TITLELINE: " + line + "in obj: " + allUrls.get(allUrls.size() - 1));//eee+
										iterate(fileScan);
										isUrlObjectComplete = true; //'recursive base case' to eventually break out of loop
									}
										break;

				case UrlLine: 		{
										int i = allUrls.size() - 1;
										UrlCard url = allUrls.get(i);
										url.setUrl(line);
										System.out.println();//eee+
										System.out.println("ManifestParser----update -- URLLINE: " + line + "in obj: " + url);//eee+
										iterate(fileScan);
										isUrlObjectComplete = true; //'recursive base case' to eventually break out of loop
									}
										break;

				case CategoryLine: 	{
										int i = allUrls.size() - 1;
										UrlCard url = allUrls.get(i);
										url.setCategory(CategoryParser.clean(line));
										Parser category = new CategoryParser();
										System.out.println();//eee+
										System.out.println("ManifestParser----calls category parser update");//eee+										
										category.update(line, status, fileScan);
										System.out.println();//eee+
										System.out.println("ManifestParser----update -- CATEGORYLINE: " + line + "in obj: " + url);//eee+
										iterate(fileScan);
										isUrlObjectComplete = true; //'recursive base case' to eventually break out of loop
									}
										break;

				case TodoLine: 		{
										int i = allUrls.size() - 1;
										UrlCard url = allUrls.get(i);
										url.setTodo(TodoParser.clean(line));
										System.out.println();//eee+
										System.out.println("ManifestParser----update -- TODOLINE: " + TodoParser.clean(line) + "in obj: " + url);//eee+
										iterate(fileScan);
										isUrlObjectComplete = true; //'recursive base case' to eventually break out of loop
									}
										break;

				case NoteLine: 		{
										int i = allUrls.size() - 1;
										UrlCard url = allUrls.get(i);
										url.setNotes(NoteParser.clean(line));
										System.out.println();//eee+
										System.out.println("ManifestParser----update -- NOTELINE: " + NoteParser.clean(line) + "in obj: " + url);//eee+
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
										System.out.println();//eee+
										System.out.println("ManifestParser-----UrlObject Complete!");//eee+
		//--> concider adding variable here to feed a progress bar or increment to show how many have been entered into main memory
	}

	public static void reset()
	{
		System.out.println();//eee+
		System.out.println("ManifestParser----wipe out the manifest");//eee+
		ManifestParser.wipe(BookmarkWorkbench.mainManifest);
		System.out.println();//eee+
		System.out.println("ManifestParser----add all active objects to the manifest");//eee+		
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