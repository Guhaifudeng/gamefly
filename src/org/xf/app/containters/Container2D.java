package org.xf.app.containters;

import java.util.Enumeration;
import java.util.Vector;

import org.xf.app.actor2D.Vector2D;
import org.xf.app.components.Component2D;

//定义一个可以容纳其他组件的容器
public abstract class Container2D extends Component2D {
	// 容纳组件的动态Vector，以及所有组件的枚举
	protected Vector components;
	protected Enumeration e;

	//使用传入的背景图像和位置创建一个新的Container2D对象
	protected Container2D(Vector2D p) {
		super();
		components = new Vector();
		pos = p;
		if (pos == null) {
			pos = new Vector2D.Double();
		}
		updateBounds();
	}

	// 将传入的组件添加到指定的位置
	public abstract void add(Component2D c, double x, double y);

	// 更新容器的边界
	public abstract void updateBounds();
} // Container2D