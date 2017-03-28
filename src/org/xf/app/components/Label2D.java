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
	//����ʱʹ�õ�����
	protected Font font;
	// ʵ��Ҫ���Ƶ��ı�
	protected String text;
	// Ϊ���ò���������״̬��׼����Paint
	protected Paint paint;
	protected Paint disabledPaint;
	// Ĭ�ϵ�Paint
	protected final Paint DEFAULT_PAINT = Color.GRAY;

	// ��ָ�������塢�ı���paint����һ���µ�Label2D
	public Label2D(Font font2, String str, Paint p) {
		super();
		setFont(font2);
		setText(str);
		setPaint(p);
		// ��Ĭ��paint����Ϊ������״̬��paint
		setDisabledPaint(DEFAULT_PAINT);
	}

	// ʹ��ָ�������塢�ı���paint��λ�ù���һ���µ�Label2D
	public Label2D(Font f, String str, Paint p, Vector2D v) {
		super();
		setFont(f);
		setText(str);
		setPaint(p);
		setPos(v);
		// ��Ĭ��paint����Ϊ������״̬
		setDisabledPaint(DEFAULT_PAINT);
	}

	// ����ǩ�����ָ���ľ��ζ���
	public void centerOn(Rectangle2D r, Graphics2D g2d) {
		// ��Graphics2D�����л�ȡFontRenderContext
		FontRenderContext frc = g2d.getFontRenderContext();
		// ��ȡ�ı�����
		TextLayout layout = new TextLayout(text, font, frc);
		// ��ȡ���ֵı߽�
		Rectangle2D textBounds = layout.getBounds();
		// �����µ�λ��
		setX(r.getX() + (r.getWidth() / 2) - (textBounds.getWidth() / 2));
		setY(r.getY() + ((r.getHeight() + textBounds.getHeight()) / 2));
		// ��������߽����
		updateBounds(g2d);
	}

	//ʹ�õ�ǰ����Ĳ��ָ��±�ǩ�ı߽�
	public void updateBounds(Graphics2D g2d) {
		// ��Graphics2D�����л�ȡFontRenderContext
		FontRenderContext frc = g2d.getFontRenderContext();
		// ����ı�����
		TextLayout layout = new TextLayout(text, font, frc);
		// ��ò��ֵı߽�
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

	// �ڵ�ǰλ�û��Ʊ�ǩ
	public void paintComponent2D(Graphics2D g2d) {
		// ֻ���ڿɼ�ʱ�Ż���
		if (isVisible()) {
			//��������
			g2d.setFont(font);
			// ���ݿ���״̬����paint
			if (isEnabled()) {
				g2d.setPaint(paint);
			} else {
				g2d.setPaint(disabledPaint);
			}
			g2d.drawString(text, (int) pos.getX(), (int) pos.getY());
		}
	}

	//��ָ����ƫ�ƻ��Ʊ�ǩ
	public void paintComponent2D(Graphics2D g2d, double dx, double dy) {
		// ֻ���ڿɼ�ʱ����
		if (isVisible()) {
			// ��������
			g2d.setFont(font);
			// ���ݿ���״̬����paint
			if (isEnabled()) {
				g2d.setPaint(paint);
			} else {
				g2d.setPaint(disabledPaint);
			}
			// �����ַ���������ӵ�ƫ��λ��
			g2d.drawString(text, (int) (pos.getX() + dx),
					(int) (pos.getY() + dy));
		}
	}
	 
	// ����������ǩ���ַ���
	public String toString() {
		// ���ظ���toString�����Ľ���͵�ǰ���ı���ǩ
		return super.toString() + " " + text;
	}
} // Label2D