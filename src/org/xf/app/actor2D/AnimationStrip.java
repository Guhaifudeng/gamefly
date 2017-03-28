package org.xf.app.actor2D;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.LinkedList;
/*
 * 每一个ImageGroup类定义一个AnimationStrip对象
 * AnimationStrip对象包含一个图像帧的链接列表，
 * 使用ImageLoader对象填充
 */
public class AnimationStrip {
	//观察外部对象的绘制
	public static ImageObserver observer;
	//图像帧链表以及链表大小
	protected LinkedList frames;
	protected int numFrames;
	//负责放映的Animater
	protected Animator animator;
	
	//创建一个新的AnimationStrip对象
	public AnimationStrip(){
		frames = null;
		numFrames = 0;
		animator = null;
		
	}
	public final void setAnimator(Animator anim){
		animator = anim;
		animator.setFrames(frames);
	}
	//把图像帧添加到链表中
	public void addFrame(Image i)
	{
		if(frames == null){
			frames = new LinkedList();
			numFrames = 0;
			
		}
		frames.add(i);
		numFrames++;
		
	}
	//返回Animator的当前帧
	public Image getCurrFrame(){
		if(frames != null){
			return animator.getCurrFrame();
		}
		return null;
	}
	//让Animator产生下一帧动画
	public void animate(){
		if(animator != null){
			animator.nextFrame();
		}
	}
	//返回Animator的下一帧动画
	public Image getNextFrame(){
		if(frames != null){
			animator.nextFrame();
			return animator.getCurrFrame();
			
		}
		return null;
	}
	//返回第一帧动画
	public Image getFirstFrame(){
		if(frames != null){
			
			return (Image)frames.getFirst();
			
		}
		return null;
	}
	//返回最后一帧动画
	public Image getLastFrame()
	{
	if(frames != null){
			
			return (Image)frames.getLast();
			
		}
		return null;
	}
	//重置内部Animator内部的动画序列
	public void reset(){
		if(animator != null){
			animator.reset();
		}
	}
	//动画帧的宽度
	public int getFrameWidth(){
		if(frames != null && !frames.isEmpty()){
			return getFirstFrame().getWidth(observer);
		}
		return 0;
	}
	//返回动画帧的高度
	public int getFrameHeight(){
		if(frames != null && !frames.isEmpty()){
			return getFirstFrame().getHeight(observer);
		}
		return 0;
	}
	
}//AnimatonStrip

