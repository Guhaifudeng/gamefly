package org.xf.app.actor2D;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;

import org.xf.app.actor2D.Vector2D.Double;

public abstract class Actor2D implements Moveable {

	// 这个类封装了移动和绘制一个2D游戏对象所需的一般信息
	public static  final int STATE_ALIVE = 1;
	public static final int STATE_DYING = 2;
	public static final int STATE_DEAD = 4;

	protected int state;

	protected Actor2DGroup group;

	// actor位置、速度、旋转以及缓存的变换
	protected Vector2D pos;
	protected Vector2D vel;
	protected double rotation;
	protected AffineTransform xform;

	protected final double TWO_PI = 2 * Math.PI;
	// 冲突检测用的边界矩形
	protected Rectangle2D bounds;
	// 和这个actor有冲突的actor列表
	protected LinkedList collisionList;
	// 宽和高
	protected int frameWidth;
	protected int frameHeight;
	// 指向当前动画条的引用
	protected AnimationStrip currAnimation;
	// 播放下一帧之前需要等待的帧数和一个帧计数器
	protected int animWait;
	protected int animCount;

	// 创建一个属于给定ActorGroup的Actor2D对象
	public Actor2D(Actor2DGroup grp) {
		group = grp;
		bounds = new Rectangle2D.Double();
		collisionList = new LinkedList();

		state = 0;
		pos = new Vector2D.Double();
		vel = new Vector2D.Double();
		rotation = 0;
		xform = new AffineTransform();

		currAnimation = null;
		animWait = 0;
		animCount = 0;

		frameWidth = 0;
		frameHeight = 0;
	}

	// 每隔animWait帧，移动一下actor
	public void animate() {
		if (currAnimation != null) {
			//System.out.print("1");
			if (++animCount >= animWait) {
				currAnimation.getNextFrame();
				animCount = 0;
			}
		}
	}

	// 使用它自身的变换绘制actor
	public void paint(Graphics2D g2d) {
		if (currAnimation != null) {
			g2d.drawImage(currAnimation.getCurrFrame(), xform,
					AnimationStrip.observer);
		}
	}

	// 在（x,y）坐标处绘制actor
	public void paint(Graphics2D g2d, double x, double y) {
		if (currAnimation != null) {
			g2d.drawImage(currAnimation.getCurrFrame(),
					AffineTransform.getTranslateInstance(x, y),
					AnimationStrip.observer);
		}
	}

	// 简单边界盒，判断actor是否和传入的actor冲突

	public boolean intersects(Actor2D other) {
		return bounds.intersects(other.getBounds());
	}

	// 根据当前的x和y位置更新边界盒
	public void updateBounds() {
		// 确保知道actor的正确的宽度和高度
		if (frameWidth <= 0 && currAnimation != null) {
			frameWidth = currAnimation.getFrameWidth();
		}
		if (frameHeight <= 0 && currAnimation != null) {
			frameHeight = currAnimation.getFrameHeight();
		}
		bounds.setRect(pos.getX(), pos.getY(), frameWidth, frameHeight);
	}

	// 确保actor的边界没有超出actor组所限定的边界

	public void checkBounds() {
		if (group == null)
			return;
		if (bounds.getX() < group.MIN_X_POS) {
			pos.setX(group.MIN_X_POS);
		} else if (bounds.getX() + frameWidth > group.MAX_X_POS) {
			pos.setX(group.MAX_X_POS - frameWidth);
		}
		if (bounds.getY() < group.MIN_Y_POS) {
			pos.setY(group.MIN_Y_POS);
		} else if (bounds.getY() + frameHeight > group.MAX_Y_POS) {
			pos.setY(group.MAX_Y_POS - frameHeight);
		}

	}

	// 返回一个描述actor的字符串
	public String toString() {
		return super.toString();
	}

	// 把传入的值和当前的属性值进行位或操作
	public final void setState(int attr) {
		state |= attr;
	}

