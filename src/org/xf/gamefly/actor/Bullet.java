package org.xf.gamefly.actor;

import java.util.LinkedList;

import org.xf.app.actor2D.Actor2D;
import org.xf.app.actor2D.Actor2DGroup;
import org.xf.app.actor2D.Moveable;
import org.xf.app.actor2D.Vector2D;
import org.xf.app.actor2D.Vector2D.Double;
import org.xf.app.robot.RobotGroup;
import org.xf.gamefly.graphics.BulletGroup;

/**
 * ������Ϸ���ӵ���ص�����
 * 
 * @author л��
 * @version 1.0
 * @time 2016-3-28
 */
abstract public class Bullet extends Actor2D {

	/**
	 * ��ǰ����֡��������
	 */
	protected int currAnimIndex;
	/**
	 * �����ӵ������������ӵ��б�
	 */
	protected LinkedList nextBullet;
	/**
	 * �����ӵ��Ľ�ɫ
	 */
	protected Hero hostHero;

	/**
	 * �ӵ��ƶ����ܳ���-����
	 */
	public double moveLength;
	/**
	 * �������ֵ��ˡ����Ǽ����ӵ�ʱ��ѡ��Ĺ��췽�� �趨�ӵ�����
	 */
	protected int num;
	
	public final int getNum(){
		return num;
	}
	public final Hero getHostHero() {
		return hostHero;
	}

	public final void setHostHero(Hero hostHero) {
		this.hostHero = hostHero;
	}

	/**
	 * ��� �����า�ǣ�����null; ��������ǣ����ظ��ӵ��ĺ�׶β������ӵ��б��� ���ѵ���Բ��
	 * 
	 * @return
	 */
	public LinkedList getNextBulletList() {
		return null;
	}

	public void setBulletShotList() {
	}

	/**
	 * @param theta
	 *            �ӵ�ѡ��Ƕ�
	 * @param v
	 *            �ӵ��ٶ�ʸ��
	 * @param vpos
	 *            �ӵ�λ��ʸ��
	 */
	abstract public void setPosVelRot(double theta, Vector2D v, Vector2D vpos);

	/**
	 * ���ö���֡�л��ĵȴ���� ����Ĭ�϶���֡index
	 * 
	 * @param grp
	 *            ���ӵ������Ķ���֡��
	 */
	public Bullet(BulletGroup grp) {
		super(grp);
		animWait = 3;
		currAnimIndex = 0;
		// currAnimation = group.getAnimationStrip(BulletGroup.BULLET_SHOT);
		//

	}

	/**
	 * ɢ��-ֱ��
	 * 
	 * @author л��
	 *
	 */
	
	public static class BulletShot extends Bullet {
		

		/**
		 * ����ɢ����ʽ
		 * 
		 * @param grp
		 */
		public BulletShot(BulletGroup grp) {
			super(grp);
			num = 0;
			currAnimIndex = 0;
			currAnimation = grp
					.getAnimationStrip(BulletGroup.ENEMY_BULLET_SHOT);
			// if(currAnimation==null) System.out.print("1");
			frameWidth = currAnimation.getFrameWidth();
			frameHeight = currAnimation.getFrameHeight();

		}

		/**
		 * ����ɢ����ʽ
		 * 
		 * @param grp
		 * @param num
		 */
		public BulletShot(BulletGroup grp, int num) {
			super(grp);
			this.num = num;
			currAnimIndex = 0;
			currAnimation = grp.getAnimationStrip(BulletGroup.PLAYER_BULLET_SHOT);
			frameWidth = currAnimation.getFrameWidth();
			frameHeight = currAnimation.getFrameHeight();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.xf.gamefly.actor.Bullet#setPosVelRot(double,
		 * org.xf.app.actor2D.Vector2D, org.xf.app.actor2D.Vector2D)
		 */
		public void setPosVelRot(double theta, Vector2D veln, Vector2D vpos) {
			if (veln == null) {
				vel.setX(0);
				vel.setY( - num);
			} else {
				setVel(veln);
			}
			setPos(vpos);
			setRot(theta);
			setVel(vel.getX() * Math.cos(theta) - vel.getY() * Math.sin(theta),
					vel.getY() * Math.cos(theta) + vel.getX() * Math.sin(theta));
			updateBounds();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.xf.app.actor2D.Actor2D#processCollisions()
		 */
		@Override
		public void processCollisions() {
			if (this.collisionList != null) {
				for (int i = 0; i < collisionList.size(); i++) {
					if (collisionList.get(i) instanceof Enemy && num == 10) {
						this.setState(STATE_DEAD);// �����Լ���״̬Ϊdead
						collisionList.remove(i); // ��������������ӵ�����ײ��¼����ɾ��
						i--;
					} else if ((collisionList.get(i) instanceof Hero && num == 0)) {// ע���������else����������
						this.setState(STATE_DEAD);
						collisionList.remove(i);
						i--;

					}

				}
			}

		}

	}

