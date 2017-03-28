package org.xf.app.robot;

import org.xf.app.actor2D.Actor2D;
import org.xf.app.actor2D.Actor2DGroup;

//创建一个可以按照指定的方向移动并可以使用武器开发的简单机器人
public class Robot extends Actor2D {
	// 和当前动画关联的索引
	protected int currAnimIndex;
	// 为设计动画保存当前的动画
	protected int preAnimIndex;
	// Robot 开火状态
	public final static int SHOOTING = 8;
	public final static int DIR_NORTH = 0;
	public final static int DIR_SOUTH = 1;
	public final static int DIR_EAST = 2;
	public final static int DIR_WEST = 3;

	public Robot(Actor2DGroup grp) {
		super(grp);
		vel.setX(20);
		vel.setY(20);
		animWait = 3;
		currAnimIndex = 0;
		preAnimIndex = 0;
		currAnimation = group.getAnimationStrip(RobotGroup.WALKING_WEST);
		frameWidth = currAnimation.getFrameWidth();
		frameHeight = currAnimation.getFrameHeight();
	}

	// 更新机器人的位置，如果它在射击的话让它动起来
	public void update() {
		if (hasState(SHOOTING)){
			System.out.print("1");
			animate();
		}
			
		
		xform.setToTranslation(pos.getX(), pos.getY());
		updateBounds();
		checkBounds();
	}

	// 让机器人射击直到stopShooting方法被调用
	public void startShooting() {
		preAnimIndex = currAnimIndex;
		if (currAnimIndex % 2 == 0) {
			currAnimIndex++;
		}
		currAnimation = group.getAnimationStrip(currAnimIndex);
		currAnimation.reset();
		setState(SHOOTING);

	}

	public void stopShooting() {
		currAnimIndex = preAnimIndex;
		currAnimation = group.getAnimationStrip(currAnimIndex);
		currAnimation.reset();
		resetState(SHOOTING);
	}

	// 根据指定的方向，让机器人动起来
	public void move(int dir) {
		// 防止过多的射击
		resetState(SHOOTING);
		switch (dir) {
		case DIR_NORTH: {
			if (currAnimIndex != RobotGroup.WALKING_NORTH) {
				preAnimIndex = currAnimIndex;
				currAnimation = group
						.getAnimationStrip(RobotGroup.WALKING_NORTH);
				currAnimIndex = RobotGroup.WALKING_NORTH;
				currAnimation.reset();

			} else {
				animate();
				pos.translate(0, -vel.getY());

			}
		}
			break;
		case DIR_SOUTH: {
			if (currAnimIndex != RobotGroup.WALKING_SOUTH) {
				preAnimIndex = currAnimIndex;
				currAnimation = group
						.getAnimationStrip(RobotGroup.WALKING_SOUTH);
				currAnimIndex = RobotGroup.WALKING_SOUTH;
				currAnimation.reset();

			} else {
				animate();
				pos.translate(0, vel.getY());

			}
		}
			break;
		case DIR_WEST: {
			if (currAnimIndex != RobotGroup.WALKING_WEST) {
				preAnimIndex = currAnimIndex;
				currAnimation = group
						.getAnimationStrip(RobotGroup.WALKING_WEST);
				currAnimIndex = RobotGroup.WALKING_WEST;
				currAnimation.reset();

			} else {
				animate();
				pos.translate(-vel.getX(), 0);

			}
		}
			break;
		case DIR_EAST: {
			if (currAnimIndex != RobotGroup.WALKING_EAST) {
				preAnimIndex = currAnimIndex;
				currAnimation = group
						.getAnimationStrip(RobotGroup.WALKING_EAST);
				currAnimIndex = RobotGroup.WALKING_EAST;
				currAnimation.reset();

			} else {
				animate();
				pos.translate(vel.getX(), 0);

			}
		}
			break;
		default:
			break;
		}
	}

}
