package org.xf.gamefly.scene;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import org.xf.app.scene.Scene;
import org.xf.gamefly.actor.StaticActor;

//基础的可以滚动的场景
public class SpaceScene extends Scene {
	// 场景中的物体
	protected StaticActor scenery;

	public SpaceScene(Rectangle2D v) {
		super(v, v);
		scenery = null;
	}

	public void setScenery(StaticActor v) {
		scenery = v;
	}

	// 移动场景中的物体
	public  void update() {
		if (scenery == null)
			return;
		
	
			if (scenery != null) {
				// 物体离开场景后让它再次转回来
				
				if (scenery.getY() >= bounds.getHeight()+10) {
					// 为下一帧准备scenery
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
				// 如果物体不是完全在边界内侧则将它添加到自身上
				if (scenery.getY() >=0) {
					//System.out.print("d");
					scenery.paint(g2d,scenery.getX() ,scenery.getY()-scenery.getHeight());
				}
			}
		
	}
} // SpaceScene
