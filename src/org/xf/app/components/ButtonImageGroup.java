package org.xf.app.components;

import java.awt.Image;

import javax.swing.JPanel;

import org.xf.app.actor2D.AnimationStrip;
import org.xf.app.actor2D.Animator;
import org.xf.app.actor2D.ImageGroup;
import org.xf.app.actor2D.ImageLoader;

public class ButtonImageGroup extends ImageGroup {
	
	//图像的文件名和它所包含的帧数
	protected String filename;
	protected int numFrames;

	public ButtonImageGroup(int n, String str) {
		super();
		numFrames = n;
		filename = str;
		animations = new AnimationStrip[1];
	}

	// 加载动画条并把它分为numFrame帧 横放
	public void init(JPanel a) {
		ImageLoader loader;
		loader = new ImageLoader(a, filename, true);
		
		//得到每一帧的宽和高，注意它假设所有的图像都处于
		//1 x numFrame的网格中，没有任何填充单元和分割线
		frameWidth = loader.getImageWidth() / numFrames;
		frameHeight = loader.getImageHeight();
		animations[0] = new AnimationStrip();
		for (int i = 0; i < numFrames; i++) {
			animations[0].addFrame(loader.extractCell((i * frameWidth), 0,
					frameWidth, frameHeight));
		}
		animations[0].setAnimator(new Animator.Looped());
	}

	public Image getFrame(int frame) {
		animations[0].reset();
		for (int i = 0; i < frame; i++) {
			animations[0].animate();
		}
		return animations[0].getCurrFrame();
	}

} // ButtonImageGroup