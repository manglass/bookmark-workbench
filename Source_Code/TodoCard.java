/*****************************************************************************************
TodoCard.java

This class creates instances of TodoCard objects. 

	Methods:

		void main(String[]) //unit test method

*****************************************************************************************/

import java.util.*;

class TodoCard implements Card
{	

	//the idea here is to have multiple card objects which are todo lists comprised of different categories/url bases, or other choices... or main list of all

	public static ArrayList<TodoCard> allTodoLists;
	private static int allTodoListsCount = 0;
	private static int allTodosCount = 0; //increment with every to do entered... (different than others)

	private String title, url;
	private ArrayList<String> category, todo, notes;

	public TodoCard(String title)
	{
		this.title = title;
		url = "";
		category = new ArrayList<String>();
		todo = new ArrayList<String>();
		notes = new ArrayList<String>();

		//allUrlsCount++;
	}

	public void setUrl(UrlCard url)
	{
		//this.url = url;
	}

	public String getUrl()
	{
		return url;
	}

	public void setCategory(ArrayList<String> category) //or Category card?
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
		TodoCard.allTodoLists = new ArrayList<TodoCard>();
	}

	public static int getAllTodosCount()
	{
		return allTodosCount;
	}

	public static ArrayList<TodoCard> getAllTodoLists()
	{
		return allTodoLists;
	}

	public static void clearOut()
	{
		allTodoLists.clear();
	}

	//-------------------------------------------------------------------------------
	// TodoCard.java Unit Tests:
	//-------------------------------------------------------------------------------

	public static void main(String[] args)
	{

	}

}