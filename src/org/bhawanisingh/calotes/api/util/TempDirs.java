package org.bhawanisingh.calotes.api.util;

import java.io.File;

public class TempDirs {
	public static void createTempDirs(){
		String path = System.getProperty("user.home")+System.getProperty("file.separator")+".Calotes";
		File tempdir = new File(path);
		if(!tempdir.exists()){
			tempdir.mkdir();
		}
		FileNames.TEMP_PATH = tempdir.getAbsolutePath();
	}
}