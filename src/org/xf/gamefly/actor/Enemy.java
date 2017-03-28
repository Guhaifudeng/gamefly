package org.xf.gamefly.actor;


import java.util.LinkedList;
import java.util.Random;

import javax.swing.JPanel;

import org.xf.app.actor2D.Actor2D;
import org.xf.app.actor2D.Actor2DGroup;
import org.xf.app.actor2D.Vector2D;
import org.xf.gamefly.graphics.BulletGroup;
import org.xf.gamefly.graphics.BurstGroup;
import org.xf.gamefly.graphics.EnemyGroup;
import org.xf.gamefly.panel.GameJPanel;
import org.xf.gamefly.util.RoleBulletGroup;

abstract public class Enemy extends Actor2D{
	protected LinkedList bulletList;
	protected int bulletNum;
	protected int power;
	protected int currAnimationIndex;
	protected Bullet myBullet;
	protected int currBullet;
	protected int kill ;
	protected RoleBulletGroup bgrp;
	private Burst burst;
	private BurstGroup burstGroup ;
	Vector2D vbullet;
	private boolean isEnemy;
	private JPanel pnl;
	public Enemy(Actor2DGroup grp, RoleBulletGroup bgrp,JPanel a) {
		super(grp);
		this.pnl = a;
		this.bgrp = bgrp;
		isEnemy = true;
		burst = null;
		burstGroup =GameJPanel.burstGroup;
		 
		
	}
	public final boolean isEnemy() {
		return isEnemy;
	}
	public final void setEnemy(boolean isEnemy) {
		this.isEnemy = isEnemy;
	}
	public void setVbullet(double y){
		vbullet= new Vector2D.Double(0, y);
	}
	public void setPosVelRot(double theta, Vector2D v,Vector2D vpos) {
		setVel(v);
		setPos(vpos);
		setRot(theta);
		setVel(vel.getX() * Math.cos(theta) - vel.getY() * Math.sin(theta),
				vel.getY() * Math.cos(theta) + vel.getX() * Math.sin(theta));
		updateBounds();

	}
	
	public Burst getEnemyBurst(){
		return burst;
	}
	abstract public LinkedList getBulletList();
	abstract public void setBulletList();
	
	
	public static  class EnemySmall extends Enemy{

		public EnemySmall(EnemyGroup grp, RoleBulletGroup bgrp,JPanel a) {
			super(grp, bgrp,a);
			bulletList = null;
			power = 10;
			kill = 5;
			Random r = new Random();
			currAnimationIndex = r.nextInt(4);
			currAnimation = group.getAnimationStrip(currAnimationIndex);
		
			
			//子弹
		}
		public void setBulletList(){
			
			myBullet = this.bgrp.createNowBullets(BulletGroup.ENEMY_BULLET_RANDOM);
			Vector2D tpv = new Vector2D.Double(this.getX()+this.getWidth()/2-myBullet.getWidth()/2,
					this.getY()+myBullet.getHeight());
			
			myBullet.setPosVelRot(0, vbullet,tpv);

			bulletList = new LinkedList();
			bulletList.add(myBullet);
			
			
		}
		@Override
		public LinkedList getBulletList() {
			// TODO Auto-generated method stub
			return bulletList;
		}
	
		
		
	}
	public static class EnemyMiddleLaser extends Enemy{

		public EnemyMiddleLaser(Actor2DGroup grp, RoleBulletGroup bgrp,JPanel a) {
			super(grp, bgrp,a);
			bulletList = null;
			power = 40;
			kill = 10;
			Random r = new Random();
			currAnimationIndex=r.nextInt(2)+ EnemyGroup.ENEMY_MIDDLE_1;
			currAnimation = group.getAnimationStrip(currAnimationIndex);
		
		}
		public void setBulletList(){
			bulletList = new LinkedList();
			myBullet = this.bgrp.createNowBullets(BulletGroup.ENEMY_BULLET_LASER);
			Vector2D tpv = 	new Vector2D.Double(this.getX()+this.getWidth()/2-myBullet.getWidth(),
					this.getY()+this.getHeight());
			
			myBullet.setPosVelRot(rotation, vbullet,tpv);
			bulletList.add(myBullet);
			
			myBullet = this.bgrp.createNowBullets(BulletGroup.ENEMY_BULLET_LASER);
			tpv = new Vector2D.Double(this.getX()+this.getWidth()/2+myBullet.getWidth(),
					this.getY()+this.getHeight());
			myBullet.setPosVelRot(rotation, vbullet,tpv);
			bulletList.add(myBullet);
			
			
		}
		@Override
		public  LinkedList getBulletList() {
			// TODO Auto-generated method stub
			return bulletList;
		}
		
	}
	
	public static class EnemyMiddleSplit extends Enemy{

		public EnemyMiddleSplit(EnemyGroup grp, RoleBulletGroup bgrp,JPanel a) {
			super(grp, bgrp,a);
			bulletList = null;
			power = 40;
			kill = 10;
			//Random r = new Random();
			currAnimation = group.getAnimationStrip(EnemyGroup.ENEMY_MIDDLE_3);
		
		}
		public void setBulletList(){
			bulletList = new LinkedList();
			myBullet = this.bgrp. createNowBullets(BulletGroup.ENEMY_BULLET_SPLIT);
			Vector2D tpv = 	new Vector2D.Double(this.getX()+this.getWidth()/2,
					this.getY());
			myBullet.setPosVelRot(rotation, vbullet,tpv);
			bulletList.add(myBullet);
		}
		@Override
		public  LinkedList getBulletList() {
			// TODO Auto-generated method stub
			return bulletList;
		}
		
	}
	
