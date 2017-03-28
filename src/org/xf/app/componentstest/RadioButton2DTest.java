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
	
	// 是否禁用超级能量列表的单选按钮
	private RadioButton2D singleRB;
	
	// 跟踪超级能量是否可用
	private boolean powersEnabled;
	
	//放置超级能量列表的按钮组
	private RadioButtonGroup rbGroup;
	
	// 可用的超级能量的字符串描述
	private final String[] POWERS = { "Fireball", "Super Kick", "Acid Storm",
			"Razor Talons", "Electroshock" };
	private final int NUM_BUTTONS = POWERS.length;
	
	// 描述未选择的状态
	private Label2D status;
	public RadioButton2DTest(){
		setBounds(0,0,400,400);
		init();
	}
	public void init() {
		offscreen = new BufferedImage(this.getSize().width,
				this.getSize().height, BufferedImage.TYPE_INT_RGB);
		
		// 为按钮创建一个按钮组
		ButtonImageGroup group = new ButtonImageGroup(2, "images/bg4.jpg");
		group.init(this);
		Label2D label;
		Font font = new Font("Helvetica", Font.PLAIN, 18);
		
		// 创建切换超级能量是否可用的单选按钮
		label = new Label2D(font, "Enable Superpowers", Color.WHITE);
		singleRB = new RadioButton2D(label, group, null, new Vector2D.Double(
				50, 50));
		
		label.centerOn(singleRB.getBounds(), (Graphics2D) offscreen.getGraphics());
		label.setX(singleRB.getBounds().getX()
				+ singleRB.getBounds().getWidth() + 5);
		singleRB.setSelected(true);
		powersEnabled = singleRB.isSelected();
		addMouseListener(singleRB);
		
		// 创建容纳不同能量的单选按钮
		RadioButton2D rb;
		rbGroup = new RadioButtonGroup();
		for (int i = 0; i < NUM_BUTTONS; i++) {
			label = new Label2D(font, POWERS[i], Color.WHITE);
			rb = new RadioButton2D(label, group, rbGroup, new Vector2D.Double(
					100, 100 + (i * 35)));
			label.centerOn(rb.getBounds(), (Graphics2D) offscreen .getGraphics());
			label.setX(rb.getBounds().getX() + rb.getBounds().getWidth() + 5);
			addMouseListener(rb);
			
			//默认情况下，第0个按钮应该被选中
			if (i == 0)
				rb.setSelected(true);
		}
		
		// 用初始的空白字符串创建状态标签
		status = new Label2D(font, "", new Color(0, 255, 255));
		status.setPos(new Vector2D.Double(50, 325));
		
		AnimationStrip.observer = this;
	} // init

	public void start() {
		
		// 启动动画线程
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
		
		// 如果独立的那个选项改变，则切换能量选项组的状态
		// group
		if (powersEnabled != singleRB.isSelected()) {
			powersEnabled = singleRB.isSelected();
			rbGroup.setVisible(powersEnabled);
		}
		
		// 更新标签的描述
		
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
		
		// 绘制单独的那个按钮
		singleRB.paintComponent2D(bg);
		
		// 绘制按钮组
		rbGroup.paint(bg);
		
		// 绘制状态标签
		status.paintComponent2D(bg);
		g.drawImage(offscreen, 0, 0, this);
	} // paint
} // RadioButton2DTest