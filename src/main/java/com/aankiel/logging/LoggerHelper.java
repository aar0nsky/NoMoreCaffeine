package com.aankiel.logging;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerHelper {

	private Logger LOGGER;
	
	public LoggerHelper(String className) {
		initLogger(className);
	}

	private void initLogger(String className) {
		LOGGER = Logger.getLogger(className);
		Handler consoleHandler = null;
		Handler fileHandler = null;

		try {
			consoleHandler = new ConsoleHandler();
			fileHandler = new FileHandler("./" + className + ".log");
			// assign handlers to logger
			LOGGER.addHandler(consoleHandler);
			LOGGER.addHandler(fileHandler);

			// set levels
			consoleHandler.setLevel(Level.ALL);
			fileHandler.setLevel(Level.ALL);
			LOGGER.setLevel(Level.ALL);
		} catch (IOException exception) {
			LOGGER.log(Level.SEVERE, "Exception in initLogger");
		}

	}
	
	public Logger getLogger() {
		return this.LOGGER;
	}

}
