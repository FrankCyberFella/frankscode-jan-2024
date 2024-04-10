package com.frank;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * File I/O Example
 *
 */
public class FileIOExample {
	
	/*****************************************************************************************************************************
	 * variables shared by all methods in the application
	 *****************************************************************************************************************************/
	
    // Define object to interact with user via keyboard
	static private Scanner     userInput;
	
	// Define a File object for the output file we want to create and write to	
	static private File        theOutputFile;
	
	// Define a PrintWriter for the File object represent our output disk file
	static private PrintWriter file2Write;
	
	static private boolean     shouldDisplayLines;
	
	/*****************************************************************************************************************************
	 * Constructor to initialize variables defined for sharing in the Application
	 * 
	 * (Note: This could also be a static constructor as all variables are static
	 *             this was not done to simplify the code for teaching purposes.
	 *             
	 *             If a static constructor WAS used it would like this:
	 *             
	 *             	static() {
	 *						  userInput          = null;
	 *						  theOutputFile      = null;
	 *						  file2Write         = null;
	 *						  shouldDisplayLines = false;
	 *						 }          
	 *           
	 *****************************************************************************************************************************/
	
	public FileIOExample() {
		userInput          = null;
		theOutputFile      = null;
		file2Write         = null;
		shouldDisplayLines = false;
	}
	

    public static void main( String[] args ) throws IOException
    {
        System.out.println("-".repeat(80)  
        		          +"\n"
        		          +"File I/O Example Program Starting"         		         
        		          +"\n"
        		          +"-".repeat(80)
        		          +"\n");
        
        // Assign userInput to keyboard
        userInput = new Scanner(System.in);
    
    	/****************************************************************************************************************
    	 * Defining a File object for the file on our computer we want to read
    	 * 
    	 * Provide a path to the file when defining a File object
    	 *
    	 * Paths can be: absolute - code all parts from the root folder of your OS (Windows / Mac / Linux)
    	 *
    	 *               relative - code the parts from the assumed current position to the file
    	 *
    	 * absolute paths should be avoided - they tightly coupled the program to directory structure it was created on
    	 *                                    if the program is run on a machine with a different directory it won't work
    	 *                                       as the absolute path doesn't exist in a different directory structure
    	 *
    	 * relatives paths are preferred because you have loosely coupled the file to directory structure
    	 *                 it is more likely that the relative paths will be the same from machine to machine
    	 *
    	 * relative path: . = current directory
    	 *                / = then (sub-directory or file follows)
    	 *                declarationOfIndependence.txt - file name
    	 *
    	 *******************************************************************************************************************/
     
        File theFile = new File("./declarationOfIndependence.txt");
         
        verifyInputFile(theFile);
        
        Scanner file2Read = new Scanner(theFile);
     
		setupOutputFile("theOutputFile.txt");
		
        // Determine if user would like lines from input file displayed during processing
        shouldDisplayLines = askUserIfDisplayOfLinesDesired();
		
		if(shouldDisplayLines) {        
			displayLine("-".repeat(80) + "\nStart-of-File-Contents\n" + "-".repeat(80) , 80);
		}
        while(file2Read.hasNext()) {
        	String lineFromFile = file2Read.nextLine();
    		if(shouldDisplayLines) { 
    			displayLine(lineFromFile, 80);
    		}
        	writeLineToFile(lineFromFile, 80);
	    }
        
        takeDownFiles(file2Read);
        
		if(shouldDisplayLines) { 
			displayLine("\n" + "-".repeat(80) + "\nEnd-of-File-Contents\n" + "-".repeat(80), 80);
		}
		
		displayLine("\n" + "-".repeat(80) + "\nFile-I/O-Example-Program-Ending\n" + "-".repeat(80), 80);
    }
  
    /*********************************************************************************************************************************
     * Class Helper Methods for Application
     *********************************************************************************************************************************/

    private static File verifyInputFile(File theInputFile) {
    	// Check to be sure the File Object is assigned an existing file - terminate if not
    	//
		// .exists() - is the path specified a name in the file system
		// .isFile() - is the name the name of a file in the file system
		// Since we can only read files in a Java program - we want to be sure the path we have is a file
		// if path does not exist or the path is not a file - terminate the program with a message
		if (!theInputFile.exists() || !theInputFile.isFile()) {
			System.out.println("----- Uh-Oh!! uh-Oh! ----\n"
							  +"Input file path specified: \n \t \"" 
		                      + theInputFile.getAbsolutePath() 
		                      + "\"\nis not an existing file"
		                      + "\n----- Terminating program! -----");
			// There are two way to terminate a program/method/function:
			//    1. return statement - return to the called of the method/program/function
			//    2. System.exit(value) - return to the Operating System - all programs in the call stack are terminated
	        //                            with the value specified in the () - a return code
			//                            return codes may be checked by scripting languages that execute applications
			//                            the meaning of return codes is up to the script that checks them
			//                            in general return codes are multiples of 4
			//                                0 - usually means no errors - program was successful
			//                                4 - usually means there were minor errors, but program was still successful
			//                                8 - usually means a serious error, program completed, but results may be incorrecy
			//                               12 - usually means a severe error, program completed, but NOT successfully
			//                               16 - usually means a catastrophic, error program did not even do much of anything
			System.exit(16);   // terminate program with a return code of 16 - whatever that means
		}
		return theInputFile;
    }
    
    
    
    private static void displayLine(String aLine, int maxLineSize) {
    	
    	String[] words = aLine.split(" ");
    	
		int charsDisplayed = 0;
    	
    	for (String aWord : words) {
    			
   			System.out.print(aWord + " ");
   			charsDisplayed += aWord.length();
    			
   			if(charsDisplayed > maxLineSize) {
   		    	System.out.println();
   		    	charsDisplayed = 0;
   			}
    	}
    	System.out.println();
    }
    
 private static void writeLineToFile(String aLine, int maxLineSize) throws IOException {
    		
	String[] words = aLine.split(" ");
	
	int charsDisplayed = 0;
	
	for (String aWord : words) {
			
		file2Write.print(aWord + " ");
		charsDisplayed += aWord.length();
			
		if(charsDisplayed > maxLineSize) {
	    	file2Write.println();
	    	charsDisplayed = 0;
		}
	}
	file2Write.println();
 	}
 
 
 private static void setupOutputFile(String fileName) throws IOException {
	 
	// Assign the name of the output file to the File object representing the file 
	// We want the file in the project root folder
	// We want to call it "theOutputFile.txt"
	if(theOutputFile == null) {
		theOutputFile= new File(fileName);
	}
	
	// Tell Java to actually create the file in the file system
	theOutputFile.createNewFile();   // allocate a new - any existing version is destroyed

	// Set the PrintWriter to the File object representing our output disk file
		file2Write = new PrintWriter(theOutputFile);	
 }
 
private static void takeDownFiles(Scanner inputFile) {
     inputFile.close();
 	 file2Write.close();
}
 
private static boolean askUserIfDisplayOfLinesDesired() {
	 
	 System.out.println("Would you like to display lines from input file? (Y or N)");
     String aLine = userInput.nextLine();
      
     if(aLine.substring(0,1).toUpperCase().equals("Y")) {
    	return true;
     }    
     return false;	 
 }
}
