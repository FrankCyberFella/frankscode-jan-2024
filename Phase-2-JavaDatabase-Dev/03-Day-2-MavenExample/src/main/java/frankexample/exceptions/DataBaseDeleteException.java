package frankexample.exceptions;

// This is a custom exception used when there is a data base delete error
//
// We wanted to provide more granularity to a problem than the
//    generic DataAccessException provided by Spring JDBC

// To define a custom exception:
//
//  1. Make it a subclass of Exception
//  2. Provide a default ctor that calls the Exception ctor (super())
//  3. Provide a ctor that takes a String parameter (error message)
//             and pass it to super ctor
//  4. Anything else you think you want in the custom exception

public class DataBaseDeleteException extends Exception {

	public DataBaseDeleteException() {
		super();
	}
	
	public DataBaseDeleteException(String message) {
		super(message);
		
		// Display the message to the user is this Exception is thrown
		System.out.println("Problem deleting data in database:");
		System.out.println(message);
	}
}

