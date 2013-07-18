package org.bhawanisingh.calotes.api.util;

import java.io.File;

public class Directory {
	public static void createTempDirs() {
		String path = System.getProperty("user.home") + System.getProperty("file.separator") + ".Calotes";
		File tempdir = new File(path);
		if (!tempdir.exists()) {
			tempdir.mkdir();
		}
		FileNames.TEMP_PATH = tempdir.getAbsolutePath();
	}

	public static void deletetempFiles() {
		File template = new File(FileNames.TEMP_PATH + FileNames.SEPARATOR + FileNames.TEMP_LIC_TEMPLATE);
		File license = new File(FileNames.TEMP_PATH + FileNames.SEPARATOR + FileNames.TEMP_LIC);

		if (template.exists()) {
			template.delete();
			license.delete();
		}
	}

	public static boolean validateDirectory(String directory) {
		if (new File(directory).exists()) {
			return true;
		}
		return false;
	}
}