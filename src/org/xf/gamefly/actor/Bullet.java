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
 * 设置游戏中子弹相关的属性
 * 
 * @author 谢峰
 * @version 1.0
 * @time 2016-3-28
 */
abstract public class Bullet extends Actor2D {

	/**
	 * 当前动画帧的索引号
	 */
	protected int currAnimIndex;
	/**
	 * 特殊子弹后续产生的子弹列表
	 */
	protected LinkedList nextBullet;
	/**
	 * 发出子弹的角色
	 */
	protected Hero hostHero;

	/**
	 * 子弹移动的总长度-像素
	 */
	public double moveLength;
	/**
	 * 用以区分敌人、主角加载子弹时所选择的构造方法 设定子弹方向
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
	 * 如果 被子类覆盖，返回null; 如果被覆盖，返回该子弹的后阶段产生的子弹列表，如 分裂弹、圆弹
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
	 *            子弹选择角度
	 * @param v
	 *            子弹速度矢量
	 * @param vpos
	 *            子弹位置矢量
	 */
	abstract public void setPosVelRot(double theta, Vector2D v, Vector2D vpos);

	/**
	 * 设置动画帧切换的等待间隔 设置默认动画帧index
	 * 
	 * @param grp
	 *            该子弹所属的动画帧组
	 */
	public Bullet(BulletGroup grp) {
		super(grp);
		animWait = 3;
		currAnimIndex = 0;
		// currAnimation = group.getAnimationStrip(BulletGroup.BULLET_SHOT);
		//

	}

	/**
	 * 散弹-直弹
	 * 
	 * @author 谢峰
	 *
	 */
	
	public static class BulletShot extends Bullet {
		

		/**
		 * 敌人散弹格式
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
		 * 主角散弹格式
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
						this.setState(STATE_DEAD);// 设置自己的状态为dead
						collisionList.remove(i); // 将被击中物体从子弹的碰撞记录表中删除
						i--;
					} else if ((collisionList.get(i) instanceof Hero && num == 0)) {// 注：必须加上else，否则会溢出
						this.setState(STATE_DEAD);
						collisionList.remove(i);
						i--;

					}

				}
			}

		}

	}

	// 激光
	/**
	 * 激光属性设置
	 * 
	 * @author 谢峰
	 *
	 */
	public static class BulletLaser extends Bullet {

		/**
		 * 敌人激光
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
		 * 主角激光
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

	// 圆弹，自己
	/**
	 * 圆弹 主角
	 * 
	 * @author 谢峰
	 *
	 */
	public static class BulletMess extends Bullet {

		/**
		 * 圆弹的动画帧、高度、宽度设置
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
			// 设置圆弹的产生情况

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
		 * 往后阶段产生的子弹列表里添加子弹
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

	// 跟踪
	/**
	 * 敌人 跟踪弹
	 * 
	 * @author Administrator
	 *
	 */
	public static class BulletTrack extends Bullet {
		/**
		 * 子弹与主角之前的距离
		 */
		private double distance;
		private Hero ziki;
		public BulletTrack(BulletGroup grp) {
			super(grp);
			
			// 初始化图像
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

			// 设置位移
			
			ziki = this.getHostHero();
			if(ziki!=null){
			double speed = 1;// 可能还需要重新设置？？？？
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

		// 更新对象的位置和边界盒，移动它然后更新变化
		@Override
		public void update() {
			setPosVelRot(this.getRot(), this.getVel(), this.getPos());
			move(vel);
			// System.out.print(vel.getX()+""+vel.getY());
			updateBounds();
			checkBounds();

			animate();
			// 子类如果要覆盖这个方法，必须要求变化后是以远点为中心的
			// 而不是以位置点为中心
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
		 * 移动一个速度矢量，并记录以及移动的距离
		 * 
		 * @param v
		 *            速度矢量
		 */
		public void move(Vector2D v) {
			moveBy(v);
			moveLength += Math.sqrt(v.getX() * v.getX() + v.getY() * v.getY());
		}

		/**
		 * @param x
		 *            速度矢量-x
		 * @param y
		 *            速度矢量-y
		 */
		public void move(double x, double y) {
			moveBy(x, y);
			moveLength += Math.sqrt(x * x + y * y);
		}

		// step 1
		/**
		 * 跟踪弹的目标
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
						this.setState(STATE_DEAD);// 设置该子弹状态
						collisionList.remove(i);// 删除与该子弹碰撞的列表
						i--;
					}

				}
			}

		}

	}

	// 随机
	/**
	 * 
	 * 敌人随机弹
	 * 
	 * @author Administrator
	 *
	 */
	public static class BulletRandom extends Bullet {
		/**
		 * 随机弹的动画帧、高度、宽度
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
			

			// 设置随机弹的产生情况
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
						this.setState(STATE_DEAD);// 设置该子弹状态
						collisionList.remove(i);// 删除与该子弹碰撞的列表
						i--;
					}

				}
			}

		}

	}// 圆形

	/**
	 * 敌人圆弹
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
			// 设置圆弹的产生情况

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
		 * 移动一个速度矢量，并记录以及移动的距离
		 * 
		 * @param v
		 *            速度矢量
		 */
		public void move(Vector2D v) {
			moveBy(v);
			moveLength += Math.sqrt(v.getX() * v.getX() + v.getY() * v.getY());
		}

		/**
		 * @param x
		 *            速度矢量-x
		 * @param y
		 *            速度矢量-y
		 */
		public void move(double x, double y) {
			moveBy(x, y);
			moveLength += Math.sqrt(x * x + y * y);
		}

		/**
		 * 后阶段子弹添加
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
						this.setState(STATE_DEAD);// 设置该子弹状态
						collisionList.remove(i);// 删除与该子弹碰撞的列表
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

	// 半圆
	/**
	 * 分裂弹-半圆
	 * 
	 * @author 谢峰
	 *
	 */
	public static class BulletSplit extends Bullet {
		/**
		 * 记录子弹已经移动的距离
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
			// 设置圆弹的产生情况

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
