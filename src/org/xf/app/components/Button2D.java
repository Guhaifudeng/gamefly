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
	// ��ť�Ͽ�ѡ���ı���ǩ
	protected Label2D label;
	
	// ���ն����¼��ļ�����������
	protected ActionListener[] actionListeners;
	protected final int MAX_LISTENERS = 5;
	
	// ��ť��3�ֿ���״̬����ͨ��������Ϻ���갴��
	
	public static final int BUTTON_NORMAL = 0;
	public static final int BUTTON_OVER = 1;
	public static final int BUTTON_DOWN = 2;

	
	//������ǩ��ͼ�����λ�ô���һ���µ�Button2D����
	public Button2D(Label2D lbl, ImageGroup grp, Vector2D p) {
		super();
		label = lbl;
		group = grp;
		setPos(p);
		updateBounds();
		update();
		
		// Ϊ���ն����¼��ļ���������һ���յ�����
		actionListeners = new ActionListener[MAX_LISTENERS];
		for (int i = 0; i < MAX_LISTENERS; i++) {
			actionListeners[i] = null;
		}
	}

	// ����ͼ�����λ�ô���һ���µ�Button2D����
	public Button2D(ImageGroup grp, Vector2D p) {
		this(null, grp, p);
	}

	// ������ǩ��ͼ���鴴��һ���µ�Button2D����
	public Button2D(Label2D lbl, ImageGroup grp) {
		this(lbl, grp, Vector2D.ZERO_VECTOR);
	}

	// ���԰Ѵ���ļ�������ӵ�ActionListener����������
	// ʧ��������
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

	// ʹ�ð�ť�ı߽�����ð�ť��ǩ����
	public void centerLabel(Graphics2D g2d) {
		if (label != null) {
			label.centerOn(this.getBounds(), g2d);
		}
	}

	// ���ð�ť�Ŀ���״̬�����������ڲ�״̬��������Ҫ������¸������ı�ǩ
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

	// �����ť���ò��ҵ����¼��ڰ�ť���淢�����ͰѰ�ť��״̬����Ϊ����״̬
	public void mousePressed(MouseEvent e) {
		if (isEnabled()) {
			if (bounds.contains(e.getPoint())) {
				state = BUTTON_DOWN;
			}
		}
	}

	// ͨ��֪ͨ���еĶ��������������˵����¼��ķ�ʽ��ģ�ⰴť�ĵ���
	// �����겻���ڰ�ť�����ɿ�����ô��ť����һ��״̬��û�ж����ᷢ��
	// 
	// 
	public void mouseReleased(MouseEvent e) {
		
		// ֻ������õİ�ť
		if (isEnabled()) {
			
			// ֻ���������Ȼ�ڰ�ť���������²��ܴ����¼�
			if (bounds.contains(e.getPoint())) {
				if (state == BUTTON_DOWN) {
					ActionEvent thisEvent = new ActionEvent(this,
							ActionEvent.ACTION_PERFORMED, "");
					
					// �����еļ�����֪����Щ���鱻����ȥ��
					for (int i = 0; i < MAX_LISTENERS; i++) {
						if (actionListeners[i] != null) {
							actionListeners[i].actionPerformed(thisEvent);
						}
					}
				}
				
				// �ָ����������״̬
				state = BUTTON_OVER;
			}
			
			// ������û���ڰ�ť�����ɿ��򽫰�ť��״̬����Ϊһ��״̬
			
			else {
				state = BUTTON_NORMAL;
			}
		}
	}

	public void mouseDragged(MouseEvent e) {
	}

	// ���ݵ�ǰ��״̬������λ�ø��°�ť��״̬
	public void mouseMoved(MouseEvent e) {
		if (isEnabled()) {
			
			// �������Ѿ�������Ҫ���κ�����
			if (state == BUTTON_DOWN)
				return;
			// �л����������״̬
			if (bounds.contains(e.getPoint())) {
				state = BUTTON_OVER;
			}
			// ������ָ����뿪��ť�����򽫰�ť״̬�ָ�Ϊһ��״̬
			else {
				state = BUTTON_NORMAL;
			}
		}
	}

	// ���ݰ�ť�ĵ�ǰ״̬��������
	public void paintComponent2D(Graphics2D g2d) {
		
		// ֻ���ƿ������
		if (isVisible()) {
			
			// �����ͼ���Ȼ���ͼ��
			if (group != null) {
				g2d.drawImage(((ButtonImageGroup) group).getFrame(state),
						xform, AnimationStrip.observer);
			}
			
			// ���������Ч���ı���ǩ���������
			if (label != null) {
				
				//�����ť�����£���ѱ�ǩ�Ŵ�һ�㣬������������ ���µ�
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

	// ���ݵ�ǰ��״̬�ʹ����x��y���������Ʊ�ǩ
	public void paintComponent2D(Graphics2D g2d, double dx, double dy) {
		
		// ֻ���ƿɼ����
		if (isVisible()) {
			
			// �����ͼ���Ȼ���ͼ��
			if (group != null) {
				g2d.drawImage(((ButtonImageGroup) group).getFrame(state),
						AffineTransform.getTranslateInstance(pos.getX() + dx,
								pos.getY() + dy), AnimationStrip.observer);
			}
			// ���������Ч���ı���ǩ���������
			if (label != null) {
				
				// �����ť�����£���ѱ�ǩ�Ŵ�һ�㣬������������ ���µ�
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

	// ����һ���������ť�������ַ���
	public String toString() {
		
		// �����ǩ��Ч�����ظ����toString���ϱ�ǩ��toString
		if (label != null) {
			return super.toString() + " " + label.toString();
		}
		
		// ����ֻ���ظ����toString
		return super.toString();
	}

} // Button2D