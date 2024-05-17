package exceptions;

public class DataBaseUpdateException extends Exception {

	public DataBaseUpdateException() {
		super();
	}
	
	public DataBaseUpdateException(String message) {
		super(message);
		
		System.out.println("Problem updating data in database:");
		System.out.println(message);
	}
}

