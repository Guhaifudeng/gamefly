package org.xf.app.componentstest;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import org.xf.app.actor2D.AnimationStrip;
import org.xf.app.actor2D.Vector2D;
import org.xf.app.components.Button2D;
import org.xf.app.components.ButtonImageGroup;
import org.xf.app.components.Label2D;

public class Button2DTest extends JPanel implements Runnable, ActionListener {
	// �����߳�
	private Thread animation;
	private BufferedImage offscreen;
	
	// Ҫ���Ƶ�Button2D��������
	private Button2D[] buttons;
	
	// Ҫ�����Ƶ�����������
	private final String[] fonts = { "Helvetica", "Arial", "Courier",
			"Terminal", "Georgia" };
	private final int NUM_BUTTONS = fonts.length;

	public Button2DTest() {
		setVisible(true);
		setEnabled(true);
		setBounds(0, 0, 400, 400);
		init();
	}

	public void init() {
		
		//����ButtonImageGroup����ťʹ��
		ButtonImageGroup group = new ButtonImageGroup(3, "images/bg4.jpg");
		group.init(this);
		offscreen = new BufferedImage(this.getSize().width,
				this.getSize().height, BufferedImage.TYPE_INT_RGB);
		// ����ʵ�ʵİ�ť
		buttons = new Button2D[NUM_BUTTONS];
		for (int i = 0; i < NUM_BUTTONS; i++) {
			
			//����һ��������������ı�ǩ
			Label2D label = new Label2D(new Font(fonts[i], Font.PLAIN, 18),
					fonts[i], Color.BLACK);
			
			// ������ť���ñ�ǩ����
			buttons[i] = new Button2D(label, group, new Vector2D.Double(50,
					10 + (i * 55)));
			
			//ֻ��Ҫ��������ĳЩ������������Ըö����������--------������������
			
			label.centerOn(buttons[i].getBounds(), (Graphics2D)offscreen.getGraphics());
			
			// ע�ᰴť����������¼�
			addMouseListener(buttons[i]);
			//addActionListener(this);
			addMouseMotionListener(buttons[i]);
			
			// �ð�ť֪��JPanel��������ն����¼�
			// 
			buttons[i].addActionListener(this);
		}
		
		AnimationStrip.observer = this;
	} // init

	public void start() {
		
		// start the animation thread
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

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D bg = (Graphics2D) offscreen.getGraphics();
		bg.setPaint(Color.BLUE.darker());
		bg.fillRect(0, 0, getSize().width, getSize().height);
		
		// ���ư�ť
		for (int i = 0; i < NUM_BUTTONS; i++) {
			buttons[i].paintComponent2D(bg);
		}
		g.drawImage(offscreen, 0, 0, this);
	} // ����ж�����Button2D���󷢳������¼���ӡ������̨

	public void actionPerformed(ActionEvent e) {
		System.out.println(e);
	}
} // Button2DTest