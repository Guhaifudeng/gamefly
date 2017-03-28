package org.xf.app.scene.wrap;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.xf.app.actor2D.Actor2D;
import org.xf.app.actor2D.AnimationStrip;
import org.xf.app.scene.space.StaticActor;
import org.xf.app.scene.space.StaticActorGroup;

public class WrapTest extends JPanel implements Runnable {
	// 动画线程
	private Thread animation;
	private BufferedImage offscreen;
	private WrapScene wrapscene;
	// 跟踪飞船的加速
	private Actor2D ship;
	
	public WrapTest(){
		setBounds(0,0,800,600);
		init();
	}
	public void init() {
		// 创建飞船和它的角色组
		StaticActorGroup group = new StaticActorGroup("images/myplan_1.png");
		group.init(this);

		ship = new StaticActor(group);

		wrapscene = new WrapScene(this.getBounds(), this);
		setFocusable(true);
		addKeyListener(wrapscene);
		wrapscene.add(ship);
		offscreen = new BufferedImage(this.getSize().width,
				this.getSize().height, BufferedImage.TYPE_INT_RGB);

		animation = new Thread(this);
		AnimationStrip.observer = this;

	} // init

	public void start() {
		// 启动动画线程
		animation.start();
	}

	public void stop() {
		animation = null;
	}

	public void run() {
		Thread t = Thread.currentThread();
		while (t == animation) {
			update();
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				break;
			}
		}
	} // run

	public void update() {
		//更新场景
		wrapscene.update();
	}
	
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D bg = (Graphics2D) offscreen.getGraphics();
		bg.setBackground(Color.BLACK);
		bg.clearRect(0, 0, getSize().width, getSize().height);

		// 绘制场景
		wrapscene.paint(bg);

		g.drawImage(offscreen, 0, 0, this);
	} // paint
}// WrapTest
