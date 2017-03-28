package org.xf.gamefly.graphics;

import javax.swing.JPanel;

import org.xf.app.actor2D.Actor2DGroup;
import org.xf.app.actor2D.AnimationStrip;
import org.xf.app.actor2D.Animator;
import org.xf.app.actor2D.ImageLoader;

public class AwardGroup extends Actor2DGroup{
	public static  final int AWARD_MONEY = 0;
	public static  final int AWARD_BULLET = 2;
	public static  final int AWARD_BLOOD =1; 
	//public static  final int AWARD_
	/**
	 * ½±Àø¶¯»­×é
	 */
	public AwardGroup() {
		animations = new AnimationStrip[5];

	}

	/* (non-Javadoc)
	 * @see org.xf.app.actor2D.Actor2DGroup#init(javax.swing.JPanel)
	 */
	@Override
	public void init(JPanel a) {
		ImageLoader loader;
		
		animations[AWARD_MONEY] = new AnimationStrip();
		loader = new ImageLoader(a, "myimage/awardmoney.gif", true);
		animations[AWARD_MONEY].addFrame(loader.extractCellScaled(0, 0, loader.getImageWidth(),loader.getImageHeight(),30,30));
		animations[AWARD_MONEY].setAnimator(new Animator.Single());
		
		animations[AWARD_BLOOD] = new AnimationStrip();
		loader = new ImageLoader(a, "myimage/awardblood.gif", true);
		animations[AWARD_BLOOD].addFrame(loader.extractCellScaled(0, 0, loader.getImageWidth(),loader.getImageHeight(),30,30));
		animations[AWARD_BLOOD].setAnimator(new Animator.Single());
		
		animations[AWARD_BULLET] = new AnimationStrip();
		loader = new ImageLoader(a, "myimage/awardbullet.gif", true);
		animations[AWARD_BULLET].addFrame(loader.extractCellScaled(0, 0, loader.getImageWidth(),loader.getImageHeight(),30,30));
		animations[AWARD_BULLET].setAnimator(new Animator.Single());

	}
}