	public static class EnemyMiddleTrack extends Enemy{

		public EnemyMiddleTrack(EnemyGroup grp, RoleBulletGroup bgrp,JPanel a) {
			super(grp, bgrp,a);
			bulletList = null;
			power = 40;
			kill = 10;
			Random r = new Random();
			currAnimation = group.getAnimationStrip(EnemyGroup.ENEMY_MIDDLE_5);
		
			
		}
		public void setBulletList(){
			bulletList = new LinkedList();
			myBullet = this.bgrp. createNowBullets(BulletGroup.ENEMY_BULLET_TRACK);
			Vector2D tpv = 	new Vector2D.Double(this.getX()+this.getWidth()/2,
					this.getY()+this.getHeight()/2);
			myBullet.setHostHero(this.hero);
			myBullet.setPosVelRot(rotation, vbullet,tpv);
			bulletList.add(myBullet);
		}
		@Override
		public  LinkedList getBulletList() {
			// TODO Auto-generated method stub
			return bulletList;
		}
		
	}
	public static class EnemyMiddleMess extends Enemy{

		public EnemyMiddleMess(EnemyGroup grp, RoleBulletGroup bgrp,JPanel a) {
			super(grp, bgrp,a);
			bulletList = null;
			power = 40;
			kill = 10;
			Random r = new Random();
			currAnimation = group.getAnimationStrip(EnemyGroup.ENEMY_MIDDLE_4);
		
			
		}
		public void setBulletList(){
			bulletList = new LinkedList();
			myBullet = this.bgrp. createNowBullets(BulletGroup.ENEMY_BULLET_RANDOM);
			Vector2D tpv = 	new Vector2D.Double(this.getX()+this.getWidth()/2-myBullet.getWidth()*2.5,
					this.getY()+this.getHeight());
			myBullet.setPosVelRot(rotation, vbullet,tpv);
			
			bulletList.add(myBullet);
			
			myBullet = this.bgrp.createNowBullets(BulletGroup.ENEMY_BULLET_RANDOM);
			tpv = new Vector2D.Double(this.getX()+this.getWidth()/2+myBullet.getWidth()*2,
					this.getY()+this.getHeight());
			myBullet.setPosVelRot(rotation, vbullet,tpv);
			bulletList.add(myBullet);
			
			myBullet = this.bgrp. createNowBullets(BulletGroup.ENEMY_BULLET_SHOT);
			tpv = 	new Vector2D.Double(this.getX()+this.getWidth()/2-myBullet.getWidth(),
					this.getY()+this.getHeight());
			myBullet.setPosVelRot(rotation,vbullet,tpv);
			
			bulletList.add(myBullet);
			
			myBullet = this.bgrp.createNowBullets(BulletGroup.ENEMY_BULLET_SHOT);
			tpv = new Vector2D.Double(this.getX()+this.getWidth()/2+myBullet.getWidth(),
					this.getY()+this.getHeight());
			myBullet.setPosVelRot(rotation, vbullet,tpv);
			bulletList.add(myBullet);
			
			
		
		}
		@Override
		public  LinkedList getBulletList() {
			// TODO Auto-generated method stub
			return bulletList;
		}
		
	}

	public  Bullet getMyBullet() {
		return myBullet;
	}
	
	public  int getCurrBullet() {
		return currBullet;
	}

	public  void setCurrBullet(int currBullet) {
		this.currBullet = currBullet;
	}
	

	public void startShooting(){
		setBulletList();
	}

	public void stopShooting(){
		bulletList = null;
	}
	int dataTime;
	protected Hero hero;
	//处理与冲突列表对象之间冲突的stub方法
	@Override
	public void processCollisions() {
		//处理哈
		if(this.collisionList!=null){
			for(int i =0;i<collisionList.size();i++){
				if(collisionList.get(i) instanceof Bullet && ((Bullet)collisionList.get(i)).getNum()==10){
					
					//System.out.print(dataTime++);
					this.minusPower(this.kill);
					if(this.getPower()<0){
						burst = new Burst.BasicBurst(burstGroup,this.getPos(),this.pnl);
						//System.out.println(this.getPos().getX()+"-"+this.getPos().getY());
						burst.updateBounds();
						burst.setVel(this.vel);
						//System.out.print(burst);
						if(hero!=null)
							hero.addScore(this.kill);
						
						this.setState(Actor2D.STATE_DEAD);
					}else{
						collisionList.remove(i);
						i--;
					}
					
					
					
				}else if(collisionList.get(i) instanceof Hero){
					this.minusPower(this.kill);
					burst = new Burst.BasicBurst(burstGroup,this.getPos(),this.pnl);
					burst.updateBounds();
					burst.setVel(this.vel);
					collisionList.remove(i);
					i--;
					this.setState(Actor2D.STATE_DEAD);
				}
				
			}
		}
		
		//System.out.print(1+1);
	}
	
	public void setHero(Hero hero){
		this.hero = hero;
	}
	public int getPower() {
		return power;
	}
	public void minusPower(int minus){
		power -= minus;
	}

	
	public void setPower(int power) {
		this.power = power;
	}




}
