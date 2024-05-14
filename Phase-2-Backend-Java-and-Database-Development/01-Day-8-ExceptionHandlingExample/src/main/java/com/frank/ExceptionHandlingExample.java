
package com.frank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;  

public class ExceptionHandlingExample {
   
    private static Scanner theKeyboard = new Scanner(System.in);
    
    public  static void main(String[] args) throws FileNotFoundException { 
	
        displaySectionHeader("Hello Java Learner!\n\nWelcome to an Exception Handling Example!");
        
        TestUserInputLoop();
        
        System.out.println("\n\nThe sum of all the numbers is: " 
        	          + sumListOfValues(getValuesFromFile()));
        
        displaySectionHeader("Thanks for trying out our Exception Handling Example!", true);
        
    } // End of main()
 
/************************************************************************************************
 * Functions called by main() 
 * 
 * Note: Functions are marked static because they are being called by a static method
 *************************************************************************************************/
 
 /************************************************************************************************
  * User interaction loop until user indicates to stop example  
  *************************************************************************************************/ 
     static void TestUserInputLoop() {       
           
	 displaySectionHeader("User input loop example");
            
	 List<Double> numbersEnteredByUser = new ArrayList();
        
	 String aLine;
        
	 boolean shouldLoop = false;
        
	 System.out.println("Would you like to enter some data? (Y or N)");
	 aLine = theKeyboard.nextLine();
        
	 if(aLine.substring(0,1).toUpperCase().equals("Y")) {
	     	shouldLoop = true;
	 }
	 else {
	     	shouldLoop = false;
	 }
        
	 while(shouldLoop) {
	     
            System.out.print("Please enter a numeric value or press enter to end: ");
            aLine = theKeyboard.nextLine();
            
            if(aLine.equals("")) {
        	shouldLoop = false;
        	continue;
            }
            
            double userEnteredValue = Double.parseDouble(aLine);
            
            if(userEnteredValue == 0) {
        	shouldLoop = false;
        	continue;
            }
            
            numbersEnteredByUser.add(userEnteredValue);          
        }
        
            System.out.println("\n\nYou entered "
            	          + numbersEnteredByUser.size()
            	          + " numbers and their sum is: " 
            	          + sumListOfValues(numbersEnteredByUser));
     }  
/************************************************************************************************
 * End of TestUserInputLoop() code       
 *************************************************************************************************/               
      
/************************************************************************************************
 * Read values from a file 
 *************************************************************************************************/     
     
     
    static List<Double> getValuesFromFile() throws FileNotFoundException  {
	
	// Define a File object to represent the file we want to read
	File aFile = new File("./resources/someInputValues.txt");
	
	// Define a Scanner for the File object we created for the file on our computer
	Scanner valuesInFile = new Scanner(aFile);  // Give the Scanner object the File object for the file
	
	List<Double> numsFromFile = new ArrayList();
	
	String lineFromFile = null;
	
	while(valuesInFile.hasNext()) {
	    
	    lineFromFile = valuesInFile.nextLine();
	    
	    String[] valuesFromFile = lineFromFile.split(",");
	    
	    for(String aValue : valuesFromFile) {
		numsFromFile.add(Double.parseDouble(aValue));
	    }
	}
	
	valuesInFile.close();
	
	return numsFromFile;
    }
/************************************************************************************************
 * End of getValuesFromFile() code       
 *************************************************************************************************/     


/************************************************************************************************
 * Return the sum of values in a an array   
 *************************************************************************************************/    
        
    static double sumListOfValues(List<Double> listOfValues) {
	
	double sum = 0;
	
	System.out.printf("\nThere are %d numbers in the list passed to processListOfNumericValues function"
		+ "\nand here they are:\n", listOfValues.size());
	
	for(int i = 0; i < listOfValues.size(); i++) {
	    if (i % 5 == 0) {
		System.out.println();
	    }
	    System.out.print("[" + i + "]=" + listOfValues.get(i) + "\t");
	    sum = sum + listOfValues.get(i);
	    }
	
	return sum;
    }
/************************************************************************************************
 * End of sumListOfValues() code       
 *************************************************************************************************/     

/************************************************************************************************
 * methods to display section separators       
 *************************************************************************************************/ 
    static void displaySectionHeader(String message) {
	System.out.println("-".repeat(60));
	System.out.println(message);
	System.out.println("-".repeat(60));
    }
    
    
    static void displaySectionHeader(String message, boolean blankLineFirst) {

	if(blankLineFirst == true) {
	    System.out.println();
	}
	displaySectionHeader(message);
    }
    
/************************************************************************************************
 * End of methods to display section separators    
 ************************************************************************************************/ 
}  // end of application class