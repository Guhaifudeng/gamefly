package org.xf.app.scene;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import org.xf.app.actor2D.Actor2D;
/*
 * 类而非接口
 * （接口不能包含除类常量之外的属性）
 * 不愿强制子类实现add方法
 */
//提供场景管理的类
public abstract class Scene {
	
	//场景的总体边界
	protected Rectangle2D bounds;

	//场景中的可见部分，通常为JPabel窗体大小
	protected Rectangle2D viewable;
	public Scene(Rectangle2D v,Rectangle2D b){
		setViewable(v);
		setBounds(b);
	}
	//添加一个Actor到场景中，使用Actor2D对象的子类应该覆盖这个方法
	public  void add(Actor2D a){
		
	}//未使用抽象函数？？？？？
	//更新场景
	public abstract void  update();
	//在Graphics2D容器上绘制场景
	public abstract void paint(Graphics2D g2d);
	public final Rectangle2D getBounds() {
		return bounds;
		
	}

	public final void setBounds(Rectangle2D r) {
		this.bounds =new Rectangle2D.Double(r.getX(),r.getY(),r.getWidth(),r.getHeight());
	}

	public final Rectangle2D getViewable() {
		return viewable;
	}

	public final void setViewable(Rectangle2D r) {
		this.viewable = new Rectangle2D.Double(r.getX(),r.getY(),r.getWidth(),r.getHeight());
	}


	

}
