package org.xf.gamefly.frame;

import javax.swing.JFrame;

import org.xf.gamefly.panel.LoginJPanel;
import org.xf.gamefly.panel.ThreeDPanel;



public class LoginJFrame extends JFrame {
	public void init() {
		ThreeDPanel p = new ThreeDPanel();
		LoginJPanel lp = new LoginJPanel(this);
		lp.setOpaque(false);
		p.add(lp);
		//p.setBorder(BorderFactory.createTitledBorder("ssgfs"));
		add(p);
		setLocation(300,100+200);
		setSize(400,250);
		setTitle("�ɻ���սV1.2-��¼����");
		setResizable(false);
		//p.add(new ());
		//add(p);
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public LoginJFrame(){
		init(); 
	}
	
} 