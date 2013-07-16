package org.bhawanisingh.calotes.api.logging;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.logging.log4j.Logger;

public class ExceptionLogger {

	public static void loggerException(Logger logger, Exception exception) {
		logger.error("\n\t Exception Class : " + exception.getClass()
				+ "\n\t Cause Of Exception : " + exception.getCause()
				+ "\n\t Local Exception Message : " + exception.getLocalizedMessage()
				+ "\n\t Exception Message : " + exception.getMessage());
	}

	public static void loggerIOException(Logger logger, IOException exception) {
		ExceptionLogger.loggerException(logger, exception);
	}

	public static void loggerFileNotFoundException(Logger logger, FileNotFoundException exception) {
		ExceptionLogger.loggerException(logger, exception);
	}

	public static void loggerURISyntaxException(Logger logger, URISyntaxException exception) {
		logger.error("\n\t Exception Class : " + exception.getClass()
				+ "\n\t Cause Of Exception : " + exception.getCause()
				+ "\n\t Local Exception Message : " + exception.getLocalizedMessage()
				+ "\n\t Exception Message : " + exception.getMessage()
				+ "\n\t Exception Reason : " + exception.getReason()
				+ "\n\t Exception Index : " + exception.getIndex()
				+ "\n\t Exception Input : " + exception.getInput());
	}

}