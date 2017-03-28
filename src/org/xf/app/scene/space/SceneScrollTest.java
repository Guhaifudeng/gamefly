package org.xf.app.scene.space;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.VolatileImage;

import javax.swing.JPanel;

import org.xf.app.actor2D.AnimationStrip;

public class SceneScrollTest extends JPanel implements Runnable {
	// �����߳�
	private Thread animation;
	// �������ͼ��
	private VolatileImage offscreen;
	// һ���ɹ�������ĳ���
	private SpaceScene scene;

	public SceneScrollTest() {
		setBounds(0,0,520,800);
		init();
	}

	public void init() {
		// �����JPanel�����С��������
		scene = new SpaceScene(getBounds());
		//  "����" "ǰ��" ����ͼ��
		StaticActor[] actors = new StaticActor[2];
		StaticActorGroup group;
		//����ǰ��
		group = new StaticActorGroup("images/tile_A.png");
		group.init(this);
		actors[1] = new StaticActor(group);
		actors[1]
				.setPos(0.0, (double) getSize().height - actors[1].getHeight());
		actors[1].setVel(-3, 0);
		// ��������
		group = new StaticActorGroup("images/bg1.jpg");
		group.init(this);
		actors[0] = new StaticActor(group);
		actors[0]
				.setPos(0.0, (double) getSize().height - actors[0].getHeight());
		actors[0].setVel(-1, 0);
		
		
		
		// ����scenery
		scene.setScenery(actors);
		animation = new Thread(this);
		AnimationStrip.observer = this;
	} // init

	public void start() {
		// ���������߳�
		animation.start();
	}

	public void stop() {
		animation = null;
	}

	// ִ��һ����׼����ѭ��
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
		
	//�ó���ִ������update����
	public void update() {
		scene.update();
		//paint(g); ��JPanel��һ���ĵط�
	}

	// ���Ƴ���
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

