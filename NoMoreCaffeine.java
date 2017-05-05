import java.io.IOException;
import java.lang.Runtime;
import java.lang.InterruptedException;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;

public class NoMoreCaffeine {
	private static final Logger LOGGER = Logger.getLogger( NoMoreCaffeine.class.getName() );

	public static void main(String[] args) throws IOException {
		initLogger();
		killProcess("caffeine.exe");
		LOGGER.warning("NoMoreCaffeine Exiting...");
	}

	private static void initLogger() {
		Handler consoleHandler = null;
        Handler fileHandler  = null;
		try{
        	consoleHandler = new ConsoleHandler();
            fileHandler  = new FileHandler("./NoMoreCaffeine.log");

            // assign handlers to logger
            LOGGER.addHandler(consoleHandler);
	        LOGGER.addHandler(fileHandler);

	        // set levels
	        consoleHandler.setLevel(Level.ALL);
            fileHandler.setLevel(Level.ALL);
            LOGGER.setLevel(Level.ALL);
            LOGGER.warning("NoMoreCaffeine Starting...");
        }
        catch(IOException exception){
        	LOGGER.log(Level.SEVERE, "Exception in initLogger");
        }
	}

	private static void killProcess(String processName) {
		String processString = "Taskkill /IM " + processName + " /F";
		try {
			java.lang.Runtime rt = java.lang.Runtime.getRuntime();
           	java.lang.Process p = rt.exec(processString);
           	p.waitFor();
           	int procExitValue = p.exitValue();

           	if(procExitValue == 0){
           		LOGGER.warning("Process Successfully Killed");
           	}
           	else {
           		LOGGER.warning("Process Failed to be Killed");
           	}
		}
		catch(IOException ioe){
			LOGGER.log(Level.SEVERE, "Exception killing process",ioe);
		}
		catch(InterruptedException ie){
			LOGGER.log(Level.SEVERE, "Exception killing process", ie);
		}
	}
}