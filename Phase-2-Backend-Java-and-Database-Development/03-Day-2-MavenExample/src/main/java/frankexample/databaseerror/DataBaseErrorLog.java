package frankexample.databaseerror;

import java.io.IOException;

import frankexample.errorlog.ErrorLog;
/***************************************************************************
 * Provide the functionality to log details for a data base error
 * 
 * Your typical human user does not want see a bunch of technical info when there is an error
 * 
 * Developers need the details to help debug the error.
 * 
 * When an Exception is thrown the catch block receives an Exception object
 *    containing details on the reason for the exception.
 *    
 * Having the the Exception block information available when debugging and exception is invaluable 
 * 
 * Logging that information to a file rather than just displaying in the screen make the information persistent
 * (Usually people clear the screen when there is an error and you lose the information)  
 * 
 */
public class DataBaseErrorLog {

	// Define a reference to an ErrorLog for use in this class
    private static ErrorLog theErrorLog;

    public DataBaseErrorLog(String filePrefix) throws IOException {
    	// Instantiate an ErrorLog object to log our database errors with the file prefix passed
        theErrorLog = new ErrorLog(filePrefix); 
    }
    // Write whatever message passed to the error log
    public void writeToDatabaseErrorLog(String message){
        theErrorLog.writeToLog(message);
    }
    // Write Exception Object information to the error log
    public void writeExceptionInfoToDatabaseErrorLog(Exception exceptionObject) {
        theErrorLog.writeToLog("-".repeat(75));
        //                     type of information    data from the excpetion object
        theErrorLog.writeToLog("Exception Object: " + exceptionObject.toString());
        theErrorLog.writeToLog("Message: " + exceptionObject.getMessage());
        theErrorLog.writeToLog("LocalizedMessage: " + exceptionObject.getLocalizedMessage());
        theErrorLog.writeToLog("Cause: " + exceptionObject.getCause().toString());
        
        // The stacktrace is a record of how we got to the line of code that caused the exception
        // Get the stack trace as an array
        StackTraceElement stackTraceEntries[] = exceptionObject.getStackTrace();
        // Loop through the stack trace entries writing them to error log
        for(StackTraceElement stackTraceElement : stackTraceEntries) {
            theErrorLog.writeToLog(stackTraceElement.toString());
        }
    }

}
