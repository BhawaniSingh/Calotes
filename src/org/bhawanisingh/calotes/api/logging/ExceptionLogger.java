package org.bhawanisingh.calotes.api.logging;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import javax.swing.UnsupportedLookAndFeelException;

import org.apache.logging.log4j.Logger;

public class ExceptionLogger {

	public static void loggerIOException(Logger logger, IOException exception) {
		ExceptionLogger.loggerException(logger, exception);
	}

	public static void loggerFileNotFoundException(Logger logger, FileNotFoundException exception) {
		ExceptionLogger.loggerException(logger, exception);
	}

	public static void loggerNullPointException(Logger logger, NullPointerException nullPointerException) {
		ExceptionLogger.loggerException(logger, nullPointerException);
	}

	public static void loggerMalformedURLException(Logger logger, MalformedURLException malformedURLException) {
		ExceptionLogger.loggerException(logger, malformedURLException);
	}

	public static void loggerInstantiationException(Logger logger, InstantiationException instantiationException) {
		ExceptionLogger.loggerException(logger, instantiationException);
	}

	public static void loggerIllegalAccessException(Logger logger, IllegalAccessException illegalAccessException) {
		ExceptionLogger.loggerException(logger, illegalAccessException);
	}

	public static void loggerUnsupportedLookAndFeelException(Logger logger, UnsupportedLookAndFeelException unsupportedLookAndFeelException) {
		ExceptionLogger.loggerException(logger, unsupportedLookAndFeelException);
	}

	public static void loggerException(Logger logger, Exception exception) {
		logger.catching(exception);
		logger.error("\n\t Exception Class => \t" + exception.getClass()
				+ "\n\t Cause Of Exception : " + exception.getCause()
				+ "\n\t Local Exception Message : " + exception.getLocalizedMessage()
				+ "\n\t Exception Message : " + exception.getMessage(), exception);
	}

	public static void loggerURISyntaxException(Logger logger, URISyntaxException exception) {
		logger.error("\n\t Exception Class : " + exception.getClass()
				+ "\n\t Cause Of Exception : " + exception.getCause()
				+ "\n\t Local Exception Message : " + exception.getLocalizedMessage()
				+ "\n\t Exception Message : " + exception.getMessage()
				+ "\n\t Exception Reason : " + exception.getReason()
				+ "\n\t Exception Index : " + exception.getIndex()
				+ "\n\t Exception Input : " + exception.getInput(), exception);
	}

	public static void loggerClassNotFoundException(Logger logger, ClassNotFoundException classNotFoundException) {
		logger.catching(classNotFoundException);
		logger.error("\n\t Type Of Exception\t => \t" + classNotFoundException.getClass()
				+ "\n\t Cause\t => \t" + classNotFoundException.getCause()
				+ "\n\t Message\t => \t" + classNotFoundException.getMessage()
				+ "\n\t Localized Message\t => \t" + classNotFoundException.getLocalizedMessage()
				+ "\n\t Exception\t => \t" + classNotFoundException.getException());
	}
}