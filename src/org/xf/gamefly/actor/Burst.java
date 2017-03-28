package org.xf.gamefly.actor;

import java.util.Random;

import javax.swing.JPanel;

import org.xf.app.actor2D.Actor2D;
import org.xf.app.actor2D.Actor2DGroup;
import org.xf.app.actor2D.Vector2D;
import org.xf.gamefly.graphics.AwardGroup;
import org.xf.gamefly.graphics.BurstGroup;
import org.xf.gamefly.panel.GameJPanel;
import org.xf.gamefly.util.SoundPlayer;

abstract public class Burst extends Actor2D{

	
	private int deadTime;
	private SoundPlayer soundPlayer;
	
	private int value;
	private int award;
	
	public Burst(Actor2DGroup grp,JPanel pnl) {
		super(grp);
		award = -1;
		value = 10;
		deadTime = 0;
		soundPlayer = new SoundPlayer("sound/lazer_hit.wav");
		soundPlayer.start();
		
		
		// TODO Auto-generated constructor stub
	}

	public static class BasicBurst extends Burst{
		public BasicBurst(BurstGroup grp,Vector2D vpos,JPanel pnl) {
			super(grp,pnl);
			setPos(vpos);
			currAnimation = grp.getAnimationStrip(BurstGroup.BASIC_BURST);
			//updateBounds();
			
		}
		
		
	
		
		
	}
	
	public static class BIGBurst extends Burst{
		
		public BIGBurst(BurstGroup grp,Vector2D vpos,JPanel pnl) {
			super(grp,pnl);
			setPos(vpos);
			currAnimation = grp.getAnimationStrip(BurstGroup.BIG_BURST);
			//updateBounds();
		}
	}
	//更新对象的位置和边界盒，移动它然后更新变化
	@Override
	public void update() {
		pos.translate(vel);
		//System.out.print(vel.getX()+""+vel.getY());
		updateBounds();
		checkBounds();
		//System.out.print("1");
		animate();
		if(currAnimation.getCurrFrame() == currAnimation.getLastFrame()){
//			System.out.print(currAnimation.getLastFrame());
//			System.out.print("+"+currAnimation.getCurrFrame());
//			System.out.print("\n");
			deadTime++;
			//System.out.print(deadTime);
		}
		if(deadTime>2&&award==-1){
			//System.out.print("111");
			Random r = new Random();
			int index = r.nextInt(5000);
			if(index%2==0){
			   index = GameJPanel.awardGroup.AWARD_MONEY;
			}else if(index%5==0){
				index = GameJPanel.awardGroup.AWARD_BLOOD;
			}else if(index%19==0){
				index = GameJPanel.awardGroup.AWARD_BULLET;
			}else{
				 index = GameJPanel.awardGroup.AWARD_MONEY;
			}
			this.currAnimation = GameJPanel.awardGroup.getAnimationStrip(index);
			this.setAward(index);
			if(this.getVel().getX()<0.5) 
				this.setVel(this.getVel().getX(), 0.5);
			//this.setState(STATE_DEAD);
		}
		if(deadTime> 500){
			this.setState(STATE_DEAD);
		}
		//子类如果要覆盖这个方法，必须要求变化后是以远点为中心的
		//而不是以位置点为中心
		if(rotation !=0){
			xform.setToIdentity();
			xform.translate(pos.getX()+frameWidth/2, pos.getY()+frameHeight/2);
			xform.rotate(rotation);
			xform.translate(-frameWidth/2,-frameHeight/2);
		}
		else {//System.out.print("d");
			xform.setToTranslation(pos.getX(), pos.getY());
		}

	}
	public void setAward(int i) {
		this.award = i;
		
	}
	public int getAward() {
		return this.award;
		
	}
	public int getValue() {
		// TODO Auto-generated method stub
		return value;
	}
		
}
