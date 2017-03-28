package org.xf.gamefly.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.xf.gamefly.frame.AboutFrame;
import org.xf.gamefly.frame.GameMain;
import org.xf.gamefly.frame.HomeJFrame;
import org.xf.gamefly.frame.TopJFrame;


public class HomeJPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7164078082222991379L;
	private JButton startGameBtn;
	private JButton topBtn;
	private JButton aboutBtn;
	private JButton exitGameBtn;
	
	private Dimension preferredSize;
	MyActionListener myActionListener = new MyActionListener();
	private HomeJFrame homeFrm;
	private Icon imageIcon;

	public HomeJPanel(HomeJFrame homeFrm) {
		this.homeFrm = homeFrm;
		
		// �������
		setBounds(150, 100, 200, 200);
		setLayout(new GridLayout(4, 1, 0, 20));// ���񲼾�
		
		preferredSize = new Dimension(200,30);
		
		// ��Ϸ��ʼ��ť
		imageIcon = new ImageIcon("myimage/gameStart.gif");
		startGameBtn = new JButton(imageIcon);
		startGameBtn.setPreferredSize(preferredSize);
		startGameBtn.setFocusPainted(false);
		startGameBtn.setOpaque(false);
		startGameBtn.setContentAreaFilled(false);
		startGameBtn.setBorderPainted(false);
		startGameBtn.addActionListener(myActionListener);
		// ���������ť
		imageIcon = new ImageIcon("myimage/playerTop.gif");
		topBtn = new JButton(imageIcon);
		topBtn.setPreferredSize(preferredSize);
		topBtn.setFocusPainted(false);
		topBtn.setOpaque(false);
		topBtn.setContentAreaFilled(false);
		topBtn.setBorderPainted(false);
		topBtn.addActionListener(myActionListener);
		
		// ��Ϸ˵����ť
		imageIcon = new ImageIcon("myimage/gameShow.gif");
		aboutBtn = new JButton(imageIcon);
		aboutBtn.setPreferredSize(preferredSize);
		aboutBtn.setFocusPainted(false);
		aboutBtn.setOpaque(false);
		aboutBtn.setContentAreaFilled(false);
		aboutBtn.setBorderPainted(false);
		aboutBtn.addActionListener(myActionListener);
		// �˳���Ϸ��ť
		imageIcon = new ImageIcon("myimage/gameExit.gif");
		exitGameBtn = new JButton(imageIcon);
		//exitGameBtn.setBackground(bg);
		//exitGameBtn.setText("�˳���Ϸ");
		//exitGameBtn.setForeground(Color.GREEN);
		exitGameBtn.setPreferredSize(preferredSize);
		exitGameBtn.setFocusPainted(false);
		exitGameBtn.setOpaque(false);
		exitGameBtn.setContentAreaFilled(false);
		exitGameBtn.setBorderPainted(false);
		
		exitGameBtn.addActionListener(myActionListener);
		//����������Ϣ
		add(startGameBtn);
		add(topBtn);
		add(aboutBtn);
		add(exitGameBtn);
	}

	class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==startGameBtn){
				//��ʼ��Ϸ
				HomeJPanel.this.homeFrm.stopSound();
				HomeJPanel.this.homeFrm.dispose();
				GameMain jframe = new GameMain();
				jframe.setVisible(true);
				jframe.initSound();
			}else if(e.getSource()==topBtn){
				//��������
				new TopJFrame().setVisible(true);
			}else if(e.getSource()== aboutBtn){
				//��Ϸ˵������
				new AboutFrame().setVisible(true);
			}else if(e.getSource() == exitGameBtn){
				System.exit(0);
			}
			
			
		}

		

	}
}
