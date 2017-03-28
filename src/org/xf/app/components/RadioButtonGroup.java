package org.xf.app.components;

import java.awt.Graphics2D;
import java.util.Enumeration;
import java.util.Vector;
//维护一组按钮，这样在任意时刻只有一个被选中
public class RadioButtonGroup {
	// 用来装单选按钮的动态可增长Vector
	protected Vector buttons;
	
	//遍历上述Vector的枚举器
	protected Enumeration e;

	// 创建一个新的对象
	public RadioButtonGroup() {
		
		buttons = new Vector();
	}

	//将传入的单选按钮添加到Vector中去
	public void add(RadioButton2D rb) {
		
		buttons.add(rb);
	}

	// 得到当前所选中的单选按钮
	// 如果没有按钮被选中，则返回null
	public RadioButton2D getSelection() {
		
		for (e = buttons.elements(); e.hasMoreElements();) {
			RadioButton2D rb = (RadioButton2D) e.nextElement();
			if (rb.isSelected()) {
				return rb;
			}
		}
		return null;
	}

	// 整组按钮的可用/不可用状态
	public void setEnabled(boolean b) {
		
		for (e = buttons.elements(); e.hasMoreElements();) {
			((RadioButton2D) e.nextElement()).setEnabled(b);
		}
	}

	// 设置整个按钮组的可见性
	public void setVisible(boolean v) {
		
		for (e = buttons.elements(); e.hasMoreElements();) {
			((RadioButton2D) e.nextElement()).setVisible(v);
		}
	}

	// 更新按钮组，让除了传入的按钮之外的按钮设置为未选中状态
	public void updateGroup(RadioButton2D rb) {
		
		for (e = buttons.elements(); e.hasMoreElements();) {
			Object o = e.nextElement();
			if (rb != o) {
				((RadioButton2D) o).setSelected(false);
			}
		}
	}

	// 绘制组中的每一个按钮
	public void paint(Graphics2D g2d) {
		for (e = buttons.elements(); e.hasMoreElements();) {
			((RadioButton2D) e.nextElement()).paintComponent2D(g2d);
		}
	}

	// 在给定的位置绘制组中的每一个按钮
	public void paint(Graphics2D g2d, double dx, double dy) {
		
		for (e = buttons.elements(); e.hasMoreElements();) {
			((RadioButton2D) e.nextElement()).paintComponent2D(g2d, dx, dy);
		}
	}
} // RadioButtonGroup