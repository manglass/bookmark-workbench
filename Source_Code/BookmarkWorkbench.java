/*****************************************************************************************
BookmarkWorkbench.java

This is the starter class executable.  
All other classes and actions will be invoked here.

	Methods:
		main(String[])	//unit test method

*****************************************************************************************/

import java.io.*;

public class BookmarkWorkbench
{
	public static String mainManifest;
	public static String categoryManifest;
	public static String todoManifest;
	public static String userManifest = "user_manifest.txt";

	public static void main(String[] args) throws IOException
	{
		// User Prompt: 'Program Boilerplate: Welcome to BookmarkWorkbench, please sign in or create username'
		String userName;
		userName = UserPrompt.welcome();

		// Kicks off objects and files to set active program state.
		ManifestParser.initialize(userName);

		// User Prompt: 'Main Menu' (loops these options until program exit)
		UserPrompt.mainMenu(userName);
		
	}
}