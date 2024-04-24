package simplilearn_jan24;


import frankexample.exceptions.DataBaseDeleteException;
import frankexample.exceptions.DataBaseInsertException;
import frankexample.exceptions.DataBaseUpdateException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.frank.model.gambler.Gambler;
import com.frank.model.gambler.GamblerDao;
import com.frank.model.gambler.JdbcGamblerDao;

/**
 * JDBC Sample Application Program
 * 
 * Demonstrate use of JDBC DAOs to access data in a relational data base (mySql in this example)				
 */
public class SampleJDBCApplication {
	
    /**************************************************************************************************************
     * Instance variable accessible to all metods in the class
     *************************************************************************************************************/

	// Define a reference to a Scanner object for user interaction via the keyboard
	private Scanner theKeyboard;	

	// Define a reference to a data source object for the database we want to access
	private BasicDataSource vegasDataSource;
	
	
	// Define a reference to a DAO objects we want to use to access a table in the database
	private GamblerDao theGamblerData;
	
    /**************************************************************************************************************
     * Constructor for the application code
     *************************************************************************************************************/

	public SampleJDBCApplication() throws IOException {
		
	// Instantiate a Scanner object for the keyboard and assign to reference
	 	theKeyboard = new Scanner(System.in); 	
            
	// Call method to setup DataSource for JDBC access to the data base 	
 		setupDataSource();     
 		
 	// Instantiate a DC DAO object object to represent a DAO we will be using
 	//     sending it the DataSource object for the database containing the tables	
 		theGamblerData = new JdbcGamblerDao(vegasDataSource);
	}

	/****************************************************************************
	 * This is the method that runs and controls the application
	 * 
	 * It should be called from the main() method for the project
	 * @throws DataBaseDeleteException 
	 * @throws DataBaseUpdateException 
	 * @throws DataBaseInsertException 
	 ****************************************************************************/