	// 使用"位与" 或者"非"重新设置属性

	public final void resetState(int attr) {
		state &= ~attr;
	}

	public final int getState() {
		return state;
	}

	public final void ClearState() {
		state = 0;
	}

	// 判断所传入的状态属性是否被actor的状态属性所包含
	public final boolean hasState(int attr) {
		return ((state&attr) != 0);
	}

	// actor速度、位置和旋转访问的方法
	public final void setX(double px) {
		pos.setX(px);
	}

	public final void setY(double py) {
		pos.setY(py);
	}

	public final double getX() {
		return pos.getX();
	}

	public final double getY() {
		return pos.getY();
	}

	public final void setPos(int x, int y) {
		pos.setX(x);
		pos.setY(y);
	}

	public final void setPos(double x, double y) {
		pos.setX(x);
		pos.setY(y);
	}

	public final void setPos(Vector2D v) {
		pos.setX(v.getX());
		pos.setY(v.getY());
	}

	public final Vector2D getPos() {
		return pos;
	}

	public final void setRot(double theta) {
		rotation = theta;
	}

	public final double getRot() {
		return rotation;
	}

	public final void rotate(double theta) {
		rotation += theta;
		while (rotation > TWO_PI) {
			rotation -= TWO_PI;
		}
		while (rotation < -TWO_PI) {
			rotation += TWO_PI;
		}
	}

	public final void setVel(int x, int y) {
		vel.setX(x);
		vel.setY(y);
	}
	public final void setVel(double x, double y) {
		vel.setX(x);
		vel.setY(y);
	}
	public final void setVel(Vector2D v){
		vel.setX(v.getX());
		vel.setY(v.getY());
	}
	public final Vector2D getVel(){
		return vel;
	}
	public final void moveBy(double x,double y){
		pos.translate(x,y);
	}
	public final void moveBy(int x,int y){
		pos.translate(x,y);
	}
	public final void moveBy(Vector2D v)
	{
		pos.translate(v);
	}
	public final void accelerate(double ax,double ay){
		vel.setX(vel.getX()+ax);
		vel.setY(vel.getY()+ay);
	}
	public final void accelerate(double speed){
		vel.setX(vel.getX()*speed);
		vel.setY(vel.getY()*speed);
	}
	public int getWidth(){
		return frameWidth;
	}
	public int getHeight(){
		return frameHeight;
	}
	//从moveable接口所继承的方法
	@Override
	public Rectangle2D getBounds() {
		return bounds;
	}

	//判断一个moveable对象是否和这个对象冲突
	@Override
	public boolean collidesWith(Moveable other) {
		return (bounds.contains(other.getBounds())||bounds.intersects(other.getBounds()));
	}
	//在冲突列表中添加一个冲突对象
	@Override
	public void addCollision(Moveable other) {
		if(collisionList == null){
			collisionList = new LinkedList();
			collisionList.add(other);
			return ;
		}
		if(!collisionList.contains(other)){
			collisionList.add(other);
		}

	}
	//处理与冲突列表对象之间冲突的stub方法
	// this method is left empty ，but not abstract

	@Override
	public void processCollisions() {
		// TODO Auto-generated method stub

	}
	//更新对象的位置和边界盒，移动它然后更新变化
	@Override
	public void update() {
		pos.translate(vel);
		//System.out.print(vel.getX()+""+vel.getY());
		updateBounds();
		checkBounds();
		
		animate();
		//子类如果要覆盖这个方法，必须要求变化后是以远点为中心的
		//而不是以位置点为中心
		if(rotation !=0){
			xform.setToIdentity();
			xform.translate(pos.getX()+frameWidth/2, pos.getY()+frameHeight/2);
			xform.rotate(rotation);
			xform.translate(-frameWidth/2,-frameHeight/2);
		}
		else {//System.out.print("d");
			xform.setToTranslation(pos.getX(), pos.getY());
		}

	}

}//Actor2D
