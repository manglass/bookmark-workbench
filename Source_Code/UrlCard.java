/*****************************************************************************************
UrlCard.java

This class creates instances of UrlCard objects. 

	Methods:

		void main(String[]) //unit test method

*****************************************************************************************/

import java.util.*;

class UrlCard implements Card
{
	private static ArrayList<UrlCard> allUrls; //create add interface to encapsulate away from destructive changes, but allow necessary changes
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

		allUrlsCount = allUrls.size() + 1;
	}

	public String getTitle()
	{
		return title;
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
		for (int i = 0; i < category.size(); i++) {System.out.println("\n" + category.get(i));}	
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
		for (int i = 0; i < todo.size(); i++) {System.out.println("\t" + todo.get(i));}		
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
		return "\n" + title + "\n" + url;
	}

	public String prettyPrint()
	{
		return "\n|  " + title + "\n|  \n|  " + url;
	}

	public static void serialize(String fileName)
	{
		for(int i = 0; i<allUrls.size(); i++)
		{
			UrlCard writeUrl = allUrls.get(i);
			encodeTitle(writeUrl.title);
			encodeUrl(writeUrl.url);
			encodeCateogry(writeUrl.category);
			encodeTodo(writeUrl.todo);
			encodeNotes(writeUrl.notes);
		}
	}

	private static void encodeTitle(String line)
	{
		ManifestParser.add("", BookmarkWorkbench.mainManifest);		
		ManifestParser.add(line, BookmarkWorkbench.mainManifest);
	}

	private static void encodeUrl(String line)
	{
		ManifestParser.add(line, BookmarkWorkbench.mainManifest);
	}

	private static void encodeCateogry(ArrayList<String> array)
	{
		String categories = "[[Category]]";

		if(array.size()>0)
		{
			for(int i=0; i<array.size() - 1;i++)
			{
				String element = array.get(i).concat(", ");
				categories = categories.concat(element);
			}

			categories = categories.concat(array.get(array.size() - 1));
		}
		
		categories = categories.concat("[[/Category]]");

		ManifestParser.add(categories, BookmarkWorkbench.mainManifest);
	}	

	private static void encodeTodo(ArrayList<String> array)
	{
		String todos = "[[ToDo]]";

		if(array.size()>0)
		{		
			for(int i=0; i<array.size() - 1;i++)
				{	
					String element = array.get(i);
					element = element.replace("* ", "*");
					element = element.concat(", ");
					todos = todos.concat(element);
				}
	
				todos = todos.concat(array.get(array.size() - 1).replace("* ", "*"));
		}

		todos = todos.concat("[[/ToDo]]");

		ManifestParser.add(todos, BookmarkWorkbench.mainManifest);		
	}

	private static void encodeNotes(ArrayList<String> array)
	{
		String notes = "[[Notes]]";

		if(array.size()>0)
		{	
			for(int i=0; i<array.size();i++)
			{
				String element = array.get(i);
				notes = notes.concat(element);
			}
		}

		notes = notes.concat("[[/Notes]]");

		ManifestParser.add(notes, BookmarkWorkbench.mainManifest);
	}

	public static void initialize()
	{
		allUrls = new ArrayList<UrlCard>();
	}

	public static int getAllUrlsCount()
	{
		return allUrlsCount;
	}

	public static int todosCount()
	{
		int todoCount = 0;

		for (int url=0; url<allUrls.size() ;url++)
		{
			ArrayList<String> todos = allUrls.get(url).getTodo();

			for (int todo=0; todo<todos.size(); todo++)
			{
				todoCount++;
			}
		}

		return todoCount;
	}

	public static ArrayList<UrlCard> getAllUrls()
	{
		return allUrls;
	}

	public static void clearOut()
	{
		allUrls.clear();
	}

	//-------------------------------------------------------------------------------
	// UrlCard.java Unit Tests:
	//-------------------------------------------------------------------------------

	public static void main(String[] args)
	{
		ArrayList<UrlCard> array;
		array = UrlCard.getAllUrls();

	}
}