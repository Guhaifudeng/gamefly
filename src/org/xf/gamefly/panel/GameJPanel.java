package org.xf.gamefly.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JPanel;

import org.xf.app.actor2D.Actor2D;
import org.xf.app.actor2D.AnimationStrip;
import org.xf.app.actor2D.Vector2D;
import org.xf.gamefly.actor.Bullet;
import org.xf.gamefly.actor.Enemy;
import org.xf.gamefly.actor.Hero;
import org.xf.gamefly.actor.StaticActor;
import org.xf.gamefly.frame.GameMain;
import org.xf.gamefly.frame.HomeJFrame;
import org.xf.gamefly.graphics.AwardGroup;
import org.xf.gamefly.graphics.BulletGroup;
import org.xf.gamefly.graphics.BurstGroup;
import org.xf.gamefly.graphics.EnemyGroup;
import org.xf.gamefly.graphics.HeroGroup;
import org.xf.gamefly.graphics.StaticActorGroup;
import org.xf.gamefly.io.ObjectWriteHero;
import org.xf.gamefly.io.OpIOSet;
import org.xf.gamefly.scene.SpaceScene;
import org.xf.gamefly.scene.TreeScene;
import org.xf.gamefly.util.CreatEnemyRandom;
import org.xf.gamefly.util.RoleBulletGroup;

