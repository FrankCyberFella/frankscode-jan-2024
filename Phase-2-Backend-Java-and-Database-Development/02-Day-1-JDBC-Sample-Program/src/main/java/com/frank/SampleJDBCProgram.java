package com.frank;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import com.frank.model.*;

/**
 * JDBC Sample Application Program
 * 
 * Demonstrate use of JDBC DAOs to access data in a relational data base (mySql in this example)
 *
 */
public class SampleJDBCProgram 
{
    public static void main( String[] args )
    {
        System.out.println( "------ Start of SampleJDBCProgram ------\n" );
        
     // Define a data source object for the database we want to access
 		BasicDataSource vegasDataSource = new BasicDataSource();         // simple JDBC data source
 		
 	 // Set values in the data source for the database manager and database we want to access	
 		//                    access:dbmgr:server-name:port/databasename
 		vegasDataSource.setUrl("jdbc:mysql://localhost:3306/vegasdb");// connection string
 		vegasDataSource.setUsername("student");                       // owner of the database
 		vegasDataSource.setPassword("Java#1Rules");                   // password for owner
 		
 	// Define an object to represent a DAO we will be using	
 		GamblerDao theGamblerData = new JdbcGamblerDao(vegasDataSource);
 		
 		System.out.println("\n--- Asking for all Gamblers ----");
   
 	// Define an object to hold data returned from the DAO method	
 		List<Gambler> allGamblers = theGamblerData.getAllGamblers();
 	
 	// Display each of the rows returned from the DAO method	
 		for(Gambler aGambler : allGamblers) {
 			aGambler.displayGambler();
 		}
 	// Find a gambler using their id	
 		System.out.println("\n--- Asking for all Gambler Id 12 ----");
 		
 		theGamblerData.findGamblerById(12).displayGambler();
 		
 
 	// Adding a new gambler	
 		System.out.println("\n--- Adding Gambler named Frank Fella ----");
 			
 		Gambler luckyFrank = new Gambler(0, "Frank Fella", "Phoenix, AZ", LocalDate.parse("1952-03-19"), 1000.00);
 		
 		boolean rowWasAdded = theGamblerData.addGambler(luckyFrank);
 		
 	// Display newly added row
 		if (rowWasAdded) {
 			for(Gambler aGambler : theGamblerData.findGamblerByName("Frank Fella")) {
 				aGambler.displayGambler();
 			}
 			
 	// Update a gambler
 			System.out.println("\n--- Updating Gambler Id 12 ----");
 			
 			Gambler gamblerToUpdate = theGamblerData.findGamblerById(12);
 			
 			if (gamblerToUpdate != null) {
 				System.out.println("Data BEFORE update:"); 				
 				gamblerToUpdate.displayGambler();	
 				
 				gamblerToUpdate.setName("John Smith");
 				gamblerToUpdate.setMonthlySalary(10000);
 				
 				theGamblerData.update(gamblerToUpdate);
 				
 				System.out.println("Data AFTER update:");
 				theGamblerData.findGamblerById(gamblerToUpdate.getId()).displayGambler();
 			}
 			else {
 				System.out.println("Uh-Oh... cannot find the gambler you are looking for");
 			}
 			
 	// delete a gambler by id
 			
 			theGamblerData.delete(12);
			
 			System.out.println("\n--- Asking for all Gambler Id 12 ----");
 			
 			Gambler gamblerWeWant = theGamblerData.findGamblerById(12);
 		
 			if (gamblerWeWant != null) {
 				gamblerWeWant.displayGambler();
 			}
 			else {
 				System.out.println("Uh-Oh... cannot find the gambler you are looking for");
 			}
 			
 		}
 		
 		
 		System.out.println( "\n------ End of SampleJDBCProgram  ------\n" );	
    } // End of main()
} //End of application class
