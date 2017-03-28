package org.xf.gamefly.graphics;

import javax.swing.JPanel;

import org.xf.app.actor2D.Actor2DGroup;
import org.xf.app.actor2D.AnimationStrip;
import org.xf.app.actor2D.Animator;
import org.xf.app.actor2D.ImageLoader;

public class StaticActorGroup extends Actor2DGroup {
	private String pathName ;
	public StaticActorGroup(String pathName) {
		this.pathName = pathName;
		animations = new AnimationStrip[1];
 
	}

	@Override
	public void init(JPanel a) {

		ImageLoader loader;

		loader = new ImageLoader(a, pathName, true);
		animations[0] = new AnimationStrip();
	//	animations[0].addFrame(loader.extractCell(0, 0, loader.getImageWidth(), loader.getImageWidth()));
	//	写错了，导致浪费了四个小时的时间;
		animations[0].addFrame(loader.extractCell(0, 0,600,800));
		//animations[0].addFrame(loader.extractCell(1, 1,600,800));
		animations[0].setAnimator(new Animator.Single());

	}
}
