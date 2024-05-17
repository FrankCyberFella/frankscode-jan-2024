package com.frank.databaseerror;

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
        theErrorLog.writeToLog("Exception Object: " + exceptionObject.toString());
        theErrorLog.writeToLog("Message: " + exceptionObject.getMessage());
        theErrorLog.writeToLog("LocalizedMessage: " + exceptionObject.getLocalizedMessage());
        theErrorLog.writeToLog("Cause: " + exceptionObject.getCause().toString());
        StackTraceElement stackTraceEntries[] = exceptionObject.getStackTrace();
        for(StackTraceElement stackTraceElement : stackTraceEntries) {
            theErrorLog.writeToLog(stackTraceElement.toString());
        }
    }

}
