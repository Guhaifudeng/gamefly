package org.xf.app.actor2D;

import java.awt.Frame;
import java.awt.Image;
import java.util.LinkedList;
/*
 * 主要的动画类型
 * 类型:Indexed Looped OneShot Random Single
 * Indexed:指定顺序显示
 * Looped: 重复播放-开头，走路
 * OneShot：动画终止-最后一震，行星物体爆炸
 * Random: 见到无敌装备
 * Singal: 只包含一个实体
 * 
 */
public abstract class Animator {
	
	
	//对图像帧链表的引用
	
	protected LinkedList frames;
	//动画的当前索引
	protected int currIndex;
	//创建一个新的Animator对象，动画帧序列为空
	protected Animator()
	{
		frames =null;
		currIndex = 0;
	}
	public final void setFrames(LinkedList list)
	{
		frames = list;
	}
	//重新设置动画
	public void reset(){
		currIndex = 0;
	}
	//返回动画的当前帧
	public Image getCurrFrame(){
		if(frames!=null){
			return (Image)frames.get(currIndex);
		}
		return null;
	}
	//方法定义如何播放帧
	public abstract void nextFrame();
	//以传入数组作为索引的动画帧
	public static class Indexed extends Animator{
		protected int[] indices;
		protected int arrayIndex;
		public Indexed(){
			super();
			arrayIndex = 0;
		}
		public Indexed(int[] idx)
		{
			indices = new int[idx.length];
			System.arraycopy(idx, 0, indices,0,idx.length);
			arrayIndex = 0;
		}
		public void nextFrame() {
			
			if(frames!= null)
			{
				//索引递增
				if(++arrayIndex >= indices.length){
					arrayIndex = 0;
				}
				currIndex = indices[arrayIndex];
			}
			
		}
		
	}//Animator.Indexed
	//动画帧枚举，必要时从头循环
	public static class Looped extends Animator{
		public Looped()
		{
			super();	
		}
		public void nextFrame() {
			if(frames != null){
				if(++currIndex >= frames.size()){
					reset();
				}
			}
			
		}
		
	}//Animator.Looped
	
	//动画枚举，但是在到达最后一帧时停止
	public static class OneShot extends Animator{
		public OneShot(){
			super();
		}
		
		public void nextFrame(){
			if(frames !=null){
				if(++currIndex >= frames.size()){
					currIndex = frames.size()-1;
				}
			}
		}
	}//Animator.OneShot
	//在每次调用nextFrame时产生一个随机的动画帧
	public static class Random extends Animator
	{
		private java.util.Random random;
		public Random(){
			super();
			random = new java.util.Random();
		}
		@Override
		public void nextFrame() {
			if(frames!=null){
				currIndex = random.nextInt() %frames.size();
			}
			
		}
	}//Animator.Random
	
	//代表只包含一帧的动画，这个类节省时间，因为它不做处理
	
	public static class Single extends Animator{
		public Single(){
			super();
			
		}

		@Override
		public void nextFrame() {
			// TODO Auto-generated method stub
			//什么也不做
		}
		
	}//Animator.Single
	
	
}//Animator
