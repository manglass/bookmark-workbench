/*****************************************************************************************
UserPrompt.java

This is the class which houses all of the user interaction logic and boilerplate UI code.  

	Methods:
		String welcome()
		String signUp()
		String signIn()
		void startupChecklist()
		void mainMenu(String)
		int sessionBoilerplate(String)
		void help()
		void main(String[]) //unit test method

*****************************************************************************************/

import java.util.*;
import java.io.*;
import java.util.regex.*;

public class UserPrompt
{
	private static int menuCount = 0;
	private static Ui userInterface = new UiCli(); //this can be interchanged to UiGui provide true GUI, should use it more polymorphically from Ui base
	private static Ui graphicalInterface = new UiGui();

	public static String welcome() throws IOException
	{
		bootstrap();

		userInterface.boilerPlate(MessageType.IntroCredits);

		boolean isFirstTime = userInterface.userChoice(MessageType.FirstTimeOrReturning);

	 	String userName;

		if(isFirstTime)
			userName = signUp();
		else
			userName = signIn();

		menuCount = 0;

		return userName;
	}

	private static void bootstrap() throws IOException
	{
		Parser user = new UserParser();
		user.load("user_manifest.txt");
	}

	private static String signUp()
	{
		userInterface.boilerPlate(MessageType.SignUp);

		Scanner scan = new Scanner(System.in);

		//do loop logic
		boolean isUserNameTaken = false;
		String userName;

		do {

			System.out.print("Username: ");
			userName = scan.nextLine();
			System.out.println();

			isUserNameTaken = UserParser.isPresent(userName, BookmarkWorkbench.userManifest);

		} while(isUserNameTaken);

		UserParser.add(userName, BookmarkWorkbench.userManifest);

		System.out.println("You have chosen the username " + userName + ", please keep a record of this in a safe place.");
		System.out.println();

		return userName;
	}

	private static String signIn()
	{
		userInterface.boilerPlate(MessageType.SignIn);

		Scanner scan = new Scanner(System.in);

		//do loop logic
		boolean isUserNameNotInList = true;
		String userName;

		do {

			System.out.print("Username: ");
			userName = scan.nextLine();
			System.out.println();

			//scan user_manifest for selected username
			try {

				Scanner fileScan = new Scanner(new File(BookmarkWorkbench.userManifest));
				String activatedUser;

			
				while (fileScan.hasNext())
				{
					activatedUser = fileScan.nextLine();
					if(activatedUser.equals(userName))
					{
						isUserNameNotInList = false;
						break;
					}
				}

  			} catch (Exception e) {
  				System.err.println("Error: " + e.getMessage());
 	    	  }

			if(isUserNameNotInList==true)
			{
				boolean isFirstTime = userInterface.userChoice(MessageType.UserDoesntExist);

				if(isFirstTime)
				{
					userName = signUp();
					isUserNameNotInList = false;	
				}
			}

		} while(isUserNameNotInList);

		return userName;
	}

	public static void mainMenu(String user) throws IOException
	{
		//do loop logic
		boolean userExit = false, isEntryIncorrect = false;

		do {

			do {

				int menuSelection;
				menuSelection = sessionBoilerplate(user);

				switch(menuSelection)
				{
					//*Add* Browser 'Session File'
					case 1:  SessionParser.get();
							 break;
					//*List all* 'Categories'
					case 2:  userInterface.viewCategoryList();
							 break;
					//*List all* 'URLs' (ordered by Category)
					case 3:  userInterface.viewUrlList(CategoryCard.getAllCategory());
							 break;
					//*List all* 'To Do' Items (ordered by Category)
					case 4:  userInterface.viewTodoList(CategoryCard.getAllCategory());
							 break;
					//*View Selected* 'URL Card'
					case 5:  {
								Scanner scan = new Scanner(System.in);

								System.out.println("To view a specific 'URL Card' in the system, search for its title.");

								System.out.print("Enter your search: ");
								String query = scan.nextLine();

								ArrayList<Integer> search = userInterface.urlSearch(query);
								
								if(search.size()>0)
								{
									int urlIndex = userInterface.urlResultSet(search);
									userInterface.viewUrlCard(UrlCard.getAllUrls().get(urlIndex));
								}
								else
								{
									System.out.println();
									System.out.println("Your search returned no results.");
								}
							 }
							 break;
					//*View Random* 'URL Card'
					case 6:  {
								if(UrlCard.getAllUrls().size()>0)
									graphicalInterface.viewRandomUrlCard();
								else
									System.out.println("Please add a session file first!");
							 }
							 break;
					//[7]. *Edit Selected* 'URL Card'
					case 7:  userInterface.editUrlCard();
							 break;
					//*Help* (explains all the options and what they do)
					case 8: help();
							 break;	
					//*Exit* the application						 							 					 													
					case 9: {
								userExit = true;
							 	ManifestParser.reset();
							 }
							 break;
					default: isEntryIncorrect = true;
							 break;
				}

			} while(isEntryIncorrect);

		} while(!userExit);	

		userInterface.boilerPlate(MessageType.EndingCredits);
	}

	private static int sessionBoilerplate(String user)
	{
		while(menuCount == 0)
		{
			System.out.println();
			System.out.println(">> Hello " + user + ", you are now signed in! <<");
			System.out.println();
			System.out.println();

			menuCount++;
		}

		userInterface.boilerPlate(MessageType.MainMenuHead);
		userInterface.boilerPlate(MessageType.ListNumberOfCategoriesUrlsandToDos);
		userInterface.boilerPlate(MessageType.MainMenuBody);

 		Scanner scan = new Scanner(System.in);

 		//do loop logic
 		boolean isEntryIncorrect;
 		int menuSelection = 0;

 		boolean correctInput;

 		do {

 			System.out.print("Please enter a selection number ([?]) from the menu index: ");
 			
			do {
				
				try {
				
					menuSelection = Integer.parseInt(scan.nextLine());
					correctInput = true;
			
				} catch (NumberFormatException e) 
				  {System.out.println("\nPlease, enter a number from the list...\n"); correctInput = false;}
			
			} while(!correctInput);

 			System.out.println();

 			if(menuSelection>12)
 				isEntryIncorrect = true;
 			else if(menuSelection<1)
 				isEntryIncorrect = true;
 			else
 				isEntryIncorrect = false;

 		} while(isEntryIncorrect);

 		return menuSelection;
	}

	private static void help()
	{
		System.out.println();

		System.out.println("Help:");
		System.out.println();

		System.out.println("About this program:"); 
		System.out.println("\tEnter session files to populate program, then work with your bookmarks.");
		System.out.println();	
	}

	//-------------------------------------------------------------------------------
	// UserPrompt.java Unit Tests:
	//-------------------------------------------------------------------------------

	public static void main(String[] args) throws IOException
	{
		System.out.println("RUNNING: programBoilerplate(); ------------------------");
		String name; 		
		name = welcome();
		System.out.println("END: programBoilerplate(); ------------------------");

		System.out.println("RUNNING: mainMenu(); ------------------------");
		mainMenu(name);
		System.out.println("END: mainMenu(); ------------------------");
	}

}


