package org.bhawanisingh.calotes.gui;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class Layout {

	public static void add(JPanel panel, JComponent component, int xPos, int yPos, int compWidth, int compHeight, int xStretch, int yStretch, int anchor, int fill) {
		add(panel, component, xPos, yPos, compWidth, compHeight, xStretch, yStretch, anchor, fill, new Insets(5, 5, 5, 5));
	}

	public static void add(JPanel panel, JComponent component, int xPos, int yPos, int compWidth, int compHeight, int xStretch, int yStretch, int anchor, int fill, Insets insets) {

		GridBagConstraints constraints = new GridBagConstraints();

		constraints.gridx = xPos;
		constraints.gridy = yPos;
		constraints.gridwidth = compWidth;
		constraints.gridheight = compHeight;
		constraints.fill = fill;
		constraints.weightx = xStretch;
		constraints.weighty = yStretch;
		constraints.insets = insets;
		constraints.ipadx = 2;
		constraints.ipady = 2;
		panel.add(component, constraints);
	}
}