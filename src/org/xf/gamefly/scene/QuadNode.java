package org.xf.gamefly.scene;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import org.xf.app.actor2D.Actor2D;
import org.xf.app.actor2D.Moveable;


//QuadTree结构中的一个节点
public class QuadNode {
	// 分配给节点的下一个唯一id
	protected static int UNIQUE_ID = 0;
	// 标志这个节点的唯一整数
	protected int id;
	// 包含这个节点的父节点
	protected QuadNode parent;
	// 这个QuadNode孩子节点数组
	protected QuadNode[] nodes;
	// 如果这个节点为叶子节点，则为true
	protected boolean leaf;
	// 这个节点所包含的物体的链表
	protected LinkedList objects;
	// 节点边界
	protected Rectangle2D bounds;

	// 默认构造函数
	private QuadNode() {
		id = -1;
		parent = null;
		nodes = null;
		leaf = true;
		bounds = null;

	}

	// 给定父亲点、深度、边界构建一个QuadNode
	public QuadNode(QuadNode p, int depth, Rectangle2D r) {
		parent = p;
		bounds = r;
		id = UNIQUE_ID++;

		// 如果剩余的深度为零，则这个节点为叶子节点
		if (depth == 0) {
			leaf = true;
			objects = new LinkedList();
			nodes = null;
			QuadTree.addLeaf(this);

		} else {// 否则包含4个孩子节点
			leaf = false;
			objects = null;
			nodes = new QuadNode[4];
			double x = bounds.getX();
			double y = bounds.getY();
			double w = bounds.getWidth();
			double h = bounds.getHeight();

			// 创建子节点
			nodes[0] = new QuadNode(this, depth - 1, new Rectangle2D.Double(x,
					y + h / 2, w / 2, h / 2));
			nodes[1] = new QuadNode(this, depth - 1, new Rectangle2D.Double(x
					+ w / 2, y + h / 2, w / 2, h / 2));
			nodes[2] = new QuadNode(this, depth - 1, new Rectangle2D.Double(x
					+ w / 2, y, w / 2, h / 2));
			nodes[3] = new QuadNode(this, depth - 1, new Rectangle2D.Double(x,
					y, w / 2, h / 2));
		}
	}

	// //如果这个节点是叶子节点则返回true
	public final boolean isLeaf() {
		return leaf;
	}

	// 尝试将一个传入的物体插入到这个节点中
	public void insert(Moveable c,// 要插入的Moveable物体
			boolean propagate) {// 如果真，则c不在这个节点的边界，向上一级插入
		// 尝试插入  包含或者有交界，都会插入
		if (bounds.contains(c.getBounds()) || bounds.intersects(c.getBounds())) {

			if (isLeaf()) {// 如果为叶子节点，插入
				if (objects == null) {
					objects = new LinkedList();

				}
				if (!objects.contains(c)) {
					objects.add(c);
				}
			} else {// 继续向下一层插入
				for (int i = 0; i < 4; i++) {
					if (nodes[i] != null) {
						nodes[i].insert(c, false);
					}
				}
			}
		} else {// 否则在允许的情况下向上一级插入物体
			if (propagate) {
				if (parent != null) {
					parent.insert(c, true);
				}
			}
		}
	}
	//将这个节点所包含的所有物体平移（x,y）
	public void moveAll(double x,double y){
		if(objects ==null||objects.isEmpty()) return;
		Actor2D a;
		for(int i = 0;i < objects.size();i++){
			a = (Actor2D) objects.get(i);
			a.moveBy(x, y);
		}
	}
	public void update(){
		if(objects == null||objects.isEmpty()) return ;
		//更新链表，将不再被这个节点包含的物体剔除
		Moveable m;
		for(int i =0;i<objects.size();i++){
			m = (Moveable) objects.get(i);
			
			//验证物体的状态，dead，从当前列表里删除它，没有则继续向下
			//测试物体是否要离开这个节点，如果是，向上一级插
			if(((Actor2D)m).getState()!=Actor2D.STATE_DEAD){
				m.update();
				if(!bounds.contains(m.getBounds())&&!bounds.intersects(m.getBounds())){
					insert((Moveable)objects.remove(i),true);
				}
				
			}else {
				m =(Moveable) objects.remove(i);
				m=null;
				
			}
			
			
		}
		//可能有物体被删除，所以需要获得更新后的大小
		int size = objects.size();
		//检测这个节点之内物体之间的冲突
		for(int i = 0;i<size-1;i++){
			Moveable a = (Moveable) objects.get(i);
				for(int j=i+1;j<size;j++)
				{	
					Moveable b = (Moveable)objects.get(j);
					if(a.collidesWith(b)||b.collidesWith(a)){
						a.addCollision(b);
						b.addCollision(a);
					}
				}
		}
	}
	public void paintBounds(Graphics2D g2d,Color c){
		if(c == null) c = Color.RED;
		g2d.setPaint(c);
		g2d.draw(bounds);
	}

	public void clear() {
		objects.clear();
		
	}
	
	
	
}//QuadNode
