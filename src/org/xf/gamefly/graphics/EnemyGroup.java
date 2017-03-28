package org.xf.gamefly.graphics;

import javax.swing.JPanel;

import org.xf.app.actor2D.Actor2DGroup;
import org.xf.app.actor2D.AnimationStrip;
import org.xf.app.actor2D.Animator;
import org.xf.app.actor2D.ImageLoader;

public class EnemyGroup extends Actor2DGroup {
	// 四张小兵-正弦、横排、随机、对称
	public static final int ENEMY_SMALL_1 = 0;
	public static final int ENEMY_SMALL_2 = 1;
	public static final int ENEMY_SMALL_3 = 2;
	public static final int ENEMY_SMALL_4 = 3;
	// 2张中兵-直弹、激光 均可停滞而后转动
	public static final int ENEMY_MIDDLE_1 = 4;
	public static final int ENEMY_MIDDLE_2 = 5;
	// 2张中兵-半散弹、跟踪弹
	public static final int ENEMY_MIDDLE_3 = 6;
	public static final int ENEMY_MIDDLE_4 = 7;
	public static final int ENEMY_MIDDLE_5 = 8;

	// 大头兵
	public static final int ENEMY_LARGE_1 = 9;
//	public static final int ENEMY_LARGE_2 = 10;
//
//	// 石头滚动
//	public static final int ENEMY_STONE_1 = 11;
//	public static final int ENEMY_STONE_2 = 12;
//	public static final int ENEMY_STONE_3 = 13;
//	public static final int ENEMY_STONE_4 = 14;

	public EnemyGroup() {
		super();
		animations = new AnimationStrip[15];
	}

