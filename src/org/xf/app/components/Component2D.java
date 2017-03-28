package org.xf.app.components;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import org.xf.app.actor2D.ImageGroup;
import org.xf.app.actor2D.Vector2D;
import org.xf.app.actor2D.Vector2D.Double;

public abstract class Component2D {
	// ���������۵�ImageGroup
	protected ImageGroup group;
	// ����Ĵ�С
	protected int frameWidth;
	protected int frameHeight;
	// �����λ�úͱ߽磬�Լ�����ı任
	protected Vector2D pos;
	protected Rectangle2D bounds;
	protected AffineTransform xform;
	// ����Ƿ���ӣ��Ƿ����
	protected boolean enabled;
	protected boolean visible;
	// ����������ڲ�״̬
	protected int state;

	// ��Ĭ�ϵ����Դ���һ���µ�Component2D����
	protected Component2D() {
		pos = new Vector2D.Double();
		xform = new AffineTransform();
		setEnabled(true);
		setVisible(true);
		bounds = new Rectangle2D.Double();
		frameWidth = 0;
		frameHeight = 0;
	}

	// ʹ�ô����Graphics2D�����������
	public abstract void paintComponent2D(Graphics2D g2d);

	// ������ƫ��λ�û������
	public abstract void paintComponent2D(Graphics2D g2d, double dx, double dy);

	//��������ڲ��ı任
	public void update() {
		xform.setToTranslation(pos.getX(), pos.getY());
	}

	// ��������ı߽����
	public void updateBounds() {
		if (frameWidth <= 0 && group != null) {
			frameWidth = group.getFrameWidth();
		}
		if (frameHeight <= 0 && group != null) {
			frameHeight = group.getFrameHeight();
		}
		bounds.setRect(pos.getX(), pos.getY(), frameWidth, frameHeight);
	}

	// ����һ�����������String
	public String toString() {//��������͵�ǰλ��
		return getClass().getName() + ": " + pos.toString();
	}

	// ��Ҫ���Եķ��ʷ���
	public void setEnabled(boolean e) {
		enabled = e;
	}

	public final boolean isEnabled() {
		return enabled;
	}

	public final void setVisible(boolean v) {
		visible = v;
	}

	public final boolean isVisible() {
		return visible;
	}

	public final void setX(double px) {
		pos.setX(px);
	}

	public final void setY(double py) {
		pos.setY(py);
	}

	public final double getX() {
		return pos.getX();
	}

	public final double getY() {
		return pos.getY();
	}

	public final void setPos(int x, int y) {
		pos.setX(x);
		pos.setY(y);
	}

	public final void setPos(double x, double y) {
		pos.setX(x);
		pos.setY(y);
	}

	public final void setPos(Vector2D v) {
		pos.setX(v.getX());
		pos.setY(v.getY());
	}

	public final Vector2D getPos() {
		return pos;
	}

	public Rectangle2D getBounds() {
		return bounds;
	}
} // Component2D

