package org.xf.app.scene.tree;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import org.xf.app.actor2D.Actor2D;
import org.xf.app.actor2D.Moveable;
import org.xf.gamefly.actor.Burst;
import org.xf.gamefly.actor.Enemy;
import org.xf.gamefly.actor.Hero;

public class QuadTree extends QuadNode {
	// 树的总体深度
	protected int globalDepth;
	// 全局叶子节点链表
	public static LinkedList leaves = new LinkedList();
	// 将被绘制的物体的全局链表
	public static LinkedList paintList = new LinkedList();

	// 用传入的深度、总体边界和可见屏幕边界创建一个QuadTree
	public QuadTree(int depth, Rectangle2D r) {
		super(null, depth, r);
		// 总体深度必须大于0
		if (depth <= 0) {
			throw new IllegalArgumentException(
					"depth must be greater than zero");

		}
		globalDepth = depth;
		// 初始化溢出链表
		objects = new LinkedList();
	}

	// 在全局叶子链表中添加一个叶子节点
	public static void addLeaf(QuadNode node) {
		if (!leaves.contains(node)) {
			leaves.add(node);
		}
	}

	// 在全局绘制链表中添加一个Moveable物体
	public static void addToPaintList(Moveable m) {
		if (!paintList.contains(m)) {
			paintList.add(m);
		}
	}

	// QuadTree必须覆盖insert方法来处理不在场景边界中的物体
	public void insert(Moveable c) {
		// 如果物体不在场景边界中，则把它添加到堆中

		if (!bounds.contains(c.getBounds())
				&& !bounds.intersects(c.getBounds())) {
			if (!objects.contains(c)) {
				objects.add(c);
			}
			return;
		}
		//System.out.print("1");
		// 将物体添加到全局绘制链表中
		addToPaintList(c);
		// 试图将物体添加到所有的孩子节点中去
		for (int i = 0; i < 4; i++) {
			if (nodes[i] != null) {
				nodes[i].insert(c, false);
			}
		}
	}
	public void update() {

		if (objects != null && !objects.isEmpty()) {
			for (int i = 0; i < objects.size(); i++) {
				Moveable a = (Moveable) objects.get(i);
				a.update();

				//
				if (bounds.contains(a.getBounds())
						|| bounds.intersects(a.getBounds())) {
					insert((Moveable) objects.remove(), false);
				}
			}
		}
		Actor2D a;
		Burst b;
		for (int i = 0; i < paintList.size(); i++) {
			a = (Actor2D) paintList.get(i);
			a.processCollisions();
			
			if(a instanceof Hero && a.getState()==Actor2D.STATE_DEAD ){
				
				b = ((Hero)a).getHeroBurst();
				if(b!=null){
					addToPaintList(b);
					insert(b);
				}
			}
			if(a instanceof Enemy && a.getState()==Actor2D.STATE_DEAD ){
				//System.out.print("1");
				b = ((Enemy)a).getEnemyBurst();
				if(b!=null){
					//System.out.print("-");
					//addToPaintList(b);
					insert(b);
				}
			}
				

		}

		for (int i = 0; i < leaves.size(); i++) {
			((QuadNode) leaves.get(i)).update();
		}

	}

	public void paint(Graphics2D g2d) {
		Actor2D a;
		for (int i = 0; i < paintList.size(); i++) {
			a = (Actor2D) paintList.get(i);
			//
			if(a.getState()!=Actor2D.STATE_DEAD){
				
				if (bounds.contains((a.getBounds()))
						|| bounds.intersects(a.getBounds())) {
					a.paint(g2d);
				
				}
			}else{
				
				paintList.remove(a);
				
			}
		}

	}

	public void paintBounds(Graphics2D g2d, Color color) {
		//
		for (int i = 0; i < leaves.size(); i++) {
			((QuadNode) leaves.get(i)).paintBounds(g2d, color);

		}
	}
}// QuadTree
