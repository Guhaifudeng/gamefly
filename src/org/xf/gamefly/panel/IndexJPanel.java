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
		// FontUtil.InitGlobalFont(new Font("�����п�", Font.PLAIN, 12));
		// UIManager.put("TextField.font", new Font("����", Font.PLAIN, 12));
		// this.setBackground(Color.cyan);
		this.setLayout(null);
		
		btnVisit = new FiveAngleStarJButton("�ο�");
		btnVisit.setForeground(Color.BLACK);
		btnVisit.setBounds(40, 20, 70, 70);
		btnVisit.setFont(new Font("�����п�", Font.ITALIC, 12));
		btnVisit.addActionListener(myActionListener);
		btnVisit.setFocusPainted(false);
		this.add(btnVisit);
		
		
		btnLogin = new FiveAngleStarJButton("��¼");
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setBounds(278, 72, 120, 120);
		btnLogin.addActionListener(myActionListener);
		btnLogin.setFocusPainted(false);
		this.add(btnLogin);

		btnExit = new FiveAngleStarJButton("�˳�");
		btnExit.setForeground(Color.BLACK);
		btnExit.setBounds(395, 20, 60, 60);
		btnExit.setFont(new Font("�����п�", Font.ITALIC, 13));
		btnExit.setFocusPainted(false);
		btnExit.addActionListener(myActionListener);
		this.add(btnExit);

		btnReg = new FiveAngleStarJButton("ע��");
		btnReg.setForeground(Color.BLACK);
		btnReg.setBounds(160, 52, 80, 80);
		btnReg.setFont(new Font("�����п�", Font.PLAIN, 14));
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
		TitledBorder border = BorderFactory.createTitledBorder("ϵͳ����");
		border.setTitleColor(new Color(221,228,251));
		pnlTop.setBorder(border);
		lblTitle = new JLabel();
		pnlTop.setLayout(new BorderLayout());
		lblTitle.setText("�ɻ���սV1.2�����");
		lblTitle.setForeground(new Color(221,228,251));
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
		lblTitle.setFont(new Font("����", Font.PLAIN, 18));
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
				+ "������ǧ������ҺƵ��л���<br>"
				+ "�������ϣ���ָ��ɽ����<br>"
				+ "��Ҷ�����£���Ҷ����ѩ��<br>"
				+ "�ں�֮�����������Ӷ����Ź���<br>"
				+ "���ϰ뵺�ùţ���������ң���������档<br>"
				+ "����ָ���������Ӷݣ�<br>"
				+ "�ҷ��л������ߡ���Զ����"
				+"</body></html>\n");
		lblText.setHorizontalAlignment(JLabel.CENTER);
		lblText.setFont(new Font("���ķ���", Font.PLAIN, 15));
		lblText.setSize(70, 100);
		lblText.setForeground(new Color(221,228,251));

		pnlBottom.add(lblText, BorderLayout.CENTER);
		TitledBorder border = BorderFactory.createTitledBorder("���±���");
		border.setTitleColor(new Color(221,228,251));
		pnlBottom.setBorder(border);
		return pnlBottom;
	}

	private JPanel getPnlRight() {

		pnlRight = new JPanel(new BorderLayout());
		TitledBorder border = BorderFactory.createTitledBorder("��������Ϣ");
		border.setTitleColor(new Color(221,228,251));
		pnlRight.setBorder(border);
		lblText1 = new JLabel();
		lblText1.setText("<html><HTML><body>"
				+ "л��<br>"
				+ "�ల��<br>"
				+ "������<br>"
				+ "����<br>"
				+ "������<br>"
				+ "������<br>"
				+"</body></html>\n");
		
		lblText1.setVerticalAlignment(JLabel.NORTH);
		lblText1.setHorizontalAlignment(JLabel.CENTER);
		lblText1.setFont(new Font("���ķ���", Font.PLAIN, 15));
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
