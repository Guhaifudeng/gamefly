package org.xf.gamefly.util;

import java.util.Random;

import javax.swing.JPanel;

import org.xf.app.actor2D.Vector2D;
import org.xf.gamefly.actor.Enemy;
import org.xf.gamefly.graphics.BulletGroup;
import org.xf.gamefly.graphics.EnemyGroup;
import org.xf.gamefly.panel.GameJPanel;

public class CreatEnemyRandom {
	// 主要用于子弹设定
	protected int frameWidth;
	protected int frameHeight;
	protected BulletGroup grp;

	private  double  theta;
	private Vector2D moveVel;
	private Vector2D enemyPos;
	private  EnemyGroup egp;
	private RoleBulletGroup rbgp;
	private JPanel pnl;
	
	public CreatEnemyRandom( JPanel pnl) {
		this.pnl = pnl;
		egp = GameJPanel.enemyGroup;
		
		rbgp = new RoleBulletGroup(pnl);
		
	}
	public void getRandomPosVelRot(){
		Random r =new Random();
		theta = r.nextDouble()/4-0.125;
		//theta = 0;
		moveVel = new Vector2D.Double(0,r.nextDouble()*(enemyMove)+0.1);
		enemyPos = new Vector2D.Double(r.nextInt(400),r.nextInt(10)-10);
	}
	public void setVbulletLevel(double y){
		vbullet = y;
	}
	private Enemy nowEnemy;
	private double vbullet;
	private double enemyMove;
	/**
	 * @param index 随机产生的敌人 类别号
	 * @return 该类别号的一个实例
	 */
	public Enemy creatNowEnemy(int index){
		getRandomPosVelRot();
		
		switch(index){
		
		case EnemyGroup.ENEMY_SMALL_1:
		case EnemyGroup.ENEMY_SMALL_2:	
		case EnemyGroup.ENEMY_SMALL_3:	
		case EnemyGroup.ENEMY_SMALL_4:
		nowEnemy = new Enemy.EnemySmall(egp,rbgp,pnl);
		nowEnemy.setVbullet(vbullet);
		nowEnemy.setPosVelRot(theta, moveVel, enemyPos);
		return nowEnemy;
			
		case EnemyGroup.ENEMY_MIDDLE_1:
		case EnemyGroup.ENEMY_MIDDLE_2:
			nowEnemy = new Enemy.EnemyMiddleLaser(egp,rbgp,pnl);
			nowEnemy.setPosVelRot(theta, moveVel, enemyPos);
			nowEnemy.setVbullet(vbullet);
			return nowEnemy;
			
		case EnemyGroup.ENEMY_MIDDLE_3:
			nowEnemy = new Enemy.EnemyMiddleSplit(egp,rbgp,pnl);
			nowEnemy.setPosVelRot(theta, moveVel, enemyPos);
			nowEnemy.setVbullet(vbullet);
			return nowEnemy;
			

			
		case EnemyGroup.ENEMY_MIDDLE_4:
			nowEnemy = new Enemy.EnemyMiddleMess(egp,rbgp,pnl);
			nowEnemy.setPosVelRot(theta, moveVel, enemyPos);
			nowEnemy.setVbullet(vbullet);
			return nowEnemy;
			
		case EnemyGroup.ENEMY_MIDDLE_5:
			nowEnemy = new Enemy.EnemyMiddleTrack(egp,rbgp,pnl);
			nowEnemy.setPosVelRot(theta, moveVel, enemyPos);
			nowEnemy.setVbullet(vbullet);
			return nowEnemy;
			default:
				return null;
		}
		
	}
	public void setEnemyMove(double i) {
		enemyMove = i;
		
	}
	
	
}
