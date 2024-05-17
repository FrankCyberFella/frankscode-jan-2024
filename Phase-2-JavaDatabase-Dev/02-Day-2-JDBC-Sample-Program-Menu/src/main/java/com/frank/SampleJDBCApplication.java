package com.frank;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.dbcp2.BasicDataSource;
import com.frank.model.*;
import com.frank.model.Gambler.Gambler;
import com.frank.model.Gambler.GamblerDao;
import com.frank.model.Gambler.JdbcGamblerDao;

/**
 * JDBC Sample Application Program
 * 
 * Demonstrate use of JDBC DAOs to access data in a relational data base (mySql in this example)
 *
 	 				updateSalary = true;
	 				System.out.println("Enter new monthly salary for Gambler or press enter to keep \"" + gamblerToUpdate.getMonthlySalary() +  "\"");
	 				String  newSalary = theKeyboard.nextLine();
	 				if(newSalary.equals("")) {
	 					updateSalary = false;
 						continue;
 					}
	 			
 					Double validSalary = validateSalary(newSalary);
 					if (validSalary != null) {
 						gamblerToUpdate.setMonthlySalary(validSalary);
 						updateSalary = false;
 					}
 				
 */
public class SampleJDBCApplication {
	
    /**************************************************************************************************************
     * Instance variable acessible to all metods in the class
     *************************************************************************************************************/

	// Define a reference to a Scanner object for user interaction via the keyboard
	private Scanner theKeyboard;	

	// Define a reference to a data source object for the database we want to access
	private BasicDataSource vegasDataSource;
	
	
	// Define a reference to a DAO object we want to use to access a table in the database
	private GamblerDao theGamblerData;
	
    /**************************************************************************************************************
     * Constructor for the application code
     *************************************************************************************************************/

	public SampleJDBCApplication() {
		
	// Instantiate a Scanner object for the keyboard and assign to reference
	 	theKeyboard = new Scanner(System.in); 	
		
	// Call method to setup DataSource for JDBC access to the data base 	
 		setupDataSource();     
 		
 	// Instantiate a DC DAO object object to represent a DAO we will be using	
 		theGamblerData = new JdbcGamblerDao(vegasDataSource);
	}
	

