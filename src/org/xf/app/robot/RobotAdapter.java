package org.xf.app.robot;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//控制Robot对象的适配器类
public class RobotAdapter implements KeyListener {
	private Robot robot;

	public RobotAdapter(Robot r) {
		robot = r;
		//{System.out.print("33");}
	}

	// 让机器人开火或者移动
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

	// 如果空格键松开，则让机器人停止射击
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
