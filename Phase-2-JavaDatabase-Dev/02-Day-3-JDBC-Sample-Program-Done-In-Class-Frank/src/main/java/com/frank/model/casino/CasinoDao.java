package com.frank.model.casino;

import java.util.List;

import com.frank.exceptions.DataBaseDeleteException;
import com.frank.exceptions.DataBaseInsertException;
import com.frank.exceptions.DataBaseUpdateException;

// DAO Interface for the casino Table in the data base

// Identify the C.R.U.D.  (and any other methods) required to access the data

// Define the operation required if one implements this interface

// Interface contains method signatures of the methods required

// A method signature:  access  return-type methodName(parameters);
//
//   the method signatures in an interface do not have any code 


public interface CasinoDao {
	
	// Get all casinos
	public List<Casino> getAllCasino();
	
	// Get a specific casino by name
	public Casino getCasinoByName(String name);
	
	// Add a new casino
	public void addCasino(Casino casino) throws DataBaseInsertException;
	
	// Update a specific casino
	public boolean updateCasino(Casino aCasino) throws DataBaseUpdateException;
	
	// Delete a specific casino
	public void trashCasino(String name) throws DataBaseDeleteException;
	

}
