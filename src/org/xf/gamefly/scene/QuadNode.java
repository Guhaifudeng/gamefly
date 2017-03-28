package org.xf.gamefly.scene;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import org.xf.app.actor2D.Actor2D;
import org.xf.app.actor2D.Moveable;


//QuadTree�ṹ�е�һ���ڵ�
public class QuadNode {
	// ������ڵ����һ��Ψһid
	protected static int UNIQUE_ID = 0;
	// ��־����ڵ��Ψһ����
	protected int id;
	// ��������ڵ�ĸ��ڵ�
	protected QuadNode parent;
	// ���QuadNode���ӽڵ�����
	protected QuadNode[] nodes;
	// �������ڵ�ΪҶ�ӽڵ㣬��Ϊtrue
	protected boolean leaf;
	// ����ڵ������������������
	protected LinkedList objects;
	// �ڵ�߽�
	protected Rectangle2D bounds;

	// Ĭ�Ϲ��캯��
	private QuadNode() {
		id = -1;
		parent = null;
		nodes = null;
		leaf = true;
		bounds = null;

	}

	// �������׵㡢��ȡ��߽繹��һ��QuadNode
	public QuadNode(QuadNode p, int depth, Rectangle2D r) {
		parent = p;
		bounds = r;
		id = UNIQUE_ID++;

		// ���ʣ������Ϊ�㣬������ڵ�ΪҶ�ӽڵ�
		if (depth == 0) {
			leaf = true;
			objects = new LinkedList();
			nodes = null;
			QuadTree.addLeaf(this);

		} else {// �������4�����ӽڵ�
			leaf = false;
			objects = null;
			nodes = new QuadNode[4];
			double x = bounds.getX();
			double y = bounds.getY();
			double w = bounds.getWidth();
			double h = bounds.getHeight();

			// �����ӽڵ�
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

	// //�������ڵ���Ҷ�ӽڵ��򷵻�true
	public final boolean isLeaf() {
		return leaf;
	}

	// ���Խ�һ�������������뵽����ڵ���
	public void insert(Moveable c,// Ҫ�����Moveable����
			boolean propagate) {// ����棬��c��������ڵ�ı߽磬����һ������
		// ���Բ���  ���������н��磬�������
		if (bounds.contains(c.getBounds()) || bounds.intersects(c.getBounds())) {

			if (isLeaf()) {// ���ΪҶ�ӽڵ㣬����
				if (objects == null) {
					objects = new LinkedList();

				}
				if (!objects.contains(c)) {
					objects.add(c);
				}
			} else {// ��������һ�����
				for (int i = 0; i < 4; i++) {
					if (nodes[i] != null) {
						nodes[i].insert(c, false);
					}
				}
			}
		} else {// ��������������������һ����������
			if (propagate) {
				if (parent != null) {
					parent.insert(c, true);
				}
			}
		}
	}
	//������ڵ�����������������ƽ�ƣ�x,y��
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
		//�������������ٱ�����ڵ�����������޳�
		Moveable m;
		for(int i =0;i<objects.size();i++){
			m = (Moveable) objects.get(i);
			
			//��֤�����״̬��dead���ӵ�ǰ�б���ɾ������û�����������
			//���������Ƿ�Ҫ�뿪����ڵ㣬����ǣ�����һ����
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
		//���������屻ɾ����������Ҫ��ø��º�Ĵ�С
		int size = objects.size();
		//�������ڵ�֮������֮��ĳ�ͻ
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
