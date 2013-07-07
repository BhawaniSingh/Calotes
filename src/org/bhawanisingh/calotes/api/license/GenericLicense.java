package org.bhawanisingh.calotes.api.license;

import java.util.ArrayList;

public class GenericLicense {

	public static final String DO = "YES";
	public static final String DO_NOT = "NO";
	public static final String MUST = "MUST";
	public static final String NOT_APPLICABLE = "N/A";
	public static final String NOT_DEFINED = "Not Defined By License";

	public static ArrayList<GenericLicense> licenseObjects = new ArrayList<GenericLicense>() {
		{
			add(new GPL3());
			add(new Boost1());
		}
	};
	public static int numberOfLicences = 0;
	public static final int numberOfOptions = 4;

	public String LICENSE_NAME;
	public static final String[][] LICENSE_FILTERING = { { "Commercial Use", "Derived Work Can Be Commercial Or Not" }, { "Disclose Source", "Derived Work Should Disclose Its Source Or Not" }, { "Distribute", "Distibute This SOftware With Derived Work" }, { "Hold Liable", "Software Come Under Warranty. You Will be Blamed For The Damages" }, { "Inclued Copyright Notice", "Retain Copyright Notice In Derived Work" }, { "Include Original", "Include This Software In Derived Work" }, { "Include License", "Your License In Derived Work" }, { "Modify", "Allow Modification" }, { "Non-Static Linkage", "If This Is Library User Can Replace This With Other Version Of It" }, { "Private Use", "Allow Private Use Of Your Software" }, { "Rename", "Allow User To Rename Your Software And Distribute/Use It" },
			{ "State Changes", "State The Changes Done In Derived Software" }, { "Sublicense", "Re-License The Derived Software In Other License" }, { "Use Trademark", "Use Your Name/Software As Trademark By Others" }, { "Warranty", "Do You Want To Include Warranty Information" } };

	String COMMERCIAL_USE;
	String DISCLOSE_SOURCE;
	String DISTRIBUTE;
	String HOLD_LIABLE;
	String INCLUDE_COPYRIGHT_NOTICE;
	String INCLUDE_ORIGINAL;
	String INCLUDE_LICENSE;
	String MODIFY;
	String NON_STATIC_LINKAGE;
	String PRIVATE_USE;
	String RENAME;
	String STATE_CHANGES;
	String SUBLICENSE;
	String USE_TRADEMARK;
	String WARRANTY;

	String COMMERCIAL_USE_TOOLTIP;
	String DISCLOSE_SOURCE_TOOLTIP;
	String DISTRIBUTE_TOOLTIP;
	String HOLD_LIABLE_TOOLTIP;
	String INCLUDE_COPYRIGHT_NOTICE_TOOLTIP;
	String INCLUDE_ORIGINAL_TOOLTIP;
	String INCLUDE_LICENSE_TOOLTIP;
	String MODIFY_TOOLTIP;
	String NON_STATIC_LINKAGE_TOOLTIP;
	String PRIVATE_USE_TOOLTIP;
	String RENAME_TOOLTIP;
	String STATE_CHANGES_TOOLTIP;
	String SUBLICENSE_TOOLTIP;
	String USE_TRADEMARK_TOOLTIP;
	String WARRANTY_TOOLTIP;

	void addLicense(GenericLicense license) {
		licenseObjects.add(license);
	}

