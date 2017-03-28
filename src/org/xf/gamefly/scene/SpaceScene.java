package org.xf.gamefly.scene;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import org.xf.app.scene.Scene;
import org.xf.gamefly.actor.StaticActor;

//�����Ŀ��Թ����ĳ���
public class SpaceScene extends Scene {
	// �����е�����
	protected StaticActor scenery;

	public SpaceScene(Rectangle2D v) {
		super(v, v);
		scenery = null;
	}

	public void setScenery(StaticActor v) {
		scenery = v;
	}

	// �ƶ������е�����
	public  void update() {
		if (scenery == null)
			return;
		
	
			if (scenery != null) {
				// �����뿪�����������ٴ�ת����
				
				if (scenery.getY() >= bounds.getHeight()+10) {
					// Ϊ��һ֡׼��scenery
					//System.out.print("d");
					scenery.setY(0);
				}
				scenery.update();
			}
		
	}

	public   void paint(Graphics2D g2d) {
		if (scenery == null)
			return;
		//g2d.setClip(getBounds());

			if (scenery != null) {
				
				scenery.paint(g2d);
				// ������岻����ȫ�ڱ߽��ڲ�������ӵ�������
				if (scenery.getY() >=0) {
					//System.out.print("d");
					scenery.paint(g2d,scenery.getX() ,scenery.getY()-scenery.getHeight());
				}
			}
		
	}
} // SpaceScene
