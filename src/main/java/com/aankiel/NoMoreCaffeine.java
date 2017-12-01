package com.aankiel;

import java.io.IOException;
import java.lang.InterruptedException;
import java.util.logging.Logger;

import com.aankiel.logging.LoggerHelper;

import java.util.logging.Level;

public class NoMoreCaffeine {
	
	private static final Logger LOGGER = new LoggerHelper(NoMoreCaffeine.class.getName()).getLogger();

	public static void main(String[] args) throws IOException {
		LOGGER.warning("NoMoreCaffeine Starting...");
		killProcess("caffeine.exe");
		LOGGER.warning("NoMoreCaffeine Exiting...");
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