package org.bhawanisingh.calotes.api.addlicense;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bhawanisingh.calotes.api.util.FileNames;
import org.bhawanisingh.calotes.api.logging.ExceptionLogger;
import org.bhawanisingh.calotes.api.logging.LoggerValues;

public abstract class AddLicense {
	private static Logger addLicenseLogger = LogManager.getLogger(AddLicense.class);
	public static void insertLicense(String sourceFolder, ArrayList<String> fileTypes, boolean recursive, boolean addCopy) {
		addLicenseLogger.entry();
		File tempFile;
		File rootDir = new File(sourceFolder);
		String[] childDir = rootDir.list();

		if (addCopy) {
			try {
				BufferedReader srcReader = new BufferedReader(new FileReader(new File(FileNames.TEMP_PATH + FileNames.SEPARATOR + FileNames.TEMP_LIC)));
				BufferedWriter destWriter = new BufferedWriter(new FileWriter(new File(rootDir, FileNames.LICENSE_NAME)));
				String line;
				while ((line = srcReader.readLine()) != null) {
					destWriter.write(line + "\n");
					destWriter.flush();
				}
				destWriter.close();
				srcReader.close();

			} catch (FileNotFoundException e) {
				addLicenseLogger.error("Source File /License.txt Not Found", e);
				ExceptionLogger.loggerFileNotFoundException(addLicenseLogger, e);
			} catch (IOException e) {
				addLicenseLogger.error("Source File /License.txt Not Found", e);
				ExceptionLogger.loggerIOException(addLicenseLogger, e);
			}

		}
		for (String child : childDir) {
			tempFile = new File(rootDir, child);
			if (tempFile.isFile()) {
				System.out.println(tempFile.getAbsolutePath() + "\tWorking");
				String name = tempFile.getName().substring(tempFile.getName().lastIndexOf(".") + 1);
				name = name.trim();
				if (fileTypes.contains(name)) {
					if ("java".equals(name) || "cs".equals(name) || "c".equals(name) || "i".equals(name) || "ii".equals(name)) {
						javaTypeAddLicense(tempFile);

					} else if ("m".equals(name) || "mi".equals(name) || "mii".equals(name) || "M".equals(name) || "mm".equals(name)) {
						javaTypeAddLicense(tempFile);

					} else if ("h".equals(name) || "cc".equals(name) || "cp".equals(name) || "cxx".equals(name) || "cpp".equals(name)) {
						javaTypeAddLicense(tempFile);

					} else if ("CPP".equals(name) || "c++".equals(name) || "C".equals(name) || "hh".equals(name) || "H".equals(name)) {
						javaTypeAddLicense(tempFile);

					} else if ("hp".equals(name) || "hxx".equals(name) || "hpp".equals(name) || "HPP".equals(name) || "h++".equals(name) || "tcc".equals(name)) {
						javaTypeAddLicense(tempFile);

					} else if ("py".equals(name)) {
						pythonTypeAddLicense(tempFile, "\"\"\"", "\"\"\"");

					}
				}
			} else if (recursive) {
				insertLicense(tempFile.getAbsolutePath(), fileTypes, recursive, false);
			}
		}
		addLicenseLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private static void javaTypeAddLicense(File tempFile) {
		addLicenseLogger.entry();
		try {
			File temp = new File(tempFile.getParent() + FileNames.SEPARATOR + "TEMPfile");
			BufferedWriter tempwriter = new BufferedWriter(new FileWriter(temp));
			BufferedReader sourceReader = new BufferedReader(new FileReader(tempFile));
			BufferedReader licenseReader = new BufferedReader(new FileReader(FileNames.TEMP_PATH + FileNames.SEPARATOR + FileNames.TEMP_LIC_TEMPLATE));
			String line;
			tempwriter.write("/**\n");
			tempwriter.flush();
			while ((line = licenseReader.readLine()) != null) {
				tempwriter.write("  *  " + line + "\n");
				tempwriter.flush();
			}
			tempwriter.write("  */\n");
			tempwriter.flush();
			while ((line = sourceReader.readLine()) != null) {
				tempwriter.write(line + "\n");
				tempwriter.flush();
			}
			tempwriter.close();
			sourceReader.close();
			licenseReader.close();
			tempFile.delete();
			temp.renameTo(tempFile);
			addLicenseLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
		} catch (IOException e) {
			addLicenseLogger.error("Error In Adding License To File", e);
			ExceptionLogger.loggerIOException(addLicenseLogger, e);
			addLicenseLogger.exit(LoggerValues.UNSUCCESSFUL_EXIT);
		}
	}

	static void pythonTypeAddLicense(File tempFile, String STARTING_PATTERN, String ENDING_PATTERN) {
		addLicenseLogger.entry();
		try {
			File temp = new File(tempFile.getParent() + FileNames.SEPARATOR + "TEMPfile");
			BufferedWriter tempwriter = new BufferedWriter(new FileWriter(temp));
			BufferedReader sourceReader = new BufferedReader(new FileReader(tempFile));
			BufferedReader licenseReader = new BufferedReader(new FileReader(FileNames.TEMP_PATH + FileNames.SEPARATOR + FileNames.TEMP_LIC_TEMPLATE));
			String line;
			String line2 = null;
			while ((line = sourceReader.readLine()) != null) {
				if (line.startsWith("#")) {
					tempwriter.write(line + "\n");
					tempwriter.flush();
				} else {
					line2 = line;
					break;
				}
			}
			tempwriter.write(STARTING_PATTERN + "\n");
			tempwriter.flush();
			while ((line = licenseReader.readLine()) != null) {
				tempwriter.write("  #  " + line + "\n");
				tempwriter.flush();
			}
			tempwriter.write(ENDING_PATTERN + "\n");
			tempwriter.flush();
			if (line2 != null) {
				tempwriter.write(line2 + "\n");
				tempwriter.flush();
			}
			while ((line = sourceReader.readLine()) != null) {
				tempwriter.write(line + "\n");
				tempwriter.flush();
			}
			tempwriter.close();
			sourceReader.close();
			licenseReader.close();
			tempFile.delete();
			temp.renameTo(tempFile);
			addLicenseLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
		} catch (IOException e) {
			addLicenseLogger.error("Error In Adding License To File", e);
			ExceptionLogger.loggerIOException(addLicenseLogger, e);
			addLicenseLogger.exit(LoggerValues.UNSUCCESSFUL_EXIT);
		}
	}
}