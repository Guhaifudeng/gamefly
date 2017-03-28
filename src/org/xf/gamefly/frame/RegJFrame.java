package org.xf.gamefly.frame;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import org.xf.gamefly.graphics.BackgroundImageGroup;
import org.xf.gamefly.panel.RegJPanel;
import org.xf.gamefly.util.FontUtil;
import org.xf.gamefly.util.SkinUtil;




public class RegJFrame extends JFrame {
	public RegJFrame() {
		this.init();
		
		/*Font font = new Font("华文行楷", Font.PLAIN, 16);
		UIManager.put("JButton.font",font);
		UIManager.put("JLabel.font",font);
		UIManager.put("JRadioButton.font",font);
		UIManager.put("JCheckBox.font",font);
		UIManager.put("JList.font",font);
		UIManager.put("JMenu.font",font);
		UIManager.put("JMenuItem.font",font);
		UIManager.put("JComboBox.font",font);
		UIManager.put("JTabbedPane.font",font); 
		*/
	} 

	public void init() {
		RegJPanel pnl = new RegJPanel(this);
		MyJPanel pl = new MyJPanel();
		pnl.setOpaque(false);
		pl.add(pnl);
		this.setLocation(300, 100);
		this.setSize(500, 500);
		this.setLayout(null);
		this.setVisible(true);
		this.setTitle("信息注册");
		this.setBackground(Color.BLUE);
		setTitle("飞机大战V1.2-注册窗口");
		this.add(pl);
		JLabel title = new JLabel("欢迎各界人士入住飞战联盟");
		title.setBounds(120,30,300,40);
		title.setFont(new Font("方正姚体",Font.PLAIN,20));
		title.setForeground(Color.BLACK);
		this.add(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		



		/*this.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				// 获得当前事件发生时鼠标所在的X和Y坐标
				int x = e.getX();
				int y = e.getY();
				//System.out.println(x+" "+y);
				
			}
		
		});*/
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int rst = JOptionPane.showConfirmDialog(RegJFrame.this,
						"Are you sure?", "Confirm",
						JOptionPane.OK_CANCEL_OPTION);
				if (rst == JOptionPane.OK_OPTION) {
					//GegFrm.this.dispose();//内部类调用外部方法
					//System.exit(0);
					RegJFrame.this.dispose();
				}
			}
		});
		// 如果想要有确认对话框 JFrame.DO_NOTHING_ON_CLOSE
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
	}
	class MyJPanel  extends JPanel{
		public MyJPanel(){
			this.setBounds(0, 0, 500, 500);
			this.setLayout(null);
		}
		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			Image image = IndexJFrame.bgImage.getImage(BackgroundImageGroup.REG_IMAGE);
			g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
}
