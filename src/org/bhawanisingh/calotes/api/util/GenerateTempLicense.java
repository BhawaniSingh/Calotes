package org.bhawanisingh.calotes.api.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bhawanisingh.calotes.api.logging.ExceptionLogger;
import org.bhawanisingh.calotes.api.logging.LoggerValues;

public class GenerateTempLicense {
	
	private static Logger generateTempLicenseLogger = LogManager.getLogger(GenerateTempLicense.class); 
	
	public static void tempLicense(String licensename) {
		generateTempLicenseLogger.entry();
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(FileNames.TEMP_PATH + FileNames.SEPARATOR + FileNames.TEMP_LIC));
			InputStream inputStream = GenerateTempLicense.class.getResourceAsStream(FileNames.LIC_TEMPLATE_PATH + FileNames.SEPARATOR + licensename + ".lic");
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String line;
			while((line = reader.readLine())!=null){
				writer.write(line+"\n");
				writer.flush();
			}
			reader.close();
			inputStream.close(); 
			writer.close();
			generateTempLicenseLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
		} catch (FileNotFoundException e) {
			generateTempLicenseLogger.error("Error In Finding Temporary License", e);
			ExceptionLogger.loggerFileNotFoundException(generateTempLicenseLogger, e);
			generateTempLicenseLogger.exit(LoggerValues.UNSUCCESSFUL_EXIT);
		} catch (IOException e) {
			generateTempLicenseLogger.error("I/O Exception I Don't Know", e);
			ExceptionLogger.loggerIOException(generateTempLicenseLogger, e);
			generateTempLicenseLogger.exit(LoggerValues.UNSUCCESSFUL_EXIT);
		}
	}
	
	public static void tempLicenseFromString(String license){
		generateTempLicenseLogger.entry();
		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(FileNames.TEMP_PATH + FileNames.SEPARATOR + FileNames.TEMP_LIC));
			writer.write(license.trim());
			writer.flush();
			writer.close();
			generateTempLicenseLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
		} catch (FileNotFoundException e) {
			generateTempLicenseLogger.error("Error In Finding Temporary License", e);
			ExceptionLogger.loggerFileNotFoundException(generateTempLicenseLogger, e);
			generateTempLicenseLogger.exit(LoggerValues.UNSUCCESSFUL_EXIT);
		} catch (IOException e) {
			generateTempLicenseLogger.error("I/O Exception I Don't Know", e);
			ExceptionLogger.loggerIOException(generateTempLicenseLogger, e);
			generateTempLicenseLogger.exit(LoggerValues.UNSUCCESSFUL_EXIT);
		}
	}
	
	public static void tempLicenseTemplate(String licenseTemplate) {
		generateTempLicenseLogger.entry();
		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(FileNames.TEMP_PATH + FileNames.SEPARATOR + FileNames.TEMP_LIC_TEMPLATE));
			writer.write(licenseTemplate.trim());
			writer.flush();
			writer.close();
			generateTempLicenseLogger.exit(LoggerValues.SUCCESSFUL_EXIT);

		} catch (FileNotFoundException e) {
			generateTempLicenseLogger.error("Error In Finding Temporary License", e);
			ExceptionLogger.loggerFileNotFoundException(generateTempLicenseLogger, e);
			generateTempLicenseLogger.exit(LoggerValues.UNSUCCESSFUL_EXIT);
		} catch (IOException e) {
			generateTempLicenseLogger.error("I/O Exception I Don't Know", e);
			ExceptionLogger.loggerIOException(generateTempLicenseLogger, e);
			generateTempLicenseLogger.exit(LoggerValues.UNSUCCESSFUL_EXIT);
		}
	}

	public static BufferedReader aboutSoft(){
		return tempLicenseTepmlateStream(FileNames.PROGRAM_LICENSE_NAME, FileNames.ABOUT_PROGRAM, FileNames.AUTHOR_NAME, FileNames.COPYRIGHT_YEAR, FileNames.ORGANIZATION);
	}
	
	public static BufferedReader tempLicenseTepmlateStream(String licenseName, String program, String author, String year,String organisation) {
		generateTempLicenseLogger.entry();
		try {
			InputStream inputStream = GenerateTempLicense.class.getResourceAsStream(FileNames.LIC_TEMPLATE_PATH + "/" + licenseName + ".lic");
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			BufferedWriter writer = new BufferedWriter(new FileWriter(FileNames.TEMP_PATH + FileNames.SEPARATOR + FileNames.TEMP_LIC_TEMPLATE));
			String line;
			while((line = reader.readLine())!=null){
				if(line.contains("<AUTHOR>")){
					line = line.replace("<AUTHOR>", author);
				}
				if(line.contains("<PROGRAM>")){
					line = line.replace("<PROGRAM>", program);
				}
				if(line.contains("<YEAR>")){
					line = line.replace("<YEAR>", year);
				}
				if(line.contains("<ORGANISATION>")){
					line = line.replace("<ORGANISATION>", organisation);
				}
				writer.write(line+"\n");
				writer.flush();
			}
			reader.close();
			inputStream.close(); 
			writer.close();
			reader = new BufferedReader(new FileReader(FileNames.TEMP_PATH + FileNames.SEPARATOR + FileNames.TEMP_LIC_TEMPLATE));
			generateTempLicenseLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
			return reader;

		} catch (FileNotFoundException e) {
			generateTempLicenseLogger.error("Error In Finding Temporary License", e);
			ExceptionLogger.loggerFileNotFoundException(generateTempLicenseLogger, e);
			generateTempLicenseLogger.exit(LoggerValues.UNSUCCESSFUL_EXIT);
		} catch (IOException e) {
			generateTempLicenseLogger.error("I/O Exception I Don't Know", e);
			ExceptionLogger.loggerIOException(generateTempLicenseLogger, e);
			generateTempLicenseLogger.exit(LoggerValues.UNSUCCESSFUL_EXIT);
		}
		return null;
	}
}