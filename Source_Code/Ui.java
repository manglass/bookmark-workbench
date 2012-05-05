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
}