	// ����
	/**
	 * ������������
	 * 
	 * @author л��
	 *
	 */
	public static class BulletLaser extends Bullet {

		/**
		 * ���˼���
		 * 
		 * @param grp
		 */
		public BulletLaser(BulletGroup grp) {
			super(grp);
			num = 0;
			currAnimIndex = 0;
			currAnimation = grp
					.getAnimationStrip(BulletGroup.ENEMY_BULLET_LASER);
			frameWidth = currAnimation.getFrameWidth();
			frameHeight = currAnimation.getFrameHeight();
			// TODO Auto-generated constructor stub
		}

		/**
		 * ���Ǽ���
		 * 
		 * @param grp
		 * @param num
		 */
		public BulletLaser(BulletGroup grp, int num) {
			super(grp);
			this.num = num;
			currAnimIndex = 0;
			currAnimation = grp
					.getAnimationStrip(BulletGroup.PLAYER_BULLET_LASER);
			frameWidth = currAnimation.getFrameWidth();
			frameHeight = currAnimation.getFrameHeight();
			// TODO Auto-generated constructor stub
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.xf.gamefly.actor.Bullet#setPosVelRot(double,
		 * org.xf.app.actor2D.Vector2D, org.xf.app.actor2D.Vector2D)
		 */
		public void setPosVelRot(double theta, Vector2D veln, Vector2D vpos) {
			if (veln == null) {
				vel.setX(0);
				vel.setY( - num);
			} else {
				setVel(veln);
			}
			setPos(vpos);
			setRot(theta);
			// accelerate(2.0, 2.0);
			setVel(vel.getX() * Math.cos(theta) - vel.getY() * Math.sin(theta),
					vel.getY() * Math.cos(theta) + vel.getX() * Math.sin(theta));
			updateBounds();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.xf.app.actor2D.Actor2D#processCollisions()
		 */
		@Override
		public void processCollisions() {
			if (this.collisionList != null) {
				for (int i = 0; i < collisionList.size(); i++) {
					if (collisionList.get(i) instanceof Enemy && num == 10) {
						this.setState(STATE_DEAD);
						collisionList.remove(i);
						i--;

					} else if ((collisionList.get(i) instanceof Hero && num == 0)) {

						this.setState(STATE_DEAD);
						collisionList.remove(i);
						i--;

					}

				}
			}

		}

	}

	// Բ�����Լ�
	/**
	 * Բ�� ����
	 * 
	 * @author л��
	 *
	 */
	public static class BulletMess extends Bullet {

		/**
		 * Բ���Ķ���֡���߶ȡ��������
		 * 
		 * @param grp
		 */
		public BulletMess(BulletGroup grp,int num) {
			super(grp);
			this.num=num;
			currAnimIndex = 0;
			nextBullet = null;
			currAnimation = grp
					.getAnimationStrip(BulletGroup.PLAYER_BULLET_MESS);
			frameWidth = currAnimation.getFrameWidth();
			frameHeight = currAnimation.getFrameHeight();
		}

		// step 1
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.xf.gamefly.actor.Bullet#setPosVelRot(double,
		 * org.xf.app.actor2D.Vector2D, org.xf.app.actor2D.Vector2D)
		 */
		public void setPosVelRot(double theta, Vector2D veln, Vector2D vpos) {

			if (veln == null) {
				vel.setX(0);
				vel.setY(-10);
			} else {
				setVel(veln);
			}
			setPos(vpos);
			updateBounds();
			setBulletShotList();

		}

		// step 2
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.xf.gamefly.actor.Bullet#setBulletShotList()
		 */
		public void setBulletShotList() {
			// ����Բ���Ĳ������

			int n = 36;
			BulletShot bas;
			double radstep = Math.toRadians(10);
			double rad = radstep;
			Vector2D vell = new Vector2D.Double(0, -5);
			// BulletGroup grp = new BulletGroup();
			for (int i = 0; i < n; i++) {
				bas = new BulletShot((BulletGroup) group, 10);

				bas.setPosVelRot(rad, null, this.pos);
				addBullet(bas);
				rad += radstep;
			}
		}

