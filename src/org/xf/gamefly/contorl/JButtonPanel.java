package org.xf.gamefly.contorl;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JPanel;

class JButtonPanel extends JPanel {
	JPanel     buttonPanel = new JPanel();
    Separator separator   = new Separator();

    public JButtonPanel() {
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        setLayout(new BorderLayout(0,5));
        add(separator, "North");
        add(buttonPanel, "Center");
    }
    public void add(JButton button) {
        buttonPanel.add(button);
    }
} 
