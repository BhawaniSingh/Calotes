package org.bhawanisingh.calotes.api.util;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class LicenseFileFilter extends FileFilter {

	public boolean accept(File f) {
		if (f.isDirectory())
			return true;
		String s = f.getName();
		int i = s.lastIndexOf('.');

		if (i > 0 && i < s.length() - 1)
			if (s.substring(i + 1).toLowerCase().equals("lic") || s.substring(i + 1).toLowerCase().equals("txt"))
				return true;

		return false;
	}

	public String getDescription() {
		return "*.lic, *.txt - License Files";
	}

}
