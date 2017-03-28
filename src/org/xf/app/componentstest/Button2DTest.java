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
	// 动画线程
	private Thread animation;
	private BufferedImage offscreen;
	
	// 要绘制的Button2D对象数组
	private Button2D[] buttons;
	
	// 要来绘制的字体名数组
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
		
		//创建ButtonImageGroup供按钮使用
		ButtonImageGroup group = new ButtonImageGroup(3, "images/bg4.jpg");
		group.init(this);
		offscreen = new BufferedImage(this.getSize().width,
				this.getSize().height, BufferedImage.TYPE_INT_RGB);
		// 创建实际的按钮
		buttons = new Button2D[NUM_BUTTONS];
		for (int i = 0; i < NUM_BUTTONS; i++) {
			
			//创建一个描述所用字体的标签
			Label2D label = new Label2D(new Font(fonts[i], Font.PLAIN, 18),
					fonts[i], Color.BLACK);
			
			// 创建按钮并让标签居中
			buttons[i] = new Button2D(label, group, new Vector2D.Double(50,
					10 + (i * 55)));
			
			//只需要这个对象的某些操作，并不会对该对象进行设置--------？？？？？？
			
			label.centerOn(buttons[i].getBounds(), (Graphics2D)offscreen.getGraphics());
			
			// 注册按钮来接收鼠标事件
			addMouseListener(buttons[i]);
			//addActionListener(this);
			addMouseMotionListener(buttons[i]);
			
			// 让按钮知道JPanel像从它接收动作事件
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
		
		// 绘制按钮
		for (int i = 0; i < NUM_BUTTONS; i++) {
			buttons[i].paintComponent2D(bg);
		}
		g.drawImage(offscreen, 0, 0, this);
	} // 如果有动作从Button2D对象发出，把事件打印到控制台

	public void actionPerformed(ActionEvent e) {
		System.out.println(e);
	}
} // Button2DTest