	void setTooltip() {
		// For COMMERCIAL_USE
		if (COMMERCIAL_USE.equals(DO)) {
			COMMERCIAL_USE_TOOLTIP = "This Software Can Be Used For Commercial Purpose";
		} else if (COMMERCIAL_USE.equals(DO_NOT)) {
			COMMERCIAL_USE_TOOLTIP = "This Software Can't Be Used For Commercial Purpose";
		} else if (COMMERCIAL_USE.equals(MUST)) {
			COMMERCIAL_USE_TOOLTIP = "This Software Must Be Used For Commercial Purpose";
		} else {
			COMMERCIAL_USE_TOOLTIP = NOT_DEFINED;
		}

		// For DISCLOSE_SOURCE
		if (DISCLOSE_SOURCE.equals(DO)) {
			DISCLOSE_SOURCE_TOOLTIP = "You Can Disclose Your Source Code";
		} else if (MODIFY.equals(DO_NOT)) {
			DISCLOSE_SOURCE_TOOLTIP = "You Can Disclose Your Source Code";
		} else if (DISCLOSE_SOURCE.equals(MUST)) {
			DISCLOSE_SOURCE_TOOLTIP = "You Must Disclose Your Source Code";
		} else {
			DISCLOSE_SOURCE_TOOLTIP = NOT_DEFINED;
		}

		// For DISTRIBUTE
		if (DISTRIBUTE.equals(DO)) {
			DISTRIBUTE_TOOLTIP = "This Software Can Be Distributed";
		} else if (DISTRIBUTE.equals(DO_NOT)) {
			DISTRIBUTE_TOOLTIP = "This Software Can't Be Distrbuted";
		} else if (DISTRIBUTE.equals(MUST)) {
			DISTRIBUTE_TOOLTIP = "This Software Must Be Distrbuted";
		} else {
			DISTRIBUTE_TOOLTIP = NOT_DEFINED;
		}

		// For HOLD_LIABLE
		if (HOLD_LIABLE.equals(DO)) {
			HOLD_LIABLE_TOOLTIP = "Software Owner Can Be Blamed For The Damage By The Software";
		} else if (HOLD_LIABLE.equals(DO_NOT)) {
			HOLD_LIABLE_TOOLTIP = "Software Owner Can't Be Blamed For The Damage By The Software";
		} else if (HOLD_LIABLE.equals(MUST)) {
			HOLD_LIABLE_TOOLTIP = "Software Comes With Warranty. Software Owner Is Responsible For The Damage";
		} else {
			HOLD_LIABLE_TOOLTIP = NOT_DEFINED;
		}

		// For INCLUDE_COPYRIGHT_NOTICE
		if (INCLUDE_COPYRIGHT_NOTICE.equals(DO)) {
			INCLUDE_COPYRIGHT_NOTICE_TOOLTIP = "Original Copyright Notice Can Be Retained";
		} else if (INCLUDE_COPYRIGHT_NOTICE.equals(DO_NOT)) {
			INCLUDE_COPYRIGHT_NOTICE_TOOLTIP = "Original Copyright Notice Can Be Removed";
		} else if (INCLUDE_COPYRIGHT_NOTICE.equals(MUST)) {
			INCLUDE_COPYRIGHT_NOTICE_TOOLTIP = "Original Copyright Notice Must Be Retained";
		} else {
			INCLUDE_COPYRIGHT_NOTICE_TOOLTIP = NOT_DEFINED;
		}

		// Work In Progress

		// For INCLUDE_ORIGINAL
		if (INCLUDE_ORIGINAL.equals(DO)) {
			INCLUDE_ORIGINAL_TOOLTIP = "Original Software Can Be Distribued With Derived Work/Software";
		} else if (INCLUDE_ORIGINAL.equals(DO_NOT)) {
			INCLUDE_ORIGINAL_TOOLTIP = "Original Software Can't Be Distribued With Derived Work/Software";
		} else if (INCLUDE_ORIGINAL.equals(MUST)) {
			INCLUDE_ORIGINAL_TOOLTIP = "Original Software Must Be Distribued With Derived Work/Software";
		} else {
			INCLUDE_ORIGINAL_TOOLTIP = NOT_DEFINED;
		}

		// For INCLUDE_LICENSE
		if (INCLUDE_LICENSE.equals(DO)) {
			INCLUDE_LICENSE_TOOLTIP = "Original Copyright Notice Can Be Retained";
		} else if (INCLUDE_LICENSE.equals(DO_NOT)) {
			INCLUDE_LICENSE_TOOLTIP = "Original Copyright Notice Can Be Removed";
		} else if (INCLUDE_LICENSE.equals(MUST)) {
			INCLUDE_LICENSE_TOOLTIP = "Original Copyright Notice Must Be Retained";
		} else {
			INCLUDE_LICENSE_TOOLTIP = NOT_DEFINED;
		}

		// For MODIFY
		if (MODIFY.equals(DO)) {
			MODIFY_TOOLTIP = "Original Copyright Notice Can Be Retained";
		} else if (MODIFY.equals(DO_NOT)) {
			MODIFY_TOOLTIP = "Original Copyright Notice Can Be Removed";
		} else if (MODIFY.equals(MUST)) {
			MODIFY_TOOLTIP = "Original Copyright Notice Must Be Retained";
		} else {
			MODIFY_TOOLTIP = NOT_DEFINED;
		}

		// For NON_STATIC_LINKAGE
		if (NON_STATIC_LINKAGE.equals(DO)) {
			NON_STATIC_LINKAGE_TOOLTIP = "Original Copyright Notice Can Be Retained";
		} else if (NON_STATIC_LINKAGE.equals(DO_NOT)) {
			NON_STATIC_LINKAGE_TOOLTIP = "Original Copyright Notice Can Be Removed";
		} else if (NON_STATIC_LINKAGE.equals(MUST)) {
			NON_STATIC_LINKAGE_TOOLTIP = "Original Copyright Notice Must Be Retained";
		} else {
			NON_STATIC_LINKAGE_TOOLTIP = NOT_DEFINED;
		}

		// For PRIVATE_USE
		if (PRIVATE_USE.equals(DO)) {
			PRIVATE_USE_TOOLTIP = "Original Copyright Notice Can Be Retained";
		} else if (PRIVATE_USE.equals(DO_NOT)) {
			PRIVATE_USE_TOOLTIP = "Original Copyright Notice Can Be Removed";
		} else if (PRIVATE_USE.equals(MUST)) {
			PRIVATE_USE_TOOLTIP = "Original Copyright Notice Must Be Retained";
		} else {
			PRIVATE_USE_TOOLTIP = NOT_DEFINED;
		}

		// For RENAME
		if (RENAME.equals(DO)) {
			RENAME_TOOLTIP = "Original Copyright Notice Can Be Retained";
		} else if (RENAME.equals(DO_NOT)) {
			RENAME_TOOLTIP = "Original Copyright Notice Can Be Removed";
		} else if (RENAME.equals(MUST)) {
			RENAME_TOOLTIP = "Original Copyright Notice Must Be Retained";
		} else {
			RENAME_TOOLTIP = NOT_DEFINED;
		}

		// For STATE_CHANGES
		if (STATE_CHANGES.equals(DO)) {
			STATE_CHANGES_TOOLTIP = "Original Copyright Notice Can Be Retained";
		} else if (STATE_CHANGES.equals(DO_NOT)) {
			STATE_CHANGES_TOOLTIP = "Original Copyright Notice Can Be Removed";
		} else if (STATE_CHANGES.equals(MUST)) {
			STATE_CHANGES_TOOLTIP = "Original Copyright Notice Must Be Retained";
		} else {
			STATE_CHANGES_TOOLTIP = NOT_DEFINED;
		}

		// For SUBLICENSE
		if (SUBLICENSE.equals(DO)) {
			SUBLICENSE_TOOLTIP = "Original Copyright Notice Can Be Retained";
		} else if (SUBLICENSE.equals(DO_NOT)) {
			SUBLICENSE_TOOLTIP = "Original Copyright Notice Can Be Removed";
		} else if (SUBLICENSE.equals(MUST)) {
			SUBLICENSE_TOOLTIP = "Original Copyright Notice Must Be Retained";
		} else {
			SUBLICENSE_TOOLTIP = NOT_DEFINED;
		}

		// For USE_TRADEMARK
		if (USE_TRADEMARK.equals(DO)) {
			USE_TRADEMARK_TOOLTIP = "Original Copyright Notice Can Be Retained";
		} else if (USE_TRADEMARK.equals(DO_NOT)) {
			USE_TRADEMARK_TOOLTIP = "Original Copyright Notice Can Be Removed";
		} else if (USE_TRADEMARK.equals(MUST)) {
			USE_TRADEMARK_TOOLTIP = "Original Copyright Notice Must Be Retained";
		} else {
			USE_TRADEMARK_TOOLTIP = NOT_DEFINED;
		}

		// For WARRANTY
		if (WARRANTY.equals(DO)) {
			WARRANTY_TOOLTIP = "Original Copyright Notice Can Be Retained";
		} else if (WARRANTY.equals(DO_NOT)) {
			WARRANTY_TOOLTIP = "Original Copyright Notice Can Be Removed";
		} else if (WARRANTY.equals(MUST)) {
			WARRANTY_TOOLTIP = "Original Copyright Notice Must Be Retained";
		} else {
			WARRANTY_TOOLTIP = NOT_DEFINED;
		}
	}

