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
	// �����߳�
	private Thread animation;
	
	private BufferedImage offscreen;
	
	// Ҫ���Ƶı�ǩ����
	private Label2D[] labels;
	
	// ��Ҫ�õĸ�������������ַ���
	private final String[] fonts = { "Helvetica", "Arial", "Courier",
			"Terminal", "Georgia" };
	public Label2DTest(){
		setBounds(0,0,400,400);
		setBorder(BorderFactory.createEtchedBorder());
		init();
	}

	// ���㴴��TexturePaint����ʹ�õĹ�����
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
		
		// ʹ��ͼ��Ŀ�͸ߴ���һ���µ�BufferedImage
		BufferedImage bi = new BufferedImage(image.getWidth(this),
				image.getHeight(this), BufferedImage.TYPE_INT_RGB);
		
		// �õ�BufferedImage��Graphics2D���ײ���������� ԭ����ͼ��
		((Graphics2D) bi.getGraphics()).drawImage(image, new AffineTransform(),
				this);
		
		// Ϊpaint��ͼ�񴴽��߽����
		Rectangle bounds = new Rectangle(0, 0, bi.getWidth(), bi.getHeight());
		
		// ����Paint
		return new TexturePaint(bi, bounds);
	}

	public void init() {
		labels = new Label2D[fonts.length];
		
		// ��������TexturePaint,һ�����ڿ���״̬��һ�����ڲ�����״̬
		TexturePaint tpEnabled = createTexturePaint("tile_A.png");
		TexturePaint tpDisabled = createTexturePaint("tile_B.png");
		
		// ������ǩ
		for (int i = 0; i < fonts.length; i++) {
			
			// ��ָ�������塢������ʽ�Ϳ���paint����һ��Label2D
			labels[i] = new Label2D(new Font(fonts[i], Font.PLAIN, 50),
					fonts[i], tpEnabled);
			
			// ���ñ�ǩ��λ��
			labels[i].setPos(new Vector2D.Double(50, 50 + (i * 50)));
			
			// ���ñ�ǩ�Ĳ�����paint
			labels[i].setDisabledPaint(tpDisabled);
		}
		offscreen = new BufferedImage(this.getSize().width,
				this.getSize().height, BufferedImage.TYPE_INT_RGB);
		
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
		
		long time = System.currentTimeMillis();
		Thread t = Thread.currentThread();
		
		while (t == animation) {
			repaint();
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				break;
			}
			// ÿ�����뷴תһ�±�ǩ�Ŀ���״̬
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
		
		// ���Ʊ�ǩ
		for (int i = 0; i < fonts.length; i++) {
			labels[i].paintComponent2D(bg);
		}
		
		g.drawImage(offscreen, 0, 0, this);
	} // paint
} // Label2Dtest

