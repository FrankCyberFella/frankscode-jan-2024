// Same comments syntax as JavaScript

// Everything in Java is part of a "package"
// A package is a group of related Java entities/elements/things/stuff
package com.frank;

import java.util.Scanner;  // gaining access to code in a different package

// A class is a group of related application information
// A class is associated with a package

public class SampleJavaProject {
    
    // Every application has exactly one function called main()
    // The main() function is the starting point for the application
    // When the application is run, the main function is executed
    
    // Every function has a function header which described the function
    //    return-type  name(parameters)
    //
    // Things in class may be assigned access-modifier:
    //
    //     public  - anyone can access or use it
    //     private - only members of the class can access (Object-Oriented programming)
    //
    // static - tells Java there is only one of these in the application
    
    public static void main(String[] args) { // main is function that returns nothing (void)
                                             //         and receives a String array parameter
	// System.out.println() displays whatever is inside the () on the screen
	//  (its the Java version of console.log() in JavaScript)
        System.out.println("Hello Java Learner!");
/************************************************************************************************
 * Variable definition examples        
 *************************************************************************************************/
// to define a variable in Java:   datatype name ; -or- data-type name = initial-value;
//
// If you do not assign a variable and initial value 
//        it generally cannot be used correctly until it is assigned a value        
        
        
//    datatype  name     initial-value        
        int 	num1;
        int 	num2   = 0;
//      int     num3   = 1.99;  // cannot store a double value in an int
        
        double 	amount = 19.95;
        float 	cost   = 9.99F; // F (or f) designates the value as a floating value
//      float 	amt    = 9.99 ; // cannot store a double value in a float
        
        char	letter = 'a'; // a single character is enclosed in ''
        
        String  sentence  = "I love programming!"; // a series of characters (String) in ""
        
        boolean isOk      = true;
        
        // indicate an array variable using [] (like in JavaScript
//      datatype name[]   = {initializers-separated-by-commas};        
        String  charles[] = {"Diana", "William", "Harry"}; // define and initialize an array
        
//      datatype  name[]  = new datatype[number-of-elements];        
        int	scores[]  = new int[5]; // define an array to hold 5 elements (default initializer 0)
        
        System.out.println("-".repeat(50));
        System.out.println("Displaying contents of variables");
        System.out.println("-".repeat(50));

// + when used with a String means concatenation - stick the string at the end of the other one        
//      System.out.println("num1 is: " + num1);  // error since num1 has been assigned a value
        System.out.println("num2 is: " + num2);
        System.out.println("amount is: " + amount + " cost is: " + cost);
        System.out.println("letter is: " + letter);
        System.out.println("sentence is: " + sentence);
        System.out.println("isOK is: " + isOk);
        
        
        System.out.println("-".repeat(50));
        System.out.println("Displaying array contents using System.out.println");
        System.out.println("-".repeat(50));

// the iterative for-loop in Java is exactly like the for-loop in JavaScript        
        System.out.println("Contents of array charles...");
        for(int i=0; i < charles.length; i++) {
            System.out.println("charles array element " + i + " is: " + charles[i]);
        }
        System.out.println("\nContents of array scores...");
        for(int i=0; i < scores.length; i++) {
            System.out.println("scores[" + i + "]: " + scores[i]);
        }
        
        System.out.println("-".repeat(50));
        System.out.println("Displaying array contents using System.out.print");
        System.out.println("-".repeat(50));
        
        for(int i=0; i < scores.length; i++) {
            System.out.print("scores[" + i + "]: " + scores[i] + ", ");
        }
        System.out.println();
      
/************************************************************************************************
 * Keyboard interaction with user example
 **************************************************************************************************/
        displaySectionHeader("Keyboard input demo", true);
        
        Scanner theKeyboard = new Scanner(System.in);
        
        String aLine;
        
        System.out.println("Please enter your name: ");
        String userName = theKeyboard.nextLine();
        
        System.out.println("Hello, " + userName + " nice to meet you!\n");
        
        System.out.println("Please enter 5 whole numbers separate by commas (1,2,3,4,5)");  
        
        aLine = theKeyboard.nextLine();
        
        String numbers[] = aLine.split(",");
        	
        for(int i=0; i < numbers.length; i++) {
            System.out.println("You entered: " + numbers[i]);
        }
        
/************************************************************************************************
 * User interaction loop until user indicates to stop example  
 *************************************************************************************************/ 
        
        displaySectionHeader("User input loop example");
        
        double numbersEnteredByUser[] = new double[5];
        
        System.out.println("Hello " + userName + "!");
        
        int numberOfValuesEnteredByUser = 0;
        
        boolean shouldLoop = true;
        
        while(shouldLoop && numberOfValuesEnteredByUser < numbersEnteredByUser.length )
        {
            System.out.println("Would you like to enter some data? (Y or N)");
            aLine = theKeyboard.nextLine();
            
            if(!aLine.substring(0,1).toUpperCase().equals("Y")) {
        	break;
            }
            System.out.print("Please enter a numeric value: ");
            aLine = theKeyboard.nextLine();
            
            double userEnteredValue = Double.parseDouble(aLine);
            
            numbersEnteredByUser[numberOfValuesEnteredByUser] = userEnteredValue;
            
            numberOfValuesEnteredByUser++;
            
            if (numberOfValuesEnteredByUser == numbersEnteredByUser.length - 1) {
        	System.out.println("*** warning - you may only enter one more number ***");
            }
            
        }
        
        System.out.println("\n\nYou entered "
        	          + numberOfValuesEnteredByUser
        	          + " numbers and their sum is: " 
        	          + processArrayOfNumericValues(numbersEnteredByUser));
 
/************************************************************************************************
 * End of example code       
 *************************************************************************************************/               
        displaySectionHeader("Thanks for trying out our Sample Java Program!", true);
    }
 
/************************************************************************************************
 * Functions examples    
 *************************************************************************************************/
    
    static void displaySectionHeader(String message) {
	System.out.println("-".repeat(50));
	System.out.println(message);
	System.out.println("-".repeat(50));
    }
    
    
    static void displaySectionHeader(String message, boolean blankLineFirst) {

	if(blankLineFirst == true) {
	    System.out.println();
	}
	displaySectionHeader(message);
    }
    
    
    static double processArrayOfNumericValues(double valuesArray[]) {
	
	double sum = 0;
	
	System.out.println("\nValues in array passed to processArrayOfNumericValues function: \n");
	
	for(int i = 0; i < valuesArray.length; i++) {
	    System.out.print("[" + i + "]=" + valuesArray[i] + ", ");
	    sum = sum + valuesArray[i];
	}
	
	return sum;
    }
}