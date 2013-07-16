package org.bhawanisingh.calotes.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bhawanisingh.calotes.api.logging.ExceptionLogger;
import org.bhawanisingh.calotes.api.logging.LoggerValues;
import org.bhawanisingh.calotes.api.util.CustomLicense;
import org.bhawanisingh.calotes.api.util.LicenseFileFilter;

public class CustomLicenseDialog extends JDialog {

	private static final long serialVersionUID = 6403977444777963190L;

	private Logger customLicenseLogger = LogManager.getLogger(CustomLicenseDialog.class);

	private BufferedImage bufferedImage;
	private int yPosition = -1;

	private JPanel mainPanel;
	private JPanel emptyPanel;
	private JScrollPane licenseScrollPane;
	private JScrollPane templateScrollPane;
	private JTextArea licenseTextArea;
	private JTextArea templateTextArea;
	private JButton licenseButton;
	private JButton templateButton;
	private JButton okButton;
	private JButton cancelButton;

	private PleaseWait pleaseWait;
	private MainGUI gui;

	public CustomLicenseDialog(MainGUI gui, BufferedImage bufferedImage) {
		this.customLicenseLogger.entry();
		this.gui = gui;
		this.bufferedImage = bufferedImage;
		this.initialize();
		this.themeing();
		this.addComponents();
		this.addEvents();
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.pack();
		this.pleaseWait = new PleaseWait();
		this.setLocationRelativeTo(this.gui);
		this.setVisible(true);
		this.setGlassPane(this.pleaseWait);
		this.customLicenseLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void initialize() {
		this.customLicenseLogger.entry();
		this.mainPanel = new JPanel(new GridBagLayout()) {

			private static final long serialVersionUID = -8427191383496124995L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(CustomLicenseDialog.this.bufferedImage, 0, 0, this.getWidth(), this.getHeight(), this);
				g.setColor(new Color(0, 153, 204, 30));
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
			}
		};
		this.emptyPanel = new JPanel();
		this.licenseTextArea = new JTextArea(10, 30);
		this.licenseScrollPane = new JScrollPane(this.licenseTextArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.templateTextArea = new JTextArea(10, 30);
		this.templateScrollPane = new JScrollPane(this.templateTextArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.licenseButton = new JButton("License");
		this.licenseButton.setToolTipText("Click To Choose License File");
		this.templateButton = new JButton("Template");
		this.templateButton.setToolTipText("Click To Choose Template File");
		this.okButton = new JButton("OK");
		this.cancelButton = new JButton("Cancel");
		this.customLicenseLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void themeing() {
		this.customLicenseLogger.entry();
		Color color = new Color(0, 0, 0, 0);

		this.mainPanel.setOpaque(false);
		this.emptyPanel.setOpaque(false);
		this.licenseScrollPane.setOpaque(false);
		this.licenseScrollPane.getViewport().setOpaque(false);
		this.licenseScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 1), "License"));
		this.licenseScrollPane.setViewportBorder(null);
		this.licenseTextArea.setOpaque(false);
		this.licenseTextArea.setBackground(color);
		this.templateScrollPane.setOpaque(false);
		this.templateScrollPane.getViewport().setOpaque(false);
		this.templateScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 1), "Template"));
		this.templateScrollPane.setViewportBorder(null);
		this.templateTextArea.setOpaque(false);
		this.templateTextArea.setBackground(color);
		this.customLicenseLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void addComponents() {
		this.customLicenseLogger.entry();
		Layout.add(this.mainPanel, this.templateScrollPane, 0, ++this.yPosition, 3, 1, 50, 100, GridBagConstraints.EAST, GridBagConstraints.BOTH);
		Layout.add(this.mainPanel, this.licenseScrollPane, 3, this.yPosition, 3, 1, 50, 100, GridBagConstraints.EAST, GridBagConstraints.BOTH);

		Layout.add(this.mainPanel, this.templateButton, 0, ++this.yPosition, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE);
		Layout.add(this.mainPanel, this.licenseButton, 1, this.yPosition, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE);
		Layout.add(this.mainPanel, this.emptyPanel, 2, this.yPosition, 2, 1, 100, 0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL);
		Layout.add(this.mainPanel, this.cancelButton, 4, this.yPosition, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE);
		Layout.add(this.mainPanel, this.okButton, 5, this.yPosition, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE);

		this.add(this.mainPanel);
		this.customLicenseLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void addEvents() {
		this.customLicenseLogger.entry();
		this.licenseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CustomLicenseDialog.this.addAction(CustomLicenseDialog.this.licenseTextArea);
			}
		});

		this.templateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CustomLicenseDialog.this.addAction(CustomLicenseDialog.this.templateTextArea);
			}
		});

		this.okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CustomLicenseDialog.this.okButtonAction();
			}
		});

		this.cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CustomLicenseDialog.this.cancelButtonAction();
			}
		});
		this.customLicenseLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void addAction(JTextArea textArea) {
		this.customLicenseLogger.entry();
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Select Template File");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setFileFilter(new LicenseFileFilter());
		if (fileChooser.showDialog(this, "Select") == JFileChooser.APPROVE_OPTION) {
			textArea.setText("");
			try {
				new Thread(new ReaderThread(fileChooser.getSelectedFile().getCanonicalPath(), textArea)).start();
			} catch (IOException ioException) {
				CustomLicenseDialog.this.customLicenseLogger.error("Error In Creating Getting File Path", ioException);
				ExceptionLogger.loggerIOException(CustomLicenseDialog.this.customLicenseLogger, ioException);
			}
		}
		this.customLicenseLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	protected void okButtonAction() {
		this.customLicenseLogger.entry();
		if (this.licenseTextArea.getText().trim().equals("") ||
				this.templateTextArea.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Both License And Template Are Required",
					"Error !!!", JOptionPane.ERROR_MESSAGE);
			this.customLicenseLogger.exit(LoggerValues.UNSUCCESSFUL_EXIT);
		} else {
			CustomLicense.generateLicenseFile(this.licenseTextArea.getText());
			this.gui.setText(this.templateTextArea.getText());
			this.dispose();
			this.customLicenseLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
		}
	}

	protected void cancelButtonAction() {
		this.customLicenseLogger.entry();
		this.dispose();
		this.customLicenseLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	class ReaderThread implements Runnable {

		String fileName;
		JTextArea textArea;

		public ReaderThread(String fileName, JTextArea textArea) {
			this.fileName = fileName;
			this.textArea = textArea;
		}

		@Override
		public void run() {
			CustomLicenseDialog.this.pleaseWait.start();
			try {
				BufferedReader reader = CustomLicense.generateLicense(this.fileName);
				String line;
				while ((line = reader.readLine()) != null) {
					this.textArea.setText(this.textArea.getText() + line + "\n");
				}
			} catch (IOException e) {
				CustomLicenseDialog.this.customLicenseLogger.error("Error In Creating Getting File Path", e);
				ExceptionLogger.loggerIOException(CustomLicenseDialog.this.customLicenseLogger, e);
			}
			CustomLicenseDialog.this.pleaseWait.stop();
		}

	}
}