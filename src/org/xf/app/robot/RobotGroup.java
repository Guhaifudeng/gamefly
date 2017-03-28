package org.xf.app.robot;

import javax.swing.JPanel;

import org.xf.app.actor2D.Actor2DGroup;
import org.xf.app.actor2D.AnimationStrip;
import org.xf.app.actor2D.Animator;
import org.xf.app.actor2D.ImageLoader;

public class RobotGroup extends Actor2DGroup {
	// 预先定义的动画序列
	public static final int WALKING_NORTH = 0;
	public static final int SHOOTING_NORTH = 1;
	public static final int WALKING_SOUTH = 2;
	public static final int SHOOTING_SOUTH = 3;
	public static final int WALKING_EAST = 4;
	public static final int SHOOTING_EAST = 5;
	public static final int WALKING_WEST = 6;
	public static final int SHOOTING_WEST = 7;
	private int[] id;
	private String[] idPathName;
	// 创建一个新的RobotGroup对象
	public RobotGroup() {
		super();
		animations = new AnimationStrip[8];
	}
//	void initdata(){
//		//private int[] id;
//		//private String[] idPathName;
//	}

	// 初始化8个动画序列
	@Override
	public void init(JPanel a) {
		// TODO Auto-generated method stub
		ImageLoader loader;

		// 北
		loader = new ImageLoader(a, "images/myplan_1.png", true);
		animations[WALKING_NORTH] = new AnimationStrip();
		for (int i = 0; i < 4; i++) {
			animations[WALKING_NORTH].addFrame(loader.extractCellScaled(0,
					110 * i, 150, 110,100,80));
			// 可以设置成动画状态
		}
		// animations[WALKING_NORTH].addFrame(loader.getImage());
		animations[WALKING_NORTH].setAnimator(new Animator.Looped());

		// loader = new ImageLoader(a,"images/robot_east.png",true);
		animations[SHOOTING_NORTH] = new AnimationStrip();
		animations[SHOOTING_NORTH].addFrame(loader.extractCell(0, 0, 80,
				95));
		animations[SHOOTING_NORTH].setAnimator(new Animator.Looped());

		// 南
		loader = new ImageLoader(a, "images/myplan_1.png", true);
		animations[WALKING_SOUTH] = new AnimationStrip();
		for (int i = 0; i < 4; i++) {
			animations[WALKING_SOUTH].addFrame(loader.extractCell(0,
					110 * i, 150, 110));
		}
		animations[WALKING_SOUTH].setAnimator(new Animator.Looped());

		//loader = new ImageLoader(a, "", true);
		animations[SHOOTING_SOUTH] = new AnimationStrip();
		animations[SHOOTING_SOUTH].addFrame(loader.extractCell(0, 0, 150,
				110));
		animations[SHOOTING_SOUTH].setAnimator(new Animator.Looped());

		// 东
		loader = new ImageLoader(a, "images/myplan_1.png", true);

		animations[WALKING_EAST] = new AnimationStrip();
		for (int i = 0; i < 4; i++) {
			animations[WALKING_EAST].addFrame(loader.extractCell(0,
					110 * i, 150, 110));
		}
		animations[WALKING_EAST].setAnimator(new Animator.Looped());

		// loader = new ImageLoader(a,"",true);
		animations[SHOOTING_EAST] = new AnimationStrip();
		animations[SHOOTING_EAST].addFrame(loader.extractCell(0, 0, 150,
				110));
		animations[SHOOTING_EAST].setAnimator(new Animator.Looped());

		// 西
		loader = new ImageLoader(a, "images/myplan_1.png", true);
		animations[WALKING_WEST] = new AnimationStrip();
		for (int i = 0; i < 4; i++) {
			animations[WALKING_WEST].addFrame(loader.extractCell(0,
					110 * i, 150, 110));
		}
		animations[WALKING_WEST].setAnimator(new Animator.Looped());

		// loader = new ImageLoader(a,"images/robot_east.png",true);
		animations[SHOOTING_WEST] = new AnimationStrip();
		animations[SHOOTING_WEST].addFrame(loader.extractCell(0, 0, 150,
				110));
		animations[SHOOTING_WEST].setAnimator(new Animator.Looped());

	}

}// RobotGroup2D
