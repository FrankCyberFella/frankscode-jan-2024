package exceptions;

public class DataBaseDeleteException extends Exception {

	public DataBaseDeleteException() {
		super();
	}
	
	public DataBaseDeleteException(String message) {
		super(message);
		
		System.out.println("Problem deleting data in database:");
		System.out.println(message);
	}
}

