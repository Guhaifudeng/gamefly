package org.xf.app.scene.wrap;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import org.xf.app.actor2D.Actor2D;
import org.xf.app.actor2D.Vector2D;
import org.xf.app.scene.Scene;

public class WrapScene extends Scene implements KeyListener {
	// ���ٷɴ��ļ���
	private Vector2D accel;
	private final double MAX_ACCEL = 5.0f;
	// ��ת 1 degree
	private final double ONE_RADIAN = Math.toRadians(1.0);

	// ����ó��������Panel
	private JPanel pnl;
	// �䵱��ת���actor
	private Actor2D ship;

	public WrapScene(Rectangle2D v, JPanel a) {
		super(v, v);
		this.pnl = a;
		accel = new Vector2D.Double();
		

	}

	public void add(Actor2D ship) {
		this.ship = ship;
	}

	@Override
	public void update() {
		double accelX = accel.getX();
		double accelY = accel.getY();
		// �ֱ�������(x��y)������м���
		// �ٴμ�����ֵ
		if (accelX != 0) {
			ship.accelerate(accelX, 0);
			if (ship.getVel().getX() > MAX_ACCEL) {

				ship.getVel().setX(MAX_ACCEL);
			} else if (ship.getVel().getX() < -MAX_ACCEL) {

				ship.getVel().setX(-MAX_ACCEL);
			}
		}
		if (accelY != 0) {
			ship.accelerate(0, accelY);
			if (ship.getVel().getY() > MAX_ACCEL) {

				ship.getVel().setY(MAX_ACCEL);
			} else if (ship.getVel().getY() < -MAX_ACCEL) {

				ship.getVel().setY(-MAX_ACCEL);
			}
		}
		// ������ټ��٣��÷ɴ�������
		if (!ship.getVel().equals(Vector2D.ZERO_VECTOR) && accelX == 0
				&& accelY == 0) {

			Vector2D drag = ship.getVel();
			drag.normalize();
			ship.accelerate(-drag.getX(), -drag.getY());

		}
		// ���·ɴ���λ�ã�������Ҫʱ��ת
		ship.update();

		int x = (int) ship.getX();
		int y = (int) ship.getY();

		int h = (int) ship.getHeight();
		int w = (int) ship.getWidth();

		if (x > pnl.getSize().width) {

			ship.setX(-w + 1);

		} else if (x < -w) {

			ship.setX(pnl.getSize().width - 1);

		}
		if (y > pnl.getSize().height) {

			ship.setY(-h + 1);

		} else if (y < -h) {

			ship.setY(pnl.getSize().height - 1);

		}

	
	}

	@Override
	public void paint(Graphics2D g2d) {
		// TODO Auto-generated method stub
		ship.paint(g2d);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	// ���Ӽ������¼���������Ҫʱ�Էɴ����м��ٺ���ת
	
	public void keyPressed(KeyEvent e) {
		if (ship == null)
			return;
		switch (e.getKeyCode()) {
		
		// ���� ǰ������Էɴ����ٻ��߼���
		case KeyEvent.VK_UP:{
			
			accel.setX(Math.cos(ship.getRot()));
			accel.setY(Math.sin(ship.getRot()));
			accel.normalize();
		
		}
			break;
		//��ת�ɴ�����������ǰ���ٶ�
		case KeyEvent.VK_DOWN:
			
			accel.setX(-Math.cos(ship.getRot()));
			accel.setY(-Math.sin(ship.getRot()));
			accel.normalize();
			
			break;
		// 
		case KeyEvent.VK_LEFT:
			ship.rotate(-5 * ONE_RADIAN);
			
			if (!ship.getVel().equals(Vector2D.ZERO_VECTOR)) {
				accel.setX(Math.cos(ship.getRot()));
				accel.setY(Math.sin(ship.getRot()));
				accel.normalize();
			}
			
			break;
		case KeyEvent.VK_RIGHT:
			ship.rotate(+5 * ONE_RADIAN);
			
			if (!ship.getVel().equals(Vector2D.ZERO_VECTOR)) {
				accel.setX(Math.cos(ship.getRot()));
				accel.setY(Math.sin(ship.getRot()));
				accel.normalize();
			}
			
			break;
		default:
			break;
		}
		// show the ship's stats on the status window
		System.out.println(ship.getPos() + " "
				+ (int) Math.toDegrees(ship.getRot()) + " " + ship.getVel());

	}

	public void keyReleased(KeyEvent e) {
		if (ship == null)
			return;
		switch (e.getKeyCode()) {
		//ֻҪ����/���¼�ͷ���ɿ���ֹͣ���� 
		case KeyEvent.VK_UP:
		case KeyEvent.VK_DOWN:
			accel.setX(0.0);
			accel.setY(0.0);
			break;
		default:
			break;
		}
	}
}
