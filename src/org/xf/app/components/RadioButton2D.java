package org.xf.app.components;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;

import org.xf.app.actor2D.AnimationStrip;
import org.xf.app.actor2D.ImageGroup;
import org.xf.app.actor2D.Vector2D;

public class RadioButton2D extends Component2D implements MouseListener {
	// 和按钮一起显示的标签
	protected Label2D label;
	
	// 这个按钮所从属的组
	protected RadioButtonGroup rbGroup;
	
	// 跟踪按钮状态的常量
	public static final int BUTTON_OFF = 0;
	public static final int BUTTON_ON = 1;

	// 用给定的标签、图像、按钮组和位置创建一个RadioButton2D
	// 
	public RadioButton2D(Label2D lbl, ImageGroup grp, RadioButtonGroup rbg,
			Vector2D p) {
		super();
		label = lbl;
		group = grp;
		rbGroup = rbg;
		if (rbGroup != null) {
			rbGroup.add(this);
		}
		setPos(p);
		updateBounds();
		update();
		setSelected(false);
	}

	// 用给定的图像、按钮组和位置创建一个新的RadioButton2D
	public RadioButton2D(ImageGroup grp, RadioButtonGroup rbg, Vector2D p) {
		this(null, grp, rbg, p);
	}
	
	//用给定的标签、图像、按钮创建一个新的RadioButton2D 
	public RadioButton2D(Label2D lbl, ImageGroup grp, RadioButtonGroup rbg) {
		this(lbl, grp, rbg, Vector2D.ZERO_VECTOR);
	}

	// 用给定的图像组创新一个新的RadioButton2D
	public RadioButton2D(ImageGroup grp) {
		this(null, grp, null, Vector2D.ZERO_VECTOR);
	}

	public boolean isSelected() {
		return (state == BUTTON_ON);
	}

	// 
	public void setSelected(boolean selected) {
		state = (selected) ? BUTTON_ON : BUTTON_OFF;
	}

	// 

	public String getText() {
		if (label != null) {
			return label.getText();
		}
		return "";
	}

	// 设置按钮和它的标签的可用状态
	public void setEnabled(boolean e) {
		super.setEnabled(e);
		if (label != null) {
			label.setEnabled(e);
		}
	}

	// 变换按钮的状态
	public void mousePressed(MouseEvent e) {
		if (!isEnabled() || !isVisible())
			return;
		
		// 如果没有按钮组则表明按钮独立行动
		if (rbGroup == null) {
			if (bounds.contains(e.getPoint())) {
				setSelected(!isSelected());
			}
		}
		// 否则 更新整个按钮
		else {
			if (bounds.contains(e.getPoint())) {
				setSelected(true);
				rbGroup.updateGroup(this);
			}
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	// 用当前的变换绘制按钮
	public void paintComponent2D(Graphics2D g2d) {
		
		// 只绘制可见组件
		if (isVisible()) {
			g2d.drawImage(((ButtonImageGroup) group).getFrame(state), xform,
					AnimationStrip.observer);
			
			// 如果标签有效则绘制标签
			if (label != null) {
				label.paintComponent2D(g2d);
			}
		}
	}

	// 用当前的变换绘制按钮
	public void paintComponent2D(Graphics2D g2d, double dx, double dy) {
		
		// 
		if (isVisible()) {
			g2d.drawImage(
					((ButtonImageGroup) group).getFrame(state),
					AffineTransform.getTranslateInstance(pos.getX() + dx,
							pos.getY() + dy), AnimationStrip.observer);
			
			// 
			if (label != null) {
				label.paintComponent2D(g2d, dx, dx);
			}
		}
	}

	// 返回描述这个按钮的文本
	public String toString() {
		if (label != null) {
			return super.toString() + " " + label.toString();
		}
		return super.toString();
	}

} // RadioButton2D