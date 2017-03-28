package org.xf.app.scene.iso;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import org.xf.app.actor2D.Actor2D;
import org.xf.app.actor2D.Actor2DGroup;
import org.xf.app.actor2D.AnimationStrip;

public class IsoTile extends Actor2D{

	public IsoTile(IsoTileGroup grp) {
		super(grp);
		currAnimation = group.getAnimationStrip(0);
		frameWidth = currAnimation.getFrameWidth();
		frameHeight = currAnimation.getFrameHeight();
	}
	// 使用它自身的变换绘制actor
	public void paint(Graphics2D g2d) {
		if (currAnimation != null) {
			g2d.drawImage(currAnimation.getCurrFrame(), AffineTransform.getTranslateInstance(this.getX(),this.getY()),
					AnimationStrip.observer);
		}
	}
}
