package errorlog;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ErrorLog {

    private static File           logFile;
    private static FileWriter     aFileWriter;
    private static BufferedWriter aBufferedWriter;
    private static PrintWriter    logWriter;

    private static String logFileName;

    private static boolean isFileDefined = false;

    public ErrorLog(String prefix) throws IOException {

        if (!isFileDefined) {
            String currentDateTime = LocalDate.now().toString();
            logFileName = prefix+"errorLog-" + currentDateTime + ".log";
            logFile = new File("./"+logFileName);
            aFileWriter = new FileWriter(logFile, true);
            aBufferedWriter = new BufferedWriter(aFileWriter);
            logWriter = new PrintWriter(aBufferedWriter);
            
            System.out.println("Error Log: " + logFile.getAbsolutePath() + "\n");

            isFileDefined = true;
        }
    }
     public void writeToLog(String message) {
        logWriter.println(LocalDateTime.now().toString().replace("T"," ")
                         + " - "
                         + message);
       flushErrorLog();
    }

     public void flushErrorLog() {
    	 logWriter.flush();
     }
     
     public String getErrorLogFileName() {
    	 return logFile.getAbsolutePath();
     }
}
