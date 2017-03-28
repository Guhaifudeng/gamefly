package org.xf.gamefly.frame;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.xf.gamefly.graphics.BackgroundImageGroup;
import org.xf.gamefly.panel.IndexJPanel;
import org.xf.gamefly.util.ColorUtil;
import org.xf.gamefly.util.FontUtil;
import org.xf.gamefly.util.SkinUtil;





public class IndexJFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IndexJPanel indexPnl;
	public static BackgroundImageGroup bgImage;
	public IndexJFrame(){
		
		init();
		moveFrame();
	}
	private void init(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 500, 500);
		indexPnl = new IndexJPanel(this);
		add(indexPnl);
		
		bgImage = new BackgroundImageGroup(); 
		bgImage.init(indexPnl);
		
		setUndecorated(true);
		setTitle("飞机大战V1.2-项目主页");
		
		FontUtil.InitGlobalFont(new Font("楷体", Font.PLAIN, 16));
		//ColorUtil.InitGlobalColor(new Color(221,228,251));
		
		UIManager.put("TextField.font", new Font("楷体", Font.PLAIN, 12));
		UIManager.put("Button.font", new Font("华文行楷", Font.PLAIN, 18));
		
		SkinUtil.setSkin("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel",
				this);
		
		//SkinUtil.setSkin("javax.swing.plaf.metal.MetalLookAndFeel",this);
		//this.setVisible(true);
	}
	private boolean isDraging;
	private int xx;
	private int yy;
	public void moveFrame() {
		addMouseListener(new MouseAdapter() {
			

			

			public void mousePressed(MouseEvent e) {
				isDraging = true;
				xx = e.getX();
				yy = e.getY();
			}

			public void mouseReleased(MouseEvent e) {
				isDraging = false;
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (isDraging) {
					int left = IndexJFrame.this.getLocation().x;
					int top = IndexJFrame.this.getLocation().y;
					IndexJFrame.this.setLocation(left + e.getX() - xx, top + e.getY()
							- yy);

					IndexJFrame.this.repaint();
				}
			}
		});
	}
	


	
}
