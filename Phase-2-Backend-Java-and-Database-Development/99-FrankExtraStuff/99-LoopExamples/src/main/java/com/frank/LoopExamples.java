package com.frank;

public class LoopExamples {

	public static void main(String[] args) {
		
	/**********************************************************************************
	 * This code will illustrate how the same problem can be solved using the three
	 * types of loop available in Java
	 * 
	 * Note:  This is not meant to show the best use of each type of loop 
	 *        it is an academic example to aid in understanding the syntax
	 *        and how each type of loop works.
	 **********************************************************************************/
		
	
	/***************************************************************************************
	 * 	Types of loops in Java:
	 * 
	 *  for loop - used when you need to iterate or count through a series of values
	 *             it is possible to not execute the loop body even once if the loop
	 *             condition is true at the start of the loop
	 *             
	 *             syntax: for (initialization; loop-condition; increment) {
	 *                          loop body - what you want to do each time through the loop
	 *                     }
	 *               
	 *             How for loop works:
	 *             
	 *             	  1. Perform the initialization process - this usually sets the loop 
	 *                                                        variable to its initial value.
	 *                                                      
	 *                2. Test the loop-condition - if true, go to step #3
	 *                                             if false, end the loop
	 *                                           
	 *                3. Execute the code in the loop body.
	 *              
	 *                4. Perform the increment process
	 *              
	 *                5. Go to step #2
	 *              
	 *              Example:
	 *              
	 *                 for (int i=0; i < 5; i++) {  // Loop while i is less than 5 (i=0,1,2,3,4)
	 *		               // some processing for the loop
	 *					}
	 *                                           
	 * 
	 *  while loop - used when you need to loop as long as a condition is true
	 *               it is possible to not execute the loop body even once if the loop
	 *               condition is true at the start of the loop
	 *             
	 *             syntax: while (loop-condition) {
	 *                          loop body - what you want to do each time through the loop
	 *                     }
	 *               
	 *             How while loop works:
	 *                                                      
	 *                1. Test the loop-condition - if true, go to step #2
	 *                                             if false, end the loop
	 *                                           
	 *                2. Execute the code in the loop body.
	 *              
	 *                3. Go to step #1
	 *              
	 *              Example:
	 *              
	 *                 int i = 0;
	 *                 while (i < 5) {     // loop while i is less than 5
	 *                     // some processing for the loop
	 *                     i++;            // be sure the processing does something 
	 *                                     //    that will eventually make the loop 
	 *                                     //    condition false inside the loop
	 *                  }
	 *
	 *
	 * do while loop - used when you need to loop as long as a condition is true
	 *                 the loop body always executes at least once
	 *         
	 *             
	 *             syntax: do {
	 *                         loop body - what you want to do each time through the loop
	 *                        }
	 *                     while (loop-condition);
	 *                   
	 *               
	 *             How while loop works:
	 *             
	 *                1. Execute the code in the loop body.
	 *                                                      
	 *                2. Test the loop-condition - if true, go to step #2
	 *                                             if false, end the loop
	 *             
	 *                3. Go to step #1
	 *              
	 *              Example:
	 *              
	 *                 int i = 0;
	 *                 do {
	 *                     // some processing for the loop
	 *                     i++;            // be sure the processing does something 
	 *                                     //    that will eventually make the loop 
	 *                                     //    condition false inside the loop
	 *                 }          
	 *                 while (i < 5);      // loop while i is less than 5
	 *                    
	 *                             
	 ****************************************************************************************/
		
		//----------------------------------------------------------------------------------
		// Problem: Display all the odd numbers between 0 and 10 and their sum
		//----------------------------------------------------------------------------------
			
		int sum = 0;                  // Define and initialize the variable to hold teh sum
		
		System.out.println("Using a for loop to solve problem...");
		
		for (int i=0; i < 10; i++) {        // Loop while i < 10 (i=0,1,2,3,4,5,6,7,8,9)
			if (i % 2 != 0) {               // if i is not an even value (meaning it's odd)
				sum = sum + i;              //     add i to sum
				System.out.println("i="+i); //     Display i on the screen
			}
		}
		System.out.println("Sum of the above numbers is: " + sum);
		
		System.out.println("-----------------------------------------");   
		
		//----------------------------------------------------------------------------------
		// Problem: Display all the odd numbers between 0 and 10 and their sum
		//----------------------------------------------------------------------------------
		
		sum = 0;                            // Reset the variable to hold the sum
			
		System.out.println("Using a while loop to solve problem...");
		
		int i = 0;                          // Initialize the loop control variable
		
		while (i < 10) {                    // Loop while i < 10 (i=0,1,2,3,4,5,6,7,8,9)
			if (i % 2 != 0) {               // if i is not an even value (meaning it's odd)
				sum = sum + i;              //     add i to sum
				System.out.println("i="+i); //     Display i on the screen
			}
			i++;                            // modify the loop control variable
		}
		System.out.println("Sum of the above numbers is: " + sum);
		
		System.out.println("-----------------------------------------"); 
		
		//----------------------------------------------------------------------------------
		// Problem: Display all the odd numbers between 0 and 10 and their sum
		//----------------------------------------------------------------------------------
				
		sum = 0;                            // Reset the variable to hold the sum
					
		System.out.println("Using a do-while loop to solve problem...");
				
		i = 0;                             // Initialize the loop control variable
			
		do {
		   if (i % 2 != 0) {               // if i is not an even value (meaning it's odd)
		      sum = sum + i;               //     add i to sum
			  System.out.println("i="+i);  //     Display i on the screen
		   }
		   i++;                            // modify the loop control variable
		   }
		while (i < 10);                   // Loop while i < 10 (i=0,1,2,3,4,5,6,7,8,9)
		
		System.out.println("Sum of the above numbers is: " + sum);
		
		System.out.println("-----------------------------------------"); 
	}
	
}