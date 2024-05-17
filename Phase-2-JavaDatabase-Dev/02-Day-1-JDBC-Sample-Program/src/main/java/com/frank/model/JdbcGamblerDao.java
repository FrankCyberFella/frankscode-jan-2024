package com.frank.model;

/*****************************************************************************************************************
 * Implement methods for manipulating are returning data from the Gambler table
 * At minimum, implement the methods required by the GamblerDao interface
 
    Gambler table as defined in the database
  	
 	+----------------+--------------+------+-----+---------+----------------+
    | Field          | Type         | Null | Key | Default | Extra          |
    +----------------+--------------+------+-----+---------+----------------+
    | id             | smallint     | NO   | PRI | NULL    | auto_increment |
    | gambler_name   | char(20)     | NO   |     | NULL    |                |
    | address        | char(20)     | YES  |     | NULL    |                |
    | birth_date     | date         | NO   |     | NULL    |                |
    | monthly_salary | decimal(9,2) | NO   |     | NULL    |                |
    +----------------+--------------+------+-----+---------+----------------+

 *****************************************************************************************************************/

import java.util.ArrayList;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcGamblerDao implements GamblerDao {
	
	// define a reference to the JdbcTemplate object we will use to access Spring DAO Framework
	private JdbcTemplate theDataBase;

	// constructor for the class which takes the dataSource as a parameter
	// dataSource will be provided when this DAO is instantiated (from application program)
	public JdbcGamblerDao(DataSource dataSource) {
		// Instantiate a JdbcTemplate object with the dataSource give and assign it to our reference
		this.theDataBase = new JdbcTemplate(dataSource);
	}


	@Override
	public List<Gambler> getAllGamblers() {
		List<Gambler> allGamblers = new ArrayList();
		
		String selectAllGamblersSql = "select * from gambler";
		
	    SqlRowSet allGamblerRows = theDataBase.queryForRowSet(selectAllGamblersSql);

        while(allGamblerRows.next()) {
            allGamblers.add(MapRowToGambler(allGamblerRows));
        }
		
		return allGamblers;
	}

	@Override
	public boolean addGambler(Gambler gamblerToAdd) {
		
		Gambler aNewGambler = new Gambler();
		
		String addAGamblerSql = "INSERT INTO gambler "
				              + " (gambler_name, address, birth_date, monthly_salary) "
				              + " VALUES (?, ?, ?, ?)";
		
        
		int numRowsAdded = theDataBase.update(addAGamblerSql
				                             ,gamblerToAdd.getName()
				                             ,gamblerToAdd.getAddress()
				                             ,gamblerToAdd.getBirthDate()
				                             ,gamblerToAdd.getMonthlySalary()
			                                 );
		
		if (numRowsAdded == 1) {
			return true;
		}
		return false;
		
	}

	@Override
	public Gambler findGamblerById(long id) {
	
		String selectAGamblerByIdSql = "select * from gambler where id = ?";
		
	    SqlRowSet aGamblerRow = theDataBase.queryForRowSet(selectAGamblerByIdSql, id);

	    Gambler aGambler = null;
	   
	    if(aGamblerRow.next()) {
         aGambler = MapRowToGambler(aGamblerRow);
	    }
	    
		return aGambler;
	}

	@Override
	public List<Gambler> findGamblerByName(String name) {
		
		List<Gambler> gamblersFound = new ArrayList();		
		
		String selectByNameSql = "Select * from gambler "
				                + "Where gambler_name = ?";
		
		SqlRowSet rowsFromDataBase = theDataBase.queryForRowSet(selectByNameSql, name);
		
		  while(rowsFromDataBase.next()) {
	            gamblersFound.add(MapRowToGambler(rowsFromDataBase));
	        }
				
				                      
		return gamblersFound;
	}

	@Override
	public void update(Gambler gamblerPassed) {
		
		String updateGamblerSql = " update gambler "
				                + " set gambler_name = ? "
				                + "            ,address = ?"
				                + "            ,birth_date = ?"
				                + "            ,monthly_salary = ?";
		
		theDataBase.update(updateGamblerSql, gamblerPassed.getName()
				                           , gamblerPassed.getAddress()
				                           , gamblerPassed.getBirthDate()
				                           , gamblerPassed.getMonthlySalary()
				
						  );
		
	}

	@Override
	public void delete(long id) {
		
		String deleteByIdSql = "delete from gambler where id = ?";
		
		theDataBase.update(deleteByIdSql, id);
	}		
	
	Gambler MapRowToGambler(SqlRowSet aRow) {
        Gambler aGambler = new Gambler();

        aGambler.setId(aRow.getInt("id"));
        aGambler.setBirthDate(aRow.getDate("birth_date").toLocalDate());
        aGambler.setAddress(aRow.getString("address"));
        aGambler.setName(aRow.getString("gambler_name"));
        aGambler.setMonthlySalary(aRow.getDouble("monthly_salary"));

        return aGambler;
    }
	
} // End of class
