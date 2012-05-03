/*****************************************************************************************
SessionParser.java

	1. [loads] a user "Session File" 
	2. [reads] each line and determines LineType
	3. [appends] each "Url Object" in proper format to '*_main_manifest'

		^^ triggers Manifest Parser to run again (only adding new "Url Objects") ^^

	Methods:

		void main(String[]) //unit test method

*****************************************************************************************/

import java.util.*;
import java.io.*;
import java.util.regex.*;

public class SessionParser extends Parser
{
	public static void get() throws IOException
	{
		Scanner scan = new Scanner(System.in);

		Ui userInterface = new UiCli(); //decouple this from model (observer pattern?)
		userInterface.boilerPlate(MessageType.ManuallyAddBrowserSessionFile); //decouple this from model (observer pattern?)

		//do loop logic
		boolean isFilenameCorrect = false;
		int urlCount;
		String fileName;

		do {

			urlCount = 0;

			System.out.print("Enter your Session File name (with file extenstion): ");
			fileName = scan.nextLine();
			System.out.println();

			try {
			
				Scanner fileScan = new Scanner(new File(fileName));	

				//while loop initializer message
				System.out.print("Loading ");

				//prints exception and/or shows '0' urls if there are any file problems
				while (fileScan.hasNext())
				{
					String line = fileScan.nextLine();
					boolean urlLine = Pattern.matches("^http.*://.*", line);

					System.out.print(".");

					if(urlLine)
						urlCount++;
				}

				System.out.println();
				System.out.println();

			} catch (Exception e) {System.err.println("Error: " + e.getMessage());
  				                   System.err.println();}

			System.out.println("There are " + urlCount + " urls in " + fileName + ", are you sure this is the correct file?");
			System.out.println();

			isFilenameCorrect = userInterface.userChoice(MessageType.YesOrNo);	//decouple this from model (observer pattern?)		

		} while(isFilenameCorrect==false);		

		initialize(fileName);
	}

	public static void initialize(String fileName) throws IOException
	{
		Parser session = new SessionParser();

		System.out.println("SessionParser-----Manifest parser is resetting."); //eee+
		System.out.println(); //eee+
		ManifestParser.reset();
		System.out.println("SessionParser-----One line will be added to the mainManifest file."); //eee+
		System.out.println(); //eee+
		add("", BookmarkWorkbench.mainManifest); //eee-(1)->
		//eee-(1)-> add("", BookmarkWorkbench.mainManifest); //lnbreak to offset user file which starts with 'TitleLine'
		System.out.println();//eee+
		System.out.println("SessionParser----calls load");//eee+		
		session.load(fileName);
		System.out.println();//eee+
		System.out.println("SessionParser----calls scrap");//eee+
		scrap();
		System.out.println();//eee+
		System.out.println("SessionParser----ManifestParser is initializing");//eee+
		ManifestParser.initialize(BookmarkWorkbench.mainManifest.replace("_main_manifest.txt", "")); //triggers the manifest parser to refresh working memory(refactor this to trigger it on the manifest file instead of session file, but need some way to know what has and has not already been entered into working memory! --> like rails migrations by date)
		//UiCli.neutralMessage(MessageType.CompletedParsingSessionFile); //concider not showing this alert to user? -- instead write it to a log file with date stamp?
	}

	public void update(String line, LineStatus status, Scanner fileScan) throws IOException
	{
		System.out.println();//eee+
		System.out.println("SessionParser----begin update with: " + line + "of type: " + status.toString());//eee+
		boolean isUrlObjectComplete = false;

		do {
					
			switch (status)
			{
				case BlankLine: 	{
										add("", BookmarkWorkbench.mainManifest);
										System.out.println();//eee+
										System.out.println("SessionParser----Update to " + BookmarkWorkbench.mainManifest + " : BlankLine");//eee+
										isUrlObjectComplete = true;
									}
										break;

				case TitleLine: 	{
										add(line, BookmarkWorkbench.mainManifest);
										System.out.println();//eee+
										System.out.println("SessionParser----Update: " + line);//eee+
										iterate(fileScan);
										isUrlObjectComplete = true; //'recursive base case' to eventually break out of loop
									}
										break;

				case UrlLine: 		{
										add(line, BookmarkWorkbench.mainManifest);
										System.out.println();//eee+
										System.out.println("SessionParser----Update: " + line);//eee+
										iterate(fileScan);
										isUrlObjectComplete = true; //'recursive base case' to eventually break out of loop
									}
										break;

				case CategoryLine: 	{
										add(line, BookmarkWorkbench.mainManifest);
										System.out.println();//eee+
										System.out.println("SessionParser----Update: " + line);//eee+
										iterate(fileScan);
										isUrlObjectComplete = true; //'recursive base case' to eventually break out of loop
									}
										break;

				case TodoLine: 		{
										add(line, BookmarkWorkbench.mainManifest);
										System.out.println();//eee+
										System.out.println("SessionParser----Update: " + line);//eee+
										iterate(fileScan);
										isUrlObjectComplete = true; //'recursive base case' to eventually break out of loop
									}
										break;

				case NoteLine: 		{
										add(line, BookmarkWorkbench.mainManifest);
										System.out.println();//eee+
										System.out.println("SessionParser----Update: " + line);//eee+
										iterate(fileScan);
										isUrlObjectComplete = true; //'recursive base case' to eventually break out of loop
									}
										break;		

				case OtherLine: 	{
										Ui userInterface = new UiCli(); //decouple this from model (observer pattern?)
										userInterface.warningMessage(MessageType.ProblemParsingSessionFile); //decouple this from model (observer pattern?)
									}
										break;
			}

		} while(isUrlObjectComplete==false);

		add("", BookmarkWorkbench.mainManifest); //extra line break after the object finishes
	}

	private static void scrap()
	{
		//wipe active objects from program memory
		System.out.println();//eee+
		System.out.println("SessionParser----Scrap: UrlCards");//eee+
		UrlCard.clearOut();
		System.out.println();//eee+
		System.out.println("SessionParser----Scrap: CategoryCard");//eee+
		CategoryCard.clearOut();
		System.out.println();//eee+
		System.out.println("SessionParser----Scrap: TodoCard");//eee+
		TodoCard.clearOut();
	}


	//-------------------------------------------------------------------------------
	// SessionParser.java Unit Tests:
	//-------------------------------------------------------------------------------

	public static void main(String[] args) throws IOException
	{
		//System.out.println("RUNNING: loadManifest(); ------------------------");
		//String testManifest = "testUser_main_manifest.txt"; 
		//load(testManifest);		
		//System.out.println("END: loadManifest(); ------------------------");
	}
}