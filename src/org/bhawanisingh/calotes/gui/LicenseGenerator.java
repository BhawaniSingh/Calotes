package org.bhawanisingh.calotes.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bhawanisingh.calotes.api.license.GenericLicense;
import org.bhawanisingh.calotes.api.logging.LoggerValues;
import org.bhawanisingh.calotes.api.util.FileNames;

public class LicenseGenerator extends JDialog {
	private Logger licenseGeneratorLogger = LogManager.getLogger(LicenseGenerator.class.getName());

	private static final long serialVersionUID = -1324260102680522813L;
	private int yPosition = -1;
	private BufferedImage bufferedImage;
	private MainGUI gui;

	private JPanel mainPanel;
	private JLabel[] labels;
	private JScrollPane itemScrollPane;
	private JPanel itemPanel;
	private JRadioButton[][] radioButtons;
	private ButtonGroup[] buttonGroups;
	private JPanel[] itemPanels;
	private JPanel buttonPanel;
	private JPanel emptyPanel;
	private JButton okButton;
	private JButton cancelButton;
	private GridBagLayout gridBagLayout = new GridBagLayout();

	public LicenseGenerator(MainGUI gui, BufferedImage bufferedImage) {
		licenseGeneratorLogger.entry();
		this.gui = gui;
		this.bufferedImage = bufferedImage;

		initialize();
		themeing();
		addComponents();
		addEvents();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		this.setTitle(FileNames.PROGRAM_NAME + " : " + FileNames.PROGRAM_VERSION);
		this.setLocationRelativeTo(this.gui);
		this.setVisible(true);
		licenseGeneratorLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void initialize() {
		licenseGeneratorLogger.entry();
		mainPanel = new JPanel(new BorderLayout(5, 5)) {
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(bufferedImage, 0, 0, getWidth(), getHeight(), this);
				g.setColor(new Color(0, 153, 204, 30));
				g.fillRect(0, 0, getWidth(), getHeight());
			}
		};

		labels = new JLabel[GenericLicense.LICENSE_FILTERING.length];
		itemPanels = new JPanel[GenericLicense.LICENSE_FILTERING.length];
		radioButtons = new JRadioButton[GenericLicense.LICENSE_FILTERING.length][GenericLicense.numberOfOptions];
		buttonGroups = new ButtonGroup[GenericLicense.LICENSE_FILTERING.length];

		for (int i = 0; i < GenericLicense.LICENSE_FILTERING.length; ++i) {
			itemPanels[i] = new JPanel(gridBagLayout);
			labels[i] = new JLabel(GenericLicense.LICENSE_FILTERING[i][0]);
			labels[i].setToolTipText(GenericLicense.LICENSE_FILTERING[i][1]);
			buttonGroups[i] = new ButtonGroup();
			radioButtons[i][0] = new JRadioButton("Yes");
			buttonGroups[i].add(radioButtons[i][0]);
			radioButtons[i][1] = new JRadioButton("No");
			buttonGroups[i].add(radioButtons[i][1]);
			radioButtons[i][2] = new JRadioButton("Must");
			buttonGroups[i].add(radioButtons[i][2]);
			radioButtons[i][3] = new JRadioButton("N/A");
			radioButtons[i][3].setSelected(true);
			buttonGroups[i].add(radioButtons[i][3]);
		}
		itemPanel = new JPanel(gridBagLayout);
		itemScrollPane = new JScrollPane(itemPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		buttonPanel = new JPanel(gridBagLayout);
		emptyPanel = new JPanel();
		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		licenseGeneratorLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void themeing() {
		licenseGeneratorLogger.entry();
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		Color color = new Color(0, 0, 0, 0);

		mainPanel.setOpaque(false);
		mainPanel.setBackground(new Color(51, 181, 229));
		for (int i = 0; i < GenericLicense.LICENSE_FILTERING.length; ++i) {
			itemPanels[i].setOpaque(false);
			itemPanels[i].setBackground(color);
			itemPanels[i].setBorder(border);
		}

		itemScrollPane.getViewport().setPreferredSize(new Dimension(250, 425));
		itemScrollPane.setOpaque(true);
		itemScrollPane.setBackground(color);
		itemScrollPane.setBorder(border);
		itemScrollPane.getViewport().setVisible(true);

		itemPanel.setOpaque(true);
		itemPanel.setBackground(color);

		buttonPanel.setOpaque(false);
		buttonPanel.setBackground(color);
		emptyPanel.setOpaque(false);
		buttonPanel.setBackground(color);
		licenseGeneratorLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void addComponents() {
		licenseGeneratorLogger.entry();
		for (int i = 0; i < GenericLicense.LICENSE_FILTERING.length; ++i) {
			yPosition = -1;
			Layout.add(itemPanels[i], labels[i], 0, ++yPosition, 4, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL);
			Layout.add(itemPanels[i], radioButtons[i][0], 0, ++yPosition, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE);
			Layout.add(itemPanels[i], radioButtons[i][1], 1, yPosition, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE);
			Layout.add(itemPanels[i], radioButtons[i][2], 2, yPosition, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE);
			Layout.add(itemPanels[i], radioButtons[i][3], 3, yPosition, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE);
		}
		yPosition = -1;
		for (int i = 0; i < GenericLicense.LICENSE_FILTERING.length; ++i) {
			Layout.add(itemPanel, itemPanels[i], 0, ++yPosition, 1, 1, 100, 0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL);
		}
		yPosition = -1;
		Layout.add(buttonPanel, emptyPanel, 0, ++yPosition, 1, 1, 100, 0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL);
		Layout.add(buttonPanel, okButton, 1, yPosition, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE);
		Layout.add(buttonPanel, cancelButton, 2, yPosition, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE);
		mainPanel.add(itemScrollPane, BorderLayout.WEST);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		this.add(mainPanel);
		licenseGeneratorLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}

	private void addEvents() {
		licenseGeneratorLogger.entry();
		itemScrollPane.getHorizontalScrollBar().addAdjustmentListener(new AdjustmentListener() {

			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				repaint();

			}
		});
		itemScrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {

			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				repaint();

			}
		});
		licenseGeneratorLogger.exit(LoggerValues.SUCCESSFUL_EXIT);
	}
}