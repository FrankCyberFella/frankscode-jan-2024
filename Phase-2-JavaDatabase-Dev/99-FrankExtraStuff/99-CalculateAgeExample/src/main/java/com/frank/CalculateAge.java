package com.frank;

/***********************************************************************************
 * This code illustrates use of the Java LocalDate class
 * 
 * For additional information on LocalDate please visit:
 * 
 *         https://www.w3schools.com/java/java_date.asp
 *         
 ***********************************************************************************/

import java.time.LocalDate;  // Java date format
import java.time.Period;     // Methods to calculate time periods for LocalDate objects
import java.util.Scanner;    // Java input class

public class CalculateAge {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Enter birth date in yyyy-mm-dd format");
		String userInput = keyboard.nextLine();
		
		LocalDate birthDate = LocalDate.parse(userInput);
		LocalDate currentDate = LocalDate.now();
		  
		int ageInYears = Period.between(birthDate, currentDate).getYears();
		  
		System.out.println("As of today you are " + ageInYears + " years old");
	}
}
