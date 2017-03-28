package org.xf.app.componentstest;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import org.xf.app.actor2D.AnimationStrip;
import org.xf.app.actor2D.Vector2D;
import org.xf.app.components.ButtonImageGroup;
import org.xf.app.components.Label2D;
import org.xf.app.components.RadioButton2D;
import org.xf.app.components.RadioButtonGroup;

public class RadioButton2DTest extends JPanel implements Runnable {
	// 
	private Thread animation;
	private BufferedImage offscreen;
	
	// �Ƿ���ó��������б�ĵ�ѡ��ť
	private RadioButton2D singleRB;
	
	// ���ٳ��������Ƿ����
	private boolean powersEnabled;
	
	//���ó��������б�İ�ť��
	private RadioButtonGroup rbGroup;
	
	// ���õĳ����������ַ�������
	private final String[] POWERS = { "Fireball", "Super Kick", "Acid Storm",
			"Razor Talons", "Electroshock" };
	private final int NUM_BUTTONS = POWERS.length;
	
	// ����δѡ���״̬
	private Label2D status;
	public RadioButton2DTest(){
		setBounds(0,0,400,400);
		init();
	}
	public void init() {
		offscreen = new BufferedImage(this.getSize().width,
				this.getSize().height, BufferedImage.TYPE_INT_RGB);
		
		// Ϊ��ť����һ����ť��
		ButtonImageGroup group = new ButtonImageGroup(2, "images/bg4.jpg");
		group.init(this);
		Label2D label;
		Font font = new Font("Helvetica", Font.PLAIN, 18);
		
		// �����л����������Ƿ���õĵ�ѡ��ť
		label = new Label2D(font, "Enable Superpowers", Color.WHITE);
		singleRB = new RadioButton2D(label, group, null, new Vector2D.Double(
				50, 50));
		
		label.centerOn(singleRB.getBounds(), (Graphics2D) offscreen.getGraphics());
		label.setX(singleRB.getBounds().getX()
				+ singleRB.getBounds().getWidth() + 5);
		singleRB.setSelected(true);
		powersEnabled = singleRB.isSelected();
		addMouseListener(singleRB);
		
		// �������ɲ�ͬ�����ĵ�ѡ��ť
		RadioButton2D rb;
		rbGroup = new RadioButtonGroup();
		for (int i = 0; i < NUM_BUTTONS; i++) {
			label = new Label2D(font, POWERS[i], Color.WHITE);
			rb = new RadioButton2D(label, group, rbGroup, new Vector2D.Double(
					100, 100 + (i * 35)));
			label.centerOn(rb.getBounds(), (Graphics2D) offscreen .getGraphics());
			label.setX(rb.getBounds().getX() + rb.getBounds().getWidth() + 5);
			addMouseListener(rb);
			
			//Ĭ������£���0����ťӦ�ñ�ѡ��
			if (i == 0)
				rb.setSelected(true);
		}
		
		// �ó�ʼ�Ŀհ��ַ�������״̬��ǩ
		status = new Label2D(font, "", new Color(0, 255, 255));
		status.setPos(new Vector2D.Double(50, 325));
		
		AnimationStrip.observer = this;
	} // init

	public void start() {
		
		// ���������߳�
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
		
		// ����������Ǹ�ѡ��ı䣬���л�����ѡ�����״̬
		// group
		if (powersEnabled != singleRB.isSelected()) {
			powersEnabled = singleRB.isSelected();
			rbGroup.setVisible(powersEnabled);
		}
		
		// ���±�ǩ������
		
		if (powersEnabled == true && rbGroup.getSelection() != null) {
			status.setText("Selected superpower: "
					+ rbGroup.getSelection().getText());
		} else {
			status.setText("Superpowers disabled");
		}
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D bg = (Graphics2D) offscreen.getGraphics();
		bg.setPaint(Color.BLACK);
		bg.fillRect(0, 0, getSize().width, getSize().height);
		
		// ���Ƶ������Ǹ���ť
		singleRB.paintComponent2D(bg);
		
		// ���ư�ť��
		rbGroup.paint(bg);
		
		// ����״̬��ǩ
		status.paintComponent2D(bg);
		g.drawImage(offscreen, 0, 0, this);
	} // paint
} // RadioButton2DTest