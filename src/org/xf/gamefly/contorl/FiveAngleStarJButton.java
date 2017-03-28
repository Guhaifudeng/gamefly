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
	// ��ͼ�İ�ť�ı����ͱ�ǩ
	
	public void paintComponent(Graphics g) {
		
		// ���ø����paintComponent����ť�ı�ǩ�ͽ������ڵ�С����
		
		if (getModel().isArmed()) {
			g.setColor(Color.GREEN);
		} else {
			g.setColor(getBackground());
		}
			//g.fillPolygon(fiveStarPolyA(0,getSize().height*0.4,getSize().width*0.9));
			super.paintComponent(g);//����һ��----
	}

	// �ü򵥵Ļ��䵱��ť�ı߽�
	public void paintBorder(Graphics g) {
		Graphics2D g2 =(Graphics2D)g;
		g2.setColor(new Color(179,251,157));
		// drawOval���������ε�������Բ,�������,ֻ����һ���߽�
		g2.setStroke(new BasicStroke(1,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		g2.drawPolygon(fiveStarPolyA(0,getSize().height*0.4,getSize().width*0.9));
		
		g2.setStroke(new BasicStroke(4,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		g2.setColor(new Color(179,251,157));
		g2.drawString(label,(int)(getSize().width*0.3),(int)(getSize().height*0.6));
		
	}
	Shape shape;//���ڱ��水ť����״,����������������ť�¼�
	 
	 //�ж�����Ƿ���ڰ�ť��
	 
	 public boolean contains(int x,int y){
		
	  //�����ť�߿�,λ�÷����ı�,�����һ���µ���״����
	  if((shape==null)||(!shape.getBounds().equals(getBounds()))){
	   //������Բ�Ͷ���
	   shape=fiveStarPolyB(0,getSize().height*0.4,getSize().width*0.9);
		
	  }
	  //�ж�����x,y�����Ƿ����ڰ�ť��״��
	  return shape.contains(x,y);
	 }
	
	//�����
	public Polygon fiveStarPolyA(double x0, double y0, double r) {
		double ch = 72 * Math.PI / 180;// Բ�ĽǵĻ�����
		
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
		double ch = 72 * Math.PI / 180;// Բ�ĽǵĻ�����
		
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
