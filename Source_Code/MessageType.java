/***********************************************************************************************
MessageType.java

Enumerated elements for use as symbols: 
	
	Used as arguements to call particular reusable UI elements.  
************************************************************************************************/

public enum MessageType 
{
	/* boilerPlate    */ IntroCredits, SignUp, SignIn, ListNumberOfCategoriesUrlsandToDos, MainMenuHead, MainMenuBody, ManuallyAddBrowserSessionFile, EndingCredits,
	/* userChoice     */ YesOrNo, FirstTimeOrReturning, UserDoesntExist,
	/* warningMessage */ UsernameTaken, ProblemParsingManifest, ProblemParsingSessionFile, 
	/* neutralMessage */ CompletedParsingManifest, CompletedParsingSessionFile
	/* successMessage */ 
}	