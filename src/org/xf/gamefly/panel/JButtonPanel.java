package org.xf.gamefly.panel;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.xf.gamefly.contorl.Separator;



public class JButtonPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3665345893153591808L;
	JPanel     buttonPanel = new JPanel();
    Separator separator   = new Separator();

    public JButtonPanel() {
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.setOpaque(false);
        setLayout(new BorderLayout(0,5));
        add(separator, "North");
        add(buttonPanel, "Center");
    }
    public void add(JButton button) {
    	
    	button.setForeground(new Color(221,228,251));
        buttonPanel.add(button);
    }
} 
