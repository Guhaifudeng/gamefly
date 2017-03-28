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
	//�����߳�
	private Thread animation;
	//������ƻ���
	private Image offscreen;
	//����ƽ�̱�����Paint
	Paint paint;
	//��䱳���ļ���ͼ��
	private Rectangle2D floor;
	//�����ƶ��Ļ�����
	private Robot robot;
	
	public ActorTest(){
		setBounds(0, 0, 1000, 600);
			
		init();
		createPaint();
	}
	public void init(){
		System.out.print("2");
		//����RobotGroup
		RobotGroup group = new RobotGroup();
		group.init(this);
		//����Robot�߽�Ϊ����߽�
		group.MIN_X_POS = 0;
		group.MIN_Y_POS = 0;
		
		group.MAX_X_POS = getSize().width;
		group.MAX_Y_POS = getSize().height;
		
		//����Ļ�м䴴���µĻ�����
		robot = new Robot(group);
		
		robot.setPos((getSize().width-robot.getWidth())/2, (getSize().height-robot.getHeight())/2);
		
		//ע��һ���µ�RobotAdapter������Robot�ƶ�ָ��
		//addKeyListener(new RobotAdapter(robot));
		//��������paint
		setFocusable(true);
		addKeyListener(new RobotAdapter(robot));
		createPaint();
			
		AnimationStrip.observer = this;
		animation = new Thread(this);
		
	
	}//init
	//����һ��ƽ�̱���paint
	private void createPaint(){
		ImageLoader lp = new ImageLoader(this,"images/bg1.jpg",true);
		Image image = lp.getImage();
		while(image.getWidth(this)<=0);//Ϊʲô������
		{
			//��image�Ŀ�͸ߴ���һ���µ�BufferImage
			BufferedImage bi = new BufferedImage(image.getWidth(this),image.getHeight(this),BufferedImage.TYPE_INT_RGB);
			//�õ���BufferedImage��Graphics2D����������ԭʼͼ��
			((Graphics2D)bi.getGraphics()).drawImage(image, new AffineTransform(),this);
			//Graphics2D
			//��ͼ���С�����������
			//image.
			floor = new Rectangle2D.Double(0,0,getSize().width,getSize().height);
			//����paint
			paint = new TexturePaint(bi,new Rectangle(0,0,image.getWidth(this),image.getHeight(this)));
			
		}
		
	}
	public void start(){
		//���������߳�
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
		
		//����paint����䱳��
		bg.setPaint(paint);
		bg.fill(floor);
		//���ƻ�����
		robot.paint(bg);
		
		//�ڴ����ϻ�������ͼ��
		g.drawImage(offscreen, 0, 0, this);
	
	}
}//ActorTest