		/**
		 * ����׶β������ӵ��б�������ӵ�
		 * 
		 * @param bas
		 */
		public void addBullet(BulletShot bas) {
			if (nextBullet == null) {
				nextBullet = new LinkedList();
			}
			nextBullet.add(bas);

		}

		// step 3
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.xf.gamefly.actor.Bullet#getNextBulletList()
		 */
		public LinkedList getNextBulletList() {

			return nextBullet;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.xf.app.actor2D.Actor2D#processCollisions()
		 */
		@Override
		public void processCollisions() {
			if (this.collisionList != null) {
				for (int i = 0; i < collisionList.size(); i++) {
					if (collisionList.get(i) instanceof Enemy) {
						this.setState(STATE_DEAD);
						collisionList.remove(i);
						i--;

						if (this.getNextBulletList() != null) {
							Bullet a;
							for (int j = 0; j < this.getNextBulletList().size(); j++) {
								a = (Bullet) this.getNextBulletList().get(j);
								a.setState(STATE_DEAD);
							}
						}
					}
				}

			}
		}

	}

	// ����
	/**
	 * ���� ���ٵ�
	 * 
	 * @author Administrator
	 *
	 */
	public static class BulletTrack extends Bullet {
		/**
		 * �ӵ�������֮ǰ�ľ���
		 */
		private double distance;
		private Hero ziki;
		public BulletTrack(BulletGroup grp) {
			super(grp);
			
			// ��ʼ��ͼ��
			currAnimIndex = 0;
			currAnimation = grp
					.getAnimationStrip(BulletGroup.ENEMY_BULLET_TRACK);
			frameWidth = currAnimation.getFrameWidth();
			frameHeight = currAnimation.getFrameHeight();

			// TODO Auto-generated constructor stub
		}

		// step 2
		public void setPosVelRot(double theta, Vector2D veln, Vector2D vpos) {
			// if(veln==null){
			// vel.setX(0);
			// vel.setY(5.0-num);
			// }else {
			// setVel(veln);
			// }

			// ����λ��
			
			ziki = this.getHostHero();
			if(ziki!=null){
			double speed = 1;// ���ܻ���Ҫ�������ã�������
			double xd = (ziki.getX() + ziki.getWidth() / 2 - this.getX() - this
					.getWidth() / 2);
			double yd = (ziki.getY() + ziki.getHeight() / 2 - this.getY() - this
					.getHeight() / 2);
			distance = Math.sqrt(xd * xd + yd * yd);
			if (distance != 0.0) {
				vel.setX((xd / distance) * speed);
				vel.setY((yd / distance) * speed);
			} else {
				vel.setX(0);
				vel.setY(speed);
			}
			this.setRot(Math.asin((-xd / distance)));
			updateBounds();
			}
		}

		// ���¶����λ�úͱ߽�У��ƶ���Ȼ����±仯
		@Override
		public void update() {
			setPosVelRot(this.getRot(), this.getVel(), this.getPos());
			move(vel);
			// System.out.print(vel.getX()+""+vel.getY());
			updateBounds();
			checkBounds();

			animate();
			// �������Ҫ�����������������Ҫ��仯������Զ��Ϊ���ĵ�
			// ��������λ�õ�Ϊ����
			if (rotation != 0) {
				xform.setToIdentity();
				xform.translate(pos.getX() + frameWidth / 2, pos.getY()
						+ frameHeight / 2);
				xform.rotate(rotation);
				xform.translate(-frameWidth / 2, -frameHeight / 2);
			} else {// System.out.print("d");
				xform.setToTranslation(pos.getX(), pos.getY());
			}

		}
		
		/**
		 * �ƶ�һ���ٶ�ʸ��������¼�Լ��ƶ��ľ���
		 * 
		 * @param v
		 *            �ٶ�ʸ��
		 */
		public void move(Vector2D v) {
			moveBy(v);
			moveLength += Math.sqrt(v.getX() * v.getX() + v.getY() * v.getY());
		}

		/**
		 * @param x
		 *            �ٶ�ʸ��-x
		 * @param y
		 *            �ٶ�ʸ��-y
		 */
		public void move(double x, double y) {
			moveBy(x, y);
			moveLength += Math.sqrt(x * x + y * y);
		}