    public void run() {
    	
        System.out.println( "------ Start of SampleJDBCApplication ------\n" );
        
        boolean continueProcessing = true;
        
        while(continueProcessing) {
 
 		String menuOptionChosen = displayMenuAndReceiveOption();
 		
 		switch(menuOptionChosen) {
 		
 			case "X": {
 				continueProcessing = false;
				break;
 			}
 	 		case "1": {
 				getAndDisplayAllGamblersFromTheDataBase();
 				break;
	 		}
	 		case "2": {
	 			getAGamblerBasedOnTheirId();
 				break;
	 		}
	 		case "3": {
	 			getAGamblerBasedOnName();
 				break;
	 		}
	 		case "4": {
	 			addANewGamblerToDataBase();
	 			break;
	 		}
	 		case "5": {
	 			updateAGamblerBasedOnId();
	 			break;
	 		}
	 		case "6" : {
	 			removeAGamblerFromTheDataBaseBasedOnId();
	 			break;
	 		}
	 		case "A" : {
	 			displayAllCasinos();
	 			break;
	 		}
	 		case "B" : {
	 			displayACasinoBasedOnName();
	 			break;
	 		}
	 		case "C" : {
	 			updateACasino();
	 			break;
	 		}
	 		case "D" : {
	 			deleteACasino();
	 			break;
	 		}
	 		default:
	 			System.out.println("\n!!!!! Invalid option : " + menuOptionChosen + " !!!!!");
	 			System.out.println("!!!!! Please try again !!!!!\n");
	 			break;
 		} // End of switch for menu options
 		

        } // End of while processing loop
        
 		System.out.println( "\n------ End of SampleJDBCApplication  ------\n" );
    
    } // End of run()
    
    
    private String displayMenuAndReceiveOption() {
    	
    	String optionChosen = "";
    		
    		System.out.println("-".repeat(50));
    		System.out.println("Here are your Options for This Application: \n");
    		
    		System.out.println("----- Gambler Processing Options -----");
    		System.out.println("1 - Display all Gamblers");
    		System.out.println("2 - Display a Specific Gambler");
    		System.out.println("3 - Display a Gambler Based On Name");
    		System.out.println("4 - Add a Gambler to the Data Base");
    		System.out.println("5 - Update a Gambler in the Data Base");
    		System.out.println("6 - Delete a Specific Gambler from the Data Base");	
    		System.out.println("\n----- Casino Processing Options -----");
    		System.out.println("A - Display all Casinos");
    		System.out.println("B - Display a Casino Based On Name");
    		System.out.println("C - Update a Casino");
    		System.out.println("D - Delete a Casino");
    		
    		System.out.println("\n X - Exit");
    		
    		System.out.print("\nPlease choose an option: ");
    		
    		optionChosen = theKeyboard.nextLine().toUpperCase().substring(0,1);
    		
    		return optionChosen;
   
    } // End of displayOptionAndReceiveOption()
    private int getIntValueFromUser() {	
    	
 		boolean haveValidIntValue;
 		
 		int intFromUser = 0;
 		
 		do {
 			haveValidIntValue = true;
	 		try {
	 			intFromUser = theKeyboard.nextInt();
	 		}
	 		catch(InputMismatchException exceptionInfo) {
	 			System.out.println("!!!!! Uh-Oh! UhOh! Uh-Oh! !!!!!");
	 			System.out.println("Expected an integer/whole number");
	 			System.out.println("Please try again");
	 			
	 			haveValidIntValue = false;
	 		}
	 		finally {
	 			theKeyboard.nextLine(); // Clear the enter left in the keyboard buffer
	 		}
 		}
	 		while(!haveValidIntValue);
 		
 		return intFromUser;
    }
 	private Gambler getGamblerInfoFromUser() {
 		
 		Gambler aNewGambler = new Gambler();
 		
 		System.out.println("\nPlease enter information for the new Gambler:");
 		
 		System.out.print("\nName: ");
 		aNewGambler.setName(theKeyboard.nextLine());
 		
 		System.out.print("\nCity: ");
 		aNewGambler.setAddress(theKeyboard.nextLine());
 		
 		aNewGambler.setBirthDate(getValidBirthDate());
 		
 		aNewGambler.setMonthlySalary(getValidMonthlySalary());
 		
 		return aNewGambler;
 		
 	}
  	private LocalDate getValidBirthDate() {
 		
 		LocalDate birthDay = null;
 		
		boolean haveValidBirthday;
		
 		do {
 			haveValidBirthday = true;
 			
 			System.out.print("\nBirthday (mm/dd/yyyy): ");
 		 
	 		String newBirthDate = theKeyboard.nextLine();
	 		
	 		if(!newBirthDate.equals("")) {
	 			birthDay = validateBirthDate(newBirthDate);
	 		}
	 		if(birthDay!= null) {
	 			haveValidBirthday = true;
	 		}
	 		else {
	 			haveValidBirthday = false;
	 		}
		}
 		while(!haveValidBirthday);
 		
 		return birthDay;
 	}
 	private LocalDate validateBirthDate(String birthDay) {
 		
		LocalDate validBirthDate = null;
	
		boolean haveValidBirthday;
		
 		do {
 			haveValidBirthday = false;;
 		
 		// Define USA formatted date (mm/dd/yyyy) for use with birthday entry
 		
 		DateTimeFormatter dateFormatterUSA = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	 
 		try {
	 			validBirthDate = LocalDate.parse(birthDay, dateFormatterUSA);
	 			haveValidBirthday = true;
	 		}
	 		catch(DateTimeParseException exceptionInfo) {
	 			haveValidBirthday = false;
	 			System.out.println("\n!!!!! Uh-Oh! uh-Oh! Uh-Oh!  !!!!!");
	 			System.out.println("!!!!! Invalid date entered  !!!!!");
	 			System.out.println("!!!!! Please try again      !!!!!");
	 			haveValidBirthday = false;
	 		}
 		}
 		while(!haveValidBirthday);
 		
 		return validBirthDate;
 	}
 	private double getValidMonthlySalary() {

 		Double monthlySalary = null;
 		
 		boolean haveValidSalary;
 		do {
 			System.out.print("\nMonthly Salary: ");
	 		try {
	 			String newMonthlySalary = theKeyboard.nextLine();
	 			monthlySalary = validateSalary(newMonthlySalary);  // Clear enter left in the keyboard buffer by nextDouble()
	 			if(monthlySalary != null) {
	 				haveValidSalary = true;
	 			}
	 			else {
	 				haveValidSalary = false;;
	 			}
	 		}
	 		catch(InputMismatchException exceptionInfo) {
	 			haveValidSalary = false;
	 			System.out.println("\n!!!!! Uh-Oh! uh-Oh! Uh-Oh!           !!!!!");
	 			System.out.println("!!!!! Invalid monthly salary entered !!!!!");
	 			System.out.println("!!!!! Please try again               !!!!!");
	 			haveValidSalary = false;
	 		}
 		}
 		while(!haveValidSalary);
 		
 		return monthlySalary;
 		
 	}
 	private Double validateSalary(String aSalary) {
 		
 		Double validSalary = null;
 	
	 		try {
	 			validSalary = Double.parseDouble(aSalary);
	 		}
	 		catch(NumberFormatException exceptionInfo) {
	 			System.out.println("\n!!!!! Uh-Oh! uh-Oh! Uh-Oh!           !!!!!");
	 			System.out.println("!!!!! Invalid monthly salary entered !!!!!");
	 			System.out.println("!!!!! Please try again               !!!!!");
	 		}
	 		catch(NullPointerException exceptionInfo) {
	 			System.out.println("\n!!!!! Uh-Oh! uh-Oh! Uh-Oh!           !!!!!");
	 			System.out.println("!!!!! Unexpected error occured       !!!!!");
	 			System.out.println("!!!!! Contact your I.T department    !!!!!");
	 		}
 		return validSalary;
 	}
 	
