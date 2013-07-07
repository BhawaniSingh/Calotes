package org.bhawanisingh.calotes.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bhawanisingh.calotes.api.util.DeleteTempFiles;
import org.bhawanisingh.calotes.api.util.FileNames;
import org.bhawanisingh.calotes.api.util.GenerateTempLicense;
import org.bhawanisingh.calotes.api.addlicense.AddLicense;
import org.bhawanisingh.calotes.api.logging.ExceptionLogger;
import org.bhawanisingh.calotes.api.logging.LoggerValues;
import org.bhawanisingh.calotes.api.removelicense.RemoveLicense;

public class MainGUI extends JFrame {

	private Logger mainGUILogger = LogManager.getLogger(MainGUI.class.getName());

	int yPosition = -1;

	String licenseName = "";

	private static final long serialVersionUID = 1L;

	private BufferedImage bufferedImage;

	private JPanel mainPanel;

	private JMenuBar mainMenuBar;
	private JSeparator separator;
	private JMenu fileMenu;
	private JMenuItem selectLicenseItem;
	private JMenuItem customLicenseItem;
	private JMenuItem restartItem;
	private JMenuItem exitItem;

	private JMenu helpMenu;
	private JMenuItem aboutItem;

	private JLabel locationLabel;
	private JTextField srcPathField;
	private JButton browseButton;

	private JLabel fileTypeLabel;
	private JTextField fileTypeField;

	private JScrollPane licenseScrollPane;
	private JTextArea licenseArea;

	private JCheckBox recursiveBox;
	private JCheckBox includeLicenseBox;
	private JPanel emptyPanel;
	private JButton addLicenseButton;
	private JButton replaceLicenseButton;
	private JButton removeLicenseButton;

	private JLabel bottomLabel;

