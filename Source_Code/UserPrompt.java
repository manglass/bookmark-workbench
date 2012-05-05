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


	//make "user_manifest.txt" a constant?

*****************************************************************************************/

import java.util.*;
import java.io.*;
import java.util.regex.*;

public class UserPrompt
{
	private static int menuCount = 0;
	private static Ui userInterface = new UiCli(); //this can be changed to UiGui provide true GUI
	private static Ui graphicalInterface = new UiGui();

	public static String welcome() throws IOException
	{
		bootstrap();

		System.out.println();
		System.out.println(); 
		System.out.println();

		System.out.println("  -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-   ");
		System.out.println("-------------------------------------------------------------------");
		System.out.println(" _                                                                  ");
		System.out.println("|_)  _   _  |  ._ _   _. ._ |    \\    / _  ._ |  |_   _  ._   _ |_  ");
		System.out.println("|_) (_) (_) |< | | | (_| |  |<    \\/\\/ (_) |  |< |_) (/_ | | (_ | | ");
		System.out.println("																	"); 
		System.out.println("-------------------------------------------------------------------");
		System.out.println("  -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-   ");
 
		System.out.println();
		System.out.println(); 
		System.out.println();		

		System.out.println("****************************************");
		System.out.println("**   Please select an option below:   **");
		System.out.println("****************************************");

		System.out.println();
		System.out.println();

		System.out.println("\t[1] This is my first time using Bookmark Workbench, I need to signup and set up a username.");
		System.out.println("\t[2] I already have a username, let me sign in to my account.");
		System.out.println();
		System.out.println();		

		Scanner scan = new Scanner(System.in);

		//do loop logic
		boolean isEntryIncorrect, isFirstTime = true;

		do {

			int userOption = 0;

			System.out.print("Please enter option 1 or 2: ");
			userOption = Integer.parseInt(scan.nextLine());
			System.out.println();

			if(userOption == 1)
			{
				isFirstTime = true;
				isEntryIncorrect = false;
			}
			else if(userOption == 2)
			{
				isFirstTime = false;
				isEntryIncorrect = false;
			}
			else
				isEntryIncorrect = true;

	 	} while(isEntryIncorrect);

	 	String userName;

		if(isFirstTime)
			userName = signUp();
		else
			userName = signIn();

		menuCount = 0;

		return userName;
	}

