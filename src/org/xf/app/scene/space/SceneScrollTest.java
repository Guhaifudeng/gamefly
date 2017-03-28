package org.xf.app.scene.space;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.VolatileImage;

import javax.swing.JPanel;

import org.xf.app.actor2D.AnimationStrip;

public class SceneScrollTest extends JPanel implements Runnable {
	// 动画线程
	private Thread animation;
	// 屏外绘制图像
	private VolatileImage offscreen;
	// 一个可滚动物体的场景
	private SpaceScene scene;

	public SceneScrollTest() {
		setBounds(0,0,520,800);
		init();
	}

	public void init() {
		// 用这个JPanel窗体大小创建场景
		scene = new SpaceScene(getBounds());
		//  "背景" "前景" 场景图像
		StaticActor[] actors = new StaticActor[2];
		StaticActorGroup group;
		//创建前景
		group = new StaticActorGroup("images/tile_A.png");
		group.init(this);
		actors[1] = new StaticActor(group);
		actors[1]
				.setPos(0.0, (double) getSize().height - actors[1].getHeight());
		actors[1].setVel(-3, 0);
		// 创建背景
		group = new StaticActorGroup("images/bg1.jpg");
		group.init(this);
		actors[0] = new StaticActor(group);
		actors[0]
				.setPos(0.0, (double) getSize().height - actors[0].getHeight());
		actors[0].setVel(-1, 0);
		
		
		
		// 设置scenery
		scene.setScenery(actors);
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

	// 执行一个标准绘制循环
	public void run() {
		Thread t = Thread.currentThread();
		while (t == animation) {
			try {
				update();
				Thread.sleep(10);
			} catch (InterruptedException e) {
				break;
			}
			repaint();
		}
	} // run
		
	//让场景执行它的update方法
	public void update() {
		scene.update();
		//paint(g); 和JPanel不一样的地方
	}

	// 绘制场景
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		offscreen = createVolatileImage(getSize().width, getSize().height);
		
		Graphics2D bg = (Graphics2D) offscreen.getGraphics();
		bg.setPaint(Color.BLACK);
		bg.fill(getBounds());
		scene.paint(bg);
		g.drawImage(offscreen, 0, 0, this);
	}
} // SceneScrollTes

