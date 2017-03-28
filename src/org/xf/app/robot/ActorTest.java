package org.xf.app.robot;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import org.xf.app.actor2D.AnimationStrip;
import org.xf.app.actor2D.ImageLoader;

public class ActorTest extends JPanel implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//动画线程
	private Thread animation;
	//屏外绘制缓冲
	private Image offscreen;
	//屏外平铺背景的Paint
	Paint paint;
	//填充背景的几何图形
	private Rectangle2D floor;
	//可以移动的机器人
	private Robot robot;
	
	public ActorTest(){
		setBounds(0, 0, 1000, 600);
			
		init();
		createPaint();
	}
	public void init(){
		System.out.print("2");
		//创建RobotGroup
		RobotGroup group = new RobotGroup();
		group.init(this);
		//设置Robot边界为窗体边界
		group.MIN_X_POS = 0;
		group.MIN_Y_POS = 0;
		
		group.MAX_X_POS = getSize().width;
		group.MAX_Y_POS = getSize().height;
		
		//在屏幕中间创建新的机器人
		robot = new Robot(group);
		
		robot.setPos((getSize().width-robot.getWidth())/2, (getSize().height-robot.getHeight())/2);
		
		//注册一个新的RobotAdapter来接收Robot移动指令
		//addKeyListener(new RobotAdapter(robot));
		//创建背景paint
		setFocusable(true);
		addKeyListener(new RobotAdapter(robot));
		createPaint();
			
		AnimationStrip.observer = this;
		animation = new Thread(this);
		
	
	}//init
	//创建一个平铺背景paint
	private void createPaint(){
		ImageLoader lp = new ImageLoader(this,"images/bg1.jpg",true);
		Image image = lp.getImage();
		while(image.getWidth(this)<=0);//为什么？？？
		{
			//用image的宽和高创建一个新的BufferImage
			BufferedImage bi = new BufferedImage(image.getWidth(this),image.getHeight(this),BufferedImage.TYPE_INT_RGB);
			//得到的BufferedImage的Graphics2D容器并绘制原始图像
			((Graphics2D)bi.getGraphics()).drawImage(image, new AffineTransform(),this);
			//Graphics2D
			//以图像大小创建任意矩形
			//image.
			floor = new Rectangle2D.Double(0,0,getSize().width,getSize().height);
			//设置paint
			paint = new TexturePaint(bi,new Rectangle(0,0,image.getWidth(this),image.getHeight(this)));
			
		}
		
	}
	public void start(){
		//启动动画线程
		animation.start();
	}
	public void stop(){
		animation = null;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Thread t = Thread.currentThread();
		while(t == animation){
			try{
				
				Thread.sleep(10);
			}
			catch(InterruptedException e){
				e.printStackTrace();
				break;
			}
			repaint();
		}
	}//run
	
	public void update(){
		robot.update();
	}
	public void paintComponent(Graphics g){
		update();
		super.paintComponent(g);
		//System.out.print("1");
		offscreen = this.createImage(getSize().width, getSize().height);
			//	System.out.print("1");
		Graphics2D bg=(Graphics2D)offscreen.getGraphics();
		
		//设置paint并填充背景
		bg.setPaint(paint);
		bg.fill(floor);
		//绘制机器人
		robot.paint(bg);
		
		//在窗体上绘制屏外图形
		g.drawImage(offscreen, 0, 0, this);
	
	}
}//ActorTest
