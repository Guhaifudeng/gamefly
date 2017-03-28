package org.xf.app.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

import org.xf.app.actor2D.Vector2D;

public class Label2D extends Component2D {
	//绘制时使用的字体
	protected Font font;
	// 实际要绘制的文本
	protected String text;
	// 为可用不可用两种状态所准备的Paint
	protected Paint paint;
	protected Paint disabledPaint;
	// 默认的Paint
	protected final Paint DEFAULT_PAINT = Color.GRAY;

	// 用指定的字体、文本和paint构建一个新的Label2D
	public Label2D(Font font2, String str, Paint p) {
		super();
		setFont(font2);
		setText(str);
		setPaint(p);
		// 将默认paint设置为不可用状态的paint
		setDisabledPaint(DEFAULT_PAINT);
	}

	// 使用指定的字体、文本、paint和位置构建一个新的Label2D
	public Label2D(Font f, String str, Paint p, Vector2D v) {
		super();
		setFont(f);
		setText(str);
		setPaint(p);
		setPos(v);
		// 将默认paint设置为不可用状态
		setDisabledPaint(DEFAULT_PAINT);
	}

	// 将标签相对于指定的矩形对齐
	public void centerOn(Rectangle2D r, Graphics2D g2d) {
		// 从Graphics2D容器中获取FontRenderContext
		FontRenderContext frc = g2d.getFontRenderContext();
		// 获取文本布局
		TextLayout layout = new TextLayout(text, font, frc);
		// 获取布局的边界
		Rectangle2D textBounds = layout.getBounds();
		// 设置新的位置
		setX(r.getX() + (r.getWidth() / 2) - (textBounds.getWidth() / 2));
		setY(r.getY() + ((r.getHeight() + textBounds.getHeight()) / 2));
		// 更新总体边界矩形
		updateBounds(g2d);
	}

	//使用当前字体的布局更新标签的边界
	public void updateBounds(Graphics2D g2d) {
		// 从Graphics2D容器中获取FontRenderContext
		FontRenderContext frc = g2d.getFontRenderContext();
		// 获得文本布局
		TextLayout layout = new TextLayout(text, font, frc);
		// 获得布局的边界
		Rectangle2D textBounds = layout.getBounds();
		bounds.setRect(getX(), getY(), textBounds.getWidth(),
				textBounds.getHeight());
	}

	public void setFont(Font f) {
		font = f;
	}

	public void setText(String str) {
		text = str;
	}

	public String getText() {
		return text;
	}

	public void setPaint(Paint p) {
		paint = p;
	}

	public void setDisabledPaint(Paint p) {
		disabledPaint = p;
	}

	// 在当前位置绘制标签
	public void paintComponent2D(Graphics2D g2d) {
		// 只有在可见时才绘制
		if (isVisible()) {
			//设置字体
			g2d.setFont(font);
			// 根据可用状态设置paint
			if (isEnabled()) {
				g2d.setPaint(paint);
			} else {
				g2d.setPaint(disabledPaint);
			}
			g2d.drawString(text, (int) pos.getX(), (int) pos.getY());
		}
	}

	//在指定的偏移绘制标签
	public void paintComponent2D(Graphics2D g2d, double dx, double dy) {
		// 只有在可见时绘制
		if (isVisible()) {
			// 设置字体
			g2d.setFont(font);
			// 根据可用状态设置paint
			if (isEnabled()) {
				g2d.setPaint(paint);
			} else {
				g2d.setPaint(disabledPaint);
			}
			// 绘制字符串，并添加到偏移位置
			g2d.drawString(text, (int) (pos.getX() + dx),
					(int) (pos.getY() + dy));
		}
	}
	 
	// 返回描述标签的字符串
	public String toString() {
		// 返回父类toString方法的结果和当前的文本标签
		return super.toString() + " " + text;
	}
} // Label2D