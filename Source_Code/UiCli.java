/*****************************************************************************************
UiCli.java

This is a class of reusable partial UI elements.

These are used in CLI views.  


	Methods:
		main(String[])	//unit test method

*****************************************************************************************/

import java.util.*;
import java.io.*;

class UiCli extends Ui
{
	public void viewUrlCard(UrlCard url)
	{
		System.out.println();
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println();

		System.out.println(url.prettyPrint()); //temp view, need to add ALL attributes and card-like structure.
		System.out.println();

		System.out.println();
		System.out.println("Categories: ");
		System.out.println("----------"  );		
		System.out.println();

		url.showCategory();
		System.out.println();

		System.out.println();
		System.out.println("Todos: ");
		System.out.println("-----"  );		
		System.out.println();
		
		url.showTodo();
		System.out.println();

		System.out.println("Notes: ");
		System.out.println("-----"  );		
		System.out.println();

		url.showNotes();
		System.out.println();

		System.out.println("-------------------------------------------------------------------------------");
		System.out.println();
	}

	public void viewUrlList(ArrayList<CategoryCard> list)
	{
		for (int i = 0; i<list.size(); i++)
		{
			System.out.print(printBox(list.get(i).getTitle(), InterfaceElements.TitleBox, "*", "*", 1));	
			list.get(i).showUrl();
			System.out.println();
		}
	}

	public void viewCategoryCard()
	{

	}

	public void viewCategoryList()
	{
		System.out.println();
		System.out.print(printBox("Categories:", InterfaceElements.TitleBox, "*", "*", 1));	

		try {

				Scanner fileScan = new Scanner(new File(BookmarkWorkbench.categoryManifest));
	
	
				while (fileScan.hasNext())
		  		{
		  			String category = fileScan.nextLine();

		  			System.out.println();
		  			System.out.println(category);
		  			for(int i=0; i<category.length(); i++)
		  			{
		  				System.out.print("-");
		  			}
		  			System.out.println();
		  		}	
		  		
		} catch (Exception e) {System.err.println("Error: " + e.getMessage());}	
	}

	public void viewTodoList(ArrayList<CategoryCard> list)
	{
		String categoryTitle;

		for (int i = 0; i<list.size(); i++)
		{
			CategoryCard categorycard = list.get(i);
			categoryTitle = categorycard.getTitle();

			System.out.println();
			System.out.println();

			System.out.print(printBox(categoryTitle, InterfaceElements.TitleBox, "*", "*", 1));

			System.out.println();

			ArrayList<UrlCard> url = categorycard.getUrl();

			for (int j = 0; j<url.size(); j++)
			{
				UrlCard urlcard = url.get(j);

				System.out.println();
				System.out.println(urlcard.prettyPrint());

				System.out.println();
				urlcard.showTodo();
			}
		}
	}