 	private void getAndDisplayAllGamblersFromTheDataBase() {	
 		
 	// Define an object to hold data returned from the DAO method	
 		List<Gambler> allGamblers = theGamblerData.getAllGamblers();
 	
 	// Display each of the rows returned from the DAO method	
 		for(Gambler aGambler : allGamblers) {
 			aGambler.displayGambler();
 		}
 	}
 
 	private void getAGamblerBasedOnTheirId() {
 	// Find a gambler using their id	
 		System.out.print("\nPlease enter the id of the Gambler you would like to display: ");
 		
 		int idWeWant = getIntValueFromUser();
 	
 		try {
 			theGamblerData.findGamblerById(idWeWant).displayGambler();
 		}
 		catch(NullPointerException expectionBlock) {
 			System.out.println("Gambler with id: " + idWeWant + " not found!");
 		}
 	}	
 	private void getAGamblerBasedOnName() {
 		System.out.print("\nPlease enter the name of the Gambler you would like to display: ");
 		
 		String nameWeWant = theKeyboard.nextLine();
 	
 		try {
 			List<Gambler> gamblers = theGamblerData.findGamblerByName(nameWeWant);
 			
 			if(gamblers.size() == 0) {
 				System.out.println("\n ***** No Gamblers found with name: " + nameWeWant);
 				return;
 			}
 			System.out.println("\n Here are Gamblers we found with the name: " + nameWeWant);
 			
 			for(Gambler aGambler : gamblers) {		
 				aGambler.displayGambler();
 			}
 		}
 		catch(NullPointerException expectionBlock) {
 			System.out.println("Gambler with Name: " + nameWeWant + " not found!");
 		}
 		
 	}
 
 	private void addANewGamblerToDataBase() {
 		
 		Gambler newGambler = getGamblerInfoFromUser();
 		
 		System.out.println("\n--- Adding Gambler named: " + newGambler.getName());
 		
		boolean rowWasAdded = theGamblerData.addGambler(newGambler);
		
		if (rowWasAdded) {
			System.out.println("--- Gambler name: " + newGambler.getName() + "was added successfully");
 		}
 		
 		
 
 	}
 	
