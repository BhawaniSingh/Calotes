package org.bhawanisingh.calotes.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bhawanisingh.calotes.api.util.FileNames;
import org.bhawanisingh.calotes.api.util.GenerateTempLicense;
import org.bhawanisingh.calotes.api.logging.ExceptionLogger;
import org.bhawanisingh.calotes.api.logging.LoggerValues;

public class LicenseChooserDialog extends JDialog {

	private Logger licenseChooserDialogLogger = LogManager.getLogger(LicenseChooserDialog.class);

	private static final long serialVersionUID = 360995776438444664L;

	boolean useDefaultLicenseTemplate;
	boolean attachLicenseCopy;

	private JPanel emptyPanel;

	private MainGUI gui;

	private int yPosition = -1;

	private JPanel mainPanel;

	private JPanel buttonPanel;
	private JRadioButton licenseButtons[];
	private ButtonGroup licenseButtonGroup;

	private JPanel detailPanel;

	private JLabel programLabel;
	private JTextField programField;

	private JLabel authorLabel;
	private JTextField authorField;

	private JLabel yearLabel;
	private JTextField yearField;

	private JLabel organisationLabel;
	private JTextField organisationField;

	private JPanel optionPanel;
	private JButton okButton;
	private JButton cancelButton;

	private BufferedImage bufferedImage;

