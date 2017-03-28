package org.xf.app.containterstest;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Stack;

import javax.swing.JPanel;

import org.xf.app.actor2D.AnimationStrip;
import org.xf.app.actor2D.ImageLoader;
import org.xf.app.actor2D.Vector2D;
import org.xf.app.components.Button2D;
import org.xf.app.components.ButtonImageGroup;
import org.xf.app.components.Label2D;
import org.xf.app.containters.Menu2D;

public class Menu2DTest extends JPanel implements Runnable, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// �����߳�
	private Thread animation;
	private BufferedImage offscreen;
	
	//����
	private Menu2D menuA;
	private Menu2D menuB;
	private Menu2D menuC;
	
	// ��������Ĳ˵��ϵİ�ť
	private Button2D goToA;
	private Button2D goToB;
	private Button2D goToC;
	
	//���ɲ˵���ջ
	private Stack menuStack;
	
	public Menu2DTest(){
		setBounds(0,0,400,400);
		init();
	}
	public void init() {
		// ���һ���ʼ������
		offscreen = new BufferedImage(this.getSize().width,
				this.getSize().height, BufferedImage.TYPE_INT_RGB);
	
		AnimationStrip.observer = this;
		
		// ��ȡһ��Graphics2D������ʹ��ǩ���ж���
		Graphics2D g2d = (Graphics2D) offscreen.getGraphics();
		
		// Ϊ�˵���ť����һ��ͼ����
		ButtonImageGroup group = new ButtonImageGroup(3, "images/bg1.jpg");
		group.init(this);
		
		// ���õ�����ť
		Label2D label;
		Font font = new Font("Helvetica", Font.PLAIN, 18);
		label = new Label2D(font, "Menu A", Color.BLACK);
		label.setDisabledPaint(Color.WHITE);
		goToA = new Button2D(label, group);
		label.centerOn(goToA.getBounds(), g2d);
		addMouseListener(goToA);
		addMouseMotionListener(goToA);
		
		label = new Label2D(font, "Menu B", Color.BLACK);
		label.setDisabledPaint(Color.WHITE);
		goToB = new Button2D(label, group);
		label.centerOn(goToB.getBounds(), g2d);
		addMouseListener(goToB);
		addMouseMotionListener(goToB);
		
		label = new Label2D(font, "Menu C", Color.BLACK);
		label.setDisabledPaint(Color.WHITE);
		goToC = new Button2D(label, group);
		label.centerOn(goToC.getBounds(), g2d);
		addMouseListener(goToC);
		addMouseMotionListener(goToC);
		
		// ������������
		ActionListener listener = new ActionListener() {
			
			// �����ĸ���ť�����¶��Ѻ��ʵĲ˵��ŵ�ջ��
			// button was pressed
			public void actionPerformed(ActionEvent e) {
				if (goToA == e.getSource()) {
					
					// ��ʾ�˵�A
					menuStack.push(menuA);
					updateButtonSettings();
				} else if (goToB == e.getSource()) {
					
					// ��ʾ�˵�B
					menuStack.push(menuB);
					updateButtonSettings();
				} else if (goToC == e.getSource()) {
					
					// ��ʾ�˵�C
					menuStack.push(menuC);
					updateButtonSettings();
				}
			}
		};
		//ע��JPanel�������հ�ť�������Ķ����¼�
		goToA.addActionListener(listener);
		goToB.addActionListener(listener);
		goToC.addActionListener(listener);
		
		// �����Զ���˵�
		Label2D header;
		final Vector2D pos = new Vector2D.Double(100, 50);
		menuA = new Menu2D(new ImageLoader(this, "images/tile_D.png", true).getImage(),
				pos, false);
		header = new Label2D(font, "Menu A", Color.BLACK);
		menuA.add(header, 0, 0);
		header.centerOn(menuA.getBounds(), g2d);
		header.setY(menuA.getY() + header.getBounds().getHeight() + 5);
		menuA.add(goToA, 78, 50);
		menuA.add(goToB, 78, 120);
		menuA.add(goToC, 78, 190);
		
		menuB = new Menu2D(new ImageLoader(this, "images/tile_H.png", true).getImage(),
				pos, true);
		header = new Label2D(font, "Menu B", Color.BLACK);
		menuB.add(header, 0, 0);
		header.centerOn(menuB.getBounds(), g2d);
		header.setY(menuB.getY() + header.getBounds().getHeight() + 5);
		menuB.add(goToA, 78, 50);
		menuB.add(goToB, 78, 120);
		menuB.add(goToC, 78, 190);
		
		menuC = new Menu2D(new ImageLoader(this, "images/tile_G.png", true).getImage(),
				pos, false);
		header = new Label2D(font, "Menu C", Color.BLACK);
		menuC.add(header, 0, 0);
		header.centerOn(menuC.getBounds(), g2d);
		header.setY(menuC.getY() + header.getBounds().getHeight() + 5);
		menuC.add(goToA, 78, 50);
		menuC.add(goToB, 78, 120);
		menuC.add(goToC, 78, 190);
		
		goToA.centerLabel(g2d);
		goToB.centerLabel(g2d);
		goToC.centerLabel(g2d);
		
		// �����˵���
		menuStack = new Stack();
		goToA.setEnabled(false);
		goToB.setEnabled(true);
		goToC.setEnabled(true);
		menuStack.push(menuA);
		
		addKeyListener(this);
		
	} // init
	
		//��ֹ��Щ����õ�ǰ��˵��İ�ť 
		//

	private void updateButtonSettings() {
		if (menuStack.empty())
			return;
		Menu2D menu = (Menu2D) menuStack.peek();
		if (menuA == menu) {
			
			goToA.setEnabled(false);
			goToB.setEnabled(true);
			goToC.setEnabled(true);
			
		} else if (menuB == menu) {
			
			goToA.setEnabled(true);
			goToB.setEnabled(false);
			goToC.setEnabled(true);
			
		} else if (menuC == menu) {
			
			goToA.setEnabled(true);
			goToB.setEnabled(true);
			goToC.setEnabled(false);
			
		}
	}

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



	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D bg = (Graphics2D) offscreen.getGraphics();
		bg.setPaint(Color.BLACK);
		bg.fillRect(0, 0, getSize().width, getSize().height);
		
		// ȷ�����ǵ��ı�����������
		bg.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		// ���ջ��Ϊ�գ������ϵĲ˵�
		if (!menuStack.empty()) {
			
			// ֻ���ڶ��㰴ť��һ�����ǲ˵�ʱ�Ż��ƻ���
			if (((Menu2D) menuStack.peek()).isOverlay()) {
				paintScene(bg);
			}
			
			// �����Ļ�����ɺ���Ʋ˵�
			((Menu2D) menuStack.peek()).paintComponent2D(bg);
		}
		else
		// ���ջΪ�գ����ճ����ƻ���
		{
			paintScene(bg);
		}
		g.drawImage(offscreen, 0, 0, this);
	} // paint

	public void paintScene(Graphics2D g2d) {
		
		// ����һ��ʵ�ʵĻ��棬����һ�������paint����Ϊռλ��
		g2d.setPaint(new GradientPaint(0.0f, 0.0f, Color.BLACK,
				(float) getSize().width, (float) getSize().height, Color.WHITE));
		g2d.fillRect(0, 0, getSize().width, getSize().height);
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	// ���²˵�ջ��˳��
	public void keyTyped(KeyEvent e) {
		// �˸����Ʋ˵�˳��
		if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
			// ����˵�ջ����գ���ɾ������Ԫ��
			if (!menuStack.empty()) {
				menuStack.pop();
				updateButtonSettings();
			}
			// ����Ѳ˵�A�ŵ�ջ��
			else {
				menuStack.push(menuA);
				updateButtonSettings();
			}
		}
	}
} // Menu2DTest