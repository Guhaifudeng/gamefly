package org.xf.app.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

import javax.swing.JPanel;

import org.xf.app.actor2D.Vector2D;

public class Vector2DTestPanel extends JPanel implements Runnable{
	/**
	 * 
	 */
	Vector2DTestPanel(){
		setBounds(0, 0, 400, 400);
		init();
		//start();
		//update();
	}
	private static final long serialVersionUID = 8565936385649552211L;
	//速度数组
	private Vector2D[] vects;
	//上面数组的速度值
	private Vector2D[] vels;
	//动画颜色
	private final Color[] COLORS = {
			Color.BLACK,Color.BLUE,Color.CYAN,Color.DARK_GRAY
			,Color.GREEN,Color.ORANGE,Color.PINK,Color.RED
	};
	//动画线程
	private Thread animation;
	private Image offscreen;
	public void init(){
		int len = COLORS.length;
		vects = new Vector2D[len];
		vels  = new Vector2D[len];
		Random r = new Random();
		for(int i=0;i<len;i++)
		{
			//创建组成园 的点
			vects[i] = new Vector2D.Double(50*(Math.cos(Math.toRadians(i*(360/len)))),
					50*(Math.sin(Math.toRadians(i*(360/len)))));
			//将点移动到屏幕中间
			vects[i].translate(getSize().width/2, getSize().height/2);
			//System.out.println(vects[i].getX()+"+"+vects[i].getY());
			//System.out.print(width+"-"+height);
			vels[i] = new Vector2D.Integer(1+r.nextInt()%5,1+r.nextInt()%5);
			
		}
		offscreen = this.createImage(getSize().width,getSize().height);
		animation = new Thread(this);
		animation.start();
	}
	public void start(){
		
	}
	public void stop(){
		animation = null;
	}
	@Override
	public void run() {
		Thread t = Thread.currentThread();
		while(true)
		{
			update();
			repaint();
			try{
				t.sleep(20);
				
			}
			catch (InterruptedException e){
				
			}
		}
	}
	public void update(){
		double width = (double)getSize().width;
		double height = (double)getSize().height;
		//System.out.print(width+"-"+height);
		for(int i = 0;i<COLORS.length;i++){
			System.out.println(vects[i].getX()+"+"+vects[i].getY());
			vects[i].translate(vels[i].getX(),vels[i].getY());
			if(vects[i].getX()>width){
				vects[i].setX(width);
				vels[i].setX(-vels[i].getX());
			}else if(vects[i].getX()<0){
				vects[i].setX(0);
				vels[i].setX(-vels[i].getX());
			}
			
			if(vects[i].getY()>height)
			{
				vects[i].setY(0);
				vels[i].setY(-vels[i].getY());
			}else if(vects[i].getY()<0){
				vects[i].setY(height);
				vels[i].setY(-vels[i].getY());
			}
			
		}
		if(offscreen == null||offscreen.getWidth(null)!=getSize().width||
			offscreen.getHeight(null)!=getSize().height){
				offscreen = createImage(getSize().width,getSize().height);
						
		}
		//paint(g);
	}
		//
			
	
		
	
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//offscreen = createImage(getSize().width,getSize().height);
		Graphics2D g2d = (Graphics2D)offscreen.getGraphics();
		g2d.setPaint(Color.WHITE);
		g2d.fillRect(0, 0, getSize().width,getSize().height);
		g2d.setStroke(new BasicStroke(3.0f));
		Vector2D prev = vects[COLORS.length-1];
		for(int i = 0;i<COLORS.length;i++){
			g2d.setPaint(COLORS[i]);
			g2d.drawLine((int)prev.getX(),(int)prev.getY(),(int)vects[i].getX(),
					(int)vects[i].getY());
			prev = vects[i];
		}
		g.drawImage(offscreen, 0, 0, this);
	}
	
}//Vector Test