    public void run() throws DataBaseDeleteException, DataBaseUpdateException, DataBaseInsertException {
    	
        System.out.println( "------ Start of SampleJDBCApplication ------\n" );
        
        // Process loop control variable
        boolean continueProcessing = true;
        
        // Keep processing while teh loop control variable is true
        while(continueProcessing) {
 
        // Display the menu to user and get their option choice
        // The method called returns the option chosen 
        //  	and we store it it menuOptionChosen variable
 		String menuOptionChosen = displayMenuAndReceiveOption();
 		
 		// Check the option chosen by the user and run the associate method
 		switch(menuOptionChosen) {
 		
 			case "X": {                     // If they choose to exit...
 				continueProcessing = false; //    set loop control variable to end loop
				break;                      //     exit the switch statement
 			}
 	 		case "1": {                     // If the choose to display all gamblers    
 				getAndDisplayAllGamblersFromTheDataBase(); // run the method to do that
 				break;                                     // exit the switch
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
    		System.out.println("2 - Display a Specific gambler");
    		System.out.println("3 - Display a gambler Based On Name");
    		System.out.println("4 - Add a gambler to the Data Base");
			System.out.println("5 - Update a gambler in the Data Base");
			System.out.println("6 - Delete a Specific gambler from the Data Base");
				
			System.out.println("\n X - Exit");

		System.out.print("\nPlease choose an option: ");

		// Only the first character of what the user enters is returned in uppercase
			optionChosen = theKeyboard.nextLine().toUpperCase().substring(0,1);
		
		return optionChosen;
   
    } // End of displayOptionAndReceiveOption()
    
    private int getIntValueFromUser() {	
    	
 		boolean haveValidIntValue;
 		
 		int intFromUser = 0;
 		
 		do {
 			haveValidIntValue = true;
	 		try {
	 			intFromUser = theKeyboard.nextInt();  // Get an int from the keyboard
	 		}
	 		catch(InputMismatchException exceptionInfo) {
	 			System.out.println("!!!!! Uh-Oh! UhOh! Uh-Oh! !!!!!");
	 			System.out.println("Expected an integer/whole number");
	 			System.out.println("Please try again");
	 			
	 			haveValidIntValue = false;
	 		}
	 		finally {  // Regardless if there is an exception or not...
	 			theKeyboard.nextLine(); // Clear the enter left in the keyboard buffer by nextInt()
	 		}
 		}
	 		while(!haveValidIntValue);
 		
 		return intFromUser;
    }
 	private Gambler getGamblerInfoFromUser() {
 		
 		Gambler aNewGambler = new Gambler();
 		
 		System.out.println("\nPlease enter information for the new gambler:");
 		
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

		// Define USA formatted date (mm/dd/yyyy) for use with birthday entry

		DateTimeFormatter dateFormatterUSA = DateTimeFormatter.ofPattern("MM/dd/yyyy");

		try {
				validBirthDate = LocalDate.parse(birthDay, dateFormatterUSA);
			}
			catch(DateTimeParseException exceptionInfo) {
				System.out.println("\n!!!!! Uh-Oh! uh-Oh! Uh-Oh!  !!!!!");
				System.out.println("!!!!! Invalid date entered  !!!!!");
				System.out.println("!!!!! Please try again      !!!!!");
			}
	
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
 		System.out.print("\nPlease enter the id of the gambler you would like to display: ");
 		
 		int idWeWant = getIntValueFromUser();
 	
 		try {
 			theGamblerData.findGamblerById(idWeWant).displayGambler();
 		}
 		catch(NullPointerException expectionBlock) {
 			System.out.println("gambler with id: " + idWeWant + " not found!");
 		}
 	}	
 	private void getAGamblerBasedOnName() {
 		System.out.print("\nPlease enter the name of the gambler you would like to display: ");
 		
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
 			System.out.println("gambler with Name: " + nameWeWant + " not found!");
 		}
 		
 	}
 	private void addANewGamblerToDataBase() {
 		
 		Gambler newGambler = getGamblerInfoFromUser();
 		
 		System.out.println("\n--- Adding gambler named: " + newGambler.getName());
 		
		boolean rowWasAdded = theGamblerData.addGambler(newGambler);
		
		if (rowWasAdded) {
			System.out.println("--- gambler name: " + newGambler.getName() + "was added successfully");
 		}
 	 	}
 	private void updateAGamblerBasedOnId() {
 	// Update a gambler
 		
 			System.out.println("\nPlease enter the id of the gambler to be updated: ");
 			int idToBeUpdated = getIntValueFromUser();
 		
 			System.out.println("\n--- Updating gambler Id " + idToBeUpdated+ " ----");
 			
 			Gambler gamblerToUpdate = theGamblerData.findGamblerById(idToBeUpdated);
 			
 			if (gamblerToUpdate != null) {
 				System.out.println("Data BEFORE update:"); 				
 				gamblerToUpdate.displayGambler();	
 				
 				System.out.println("Enter new name for gambler or press enter to keep \"" + gamblerToUpdate.getName() + "\"");
 				String newName = theKeyboard.nextLine();
 				if(!newName.equals("")) {
 					gamblerToUpdate.setName(newName);
 				}
 				
 				System.out.println("Enter new city for gambler or press enter to keep \"" + gamblerToUpdate.getAddress() + "\"");
 				String newAddress = theKeyboard.nextLine();
 				if(!newAddress.equals("")) {
 					gamblerToUpdate.setAddress(newAddress);
 				}
 
 				boolean updateBirthDate;
 				do {
 	 				updateBirthDate = true;
 	 				
 	 				DateTimeFormatter formatUSA = DateTimeFormatter.ofPattern("MM/dd/yyyy");
 	 			    String birthDateUSA = gamblerToUpdate.getBirthDate().format(formatUSA);
 	 				
	 				System.out.println("Enter new birth date for gambler or press enter to keep \"" + birthDateUSA + "\"");
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
	 				System.out.println("Enter new monthly salary for gambler or press enter to keep \"" + gamblerToUpdate.getMonthlySalary() +  "\"");
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
 			System.out.print("\nEnter id for gambler to delete: ");
 			int gamblerToDelete = getIntValueFromUser();	
 		
 			theGamblerData.delete(gamblerToDelete);
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
