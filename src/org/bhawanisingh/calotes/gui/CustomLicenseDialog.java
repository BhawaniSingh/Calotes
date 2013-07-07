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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
		customLicenseLogger.entry();
		this.gui = gui;
		this.bufferedImage = bufferedImage;
		initialize();
		themeing();
		addComponents();
		addEvents();
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		pleaseWait = new PleaseWait();
		this.setLocationRelativeTo(this.gui);
		this.setVisible(true);
		this.setGlassPane(pleaseWait);
		customLicenseLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void initialize() {
		customLicenseLogger.entry();
		mainPanel = new JPanel(new GridBagLayout()) {

			private static final long serialVersionUID = -8427191383496124995L;

			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(bufferedImage, 0, 0, getWidth(), getHeight(), this);
				g.setColor(new Color(0, 153, 204, 30));
				g.fillRect(0, 0, getWidth(), getHeight());
			}
		};
		emptyPanel = new JPanel();
		licenseTextArea = new JTextArea(10, 30);
		licenseScrollPane = new JScrollPane(licenseTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		templateTextArea = new JTextArea(10, 30);
		templateScrollPane = new JScrollPane(templateTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		licenseButton = new JButton("License");
		licenseButton.setToolTipText("Click To Choose License File");
		templateButton = new JButton("Template");
		templateButton.setToolTipText("Click To Choose Template File");
		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		customLicenseLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void themeing() {
		customLicenseLogger.entry();
		Color color = new Color(0, 0, 0, 0);

		mainPanel.setOpaque(false);
		emptyPanel.setOpaque(false);
		licenseScrollPane.setOpaque(false);
		licenseScrollPane.getViewport().setOpaque(false);
		licenseScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 1), "License"));
		licenseScrollPane.setViewportBorder(null);
		licenseTextArea.setOpaque(false);
		licenseTextArea.setBackground(color);
		templateScrollPane.setOpaque(false);
		templateScrollPane.getViewport().setOpaque(false);
		templateScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 1), "Template"));
		templateScrollPane.setViewportBorder(null);
		templateTextArea.setOpaque(false);
		templateTextArea.setBackground(color);
		customLicenseLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void addComponents() {
		customLicenseLogger.entry();
		Layout.add(mainPanel, templateScrollPane, 0, ++yPosition, 3, 1, 50, 100, GridBagConstraints.EAST, GridBagConstraints.BOTH);
		Layout.add(mainPanel, licenseScrollPane, 3, yPosition, 3, 1, 50, 100, GridBagConstraints.EAST, GridBagConstraints.BOTH);

		Layout.add(mainPanel, templateButton, 0, ++yPosition, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE);
		Layout.add(mainPanel, licenseButton, 1, yPosition, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE);
		Layout.add(mainPanel, emptyPanel, 2, yPosition, 2, 1, 100, 0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL);
		Layout.add(mainPanel, cancelButton, 4, yPosition, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE);
		Layout.add(mainPanel, okButton, 5, yPosition, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE);

		this.add(mainPanel);
		customLicenseLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void addEvents() {
		customLicenseLogger.entry();
		licenseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				licenseButtonAction();
			}
		});

		templateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				templateButtonAction();
			}
		});

		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				okButtonAction();
			}
		});

		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cancelButtonAction();
			}
		});
		customLicenseLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	protected void licenseButtonAction() {
		customLicenseLogger.entry();
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Select License File");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setFileFilter(new LicenseFileFilter());
		if (fileChooser.showDialog(this, "Select") == JFileChooser.APPROVE_OPTION) {
			licenseTextArea.setText("");
			pleaseWait.start();
			try {
				BufferedReader reader = CustomLicense.generateLicense(fileChooser.getSelectedFile().getCanonicalPath());
				String line;
				while ((line = reader.readLine()) != null) {
					licenseTextArea.setText(licenseTextArea.getText() + line + "\n");
				}
			} catch (IOException e) {
				customLicenseLogger.error("Error In Creating Getting File Path", e);
				ExceptionLogger.loggerIOException(customLicenseLogger, e);
			}
		}
//		pleaseWait.stop();
		customLicenseLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	protected void templateButtonAction() {
		customLicenseLogger.entry();
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Select Template File");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setFileFilter(new LicenseFileFilter());
		if (fileChooser.showDialog(this, "Select") == JFileChooser.APPROVE_OPTION) {
			templateTextArea.setText("");
			pleaseWait.start();
			try {
				BufferedReader reader = CustomLicense.generateLicense(fileChooser.getSelectedFile().getCanonicalPath());
				String line;
				while ((line = reader.readLine()) != null) {
					templateTextArea.setText(templateTextArea.getText() + line + "\n");
				}
			} catch (IOException e) {
				customLicenseLogger.error("Error In Creating Getting File Path", e);
				ExceptionLogger.loggerIOException(customLicenseLogger, e);
				pleaseWait.stop();
			}
		}
		pleaseWait.stop();
		customLicenseLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	protected void okButtonAction() {
		customLicenseLogger.entry();
		if (licenseTextArea.getText().trim().equals("") || templateTextArea.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Both License And Template Are Required", "Error !!!", JOptionPane.ERROR_MESSAGE);
			customLicenseLogger.exit(LoggerValues.UNSUCCESSFUL_EXIT);
		} else {
			CustomLicense.generateLicenseFile(licenseTextArea.getText());
			gui.setText(templateTextArea.getText());
			this.dispose();
			customLicenseLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
			}
	}

	protected void cancelButtonAction() {
		customLicenseLogger.entry();
		this.dispose();
		customLicenseLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}
}