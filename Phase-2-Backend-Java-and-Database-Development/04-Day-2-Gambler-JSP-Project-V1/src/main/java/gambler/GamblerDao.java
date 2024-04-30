package gambler;

import java.util.List;

import exceptions.DataBaseDeleteException;
import exceptions.DataBaseInsertException;
import exceptions.DataBaseUpdateException;

/*****************************************************************************************************
 * gambler Table DAO - identify C.R.U.D. (and other methods) required to access gambler table
 *****************************************************************************************************/

public interface GamblerDao {
	
	// NOTE: Objects are used in the DAO methods instead of individual variables
	//       except for find and delete methods
	//       When saving or updating data to the database - objects are used NOT variables

	// Save the given gambler object to the database
	public boolean addGambler(Gambler newGambler) throws DataBaseInsertException;
	
	// Return a gambler object from the database for the all gambler in the data base
	public List<Gambler> getAllGamblers();;

	// Return a gambler object from the database for the id specified
	public Gambler findGamblerById(long id);

	// Return all the gambler objects from the database for the given name or partial name
	public List<Gambler> findGamblerByName(String name);

	// Update the gambler data in the database using the gambler object passed
	public void update(Gambler gambler) throws DataBaseUpdateException, DataBaseDeleteException;

	// Delete the gambler data in the database for the given id
	public void delete(long id) throws DataBaseDeleteException;

}