	/**
	 * @return the DO
	 */
	public static String getDo() {
		return DO;
	}

	/**
	 * @return the DO_NOT
	 */
	public static String getDoNot() {
		return DO_NOT;
	}

	/**
	 * @return the MUST
	 */
	public static String getMust() {
		return MUST;
	}

	/**
	 * @return the NOT_APPLICABLE
	 */
	public static String getNotApplicable() {
		return NOT_APPLICABLE;
	}

	/**
	 * @return the NOT_DEFINED
	 */
	public static String getNotDefined() {
		return NOT_DEFINED;
	}

	/**
	 * @return the COMMERCIAL_USE
	 */
	public String getCOMMERCIAL_USE() {
		return COMMERCIAL_USE;
	}

	/**
	 * @return the DISCLOSE_SOURCE
	 */
	public String getDISCLOSE_SOURCE() {
		return DISCLOSE_SOURCE;
	}

	/**
	 * @return the DISTRIBUTE
	 */
	public String getDISTRIBUTE() {
		return DISTRIBUTE;
	}

	/**
	 * @return the HOLD_LIABLE
	 */
	public String getHOLD_LIABLE() {
		return HOLD_LIABLE;
	}

	/**
	 * @return the INCLUDE_COPYRIGHT_NOTICE
	 */
	public String getINCLUDE_COPYRIGHT_NOTICE() {
		return INCLUDE_COPYRIGHT_NOTICE;
	}

