package org.xf.app.containters;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Enumeration;
import java.util.Vector;

import org.xf.app.actor2D.AnimationStrip;
import org.xf.app.actor2D.Vector2D;
import org.xf.app.components.Component2D;

//����һ����������������������
public class Panel2D extends Container2D {
	// ����ͼƬ
	protected Image background;
	// ��ָ���ı���ͼƬ��λ�ô���һ���µ�Panel2D����
	public Panel2D(Image bgImage, Vector2D p) {
		super(p);
		background = bgImage;
		
	}

	// ������ָ��λ��������
	// ע���������x��yֵ����������ԭ������Ͻ�
	// 
	public void add(Component2D c, double x, double y) {
		c.setX(getX() + x);
		c.setY(getY() + y);
		
		// ȷ����������֮��
		if (c.getX() + c.getBounds().getWidth() > getX()
				+ getBounds().getWidth()) {
			c.setX(getX() + getBounds().getWidth() - c.getBounds().getWidth());
		}
		
		if (c.getY() + c.getBounds().getHeight() > getY()
				+ getBounds().getHeight()) {
			c.setY(getY() + getBounds().getHeight() - c.getBounds().getHeight());
		}
		
		c.update();
		c.updateBounds();
		components.add(c);
	}

	// ���������������
	public void paintComponent2D(Graphics2D g2d) {
		
		// ���Ʊ���
		if (background != null) {
			g2d.drawImage(background, (int) getX(), (int) getY(),
					AnimationStrip.observer);
		}
		// ��������ϵ����
		for (e = components.elements(); e.hasMoreElements();) {
			((Component2D) e.nextElement()).paintComponent2D(g2d);
		}
	}

	// ��ָ����ƫ��ֵ���������������
	public void paintComponent2D(Graphics2D g2d, double dx, double dy) {
		
		if (background != null) {
			g2d.drawImage(background, (int) (getX() + dx), (int) (getY() + dy),
					AnimationStrip.observer);
		}
		for (e = components.elements(); e.hasMoreElements();) {
			((Component2D) e.nextElement()).paintComponent2D(g2d, dx, dy);
		}
	}

	//�������ı߽磬������û��ͼƬ���򲻻᳢�Լ������ı߽� 
	// 

	public void updateBounds() {
		if (background != null) {
			frameWidth = background.getWidth(AnimationStrip.observer);
			frameHeight = background.getHeight(AnimationStrip.observer);
		} else {
			frameWidth = Integer.MAX_VALUE;
			frameHeight = Integer.MAX_VALUE;
		}
		bounds.setRect(pos.getX(), pos.getY(), frameWidth, frameHeight);
	}

} // Panel2D