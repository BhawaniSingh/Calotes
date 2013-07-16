package org.bhawanisingh.calotes;

import javax.swing.UnsupportedLookAndFeelException;

import org.bhawanisingh.calotes.api.util.TempDirs;
import org.bhawanisingh.calotes.gui.MainGUI;

public class LaunchCalotes {

	public static void main(String[] args) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					info.getClassName();
					break;
				}
			}
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (UnsupportedLookAndFeelException e) {
		}
		TempDirs.createTempDirs();
		new MainGUI();
	}
}