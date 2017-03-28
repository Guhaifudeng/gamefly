package org.xf.app.robot;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//����Robot�������������
public class RobotAdapter implements KeyListener {
	private Robot robot;

	public RobotAdapter(Robot r) {
		robot = r;
		//{System.out.print("33");}
	}

	// �û����˿�������ƶ�
	// keyPressed(KeyEvent e)
	public void keyPressed(KeyEvent e) {
		robot.resetState(Robot.SHOOTING);
		switch (e.getKeyCode()) {
			case KeyEvent.VK_SPACE: 
				robot.startShooting();
	
			
				break;
			case KeyEvent.VK_UP: 
				
				robot.move(Robot.DIR_NORTH);
	
			
	
				break;
			case KeyEvent.VK_DOWN:
				robot.move(Robot.DIR_SOUTH);
				break;
			case KeyEvent.VK_LEFT:
				robot.move(Robot.DIR_WEST);
				break;
			case KeyEvent.VK_RIGHT:
				robot.move(Robot.DIR_EAST);
				break;
			default:
				break;
		}
	}

	// ����ո���ɿ������û�����ֹͣ���
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			robot.stopShooting();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}// RobotAdapter