	/**
	 * @return the INCLUDE_ORIGINAL
	 */
	public String getINCLUDE_ORIGINAL() {
		return INCLUDE_ORIGINAL;
	}

	/**
	 * @return the INCLUDE_LICENSE
	 */
	public String getINCLUDE_LICENSE() {
		return INCLUDE_LICENSE;
	}

	/**
	 * @return the MODIFY
	 */
	public String getMODIFY() {
		return MODIFY;
	}

	/**
	 * @return the NON_STATIC_LINKAGE
	 */
	public String getNON_STATIC_LINKAGE() {
		return NON_STATIC_LINKAGE;
	}

	/**
	 * @return the PRIVATE_USE
	 */
	public String getPRIVATE_USE() {
		return PRIVATE_USE;
	}

	/**
	 * @return the RENAME
	 */
	public String getRENAME() {
		return RENAME;
	}

	/**
	 * @return the STATE_CHANGES
	 */
	public String getSTATE_CHANGES() {
		return STATE_CHANGES;
	}

	/**
	 * @return the SUBLICENSE
	 */
	public String getSUBLICENSE() {
		return SUBLICENSE;
	}

	/**
	 * @return the USE_TRADEMARK
	 */
	public String getUSE_TRADEMARK() {
		return USE_TRADEMARK;
	}

	/**
	 * @return the WARRANTY
	 */
	public String getWARRANTY() {
		return WARRANTY;
	}

	/**
	 * @return the COMMERCIAL_USE_TOOLTIP
	 */
	public String getCOMMERCIAL_USE_TOOLTIP() {
		return COMMERCIAL_USE_TOOLTIP;
	}

	/**
	 * @return the DISCLOSE_SOURCE_TOOLTIP
	 */
	public String getDISCLOSE_SOURCE_TOOLTIP() {
		return DISCLOSE_SOURCE_TOOLTIP;
	}

	/**
	 * @return the DISTRIBUTE_TOOLTIP
	 */
	public String getDISTRIBUTE_TOOLTIP() {
		return DISTRIBUTE_TOOLTIP;
	}

	/**
	 * @return the HOLD_LIABLE_TOOLTIP
	 */
	public String getHOLD_LIABLE_TOOLTIP() {
		return HOLD_LIABLE_TOOLTIP;
	}

	/**
	 * @return the INCLUDE_COPYRIGHT_NOTICE_TOOLTIP
	 */
	public String getINCLUDE_COPYRIGHT_NOTICE_TOOLTIP() {
		return INCLUDE_COPYRIGHT_NOTICE_TOOLTIP;
	}

	/**
	 * @return the INCLUDE_ORIGINAL_TOOLTIP
	 */
	public String getINCLUDE_ORIGINAL_TOOLTIP() {
		return INCLUDE_ORIGINAL_TOOLTIP;
	}

	/**
	 * @return the INCLUDE_LICENSE_TOOLTIP
	 */
	public String getINCLUDE_LICENSE_TOOLTIP() {
		return INCLUDE_LICENSE_TOOLTIP;
	}

	/**
	 * @return the MODIFY_TOOLTIP
	 */
	public String getMODIFY_TOOLTIP() {
		return MODIFY_TOOLTIP;
	}

	/**
	 * @return the NON_STATIC_LINKAGE_TOOLTIP
	 */
	public String getNON_STATIC_LINKAGE_TOOLTIP() {
		return NON_STATIC_LINKAGE_TOOLTIP;
	}

	/**
	 * @return the PRIVATE_USE_TOOLTIP
	 */
	public String getPRIVATE_USE_TOOLTIP() {
		return PRIVATE_USE_TOOLTIP;
	}

	/**
	 * @return the RENAME_TOOLTIP
	 */
	public String getRENAME_TOOLTIP() {
		return RENAME_TOOLTIP;
	}

	/**
	 * @return the STATE_CHANGES_TOOLTIP
	 */
	public String getSTATE_CHANGES_TOOLTIP() {
		return STATE_CHANGES_TOOLTIP;
	}

	/**
	 * @return the SUBLICENSE_TOOLTIP
	 */
	public String getSUBLICENSE_TOOLTIP() {
		return SUBLICENSE_TOOLTIP;
	}

	/**
	 * @return the USE_TRADEMARK_TOOLTIP
	 */
	public String getUSE_TRADEMARK_TOOLTIP() {
		return USE_TRADEMARK_TOOLTIP;
	}

	/**
	 * @return the WARRANTY_TOOLTIP
	 */
	public String getWARRANTY_TOOLTIP() {
		return WARRANTY_TOOLTIP;
	}
}