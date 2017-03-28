package org.xf.app.actor2D;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;

import org.xf.app.actor2D.Vector2D.Double;

public abstract class Actor2D implements Moveable {

	// ������װ���ƶ��ͻ���һ��2D��Ϸ���������һ����Ϣ
	public static  final int STATE_ALIVE = 1;
	public static final int STATE_DYING = 2;
	public static final int STATE_DEAD = 4;

	protected int state;

	protected Actor2DGroup group;

	// actorλ�á��ٶȡ���ת�Լ�����ı任
	protected Vector2D pos;
	protected Vector2D vel;
	protected double rotation;
	protected AffineTransform xform;

	protected final double TWO_PI = 2 * Math.PI;
	// ��ͻ����õı߽����
	protected Rectangle2D bounds;
	// �����actor�г�ͻ��actor�б�
	protected LinkedList collisionList;
	// ��͸�
	protected int frameWidth;
	protected int frameHeight;
	// ָ��ǰ������������
	protected AnimationStrip currAnimation;
	// ������һ֮֡ǰ��Ҫ�ȴ���֡����һ��֡������
	protected int animWait;
	protected int animCount;

	// ����һ�����ڸ���ActorGroup��Actor2D����
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

	// ÿ��animWait֡���ƶ�һ��actor
	public void animate() {
		if (currAnimation != null) {
			//System.out.print("1");
			if (++animCount >= animWait) {
				currAnimation.getNextFrame();
				animCount = 0;
			}
		}
	}

	// ʹ��������ı任����actor
	public void paint(Graphics2D g2d) {
		if (currAnimation != null) {
			g2d.drawImage(currAnimation.getCurrFrame(), xform,
					AnimationStrip.observer);
		}
	}

	// �ڣ�x,y�����괦����actor
	public void paint(Graphics2D g2d, double x, double y) {
		if (currAnimation != null) {
			g2d.drawImage(currAnimation.getCurrFrame(),
					AffineTransform.getTranslateInstance(x, y),
					AnimationStrip.observer);
		}
	}

	// �򵥱߽�У��ж�actor�Ƿ�ʹ����actor��ͻ

	public boolean intersects(Actor2D other) {
		return bounds.intersects(other.getBounds());
	}

	// ���ݵ�ǰ��x��yλ�ø��±߽��
	public void updateBounds() {
		// ȷ��֪��actor����ȷ�Ŀ�Ⱥ͸߶�
		if (frameWidth <= 0 && currAnimation != null) {
			frameWidth = currAnimation.getFrameWidth();
		}
		if (frameHeight <= 0 && currAnimation != null) {
			frameHeight = currAnimation.getFrameHeight();
		}
		bounds.setRect(pos.getX(), pos.getY(), frameWidth, frameHeight);
	}

	// ȷ��actor�ı߽�û�г���actor�����޶��ı߽�

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

	// ����һ������actor���ַ���
	public String toString() {
		return super.toString();
	}

	// �Ѵ����ֵ�͵�ǰ������ֵ����λ�����
	public final void setState(int attr) {
		state |= attr;
	}

	// ʹ��"λ��" ����"��"������������

	public final void resetState(int attr) {
		state &= ~attr;
	}

	public final int getState() {
		return state;
	}

	public final void ClearState() {
		state = 0;
	}

	// �ж��������״̬�����Ƿ�actor��״̬����������
	public final boolean hasState(int attr) {
		return ((state&attr) != 0);
	}

	// actor�ٶȡ�λ�ú���ת���ʵķ���
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
	//��moveable�ӿ����̳еķ���
	@Override
	public Rectangle2D getBounds() {
		return bounds;
	}

	//�ж�һ��moveable�����Ƿ����������ͻ
	@Override
	public boolean collidesWith(Moveable other) {
		return (bounds.contains(other.getBounds())||bounds.intersects(other.getBounds()));
	}
	//�ڳ�ͻ�б������һ����ͻ����
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
	//�������ͻ�б����֮���ͻ��stub����
	// this method is left empty ��but not abstract

	@Override
	public void processCollisions() {
		// TODO Auto-generated method stub

	}
	//���¶����λ�úͱ߽�У��ƶ���Ȼ����±仯
	@Override
	public void update() {
		pos.translate(vel);
		//System.out.print(vel.getX()+""+vel.getY());
		updateBounds();
		checkBounds();
		
		animate();
		//�������Ҫ�����������������Ҫ��仯������Զ��Ϊ���ĵ�
		//��������λ�õ�Ϊ����
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
