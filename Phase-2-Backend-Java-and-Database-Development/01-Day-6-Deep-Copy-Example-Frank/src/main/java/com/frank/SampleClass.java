package com.frank;

import java.util.Arrays;

public class SampleClass {
        // member data - instance data for the class
	    // private means only members of the class can access 
        private int[] anArray; // private means only members of the class can access 

        // Constructor to receive an array and assign it member data array
        // An array is a reference type 
        //         name of the array contains the location of array in memory
        public SampleClass(int[] intArray) {
        	// assign the reference received to the reference in this class 
        	// Shallow Copy - copy one reference into another - both point to the same data
        	//anArray = intArray;  // assign the reference received to the reference in this class 
        	
        	// Deep Copy (Defensive Copy) a reference you receive to references in the class
        	//                       source     size-of-source
        	anArray = Arrays.copyOf(intArray, intArray.length);
        }

        // Getter to return the member data array
        public int[] getAnArray() {
               //return anArray;    // return a reference to the array in the class - Shallow Return
                                    // Anyone with the reference can change the data in the member array
                                    // Violated Encapsulation - allow access to data outside the class
        	   // return a copy of the member data - Defensive Return/ Defensive Copy
        	   return Arrays.copyOf(anArray, anArray.length);
        }
        
        // Helper method to display the contents of the member data data
        public void showClass() {
                System.out.println(("\nContents of array in SampleClass: "));
                for (int i = 0; i < anArray.length; i++) {
                        System.out.println("Element " + i + ": " + anArray[i]);
                }
        }
}
