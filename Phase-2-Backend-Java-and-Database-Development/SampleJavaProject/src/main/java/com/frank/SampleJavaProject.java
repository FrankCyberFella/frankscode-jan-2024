package com.frank;

import java.util.Scanner;

public class SampleJavaProject {
    
    public static void main(String[] args) {
	
        System.out.println("Hello Java Learner!");
/************************************************************************************************
 * Variable definition examples        
 *************************************************************************************************/
        int 	num1;
        int 	num2   = 0;
//      int     num3   - 1.99;
        
        double 	amount = 19.95;
        float 	cost   = 9.99F;
        
        char	letter = 'a';
        
        String  sentence  = "I love programming!";
        
        boolean isOk      = true;
        
        String  charles[] = {"Diana", "William", "Harry"};
        
        int	scores[]  = new int[5];
        
        System.out.println("-".repeat(50));
        System.out.println("Displaying contents of variables");
        System.out.println("-".repeat(50));
        
//      System.out.println("num1 is: " + num1);
        System.out.println("num2 is: " + num2);
        System.out.println("amount is: " + amount + " cost is: " + cost);
        System.out.println("letter is: " + letter);
        System.out.println("sentence is: " + sentence);
        System.out.println("isOK is: " + isOk);
        
        
        System.out.println("-".repeat(50));
        System.out.println("Displaying array contents using System.out.println");
        System.out.println("-".repeat(50));
        
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