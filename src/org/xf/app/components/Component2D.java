package org.xf.app.components;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import org.xf.app.actor2D.ImageGroup;
import org.xf.app.actor2D.Vector2D;
import org.xf.app.actor2D.Vector2D.Double;

public abstract class Component2D {
	// 容纳组件外观的ImageGroup
	protected ImageGroup group;
	// 组件的大小
	protected int frameWidth;
	protected int frameHeight;
	// 组件的位置和边界，以及缓存的变换
	protected Vector2D pos;
	protected Rectangle2D bounds;
	protected AffineTransform xform;
	// 组件是否可视，是否可用
	protected boolean enabled;
	protected boolean visible;
	// 跟踪组件的内部状态
	protected int state;

	// 用默认的属性创建一个新的Component2D对象
	protected Component2D() {
		pos = new Vector2D.Double();
		xform = new AffineTransform();
		setEnabled(true);
		setVisible(true);
		bounds = new Rectangle2D.Double();
		frameWidth = 0;
		frameHeight = 0;
	}

	// 使用传入的Graphics2D容器绘制组件
	public abstract void paintComponent2D(Graphics2D g2d);

	// 给定的偏移位置绘制组件
	public abstract void paintComponent2D(Graphics2D g2d, double dx, double dy);

	//更新组件内部的变换
	public void update() {
		xform.setToTranslation(pos.getX(), pos.getY());
	}

	// 更新组件的边界矩形
	public void updateBounds() {
		if (frameWidth <= 0 && group != null) {
			frameWidth = group.getFrameWidth();
		}
		if (frameHeight <= 0 && group != null) {
			frameHeight = group.getFrameHeight();
		}
		bounds.setRect(pos.getX(), pos.getY(), frameWidth, frameHeight);
	}

	// 返回一个描述组件的String
	public String toString() {//输出类名和当前位置
		return getClass().getName() + ": " + pos.toString();
	}

	// 主要属性的访问方法
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

