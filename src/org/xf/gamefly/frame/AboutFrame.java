package org.xf.gamefly.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.xf.gamefly.graphics.BackgroundImageGroup;

public class AboutFrame extends JFrame{
	
	public AboutFrame() {
		this.init();
	}
	
	
	public void init() {
		this.setLocation(350, 150);//窗口位置
		this.setSize(400, 550);//窗口大小
		this.setTitle("帮助");//标题
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(null);//面板间距
		MyJPanel mypnl = new MyJPanel();
		this.add(mypnl);
		
	}
	class MyJPanel  extends JPanel{
		public MyJPanel(){
			this.setBounds(0, 0, 390, 520);
			this.setLayout(new BorderLayout(10,10));
			//游戏规则
			JPanel topPnl = new JPanel(new GridLayout(6,1,10,10));//前两个数字表示行列设置，后两个表示行列间距
			TitledBorder border = BorderFactory.createTitledBorder("游戏说明");
			border.setTitleColor(Color.WHITE);
			topPnl.setBorder(border);
			topPnl.setOpaque(false);
			//初始化
			JLabel[] topLbl = new JLabel[6];
			for(int i=0;i<6;i++){
				topLbl[i] = new JLabel();
				topLbl[i].setForeground(Color.WHITE);
			}
			//设置面板里面的属性值
			topLbl[0].setText("血量： "+"20  "+"击中血量减一");
			topLbl[1].setText("积分： "+"小型机+5、中型机+10、蓝币+10");
			topLbl[2].setText("奖励： "+"5秒后消失，绿+1圆弹、红+1血量");
			topLbl[3].setText("玩家子弹： "+"直弹、激光、圆弹（5）");
			topLbl[4].setText("敌机子弹(<1500)： "+"直弹、激光、随机");
			topLbl[5].setText("敌机子弹(>1500)：跟踪");
			//把标签放到面板里面
			topPnl.add(topLbl[0]);
			topPnl.add(topLbl[1]);
			topPnl.add(topLbl[2]);
			topPnl.add(topLbl[3]);
			topPnl.add(topLbl[4]);
			topPnl.add(topLbl[5]);
			
			
			this.add(topPnl,BorderLayout.NORTH);
			//快捷键
			JPanel cetPnl = new JPanel(new GridLayout(5,1,10,10));
			border = BorderFactory.createTitledBorder("快捷键");
			border.setTitleColor(Color.WHITE);
			cetPnl.setBorder(border);
//		cetPnl.setBorder(BorderFactory.createTitledBorder("快捷键"));
			cetPnl.setOpaque(false);
			JLabel[] cetLbl = new JLabel[5];
			for(int i=0;i<5;i++){
				cetLbl[i] = new JLabel();
				cetLbl[i].setForeground(Color.WHITE);
			}
			
			cetLbl[0].setText("子弹切换："+"Ctrl+X");
			cetLbl[1].setText("子弹发射："+"Ctrl+z");
			cetLbl[2].setText("退出游戏(Game Over)："+"F1");
			cetLbl[3].setText("重新开始游戏(Game Over)："+"F2");
			cetLbl[4].setText("位置移动："+"鼠标、上下左右键");
			cetPnl.add(cetLbl[0]);
			cetPnl.add(cetLbl[1]);
			cetPnl.add(cetLbl[2]);
			cetPnl.add(cetLbl[3]);
			cetPnl.add(cetLbl[4]);
		
			this.add(cetPnl,BorderLayout.CENTER);
			//关于
			JPanel btmPnl = new JPanel();
			btmPnl.setOpaque(false);
			border = BorderFactory.createTitledBorder("关于");
			border.setTitleColor(Color.WHITE);
			btmPnl.setBorder(border);
			//btmPnl.setBorder(BorderFactory.createTitledBorder("关于"));
			
			JLabel[] btmLbl = new JLabel[1];
			for(int i=0;i<1;i++){
				btmLbl[i] = new JLabel();
				btmLbl[i].setForeground(Color.WHITE);
			}
			
			
			btmLbl[0].setText("版本:  飞机大战V1.0   "+"2016-04");
			btmPnl.add(btmLbl[0]);
			
			this.add(btmPnl,BorderLayout.SOUTH);
		}
		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
	}
	
}
