package org.xf.gamefly.frame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.xf.gamefly.bean.Boy;
import org.xf.gamefly.bean.Girl;
import org.xf.gamefly.bean.User;
import org.xf.gamefly.graphics.BackgroundImageGroup;
import org.xf.gamefly.panel.HomeJPanel;
import org.xf.gamefly.util.SoundPlayer;

public class HomeJFrame extends JFrame {
	public static  Boy boy;
	public static  Girl girl;
	private HomeJPanel homePnl;

	
	public HomeJFrame() {
		init();
	}

	public HomeJFrame(Boy boy) {
		init();
		
		this.boy = boy;
		//System.out.println("1-"+this.boy.getName());
	}

	public HomeJFrame(Girl girl) {
		init();
		this.girl = girl;
	}
	void init() {
		initSound();
		setSize(500, 700);
		setLocation(300, 100);
		setTitle("飞机大战V1.2-主页面");
		// setLayout(null);
		// setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		MyJPanel mypnl = new MyJPanel();
		
		homePnl = new HomeJPanel(this);
		homePnl.setOpaque(false);
		mypnl.add(homePnl);
	
		add(mypnl);

	}
	private SoundPlayer soundPlayer;
	

	// 默认背景音乐
	public void initSound() {
		soundPlayer = new SoundPlayer("sound/menu.wav");
		soundPlayer.loop();
	}
	public void stopSound(){
		soundPlayer.stop();
		soundPlayer =null;
	}
	class MyJPanel  extends JPanel{
		public MyJPanel(){
			this.setBounds(0, 0, 500, 700);
			this.setLayout(null);
		}
		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			Image image = IndexJFrame.bgImage.getImage(BackgroundImageGroup.HOME_IMAGE);
			g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
	

}
