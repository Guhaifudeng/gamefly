package org.xf.app.components;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;

import org.xf.app.actor2D.AnimationStrip;
import org.xf.app.actor2D.ImageGroup;
import org.xf.app.actor2D.Vector2D;

public class RadioButton2D extends Component2D implements MouseListener {
	// �Ͱ�ťһ����ʾ�ı�ǩ
	protected Label2D label;
	
	// �����ť����������
	protected RadioButtonGroup rbGroup;
	
	// ���ٰ�ť״̬�ĳ���
	public static final int BUTTON_OFF = 0;
	public static final int BUTTON_ON = 1;

	// �ø����ı�ǩ��ͼ�񡢰�ť���λ�ô���һ��RadioButton2D
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

	// �ø�����ͼ�񡢰�ť���λ�ô���һ���µ�RadioButton2D
	public RadioButton2D(ImageGroup grp, RadioButtonGroup rbg, Vector2D p) {
		this(null, grp, rbg, p);
	}
	
	//�ø����ı�ǩ��ͼ�񡢰�ť����һ���µ�RadioButton2D 
	public RadioButton2D(Label2D lbl, ImageGroup grp, RadioButtonGroup rbg) {
		this(lbl, grp, rbg, Vector2D.ZERO_VECTOR);
	}

	// �ø�����ͼ���鴴��һ���µ�RadioButton2D
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

	// ���ð�ť�����ı�ǩ�Ŀ���״̬
	public void setEnabled(boolean e) {
		super.setEnabled(e);
		if (label != null) {
			label.setEnabled(e);
		}
	}

	// �任��ť��״̬
	public void mousePressed(MouseEvent e) {
		if (!isEnabled() || !isVisible())
			return;
		
		// ���û�а�ť���������ť�����ж�
		if (rbGroup == null) {
			if (bounds.contains(e.getPoint())) {
				setSelected(!isSelected());
			}
		}
		// ���� ����������ť
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

	// �õ�ǰ�ı任���ư�ť
	public void paintComponent2D(Graphics2D g2d) {
		
		// ֻ���ƿɼ����
		if (isVisible()) {
			g2d.drawImage(((ButtonImageGroup) group).getFrame(state), xform,
					AnimationStrip.observer);
			
			// �����ǩ��Ч����Ʊ�ǩ
			if (label != null) {
				label.paintComponent2D(g2d);
			}
		}
	}

	// �õ�ǰ�ı任���ư�ť
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

	// �������������ť���ı�
	public String toString() {
		if (label != null) {
			return super.toString() + " " + label.toString();
		}
		return super.toString();
	}

} // RadioButton2D