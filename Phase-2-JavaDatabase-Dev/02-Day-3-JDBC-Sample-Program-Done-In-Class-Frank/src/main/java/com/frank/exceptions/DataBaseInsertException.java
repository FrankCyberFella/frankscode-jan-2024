package com.frank.exceptions;

public class DataBaseInsertException extends Exception {

	public DataBaseInsertException() {
		super();
	}
	
	public DataBaseInsertException(String message) {
		super(message);
		
		System.out.println("Problem adding data to database:");
		System.out.println(message);
	}
}

