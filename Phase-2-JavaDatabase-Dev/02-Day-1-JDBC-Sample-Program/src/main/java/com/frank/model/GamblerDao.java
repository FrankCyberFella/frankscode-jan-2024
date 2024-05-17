package com.frank.model;

import java.util.List;

/*****************************************************************************************************
 * Gambler Table DAO - identify C.R.U.D. (and otehr methods) required to access Gambler table
 *****************************************************************************************************/

public interface GamblerDao {
	
	// NOTE: Objects are used in the DAO methods instead of individual variables
	//       except for find and delete methods
	//       When saving or updating data to the database - objects are used NOT variables

	// Save the given Gambler object to the database
	public boolean addGambler(Gambler newGambler);
	
	// Return a Gambler object from the database for the id specified
	public List<Gambler> getAllGamblers();;

	// Return a Gambler object from the database for the id specified
	public Gambler findGamblerById(long id);

	// Return all the Gambler objects from the database for the given name or partial name
	public List<Gambler> findGamblerByName(String name);

	// Update the Gambler data in the database using the Gambler object passed
	public void update(Gambler gambler);

	// Delete the Gambler data in the database for the given id
	public void delete(long id);

}