		// step 1
		/**
		 * ���ٵ���Ŀ��
		 * 
		 * @param ziki
		 */
		public void setZiki(Hero ziki) {
			this.ziki = ziki;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.xf.app.actor2D.Actor2D#processCollisions()
		 */
		@Override
		public void processCollisions() {
			if (this.collisionList != null) {
				for (int i = 0; i < collisionList.size(); i++) {
					if ((collisionList.get(i) instanceof Hero)) {
						this.setState(STATE_DEAD);// ���ø��ӵ�״̬
						collisionList.remove(i);// ɾ������ӵ���ײ���б�
						i--;
					}

				}
			}

		}

	}

	// ���
	/**
	 * 
	 * ���������
	 * 
	 * @author Administrator
	 *
	 */
	public static class BulletRandom extends Bullet {
		/**
		 * ������Ķ���֡���߶ȡ����
		 * 
		 * @param grp
		 */
		public BulletRandom(BulletGroup grp) {
			super(grp);
			currAnimIndex = 0;
			currAnimation = grp
					.getAnimationStrip(BulletGroup.ENEMY_BULLET_RANDOM);
			frameWidth = currAnimation.getFrameWidth();
			frameHeight = currAnimation.getFrameHeight();
			// for(int i=0;i<n;i++){
			// bas= new BulletShot(grp, rad, vpos);
			// addBullet(bas);
			// rad+=radstep;
			//
			// }

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.xf.gamefly.actor.Bullet#setPosVelRot(double,
		 * org.xf.app.actor2D.Vector2D, org.xf.app.actor2D.Vector2D)
		 */
		public void setPosVelRot(double theta, Vector2D veln, Vector2D vpos) {
			setPos(vpos);
			setVel(veln);
			

			// ����������Ĳ������
			int n = 6;
			BulletShot bas;
			double radstep = Math.toRadians(180);
			double rad = radstep / 2 + radstep * Math.random();
			this.setRot(rad);
			this.setVel(
					vel.getX() * Math.cos(rad) - vel.getY() * Math.sin(rad),
					vel.getY() * Math.cos(rad) + vel.getX() * Math.sin(rad));
			updateBounds();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.xf.app.actor2D.Actor2D#processCollisions()
		 */
		@Override
		public void processCollisions() {
			if (this.collisionList != null) {
				for (int i = 0; i < collisionList.size(); i++) {

					if ((collisionList.get(i) instanceof Hero)) {
						this.setState(STATE_DEAD);// ���ø��ӵ�״̬
						collisionList.remove(i);// ɾ������ӵ���ײ���б�
						i--;
					}

				}
			}

		}

	}// Բ��

	/**
	 * ����Բ��
	 * 
	 * @author Administrator
	 *
	 */
	public static class BulletCircle extends Bullet {

		public BulletCircle(BulletGroup grp) {
			super(grp);
			currAnimIndex = 0;
			moveLength = 0;
			nextBullet = null;

			currAnimation = group
					.getAnimationStrip(BulletGroup.ENEMY_BULLET_CIRCLE);
			frameWidth = currAnimation.getFrameWidth();
			frameHeight = currAnimation.getFrameHeight();

		}

		// step 1
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.xf.gamefly.actor.Bullet#setPosVelRot(double,
		 * org.xf.app.actor2D.Vector2D, org.xf.app.actor2D.Vector2D)
		 */
		public void setPosVelRot(double theta, Vector2D veln, Vector2D vpos) {
			setPos(vpos);
			setVel(veln);
			setRot(theta);
			setVel(vel.getX() * Math.cos(theta) - vel.getY() * Math.sin(theta),
					vel.getY() * Math.cos(theta) + vel.getX() * Math.sin(theta));
			updateBounds();
		}

		// step 2
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.xf.gamefly.actor.Bullet#setBulletShotList()
		 */
		public void setBulletShotList() {
			// ����Բ���Ĳ������

			int n = 24;
			BulletShot bas;
			double radstep = Math.toRadians(15);

			double rad = ((-n / 2) + 0.5) * radstep + radstep * 8
					* (Math.random() - 0.5);
			Vector2D vell = new Vector2D.Double(0, -0.1);
			for (int i = 0; i < n; i++) {
				bas = new BulletShot((BulletGroup) group);
				bas.setPosVelRot(rad, vell, this.pos);
				addBullet(bas);
				rad += radstep;
			}

		}

		/**
		 * �ƶ�һ���ٶ�ʸ��������¼�Լ��ƶ��ľ���
		 * 
		 * @param v
		 *            �ٶ�ʸ��
		 */
		public void move(Vector2D v) {
			moveBy(v);
			moveLength += Math.sqrt(v.getX() * v.getX() + v.getY() * v.getY());
		}

