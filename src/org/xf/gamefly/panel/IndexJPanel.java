package org.xf.gamefly.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;






import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.xf.gamefly.contorl.FiveAngleStarJButton;
import org.xf.gamefly.frame.HomeJFrame;
import org.xf.gamefly.frame.IndexJFrame;
import org.xf.gamefly.frame.LoginJFrame;
import org.xf.gamefly.frame.RegJFrame;
import org.xf.gamefly.graphics.BackgroundImageGroup;



public class IndexJPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  IndexJFrame indexJFrame;
	private JPanel pnlRight;
	private JPanel pnlBottom;
	private JPanel pnlTop;
	private FiveAngleStarJButton btnLogin;
	private FiveAngleStarJButton btnExit;
	private FiveAngleStarJButton btnReg;
	private JLabel lblTitle;
	private JLabel lblText;
	private ActionListener myActionListener;
	private FiveAngleStarJButton btnVisit;
	private JLabel lblText1;

	public IndexJPanel(IndexJFrame indexJFrame) {
		this.indexJFrame =indexJFrame;
	
		myActionListener = new MyActionListener();
		//ColorUtil.InitGlobalColor(new Color(221,228,251));
		init();
	}

	private void init() {
		// FontUtil.InitGlobalFont(new Font("华文行楷", Font.PLAIN, 12));
		// UIManager.put("TextField.font", new Font("楷体", Font.PLAIN, 12));
		// this.setBackground(Color.cyan);
		this.setLayout(null);
		
		btnVisit = new FiveAngleStarJButton("游客");
		btnVisit.setForeground(Color.BLACK);
		btnVisit.setBounds(40, 20, 70, 70);
		btnVisit.setFont(new Font("华文行楷", Font.ITALIC, 12));
		btnVisit.addActionListener(myActionListener);
		btnVisit.setFocusPainted(false);
		this.add(btnVisit);
		
		
		btnLogin = new FiveAngleStarJButton("登录");
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setBounds(278, 72, 120, 120);
		btnLogin.addActionListener(myActionListener);
		btnLogin.setFocusPainted(false);
		this.add(btnLogin);

		btnExit = new FiveAngleStarJButton("退出");
		btnExit.setForeground(Color.BLACK);
		btnExit.setBounds(395, 20, 60, 60);
		btnExit.setFont(new Font("华文行楷", Font.ITALIC, 13));
		btnExit.setFocusPainted(false);
		btnExit.addActionListener(myActionListener);
		this.add(btnExit);

		btnReg = new FiveAngleStarJButton("注册");
		btnReg.setForeground(Color.BLACK);
		btnReg.setBounds(160, 52, 80, 80);
		btnReg.setFont(new Font("华文行楷", Font.PLAIN, 14));
		btnReg.setFocusPainted(false);
		btnReg.addActionListener(myActionListener);
		this.add(btnReg);

		pnlTop = this.getPnlTop();
		pnlTop.setBounds(37, 104, 299, 85);
		pnlTop.setOpaque(false);
		this.add(pnlTop);

		pnlBottom = this.getPnlBottom();
		pnlBottom.setBounds(37, 217, 299, 203);
		pnlBottom.setOpaque(false);
		this.add(pnlBottom);
		pnlRight = this.getPnlRight();
		pnlRight.setBounds(370, 104, 103, 314);
		pnlRight.setOpaque(false);
		this.add(pnlRight);

		this.setBorder(new EmptyBorder(5, 5, 5, 5));

	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Image image = IndexJFrame.bgImage.getImage(BackgroundImageGroup.INDEX_IMAGE);
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}
	private JPanel getPnlTop() {
		pnlTop = new JPanel();
		TitledBorder border = BorderFactory.createTitledBorder("系统名称");
		border.setTitleColor(new Color(221,228,251));
		pnlTop.setBorder(border);
		lblTitle = new JLabel();
		pnlTop.setLayout(new BorderLayout());
		lblTitle.setText("飞机大战V1.2精简版");
		lblTitle.setForeground(new Color(221,228,251));
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
		lblTitle.setFont(new Font("楷体", Font.PLAIN, 18));
		lblTitle.setSize(70, 100);
		lblTitle.setBackground(Color.BLUE);

		pnlTop.add(lblTitle, BorderLayout.CENTER);
		return pnlTop;
	}

	private JPanel getPnlBottom() {
		pnlBottom = new JPanel();
		pnlBottom.setLayout(new BorderLayout());
		lblText = new JLabel();
		lblText.setText("<html><HTML><body>"
				+ "借我三千虎骑，复我浩荡中华；<br>"
				+ "饮马恒河畔，剑指天山西；<br>"
				+ "碎叶城揽月，库叶岛赏雪；<br>"
				+ "黑海之滨垂钓，贝加尔湖张弓；<br>"
				+ "中南半岛访古，东京废墟遥祭华夏列祖。<br>"
				+ "汉旗指处，望尘逃遁，<br>"
				+ "敢犯中华天威者、虽远必诛"
				+"</body></html>\n");
		lblText.setHorizontalAlignment(JLabel.CENTER);
		lblText.setFont(new Font("华文仿宋", Font.PLAIN, 15));
		lblText.setSize(70, 100);
		lblText.setForeground(new Color(221,228,251));

		pnlBottom.add(lblText, BorderLayout.CENTER);
		TitledBorder border = BorderFactory.createTitledBorder("故事背景");
		border.setTitleColor(new Color(221,228,251));
		pnlBottom.setBorder(border);
		return pnlBottom;
	}

	private JPanel getPnlRight() {

		pnlRight = new JPanel(new BorderLayout());
		TitledBorder border = BorderFactory.createTitledBorder("开发者信息");
		border.setTitleColor(new Color(221,228,251));
		pnlRight.setBorder(border);
		lblText1 = new JLabel();
		lblText1.setText("<html><HTML><body>"
				+ "谢峰<br>"
				+ "余安琪<br>"
				+ "刘震震<br>"
				+ "余茜<br>"
				+ "刘康迪<br>"
				+ "段紫阳<br>"
				+"</body></html>\n");
		
		lblText1.setVerticalAlignment(JLabel.NORTH);
		lblText1.setHorizontalAlignment(JLabel.CENTER);
		lblText1.setFont(new Font("华文仿宋", Font.PLAIN, 15));
		lblText1.setSize(70, 100);
		lblText1.setForeground(new Color(221,228,251));
		pnlRight.add(lblText1, BorderLayout.CENTER);
		return pnlRight;
	}
	class MyActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == btnLogin){
				new LoginJFrame().setVisible(true);
			 	//System.out.print("1");
				IndexJPanel.this.indexJFrame.dispose();
			}else if(e.getSource() == btnReg){
				new RegJFrame().setVisible(true);
				//System.out.print("2");
				IndexJPanel.this.indexJFrame.dispose();
			}else if(e.getSource() == btnExit){
				System.exit(0);
				//System.out.print("3");
			}else if(e.getSource()==btnVisit){
				IndexJPanel.this.indexJFrame.dispose();
				 new HomeJFrame().setVisible(true);
			}
		}
		
	}
	class MyWindowListener implements WindowListener{
		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
		
			
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

	}
}
