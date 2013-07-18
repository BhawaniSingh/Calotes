package org.bhawanisingh.calotes.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bhawanisingh.calotes.api.util.FileNames;
import org.bhawanisingh.calotes.api.util.GenerateTempLicense;
import org.bhawanisingh.calotes.api.logging.ExceptionLogger;
import org.bhawanisingh.calotes.api.logging.LoggerValues;

public class AboutDialog extends JDialog {

	private Logger aboutDialogLogger = LogManager.getLogger(AboutDialog.class);

	private int yPosition = -1;

	private static final long serialVersionUID = -1975351542158843913L;

	private BufferedImage bufferedImage;
	private BufferedImage bufferedIcon;

	private JPanel mainPanel;
	private JPanel displayPanel;
	private JPanel emptyPanel;

	private JScrollPane licenseScrollPane;
	private JScrollPane developerScrollPane;
	private JEditorPane developerPane;
	private JTextArea licenseTextArea;
	private JLabel programLabel;
	private JLabel versionLabel;
	private JButton licenseButton;
	private JButton developerButton;

	private Border border;

	public AboutDialog(MainGUI gui, BufferedImage bufferedImage, Border border) {

		aboutDialogLogger.entry();
		try {
			bufferedIcon = ImageIO.read(MainGUI.class.getResource("/icon.png"));
		} catch (IOException e) {
			aboutDialogLogger.error("Error In Loading \"background.jpg\"", e);
			ExceptionLogger.loggerIOException(aboutDialogLogger, e);
		}
		this.setTitle("About " + FileNames.PROGRAM_NAME);
		this.bufferedImage = bufferedImage;
		this.border = border;
		initialize();
		themeing();
		addComponents();
		addEvents();
		textToArea();
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(gui);
		this.setVisible(true);
		aboutDialogLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void initialize() {

		aboutDialogLogger.entry();
		mainPanel = new JPanel(new GridBagLayout()) {
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(bufferedImage, 0, 0, getWidth(), getHeight(), this);
				g.setColor(new Color(0, 153, 204, 30));
				g.drawImage(bufferedIcon, getWidth() / 2 - bufferedIcon.getWidth() / 2, getHeight() / 2 - bufferedIcon.getHeight() / 2, bufferedIcon.getWidth(), bufferedIcon.getHeight(), this);
				g.fillRect(0, 0, getWidth(), getHeight());
				
			}
		};

		programLabel = new JLabel(FileNames.PROGRAM_NAME);
		versionLabel = new JLabel("Version : " + FileNames.PROGRAM_VERSION);

		displayPanel = new JPanel(new CardLayout());
		displayPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, new Color(0, 0, 0, 255), new Color(100, 100, 100, 0)));
		displayPanel.setBackground(new Color(0, 0, 0, 0));
		displayPanel.setBorder(border);

		licenseTextArea = new JTextArea(15, 60);
		licenseScrollPane = new JScrollPane(licenseTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		developerPane = new JEditorPane();
		developerScrollPane = new JScrollPane(developerPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		licenseButton = new JButton("License");
		developerButton = new JButton("Developer");
		emptyPanel = new JPanel();
		new JButton("Close");
		aboutDialogLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void themeing() {

		aboutDialogLogger.entry();
		Color color = new Color(0, 0, 0, 0);

		mainPanel.setOpaque(false);
		mainPanel.setBackground(new Color(51, 181, 229));

		programLabel.setFont(new Font(programLabel.getFont().getName(), Font.BOLD, 24));

		displayPanel.setOpaque(false);

		licenseScrollPane.setOpaque(false);
		licenseScrollPane.getViewport().setOpaque(false);
		licenseScrollPane.setBorder(null);
		licenseScrollPane.setViewportBorder(null);
		licenseTextArea.setOpaque(false);
		licenseTextArea.setBackground(color);
		licenseTextArea.setEditable(false);

		developerScrollPane.setOpaque(false);
		developerScrollPane.getViewport().setOpaque(false);
		developerScrollPane.setBorder(null);
		developerScrollPane.setViewportBorder(null);
		developerPane.setOpaque(false);
		developerPane.setBackground(color);
		developerPane.setEditable(false);
		emptyPanel.setOpaque(false);
		aboutDialogLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void addComponents() {

		aboutDialogLogger.entry();
		displayPanel.add(developerScrollPane);
		displayPanel.add(licenseScrollPane);

		Layout.add(mainPanel, programLabel, 0, ++yPosition, 1, 1, 100, 0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL);

		Layout.add(mainPanel, versionLabel, 0, ++yPosition, 1, 1, 100, 0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL);

		Layout.add(mainPanel, displayPanel, 0, ++yPosition, 3, 1, 100, 100, GridBagConstraints.EAST, GridBagConstraints.BOTH);

		Layout.add(mainPanel, emptyPanel, 0, ++yPosition, 1, 1, 100, 0, GridBagConstraints.EAST, GridBagConstraints.BOTH);
		Layout.add(mainPanel, developerButton, 1, yPosition, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE);
		Layout.add(mainPanel, licenseButton, 2, yPosition, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE);
		
		this.add(mainPanel);
		aboutDialogLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void addEvents() {

		aboutDialogLogger.entry();
		developerPane.addHyperlinkListener(new HyperlinkListener() {

			@Override
			public void hyperlinkUpdate(HyperlinkEvent e) {
				if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
					if (Desktop.isDesktopSupported()) {
						try {
							Desktop.getDesktop().browse(e.getURL().toURI());
						} catch (IOException e1) {
							aboutDialogLogger.error("Error In browsing Links", e1);
							ExceptionLogger.loggerIOException(aboutDialogLogger, e1);
						} catch (URISyntaxException e1) {
							aboutDialogLogger.error("Error In browsing Links", e1);
							ExceptionLogger.loggerURISyntaxException(aboutDialogLogger, e1);
						}
					}
				}
			}
		});

		developerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (displayPanel.getLayout());
				cl.first(displayPanel);

			}
		});

		licenseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (displayPanel.getLayout());
				cl.last(displayPanel);

			}
		});
		aboutDialogLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void textToArea() {

		aboutDialogLogger.entry();
		StringBuilder builder = new StringBuilder("");
		developerPane.setContentType("text/html");
		licenseTextArea.setText("");
		builder.append("<HTML>");
		BufferedReader reader = GenerateTempLicense.aboutSoft();
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				licenseTextArea.setText(licenseTextArea.getText() + line + "\n");
			}
			reader.close();
		} catch (IOException e) {
			aboutDialogLogger.error("Error In Creating Software License Stream", e);
			ExceptionLogger.loggerIOException(aboutDialogLogger, e);
		}

		if (!(FileNames.DEVELOPERS.length == 1 && FileNames.DEVELOPERS[0].trim().length() == 0)) {
			builder.append("\n\t<b><u>Developers</u> :</b>\n\t<br />\n\t<ul>");
			for (int i = 0; i < FileNames.DEVELOPERS.length; ++i) {
				builder.append("\n\t\t<li><b >" + FileNames.DEVELOPERS[i] + "</b>  &lt;<a href=\"http://mailto:" + FileNames.DEVELOPERS_EMAIL[i] + "\">" + FileNames.DEVELOPERS_EMAIL[i] + "</a>&gt;  &lt;<a href=http://\"" + FileNames.DEVELOPERS_WEBSITE[i] + "\">" + FileNames.DEVELOPERS_WEBSITE[i] + "</a>&gt;</li>");
			}
			builder.append("\n\t</font></ul>");
		}
		if (!(FileNames.TESTERS.length == 1 && FileNames.TESTERS[0].trim().length() == 0)) {
			builder.append("\n\t<b><u>Testers</u> :</b>\n\t<br />\n\t<ul>");
			for (int i = 0; i < FileNames.TESTERS.length; ++i) {
				builder.append("\n\t\t<li color=\"red\">" + FileNames.TESTERS[i] + ", &lt;<a href=\"mailto:" + FileNames.TESTERS_EMAIL[i] + "\">" + FileNames.TESTERS_EMAIL[i] + "</a>&gt;  &lt;<a href=http://\"" + FileNames.TESTERS_WEBSITE[i] + "\">" + FileNames.TESTERS_WEBSITE[i] + "</a>&gt;</li>");
			}
			builder.append("\n\t</ul>");
		}
		if (!(FileNames.CONTRIBUTERS.length == 1 && FileNames.CONTRIBUTERS[0].trim().length() == 0)) {
			builder.append("\n\t<b><u>Contributers</u> :</b>\n\t<br />\n\t<ul>");
			for (int i = 0; i < FileNames.CONTRIBUTERS.length; ++i) {
				builder.append("\n\t\t<li>" + FileNames.CONTRIBUTERS[i] + ", &lt;<a href=\"mailto:" + FileNames.CONTRIBUTERS_EMAIL[i] + "\">" + FileNames.CONTRIBUTERS_EMAIL[i] + "</a>&gt;  &lt;<a href=http://\"" + FileNames.CONTRIBUTERS_WEBSITE[i] + "\">" + FileNames.CONTRIBUTERS_WEBSITE[i] + "</a>&gt;</li>");
			}
			builder.append("\n\t</ul>");
		}
		builder.append("\n</html>");
		developerPane.setText(builder.toString());
		aboutDialogLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
		;
	}
}