package org.xf.app.componentstest;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.xf.app.actor2D.AnimationStrip;
import org.xf.app.actor2D.Vector2D;
import org.xf.app.components.Label2D;

public class Label2DTest extends JPanel implements Runnable {
	// 动画线程
	private Thread animation;
	
	private BufferedImage offscreen;
	
	// 要绘制的标签数组
	private Label2D[] labels;
	
	// 将要用的各种字体的描述字符串
	private final String[] fonts = { "Helvetica", "Arial", "Courier",
			"Terminal", "Georgia" };
	public Label2DTest(){
		setBounds(0,0,400,400);
		setBorder(BorderFactory.createEtchedBorder());
		init();
	}

	// 方便创建TexturePaint对象使用的工具类
	private TexturePaint createTexturePaint(String filename) {
		
		filename="images/"+filename;
		
		MediaTracker mt = new MediaTracker(this);
		Image image = null;
		
		try {
			image = ImageIO.read(new File(filename));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		mt.addImage(image, 0);
		try {
			mt.waitForAll();
		} catch (InterruptedException e) { /* do nothing */
		}
		
		// 使用图像的宽和高创建一个新的BufferedImage
		BufferedImage bi = new BufferedImage(image.getWidth(this),
				image.getHeight(this), BufferedImage.TYPE_INT_RGB);
		
		// 得到BufferedImage的Graphics2D容易并在上面绘制 原来的图像
		((Graphics2D) bi.getGraphics()).drawImage(image, new AffineTransform(),
				this);
		
		// 为paint的图像创建边界矩形
		Rectangle bounds = new Rectangle(0, 0, bi.getWidth(), bi.getHeight());
		
		// 创建Paint
		return new TexturePaint(bi, bounds);
	}

	public void init() {
		labels = new Label2D[fonts.length];
		
		// 创建两个TexturePaint,一个用于可用状态，一个用于不可用状态
		TexturePaint tpEnabled = createTexturePaint("tile_A.png");
		TexturePaint tpDisabled = createTexturePaint("tile_B.png");
		
		// 创建标签
		for (int i = 0; i < fonts.length; i++) {
			
			// 用指定的字体、字体样式和可用paint创建一个Label2D
			labels[i] = new Label2D(new Font(fonts[i], Font.PLAIN, 50),
					fonts[i], tpEnabled);
			
			// 设置标签的位置
			labels[i].setPos(new Vector2D.Double(50, 50 + (i * 50)));
			
			// 设置标签的不可用paint
			labels[i].setDisabledPaint(tpDisabled);
		}
		offscreen = new BufferedImage(this.getSize().width,
				this.getSize().height, BufferedImage.TYPE_INT_RGB);
		
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
		
		long time = System.currentTimeMillis();
		Thread t = Thread.currentThread();
		
		while (t == animation) {
			repaint();
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				break;
			}
			// 每隔几秒反转一下标签的可用状态
			if (System.currentTimeMillis() - time > 5000) {
				
				for (int i = 0; i < fonts.length; i++) {
					labels[i].setEnabled(!labels[i].isEnabled());
				}
				
				time = System.currentTimeMillis();
			}
		}
	} // run


	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D bg = (Graphics2D) offscreen.getGraphics();
		bg.setPaint(Color.BLACK);
		bg.fillRect(0, 0, getSize().width, getSize().height);
		
		// 绘制标签
		for (int i = 0; i < fonts.length; i++) {
			labels[i].paintComponent2D(bg);
		}
		
		g.drawImage(offscreen, 0, 0, this);
	} // paint
} // Label2Dtest

