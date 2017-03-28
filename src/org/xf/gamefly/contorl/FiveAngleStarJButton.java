package org.xf.gamefly.contorl;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;

public class FiveAngleStarJButton extends JButton {
	private static final long serialVersionUID = 1L;
	private String label;

	public FiveAngleStarJButton(String label) {
		//super(label);
		this.label = label;
		setContentAreaFilled(false);
	}
	// 画图的按钮的背景和标签
	
	public void paintComponent(Graphics g) {
		
		// 调用父类的paintComponent画按钮的标签和焦点所在的小矩形
		
		if (getModel().isArmed()) {
			g.setColor(Color.GREEN);
		} else {
			g.setColor(getBackground());
		}
			//g.fillPolygon(fiveStarPolyA(0,getSize().height*0.4,getSize().width*0.9));
			super.paintComponent(g);//必须一致----
	}

	// 用简单的弧充当按钮的边界
	public void paintBorder(Graphics g) {
		Graphics2D g2 =(Graphics2D)g;
		g2.setColor(new Color(179,251,157));
		// drawOval方法画矩形的内切椭圆,但不填充,只画出一个边界
		g2.setStroke(new BasicStroke(1,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		g2.drawPolygon(fiveStarPolyA(0,getSize().height*0.4,getSize().width*0.9));
		
		g2.setStroke(new BasicStroke(4,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		g2.setColor(new Color(179,251,157));
		g2.drawString(label,(int)(getSize().width*0.3),(int)(getSize().height*0.6));
		
	}
	Shape shape;//用于保存按钮的形状,有助于侦听单击按钮事件
	 
	 //判断鼠标是否点在按钮上
	 
	 public boolean contains(int x,int y){
		
	  //如果按钮边框,位置发生改变,则产生一个新的形状对象
	  if((shape==null)||(!shape.getBounds().equals(getBounds()))){
	   //构造椭圆型对象
	   shape=fiveStarPolyB(0,getSize().height*0.4,getSize().width*0.9);
		
	  }
	  //判断鼠标的x,y坐标是否落在按钮形状内
	  return shape.contains(x,y);
	 }
	
	//五角星
	public Polygon fiveStarPolyA(double x0, double y0, double r) {
		double ch = 72 * Math.PI / 180;// 圆心角的弧度数
		
		double x1 =x0,y1 = y0;
		double x2,y2,x3,y3,x4,y4,x5,y5;
		x2 = r*0.5+x1;
		y2 = y1-Math.tan(ch/2)*r*0.5;
		x3 = r+x1;
		y3 = y1;
		x4 = x1+Math.cos(ch/2)*r;
		y4 = y1+Math.sin(ch/2)*r;
		x5 = x3-Math.cos(ch/2)*r;
		y5 = y4;
		

		Polygon a = new Polygon();

		a.addPoint((int)x1, (int)y1);
		a.addPoint((int)x3, (int)y3);
		a.addPoint((int)x5, (int)y5);
		a.addPoint((int)x2, (int)y2);
		a.addPoint((int)x4, (int)y4);
	

		return a;

	}
	public Polygon fiveStarPolyB(double x0, double y0, double r) {
		double ch = 72 * Math.PI / 180;// 圆心角的弧度数
		
		double x1 =x0,y1 = y0;
		double x2,y2,x3,y3,x4,y4,x5,y5;
		x2 = r*0.5+x1;
		y2 = y1-Math.tan(ch/2)*r*0.5;
		x3 = r+x1;
		y3 = y1;
		x4 = x1+Math.cos(ch/2)*r;
		y4 = y1+Math.sin(ch/2)*r;
		x5 = x3-Math.cos(ch/2)*r;
		y5 = y4;
		

		Polygon b = new Polygon();

		b.addPoint((int)x1, (int)y1);
		b.addPoint((int)x2, (int)y2);
		b.addPoint((int)x3, (int)y3);
		b.addPoint((int)x4, (int)y4);
		b.addPoint((int)x5, (int)y5);
	

		return b;

	}

}
