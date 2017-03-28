package org.xf.app.scene.tree;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import org.xf.app.actor2D.Actor2D;
import org.xf.app.scene.Scene;

public class TreeScene extends Scene{
	
	//用来容纳场景中的物体QuadTree
	private QuadTree tree;
	//用给定的边界构造一个TreeScene
	public TreeScene(Rectangle2D bounds) {
		super(bounds,bounds);
		tree = new QuadTree(3,bounds);
		
	}//init

	//把传入的Actor2D添加到QuadTree
	public void add(Actor2D a){
		tree.insert(a);
	}
	

	//将更新委派给QuadTree
	@Override
	public void update() {
		tree.update();
	}
	//绘制QuadTree和叶子节点的轮廓
	public void paint(Graphics2D g2d) {
		tree.paint(g2d);
		tree.paintBounds(g2d, Color.BLACK);
	}//paint
	

}//TreeScene
