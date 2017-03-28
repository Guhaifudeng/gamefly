package org.xf.app.actor2D;

import javax.swing.JPanel;
/*
 * actor ������
 */
//�����ͷ���AnimationStrip����ķ���
public abstract class ImageGroup {
	protected AnimationStrip[] animations;
	protected int frameWidth;
	protected int frameHeight;
	public final int getFrameWidth() {
		return frameWidth;
	}

	public final int getFrameHeight() {
		return frameHeight;
	}

	protected ImageGroup(){
		animations = null;
	}
	
	public abstract void init(JPanel pnl);
	
	public final AnimationStrip getAnimationStrip(int index){
		if(animations!=null){
			try{
				return animations[index];
			}
			catch(ArrayIndexOutOfBoundsException e){
				//�����������߱�׼������ʹ���
				e.printStackTrace();
			}
		}
			return null;
		//ImageGroup
			
	}
}
