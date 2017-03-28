package org.xf.gamefly.frame;

import javax.swing.JFrame;
import org.xf.gamefly.panel.TopJPanel;

public class TopJFrame extends JFrame{
	
	private TopJPanel topPnl;

	public TopJFrame(){
		init();
	}
	void init() {
		setTitle("≈≈––∞Ò");
		setLayout(null);
		setBounds(350,150,300,500);
		// setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		
		topPnl = new TopJPanel(this);
		add(topPnl);

	}
	
}
