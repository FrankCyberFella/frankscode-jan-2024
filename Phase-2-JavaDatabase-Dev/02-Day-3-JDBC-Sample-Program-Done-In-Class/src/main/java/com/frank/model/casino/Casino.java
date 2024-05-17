package com.frank.model.casino;

import java.util.Objects;

// POJO that represents the casino table in the data base

/* Columns in the casino table
	+-------------+----------+------+-----+---------+-------+
	| Field       | Type     | Null | Key | Default | Extra |
	+-------------+----------+------+-----+---------+-------+
	| casino_name | char(20) | NO   | PRI | NULL    |       |
	| location    | char(15) | NO   |     | NULL    |       |
	| owner       | char(20) | NO   |     | NULL    |       |
	| size        | int      | NO   |     | NULL    |       |
	+-------------+----------+------+-----+---------+-------+
 */

public class Casino {
 

	
	/*****************************************************************
	 * Instance variables for table columns
	 * 
	 * Make them private to support encapsulation
	 * (only members of class have access)
	 *****************************************************************/

	private String casinoName;
	private String location;
	private String owner;
	private int    size;
	
	// Add a default ctor since we have at least one other ctor Java doesn't generate it
	// You need this because Java uses it behind the scenes for several things
	//     such as when you create a array of the class objects
	
	public Casino() {}; 
	
	public Casino(String casinoName, String location, String owner, int size) {
		this.casinoName = casinoName;
		this.location = location;
		this.owner = owner;
		this.size = size;
	}

	public String getCasinoName() {
		return casinoName;
	}

	public void setCasinoName(String casinoName) {
		this.casinoName = casinoName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public int hashCode() {
		return Objects.hash(casinoName, location, owner, size);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Casino other = (Casino) obj;
		return Objects.equals(casinoName, other.casinoName) && Objects.equals(location, other.location)
				&& Objects.equals(owner, other.owner) && size == other.size;
	}

	@Override
	public String toString() {
		return "casino [casinoName=" + casinoName + ", location=" + location + ", owner=" + owner + ", size=" + size
				+ "]";
	}

	
} // End of casino class POJO



