package org.xf.app.scene.iso;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.VolatileImage;

import javax.swing.JPanel;

import org.xf.app.actor2D.AnimationStrip;

public class IsoTest extends JPanel implements Runnable {
	// 动画线程
	private Thread animation;
	private VolatileImage offscreen;
	// 一个等大场景
	private IsoScene scene;
	public IsoTest(){
		setBounds(0,0,550,500);
		init();
		
	}
	public void init() {
		// 初始化场景
		scene = new IsoScene(getBounds());
		
		// 创建IsoTileGroup
		IsoTileGroup tileGroup = new IsoTileGroup();
		tileGroup.init(this);
		
		int x = -getSize().width * 2 - (IsoTileGroup.TILE_WIDTH / 2);
		int y = -getSize().height * 2 - (IsoTileGroup.TILE_HEIGHT / 2);
		//System.out.println(x+"-"+y);
		int width = getSize().width * 4;
		int height = getSize().height * 4;
		
		// 用Iso砖块填充场景，注意offset标志是如何让其他的行偏移产生互锁的
		IsoTile tile;
		boolean offset = false;
		
		while (y < height) {
			
			
			while (x < width) {
				//if(y<=0)
				tile = new IsoTile(tileGroup);
				tile.setPos(x, y);
				//System.out.println(x+"*"+y);
				// 将砖块作为非参照物加进来
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
		// 创建一个IsoManGroup并创建一个IsoMan角色
		IsoManGroup group = new IsoManGroup();
		group.init(this);
		
		IsoMan isoMan = new IsoMan(group);
		isoMan.setPos(getSize().width / 2-isoMan.getWidth()/2, getSize().height / 2-isoMan.getHeight()/2);
		
		// 将isoman作为参照物添加到场景中
		scene.add(isoMan, true);
		
		setFocusable(true);
		// 注册场景让它接收键盘事件
		addKeyListener(scene);
		
		
		
		animation = new Thread(this);
		
		AnimationStrip.observer = this;
		//update();
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
		// 更新场景
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
		// 绘制场景
		scene.paint(bg);
		g.drawImage(offscreen, 0, 0, this);
	} // paint
} // IsoTest
