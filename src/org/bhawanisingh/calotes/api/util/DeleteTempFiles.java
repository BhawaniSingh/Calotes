package org.bhawanisingh.calotes.api.util;

import java.io.File;

public class DeleteTempFiles {
	public static void deletetempFiles(){
		File template = new File(FileNames.TEMP_PATH + FileNames.SEPARATOR + FileNames.TEMP_LIC_TEMPLATE);
		File license = new File(FileNames.TEMP_PATH + FileNames.SEPARATOR + FileNames.TEMP_LIC);
		
		if(template.exists()){
			template.delete();
			license.delete();
		}
	}
}