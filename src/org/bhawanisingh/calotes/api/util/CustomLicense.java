package org.bhawanisingh.calotes.api.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bhawanisingh.calotes.api.logging.ExceptionLogger;
import org.bhawanisingh.calotes.api.logging.LoggerValues;

public class CustomLicense {

	private static Logger customLicenseLogger = LogManager.getLogger(CustomLicense.class);

	public static BufferedReader generateLicense(String fileName) {
		customLicenseLogger.entry();
		try {
			FileReader reader = new FileReader(new File(fileName));
			BufferedReader bufferedReader = new BufferedReader(reader);
			customLicenseLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
			return bufferedReader;
		} catch (FileNotFoundException e) {
			customLicenseLogger.error("Custom License File Not Found", e);
			ExceptionLogger.loggerFileNotFoundException(customLicenseLogger, e);
		}
		customLicenseLogger.exit(LoggerValues.UNSUCCESSFUL_EXIT);
		return null;
	}

	public static void generateLicenseFile(String text) {
		BufferedWriter bufferedWriter;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(FileNames.TEMP_PATH + FileNames.SEPARATOR + FileNames.TEMP_LIC));
			bufferedWriter.write(text);
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (IOException e) {
			customLicenseLogger.error("Unable To Create Custom License Template File", e);
			ExceptionLogger.loggerIOException(customLicenseLogger, e);
		}
	}
}