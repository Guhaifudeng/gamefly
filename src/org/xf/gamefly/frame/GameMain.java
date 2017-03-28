package org.xf.gamefly.frame;



import javax.swing.JFrame;

import org.xf.gamefly.bean.User;
import org.xf.gamefly.panel.GameJPanel;
import org.xf.gamefly.util.SoundPlayer;

public class GameMain extends JFrame {

	private SoundPlayer soundPlayer;
	

	// Ĭ�ϱ�������
	public void initSound() {
		soundPlayer = new SoundPlayer("sound/action_world1.wav");
		soundPlayer.loop();
	}

	public GameMain() {

		setSize(500, 700);
		setLocation(300, 100);
		setTitle("�ɻ���սV1.2");
		// setLayout(null);
		// setLocationRelativeTo(null);
		setUndecorated(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		// setFocusable(true); ����Ҫ��һ��Ҫ�����
		// testIsoScene();
		// testWrapScene();
		// testLabel2DTest();
		// testMenu2DTest();
		gamePnl = new GameJPanel(this);
		gamePnl.start();
		// FullscreenTest test = new FullscreenTest(this);
		add(gamePnl);
		

	}

	GameJPanel gamePnl;

	

	public void stopSound() {
		// TODO Auto-generated method stub
		soundPlayer.stop();
		//soundPlayer =null;
	}
	
	

	
}