	@Override
	public void init(JPanel a) {
		// TODO Auto-generated method stub

		ImageLoader loader;
		loader = new ImageLoader(a, "myimage/sm1.gif", true);
		animations[ENEMY_SMALL_1] = new AnimationStrip();
		for(int i =0;i<2;i++){
			animations[ENEMY_SMALL_1].addFrame(loader.extractCellScaled(150*i, 0,
				150,200, 40, 45));
		}
		animations[ENEMY_SMALL_1].setAnimator(new Animator.Looped());

		loader = new ImageLoader(a, "myimage/sm2.gif", true);
		animations[ENEMY_SMALL_2] = new AnimationStrip();
		for(int i =0;i<2;i++){
			animations[ENEMY_SMALL_2].addFrame(loader.extractCellScaled(100*i,0,
				100,160, 40, 50));
		}
		animations[ENEMY_SMALL_2].setAnimator(new Animator.Looped());

		loader = new ImageLoader(a, "myimage/sm3.gif", true);
		animations[ENEMY_SMALL_3] = new AnimationStrip();
		
		for (int i = 0; i < 2; i++) {
			animations[ENEMY_SMALL_3].addFrame(loader.extractCellScaled(
					i * 150, 0, 150, 150, 40, 40));

		}
		
		animations[ENEMY_SMALL_3].setAnimator(new Animator.Looped());

		loader = new ImageLoader(a, "myimage/sm4.gif", true);
		animations[ENEMY_SMALL_4] = new AnimationStrip();
		for (int i = 0; i < 2; i++) {
			animations[ENEMY_SMALL_4].addFrame(loader.extractCellScaled(150*i,0,
					150,200,45,45));
		}
		animations[ENEMY_SMALL_4].setAnimator(new Animator.Looped());

		loader = new ImageLoader(a, "myimage/md1.gif", true);
		animations[ENEMY_MIDDLE_1] = new AnimationStrip();
		for(int i =0;i<2;i++){
			animations[ENEMY_MIDDLE_1].addFrame(loader.extractCellScaled(100*i,0,100,200, 60, 90));
		}
		animations[ENEMY_MIDDLE_1].setAnimator(new Animator.Looped());
		
		
		loader = new ImageLoader(a, "myimage/md2.gif", true);
		animations[ENEMY_MIDDLE_2] = new AnimationStrip();
		for(int i =0;i<2;i++){
			animations[ENEMY_MIDDLE_2].addFrame(loader.extractCellScaled(100*i,0,100,200, 60, 90));
		}
		animations[ENEMY_MIDDLE_2].setAnimator(new Animator.Looped());

		loader = new ImageLoader(a, "myimage/md3.gif", true);
		animations[ENEMY_MIDDLE_3] = new AnimationStrip();
		for(int i =0;i<2;i++){
			animations[ENEMY_MIDDLE_3].addFrame(loader.extractCellScaled(100*i,0,100,200, 60, 90));
		}
		animations[ENEMY_MIDDLE_3].setAnimator(new Animator.Looped());
		
		
		loader = new ImageLoader(a, "myimage/md4.gif", true);
		animations[ENEMY_MIDDLE_4] = new AnimationStrip();
		for(int i =0;i<2;i++){
			animations[ENEMY_MIDDLE_4].addFrame(loader.extractCellScaled(100*i,0,100,200, 60, 90));
		}
		animations[ENEMY_MIDDLE_4].setAnimator(new Animator.Looped());

		loader = new ImageLoader(a, "myimage/md5.gif", true);
		animations[ENEMY_MIDDLE_5] = new AnimationStrip();
		for(int i =0;i<2;i++){
			animations[ENEMY_MIDDLE_5].addFrame(loader.extractCellScaled(100*i,0,100,200, 60, 90));
		}
		animations[ENEMY_MIDDLE_5].setAnimator(new Animator.Looped());
//
//		loader = new ImageLoader(a, "myimage/player.png", true);
//		animations[ENEMY_STONE_1] = new AnimationStrip();
//		animations[ENEMY_STONE_1].addFrame(loader.extractCellScaled(0, 0,
//				loader.getImageWidth(), loader.getImageHeight(), 40, 40));
//		animations[ENEMY_STONE_1].setAnimator(new Animator.Looped());

//		loader = new ImageLoader(a, "myimage/player.png", true);
//		animations[ENEMY_STONE_2] = new AnimationStrip();
//		animations[ENEMY_STONE_2].addFrame(loader.extractCellScaled(0, 0,
//				loader.getImageWidth(), loader.getImageHeight(), 40, 40));
//		animations[ENEMY_STONE_2].setAnimator(new Animator.Looped());
//
//		loader = new ImageLoader(a, "myimage/player.png", true);
//		animations[ENEMY_STONE_3] = new AnimationStrip();
//		animations[ENEMY_STONE_3].addFrame(loader.extractCellScaled(0, 0,
//				loader.getImageWidth(), loader.getImageHeight(), 40, 40));
//		animations[ENEMY_STONE_3].setAnimator(new Animator.Looped());
//
//		loader = new ImageLoader(a, "myimage/player.png", true);
//		animations[ENEMY_STONE_4] = new AnimationStrip();
//		animations[ENEMY_STONE_4].addFrame(loader.extractCellScaled(0, 0,
//				loader.getImageWidth(), loader.getImageHeight(), 40, 40));
//		animations[ENEMY_STONE_4].setAnimator(new Animator.Looped());
//
//		loader = new ImageLoader(a, "myimage/player.png", true);
//		animations[ENEMY_LARGE_1] = new AnimationStrip();
//		animations[ENEMY_LARGE_1].addFrame(loader.extractCellScaled(0, 0,
//				loader.getImageWidth(), loader.getImageHeight(), 40, 40));
//		animations[ENEMY_LARGE_1].setAnimator(new Animator.Looped());

		loader = new ImageLoader(a, "myimage/boss1.png", true);
		animations[ENEMY_LARGE_1] = new AnimationStrip();
		animations[ENEMY_LARGE_1].addFrame(loader.extractCellScaled(0, 0,
				loader.getImageWidth(), loader.getImageHeight(), 40, 40));
		animations[ENEMY_LARGE_1].setAnimator(new Animator.Looped());

	}

}
