package org.xf.app.actor2D;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.LinkedList;
/*
 * ÿһ��ImageGroup�ඨ��һ��AnimationStrip����
 * AnimationStrip�������һ��ͼ��֡�������б�
 * ʹ��ImageLoader�������
 */
public class AnimationStrip {
	//�۲��ⲿ����Ļ���
	public static ImageObserver observer;
	//ͼ��֡�����Լ������С
	protected LinkedList frames;
	protected int numFrames;
	//�����ӳ��Animater
	protected Animator animator;
	
	//����һ���µ�AnimationStrip����
	public AnimationStrip(){
		frames = null;
		numFrames = 0;
		animator = null;
		
	}
	public final void setAnimator(Animator anim){
		animator = anim;
		animator.setFrames(frames);
	}
	//��ͼ��֡��ӵ�������
	public void addFrame(Image i)
	{
		if(frames == null){
			frames = new LinkedList();
			numFrames = 0;
			
		}
		frames.add(i);
		numFrames++;
		
	}
	//����Animator�ĵ�ǰ֡
	public Image getCurrFrame(){
		if(frames != null){
			return animator.getCurrFrame();
		}
		return null;
	}
	//��Animator������һ֡����
	public void animate(){
		if(animator != null){
			animator.nextFrame();
		}
	}
	//����Animator����һ֡����
	public Image getNextFrame(){
		if(frames != null){
			animator.nextFrame();
			return animator.getCurrFrame();
			
		}
		return null;
	}
	//���ص�һ֡����
	public Image getFirstFrame(){
		if(frames != null){
			
			return (Image)frames.getFirst();
			
		}
		return null;
	}
	//�������һ֡����
	public Image getLastFrame()
	{
	if(frames != null){
			
			return (Image)frames.getLast();
			
		}
		return null;
	}
	//�����ڲ�Animator�ڲ��Ķ�������
	public void reset(){
		if(animator != null){
			animator.reset();
		}
	}
	//����֡�Ŀ��
	public int getFrameWidth(){
		if(frames != null && !frames.isEmpty()){
			return getFirstFrame().getWidth(observer);
		}
		return 0;
	}
	//���ض���֡�ĸ߶�
	public int getFrameHeight(){
		if(frames != null && !frames.isEmpty()){
			return getFirstFrame().getHeight(observer);
		}
		return 0;
	}
	
}//AnimatonStrip

