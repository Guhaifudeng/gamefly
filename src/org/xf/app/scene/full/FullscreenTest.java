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

	//�������ƴ���frame����ȫ��frame
	protected JFrame frame;

	//ͼ�λ������õ�ͼ���豸
	protected GraphicsDevice device;
	
	//��־�Ǵ���ģʽ����ȫ��ģʽ
	protected boolean frameWindowed;

	//frame ��С
	protected Rectangle bounds;
	//���巽ʽ���Ƶ�����ͼ��
	protected Image offscreen;
	//����ѡ�����ô���ģʽ����ȫ��ģʽ
	protected JButton windowed;

	protected JButton fullscreen;
	protected JLabel desc;
	//Actor2D��������
	protected Actor2D[] actors;
	//�����߳�
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
		//����JPanel�Ŀ��ӻ����
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
		//����frame�Ŀ��ӻ����
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
		
		//Ϊ��������һ���µ��߳�
		animation = new Thread(this);
		offscreen = createVolatileImage(this.getSize().width,
				this.getSize().height);
	}

	public void stop() {
		animation = null;
	}

	public void run(){
		//ȫ��ģʽ�Ĵ���
		if(!frameWindowed){
			//����һ������������
			frame.createBufferStrategy(3);
			Thread x = Thread.currentThread();
			while(x == animation && frame.isShowing()){
				BufferStrategy bufferStrategy = frame.getBufferStrategy();
				
				//���²�����frame
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
		}else{//����ģʽ�Ĵ���
			Graphics2D g2d;
			Thread x = Thread.currentThread();
			while(x == animation && frame.isVisible()){
				
			
				g2d = (Graphics2D) offscreen.getGraphics();
				//���²�����frame
				
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
				floor = new Rectangle2D.Double(0,0,frame.getSize().width,frame.getSize().height);
				//����paint
				paint = new TexturePaint(bi,new Rectangle(0,0,image.getWidth(this),image.getHeight(this)));
				
			}
			
		}
	
	//����һ֡�����������Graphics2D�������Խ��д���ģʽ����
	//Ҳ�ɽ���ȫ��ģʽ����
	protected void paintFrame(Graphics2D g2d){
		
		g2d.setPaint(paint);
		g2d.fill(floor);
		for(int i =0;i<10;i++){
			actors[i].paint(g2d);
		}
	}
	//�Դ������ȫ��ģʽ��frame
	protected void openFrame(){
		//��ȫ��ģʽ�򿪴���Ĵ���
		if(!frameWindowed){
			try{
				//��ͼ�λ����еõ�Ĭ�ϵ�ͼ���豸
				device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
				
				//ʹ��ͼ���豸����һ��JFrame
				frame = new JFrame(device.getDefaultConfiguration());
				
				//��Ҫ����frame������
				frame.setUndecorated(true);
				//���ڻ������������еģ����к��Բ���ϵͳ�����Ļ�������
				frame.setIgnoreRepaint(true);
				//����ȫ������
				device.setFullScreenWindow(frame);
				//���֧�֣����������С
				if(device.isDisplayChangeSupported()){
					//�ڻָ�����ģʽ֮ǰ���Լ�����ʾģʽ
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
				//��������
				animation.start();
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{//����ģʽ
			//����һ����С800x600���صı�׼JFrame
			
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
			//��������
			animation.start();
		}
	}
	//�жϵ�ǰ��ͼ���豸�Ƿ�֧�����������ʾģʽ
	private boolean displayModeSupported(int width,int height,int bitDepth){
		DisplayMode[] modes = device.getDisplayModes();
		for(int i =0;i<modes.length;i++){
			if(width == modes[i].getWidth()&&
					height == modes[i].getHeight()&&
					bitDepth == modes[i].getBitDepth()){
				return true;
			}
		}
		//�Ҳ������ݵ���ʾģʽ
		return false;
	}
	
	
	
	//
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	//����ѡ��ť������
	@Override
	public void mouseMoved(MouseEvent e) {
		if(windowed == e.getSource()){
			desc.setText("Enter Windowed mode");
		}else if( fullscreen == e.getSource()){
			desc.setText("Enter fullscreen mode");
		}

	}
	//��ȫ�����ߴ���ģʽ��frame
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
