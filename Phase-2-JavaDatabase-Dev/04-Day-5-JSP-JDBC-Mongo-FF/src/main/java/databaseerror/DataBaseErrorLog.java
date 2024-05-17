package databaseerror;

import errorlog.ErrorLog;

import java.io.IOException;

public class DataBaseErrorLog {

    private static ErrorLog theErrorLog;

    public DataBaseErrorLog(String filePrefix) throws IOException {
        theErrorLog = new ErrorLog(filePrefix);
    }
    public void writeToDatabaseErrorLog(String message){
        theErrorLog.writeToLog(message);
    }
    public void writeExceptionInfoToDatabaseErrorLog(Exception exceptionObject) {
        theErrorLog.writeToLog("-".repeat(75));
        /********************************************************************************************
         * Not all information from the exception object need to be displayed/logged
         * 
         * de-comment items you want displayed/logged
         * 
         * It is good practice to be sure object to be processed is not null before using it
         *******************************************************************************************/
        //if(exceptionObject != null) {
        //	theErrorLog.writeToLog("Exception Object: " + exceptionObject.toString());
        //}
        if(exceptionObject.getMessage() != null) {
        theErrorLog.writeToLog("Message: " + exceptionObject.getMessage());
        }
        //if(exceptionObject.getLocalizedMessage() != null) {
        //theErrorLog.writeToLog("LocalizedMessage: " + exceptionObject.getLocalizedMessage());
        //}
        if(exceptionObject.getCause() != null) {
        theErrorLog.writeToLog("Cause: " + exceptionObject.getCause().toString());
        }
        StackTraceElement stackTraceEntries[] = exceptionObject.getStackTrace();
        for(StackTraceElement stackTraceElement : stackTraceEntries) {
            theErrorLog.writeToLog(stackTraceElement.toString());
        }
    }
    
    public String getErrorLogFileName() {
    	return theErrorLog.getErrorLogFileName();
    }
    

}
