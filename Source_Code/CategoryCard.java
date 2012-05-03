/*****************************************************************************************
CategoryCard.java

This class creates instances of CategoryCard objects. 

	Methods:

		void main(String[]) //unit test method

*****************************************************************************************/

import java.util.*;

class CategoryCard implements Card
{
	private static ArrayList<CategoryCard> allCategory; //create add interface
	private static int allCategoryCount = 0;

	private String title;
	private ArrayList<UrlCard> url;

	public CategoryCard(String title)
	{
		this.title = title;
		url = new ArrayList<UrlCard>();

		allCategoryCount++;
		System.out.println();//eee+
		System.out.println("CategoryCard---- new object created.");//eee+		
	}

	public String getTitle()
	{
		return title;
	}

	public void setUrl(UrlCard url)
	{
		this.url.add(url);
	}

	public ArrayList<UrlCard> getUrl()
	{
		return url;
	}

	public void showUrl()
	{
		for (int i = 0; i < url.size(); i++) {System.out.println(url.get(i));}	
	}

	public String toString()
	{
		return "Category: " + title;
	}

	public static void initialize()
	{
		allCategory = new ArrayList<CategoryCard>();
	}

	public static int getAllCategoryCount()
	{
		return allCategoryCount;
	}

	public static ArrayList<CategoryCard> getAllCategory()
	{
		return allCategory;
	}

	public static void clearOut()
	{
		allCategory.clear();
	}

	//-------------------------------------------------------------------------------
	// CategoryCard.java Unit Tests:
	//-------------------------------------------------------------------------------

	public static void main(String[] args)
	{
		initialize();
		System.out.println("There are " + CategoryCard.getAllCategoryCount() + " cards.");
		CategoryCard testCard1 = new CategoryCard("Category1");
		System.out.println();
		System.out.println("There is " + CategoryCard.getAllCategoryCount() + " card.");
		System.out.println("The card is... ");
		System.out.println(testCard1);
		System.out.println();

		//How do I test Url Cards without dependency? (Factories, Mocks/Stubs, etc?)

	}
}