 	private void updateAGamblerBasedOnId() {
 	// Update a gambler
 		
 			System.out.println("\nPlease enter the id of the Gambler to be updated: ");
 			int idToBeUpdated = getIntValueFromUser();
 		
 			System.out.println("\n--- Updating Gambler Id " + idToBeUpdated+ " ----");
 			
 			Gambler gamblerToUpdate = theGamblerData.findGamblerById(idToBeUpdated);
 			
 			if (gamblerToUpdate != null) {
 				System.out.println("Data BEFORE update:"); 				
 				gamblerToUpdate.displayGambler();	
 				
 				System.out.println("Enter new name for Gambler or press enter to keep \"" + gamblerToUpdate.getName() + "\"");
 				String newName = theKeyboard.nextLine();
 				if(!newName.equals("")) {
 					gamblerToUpdate.setName(newName);
 				}
 				
 				System.out.println("Enter new city for Gambler or press enter to keep \"" + gamblerToUpdate.getAddress() + "\"");
 				String newAddress = theKeyboard.nextLine();
 				if(!newAddress.equals("")) {
 					gamblerToUpdate.setAddress(newAddress);
 				}
 
 				boolean updateBirthDate;
 				do {
 	 				updateBirthDate = true;
 	 				
 	 				DateTimeFormatter formatUSA = DateTimeFormatter.ofPattern("MM/dd/yyyy");
 	 			    String birthDateUSA = gamblerToUpdate.getBirthDate().format(formatUSA);
 	 				
	 				System.out.println("Enter new birth date for Gambler or press enter to keep \"" + birthDateUSA + "\"");
	 				String newBirthDate = theKeyboard.nextLine();
	 				if(newBirthDate.equals("")) {
	 					updateBirthDate = false;
 						continue;
	 				}
	 				LocalDate validBirthDate = validateBirthDate(newBirthDate);
	 				if (validBirthDate != null) {
	 					gamblerToUpdate.setBirthDate(validBirthDate);
		 				updateBirthDate = false;
	 				}
 				}				
 				while (updateBirthDate);
 				
 				boolean updateSalary;
 				
 				do {
 	 				updateSalary = true;
	 				System.out.println("Enter new monthly salary for Gambler or press enter to keep \"" + gamblerToUpdate.getMonthlySalary() +  "\"");
	 				String  newSalary = theKeyboard.nextLine();
	 				if(newSalary.equals("")) {
	 					updateSalary = false;
 						continue;
 					}
	 			
 					Double validSalary = validateSalary(newSalary);
 					if (validSalary != null) {
 						gamblerToUpdate.setMonthlySalary(validSalary);
 						updateSalary = false;
 					}
 				}
 				while (updateSalary);
 				
 				theGamblerData.update(gamblerToUpdate);
 				
 				System.out.println("Data AFTER update:");
 				theGamblerData.findGamblerById(gamblerToUpdate.getId()).displayGambler();
 			}
 			else {
 				System.out.println("Uh-Oh... cannot find the gambler you are looking for");
 			}
 	} 		
	
 	private void removeAGamblerFromTheDataBaseBasedOnId() {		
 	// delete a gambler by id
 			System.out.print("\nEnter id for Gambler to delete: ");
 			int gamblerToDelete = getIntValueFromUser();	
 		
 			theGamblerData.delete(gamblerToDelete);
 		}
 		
private void displayAllCasinos() {
	System.out.println("\n ***** Option not yet implemented *****\n");	
};
private void displayACasinoBasedOnName() {
	System.out.println("\n ***** Option not yet implemented *****\n");	
}
private void updateACasino() {
	System.out.println("\n ***** Option not yet implemented *****\n");	
}
private void deleteACasino() {
	System.out.println("\n ***** Option not yet implemented *****\n");	
}
  
private void setupDataSource() {  
    /**************************************************************************************************************
     * Instantiate and initialize data source for JDBC data base access
     *************************************************************************************************************/

		vegasDataSource = new BasicDataSource();         // simple JDC data source
		
	 // Set values in the data source for the database manager and database we want to access	
		//                    access:dbmgr:server-name:port/databasename
		vegasDataSource.setUrl("jdbc:mysql://localhost:3306/vegasdb");// connection string
		vegasDataSource.setUsername("student");                       // owner of the database
		vegasDataSource.setPassword("Java#1Rules");                   // password for owner
    }
    
    
} //End of application class
