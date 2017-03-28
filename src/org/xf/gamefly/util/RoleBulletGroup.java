package org.xf.gamefly.util;

import javax.swing.JPanel;


import org.xf.gamefly.actor.Bullet;

import org.xf.gamefly.graphics.BulletGroup;

public class RoleBulletGroup {
	// 主要用于子弹设定
	protected Bullet[] bullets;
	protected int frameWidth;
	protected int frameHeight;
	protected BulletGroup grp;
	public RoleBulletGroup(JPanel pnl){
		 grp = new BulletGroup(); 
		 grp.init(pnl);
	}

	// 再次建立
	public final Bullet createNowBullets(int index) {
		switch (index) {
		case BulletGroup.ENEMY_BULLET_SPLIT:
			return new Bullet.BulletSplit(grp);
		case BulletGroup.ENEMY_BULLET_CIRCLE:
			return new Bullet.BulletCircle(grp);
		case BulletGroup.ENEMY_BULLET_RANDOM:
			return new Bullet.BulletRandom(grp);
		case BulletGroup.ENEMY_BULLET_SHOT:
			return new Bullet.BulletShot(grp);
		case BulletGroup.ENEMY_BULLET_TRACK:
			return new Bullet.BulletTrack(grp);
		case BulletGroup.ENEMY_BULLET_LASER:
			return new Bullet.BulletLaser(grp);
		case BulletGroup.PLAYER_BULLET_LASER:
			return new Bullet.BulletLaser(grp, 10);
		case BulletGroup.PLAYER_BULLET_SHOT:
			return new Bullet.BulletShot(grp, 10);

		case BulletGroup.PLAYER_BULLET_MESS:
			return new Bullet.BulletMess(grp,10);

		default:
			return null;
		}
	}


}
