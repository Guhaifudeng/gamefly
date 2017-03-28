package org.xf.app.actor2D;

import java.awt.Frame;
import java.awt.Image;
import java.util.LinkedList;
/*
 * ��Ҫ�Ķ�������
 * ����:Indexed Looped OneShot Random Single
 * Indexed:ָ��˳����ʾ
 * Looped: �ظ�����-��ͷ����·
 * OneShot��������ֹ-���һ���������屬ը
 * Random: �����޵�װ��
 * Singal: ֻ����һ��ʵ��
 * 
 */
public abstract class Animator {
	
	
	//��ͼ��֡���������
	
	protected LinkedList frames;
	//�����ĵ�ǰ����
	protected int currIndex;
	//����һ���µ�Animator���󣬶���֡����Ϊ��
	protected Animator()
	{
		frames =null;
		currIndex = 0;
	}
	public final void setFrames(LinkedList list)
	{
		frames = list;
	}
	//�������ö���
	public void reset(){
		currIndex = 0;
	}
	//���ض����ĵ�ǰ֡
	public Image getCurrFrame(){
		if(frames!=null){
			return (Image)frames.get(currIndex);
		}
		return null;
	}
	//����������β���֡
	public abstract void nextFrame();
	//�Դ���������Ϊ�����Ķ���֡
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
				//��������
				if(++arrayIndex >= indices.length){
					arrayIndex = 0;
				}
				currIndex = indices[arrayIndex];
			}
			
		}
		
	}//Animator.Indexed
	//����֡ö�٣���Ҫʱ��ͷѭ��
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
	
	//����ö�٣������ڵ������һ֡ʱֹͣ
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
	//��ÿ�ε���nextFrameʱ����һ������Ķ���֡
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
	
	//����ֻ����һ֡�Ķ�����������ʡʱ�䣬��Ϊ����������
	
	public static class Single extends Animator{
		public Single(){
			super();
			
		}

		@Override
		public void nextFrame() {
			// TODO Auto-generated method stub
			//ʲôҲ����
		}
		
	}//Animator.Single
	
	
}//Animator
