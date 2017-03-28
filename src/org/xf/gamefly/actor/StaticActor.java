package org.xf.gamefly.actor;

import org.xf.app.actor2D.Actor2D;
import org.xf.app.actor2D.Actor2DGroup;
import org.xf.app.robot.RobotGroup;
import org.xf.gamefly.graphics.StaticActorGroup;

public class StaticActor extends Actor2D{
 
	public StaticActor(StaticActorGroup grp) {
		super(grp);
		currAnimation = group.getAnimationStrip(0);
		frameWidth = currAnimation.getFrameWidth();
		frameHeight = currAnimation.getFrameHeight();

			// TODO Auto-generated constructor stub
	}

}
