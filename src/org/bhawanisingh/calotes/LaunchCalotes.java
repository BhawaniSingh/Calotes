package org.bhawanisingh.calotes;

import javax.swing.UnsupportedLookAndFeelException;

import org.apache.logging.log4j.LogManager;
import org.bhawanisingh.calotes.api.logging.ExceptionLogger;
import org.bhawanisingh.calotes.api.util.Directory;
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
		} catch (ClassNotFoundException classNotFoundException) {
			ExceptionLogger.loggerClassNotFoundException(LogManager.getLogger(LaunchCalotes.class.getName()), classNotFoundException);
		} catch (InstantiationException instantiationException) {
			ExceptionLogger.loggerInstantiationException(LogManager.getLogger(LaunchCalotes.class.getName()), instantiationException);
		} catch (IllegalAccessException illegalAccessException) {
			ExceptionLogger.loggerIllegalAccessException(LogManager.getLogger(LaunchCalotes.class.getName()), illegalAccessException);
		} catch (UnsupportedLookAndFeelException unsupportedLookAndFeelException) {
			ExceptionLogger.loggerUnsupportedLookAndFeelException(LogManager.getLogger(LaunchCalotes.class.getName()), unsupportedLookAndFeelException);
		}
		Directory.createTempDirs();
		new MainGUI();

	}
}