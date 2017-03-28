package org.xf.app.actor2D;

import javax.swing.JPanel;
/*
 * actor 辅助类
 */
//创建和访问AnimationStrip对象的方法
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
				//给调试器或者标准输出发送错误
				e.printStackTrace();
			}
		}
			return null;
		//ImageGroup
			
	}
}
