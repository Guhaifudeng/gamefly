package org.xf.app.components;

import java.awt.Graphics2D;
import java.util.Enumeration;
import java.util.Vector;
//ά��һ�鰴ť������������ʱ��ֻ��һ����ѡ��
public class RadioButtonGroup {
	// ����װ��ѡ��ť�Ķ�̬������Vector
	protected Vector buttons;
	
	//��������Vector��ö����
	protected Enumeration e;

	// ����һ���µĶ���
	public RadioButtonGroup() {
		
		buttons = new Vector();
	}

	//������ĵ�ѡ��ť��ӵ�Vector��ȥ
	public void add(RadioButton2D rb) {
		
		buttons.add(rb);
	}

	// �õ���ǰ��ѡ�еĵ�ѡ��ť
	// ���û�а�ť��ѡ�У��򷵻�null
	public RadioButton2D getSelection() {
		
		for (e = buttons.elements(); e.hasMoreElements();) {
			RadioButton2D rb = (RadioButton2D) e.nextElement();
			if (rb.isSelected()) {
				return rb;
			}
		}
		return null;
	}

	// ���鰴ť�Ŀ���/������״̬
	public void setEnabled(boolean b) {
		
		for (e = buttons.elements(); e.hasMoreElements();) {
			((RadioButton2D) e.nextElement()).setEnabled(b);
		}
	}

	// ����������ť��Ŀɼ���
	public void setVisible(boolean v) {
		
		for (e = buttons.elements(); e.hasMoreElements();) {
			((RadioButton2D) e.nextElement()).setVisible(v);
		}
	}

	// ���°�ť�飬�ó��˴���İ�ť֮��İ�ť����Ϊδѡ��״̬
	public void updateGroup(RadioButton2D rb) {
		
		for (e = buttons.elements(); e.hasMoreElements();) {
			Object o = e.nextElement();
			if (rb != o) {
				((RadioButton2D) o).setSelected(false);
			}
		}
	}

	// �������е�ÿһ����ť
	public void paint(Graphics2D g2d) {
		for (e = buttons.elements(); e.hasMoreElements();) {
			((RadioButton2D) e.nextElement()).paintComponent2D(g2d);
		}
	}

	// �ڸ�����λ�û������е�ÿһ����ť
	public void paint(Graphics2D g2d, double dx, double dy) {
		
		for (e = buttons.elements(); e.hasMoreElements();) {
			((RadioButton2D) e.nextElement()).paintComponent2D(g2d, dx, dy);
		}
	}
} // RadioButtonGroup