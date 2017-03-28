package org.xf.app.scene.tree;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import org.xf.app.actor2D.Actor2D;
import org.xf.app.scene.Scene;

public class TreeScene extends Scene{
	
	//�������ɳ����е�����QuadTree
	private QuadTree tree;
	//�ø����ı߽繹��һ��TreeScene
	public TreeScene(Rectangle2D bounds) {
		super(bounds,bounds);
		tree = new QuadTree(3,bounds);
		
	}//init

	//�Ѵ����Actor2D��ӵ�QuadTree
	public void add(Actor2D a){
		tree.insert(a);
	}
	

	//������ί�ɸ�QuadTree
	@Override
	public void update() {
		tree.update();
	}
	//����QuadTree��Ҷ�ӽڵ������
	public void paint(Graphics2D g2d) {
		tree.paint(g2d);
		tree.paintBounds(g2d, Color.BLACK);
	}//paint
	

}//TreeScene
