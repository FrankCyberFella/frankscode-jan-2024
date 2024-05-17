package frankexample.errorlog;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
/****************************************************************
 * Define a error logging process
 *****************************************************************/
public class ErrorLog {

	// Object required to define a disk file and processes to add to the end of it
	//        when we write to it
	
	// All variables are static so every instance of the class shares the file info
	//     so we don't get multiple error log files for the application
	
    private static File           logFile;          // File Object
    private static FileWriter     aFileWriter;      // FileWriter object for the File object
    private static BufferedWriter aBufferedWriter;  // BuffereredWriter for the FileWriter
    private static PrintWriter    logWriter;        // PrintWriter for the BufferedWrite

    private static String logFileName;              // Name of the log file

    private static boolean isFileDefined = false;   // Remember if the file is already defined
                                                    //     so we don't define it more than once

    // Constructor will instantiate and initialize all the variables
    //      receive a prefix to use in the log file name
    public ErrorLog(String prefix) throws IOException {

        if (!isFileDefined) {                                                // If file is not yet defined
            String currentDateTime = LocalDate.now().toString();             // Get the currentDate as a String
            logFileName     = prefix+"errorLog-" + currentDateTime + ".log"; // Generate file name with prefix passed and current date
            logFile         = new File(logFileName);
            aFileWriter     = new FileWriter(logFile, true);                 // true makes the file appendable
            aBufferedWriter = new BufferedWriter(aFileWriter);
            logWriter       = new PrintWriter(aBufferedWriter);

            isFileDefined = true;
        }
    }
    // Write the message passed to the error log file with the date and time
     public void writeToLog(String message) {
    	// replace the "T" in front of time with a space
        logWriter.println(LocalDateTime.now().toString().replace("T"," ") 
                         + " - "
                         + message);
        logWriter.flush();  // Write the message from the file buffer to the disk right now
                            //       rather than wait until buffer is full
                            // If an unhandled exception occurs, the application terminates
        		            //    and any data left in any buffer is lost
    }

}
