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
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bhawanisingh.calotes.api.addlicense.AddLicense;
import org.bhawanisingh.calotes.api.logging.ExceptionLogger;
import org.bhawanisingh.calotes.api.logging.LoggerValues;
import org.bhawanisingh.calotes.api.removelicense.RemoveLicense;
import org.bhawanisingh.calotes.api.util.Directory;
import org.bhawanisingh.calotes.api.util.FileNames;
import org.bhawanisingh.calotes.api.util.GenerateTempLicense;

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
		this.mainGUILogger.entry();
		this.initialize();
		this.themeing();
		this.addComponents();
		this.addEvents();
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.pack();
		this.setTitle(FileNames.PROGRAM_NAME + " : " + FileNames.PROGRAM_VERSION);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		// new LicenseGenerator(this, this.bufferedImage);
		this.mainGUILogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void initialize() {
		this.mainGUILogger.entry();
		try {
			this.bufferedImage = ImageIO.read(MainGUI.class.getResource("/background.jpg"));
		} catch (IOException e) {
			this.mainGUILogger.error("Error In Loading \"background.jpg\"", e);
			ExceptionLogger.loggerIOException(this.mainGUILogger, e);
		}
		this.mainPanel = new JPanel(new GridBagLayout()) {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(MainGUI.this.bufferedImage, 0, 0, this.getWidth(), this.getHeight(), this);
				g.setColor(new Color(0, 153, 204, 30));
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
			}
		};

		this.mainMenuBar = new JMenuBar();
		this.separator = new JSeparator();
		this.fileMenu = new JMenu("File");
		this.selectLicenseItem = new JMenuItem("Select License", KeyEvent.VK_S);
		this.selectLicenseItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
		this.customLicenseItem = new JMenuItem("Custom License", KeyEvent.VK_C);
		this.customLicenseItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
		this.restartItem = new JMenuItem("Restart", KeyEvent.VK_R);
		this.restartItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK));
		this.exitItem = new JMenuItem("Exit", KeyEvent.VK_E);
		this.exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
		this.helpMenu = new JMenu("Help");
		this.aboutItem = new JMenuItem("About", KeyEvent.VK_A);
		this.aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));

		this.locationLabel = new JLabel("Source Folder :");
		this.srcPathField = new JTextField("/home/bhawani/Desktop/print/testfolder");
		this.srcPathField.setOpaque(false);
		this.browseButton = new JButton("Browse");

		this.fileTypeLabel = new JLabel("File Types :");
		this.fileTypeField = new JTextField("java");

		this.licenseArea = new JTextArea(10, 80);
		this.licenseScrollPane = new JScrollPane(this.licenseArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		this.recursiveBox = new JCheckBox("Recursive");
		this.recursiveBox.setToolTipText("If Selected, Program Will Also Scan Underlying Directories");
		this.recursiveBox.setSelected(true);
		this.includeLicenseBox = new JCheckBox("Include License");
		this.includeLicenseBox.setToolTipText("Select This If You Want To Add \"Licesnse.txt\" To Source Folder");
		this.includeLicenseBox.setSelected(true);
		this.emptyPanel = new JPanel();

		this.addLicenseButton = new JButton("Add");
		this.replaceLicenseButton = new JButton("Replace");
		this.removeLicenseButton = new JButton("Remove");

		this.bottomLabel = new JLabel("I Am Ready To Rock");
		this.mainGUILogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void addComponents() {

		this.mainGUILogger.entry();
		this.fileMenu.add(this.selectLicenseItem);
		this.fileMenu.add(this.customLicenseItem);
		this.fileMenu.add(this.separator);
		this.fileMenu.add(this.restartItem);
		this.fileMenu.add(this.exitItem);
		this.helpMenu.add(this.aboutItem);
		this.mainMenuBar.add(this.fileMenu);
		this.mainMenuBar.add(this.helpMenu);

		Layout.add(this.mainPanel, this.locationLabel, 0, ++this.yPosition, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE);
		Layout.add(this.mainPanel, this.srcPathField, 1, this.yPosition, 5, 1, 100, 0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL);
		Layout.add(this.mainPanel, this.browseButton, 6, this.yPosition, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE);

		Layout.add(this.mainPanel, this.fileTypeLabel, 0, ++this.yPosition, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE);
		Layout.add(this.mainPanel, this.fileTypeField, 1, this.yPosition, 6, 1, 100, 0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL);

		Layout.add(this.mainPanel, this.licenseScrollPane, 0, ++this.yPosition, 7, 1, 100, 100, GridBagConstraints.EAST, GridBagConstraints.BOTH);

		Layout.add(this.mainPanel, this.recursiveBox, 0, ++this.yPosition, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE);
		Layout.add(this.mainPanel, this.includeLicenseBox, 1, this.yPosition, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE);
		Layout.add(this.mainPanel, this.emptyPanel, 2, this.yPosition, 2, 1, 100, 0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL);
		Layout.add(this.mainPanel, this.addLicenseButton, 4, this.yPosition, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE);
		Layout.add(this.mainPanel, this.replaceLicenseButton, 5, this.yPosition, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE);
		Layout.add(this.mainPanel, this.removeLicenseButton, 6, this.yPosition, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE);

		Layout.add(this.mainPanel, this.bottomLabel, 0, ++this.yPosition, 7, 1, 100, 0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(0, 2, 0, 0));

		this.add(this.mainPanel);
		this.setJMenuBar(this.mainMenuBar);
		this.mainGUILogger.exit(LoggerValues.SUCCESSFUL_EXIT);

	}

	private void themeing() {

		this.mainGUILogger.entry();
		Border border = BorderFactory.createEtchedBorder(EtchedBorder.RAISED, new Color(0, 0, 0, 255), new Color(100, 100, 100, 0));
		Color color = new Color(0, 0, 0, 0);

		this.mainPanel.setOpaque(false);
		this.mainPanel.setBackground(new Color(51, 181, 229));

		this.srcPathField.setOpaque(false);
		this.srcPathField.setForeground(Color.BLACK);
		this.srcPathField.setBackground(color);

		this.fileTypeField.setOpaque(false);
		this.fileTypeField.setForeground(Color.BLACK);
		this.fileTypeField.setBackground(color);

		this.licenseArea.setOpaque(false);
		this.licenseArea.setForeground(Color.BLACK);
		this.licenseArea.setBackground(color);

		this.licenseScrollPane.getViewport().setBorder(null);
		this.licenseScrollPane.setOpaque(false);
		this.licenseScrollPane.setBackground(color);
		this.licenseScrollPane.getViewport().setOpaque(false);
		this.licenseScrollPane.setViewportBorder(this.fileTypeField.getBorder());

		this.emptyPanel.setOpaque(false);

		this.bottomLabel.setBorder(border);
		this.mainGUILogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void addEvents() {

		this.mainGUILogger.entry();
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				MainGUI.this.windowClosingEvent();
			}
		});

		this.selectLicenseItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				javax.swing.SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						MainGUI.this.selectLicenseItemAction();
					}
				});

			}
		});

		this.customLicenseItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainGUI.this.customLicenseItemAction();
			}
		});

		this.restartItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainGUI.this.restartButtonAction();
			}
		});

		this.exitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainGUI.this.windowClosingEvent();
			}
		});

		this.aboutItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainGUI.this.aboutItemAction();
			}
		});

		this.browseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					MainGUI.this.browseButtonAction();
				} catch (IOException e1) {
					MainGUI.this.mainGUILogger.error("Error In Getting Path", e1);
					ExceptionLogger.loggerIOException(MainGUI.this.mainGUILogger, e1);
				}

			}
		});

		this.addLicenseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainGUI.this.addLicenseButtonAction();
			}
		});

		this.removeLicenseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainGUI.this.removeLicenseButtonAction();
			}
		});
		this.replaceLicenseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainGUI.this.replaceLicenseButtonAction();
			}
		});
		this.mainGUILogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void windowClosingEvent() {
		this.mainGUILogger.entry();
		int choice = JOptionPane.showConfirmDialog(this, "Calotes Will Be Closed !!!\n Are You Sure ?", "Exiting !!!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (choice == JOptionPane.YES_OPTION) {
			Directory.deletetempFiles();
			this.mainGUILogger.exit(LoggerValues.SUCCESSFUL_EXIT);
			System.exit(0);
		}
		this.mainGUILogger.exit("Program Not Closed");
	}

	private void selectLicenseItemAction() {
		this.mainGUILogger.entry();
		new LicenseChooserDialog(this, this.bufferedImage);
		this.mainGUILogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void customLicenseItemAction() {
		this.mainGUILogger.entry();
		new CustomLicenseDialog(this, this.bufferedImage);
		this.mainGUILogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void aboutItemAction() {
		this.mainGUILogger.entry();
		new AboutDialog(this, this.bufferedImage, this.fileTypeField.getBorder());
		this.mainGUILogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void restartButtonAction() {
		this.mainGUILogger.entry();
		Directory.deletetempFiles();
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
						MainGUI.this.mainGUILogger.error("Error In Re-Starting Application", e);
						ExceptionLogger.loggerIOException(MainGUI.this.mainGUILogger, e);
					}
				}
			});
			this.mainGUILogger.exit(LoggerValues.SUCCESSFUL_EXIT);
			System.exit(0);
		} catch (Exception e) {
			this.mainGUILogger.error("Error In Restarting Application", e);
			ExceptionLogger.loggerException(this.mainGUILogger, e);
			this.mainGUILogger.exit(LoggerValues.UNSUCCESSFUL_EXIT);
		}
	}

	private void browseButtonAction() throws IOException {
		this.mainGUILogger.entry();
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setDialogTitle("Select The Source Directory");
		if (fileChooser.showDialog(null, "Select") == JFileChooser.APPROVE_OPTION) {
			this.srcPathField.setText(fileChooser.getSelectedFile().getCanonicalPath());
		}
		this.mainGUILogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void addLicenseButtonAction() {
		this.mainGUILogger.entry();
		if (this.srcPathField.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Source Folder Not Selected.\nPlease Select A Source Folder.", "Error !!!", JOptionPane.ERROR_MESSAGE);
		} else if (!Directory.validateDirectory(this.srcPathField.getText())) {
			JOptionPane.showMessageDialog(this, "I Am Not A Fool.\nI Know This path Doesn't Exist.\nSo Never Ever Try To Do This Again.\nPress Enter To Continue.", "Common Sense Error!!!", JOptionPane.ERROR_MESSAGE);
		} else if (this.fileTypeField.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "No File Type Is Selected.\nPlease Type Atleast One FileType.", "Error !!!", JOptionPane.ERROR_MESSAGE);
		} else if (this.licenseArea.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "License Field Is Empty. Please Specify A License", "Error !!!", JOptionPane.ERROR_MESSAGE);
		} else {
			String[] fileTypes = this.fileTypeField.getText().split(" ");
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
			GenerateTempLicense.tempLicenseTemplate(this.licenseArea.getText());
			File tempLic = new File(FileNames.TEMP_PATH + FileNames.SEPARATOR + FileNames.TEMP_LIC);
			if (this.includeLicenseBox.isSelected() && !tempLic.exists()) {
				GenerateTempLicense.tempLicenseFromString(this.licenseArea.getText());
			}
			AddLicense.insertLicense(this.srcPathField.getText(), new ArrayList<String>(Arrays.asList(fileTypes)), this.recursiveBox.isSelected(), this.includeLicenseBox.isSelected());
			Directory.deletetempFiles();
		}
		this.mainGUILogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void removeLicenseButtonAction() {
		this.mainGUILogger.entry();
		if (this.srcPathField.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Source Folder Not Selected.\nPlease Select A Source Folder.", "Error !!!", JOptionPane.ERROR_MESSAGE);
			this.mainGUILogger.exit("Source Folder Not Selected");
		} else if (!Directory.validateDirectory(this.srcPathField.getText())) {
			JOptionPane.showMessageDialog(this, "I Am Not A Fool.\nI Know This path Doesn't Exist.\nSo Never Ever Try To Do This Again.\nPress Enter To Continue.", "Common Sense Error!!!", JOptionPane.ERROR_MESSAGE);
		} else if (this.fileTypeField.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "No File Type Is Selected.\nPlease Type Atleast One FileType.", "Error !!!", JOptionPane.ERROR_MESSAGE);
			this.mainGUILogger.exit("No File Type Is Selected");
		} else {
			String[] fileTypes = this.fileTypeField.getText().split(" ");
			for (int i = 0; i < fileTypes.length; ++i) {
				fileTypes[i].trim();
				if (fileTypes[i].startsWith(".")) {
					fileTypes[i] = fileTypes[i].substring(fileTypes[i].indexOf('.') + 1);
				}
			}
			RemoveLicense.removeLicense(this.srcPathField.getText(), new ArrayList<String>(Arrays.asList(fileTypes)), this.recursiveBox.isSelected());
			this.mainGUILogger.exit(LoggerValues.SUCCESSFUL_EXIT);
		}
	}

	private void replaceLicenseButtonAction() {
		JOptionPane.showMessageDialog(this, "Holy Crap This Button Doesn't WORK.\nBut It Shows This Creepy Message.\nSorry !!!!!!", "Not yet Implimented", JOptionPane.ERROR_MESSAGE);
	}

	public String getText() {
		return this.licenseArea.getText();
	}

	public void setText(String text) {
		this.licenseArea.setText(text);
	}
}