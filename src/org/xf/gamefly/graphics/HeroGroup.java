package org.xf.gamefly.graphics;

import javax.swing.JPanel;

import org.xf.app.actor2D.Actor2DGroup;
import org.xf.app.actor2D.AnimationStrip;
import org.xf.app.actor2D.Animator;
import org.xf.app.actor2D.ImageLoader;

public class HeroGroup extends Actor2DGroup {
	public static final int BOY_HERO=0;
	public static final int GIRL_HERO =1;
	public HeroGroup() {
		animations = new AnimationStrip[2];

	}

	@Override
	public void init(JPanel a) {
		ImageLoader loader;
		animations[BOY_HERO] = new AnimationStrip();
		loader = new ImageLoader(a, "myimage/hero1.png", true);
		//animations[0].addFrame(loader.extractCellScaled(0, 0, 90, 125,45,80));
		animations[BOY_HERO].addFrame(loader.extractCellScaled(0, 0, loader.getImageWidth(), loader.getImageHeight(),70,45));
		animations[BOY_HERO].setAnimator(new Animator.Looped());
		
		animations[GIRL_HERO] = new AnimationStrip();
		loader = new ImageLoader(a, "myimage/hero2.png", true);
		//animations[0].addFrame(loader.extractCellScaled(0, 0, 90, 125,45,80));
		animations[GIRL_HERO].addFrame(loader.extractCellScaled(0, 0, loader.getImageWidth(), loader.getImageHeight(),70,45));
		animations[GIRL_HERO].setAnimator(new Animator.Looped());

	}
}
