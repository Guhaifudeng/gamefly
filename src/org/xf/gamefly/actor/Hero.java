package org.xf.gamefly.actor;

import java.util.LinkedList;

import org.xf.app.actor2D.Actor2D;
import org.xf.app.actor2D.Actor2DGroup;
import org.xf.app.actor2D.Vector2D;
import org.xf.app.scene.space.StaticActorGroup;
import org.xf.gamefly.actor.Bullet.BulletMess;
import org.xf.gamefly.bean.Boy;
import org.xf.gamefly.bean.Girl;
import org.xf.gamefly.bean.User;
import org.xf.gamefly.graphics.BulletGroup;
import org.xf.gamefly.util.RoleBulletGroup;
import org.xf.gamefly.util.SoundPlayer;

public class Hero extends Actor2D{
	protected int circleNum;
	protected int power;
	protected int lifeNum;
	private boolean deadFlag;
	protected LinkedList<Bullet> myBulletList;
	protected Bullet myBullet1,myBullet2;
	private int currBullet ;
	private RoleBulletGroup bgrp;
	private Burst burst;
	private int score;
	private SoundPlayer soundPlayer;
	public Hero(Actor2DGroup grp, RoleBulletGroup bgrp) {
		
		super(grp);
		user = null;
		score = 0;
		this.bgrp = bgrp;
		burst = null;
		lifeNum = 20;
		circleNum = 5;
		//动画
		currAnimation = group.getAnimationStrip(0);
		//子弹
		setCurrBullet(10);
		
		soundPlayer = new SoundPlayer("sound/kamikaze_lazer.wav");
	}
	
	public void setCurAnimation(int index){
		currAnimation = group.getAnimationStrip(index);
	}
	

	public final void addScore(int add){
		score += add;
	}
	public final int getScore(){
		return score;
	}
	private void minusCircleBulletNum(){
		circleNum--;
	}
	private void addCircleBulletNum(){
		circleNum++;
	}
	public final LinkedList getMyBulletList() {
		return myBulletList;
	}
	
	public final int getCurrBullet() {
		return currBullet;
	}
	public void  changeBulletKind(){
		if(circleNum<=0){
			setCurrBullet((this.getCurrBullet()+1-10)%2+10);
			
		}
		else {
			setCurrBullet((this.getCurrBullet()+1-10)%3+10);
		}
		
	}
	public final void setCurrBullet(int currBullet) {
		this.currBullet = currBullet;
	}
	//处理与冲突列表对象之间冲突的stub方法
	// this method is left empty ，but not abstract
	public void startShooting(){
		myBullet1 = bgrp.createNowBullets(currBullet);
		myBullet2 = bgrp.createNowBullets(currBullet);
		if(myBullet1 instanceof BulletMess  ){
			minusCircleBulletNum();
			if(circleNum<=0 ){
				currBullet = 10; 
				
			}
		}
		myBulletList = new LinkedList<Bullet>();
		
		soundPlayer.start();
		Vector2D tpv = new Vector2D.Double(this.getX()+this.getWidth()/2-myBullet1.getWidth()-5,
				this.getY());
		myBullet1.setPosVelRot(0, null,tpv);
		myBulletList.add(myBullet1);
		tpv = new Vector2D.Double(this.getX()+this.getWidth()/2+myBullet2.getWidth()+5,
				this.getY());
		myBullet2.setPosVelRot(0, null,tpv);
		myBulletList.add(myBullet2);
	}
	public void stopShooting(){
		myBulletList = null;
	}
	



	
	
	public final int getLifeNum() {
		return lifeNum;
	}

	public final void setLifeNum(int lifeNum) {
		this.lifeNum = lifeNum;
	}
	
	public final void addLifeNum(int add) {
		lifeNum +=add;
	}

	public final void minusLifeNum(int minus) {
		lifeNum -= minus;
	}

	

	
	public void dead(){
		deadFlag = true;
	}
	public boolean isDead(){
		return deadFlag;
	}
	public Burst getHeroBurst() {
		// TODO Auto-generated method stub
		return burst;
	}
	//处理与冲突列表对象之间冲突的stub方法
		@Override
		public void processCollisions() {
			//处理哈
			if(this.collisionList!=null){
				for(int i =0;i<collisionList.size();i++){
					if(collisionList.get(i) instanceof Bullet && ((Bullet)collisionList.get(i)).getNum()==0){
						minusLifeNum(1);
						//System.out.print("1");
						collisionList.remove(i);
						i--;
					}else if(collisionList.get(i) instanceof Enemy){
						minusLifeNum(1);
						//System.out.print("2");
						collisionList.remove(i);
						i--;
					}else if(collisionList.get(i) instanceof Burst){
						burst = (Burst)collisionList.get(i);
						if(burst.getAward()==0){
							this.addScore(burst.getValue());
						}else if(burst.getAward()==1){
							addLifeNum(2);
						}else if(burst.getAward()==2){
							addCircleBulletNum();
						}
						
						collisionList.remove(i);
						burst.setState(STATE_DEAD);
						i--;
					}
					if(lifeNum<=0){
						this.setState(Actor2D.STATE_DEAD);
					}
					
				}
			}
			
			//System.out.print(1+1);
		}
		private User user; 
		public User getUser() {
			
			return this.user;
		}
		 
		public  void setUser(Boy boy) {
			this.user = (User)boy;
			
		}
		public  void setUser(Girl girl) {
			this.user = (User)girl;
			
		}
}
