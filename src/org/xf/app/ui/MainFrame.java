package org.xf.app.ui;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.xf.app.actor2D.ImageLoader;
import org.xf.app.componentstest.Button2DTest;
import org.xf.app.componentstest.Label2DTest;
import org.xf.app.componentstest.RadioButton2DTest;
import org.xf.app.containterstest.Menu2DTest;
import org.xf.app.containterstest.Panel2DTest;
import org.xf.app.robot.ActorTest;
import org.xf.app.robot.RobotAdapter;
import org.xf.app.scene.full.FullscreenTest;
import org.xf.app.scene.iso.IsoTest;
import org.xf.app.scene.space.SceneScrollTest;
import org.xf.app.scene.wrap.WrapTest;

public class MainFrame extends JFrame{
	public static void main(String[] args){
		new MainFrame().setVisible(true);;
	}
	
	public MainFrame(){
		setBounds(20,100,500,500);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
//		setFocusable(true); 很重要，一定要分清楚
//		testIsoScene();
//		testWrapScene();
		testLabel2DTest();
//		testFullScreen();
	}

	/*********矢量*********/
	private void testVector2D(){
		setTitle("Vector2DTest");
		Vector2DTestPanel test = new Vector2DTestPanel();
		test.start();
		add(test);
	}
	/*********图像载入器*********/
	private void testImageLoader(){
		setTitle("ImageLoaderTest");
		MyJPanel pnl = new MyJPanel();
		pnl.setBounds(0,0,400,400);
		ImageLoader imageLoader = new ImageLoader(pnl,"images/myplan_1.png",true);
		image = imageLoader.getImage();
		image = imageLoader.extractCellScaled(0, 0, 150, 110,50,30);
		image = imageLoader.extractCellScaled(0, 0, 100, 100,50,50);
		add(pnl);
	}
	Image image;
	class MyJPanel extends JPanel{
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawImage(image, 0, 0, image.getWidth(this),image.getHeight(this),this);
			//g.drawImage();
		}
	}
	/*********实体测试*********/
	private void testRobot(){
		setTitle("ActorTest-Robot");
		ActorTest test = new ActorTest();
		test.start();
		add(test);
	}
	/*********场景管理*********/
	private void testFullScreen(){
		FullscreenTest test = new FullscreenTest(this);		
		add(test);
		
	}
	private void testSceneScroll(){
		setTitle("SceneScroll-Robot");
		SceneScrollTest test = new SceneScrollTest();
		test.start();
//		Container contentPane = getContentPane();
//		contentPane.add(test);
//		setPreferredSize(test.getPreferredSize());
//		// 执行并构建窗体设定
//		setLayout(New BorderLayout());
//		pack();
//		
		
//		pane.setPreferredSize(test.getPreferredSize());
		add(test);
	}
	private void testIsoScene(){
		setTitle("IsoTest-IsoScene");
		IsoTest test = new IsoTest();
		test.start();
		add(test);
	}
	private void testWrapScene(){
		setTitle("WrapTest-WrapScene");
		WrapTest test = new WrapTest();
		test.start();
		add(test);
	}
	/*********组件*********/
	private void testLabel2DTest(){
		setTitle("Label2DTest-Label2D");
		Label2DTest test = new Label2DTest();
		test.start();
		add(test);
	}
	private void testButton2DTest(){
		setTitle("Button2DTest-Label2D");
		Button2DTest test = new Button2DTest();
		test.start();
		add(test);
	}
	private void testRadioButton2DTest(){
		setTitle("RadioButton2DTest-Label2D");
		RadioButton2DTest test = new RadioButton2DTest();
		test.start();
		add(test);
	}
	/*********容器*********/
	private void testPanel2DTest(){
		setTitle("Panel2DTest-Panel2D");
		Panel2DTest test = new Panel2DTest();
		test.start();
		add(test);
	}
	
	private void testMenu2DTest(){
		setTitle("Menu2DTest-Menu2D");
		Menu2DTest test = new Menu2DTest();
		test.start();
		add(test);
	}
	
}