	public LicenseChooserDialog(MainGUI gui, BufferedImage bufferedImage) {
		super(gui, "Select License");
		licenseChooserDialogLogger.entry();		
		this.gui = gui;
		this.bufferedImage = bufferedImage;
		initialize();
		themeing();
		addComponents();
		addEvents();

		setIconImage(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		pack();
		setLocationRelativeTo(this.gui);
		setVisible(true);
		licenseChooserDialogLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void initialize() {
		licenseChooserDialogLogger.entry();
		mainPanel = new JPanel(new BorderLayout(5, 5)) {
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(bufferedImage, 0, 0, getWidth(), getHeight(), this);
				g.setColor(new Color(0, 153, 204, 30));
				g.fillRect(0, 0, getWidth(), getHeight());
			}
		};

		buttonPanel = new JPanel(new GridLayout(FileNames.AVAILABLE_LICENESES.length / 3, 3, 5, 5));

		licenseButtonGroup = new ButtonGroup();

		licenseButtons = new JRadioButton[FileNames.AVAILABLE_LICENESES.length];

		for (int i = 0; i < licenseButtons.length; ++i) {
			licenseButtons[i] = new JRadioButton(FileNames.AVAILABLE_LICENESES[i]);
			licenseButtons[i].setToolTipText(FileNames.AVAILABLE_LICENESES_TOOLTIP[i]);
			licenseButtonGroup.add(licenseButtons[i]);
		}

		detailPanel = new JPanel(new GridBagLayout());

		programLabel = new JLabel("Software :");
		programLabel.setToolTipText("Write Something About Your Program");
		programField = new JTextField();
		programField.setToolTipText("<HTML>Write Something About Your Program.<hr />In This Format:<br /><b>Program Name</b>: <i>What Will It Do<i><HTML>");

		authorLabel = new JLabel("Author :");
		authorField = new JTextField();
		authorField.setToolTipText("Write Author's Name Here");

		yearLabel = new JLabel("Year :");
		yearField = new JTextField();
		yearField.setToolTipText("Year Of Adding License To File");

		organisationLabel = new JLabel("Organization :");
		organisationField = new JTextField();
		organisationField.setToolTipText("Write Organization's Name Here");

		optionPanel = new JPanel(new GridBagLayout());
		emptyPanel = new JPanel();
		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		licenseChooserDialogLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void themeing() {

		licenseChooserDialogLogger.entry();
		Color color = new Color(0, 0, 0, 10);

		mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
		buttonPanel.setOpaque(false);
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		optionPanel.setOpaque(false);
		detailPanel.setOpaque(false);
		detailPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		emptyPanel.setOpaque(false);
		programField.setForeground(Color.BLACK);
		programField.setBackground(color);
		programField.setOpaque(false);
		authorField.setForeground(Color.BLACK);
		authorField.setBackground(color);
		authorField.setOpaque(false);
		yearField.setForeground(Color.BLACK);
		yearField.setBackground(color);
		yearField.setOpaque(false);
		organisationField.setForeground(Color.BLACK);
		organisationField.setBackground(color);
		organisationField.setOpaque(false);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setAutoRequestFocus(true);
		licenseChooserDialogLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void addComponents() {
		licenseChooserDialogLogger.entry();
		for (int i = 0; i < licenseButtons.length; ++i) {
			buttonPanel.add(licenseButtons[i]);
		}
		mainPanel.add(buttonPanel, BorderLayout.NORTH);

		Layout.add(detailPanel, programLabel, 0, ++yPosition, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE);
		Layout.add(detailPanel, programField, 1, yPosition, 2, 1, 100, 0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL);

		Layout.add(detailPanel, authorLabel, 0, ++yPosition, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE);
		Layout.add(detailPanel, authorField, 1, yPosition, 2, 1, 100, 0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL);

		Layout.add(detailPanel, yearLabel, 0, ++yPosition, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE);
		Layout.add(detailPanel, yearField, 1, yPosition, 2, 1, 100, 0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL);

		Layout.add(detailPanel, organisationLabel, 0, ++yPosition, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE);
		Layout.add(detailPanel, organisationField, 1, yPosition, 2, 1, 100, 0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL);

		Layout.add(optionPanel, emptyPanel, 0, ++yPosition, 1, 1, 100, 0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL);
		Layout.add(optionPanel, cancelButton, 1, yPosition, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		Layout.add(optionPanel, okButton, 2, yPosition, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);

		mainPanel.add(detailPanel, BorderLayout.CENTER);
		mainPanel.add(optionPanel, BorderLayout.SOUTH);
		this.add(mainPanel);
		licenseChooserDialogLogger.exit(LoggerValues.SUCCESSFUL_EXIT);

	}

	private void addEvents() {

		licenseChooserDialogLogger.entry();
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				windowClosingEvent();
			}
		});
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				windowClosingEvent();
			}
		});

		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				okButtonAction();
			}
		});
		licenseChooserDialogLogger.exit(LoggerValues.SUCCESSFUL_EXIT);;
	}

	private void windowClosingEvent() {
		licenseChooserDialogLogger.entry();
		int choice = JOptionPane.showConfirmDialog(this, "You Didn't Select A License \n Are You Sure To Exit", "Exiting !!!", JOptionPane.YES_NO_OPTION);
		if (choice == JOptionPane.YES_OPTION) {
			this.dispose();
			licenseChooserDialogLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
		}
		else{
			licenseChooserDialogLogger.exit("Clicked On \"NO\" Option");
		}
	}

	private void okButtonAction() {
		licenseChooserDialogLogger.entry();
		boolean selection = false;
		String licenseName = "";
		for (int i = 0; i < licenseButtons.length; ++i) {
			if (licenseButtons[i].isSelected()) {
				selection = true;
				licenseName = licenseButtons[i].getText();
				break;
			}
		}
		if (!selection) {
			JOptionPane.showMessageDialog(this, "No License Is Selected", "Error !!!", JOptionPane.ERROR_MESSAGE);
		} else if (programField.getText().trim().equals("") || authorField.getText().trim().equals("") || yearField.getText().trim().equals("") || organisationField.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Program,\nAuthor,\nYear &\nOrganization\nAre Required", "Error !!!", JOptionPane.ERROR_MESSAGE);
		} else {
			BufferedReader reader = GenerateTempLicense.tempLicenseTepmlateStream(licenseName, programField.getText(), authorField.getText(), yearField.getText(), organisationField.getText());
			if (reader == null) {
				licenseChooserDialogLogger.error("Unable To Create License Template Stream");
				gui.setText("Unable To Create License Template Stream.\nError Logged.Please Mail The Log To Developer");
			} else {
				String line;
				gui.setText("");
				try {
					while ((line = reader.readLine()) != null) {
						gui.setText(gui.getText() + line + "\n");
					}
					reader.close();
				} catch (IOException e) {
					licenseChooserDialogLogger.error("Error In License Template Stream", e);
					ExceptionLogger.loggerIOException(licenseChooserDialogLogger, e);
				}
				GenerateTempLicense.tempLicense(licenseName);
			}
			this.dispose();
			licenseChooserDialogLogger.exit();
		}

	}
}