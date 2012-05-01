/*****************************************************************************************
UrlCard.java

This class creates instances of UrlCard objects. 

	Methods:

		void main(String[]) //unit test method

*****************************************************************************************/

import java.util.*;

class UrlCard implements Card
{
	public static ArrayList<UrlCard> allUrls;
	private static int allUrlsCount = 0;

	private String title, url;
	private ArrayList<String> category, todo, notes;

	public UrlCard(String title)
	{
		this.title = title;
		url = "";
		category = new ArrayList<String>();
		todo = new ArrayList<String>();
		notes = new ArrayList<String>();

		allUrlsCount++;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getUrl()
	{
		return url;
	}

	public void setCategory(ArrayList<String> category)
	{
		this.category.addAll(category);
	}

	public ArrayList<String> getCategory()
	{
		return category;
	}

	public void showCategory()
	{
		for (int i = 0; i < category.size(); i++) {System.out.println(category.get(i));}	
	}

	public void setTodo(ArrayList<String> todo)
	{
		this.todo.addAll(todo);
	}

	public ArrayList<String> getTodo()
	{
		return todo;
	}

	public void showTodo()
	{
		for (int i = 0; i < todo.size(); i++) {System.out.println(todo.get(i));}		
	}

	public void setNotes(ArrayList<String> notes)
	{
		this.notes.addAll(notes);
	}

	public ArrayList<String> getNotes()
	{
		return notes;	
	}

	public void showNotes()
	{
		for (int i = 0; i < notes.size(); i++) {System.out.println(notes.get(i));}		
	}

	public String toString()
	{
		return "Url :" + "/n" + title + "/n" + url;
	}

	public static void initialize()
	{
		UrlCard.allUrls = new ArrayList<UrlCard>();
	}

	public static int getAllUrlsCount()
	{
		return allUrlsCount;
	}

	//-------------------------------------------------------------------------------
	// UrlCard.java Unit Tests:
	//-------------------------------------------------------------------------------

	public static void main(String[] args)
	{

	}
}