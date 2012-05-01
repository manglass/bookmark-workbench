/*****************************************************************************************
NoteParser.java

This class parses markup and outputs clean formatted text.

	Methods:
		void main(String[]) //unit test method		

*****************************************************************************************/

import java.util.*;

class NoteParser //extends Parser 
{
	public static ArrayList<String> clean(String line)
	{

		Scanner lineScan;
		ArrayList<String> notes = new ArrayList<String>();		

		lineScan = new Scanner (line);
		lineScan.useDelimiter("\\[\\[/Notes\\]\\]");

		while (lineScan.hasNext())
		{
			String word;

			word = lineScan.next();
			word = word.replace("[[/Notes]]", "");
			word = word.replace("[[Notes]]", "");			

			notes.add(word);
		}

		return notes;
	}

	//-------------------------------------------------------------------------------
	// NoteParser.java Unit Tests:
	//-------------------------------------------------------------------------------

	public static void main(String[] args)
	{
		ArrayList<String> array = new ArrayList<String>();
		String string = "[[Notes]]This is a test note.  Test! 1 TWO.[[/Notes]]";

		System.out.println("Pre-clean : " + string);

		array = NoteParser.clean(string);

		for (int i = 0; i < array.size(); i++)
			System.out.println("Post-clean : " + array.get(i));
	}
}