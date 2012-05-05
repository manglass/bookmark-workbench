/*****************************************************************************************
Ui.java

Parent class of: UiCli and UiGui

This family of classes supplies the views and partial graphic elements for the program.

	Methods:
		main(String[])	//unit test method

*****************************************************************************************/
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.*;
import java.util.*;

class Ui extends JPanel
{
	public void viewUrlCard(UrlCard url)
	{

	}

	public void viewUrlList(ArrayList<CategoryCard> list)
	{

	}

	public void viewCategoryCard()
	{

	}

	public void viewCategoryList()
	{

	}

	public void viewTodoList(ArrayList<CategoryCard> list)
	{

	}

	public void editUrlCard()
	{

	}

	public void editCategoryCard()
	{

	}

	public void boilerPlate(MessageType message)
	{

	}

	public boolean userChoice(MessageType message)
	{
		return true; //dummy return value
	}

	public void warningMessage(MessageType message)
	{

	}

	public void neutralMessage(MessageType message)
	{

	}

	public ArrayList<Integer> urlSearch(String query)
	{
		ArrayList<UrlCard> urls = UrlCard.getAllUrls();
		ArrayList<Integer> results = new ArrayList<Integer>();

		query = query.replace(" ", "\\s*"); //spaces --> regex
		Pattern scrubbedQuery = Pattern.compile("(?i)(.*\\s*" + query + "\\s*.*)"); //eee(USE!)

		for(int i=0;i<urls.size();i++)
		{
			String title = urls.get(i).getTitle();
			boolean match;

			Matcher m = scrubbedQuery.matcher(title);
			match = m.matches();

			String bo = Boolean.toString(match); //eee+
			System.out.println("Match :" + title + ", to users input: " + scrubbedQuery + ", is " + bo); //eee+			

			if(match)
				results.add(i);
		}

		return results;
	}

	public int urlResultSet(ArrayList<Integer> results) 
	{ 	//eee(USE ALL this method)
		int urlIndex = 0;
		ArrayList<UrlCard> urls = UrlCard.getAllUrls();
		Scanner scan = new Scanner(System.in);

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
							    "please enter the id number of the url you would like to view.");
			
			boolean present = false;

			do {	
					System.out.println();
					System.out.println();
					System.out.println("Remeber, the id number must be present in the set above: ");

					boolean correctInput;

					do {
						
						try {
						
							urlIndex = Integer.parseInt(scan.nextLine());
							correctInput = true;
					
						} catch (NumberFormatException e) 
						  {System.out.println("\nPlease, enter an id number...\n"); correctInput = false;}
					
					} while(!correctInput);

					for(int result = 0; result<results.size(); result++)
					{
						if(urlIndex == results.get(result))
							present = true;
					}

			} while(!present);

		return urlIndex;
	}
}