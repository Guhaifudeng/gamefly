package org.xf.app.scene.space;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import org.xf.app.scene.Scene;

//基础的可以滚动的场景
public class SpaceScene extends Scene {
	// 场景中的物体
	protected StaticActor[] scenery;

	public SpaceScene(Rectangle2D v) {
		super(v, v);
		scenery = null;
	}

	public void setScenery(StaticActor[] v) {
		scenery = v;
	}

	// 移动场景中的物体
	public void update() {
		if (scenery == null)
			return;
		
		for (int i = 0; i < scenery.length; i++) {
			if (scenery[i] != null) {
				// 物体离开场景后让它再次转回来
				
				if (scenery[i].getX() <= -scenery[i].getWidth()) {
					// 为下一帧准备scenery
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
				// 如果物体不是完全在边界内侧则将它添加到自身上
				if (scenery[i].getX() + scenery[i].getWidth() < bounds
						.getWidth()) {
					//System.out.print("d");
					scenery[i].paint(g2d,//??????????为什么加
							scenery[i].getX() + scenery[i].getWidth(),
							scenery[i].getY());
				}
			}
		}
	}
} // SpaceScene
