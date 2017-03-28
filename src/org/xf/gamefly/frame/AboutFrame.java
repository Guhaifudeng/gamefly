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
		this.setLocation(350, 150);//����λ��
		this.setSize(400, 550);//���ڴ�С
		this.setTitle("����");//����
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(null);//�����
		MyJPanel mypnl = new MyJPanel();
		this.add(mypnl);
		
	}
	class MyJPanel  extends JPanel{
		public MyJPanel(){
			this.setBounds(0, 0, 390, 520);
			this.setLayout(new BorderLayout(10,10));
			//��Ϸ����
			JPanel topPnl = new JPanel(new GridLayout(6,1,10,10));//ǰ�������ֱ�ʾ�������ã���������ʾ���м��
			TitledBorder border = BorderFactory.createTitledBorder("��Ϸ˵��");
			border.setTitleColor(Color.WHITE);
			topPnl.setBorder(border);
			topPnl.setOpaque(false);
			//��ʼ��
			JLabel[] topLbl = new JLabel[6];
			for(int i=0;i<6;i++){
				topLbl[i] = new JLabel();
				topLbl[i].setForeground(Color.WHITE);
			}
			//����������������ֵ
			topLbl[0].setText("Ѫ���� "+"20  "+"����Ѫ����һ");
			topLbl[1].setText("���֣� "+"С�ͻ�+5�����ͻ�+10������+10");
			topLbl[2].setText("������ "+"5�����ʧ����+1Բ������+1Ѫ��");
			topLbl[3].setText("����ӵ��� "+"ֱ�������⡢Բ����5��");
			topLbl[4].setText("�л��ӵ�(<1500)�� "+"ֱ�������⡢���");
			topLbl[5].setText("�л��ӵ�(>1500)������");
			//�ѱ�ǩ�ŵ��������
			topPnl.add(topLbl[0]);
			topPnl.add(topLbl[1]);
			topPnl.add(topLbl[2]);
			topPnl.add(topLbl[3]);
			topPnl.add(topLbl[4]);
			topPnl.add(topLbl[5]);
			
			
			this.add(topPnl,BorderLayout.NORTH);
			//��ݼ�
			JPanel cetPnl = new JPanel(new GridLayout(5,1,10,10));
			border = BorderFactory.createTitledBorder("��ݼ�");
			border.setTitleColor(Color.WHITE);
			cetPnl.setBorder(border);
//		cetPnl.setBorder(BorderFactory.createTitledBorder("��ݼ�"));
			cetPnl.setOpaque(false);
			JLabel[] cetLbl = new JLabel[5];
			for(int i=0;i<5;i++){
				cetLbl[i] = new JLabel();
				cetLbl[i].setForeground(Color.WHITE);
			}
			
			cetLbl[0].setText("�ӵ��л���"+"Ctrl+X");
			cetLbl[1].setText("�ӵ����䣺"+"Ctrl+z");
			cetLbl[2].setText("�˳���Ϸ(Game Over)��"+"F1");
			cetLbl[3].setText("���¿�ʼ��Ϸ(Game Over)��"+"F2");
			cetLbl[4].setText("λ���ƶ���"+"��ꡢ�������Ҽ�");
			cetPnl.add(cetLbl[0]);
			cetPnl.add(cetLbl[1]);
			cetPnl.add(cetLbl[2]);
			cetPnl.add(cetLbl[3]);
			cetPnl.add(cetLbl[4]);
		
			this.add(cetPnl,BorderLayout.CENTER);
			//����
			JPanel btmPnl = new JPanel();
			btmPnl.setOpaque(false);
			border = BorderFactory.createTitledBorder("����");
			border.setTitleColor(Color.WHITE);
			btmPnl.setBorder(border);
			//btmPnl.setBorder(BorderFactory.createTitledBorder("����"));
			
			JLabel[] btmLbl = new JLabel[1];
			for(int i=0;i<1;i++){
				btmLbl[i] = new JLabel();
				btmLbl[i].setForeground(Color.WHITE);
			}
			
			
			btmLbl[0].setText("�汾:  �ɻ���սV1.0   "+"2016-04");
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
