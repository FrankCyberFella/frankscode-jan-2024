package com.frank.model.host;

import com.frank.model.casino.Casino;

import java.util.List;

// DAO Interface for the host Table in the data base

// Identify the C.R.U.D.  (and any other methods) required to access the data

public interface HostDao {
	
	// Get all host rows
	public List<Host> getAllHost();

	// Get a specific Host entry
	public Host getAHostEntry(String casinoName, int gamblerId);

	// Get Host entries by Gambler name
	public List<Host> getHostByGamblerId(int id);
	// Get Host entries by Casino name
	public List<Host> getHostByCasinoName(String casinoName);

	// Add a new Host entry
	public boolean addHost(Host Host);
	
	// Update a specific Host entry
	public boolean updateHost(Host aHost);
	
	// Delete a specific Host entry
	public boolean deleteAHostEntry(String casinoName, int gamblerId);
	

}