		/**
		 * @param x
		 *            �ٶ�ʸ��-x
		 * @param y
		 *            �ٶ�ʸ��-y
		 */
		public void move(double x, double y) {
			moveBy(x, y);
			moveLength += Math.sqrt(x * x + y * y);
		}

		/**
		 * ��׶��ӵ����
		 * 
		 * @param bas
		 */
		public void addBullet(BulletShot bas) {
			if (nextBullet == null) {
				nextBullet = new LinkedList();
			}
			nextBullet.add(bas);

		}

		// step 3
		public LinkedList getNextBulletList() {
			return nextBullet;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.xf.app.actor2D.Actor2D#processCollisions()
		 */
		@Override
		public void processCollisions() {
			if (this.collisionList != null) {
				for (int i = 0; i < collisionList.size(); i++) {
					if ((collisionList.get(i) instanceof Hero)) {
						this.setState(STATE_DEAD);// ���ø��ӵ�״̬
						collisionList.remove(i);// ɾ������ӵ���ײ���б�
						i--;

						if (this.getNextBulletList() != null) {
							Bullet a;
							for (int j = 0; j < this.getNextBulletList().size(); j++) {
								a = (Bullet) this.getNextBulletList().get(j);
								a.setState(STATE_DEAD);
							}
						}
					}

				}
			}

		}

	}

	// ��Բ
	/**
	 * ���ѵ�-��Բ
	 * 
	 * @author л��
	 *
	 */
	public static class BulletSplit extends Bullet {
		/**
		 * ��¼�ӵ��Ѿ��ƶ��ľ���
		 */
		private int outdistance;

		public BulletSplit(BulletGroup grp) {
			super(grp);
			currAnimIndex = 0;
			moveLength = 0;

			nextBullet = null;
			currAnimation = group
					.getAnimationStrip(BulletGroup.ENEMY_BULLET_SPLIT);
			frameWidth = currAnimation.getFrameWidth();
			frameHeight = currAnimation.getFrameHeight();

		}

		public void move(double x, double y) {
			moveBy(x, y);
			moveLength += Math.sqrt(x * x + y * y);
		}

		public void move(Vector2D v) {
			moveBy(v);
			moveLength += Math.sqrt(v.getX() * v.getX() + v.getY() * v.getY());
		}

		// step 1
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.xf.gamefly.actor.Bullet#setPosVelRot(double,
		 * org.xf.app.actor2D.Vector2D, org.xf.app.actor2D.Vector2D)
		 */
		public void setPosVelRot(double theta, Vector2D veln, Vector2D vpos) {
			setPos(vpos);
			if (veln == null) {
				vel.setX(0);
				vel.setY(5.0);
			} else {
				setVel(veln);
			}
			setRot(theta);
			setVel(vel.getX() * Math.cos(theta) - vel.getY() * Math.sin(theta),
					vel.getY() * Math.cos(theta) + vel.getX() * Math.sin(theta));
			updateBounds();
		}

		// step 2
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.xf.gamefly.actor.Bullet#setBulletShotList()
		 */
		public void setBulletShotList() {
			// ����Բ���Ĳ������

			int n = 9;
			BulletShot bas;
			double radstep = Math.toRadians(20);

			double rad = ((-n / 2) + 0.5) * radstep + radstep * 8
					* (Math.random() - 0.5);
			Vector2D vell = new Vector2D.Double(0, -0.1);
			for (int i = 0; i < n; i++) {
				bas = new BulletShot((BulletGroup) group);
				bas.setPosVelRot(rad, vell, this.pos);
				addBullet(bas);
				rad += radstep;
			}
		}

		public void addBullet(BulletShot bas) {
			if (nextBullet == null) {
				nextBullet = new LinkedList();
			}
			nextBullet.add(bas);

		}

		// step 3
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.xf.gamefly.actor.Bullet#getNextBulletList()
		 */
		public LinkedList getNextBulletList() {

			return nextBullet;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.xf.app.actor2D.Actor2D#processCollisions()
		 */
		@Override
		public void processCollisions() {
			if (this.collisionList != null) {
				for (int i = 0; i < collisionList.size(); i++) {
					if ((collisionList.get(i) instanceof Hero)) {
						this.setState(STATE_DEAD);

						if (this.getNextBulletList() != null) {
							Bullet a;
							for (int j = 0; j < this.getNextBulletList().size(); j++) {
								a = (Bullet) this.getNextBulletList().get(j);
								a.setState(STATE_DEAD);
							}
						}
					}
				}
			}

		}

	}

}
