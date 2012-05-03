import java.io.*;
import java.util.*;
import java.util.regex.*;


class Brainstorm_UrlCardSearch
{
/*
Pseudocode and quick mockup implementaiton.models/diagrams for brainstorming
----------------------------------------------------------------------------

[enter the method name, start adding logic.. then figure out what return/input parameters i need, factor and refactor until it is concise and elegant and uses the least dependencies and messy logic syntax constructs as possible!]
*/


/*

Higher Order flow

Url Card search --> returns (object in array's) index --> Ui.viewUrlCard --> see the card

Lower order syntax pseudo code below-->

*/


	public ArrayList<Integer> urlSearch(String query)
	{
		ArrayList<UrlCard> urls = UrlCard.getAllUrls();
		ArrayList<Integer> results = new ArrayList<Integer>();

		for(int i=0;i<urls.size();i++)
		{
			String title = urls.get(i).getTitle();
			boolean match = false;

			String userInput = "(?i)(.*\\s*" + query + "\\s*.*)"; //eee(USE!)
			match = Pattern.matches(userInput, title);
			String bo = Boolean.toString(match); //eee+
			System.out.println("Match :" + title + ", to users input: " + userInput + ", is " + bo); //eee+			

			if(match)
				results.add(i);
		}

		if(results.size()==0) //eee(USE!)
		{ //eee(USE!)
			System.out.println("Your search returned no results."); //eee(USE!, but enhance)
		} //eee(USE!)

		return results;
	}



/* Refactor: "The urlSearch can return more than one result!!!" */

/* 
1. save results as an array of ints.... 
2. view the Url title, Url and index of the objects at those indecies "urlResultSet"
3. have user select the index of the proper result
4. pass this index to Ui.viewUrlCard


Higher Order flow

Url Card search --> returns array of array indecies --> Url Card result set --> returns index --> Ui.viewUrlCard --> see the card

Lower order syntax pseudo code below-->

*/

	public int urlResultSet(ArrayList<Integer> results) 
	{ 	//eee(USE ALL this method)
		int urlIndex;
		ArrayList<UrlCard> urls = UrlCard.getAllUrls();
		Scanner scan = new Scanner(System.in);

		if (results.size()>0)
		{
			System.out.println();
			
			for(int url=0;url<urls.size();url++)
			{
				for(int result = 0; result<results.size(); result++)
				{
					if(results.get(result) == url)
					{
						System.out.println("ID#: " + url + "\n" + urls.get(url).prettyPrint());
						System.out.println();
					}
				}
					
			}		

			System.out.println();
			System.out.print("From the result set above, \n" +
							    "please enter the id number of the url you would like to view: ");
			
			urlIndex = scan.nextInt();
			scan.nextLine(); //nextInt error correction
		}
		else
		{
		urlIndex = 0;//eee(tmp)
		//what can i do to signify empty results set... return into search!?
		}

		return urlIndex;
	}




/* TRY IT OUT */

	public static void main(String[] args)
	{

	}
}


/* CONCIDERATIONS before integration --->

1. catch all edge cases and error cases:
	--what if initial array list is empty
	--what if during the urlResultSet, user enters an incorrect value?
	--...?
*/