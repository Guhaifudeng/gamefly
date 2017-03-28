package org.xf.app.scene.space;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import org.xf.app.scene.Scene;

//�����Ŀ��Թ����ĳ���
public class SpaceScene extends Scene {
	// �����е�����
	protected StaticActor[] scenery;

	public SpaceScene(Rectangle2D v) {
		super(v, v);
		scenery = null;
	}

	public void setScenery(StaticActor[] v) {
		scenery = v;
	}

	// �ƶ������е�����
	public void update() {
		if (scenery == null)
			return;
		
		for (int i = 0; i < scenery.length; i++) {
			if (scenery[i] != null) {
				// �����뿪�����������ٴ�ת����
				
				if (scenery[i].getX() <= -scenery[i].getWidth()) {
					// Ϊ��һ֡׼��scenery
					//System.out.print("d");
					scenery[i].setX(scenery[i].getX() + scenery[i].getWidth());
				}
				scenery[i].update();
			}
		}
	}

	public void paint(Graphics2D g2d) {
		if (scenery == null)
			return;
		g2d.setClip(getBounds());
		for (int i = 0; i < scenery.length; i++) {
			if (scenery[i] != null) {
				
				scenery[i].paint(g2d);
				// ������岻����ȫ�ڱ߽��ڲ�������ӵ�������
				if (scenery[i].getX() + scenery[i].getWidth() < bounds
						.getWidth()) {
					//System.out.print("d");
					scenery[i].paint(g2d,//??????????Ϊʲô��
							scenery[i].getX() + scenery[i].getWidth(),
							scenery[i].getY());
				}
			}
		}
	}
} // SpaceScene
