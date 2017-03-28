package org.xf.gamefly.graphics;

import javax.swing.JPanel;

import org.xf.app.actor2D.Actor2DGroup;
import org.xf.app.actor2D.AnimationStrip;
import org.xf.app.actor2D.Animator;
import org.xf.app.actor2D.ImageGroup;
import org.xf.app.actor2D.ImageLoader;
import org.xf.app.actor2D.Animator.Single;

public class BulletGroup extends Actor2DGroup {
	public static final int ENEMY_BULLET_SHOT = 0;// É¢µ¯-Ö±µ¯
	public static final int ENEMY_BULLET_LASER = 1;// ¼¤¹â
	public static final int ENEMY_BULLET_TRACK = 2;// ¸ú×Ù
	public static final int ENEMY_BULLET_RANDOM = 3;// Ëæ»ú
	public static final int ENEMY_BULLET_CIRCLE = 4;// Ô²ÐÎ
	public static final int ENEMY_BULLET_SPLIT = 5;// °ëÔ²
	
	
	public static final int PLAYER_BULLET_SHOT = 10;// É¢µ¯-Ö±µ¯
	public static final int PLAYER_BULLET_LASER = 11;// ¼¤¹â
	public static final int PLAYER_BULLET_MESS = 12;// ÓÐÏÞµÄMessµ¯
	
	// public static final int BULLET_meteor

	public BulletGroup() {
		super();
		animations = new AnimationStrip[13];
	}

	public void init(JPanel a) {
		ImageLoader loader;
		/**********player*************/
		// player ¼ÓÔØ×Óµ¯Í¼Æ×
		loader = new ImageLoader(a, "myimage/bullet.png", true);
		// É¢µ¯
		animations[PLAYER_BULLET_SHOT] = new AnimationStrip();
		animations[PLAYER_BULLET_SHOT].addFrame(loader.extractCellScaled(0, 20, 40,
				120, 10, 30));
		animations[PLAYER_BULLET_SHOT].setAnimator(new Animator.Single());
		
		// player ¼ÓÔØ×Óµ¯Í¼Æ×
		loader = new ImageLoader(a, "myimage/laser.png", true);
		// ¼¤¹â
		animations[PLAYER_BULLET_LASER] = new AnimationStrip();
		animations[PLAYER_BULLET_LASER].addFrame(loader.extractCellScaled(0, 0, 20,
				135, 10, 50));
		animations[PLAYER_BULLET_LASER].setAnimator(new Animator.Single());
		
		// player ¼ÓÔØ×Óµ¯Í¼Æ×
		loader = new ImageLoader(a, "myimage/bullet.png", true);
		// ¸ú×Ù
		animations[PLAYER_BULLET_MESS] = new AnimationStrip();
		animations[PLAYER_BULLET_MESS].addFrame(loader.extractCellScaled(0, 20, 40,
				120, 10, 30));
		animations[PLAYER_BULLET_MESS].setAnimator(new Animator.Single());
		
		/********enemy************/
		//¼ÓÔØ×Óµ¯Í¼Æ×-É¢µ¯
		loader = new ImageLoader(a, "myimage/shot.png", true);
		animations[ENEMY_BULLET_SHOT] = new AnimationStrip();
		animations[ENEMY_BULLET_SHOT].addFrame(loader.extractCellScaled(0, 20, 40,
				120, 10, 30));
		animations[ENEMY_BULLET_SHOT].setAnimator(new Animator.Single());
		// player ¼ÓÔØ×Óµ¯Í¼Æ×
		loader = new ImageLoader(a, "myimage/biglaser.gif", true);
		// ¼¤¹â
		animations[ENEMY_BULLET_LASER] = new AnimationStrip();
		for(int i =0;i<3;i++)
		animations[ENEMY_BULLET_LASER].addFrame(loader.extractCellScaled(i*40, 0, 40,
				40, 20, 20));
		animations[ENEMY_BULLET_LASER].setAnimator(new Animator.Looped());
		
		// ¼ÓÔØ×Óµ¯Í¼Æ×-¸ú×Ù
		loader = new ImageLoader(a, "myimage/track.gif", true);
		animations[ENEMY_BULLET_TRACK] = new AnimationStrip();
		for(int i =0;i<3;i++){
			animations[ENEMY_BULLET_TRACK].addFrame(loader.extractCellScaled(20*i, 0, 20,60, 10, 30));
		}
		animations[ENEMY_BULLET_TRACK].setAnimator(new Animator.Looped());

		// ¼ÓÔØ×Óµ¯Í¼Æ×-Ëæ»ú
		loader = new ImageLoader(a, "myimage/random.png", true);
		animations[ENEMY_BULLET_RANDOM] = new AnimationStrip();
		animations[ENEMY_BULLET_RANDOM].addFrame(loader.extractCellScaled(0, 5, loader.getImageWidth(),
				loader.getImageHeight(), 10, 30));
		animations[ENEMY_BULLET_RANDOM].setAnimator(new Animator.Single());

		// ¼ÓÔØ×Óµ¯Í¼Æ×-Ô²ÐÎ
		loader = new ImageLoader(a, "myimage/circle.png", true);
		animations[ENEMY_BULLET_CIRCLE] = new AnimationStrip();
		animations[ENEMY_BULLET_CIRCLE].addFrame(loader.extractCellScaled(0, 5, loader.getImageWidth(),
				loader.getImageHeight(), 20, 30));
		animations[ENEMY_BULLET_CIRCLE].setAnimator(new Animator.Single());

		// ¼ÓÔØ×Óµ¯Í¼Æ×-·ÖÁÑ
		loader = new ImageLoader(a, "myimage/circle.png", true);
		animations[ENEMY_BULLET_SPLIT] = new AnimationStrip();
		animations[ENEMY_BULLET_SPLIT].addFrame(loader.extractCellScaled(0, 5, loader.getImageWidth(),
				loader.getImageHeight(), 20, 30));
		animations[ENEMY_BULLET_SPLIT].setAnimator(new Animator.Single());

	}

}
