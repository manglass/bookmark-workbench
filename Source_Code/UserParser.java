/*****************************************************************************************
UserParser.java

This class reads the 'user_manifest' file.
  
	Methods:

		void main(String[]) //unit test method

*****************************************************************************************/

import java.util.*;
import java.io.*;

public class UserParser extends Parser
{
	public static boolean isPresent(String userName, String userManifest)
	{
		boolean isUserNameTaken = false;

		try {
		
			Scanner fileScan = new Scanner(new File(userManifest));
			String activatedUser;

			while (fileScan.hasNext())
			{
				activatedUser = fileScan.nextLine();
				if(activatedUser.equals(userName))
				{
					isUserNameTaken = true;
					UiCli.warningMessage(MessageType.UsernameTaken);
					break;
				}
			}

		} catch (Exception e) {System.err.println("Error: " + e.getMessage());}

		return isUserNameTaken;
	}


	//-------------------------------------------------------------------------------
	// UserParser.java Unit Tests:
	//-------------------------------------------------------------------------------

	public static void main(String[] args) throws IOException
	{
		//System.out.println("RUNNING: loadManifest(); ------------------------");
		//String testManifest = "testUser_main_manifest.txt"; 
		//load(testManifest);		
		//System.out.println("END: loadManifest(); ------------------------");
	}
}