	public MainGUI() {
		mainGUILogger.entry();
		initialize();
		themeing();
		addComponents();
		addEvents();
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.pack();
		this.setTitle(FileNames.PROGRAM_NAME + " : " + FileNames.PROGRAM_VERSION);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
//		new LicenseSelector(this, bufferedImage);
		mainGUILogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void initialize() {
		mainGUILogger.entry();
		try {
			bufferedImage = ImageIO.read(MainGUI.class.getResource("/background.jpg"));
		} catch (IOException e) {
			mainGUILogger.error("Error In Loading \"background.jpg\"", e);
			ExceptionLogger.loggerIOException(mainGUILogger, e);
		}
		mainPanel = new JPanel(new GridBagLayout()) {
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(bufferedImage, 0, 0, getWidth(), getHeight(), this);
				g.setColor(new Color(0, 153, 204, 30));
				g.fillRect(0, 0, getWidth(), getHeight());
			}
		};

		mainMenuBar = new JMenuBar();
		separator = new JSeparator();
		fileMenu = new JMenu("File");
		selectLicenseItem = new JMenuItem("Select License", KeyEvent.VK_S);
		selectLicenseItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
		customLicenseItem = new JMenuItem("Custom License", KeyEvent.VK_C);
		customLicenseItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
		restartItem = new JMenuItem("Restart", KeyEvent.VK_R);
		restartItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK));
		exitItem = new JMenuItem("Exit", KeyEvent.VK_E);
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
		helpMenu = new JMenu("Help");
		aboutItem = new JMenuItem("About", KeyEvent.VK_A);
		aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));

		locationLabel = new JLabel("Source Folder :");
		srcPathField = new JTextField("/home/bhawani/Desktop/print/testfolder");
		srcPathField.setOpaque(false);
		browseButton = new JButton("Browse");

		fileTypeLabel = new JLabel("File Types :");
		fileTypeField = new JTextField("java");

		licenseArea = new JTextArea(10, 80);
		licenseScrollPane = new JScrollPane(licenseArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		recursiveBox = new JCheckBox("Recursive");
		recursiveBox.setToolTipText("If Selected, Program Will Also Scan Underlying Directories");
		recursiveBox.setSelected(true);
		includeLicenseBox = new JCheckBox("Include License");
		includeLicenseBox.setToolTipText("Select This If You Want To Add \"Licesnse.txt\" To Source Folder");
		includeLicenseBox.setSelected(true);
		emptyPanel = new JPanel();

		addLicenseButton = new JButton("Add");
		replaceLicenseButton = new JButton("Replace");
		removeLicenseButton = new JButton("Remove");

		bottomLabel = new JLabel("I Am Ready To Rock");
		mainGUILogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void addComponents() {

		mainGUILogger.entry();
		fileMenu.add(selectLicenseItem);
		fileMenu.add(customLicenseItem);
		fileMenu.add(separator);
		fileMenu.add(restartItem);
		fileMenu.add(exitItem);
		helpMenu.add(aboutItem);
		mainMenuBar.add(fileMenu);
		mainMenuBar.add(helpMenu);

		Layout.add(mainPanel, locationLabel, 0, ++yPosition, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE);
		Layout.add(mainPanel, srcPathField, 1, yPosition, 5, 1, 100, 0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL);
		Layout.add(mainPanel, browseButton, 6, yPosition, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE);

		Layout.add(mainPanel, fileTypeLabel, 0, ++yPosition, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE);
		Layout.add(mainPanel, fileTypeField, 1, yPosition, 6, 1, 100, 0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL);

		Layout.add(mainPanel, licenseScrollPane, 0, ++yPosition, 7, 1, 100, 100, GridBagConstraints.EAST, GridBagConstraints.BOTH);

		Layout.add(mainPanel, recursiveBox, 0, ++yPosition, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE);
		Layout.add(mainPanel, includeLicenseBox, 1, yPosition, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE);
		Layout.add(mainPanel, emptyPanel, 2, yPosition, 2, 1, 100, 0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL);
		Layout.add(mainPanel, addLicenseButton, 4, yPosition, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE);
		Layout.add(mainPanel, replaceLicenseButton, 5, yPosition, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE);
		Layout.add(mainPanel, removeLicenseButton, 6, yPosition, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE);

		Layout.add(mainPanel, bottomLabel, 0, ++yPosition, 7, 1, 100, 0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(0, 2, 0, 0));

		this.add(mainPanel);
		this.setJMenuBar(mainMenuBar);
		mainGUILogger.exit(LoggerValues.SUCCESSFUL_EXIT);

	}

	private void themeing() {

		mainGUILogger.entry();
		Border border = BorderFactory.createEtchedBorder(EtchedBorder.RAISED, new Color(0, 0, 0, 255), new Color(100, 100, 100, 0));
		Color color = new Color(0, 0, 0, 0);

		mainPanel.setOpaque(false);
		mainPanel.setBackground(new Color(51, 181, 229));

		srcPathField.setOpaque(false);
		srcPathField.setForeground(Color.BLACK);
		srcPathField.setBackground(color);

		fileTypeField.setOpaque(false);
		fileTypeField.setForeground(Color.BLACK);
		fileTypeField.setBackground(color);

		licenseArea.setOpaque(false);
		licenseArea.setForeground(Color.BLACK);
		licenseArea.setBackground(color);

		licenseScrollPane.getViewport().setBorder(null);
		licenseScrollPane.setOpaque(false);
		licenseScrollPane.setBackground(color);
		licenseScrollPane.getViewport().setOpaque(false);
		licenseScrollPane.setViewportBorder(fileTypeField.getBorder());

		emptyPanel.setOpaque(false);

		bottomLabel.setBorder(border);
		mainGUILogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void addEvents() {

		mainGUILogger.entry();
		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				windowClosingEvent();
			}
		});

		selectLicenseItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				javax.swing.SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						selectLicenseItemAction();
					}
				});

			}
		});

		customLicenseItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				customLicenseItemAction();
			}
		});
		
		restartItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				restartButtonAction();
			}
		});

		exitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				windowClosingEvent();
			}
		});

		aboutItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				aboutItemAction();
			}
		});

		browseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					browseButtonAction();
				} catch (IOException e1) {
					mainGUILogger.error("Error In Getting Path", e1);
					ExceptionLogger.loggerIOException(mainGUILogger, e1);
				}

			}
		});

		addLicenseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addLicenseButtonAction();
			}
		});

		removeLicenseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				removeLicenseButtonAction();
			}
		});
		mainGUILogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void windowClosingEvent() {
		mainGUILogger.entry();
		int choice = JOptionPane.showConfirmDialog(this, "Calotes Will Be Closed !!!\n Are You Sure ?", "Exiting !!!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (choice == JOptionPane.YES_OPTION) {
			DeleteTempFiles.deletetempFiles();
			mainGUILogger.exit(LoggerValues.SUCCESSFUL_EXIT);
			System.exit(0);
		}
		mainGUILogger.exit("Program Not Closed");
	}

	private void selectLicenseItemAction() {
		mainGUILogger.entry();
		new LicenseChooserDialog(this, bufferedImage);
		mainGUILogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}
	
	private void customLicenseItemAction(){
		mainGUILogger.entry();
		new CustomLicenseDialog(this, bufferedImage);
		mainGUILogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void aboutItemAction() {
		mainGUILogger.entry();
		new AboutDialog(this, bufferedImage, fileTypeField.getBorder());
		mainGUILogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void restartButtonAction() {
		mainGUILogger.entry();
		DeleteTempFiles.deletetempFiles();
		try {
			String java = System.getProperty("java.home") + FileNames.SEPARATOR + "bin" + FileNames.SEPARATOR + "java";
			List<String> args = ManagementFactory.getRuntimeMXBean().getInputArguments();
			StringBuffer jvmArgs = new StringBuffer();
			for (String arg : args) {
				if (!arg.contains("-agentlib")) {
					jvmArgs.append(arg);
					jvmArgs.append(" ");
				}
			}
			final StringBuffer cmd = new StringBuffer(java + " " + jvmArgs);
			String[] programPath = System.getProperty(FileNames.JAVA_COMMAND).split(" ");
			if (programPath[0].endsWith(".jar")) {
				cmd.append("-jar " + new File(programPath[0]).getPath());
			} else {
				cmd.append("-cp " + System.getProperty("java.class.path") + " " + programPath[0]);
				System.out.println(cmd);
			}
			for (int i = 1; i < programPath.length; i++) {
				cmd.append(" ");
				cmd.append(programPath[i]);
			}
			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					try {
						Runtime.getRuntime().exec(cmd.toString());
					} catch (IOException e) {
						mainGUILogger.error("Error In Re-Starting Application", e);
						ExceptionLogger.loggerIOException(mainGUILogger, e);
					}
				}
			});
			mainGUILogger.exit(LoggerValues.SUCCESSFUL_EXIT);
			System.exit(0);
		} catch (Exception e) {
			mainGUILogger.error("Error In Restarting Application", e);
			ExceptionLogger.loggerException(mainGUILogger, e);
			mainGUILogger.exit(LoggerValues.UNSUCCESSFUL_EXIT);
		}
	}

	private void browseButtonAction() throws IOException {
		mainGUILogger.entry();
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setDialogTitle("Select The Source Directory");
		if (fileChooser.showDialog(null, "Select") == JFileChooser.APPROVE_OPTION) {
			srcPathField.setText(fileChooser.getSelectedFile().getCanonicalPath());
		}
		mainGUILogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void addLicenseButtonAction() {
		mainGUILogger.entry();
		if (srcPathField.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Source Folder Not Selected.\nPlease Select A Source Folder.", "Error !!!", JOptionPane.ERROR_MESSAGE);
		} else if (fileTypeField.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "No File Type Is Selected.\nPlease Type Atleast One FileType.", "Error !!!", JOptionPane.ERROR_MESSAGE);
		} else if (licenseArea.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "License Field Is Empty. Please Specify A License", "Error !!!", JOptionPane.ERROR_MESSAGE);
		} else {
			String[] fileTypes = fileTypeField.getText().split(" ");
			StringBuffer buffer = new StringBuffer("");
			boolean found = false;
			for (int i = 0; i < fileTypes.length; ++i) {
				found = false;
				fileTypes[i] = fileTypes[i].trim();
				if (fileTypes[i].startsWith(".")) {
					fileTypes[i] = fileTypes[i].substring(fileTypes[i].indexOf('.') + 1);
				}
				for (String string : FileNames.AVAILABLE_FILETYPES) {
					if (string.equals(fileTypes[i])) {
						found = true;
					}
				}
				if (!found) {
					buffer.append(" " + fileTypes[i]);
				}
			}
			if (buffer.length() != 0) {
				int choice = JOptionPane.showConfirmDialog(this, "Following Extensions Aren't Supported Yet :\n\t" + buffer + "\nDo You Want To Continue", "Extension(s) Not Found !!!", JOptionPane.YES_NO_OPTION);
				if (choice != JOptionPane.YES_OPTION) {
					return;
				}
			}
			GenerateTempLicense.tempLicenseTemplate(licenseArea.getText());
			File tempLic = new File(FileNames.TEMP_PATH + FileNames.SEPARATOR + FileNames.TEMP_LIC);
			if (includeLicenseBox.isSelected() && !tempLic.exists()) {
				GenerateTempLicense.tempLicenseFromString(licenseArea.getText());
			}
			AddLicense.insertLicense(srcPathField.getText(), new ArrayList<String>(Arrays.asList(fileTypes)), recursiveBox.isSelected(), includeLicenseBox.isSelected());
			DeleteTempFiles.deletetempFiles();
		}
		mainGUILogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void removeLicenseButtonAction() {
		mainGUILogger.entry();
		if (srcPathField.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Source Folder Not Selected.\nPlease Select A Source Folder.", "Error !!!", JOptionPane.ERROR_MESSAGE);
			mainGUILogger.exit("Source Folder Not Selected");
		} else if (fileTypeField.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "No File Type Is Selected.\nPlease Type Atleast One FileType.", "Error !!!", JOptionPane.ERROR_MESSAGE);
			mainGUILogger.exit("No File Type Is Selected");
		} else {
			String[] fileTypes = fileTypeField.getText().split(" ");
			for (int i = 0; i < fileTypes.length; ++i) {
				fileTypes[i].trim();
				if (fileTypes[i].startsWith(".")) {
					fileTypes[i] = fileTypes[i].substring(fileTypes[i].indexOf('.') + 1);
				}
			}
			RemoveLicense.removeLicense(srcPathField.getText(), new ArrayList<String>(Arrays.asList(fileTypes)));
			mainGUILogger.exit(LoggerValues.SUCCESSFUL_EXIT);
		}
	}

	public String getText() {
		return licenseArea.getText();
	}

	public void setText(String text) {
		licenseArea.setText(text);
	}
}