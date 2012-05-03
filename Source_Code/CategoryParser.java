/*****************************************************************************************
CategoryParser.java

This class parses the '*_main_manifest' file and creates the '*_category_manifest' file.

This will load a user's saved '*_category_manifest' into working memory (creating objects, etc).

	Methods:
		boolean isPresent(String, String)
		void main(String[]) //unit test method		

*****************************************************************************************/

import java.util.*;
import java.io.*;

class CategoryParser extends Parser 
{
	public static void initialize(String fileName) throws IOException
	{
		CategoryCard.initialize();
		Parser category = new CategoryParser();
		category.load(fileName);
		//UiCli.neutralMessage(MessageType.CompletedParsingCategoryManifest); //concider not showing this alert to user? -- instead write it to a log file with date stamp?
	}

	public File load(String fileName) throws IOException
	{
		//overwrites Parser.load() implementation: doesnt iterate, just preps file

		File f;
  		f = new File(fileName);
  		
  		if (!f.exists())
  		{
  			f.createNewFile();
  		}

  		return f;
	}

	public void update(String line, LineStatus status, Scanner fileScan) 
	{
		Parser category = new CategoryParser();
		Scanner lineScan;
		System.out.println();//eee+
		System.out.println("CategoryParser----in update -- LINE: " + line + "is : " + status.toString());//eee+
		
		lineScan = new Scanner (line);
		lineScan.useDelimiter(", ");

		while (lineScan.hasNext())
		{
			String word;

			word = lineScan.next();
			word = word.replace("[[/Category]]", "");
			word = word.replace("[[Category]]", "");
			System.out.println();//eee+
			System.out.println("CategoryParser---- line is scrubbed, now:" + word);//eee+

			category.isPresent(word);
		}
	}

	public static ArrayList<String> clean(String line)
	{
		Scanner lineScan;
		ArrayList<String> categories = new ArrayList<String>();
		
		lineScan = new Scanner (line);
		lineScan.useDelimiter(", ");

		while (lineScan.hasNext())
		{
			String word;

			word = lineScan.next();
			word = word.replace("[[/Category]]", "");
			word = word.replace("[[Category]]", "");

			categories.add(word);
		}

		return categories;
	}

	private static void invoke(String title)
	{
		ArrayList<CategoryCard> categoryArray = CategoryCard.getAllCategory();
		System.out.println();//eee+
		System.out.println("CategoryParser---- creates array, which is: " + categoryArray.size());//eee+
		System.out.println();//eee+
		System.out.println("CategoryParser---- creates new category object");//eee+		
		categoryArray.add(new CategoryCard(title));
	}

	public void associate(ArrayList<UrlCard> urlsList, ArrayList<CategoryCard> categoriesList)
	{
		for (int url = 0; url < urlsList.size(); url++)
		{
			ArrayList<String> urlCategoryList = urlsList.get(url).getCategory();
			
			for (int urlCategory = 0; urlCategory < urlCategoryList.size(); urlCategory++)
			{
				for (int category = 0; category < categoriesList.size(); category++)
				{
					if(categoriesList.get(category).getTitle().equals(urlCategoryList.get(urlCategory)));
						categoriesList.get(category).setUrl(urlsList.get(url));					
				}
			}			
		}

	}

	public void isPresent(String arg)
	{
		boolean isPresent = false;
		Parser category = new CategoryParser();
		File f = category.load(BookmarkWorkbench.categoryManifest);

  		try {

				Scanner fileScan = new Scanner(f);

		  		while (fileScan.hasNext())
		  		{
		  			String line = fileScan.nextLine();
		  			if(arg.equalsIgnoreCase(line))
		  			{
		  				isPresent = true;
		  			}
		  		}	

		  		if(isPresent)
		  		{
		  			invoke(arg);
		  		}
		  		else
		  		{
					add(arg, BookmarkWorkbench.categoryManifest);
					invoke(arg);		  					  			
		  		}

		} catch (Exception e) {System.err.println("Error: " + e.getMessage());}			
	}

	//-------------------------------------------------------------------------------
	// CategoryParser.java Unit Tests:
	//-------------------------------------------------------------------------------

	public static void main(String[] args)
	{

	}
}