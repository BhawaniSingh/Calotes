package org.bhawanisingh.calotes.api.removelicense;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bhawanisingh.calotes.api.logging.ExceptionLogger;
import org.bhawanisingh.calotes.api.logging.LoggerValues;
import org.bhawanisingh.calotes.api.util.FileNames;

public class RemoveLicense {
	private static Logger removeLicenseLogger = LogManager.getLogger(RemoveLicense.class);
	private static String STARTING_PATTERN;
	private static String ENDING_PATTERN;

	public static void removeLicense(String sourceFolder, ArrayList<String> fileTypes, boolean recursive) {

		RemoveLicense.removeLicenseLogger.entry();
		File tempFile;
		File rootDir = new File(sourceFolder);
		String[] childDir = rootDir.list();
		for (String child : childDir) {
			tempFile = new File(rootDir, child);
			if (tempFile.isFile()) {
				System.out.println(tempFile.getAbsolutePath() + "\tWorking");
				String name = tempFile.getName().substring(tempFile.getName().lastIndexOf(".") + 1);
				name = name.trim();
				if ("java".equals(name) || "cs".equals(name) || "c".equals(name) || "i".equals(name) || "ii".equals(name)) {
					if (fileTypes.contains(name)) {
						RemoveLicense.STARTING_PATTERN = "/**";
						RemoveLicense.ENDING_PATTERN = "*/";
						RemoveLicense.removeLicense(tempFile);
					}

				} else if ("m".equals(name) || "mi".equals(name) || "mii".equals(name) || "M".equals(name) || "mm".equals(name)) {
					if (fileTypes.contains(name)) {
						RemoveLicense.STARTING_PATTERN = "/**";
						RemoveLicense.ENDING_PATTERN = "*/";
						RemoveLicense.removeLicense(tempFile);
					}

				} else if ("h".equals(name) || "cc".equals(name) || "cp".equals(name) || "cxx".equals(name) || "cpp".equals(name)) {
					if (fileTypes.contains(name)) {
						RemoveLicense.STARTING_PATTERN = "/**";
						RemoveLicense.ENDING_PATTERN = "*/";
						RemoveLicense.removeLicense(tempFile);
					}

				} else if ("CPP".equals(name) || "c++".equals(name) || "C".equals(name) || "hh".equals(name) || "H".equals(name)) {
					if (fileTypes.contains(name)) {
						RemoveLicense.STARTING_PATTERN = "/**";
						RemoveLicense.ENDING_PATTERN = "*/";
						RemoveLicense.removeLicense(tempFile);
					}

				} else if ("hp".equals(name) || "hxx".equals(name) || "hpp".equals(name) || "HPP".equals(name) || "h++".equals(name) || "tcc".equals(name) || "js".equals(name)) {
					if (fileTypes.contains(name)) {
						RemoveLicense.STARTING_PATTERN = "/**";
						RemoveLicense.ENDING_PATTERN = "*/";
						RemoveLicense.removeLicense(tempFile);
					}

				} else if ("js".equals(name)) {
					if (fileTypes.contains(name)) {
						RemoveLicense.STARTING_PATTERN = "/**";
						RemoveLicense.ENDING_PATTERN = "*/";
						RemoveLicense.removeLicense(tempFile);
					}
				} else if ("py".equals(name)) {
					if (fileTypes.contains(name)) {
						RemoveLicense.STARTING_PATTERN = "\"\"\"";
						RemoveLicense.ENDING_PATTERN = "\"\"\"";
						RemoveLicense.removeLicense(tempFile);
					}
				}
			} else if (recursive) {
				RemoveLicense.removeLicense(tempFile.getAbsolutePath(), fileTypes, recursive);
			}
		}
		RemoveLicense.removeLicenseLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private static void removeLicense(File tempFile) {
		RemoveLicense.removeLicenseLogger.entry();
		File temp = new File(tempFile.getParent() + FileNames.SEPARATOR + "TEMPfile");
		try {
			BufferedWriter tempwriter = new BufferedWriter(new FileWriter(temp));
			BufferedReader sourceReader = new BufferedReader(new FileReader(tempFile));
			String line;
			boolean found = false;
			while ((line = sourceReader.readLine()) != null) {
				if (line.trim().startsWith(RemoveLicense.STARTING_PATTERN) && !found) {
					found = true;
					while ((line = sourceReader.readLine()) != null) {
						if (line.trim().contains(RemoveLicense.ENDING_PATTERN)) {
							line = sourceReader.readLine();
							break;
						}
					}
				}
				tempwriter.write(line + "\n");
				tempwriter.flush();
			}
			tempwriter.close();
			sourceReader.close();
			tempFile.delete();
			temp.renameTo(tempFile);
			RemoveLicense.removeLicenseLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
		} catch (IOException e) {
			RemoveLicense.removeLicenseLogger.error("Error In Removing License", e);
			ExceptionLogger.loggerIOException(RemoveLicense.removeLicenseLogger, e);
			RemoveLicense.removeLicenseLogger.exit(LoggerValues.UNSUCCESSFUL_EXIT);
		}
	}
}