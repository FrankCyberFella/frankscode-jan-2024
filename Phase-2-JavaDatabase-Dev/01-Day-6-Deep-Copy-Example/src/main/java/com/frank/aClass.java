package com.frank;

import java.util.Arrays;

public class aClass {
        // member data - instance data for the class
        private int[] anArray;

        public aClass(int[] intArray) {
        
        	anArray = intArray;  // assign the reference received to the reference in this class
      
        }

        public int[] getAnArray() {
               return anArray;  // replaced by defensive return
                
        }

        public void showClass() {
                System.out.println(("\nContents of array in aClass: "));
                for (int i = 0; i < anArray.length; i++) {
                        System.out.println("Element " + i + ": " + anArray[i]);
                }
        }
}
