package org.xf.app.containterstest;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import org.xf.app.actor2D.AnimationStrip;
import org.xf.app.actor2D.ImageLoader;
import org.xf.app.actor2D.Vector2D;
import org.xf.app.components.Button2D;
import org.xf.app.components.ButtonImageGroup;
import org.xf.app.components.Label2D;
import org.xf.app.components.RadioButton2D;
import org.xf.app.containters.Panel2D;

public class Panel2DTest extends JPanel implements Runnable {
	// �����߳�
	private Thread animation;
	private BufferedImage offscreen;
	
	// �����������������Panel2D��������
	private Panel2D[] panels;
	
	// �ڷ�����Panel2D���������
	private Vector2D[] panelPos = { new Vector2D.Double(10, 10),
			new Vector2D.Double(200, 100) };
	private final int NUM_PANELS = panelPos.length;
	public Panel2DTest(){
		setBounds(0,0,400,400);
		init();
	}
	public void init()

	
	{
		offscreen = new BufferedImage(this.getSize().width,
				this.getSize().height, BufferedImage.TYPE_INT_RGB);
		// ����һ���ڷ�͸����ť��ͼ����
		ButtonImageGroup biGroup = new ButtonImageGroup(3, "images/mountains.gif");
		
		biGroup.init(this);
		// Ϊ��ѡ��ť����һ��ͼ����
		ButtonImageGroup rbGroup = new ButtonImageGroup(2, "images/notice.png");
		
		rbGroup.init(this);
		
		// ��ס����observer���ԣ����������Է������ı���ͼ��Ŀ�͸�
		// 
		AnimationStrip.observer = this;
		
		// ��ť����ѡ��ť�ͱ�ǩ�����������������ӵ����ǵ����
		// 
		Button2D button;
		RadioButton2D radioButton;
		Label2D label;
		Font font = new Font("Helvetica", Font.PLAIN, 16);
		
		//Ϊ��崴������ͼ��
		Image img = new ImageLoader(this, "images/bg1.jpg", true).getImage();
		
		// ������壬����������ӵ�������
		panels = new Panel2D[NUM_PANELS];
		
		for (int i = 0; i < NUM_PANELS; i++) {
			
			// �ñ���ͼ�������������λ�ô������
			panels[i] = new Panel2D(img, panelPos[i]);
			
			// ���������ӵ�ѡ��ť������һ�㰴ť
			radioButton = new RadioButton2D(null, rbGroup, null);
			
			panels[i].add(radioButton, 95, 95);
			radioButton.setSelected(true);
			addMouseListener(radioButton);
			
			label = new Label2D(font, "Java!", Color.WHITE);
			button = new Button2D(label, biGroup);
			panels[i].add(button, 25, 25);
			label.centerOn(button.getBounds(), (Graphics2D)offscreen.getGraphics());
			addMouseListener(button);
			addMouseMotionListener(button);
			
			label = new Label2D(font, "Java!", Color.WHITE);
			button = new Button2D(label, biGroup);
			panels[i].add(button, 130, 185);
			label.centerOn(button.getBounds(), (Graphics2D) offscreen.getGraphics());
			addMouseListener(button);
			addMouseMotionListener(button);
		}
		
	} // init

	public void start() {
		
		//���������߳�
		animation = new Thread(this);
		animation.start();
	}

	public void stop() {
		animation = null;
	}

	public void run() {
		Thread t = Thread.currentThread();
		while (t == animation) {
			
			repaint();
			try {
				Thread.sleep(10);
				
			} catch (InterruptedException e) {
				break;
			}
			
		}
	} // run

	public void update(Graphics g) {
		paint(g);
	}

	public void paint(Graphics g) {
		
		Graphics2D bg = (Graphics2D) offscreen.getGraphics();
		bg.setPaint(Color.BLACK);
		bg.fillRect(0, 0, getSize().width, getSize().height);
		
		// �������
		for (int i = 0; i < NUM_PANELS; i++) {
			panels[i].paintComponent2D(bg);
		}
		
		g.drawImage(offscreen, 0, 0, this);
	} // paint
} // Panel2DTest
