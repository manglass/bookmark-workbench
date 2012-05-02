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
	public void viewUrlCard()
	{
		//user 
		//search for card (insertion sort?)
		//
	}

	public void viewUrlList()
	{

	}

	public void viewCategoryCard()
	{

	}

	public void viewCategoryList()
	{
		System.out.println();
		System.out.println("*****************");
		System.out.println("** Categories: **");
		System.out.println("*****************");
		System.out.println();

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

	public void viewToDoList()
	{

	}

	public void boilerPlate(MessageType message)
	{
		switch(message)
		{
			case ListNumberOfCategoriesUrlsandToDos :
			{
				System.out.println();
				System.out.println();

				System.out.println("This session:");
				System.out.println("------------ ");
				System.out.println("There are " + UrlCard.getAllUrlsCount() + " Urls");
				System.out.println("and " + CategoryCard.getAllCategoryCount() + " Categories");
				System.out.println();
				System.out.println("--> You have " + TodoCard.getAllTodosCount() + " active todo items!");

				System.out.println();
				System.out.println();
			}

				break;

			case ManuallyAddBrowserSessionFile :			
			{
				System.out.println();
				System.out.println();

				System.out.println("*********************************************");
				System.out.println("**   Manually add a Browser Session File   **");
				System.out.println("*********************************************");

				System.out.println();
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

}//end UiCli class