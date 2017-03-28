package org.xf.gamefly.scene;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import org.xf.app.actor2D.Actor2D;
import org.xf.app.actor2D.Moveable;
import org.xf.gamefly.actor.Bullet;
import org.xf.gamefly.actor.Burst;
import org.xf.gamefly.actor.Enemy;
import org.xf.gamefly.actor.Hero;

public class QuadTree extends QuadNode {
	// �����������
	protected int globalDepth;
	private int enemyNum;
	// ȫ��Ҷ�ӽڵ�����
	public static LinkedList leaves = new LinkedList();
	// �������Ƶ������ȫ������
	public static LinkedList paintList = new LinkedList();

	// �ô������ȡ�����߽�Ϳɼ���Ļ�߽紴��һ��QuadTree
	public QuadTree(int depth, Rectangle2D r) {
		super(null, depth, r);
		enemyNum = 0;
		// ������ȱ������0
		if (depth <= 0) {
			throw new IllegalArgumentException(
					"depth must be greater than zero");

		}
		globalDepth = depth;
		// ��ʼ���������
		//objects = new LinkedList();
	}

	// ��ȫ��Ҷ�����������һ��Ҷ�ӽڵ�
	public static void addLeaf(QuadNode node) {
		if (!leaves.contains(node)) {
			leaves.add(node);
		}
	}

	// ��ȫ�ֻ������������һ��Moveable����
	public static void addToPaintList(Moveable m) {
		if (!paintList.contains(m)) {
			paintList.add(m);
		}
	}

	// QuadTree���븲��insert�����������ڳ����߽��е�����
	public void insert(Moveable c) {
		// ������岻�ڳ����߽��У��������ӵ�����

		if (!bounds.contains(c.getBounds())
				&& !bounds.intersects(c.getBounds())) {
//			if (!objects.contains(c)) {
//				objects.add(c);
//			}
			c = null;
			//((Actor2D)c).setState(Actor2D.STATE_DEAD);
			return;
		}
		//System.out.print("1");
		// ��������ӵ�ȫ�ֻ���������
		addToPaintList(c);
		// ��ͼ��������ӵ����еĺ��ӽڵ���ȥ
		for (int i = 0; i < 4; i++) {
			if (nodes[i] != null) {
				nodes[i].insert(c, false);
			}
		}
	}
	public void update() {

//		if (objects != null && !objects.isEmpty()) {
//			for (int i = 0; i < objects.size(); i++) {
//			//	Moveable a = (Moveable) objects.get(i);
//				Actor2D a = (Actor2D) objects.get(i);
//				a.update();
//
//				//
//				if (bounds.contains(a.getBounds())
//						|| bounds.intersects(a.getBounds())) {
//					addToPaintList(a);
//					insert((Moveable) objects.remove(i), false);
//				}else{		
//						objects.remove(i);
//					
//				}
//				
//			}
//		}
		Actor2D a;
		Burst b;
		if( paintList!=null){
			enemyNum = 0;
		for (int i = 0; i < paintList.size(); i++) {
			a = (Actor2D) paintList.get(i);
			a.processCollisions();
			if(a instanceof Enemy){
				enemyNum++;
			}
			if(a instanceof Hero && a.getState()==Actor2D.STATE_DEAD ){
				
//				b = ((Hero)a).getHeroBurst();
//				paintList.remove(a);
//				i--;
//				if(b!=null){
////			        addToPaintList(b);
//					insert(b);
//				}
			}else if(a instanceof Enemy && a.getState()==Actor2D.STATE_DEAD ){
				//System.out.print("1");
				b = ((Enemy)a).getEnemyBurst();
				paintList.remove(a);
				if(b!=null){
					//System.out.print("-");
					//addToPaintList(b);
					insert(b);
				}
				i--;
			}else if(a.getState()==Actor2D.STATE_DEAD ){
				paintList.remove(a);
			}else if(a instanceof Bullet.BulletTrack){
				if(((Bullet.BulletTrack) a).moveLength>bounds.getHeight())
				a.setState(Actor2D.STATE_DEAD);
			}
				

		}
		}
		if(leaves!=null)
		for (int i = 0; i < leaves.size(); i++) {
			((QuadNode) leaves.get(i)).update();
		}
		

	}

	public void paint(Graphics2D g2d) {
		Actor2D a;
		if( paintList!=null){
			enemyNum =0;
		for (int i = 0; i < paintList.size(); i++) {
			a = (Actor2D) paintList.get(i);
			if(a instanceof Enemy){
				enemyNum++;
			}
			if(a.getState()!=Actor2D.STATE_DEAD){
				
				
				if (bounds.contains((a.getBounds()))
						|| bounds.intersects(a.getBounds())) {
					
					a.paint(g2d);
				
				}else {
					a.setState(Actor2D.STATE_DEAD);
				}
			}else{
				paintList.remove(a);
				i--;
			}
		}
		setNumEnemy(enemyNum);
		}

	}
	public synchronized int getNumEnemy(){
		return enemyNum;
	}
	public void setNumEnemy(int num){
		enemyNum = num;
	}

	public void paintBounds(Graphics2D g2d, Color color) {
		//
		for (int i = 0; i < leaves.size(); i++) {
			((QuadNode) leaves.get(i)).paintBounds(g2d, color);

		}
	}

	public void clear() {	
		//objects.clear();
		paintList.clear();
		for (int i = 0; i < leaves.size(); i++) {
			((QuadNode) leaves.get(i)).clear();

		}
		//leaves.clear();
	}
}// QuadTree
