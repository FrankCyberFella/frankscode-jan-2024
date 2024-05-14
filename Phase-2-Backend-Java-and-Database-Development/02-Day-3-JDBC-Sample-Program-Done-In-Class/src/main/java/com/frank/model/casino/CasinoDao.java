package com.frank.model.casino;

import java.util.List;

// DAO Interface for the casino Table in the data base

// Identify the C.R.U.D.  (and any other methods) required to access the data

public interface CasinoDao {
	
	// Get all casinos
	public List<Casino> getAllCasino();
	
	// Get a specific casino by name
	public Casino getCasinoByName(String name);
	
	// Add a new casino
	public void addCasino(Casino casino);
	
	// Update a specific casino
	public boolean updateCasino(Casino aCasino);
	
	// Delete a specific casino
	public void trashCasino(String name);
	

}
