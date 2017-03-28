package org.xf.app.scene.iso;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.VolatileImage;

import javax.swing.JPanel;

import org.xf.app.actor2D.AnimationStrip;

public class IsoTest extends JPanel implements Runnable {
	// �����߳�
	private Thread animation;
	private VolatileImage offscreen;
	// һ���ȴ󳡾�
	private IsoScene scene;
	public IsoTest(){
		setBounds(0,0,550,500);
		init();
		
	}
	public void init() {
		// ��ʼ������
		scene = new IsoScene(getBounds());
		
		// ����IsoTileGroup
		IsoTileGroup tileGroup = new IsoTileGroup();
		tileGroup.init(this);
		
		int x = -getSize().width * 2 - (IsoTileGroup.TILE_WIDTH / 2);
		int y = -getSize().height * 2 - (IsoTileGroup.TILE_HEIGHT / 2);
		//System.out.println(x+"-"+y);
		int width = getSize().width * 4;
		int height = getSize().height * 4;
		
		// ��Isoש����䳡����ע��offset��־���������������ƫ�Ʋ���������
		IsoTile tile;
		boolean offset = false;
		
		while (y < height) {
			
			
			while (x < width) {
				//if(y<=0)
				tile = new IsoTile(tileGroup);
				tile.setPos(x, y);
				//System.out.println(x+"*"+y);
				// ��ש����Ϊ�ǲ�����ӽ���
				scene.add(tile, false);
				x += IsoTileGroup.TILE_WIDTH;
			}
			
			offset = !offset;
			if (offset)
				x = -getSize().width * 2;
			else
				x = -getSize().width * 2 - (IsoTileGroup.TILE_WIDTH / 2);
			y += IsoTileGroup.TILE_HEIGHT / 2;
		}
		// ����һ��IsoManGroup������һ��IsoMan��ɫ
		IsoManGroup group = new IsoManGroup();
		group.init(this);
		
		IsoMan isoMan = new IsoMan(group);
		isoMan.setPos(getSize().width / 2-isoMan.getWidth()/2, getSize().height / 2-isoMan.getHeight()/2);
		
		// ��isoman��Ϊ��������ӵ�������
		scene.add(isoMan, true);
		
		setFocusable(true);
		// ע�᳡���������ռ����¼�
		addKeyListener(scene);
		
		
		
		animation = new Thread(this);
		
		AnimationStrip.observer = this;
		//update();
	} // init

	public void start() {
		// ���������߳�
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
		// ���³���
		scene.update();
		// paint(g);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		offscreen = createVolatileImage(this.getSize().width,
				this.getSize().height);
		Graphics2D bg = (Graphics2D) offscreen.getGraphics();
		bg.setPaint(Color.WHITE);
		bg.clearRect(0, 0, getSize().width, getSize().height);
		// ���Ƴ���
		scene.paint(bg);
		g.drawImage(offscreen, 0, 0, this);
	} // paint
} // IsoTest
