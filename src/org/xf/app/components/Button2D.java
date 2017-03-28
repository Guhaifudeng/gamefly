package org.xf.app.components;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;

import org.xf.app.actor2D.AnimationStrip;
import org.xf.app.actor2D.ImageGroup;
import org.xf.app.actor2D.Vector2D;

public class Button2D extends Component2D implements MouseListener,
		MouseMotionListener {
	// 按钮上可选的文本标签
	protected Label2D label;
	
	// 接收动作事件的监听器的数组
	protected ActionListener[] actionListeners;
	protected final int MAX_LISTENERS = 5;
	
	// 按钮的3种可用状态：普通、鼠标在上和鼠标按下
	
	public static final int BUTTON_NORMAL = 0;
	public static final int BUTTON_OVER = 1;
	public static final int BUTTON_DOWN = 2;

	
	//给定标签，图像组和位置创建一个新的Button2D对象
	public Button2D(Label2D lbl, ImageGroup grp, Vector2D p) {
		super();
		label = lbl;
		group = grp;
		setPos(p);
		updateBounds();
		update();
		
		// 为接收动作事件的监听器创建一个空的数组
		actionListeners = new ActionListener[MAX_LISTENERS];
		for (int i = 0; i < MAX_LISTENERS; i++) {
			actionListeners[i] = null;
		}
	}

	// 给定图像组和位置创建一个新的Button2D对象
	public Button2D(ImageGroup grp, Vector2D p) {
		this(null, grp, p);
	}

	// 给定标签和图像组创建一个新的Button2D对象
	public Button2D(Label2D lbl, ImageGroup grp) {
		this(lbl, grp, Vector2D.ZERO_VECTOR);
	}

	// 尝试把传入的监听器添加到ActionListener对象数组中
	// 失败条件：
	// 1) l is null
	// 2) the array already contains MAX_LISTENERS elements
	
	public void addActionListener(ActionListener l) {
		if (l == null) {
			return;
		}
		for (int i = 0; i < MAX_LISTENERS; i++) {
			if (actionListeners[i] == null) {
				actionListeners[i] = l;
				return;
			}
		}
	}

	// 使用按钮的边界矩形让按钮标签对齐
	public void centerLabel(Graphics2D g2d) {
		if (label != null) {
			label.centerOn(this.getBounds(), g2d);
		}
	}

	// 设置按钮的可用状态，更新它的内部状态，并在需要的情况下更新它的标签
	public void setEnabled(boolean e) {
		super.setEnabled(e);
		if (!isEnabled()) {
			state = BUTTON_NORMAL;
		}
		if (label != null) {
			label.setEnabled(e);
		}
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	// 如果按钮可用并且单击事件在按钮上面发生，就把按钮的状态设置为按下状态
	public void mousePressed(MouseEvent e) {
		if (isEnabled()) {
			if (bounds.contains(e.getPoint())) {
				state = BUTTON_DOWN;
			}
		}
	}

	// 通过通知所有的动作监听器发生了单击事件的方式来模拟按钮的单击
	// 如果鼠标不是在按钮上面松开，那么按钮返回一般状态，没有动作会发生
	// 
	// 
	public void mouseReleased(MouseEvent e) {
		
		// 只处理可用的按钮
		if (isEnabled()) {
			
			// 只有在鼠标仍然在按钮上面的情况下才能触发事件
			if (bounds.contains(e.getPoint())) {
				if (state == BUTTON_DOWN) {
					ActionEvent thisEvent = new ActionEvent(this,
							ActionEvent.ACTION_PERFORMED, "");
					
					// 让所有的监听器知道有些事情被传下去了
					for (int i = 0; i < MAX_LISTENERS; i++) {
						if (actionListeners[i] != null) {
							actionListeners[i].actionPerformed(thisEvent);
						}
					}
				}
				
				// 恢复到鼠标在上状态
				state = BUTTON_OVER;
			}
			
			// 如果鼠标没有在按钮上面松开则将按钮的状态返回为一般状态
			
			else {
				state = BUTTON_NORMAL;
			}
		}
	}

	public void mouseDragged(MouseEvent e) {
	}

	// 根据当前的状态和鼠标的位置更新按钮的状态
	public void mouseMoved(MouseEvent e) {
		if (isEnabled()) {
			
			// 如果鼠标已经按下则不要做任何事情
			if (state == BUTTON_DOWN)
				return;
			// 切换到鼠标向上状态
			if (bounds.contains(e.getPoint())) {
				state = BUTTON_OVER;
			}
			// 如果鼠标指针刚离开按钮区域则将按钮状态恢复为一般状态
			else {
				state = BUTTON_NORMAL;
			}
		}
	}

	// 根据按钮的当前状态来绘制它
	public void paintComponent2D(Graphics2D g2d) {
		
		// 只绘制可视组件
		if (isVisible()) {
			
			// 如果有图像，先绘制图像
			if (group != null) {
				g2d.drawImage(((ButtonImageGroup) group).getFrame(state),
						xform, AnimationStrip.observer);
			}
			
			// 如果存在有效的文本标签，则绘制它
			if (label != null) {
				
				//如果按钮被按下，则把标签放大一点，让它看起来像 按下的
				// look 'pressed'
				if (state == BUTTON_DOWN) {
					label.paintComponent2D(g2d, 2, 2);
					
				}
				// otherwise, just draw the label normally
				else {
					label.paintComponent2D(g2d);
				}
			}
		}
	}

	// 根据当前的状态和传入的x、y增量来绘制标签
	public void paintComponent2D(Graphics2D g2d, double dx, double dy) {
		
		// 只绘制可见组件
		if (isVisible()) {
			
			// 如果有图像，先绘制图像
			if (group != null) {
				g2d.drawImage(((ButtonImageGroup) group).getFrame(state),
						AffineTransform.getTranslateInstance(pos.getX() + dx,
								pos.getY() + dy), AnimationStrip.observer);
			}
			// 如果存在有效的文本标签，则绘制它
			if (label != null) {
				
				// 如果按钮被按下，则把标签放大一点，让它看起来像 按下的
				// look 'pressed'
				if (state == BUTTON_DOWN) {
					label.paintComponent2D(g2d, dx + 2, dx + 2);
				}
				// otherwise, just draw the label normally
				else {
					label.paintComponent2D(g2d, dx, dx);
				}
			}
		}
	}

	// 返回一个对这个按钮的描述字符串
	public String toString() {
		
		// 如果标签有效，返回父类的toString加上标签的toString
		if (label != null) {
			return super.toString() + " " + label.toString();
		}
		
		// 否则只返回父类的toString
		return super.toString();
	}

} // Button2D