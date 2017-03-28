package org.xf.app.scene.iso;

import org.xf.app.actor2D.Actor2D;
import org.xf.app.actor2D.Actor2DGroup;

public class IsoMan extends Actor2D {

	public IsoMan(IsoManGroup grp) {
		super(grp);
		currAnimation = group.getAnimationStrip(0);
		frameWidth = currAnimation.getFrameWidth();
		frameHeight = currAnimation.getFrameHeight();

		// TODO Auto-generated constructor stub
	}
}
