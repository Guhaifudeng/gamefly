package org.xf.app.scene.iso;

import javax.swing.JPanel;

import org.xf.app.actor2D.Actor2DGroup;
import org.xf.app.actor2D.AnimationStrip;
import org.xf.app.actor2D.Animator;
import org.xf.app.actor2D.ImageLoader;

public class IsoTileGroup extends Actor2DGroup{

	public static final int TILE_HEIGHT = 60;  
	public static final int TILE_WIDTH = 60;
	public IsoTileGroup(){
		animations = new AnimationStrip[1];
	}
	@Override
	public void init(JPanel a) {
		// TODO Auto-generated method stub
		
		ImageLoader loader;

		loader = new ImageLoader(a, "images/tile_A.png", true);
		animations[0] = new AnimationStrip();
		animations[0].addFrame(loader.getImage());
		animations[0].setAnimator(new Animator.Single());

	}

}