public class GameJPanel extends JPanel implements Runnable, KeyListener,
		MouseListener, MouseMotionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final int RUNNING = 0;
	protected final int GAME_OVER = 1;
	protected final int PAUSE = 2;
	private int gameState;
	private Enemy ship;
	private TreeScene treeScene;
	private Thread animation, animation2;
	private Bullet tmp;
	private BulletGroup grp;
	Image image;
	private Hero hero;
	SpaceScene scene;
	CreatEnemyRandom cr;
	Random r;

	public static AwardGroup awardGroup;
	private StaticActor actors;
	private boolean aniflag;
	private RoleBulletGroup roleBulletGroup;
	private GameMain gameFrm;
	public static EnemyGroup enemyGroup;
	public static HeroGroup heroGroup;
	public static StaticActorGroup backgroundGroup;
	public static BurstGroup burstGroup;

	public GameJPanel(GameMain gameFrm) {
		this.gameFrm = gameFrm;

		// Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		// setSize(screensize);
		
		// 奖励动画组
		awardGroup = new AwardGroup();
		awardGroup.init(this);
		// 主角动画组
		heroGroup = new HeroGroup();
		heroGroup.init(this);
		// 敌人动画组
		enemyGroup = new EnemyGroup();
		enemyGroup.init(this);
		// 静态背景动画
		backgroundGroup = new StaticActorGroup("myimage/bg3.gif");
		backgroundGroup.init(this);
		// 爆炸动画组
		burstGroup = new BurstGroup();
		burstGroup.init(this);
		// 工具类-枪
		roleBulletGroup = new RoleBulletGroup(this);

		setBounds(0, 0, 500, 700);
		animation = new Thread(this);
		animation2 = new Thread(this);

		init();
	}

	public synchronized void crear() {
		// System.out.print(1);
		
		treeScene.clear();

		animation = null;
		animation2 = null;
		tmp = null;
		grp = null;
		image = null;
		nextBulletList = null;
		nowBulletList = null;
		hero = null;
		ship = null;
		r=null;
		// timeNum=0;
		cr = null;
		;
		r = null;
		bullet = null;
		treeScene = null;
	}

	public void init() {
		maxEnemyNum = 10;// 最大敌人数量
		bulletMax = 8;// 9-对应跟踪弹
		aniflag = true;// 运行状态
		maxFlag = new boolean[5];
		for (int i = 0; i < 5; i++) {
			maxFlag[i] = true;
		}

		gameState = RUNNING;
		treeScene = new TreeScene(this.getBounds());
		// 创建飞船和它的角色组

		

		hero = new Hero(heroGroup, roleBulletGroup);
		hero.setPos(100, 400);
		hero.updateBounds();
		
		// ship = new Hero(group,bgroup);
		r = new Random();
		cr = new CreatEnemyRandom(this);
		cr.setVbulletLevel(2);
		cr.setEnemyMove(1);
		
	
		if(HomeJFrame.boy!=null){
			hero.setCurAnimation(HeroGroup.BOY_HERO);
			hero.setUser(HomeJFrame.boy);

		}
		if(HomeJFrame.girl!=null){
			hero.setCurAnimation(HeroGroup.GIRL_HERO);
			hero.setUser(HomeJFrame.girl);
		}
		
		treeScene.add(hero);

		// // BurstGroup burstg = new BurstGroup();
		// burstg.init(this);
		// Burst burst = new Burst.BasicBurst(burstg, new Vector2D.Double(100,
		// 0));
		// burst.setVel(0, 2.0);
		// bulletScene.add(burst, false);

		// grp = new BulletGroup();
		// grp.init(this);
		//
		// tmp = new Bullet.BulletSplit(grp);
		// new Vector2D.Double(ship.getX() + ship.getWidth() / 2,
		// ship.getY());
		// tmp.setPosVelRot(0, null, ship.getPos());
		// bulletScene.add(tmp,false);
		// tmp.setPos(ship.getX() + ship.getWidth() / 2 - tmp.getWidth() / 2,
		// ship.getY() - tmp.getHeight());
		// bulletScene.add(tmp,false);
		// tmp.setVel(0, -10);
		// tmp.setPos();
		// tmp.setBulletShotList();
		// LinkedList<Bullet> tmpa=tmp.getBulletList();
		// if(tmpa!=null){
		// for(int i = 0;i<tmpa.size();i++){
		// bulletScene.add(tmpa.get(i),false);
		// }
		// }
		// 用这个JPanel窗体大小创建场景
		scene = new SpaceScene(getBounds());
		// "背景" "前景" 场景图像

		// 创建背景

		actors = new StaticActor(backgroundGroup);
		actors.setPos(0.0, 0.0);
		actors.setVel(0, 0.3);
		// 设置scenery
		scene.setScenery(actors);

		addKeyListener(this);
		this.setFocusable(true);
		// addMouseListener(this);
		addMouseMotionListener(this);
		AnimationStrip.observer = this;
	}

	public synchronized boolean resetStart() {
		if (gameState == RUNNING && hero.getState() == Actor2D.STATE_DEAD) {
			
			gameState = GAME_OVER;
			if(hero.getUser()!=null){
				
				if(hero.getScore()>hero.getUser().getHighestScore())
					hero.getUser().setHighestScore(hero.getScore());
				else{
					
				}
				
				hero.getUser().updateScore(hero.getUser());
				ObjectWriteHero.setObjectWriteUser(hero.getUser());
				//OpIOSet.outAllData();
			}
			//更新积分
			return true;
		} else
			return false;
	}

	public void run() {

		Thread t = Thread.currentThread();

		while (animation != null && t == animation) {
			if (aniflag && resetStart()) {
				aniflag = false;
				crear();
				continue;
			}
			update();
			repaint();
			// timeNum++;
			// System.out.print("+");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				break;
			}
		}
		while (animation2 != null && t == animation2) {
			if (aniflag && resetStart()) {
				aniflag = false;
				crear();
				continue;
			}
			update();
			repaint();
			// System.out.print("-");
			// timeNum++;
			// System.out.print(timeNum);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				break;
			}
		}
	} // run

	/**
	 * 主角或敌人初始化对象后产生的子弹列表
	 */
	private LinkedList<Bullet> nowBulletList;
	/**
	 * 特殊子弹后续分裂产生的子弹列表
	 */
	private LinkedList<Bullet> nextBulletList;//

	/**
	 * 场景中敌人出现个数的最大值
	 */
	private int maxEnemyNum;
	private boolean[] maxFlag;
	private int bulletMax;
	private LinkedList<Enemy> shipList;
	private int enemyCreatTime;
	private int bulletAddTime;

	/**
	 * 更新画板数据 更新实体碰撞列表
	 */
	public synchronized void update() {

		// 设置关卡难度-敌人的数量、产生频率
			if (hero != null) {
			int score = hero.getScore();
			if (score > 1300 && maxFlag[4]) {
				maxEnemyNum += 5;
				maxFlag[4] = false;
				bulletMax = 9;
				//cr.setVbulletLevel(4);
				//cr.setEnemyMove(3);
			} else if (score > 1200 && maxFlag[3]) {
				maxEnemyNum += 2;
				maxFlag[3] = false;
			} else if (score > 1100 && maxFlag[2]) {
				maxEnemyNum += 3;
				maxFlag[2] = false;
				cr.setVbulletLevel(3);
				cr.setEnemyMove(2);
			} else if (score > 900 && maxFlag[1]) {
				maxEnemyNum += 5;
				maxFlag[1] = false;
			} else if (score > 500 && maxFlag[0]) {
				maxEnemyNum += 10;
				maxFlag[0] = false;
			}
		}
		enemyCreatTime++;
		// 随机产生敌人加入
		if (treeScene != null && treeScene.getEnemyNum() < maxEnemyNum && enemyCreatTime >40) {
			shipList = null;
			enemyCreatTime=0;
			int id = r.nextInt(bulletMax);
			shipList = new LinkedList<Enemy>();
			for (int i = 0; i < 4; i++) {
			
				ship = cr.creatNowEnemy(id);
				ship.setHero(hero);
				ship.setPosVelRot(0, ship.getVel(), new Vector2D.Double(ship
						.getPos().getX() + 200 * i, ship.getPos().getY()));
				shipList.add(ship);
				
			}
		}

		// 设置关卡难度-子弹的产生频率
		// 向场景中添加新的子弹
		bulletAddTime++;
		if (shipList != null && bulletAddTime>70){
			for (int i = 0; i < shipList.size(); i++) {
				
				ship = shipList.get(i);
				treeScene.add(ship);
				ship.setBulletList();
				nowBulletList = ship.getBulletList();
				if (nowBulletList != null) {
					//System.out.print(i);
					for (int ii = 0; ii < nowBulletList.size(); ii++) {
						bullet = (Bullet) nowBulletList.get(ii);
						treeScene.add(bullet);
						nextBulletList = bullet.getNextBulletList();
						if (nextBulletList != null) {
							for (int ij = 0; ij < nextBulletList.size(); ij++) {
								treeScene.add(nextBulletList.get(ij));
							}
						}
					}

				}

			}
			bulletAddTime=0;
		}
		
		
		// if(treeScene.getEnemyNum()>=20)treeScene.setEnemyNum(0);
		// System.out.print("1");

		// 加入主角发出的信息
		if (hero instanceof Hero) {

			if (nowBulletList != null) {
				for (int i = 0; i < nowBulletList.size(); i++) {
					bullet = (Bullet) nowBulletList.get(i);
					treeScene.add(bullet);
					nextBulletList = bullet.getNextBulletList();
					if (nextBulletList != null) {
						for (int j = 0; j < nextBulletList.size(); j++) {
							treeScene.add(nextBulletList.get(j));
						}
					}
				}
			}

		}

		scene.update();
		// 更新场景
		if (animation2 != null && animation != null && treeScene != null)
			treeScene.update();
		// paint(g);
	}

	public void start() {
		animation.start();
		animation2.start();
		// 启动动画线程

	}

	public void stop() {
		animation = null;
		animation2 = null;
	}

	public void clearPaint() {
		Graphics2D bg = (Graphics2D) this.getGraphics();// offscreen.getGraphics();
		bg.dispose();
		// 绘制场景
		// ship.paint(bg,ship.getX(),ship.getY());
		// scene.paint(bg);
		// treeScene.paint(bg);

	}

	public synchronized void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D bg = (Graphics2D) g;// offscreen.getGraphics();
		bg.setPaint(Color.WHITE);
		bg.clearRect(0, 0, getSize().width, getSize().height);
		// 绘制场景
		// ship.paint(bg,ship.getX(),ship.getY());
		Thread t = Thread.currentThread();
		scene.paint(bg);
		if (t == animation2 && animation == null || t == animation
				&& animation2 == null) {
			return;// 并无法保证真正意义上的互斥
		}
		if (treeScene != null)
			treeScene.paint(bg);
		paintNotice(bg);

		// g.drawImage(offscreen, 0, 0, this);
	} // paint

	private int endScore;

	public synchronized void paintNotice(Graphics2D g2d) {
		String notice = "积分:";
		if (hero != null) {
			endScore = hero.getScore();
			
		}
		Font baseFont = new Font("Helvetica", Font.PLAIN, 50);
		g2d.setFont(baseFont);
		g2d.setPaint(Color.RED);
		if (hero == null || hero != null
				&& hero.getState() == Actor2D.STATE_DEAD) {

			// treeScene = null;
			g2d.drawString("Game Over", this.getWidth() / 2 - 150,
					this.getHeight() / 2);

		}
		baseFont = new Font("Helvetica", Font.PLAIN, 30);
		g2d.setFont(baseFont);
		g2d.setPaint(Color.RED);
		g2d.drawString(notice + String.valueOf(endScore), 50, 50);
		if (hero != null){
			if(hero.getLifeNum()>=20) hero.setLifeNum(20);
			g2d.fillRect(this.getWidth() - 100, this.getHeight() - 100,
					(int) (80 * hero.getLifeNum() / 20.0), 20);
		}
		g2d.drawRect(this.getWidth() - 100, this.getHeight() - 100, 80, 20);

		// g2d.translate(45, 50);

	}

	@Override
	public void keyTyped(KeyEvent e) {

		// TODO Auto-generated method stub

	}

	boolean x = false;
	boolean z = false;
	boolean up = false;
	boolean down = false;
	boolean left = false;
	boolean ctrl = false;
	boolean right = false;
	private Bullet bullet = null;
	private int heroBulletNum = 0;
	private boolean f2 = false;
	private boolean f1;

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case 17:
			ctrl = true;
			// System.out.print("1");
			break;
		case KeyEvent.VK_Z: {
			if (heroBulletNum < 20) {
				z = true;
				heroBulletNum++;
			} else {
				z = false;
			}
			// z = true;
		}
			break;
		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		case KeyEvent.VK_X:
			x = true;
			break;
		case KeyEvent.VK_F2:
			f2 = true;
			break;
		case KeyEvent.VK_F1:
			f1 = true;
			break;
		default:
			break;

		}
		updateKey();

	}

	void updateKey() {

		if (f2 && gameState == GAME_OVER) {

			animation = new Thread(this);
			animation2 = new Thread(this);
			clearPaint();
			init();
			gameState = RUNNING;

			start();

		}

		if (f1 && gameState == GAME_OVER) {
			this.gameFrm.stopSound();
			this.gameFrm.dispose();
			
			if(HomeJFrame.boy!=null){
				new HomeJFrame(HomeJFrame.boy).setVisible(true);
			}else if(HomeJFrame.girl!=null){
				new HomeJFrame(HomeJFrame.girl).setVisible(true);
			}
			else {
				new HomeJFrame().setVisible(true);
			}
		}
		if (hero != null) {
			if (z) {

				hero.startShooting();
				nowBulletList = hero.getMyBulletList();

				// heroBulletNum++;
				// System.out.print("2");
				// ship.startShooting();
				// tmpa = ship.getBulletList();
				// if (ship instanceof Enemy) {
				//
				// for (int i = 0; i < tmpa.size(); i++){
				// System.out.println(tmpa.size());
				// tmp = tmpa.get(i);
				// bulletScene.add(tmp, false);
				// }
				//
				// }

				// tmp = new Bullet.BulletSplit(grp);
				// new Vector2D.Double(ship.getX() + ship.getWidth() / 2,
				// ship.getY());
				// tmp.setPosVelRot(0, null, ship.getPos());
				// bulletScene.add(tmp,false);
				// //tmp.setPos(ship.getX() + ship.getWidth() / 2 -
				// tmp.getWidth() /
				// 2,
				// ship.getY() - tmp.getHeight());
				// bulletScene.add(tmp,false);
				// tmp.setVel(0, -10);
				// tmp.setPos();

			} else {
				// System.out.print("1");
				hero.stopShooting();

			}
			if (ctrl && x) {
				hero.changeBulletKind();

			}
			if (up) {
				hero.moveBy(0, -10);

			}

			if (down) {
				hero.moveBy(0, +10);

				// repaint();
			}
			if (left) {
				hero.moveBy(-10, 0);

				// repaint();
			}
			if (right) {
				hero.moveBy(10, 0);

				// repaint();
			}
			if (hero.getX() < 0) {
				hero.setX(0);

				// repaint();
			}
			if (hero.getY() < 0) {
				hero.setY(0);

				// repaint();
			}

			if (hero.getY() + hero.getHeight() > this.getHeight())
				hero.setY(this.getHeight() - hero.getHeight());
			if (hero.getX() + hero.getWidth() > this.getWidth())
				hero.setX(this.getWidth() - hero.getWidth());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case 17:
			ctrl = false;
			break;
		case KeyEvent.VK_Z:
			heroBulletNum = 0;
			z = false;
			break;
		case KeyEvent.VK_UP:
			up = false;
			break;
		case KeyEvent.VK_DOWN:
			down = false;
			break;
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		case KeyEvent.VK_X:
			x = false;
			break;
		case KeyEvent.VK_F2:
			f2 = false;
			break;
		case KeyEvent.VK_F1:
			f1 = false;
			break;
		default:
			break;

		}
	}

	public void updateAndPaint() {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if (hero != null) {
			hero.setPos(e.getX() - hero.getWidth() / 2,
					e.getY() - hero.getHeight() / 2);
		}

	}
}
