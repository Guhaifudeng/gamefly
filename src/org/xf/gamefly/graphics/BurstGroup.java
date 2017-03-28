package org.xf.gamefly.graphics;

import javax.swing.JPanel;

import org.xf.app.actor2D.Actor2DGroup;
import org.xf.app.actor2D.AnimationStrip;
import org.xf.app.actor2D.Animator;
import org.xf.app.actor2D.ImageGroup;
import org.xf.app.actor2D.ImageLoader;

public class BurstGroup extends Actor2DGroup{
	public static int BASIC_BURST = 0;
	public static int BIG_BURST = 1;
	public BurstGroup(){
		super();
		animations = new AnimationStrip[2];
	}
	@Override
	public void init(JPanel a) {
		ImageLoader loader;
		//¼ÓÔØ±¬Õ¨Í¼Æ×
		
		//Ð¡±¬Õ¨
		
		animations[BASIC_BURST] = new AnimationStrip();
		for(int i =1;i<=1;i++){
			loader = new ImageLoader(a, "myimage/awardmoney.gif", true);
			animations[BASIC_BURST].addFrame(loader.extractCellScaled(0,0,loader.getImageWidth(),loader.getImageHeight(),40,40));
		}
			animations[BASIC_BURST].setAnimator(new Animator.OneShot());
	
		
	}
 
}
