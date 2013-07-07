package org.bhawanisingh.calotes.api.util;

public class FileNames {

	public static final String JAVA_COMMAND = "sun.java.command";
	
	public static final String PROGRAM_NAME = "Calotes";
	public static final String PROGRAM_VERSION = "0.1 beta15";
	public static final String PROGRAM_LICENSE_NAME = "GPL3";
	public static final String AUTHOR_NAME = "Bhawani Singh";
	public static final String COPYRIGHT_YEAR = "2013";
	public static final String ABOUT_PROGRAM = "Calotes : Tool To Add, Remove And Replace License From Source Code";
	public static final String ORGANIZATION = "";
	
	public static final String[] DEVELOPERS = {"Bhawani Singh"};
	public static final String[] DEVELOPERS_EMAIL = {"bhawanisingh2k13@gmail.com"};
	public static final String[] DEVELOPERS_WEBSITE = {"www.bhawanisingh.org"};
	public static final String[] TESTERS = {""};
	public static final String[] TESTERS_EMAIL = {""};
	public static final String[] TESTERS_WEBSITE = {""};
	public static final String[] CONTRIBUTERS = {""};
	public static final String[] CONTRIBUTERS_EMAIL = {""};
	public static final String[] CONTRIBUTERS_WEBSITE = {""};
	
	
	public static final String[] AVAILABLE_FILETYPES = {"java", "cs", "c", "i", "ii",
														"m", "mi", "mii", "M", "mm",
														"h", "cc", "cp", "cxx", "cpp",
														"CPP", "c++", "C", "hh", "H",
														"hp", "hxx", "hpp" ,"HPP", "h++", "tcc",
														"py", "js"};
	public static final String[] AVAILABLE_LICENESES = { "AGPL3", "Apache2", "Boost1", "BSD2", 
														"BSD3", "BSD4", "CDDl1", "EPL1", 
														"GPL3", "LGPL3", "MIT", "MPL2" };
	public static final String[] AVAILABLE_LICENESES_TOOLTIP = { "GNU Affero General Public License - Version 3.0", 
																"Apache License - Version 2.0", 
																"Boost Software License - Version 1.0", 
																"Simplified BSD License",
																"New BSD License", 
																"BSD License", 
																"Common Development And Distribution License Version 1.0", 
																"Eclipse Public License - Version 1.0", 
																"GNU General Public License - Version 3.0", 
																"GNU Lesser General Public License - Version 3.0", 
																"The MIT License", 
																"Mozilla Public License Version 2.0" };

	public static final String SEPARATOR = System.getProperty("file.separator");
	public static final String TEMP_LIC = "templicense.lic";
	public static final String TEMP_LIC_TEMPLATE = "templicensetemplate.lic";

	public static final String LICENSE_NAME = "License.txt";
	
	public static String TEMP_PATH;

	public static final String LIC_PATH = "/original";
	public static final String LIC_TEMPLATE_PATH = "/template";

	public static final String APACHE_LIC = LIC_PATH + "/Apache.lic";
	public static final String APACHE_LIC_TEMPLATE = LIC_TEMPLATE_PATH + "/Apache.lic";

	public static final String AGPL3_LIC = LIC_PATH + "/AGPL3.lic";
	public static final String AGPL3_LIC_TEMPLATE = LIC_TEMPLATE_PATH + "/AGPL3.lic";

	public static final String APACHE2_LIC = LIC_PATH + "/Apache2.lic";
	public static final String APACHE2_LIC_TEMPLATE = LIC_TEMPLATE_PATH + "/Apache2.lic";

	public static final String BOOST1_LIC = LIC_PATH + "/Boost1.lic";
	public static final String BOOST1_LIC_TEMPLATE = LIC_TEMPLATE_PATH + "/Boost1.lic";

	public static final String BSD2_LIC = LIC_PATH + "/BSD2.lic";
	public static final String BSD2_LIC_TEMPLATE = LIC_TEMPLATE_PATH + "/BSD2.lic";

	public static final String BSD3_LIC = LIC_PATH + "/BSD3.lic";
	public static final String BSD3_LIC_TEMPLATE = LIC_TEMPLATE_PATH + "/BSD3.lic";

	public static final String BSD4_LIC = LIC_PATH + "/BSD4.lic";
	public static final String BSD4_LIC_TEMPLATE = LIC_TEMPLATE_PATH + "/BSD4.lic";

	public static final String CDDL1_LIC = LIC_PATH + "/CDDL1.lic";
	public static final String CDDL1_LIC_TEMPLATE = LIC_TEMPLATE_PATH + "/CDDL1.lic";

	public static final String EPL1_LIC = LIC_PATH + "/EPL1.lic";
	public static final String EPL1_LIC_TEMPLATE = LIC_TEMPLATE_PATH + "/EPL1.lic";

	public static final String GPL3_LIC = LIC_PATH + "/GPL3.lic";
	public static final String GPL3_LIC_TEMPLATE = LIC_TEMPLATE_PATH + "/GPL3.lic";

	public static final String LGPL3_LIC = LIC_PATH + "/LGPL3.lic";
	public static final String LGPL3_LIC_TEMPLATE = LIC_TEMPLATE_PATH + "/LGPL3.lic";

	public static final String MIT_LIC = LIC_PATH + "/MIT.lic";
	public static final String MIT_LIC_TEMPLATE = LIC_TEMPLATE_PATH + "/MIT.lic";

	public static final String MPL2_LIC = LIC_PATH + "/MPL2.lic";
	public static final String MPL2_LIC_TEMPLATE = LIC_TEMPLATE_PATH + "/MPL2.lic";
}