	public void editUrlCard() throws IOException
	{
		Scanner scan = new Scanner(System.in);
		String newCategory, newTodo, newNote;

		System.out.println();
		System.out.println("You will be presented with a listing of available urls indexed by ID#.");
		System.out.println("Afterward you will be prompted to enter the ID# of the url you would like to edit. ");
		System.out.println();
		System.out.println("Would you like to continue on with the editing process?");
		System.out.println();

		System.out.println("\t[1] Yes.");
		System.out.println("\t[2] No.");
		System.out.println();
		System.out.println();		

		//do loop logic
		boolean isEntryIncorrect, answer = true;

		do {

			int userOption = 0;

			System.out.print("Please enter option 1 or 2: ");
			userOption = scan.nextInt();
			scan.nextLine(); //nextInt error correction
			System.out.println();

			if(userOption == 1)
			{
				isEntryIncorrect = false;
				answer = true;
			}
			else if(userOption == 2)
			{
				isEntryIncorrect = false;
				answer = false;
			}
			else
				isEntryIncorrect = true;

		} while(isEntryIncorrect);		

		if (answer) //if user wants to continue, continue...
		{	
			ArrayList<UrlCard> allUrls = UrlCard.getAllUrls();

			if(allUrls.size()>0) //if there are urls available in the system, continue...
			{	
				for(int url = 0; url<allUrls.size(); url++)
				{
					System.out.print("ID#: " + url);
					System.out.println(allUrls.get(url));
					System.out.println();
				}

				System.out.println();

				int urlID = 0;
				boolean correctInput;

				do {
					
					try {
						
						System.out.print("Type the ID# for the url you would like to edit and press enter (be sure the ID# is available): ");

						urlID = Integer.parseInt(scan.nextLine());
						
						if(urlID>=allUrls.size() || urlID<0)
							correctInput = false;
						else
							correctInput = true;
				
					} catch (NumberFormatException e) 
					  {System.out.println("\nPlease, enter an id number...\n"); correctInput = false;}
				
				} while(!correctInput);

				System.out.println();

				//Category
				System.out.println("If you would like to add a Category, type the title and press the enter key.");
				System.out.print("If you have nothing new to type, just hit the enter key: ");				
				newCategory = scan.nextLine();
				if(newCategory.isEmpty())
					{System.out.println("\t >> No category will be added."); System.out.println();}
				else
				{
					CategoryParser category = new CategoryParser();
					category.isPresent(newCategory);
					UrlCard.getAllUrls().get(urlID).addCategory(newCategory);
					System.out.println("\t >> '" + newCategory + "' has been added to the category information about this Url."); 
					System.out.println();
				}

				//Todo
				System.out.println("If you would like to add a Todo item, type it here and press the enter key (just enter a line of text, '*' will be added automatically).");
				System.out.print("If you have nothing new to type, just hit the enter key: ");
				newTodo = scan.nextLine();
				if(newTodo.isEmpty())
					{System.out.println("\t >> No todo item will be added."); System.out.println();}
				else
				{
					UrlCard.getAllUrls().get(urlID).addTodo(newTodo);
					System.out.println("\t >> '* " + newTodo + "' has been added to the todo information about this Url.");
					System.out.println();
				}

				//Note
				System.out.println("If you would like to add a Note, type it out here and press the enter key.");
				System.out.print("If you have nothing new to type, just hit the enter key: ");				
				newNote = scan.nextLine();
				if(newNote.isEmpty())
					{System.out.println("\t >> No note will be added."); System.out.println();}
				else
				{
					UrlCard.getAllUrls().get(urlID).addNotes(" " + newNote);
					System.out.println("\t >> '" + newNote + "' has been added to the notes information about this Url.");
					System.out.println();
				}

				Parser session = new SessionParser();

				ManifestParser.reset();
				SessionParser.add("", BookmarkWorkbench.mainManifest);		
				SessionParser.scrap();
				ManifestParser.initialize(BookmarkWorkbench.mainManifest.replace("_main_manifest.txt", ""));

				System.out.println(UrlCard.getAllUrls().get(urlID) + "\n \t>> Has been updated!");
			}
			else
			{
				System.out.println();
				System.out.println("There are no URLs available in the system at this time, please add some!");
				System.out.println();
			}
		}
	}

	public void editCategoryCard()
	{
		
	}

