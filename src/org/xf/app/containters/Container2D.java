package org.xf.app.containters;

import java.util.Enumeration;
import java.util.Vector;

import org.xf.app.actor2D.Vector2D;
import org.xf.app.components.Component2D;

//����һ�����������������������
public abstract class Container2D extends Component2D {
	// ��������Ķ�̬Vector���Լ����������ö��
	protected Vector components;
	protected Enumeration e;

	//ʹ�ô���ı���ͼ���λ�ô���һ���µ�Container2D����
	protected Container2D(Vector2D p) {
		super();
		components = new Vector();
		pos = p;
		if (pos == null) {
			pos = new Vector2D.Double();
		}
		updateBounds();
	}

	// ������������ӵ�ָ����λ��
	public abstract void add(Component2D c, double x, double y);

	// ���������ı߽�
	public abstract void updateBounds();
} // Container2D