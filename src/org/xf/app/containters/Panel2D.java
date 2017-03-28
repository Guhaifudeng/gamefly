package org.xf.app.containters;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Enumeration;
import java.util.Vector;

import org.xf.app.actor2D.AnimationStrip;
import org.xf.app.actor2D.Vector2D;
import org.xf.app.components.Component2D;

//定义一个可以容纳其他组件的面板
public class Panel2D extends Container2D {
	// 背景图片
	protected Image background;
	// 用指定的背景图片和位置创建一个新的Panel2D对象
	public Panel2D(Image bgImage, Vector2D p) {
		super(p);
		background = bgImage;
		
	}

	// 在面板的指定位置添加组件
	// 注意所传入的x、y值是相对于面板原点的左上角
	// 
	public void add(Component2D c, double x, double y) {
		c.setX(getX() + x);
		c.setY(getY() + y);
		
		// 确保组件在面板之内
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

	// 绘制面板和它的组件
	public void paintComponent2D(Graphics2D g2d) {
		
		// 绘制背景
		if (background != null) {
			g2d.drawImage(background, (int) getX(), (int) getY(),
					AnimationStrip.observer);
		}
		// 绘制面板上的组件
		for (e = components.elements(); e.hasMoreElements();) {
			((Component2D) e.nextElement()).paintComponent2D(g2d);
		}
	}

	// 用指定的偏移值绘制面板和它的组件
	public void paintComponent2D(Graphics2D g2d, double dx, double dy) {
		
		if (background != null) {
			g2d.drawImage(background, (int) (getX() + dx), (int) (getY() + dy),
					AnimationStrip.observer);
		}
		for (e = components.elements(); e.hasMoreElements();) {
			((Component2D) e.nextElement()).paintComponent2D(g2d, dx, dy);
		}
	}

	//更新面板的边界，如果面板没有图片，则不会尝试计算面板的边界 
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