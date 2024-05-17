package com.frank;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.frank.model.host.*;

import org.apache.commons.dbcp2.BasicDataSource;

import com.frank.exceptions.DataBaseDeleteException;
import com.frank.exceptions.DataBaseInsertException;
import com.frank.exceptions.DataBaseUpdateException;
import com.frank.model.casino.Casino;
import com.frank.model.casino.CasinoDao;
import com.frank.model.casino.JdbcCasinoDao;
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
	private HostDao    theHostData;
	private CasinoDao  theCasinoData;
	
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
		theHostData    = new JdbcHostDao(vegasDataSource);
		theCasinoData  = new JdbcCasinoDao(vegasDataSource);
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
	 		case "E" : {
	 			addACasino();
	 			break;
	 		}
			case "F" : {
				displayAllHosts();
				break;
			}
			case "G" : {
				displayHostByGambler();
				break;
			}
			case "H" : {
				displayHostByCasino();
				break;
			}
			case "I" : {
				addAHost();
				break;
			}
			case "J" : {
				updateAHost();
				break;
			}
			case "K" : {
				deleteAHost();
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
			
			System.out.println("\n----- Casino Processing Options -----");
			System.out.println("A - Display all Casinos");
			System.out.println("B - Display a casino Based On Name");
			System.out.println("C - Update a casino");
			System.out.println("D - Delete a casino");
			System.out.println("E - Add a casino");
			
			System.out.println("\n----- Host Processing Options -----");
			System.out.println("F - Display all Host Entries");
			System.out.println("G - Display a Host Entry Based On Gambler");
			System.out.println("H - Display a Host Entry Based On Casino");
			System.out.println("I - Add a Host Entry");
			System.out.println("J - Update a Host Entry");
			System.out.println("K - Delete a Host Entry");
	
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
	private void displayAllCasinos() {
	// Use the CasinoDAO object to get all the casinos from the database	
		List<Casino> allTheCasinos = theCasinoData.getAllCasino();	
	
	// Display each casino received from the database one at a time
		for(Casino aCasinoObject : allTheCasinos) {
			System.out.println(aCasinoObject);
		}
		
	};
	private void displayACasinoBasedOnName() {

		// We need to get a casino name from the user
		System.out.println("Please enter the name of the casino you want: ");
		String requestedCasino = theKeyboard.nextLine();
		
		// Call the casino DAO method and send it the name we got from the user
		//      and store the casino it returns
        Casino theCasino = theCasinoData.getCasinoByName(requestedCasino);
		
		if(theCasino == null) {
			System.out.println("\"" + requestedCasino + "\" not found in database");
			return;	
		}
		
		// Display the casino we got from the DAO
		System.out.println(theCasino);
	};
	
	private void addACasino() throws DataBaseInsertException {
		
		Casino casino2BeAdded = new Casino();
	
		System.out.print("Please enter the name of the Casino you would like to add: ");	
		casino2BeAdded.setCasinoName(theKeyboard.nextLine());
	
		System.out.println("Enter new location: ");
		casino2BeAdded.setLocation(theKeyboard.nextLine());
		
		System.out.println("Enter new owner: ");
		casino2BeAdded.setOwner(theKeyboard.nextLine());
		
		System.out.println("Enter new size: ");
		casino2BeAdded.setSize(getIntValueFromUser());
		
		try {
			theCasinoData.addCasino(casino2BeAdded);
		}
		catch (DataBaseInsertException exceptionObject) {
			System.out.println("!!!!! Error attempting to add new casino");
			return;
		}
		
		System.out.println("Casino added successfully");	
	}
	
	
	private void updateACasino() throws DataBaseUpdateException {
		System.out.print("Please enter the name of the Casino you would like to update: ");	
		String nameOfCasino2BeUpdated = theKeyboard.nextLine();
	
		Casino casino2BeUpdated = theCasinoData.getCasinoByName(nameOfCasino2BeUpdated);
		
		if(casino2BeUpdated == null) {
			System.out.println("!!!!! Casino with name \"" + nameOfCasino2BeUpdated + "\" not found !!!!!");
		    return;
		}
		
		System.out.println("Enter new location or press enter to keep \"" + casino2BeUpdated.getLocation() + "\"");
		String newLocation = theKeyboard.nextLine();
		
		if(!newLocation.equals("")) {
			casino2BeUpdated.setLocation(newLocation);
		}
		
		System.out.println("Enter new owner or press enter to keep \"" + casino2BeUpdated.getOwner() + "\"");
		String newOwner = theKeyboard.nextLine();
		
		if(!newOwner.equals("")) {
			casino2BeUpdated.setOwner(newOwner);
		}
		boolean validSizeEntered = false;
		
		do {
			System.out.println("Enter new size or press enter to keep \"" + casino2BeUpdated.getSize() + "\"");
			String newSize = theKeyboard.nextLine();
			try {
				if(!newSize.equals("")) {
					casino2BeUpdated.setSize(Integer.parseInt(newSize));
					validSizeEntered = true;
				}
				else {
					validSizeEntered = true;
				}
			}
			catch (NumberFormatException exceptionObject) {
				System.out.println("!!!!! Whole Number expected !!!!!");
				System.out.println("!!!!! You entered: " + newSize + " !!!!!");
				System.out.println("!!!!! Please try again      !!!!!\n");
			}
		} while(!validSizeEntered);
		try {
			theCasinoData.updateCasino(casino2BeUpdated);
		}
		catch (DataBaseUpdateException exceptionObject) {
			System.out.println("!!!!! Error attempting to update casino named \"" + casino2BeUpdated.getCasinoName() + "\n");
		}
	}
	private void deleteACasino() throws DataBaseDeleteException {
		
		System.out.print("Please enter the name of the Casino you would like to delete: ");	
		String nameOfCasino2BeDeleted = theKeyboard.nextLine();
		
		try {
			theCasinoData.trashCasino(nameOfCasino2BeDeleted);
		}
		catch (DataBaseDeleteException exceptionObject) {
			System.out.println("!!!!! Error attempting to delete casino named \"" + nameOfCasino2BeDeleted + "\n");
		}
	}
    private void displayAllHosts() {
		List<Host> allHostEntries = theHostData.getAllHost();

		for(Host aHostEntry : allHostEntries )
			System.out.println(aHostEntry);
	}
	private void displayHostByGambler(){
		// This is defined outside any block so it is accessible by all code in teh method
		int gamblerId = 0;

		System.out.print("Enter the id of the Ganbler you want to show Host entries for: ");

		// Get valid int for gambler id loop control variable
		boolean validIntValueReceived = false;

		do {
			try {
				gamblerId = theKeyboard.nextInt();
				validIntValueReceived = true;
			} catch (InputMismatchException exceptionObject) {
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
				System.out.println(" Whole number expected");
				System.out.println(" Please try again");
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
				validIntValueReceived = false;
			} finally {
				theKeyboard.nextLine(); // clear the enter left in the keyboard buffer by .getInt()
			}
		} // end of do
		while(!validIntValueReceived);  // Loop until valid int is entered by the user

		List<Host> hostedData = theHostData.getHostByGamblerId(gamblerId);

		if(hostedData.size() == 0) {
			System.out.println("??? Gambler Id: " + gamblerId + " has no entries in the Host table. ???");
		}

		for(Host aHostEntry : hostedData) {
			System.out.println(aHostEntry);
		}
	}
	private void displayHostByCasino() {

		String requestedCasino = "";

		System.out.print("Please enter the name of the Casino you would Host entries displayed for: ");

		requestedCasino = theKeyboard.nextLine();

		List<Host> casinoRowsInHost = theHostData.getHostByCasinoName(requestedCasino);

		if(casinoRowsInHost.size() == 0) {
			System.out.println("??? Host entries for Casino name: " + requestedCasino + " was not found in the database. ???");
		}

		for(Host aHostEntry : casinoRowsInHost) {
			System.out.println(aHostEntry);
		}
	}
	private void addAHost() {

		Host newHost = new Host();

		System.out.print("Enter Casino Name: ");
		newHost.setCasinoName(theKeyboard.nextLine());

		System.out.print("Enter Gambler Id: ");
		newHost.setGamblerId(getIntValueFromUser());

		System.out.print("Enter Room Type: ");
		newHost.setRoomType(theKeyboard.nextLine());

		System.out.print("Enter Average Bet: ");
		newHost.setAvgBet(getIntValueFromUser());

		double creditLine = 0;

		System.out.println("Enter Credit Line:");

		boolean haveValidDouble = false;
		do {
			try {
				creditLine = theKeyboard.nextDouble();
				haveValidDouble = true;
			} catch (InputMismatchException exceptionInfo) {
				System.out.println("!!!!! Uh-Oh! UhOh! Uh-Oh! !!!!!");
				System.out.println("Expected an integer/whole number");
				System.out.println("Please try again");

				haveValidDouble = false;
			} finally {  // Regardless if there is an exception or not...
				theKeyboard.nextLine(); // Clear the enter left in the keyboard buffer by nextInt()
			}
		} // End of do
		while(!haveValidDouble);

		newHost.setCreditLine(creditLine);

		boolean wasAdded = theHostData.addHost(newHost);

		if (wasAdded) {
			System.out.println("Entry add successfully o data base: ");
		}
		else{
			System.out.println("!!!!! Row was not added to data base !!!!!\n");
		}
	}
	private void updateAHost() {

		System.out.print("Enter Casino Name for host entry to be updated: ");
		String casinoName4Update = theKeyboard.nextLine();

		System.out.print("Enter Gambler Id for host entry to be updated: ");
		int gamblerId4Update = getIntValueFromUser();

		Host updatedHost = theHostData.getAHostEntry(casinoName4Update, gamblerId4Update);

		if (updatedHost == null) {
			System.out.println("Unable to finf entry in database for host casino \"" + casinoName4Update + "\""
					+ " and gambled id \"" + gamblerId4Update + "\"");
			return;
		}

		System.out.print("Enter Room Type: ");
		updatedHost.setRoomType(theKeyboard.nextLine());

		System.out.print("Credit Line: ");
		double creditLine = 0;
		boolean haveValidDouble = false;

		do {
			try {
				creditLine = theKeyboard.nextDouble();
				haveValidDouble = true;
			} catch (InputMismatchException exceptionInfo) {
				System.out.println("!!!!! Uh-Oh! UhOh! Uh-Oh! !!!!!");
				System.out.println("Expected an integer/whole number");
				System.out.println("Please try again");

				haveValidDouble = false;
			} finally {  // Regardless if there is an exception or not...
				theKeyboard.nextLine(); // Clear the enter left in the keyboard buffer by nextInt()
			}
		} // end of do
			while (!haveValidDouble) ;
			updatedHost.setCreditLine(creditLine);

			System.out.print("Enter Average Bet: ");
			updatedHost.setAvgBet(getIntValueFromUser());

			boolean hostEntryUpdated = theHostData.updateHost(updatedHost);

			if (hostEntryUpdated) {
				System.out.println("Host entry in Host updated!");
			} else {
				System.out.println("Host entry in data base was NOT updated!");
			}
	} // End of UpdateHost()

	private void  deleteAHost() {

		System.out.print("Enter Casino Name for host entry to be updated: ");
		String casinoName2Delete = theKeyboard.nextLine();

		System.out.print("Enter Gambler Id for host entry to be updated: ");
		int gamblerId2Delete = getIntValueFromUser();

		if (theHostData.deleteAHostEntry(casinoName2Delete, gamblerId2Delete)) {
			System.out.println("Entry in host was successfully deleted");

		} else {
			System.out.println("Host entry NOT deleted");
		}
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