	private static String signUp()
	{
		Scanner scan = new Scanner(System.in);

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();

		System.out.println("  ____ ____ ____ ____    ____ ____  "); 
		System.out.println(" ||S |||i |||g |||n ||  ||U |||p || ");
		System.out.println(" ||__|||__|||__|||__||  ||__|||__|| ");
		System.out.println(" |/__\\|/__\\|/__\\|/__\\|  |/__\\|/__\\| ");

		System.out.println();
		System.out.println();

		System.out.println(" Welcome to Bookmark Workbench!");
		System.out.println(" ------------------------------");
		System.out.println();
		System.out.println("In order to begin storing and working with your bookmarks, please choose a simple and unique username.");

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
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();

        System.out.println("  ____ ____ ____ ____    ____ ____  "); 
		System.out.println(" ||S |||i |||g |||n ||  ||I |||n || ");
		System.out.println(" ||__|||__|||__|||__||  ||__|||__|| ");
		System.out.println(" |/__\\|/__\\|/__\\|/__\\|  |/__\\|/__\\| ");

		System.out.println();
		System.out.println();

		System.out.println(" Welcome back to Bookmark Workbench!");
		System.out.println(" -----------------------------------");
		System.out.println();
		System.out.println("Please enter your username to resume working with your bookmarks.");

		//do loop logic
		boolean isUserNameNotInList = true;
		String userName;
		Scanner scan = new Scanner(System.in);

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
				System.out.println("*****************************************************************************");
				System.out.println("**   !! The username you entered does not exist in Bookmark Workbench !!   **");
				System.out.println("*****************************************************************************");
			    System.out.println();

				System.out.println("\t[1] I want to sign up and create a new username.");
				System.out.println("\t[2] I entered my username incorrectly, I want to try again.");
				System.out.println();

				//do loop logic
				boolean isEntryIncorrect, isFirstTime = true;

				do {

					int userOption = 0;

					System.out.print("Please enter option 1 or 2: ");
					userOption = Integer.parseInt(scan.nextLine());
					System.out.println();

					if(userOption == 1)
					{
						isFirstTime = true;
						isEntryIncorrect = false;
					}
					else if(userOption == 2)
					{
						isFirstTime = false;
						isEntryIncorrect = false;
					}
					else
						isEntryIncorrect = true;

			 	} while(isEntryIncorrect);


				if(isFirstTime)
				{
					userName = signUp();
					isUserNameNotInList = false;	
				}
			}

		} while(isUserNameNotInList);

		return userName;
	}

	private static void bootstrap() throws IOException
	{
		Parser user = new UserParser();
		user.load("user_manifest.txt");
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
					case 1:  SessionParser.get();
							 break;
					case 2:  userInterface.viewCategoryList();
							 break;
					case 3:  userInterface.viewUrlList(CategoryCard.getAllCategory());
							 break;
					case 4:  userInterface.viewTodoList(CategoryCard.getAllCategory());
							 break;
					case 5:  System.out.println("category.view()");
							 break;
					//eee-(2)->case 6:  System.out.println("url.view()");
					//		 break;
					case 6:  {//eee+
								Scanner scan = new Scanner(System.in);//eee+
								Brainstorm_UrlCardSearch test = new Brainstorm_UrlCardSearch();//eee+
//eee+							
								System.out.println("To view a specific 'URL Card' in the system, search for its title.");

								System.out.print("Enter your search: ");//eee+
								String query = scan.nextLine();//eee+
//eee+
								ArrayList<Integer> search = test.urlSearch(query);//eee+
								
								if(search.size()>0)
								{
									int urlIndex = test.urlResultSet(search);//eee+
									//userInterface.viewUrlCard(UrlCard.getAllUrls().get(urlIndex));//eee+
									graphicalInterface.editUrlCard();
								}
								else
								{
									System.out.println("Your search returned no results."); //eee(USE!, but enhance)
								}//eee+
							 }//eee+
							 break;//eee+
//eee+
					case 7:  System.out.println("category.viewRandom()");
							 break;
					case 8:  userInterface.viewUrlCard(UrlCard.getAllUrls().get((int)(Math.random() * UrlCard.getAllUrls().size())));
							 break;
					case 9:  System.out.println("category.edit()");
							 break;		
					case 10: graphicalInterface.editUrlCard();
							 break;
					case 11: help();
							 break;							 							 					 													
					case 12: {
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

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();

		System.out.println("\t\t+-++-++-++-+ +-++-++-++-+");
		System.out.println("\t\t|M||a||i||n| |M||e||n||u|");
 		System.out.println("\t\t+-++-++-++-+ +-++-++-++-+");

 		System.out.println();
 		System.out.println(); 		

 		userInterface.boilerPlate(MessageType.ListNumberOfCategoriesUrlsandToDos);

 		System.out.println();
 		System.out.println(); 
 
		System.out.println("\t[1].  *Add* Browser \'Session File\'");
		
		System.out.println();

		System.out.println("\t[2].  *List all* \'Categories\'");					        //uses categorymanifest
		System.out.println("\t[3].  *List all* \'URLs\' (ordered by Category)");	        //uses categorymanifest, urlplainprint(not pretty/verbose)
		System.out.println("\t[4].  *List all* \'To Do\' Items (ordered by Category");		//uses todomanifest

		System.out.println();

		System.out.println("\t[5].  *View Selected* \'Category Card\'");			//searches the category card array for element and shows view
		System.out.println("\t[6].  *View Selected* \'URL Card\'");					//searches the url card array for element and shows view

		System.out.println();

		System.out.println("\t[7].  *View Random* \'Category Card\'");				//calls view above (but randomly inserts index) from category card array
		System.out.println("\t[8].  *View Random* \'URL Card\'");					//calls view above (but randomly inserts index) from url card array

		System.out.println();

		System.out.println("\t[9].  *Edit* \'Category Card\'");						//an interface (pretty as opposed to cracking open the manifest manually) to edit the object (and manifest??)
		System.out.println("\t[10]. *Edit* \'URL Card\'");							//an interface (pretty as opposed to cracking open the manifest manually) to edit the object (and manifest??)

		System.out.println();

		System.out.println("\t[11]. *Help* (explains all the options and what they do)");   //populate like man page... with info above (technical explination, human explination, hints, tips tricks....)
		System.out.println("\t[12]. *Exit* the application");

 		System.out.println();

 		//do loop logic
 		boolean isEntryIncorrect;
 		int menuSelection;
 		Scanner scan = new Scanner(System.in);

 		do {

 			System.out.print("Please enter a selection number ([?]) from the menu index: ");
 			menuSelection = scan.nextInt();
 			scan.nextLine(); //nextInt error correction

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
		int selection;
		Scanner scan = new Scanner(System.in);

		System.out.println("::Enter help boilerplate here...::");
		System.out.println();

		selection = scan.nextInt();
 		scan.nextLine(); //nextInt error correction		
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


