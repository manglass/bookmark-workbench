/***********************************************************************************************
MessageType.java

Enumerated elements for use as symbols: 
	
	Used as arguements to call particular reusable UI elements.  
************************************************************************************************/

public enum MessageType 
{
	/* boilerPlate    */ ManuallyAddBrowserSessionFile, ListNumberOfCategoriesUrlsandToDos, EndingCredits,
	/* userChoice     */ YesOrNo,
	/* warningMessage */ UsernameTaken, ProblemParsingManifest, ProblemParsingSessionFile, 
	/* neutralMessage */ CompletedParsingManifest, CompletedParsingSessionFile
	/* successMessage */ 
}	