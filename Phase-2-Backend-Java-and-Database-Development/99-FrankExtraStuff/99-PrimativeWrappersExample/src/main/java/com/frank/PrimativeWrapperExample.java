package com.frank;

import java.util.ArrayList;

public class PrimativeWrapperExample {

	public static void main(String[] args) {

		
		System.out.println("#############################################################");
		System.out.println("Primitive Wrappers - Creating Object for primitives");
		System.out.println("So primitive values can be used where only objects are allows");
		System.out.println("#############################################################");

		/* Every primitive has a "Wrapper" class to use it as an Object */
		/* primitive - Wrapper
		 *  int      - Integer
		 *  double   - Double
		 *  float    - Float
		 *  boolean  - Boolean
		 *  char     - Character
		 */
		Integer int1 = Integer.valueOf(6);      // Integer Object with the value 6
		Integer int2 = Integer.valueOf(16);     // Integer Object with the value 16
		Integer int3 = Integer.valueOf("61");   // Integer Object with the value 61 (automatic convert from String)
		
		ArrayList<Integer> intList = new ArrayList<Integer>();
		
		intList.add(int1);
		intList.add(int2);
		intList.add(int3);	 
		
		// Display the values in the intList ArrayList
		for(int theValue : intList) {
			System.out.println("intList value: " + theValue);
		}

	}

}
