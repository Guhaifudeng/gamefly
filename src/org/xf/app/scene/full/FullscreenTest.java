package org.xf.app.scene.full;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.xf.app.actor2D.Actor2D;
import org.xf.app.actor2D.Actor2DGroup;
import org.xf.app.actor2D.ImageLoader;
import org.xf.app.robot.Robot;
import org.xf.app.robot.RobotGroup;

public class FullscreenTest extends JPanel implements Runnable, ActionListener,
		MouseMotionListener {

	//用来绘制窗体frame或者全屏frame
	protected JFrame frame;

	//图形环境可用的图像设备
	protected GraphicsDevice device;
	
	//标志是窗体模式还是全屏模式
	protected boolean frameWindowed;

	//frame 大小
	protected Rectangle bounds;
	//窗体方式绘制的屏外图像
	protected Image offscreen;
	//用来选择是用窗体模式还是全屏模式
	protected JButton windowed;

	protected JButton fullscreen;
	protected JLabel desc;
	//Actor2D对象数组
	protected Actor2D[] actors;
	//动画线程
	protected Thread animation;

	private Double floor;

	private TexturePaint paint;
	private JFrame thisJ;
	public FullscreenTest(JFrame thisJ){
		this.thisJ =thisJ;
		setBounds(0,0,400,200);
		
		init();
	}
	public void init() {
		//创建JPanel的可视化组件
		JPanel p;
		setLayout(new BorderLayout());
		p = new JPanel();
		p.add(new JLabel("choose your destiny!"));
		add(p, BorderLayout.NORTH);

		p = new JPanel();
		windowed = new JButton("frame Windowed");
		windowed.addActionListener(this);
		windowed.addMouseMotionListener(this);
		p.add(windowed);

		fullscreen = new JButton("I want the Red pill");
		fullscreen.addActionListener(this);
		fullscreen.addMouseMotionListener(this);
		p.add(fullscreen);

		add(p, BorderLayout.CENTER);

		p = new JPanel();
		desc = new JLabel("                 ");
		p.add(desc);
		add(p, BorderLayout.SOUTH);
		//设置frame的可视化组件
		Actor2DGroup group = new RobotGroup();

		group.init(this);
		group.MIN_X_POS = 0;
		group.MIN_Y_POS = 0;
		group.MAX_X_POS = 800;
		group.MAX_Y_POS = 600;

		Random random = new Random();
		actors = new Robot[10];
		for (int i = 0; i < 10; i++) {
			actors[i] = new Robot(group);
			actors[i].setPos(Math.abs(random.nextInt(800)),
					Math.abs(random.nextInt(600)));

		}

		bounds = null;
		
		//为动画创建一个新的线程
		animation = new Thread(this);
		offscreen = createVolatileImage(this.getSize().width,
				this.getSize().height);
	}

	public void stop() {
		animation = null;
	}

	public void run(){
		//全屏模式的代码
		if(!frameWindowed){
			//创建一个链交换策略
			frame.createBufferStrategy(3);
			Thread x = Thread.currentThread();
			while(x == animation && frame.isShowing()){
				BufferStrategy bufferStrategy = frame.getBufferStrategy();
				
				//更新并绘制frame
				do{
					Graphics2D g2d = (Graphics2D) bufferStrategy.getDrawGraphics();
					for(int i= 0;i<10;i++){
						actors[i].update();
					}
					createPaint();
					paintFrame(g2d);
					bufferStrategy.show();
					g2d.dispose();
					
				
				}while(bufferStrategy.contentsLost());
				try{
					Thread.sleep(10);
				}
				catch(InterruptedException e){
					break;
				}
			}
		}else{//窗体模式的代码
			Graphics2D g2d;
			Thread x = Thread.currentThread();
			while(x == animation && frame.isVisible()){
				
			
				g2d = (Graphics2D) offscreen.getGraphics();
				//更新并绘制frame
				
					for(int i= 0;i<10;i++){
						actors[i].update();
					}
					createPaint();
					paintFrame(g2d);
					g2d.dispose();
					frame.getGraphics().drawImage(offscreen,0,0,this);
				
					try{
						Thread.sleep(10);
					}
					catch(InterruptedException e){
						break;
					}
				}
				
			}
		}
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
				floor = new Rectangle2D.Double(0,0,frame.getSize().width,frame.getSize().height);
				//设置paint
				paint = new TexturePaint(bi,new Rectangle(0,0,image.getWidth(this),image.getHeight(this)));
				
			}
			
		}
	
	//绘制一帧动画，传入的Graphics2D容器可以进行窗体模式绘制
	//也可进行全屏模式绘制
	protected void paintFrame(Graphics2D g2d){
		
		g2d.setPaint(paint);
		g2d.fill(floor);
		for(int i =0;i<10;i++){
			actors[i].paint(g2d);
		}
	}
	//以窗体或者全屏模式打开frame
	protected void openFrame(){
		//用全屏模式打开窗体的代码
		if(!frameWindowed){
			try{
				//从图形环境中得到默认的图形设备
				device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
				
				//使用图形设备创建一个JFrame
				frame = new JFrame(device.getDefaultConfiguration());
				
				//不要绘制frame的修饰
				frame.setUndecorated(true);
				//由于绘制是主动进行的，所有忽略操作系统发来的绘制请求
				frame.setIgnoreRepaint(true);
				//创建全屏窗口
				device.setFullScreenWindow(frame);
				//如果支持，调整窗体大小
				if(device.isDisplayChangeSupported()){
					//在恢复窗体模式之前尝试几种显示模式
					if(displayModeSupported(800,600,32)){
						device.setDisplayMode(new DisplayMode(800,600,32,0));
					}
					else if(displayModeSupported(800,600,16)){
						device.setDisplayMode(new DisplayMode(800,600,16,0));}
					
						else if (displayModeSupported(640,480,16)){
							device.setDisplayMode(new DisplayMode(640,480,16,0));
						}
			}
				frame.addKeyListener(new KeyListener(){
				

					@Override
					public void keyTyped(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void keyPressed(KeyEvent e) {
						// TODO Auto-generated method stub
						frame.setVisible(false);
						frame.dispose();
					}

					@Override
					public void keyReleased(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
		
				bounds = frame.getBounds();
				//启动动画
				animation.start();
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{//窗体模式
			//创建一个大小800x600像素的标准JFrame
			
			offscreen = createImage(800,600);
			frame = new JFrame();
			frame.setSize(800,600);
			frame.setVisible(true);
			frame.setResizable(false);
			frame.addKeyListener(new KeyAdapter(){
				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					//if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
					//System.out.print("1");
					//frame.setVisible(false);
					//frame.dispose();
					
				}
			});

			frame.addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					//frame.hide();
					frame.setVisible(false);
					frame.dispose();
					//FullscreenTest.this.thisJ.repaint();
				}
			});
			bounds = frame.getBounds();
			//启动动画
			animation.start();
		}
	}
	//判断当前的图形设备是否支持所传入的显示模式
	private boolean displayModeSupported(int width,int height,int bitDepth){
		DisplayMode[] modes = device.getDisplayModes();
		for(int i =0;i<modes.length;i++){
			if(width == modes[i].getWidth()&&
					height == modes[i].getHeight()&&
					bitDepth == modes[i].getBitDepth()){
				return true;
			}
		}
		//找不到兼容的显示模式
		return false;
	}
	
	
	
	//
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	//更新选择按钮的描述
	@Override
	public void mouseMoved(MouseEvent e) {
		if(windowed == e.getSource()){
			desc.setText("Enter Windowed mode");
		}else if( fullscreen == e.getSource()){
			desc.setText("Enter fullscreen mode");
		}

	}
	//以全屏或者窗体模式打开frame
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(windowed == e.getSource()){
			frameWindowed= true;
			windowed.setEnabled(false);
			fullscreen.setEnabled(false);
			openFrame();
		}else if(fullscreen == e.getSource()){
			frameWindowed = false;
			windowed.setEnabled(false);
			fullscreen.setEnabled(false);
			openFrame();
		}
	}

}//FullscreenTest