	public void boilerPlate(MessageType message)
	{
		switch(message)
		{
			case IntroCredits :
			{
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
			}

				break;

			case SignUp :
			{
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
			}

				break;

			case SignIn :
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
			}

				break;

			case ListNumberOfCategoriesUrlsandToDos :
			{
				System.out.println();
				System.out.println();

				System.out.println("This session:");
				System.out.println("------------ ");
				System.out.println("There are " + UrlCard.getAllUrlsCount() + " Urls");
				System.out.println("and " + CategoryCard.getAllCategoryCount() + " Categories");
				System.out.println();
				System.out.println("--> You have " + UrlCard.todosCount() + " active todo items!");

				System.out.println();
				System.out.println();
			}

				break;

			case MainMenuHead :
			{
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();

				System.out.println("\t\t+-++-++-++-+ +-++-++-++-+");
				System.out.println("\t\t|M||a||i||n| |M||e||n||u|");
		 		System.out.println("\t\t+-++-++-++-+ +-++-++-++-+");

		 		System.out.println();
		 		System.out.println(); 		

		 	}

		 		break;

		 	case MainMenuBody :
		 	{	
		 		System.out.println();
		 		System.out.println(); 
		 
				System.out.println("\t[1].  *Add* Browser \'Session File\'");
				
				System.out.println();

				System.out.println("\t[2].  *List all* \'Categories\'");					        
				System.out.println("\t[3].  *List all* \'URLs\' (ordered by Category)");	        
				System.out.println("\t[4].  *List all* \'To Do\' Items (ordered by Category)");		

				System.out.println();

				System.out.println("\t[5].  *View Selected* \'URL Card\'");					
				System.out.println("\t[6].  *View Random* \'URL Card\'");				

				System.out.println();

				System.out.println("\t[7].  *Edit Selected* \'URL Card\'");

				System.out.println();

				System.out.println("\t[8].  *Help* (explains all the options and what they do)");  
				System.out.println("\t[9].  *Exit* the application");

		 		System.out.println();
			}

				break;

			case ManuallyAddBrowserSessionFile :			
			{
				System.out.println();
				System.out.println();

				System.out.print(printBox("Manually add a Browser Session File", InterfaceElements.TitleBox, "*", "*", 1));

				System.out.println();

				System.out.println("Prior to the load process: ");


				System.out.println();
				System.out.println("\t* Be sure the session file is in the proper directory (\'Bookmark_Workbench/Source_Code/\')");
				System.out.println("\t* Be sure the session filename format is correct (ex. \'Session_201204281337.txt\')");
				System.out.println("\t* Consult help and documentation for the proper session file format.");

				System.out.println();
				System.out.println();

				System.out.println("If everything is in order... lets begin!");
				System.out.println();
			}

				break;

			case EndingCredits :
			{
				System.out.println();
				System.out.println();		

				System.out.println();
				System.out.println("\t   ****************************************************************************");
				System.out.println("\t *******                                                           	  *******");
				System.out.println("\t******   Thanks for using Bookmark Workbench, your URLs are safe with us!   ******");
				System.out.println("\t *******	                                                          *******");
				System.out.println("\t   ****************************************************************************");

				System.out.println();
				System.out.println();

				System.out.println("\t\t\t                          Gee, Thanks 	");
		        System.out.println("\t\t\t                            | 			");                    
				System.out.println("\t\t\t                           |  			");
				System.out.println("\t\t\t           /\\       /\\   |   			");
				System.out.println("\t\t\t            <O>   <O>         			");
				System.out.println("\t\t\t          ==    =    ==       			");
				System.out.println("\t\t\t          ==    ^    ==       			");
				System.out.println("\t\t\t              ---*-           			");
				System.out.println("\t\t\t              000000    888888			");
				System.out.println("\t\t\t             00000000   8   88			");
				System.out.println("\t\t\t             00000000       88			");		
		        System.out.println("\t\t\t             00!!000000     88			");
		        System.out.println("\t\t\t             !!00!!000000   88			");
		        System.out.println("\t\t\t             !!00!!0000000  88			");    
		        System.out.println("\t\t\t             !!00!!0000000  88			");
				System.out.println("\t\t\t             oo  oo 000000 88 			");
				System.out.println("\t\t\t            ,,o ,,o 00000088  			");
				System.out.println("\t\t\t                              			");

				System.out.println();				
			}

				break;
		}
	}

	public boolean userChoice(MessageType message)
	{
		boolean userChoiceReturnValue = true;

		switch(message)
		{
			case YesOrNo :			
			{
				System.out.println("\t[1] Yes.");
				System.out.println("\t[2] No.");
				System.out.println();
				System.out.println();		

				//do loop logic
				Scanner scan = new Scanner(System.in);
				boolean isEntryIncorrect, answer = true;

				do {

					int userOption = 0;

					System.out.print("Please enter option 1 or 2: ");
					userOption = scan.nextInt();
					scan.nextLine(); //nextInt error correction
					System.out.println();

					if(userOption == 1)
					{
						isEntryIncorrect = false;
						answer = true;
					}
					else if(userOption == 2)
					{
						isEntryIncorrect = false;
						answer = false;
					}
					else
						isEntryIncorrect = true;

				} while(isEntryIncorrect);
			
				userChoiceReturnValue = answer;
			}

				break;

			case FirstTimeOrReturning :
			{
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
				boolean isEntryIncorrect;

				do {

					int userOption = 0;

					System.out.print("Please enter option 1 or 2: ");
					userOption = Integer.parseInt(scan.nextLine());
					System.out.println();

					if(userOption == 1)
					{
						userChoiceReturnValue = true;
						isEntryIncorrect = false;
					}
					else if(userOption == 2)
					{
						userChoiceReturnValue = false;
						isEntryIncorrect = false;
					}
					else
						isEntryIncorrect = true;

			 	} while(isEntryIncorrect);
			}

				break;

			case UserDoesntExist :
			{
				System.out.println("*****************************************************************************");
				System.out.println("**   !! The username you entered does not exist in Bookmark Workbench !!   **");
				System.out.println("*****************************************************************************");
			    System.out.println();

				System.out.println("\t[1] I want to sign up and create a new username.");
				System.out.println("\t[2] I entered my username incorrectly, I want to try again.");
				System.out.println();

				Scanner scan = new Scanner(System.in);

				//do loop logic
				boolean isEntryIncorrect;

				do {

					int userOption = 0;

					System.out.print("Please enter option 1 or 2: ");
					userOption = Integer.parseInt(scan.nextLine());
					System.out.println();

					if(userOption == 1)
					{
						userChoiceReturnValue = true;
						isEntryIncorrect = false;
					}
					else if(userOption == 2)
					{
						userChoiceReturnValue = false;
						isEntryIncorrect = false;
					}
					else
						isEntryIncorrect = true;

			 	} while(isEntryIncorrect);
			}

				break;
		}

		return userChoiceReturnValue;
	}

	public void warningMessage(MessageType message)
	{
		switch(message)
		{
			case UsernameTaken :			
			{
				System.out.println("************************************************************************************");
				System.out.println("**   !! The username you have selected is already in use, please try another !!   **");
				System.out.println("************************************************************************************");
				System.out.println();
			}

				break;

			case ProblemParsingManifest :	
			{
				System.out.println("**********************************************************************************");
				System.out.println("**   !! The manifest file may be corrupted, there was a problem parsing it !!   **");
				System.out.println("**********************************************************************************");
				System.out.println();
			}

				break;


			case ProblemParsingSessionFile :	
			{
				System.out.println("*************************************************************************************************");
				System.out.println("**   !! There was a problem parsing your session file, the manifest file may be corrupted !!   **");
				System.out.println("*************************************************************************************************");
				System.out.println();
			}

				break;
		}
	}

	public void neutralMessage(MessageType message)
	{
		switch(message)
		{
			case CompletedParsingManifest :		
			{
				System.out.println("********************************************");
				System.out.println("**   %% The manifest has been loaded %%   **");
				System.out.println("********************************************");
				System.out.println();
			}

				break;

			case CompletedParsingSessionFile :			
			{
				System.out.println("*************************************************");
				System.out.println("**   %% Your session file has been loaded %%   **");
				System.out.println("*************************************************");
				System.out.println();
			}

				break;
		}
	}

	private static String printBox(String title, InterfaceElements boxType, String symbol, String endCap, int layers)
	{
		String newTitle = "-- title goes here --";

		switch(boxType)
		{
			case TitleBox :
			{
				//int 'layers' is unimplemented here for the time being

				String border = "";

				for (int i = 0; i<title.length() + 4; i++)
				{
					border += symbol;
				}

				String edging = endCap + endCap;
				String corner = "\n" + endCap + border + endCap + "\n";

				newTitle = corner + edging + " " + title + " " + edging + corner;
			}

				break;
		}

		return newTitle;
	}

}//end UiCli class