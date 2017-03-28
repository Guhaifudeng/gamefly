package org.xf.app.scene;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import org.xf.app.actor2D.Actor2D;
/*
 * ����ǽӿ�
 * ���ӿڲ��ܰ������ೣ��֮������ԣ�
 * ��Ըǿ������ʵ��add����
 */
//�ṩ�����������
public abstract class Scene {
	
	//����������߽�
	protected Rectangle2D bounds;

	//�����еĿɼ����֣�ͨ��ΪJPabel�����С
	protected Rectangle2D viewable;
	public Scene(Rectangle2D v,Rectangle2D b){
		setViewable(v);
		setBounds(b);
	}
	//���һ��Actor�������У�ʹ��Actor2D���������Ӧ�ø����������
	public  void add(Actor2D a){
		
	}//δʹ�ó���������������
	//���³���
	public abstract void  update();
	//��Graphics2D�����ϻ��Ƴ���
	public abstract void paint(Graphics2D g2d);
	public final Rectangle2D getBounds() {
		return bounds;
		
	}

	public final void setBounds(Rectangle2D r) {
		this.bounds =new Rectangle2D.Double(r.getX(),r.getY(),r.getWidth(),r.getHeight());
	}

	public final Rectangle2D getViewable() {
		return viewable;
	}

	public final void setViewable(Rectangle2D r) {
		this.viewable = new Rectangle2D.Double(r.getX(),r.getY(),r.getWidth(),r.getHeight());
	}


	

}
