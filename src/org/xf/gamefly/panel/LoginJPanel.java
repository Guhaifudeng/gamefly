package org.xf.gamefly.panel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.xf.gamefly.bean.Boy;
import org.xf.gamefly.bean.Girl;
import org.xf.gamefly.contorl.Separator;
import org.xf.gamefly.frame.HomeJFrame;
import org.xf.gamefly.frame.IndexJFrame;
import org.xf.gamefly.pattern.SignInStrategy;



public class LoginJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Separator sep = new Separator();
	JLabel titleLbl = new JLabel("'飞机大战V1.2'游戏入口");
	JLabel nameLbl = new JLabel("用户名:");
	JLabel passwordLbl = new JLabel("密码:");
	JLabel rankLbl = new JLabel("身份确认:");
	// JLabel phone = new JLabel(":");
	JLabel timeLbl = new JLabel("系统时间:");
	JLabel stateLbl = new JLabel("用户状态:");

	JTextField nameTxt = new JTextField(25);
	JTextField passwordTxt = new JTextField(25);
	JTextField timeTxt = new JTextField(15);
	JTextField stateTxt = new JTextField(2);

	JRadioButton[] rbtRank = new JRadioButton[3];
	ButtonGroup bgRank = new ButtonGroup();
	// Choice cbxRank = new Choice();
	JButton confirmBtn = new JButton("确认");
	JButton cancelBtn = new JButton("取消");
	private boolean[] flag;
	MyActionListener actionListener;
	private JFrame loginJFrame;
	private SignInStrategy signInStrategy;
	private Boy boy;
	private Girl girl;
	public LoginJPanel(JFrame loginJFrame) {
		initData(loginJFrame);
		
		init();

	}

	private void init() {
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();

		setLayout(gbl);
		rbtRank[0] = new JRadioButton("男", true);
		rbtRank[1] = new JRadioButton("女");
		rbtRank[0].setForeground(new Color(221,228,251));
		rbtRank[1].setForeground(new Color(221,228,251));
		rbtRank[0].setBackground(Color.WHITE);
		rbtRank[1].setBackground(Color.WHITE);
		rbtRank[0].setOpaque(false);
		rbtRank[1].setOpaque(false);
		
		// rbtRank[0].setBorder(BorderFactory.createTitledBorder(""));;
		bgRank.add(rbtRank[0]);
		bgRank.add(rbtRank[1]);
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		titleLbl.setOpaque(false);
		titleLbl.setForeground(new Color(221,228,251));
		add(titleLbl, gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0, 0, 10, 0);
		add(sep, gbc);

		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0, 0, 0, 0);
		nameLbl.setForeground(new Color(221,228,251));
		add(nameLbl, gbc);

		gbc.gridwidth = GridBagConstraints.REMAINDER;
		nameTxt.setBackground(Color.WHITE);
		add(nameTxt, gbc);

		gbc.gridwidth = 1;
		passwordLbl.setForeground(new Color(221,228,251));
		add(passwordLbl, gbc);

		gbc.gridwidth = GridBagConstraints.REMAINDER;
		passwordTxt.setBackground(Color.WHITE);
		add(passwordTxt, gbc);
		
		gbc.gridwidth = 1;
		timeLbl.setForeground(new Color(221,228,251));
		add(timeLbl, gbc);
		timeTxt.setEnabled(false);
		timeTxt.setBackground(Color.WHITE);
		add(timeTxt, gbc);
		stateLbl.setForeground(new Color(221,228,251));
		add(stateLbl, gbc);

		gbc.gridwidth = GridBagConstraints.REMAINDER;
		stateTxt.setBackground(Color.WHITE);
		add(stateTxt, gbc);
		stateTxt.setEnabled(false);

		gbc.gridwidth = 1;
		rankLbl.setForeground(new Color(221,228,251));
		add(rankLbl, gbc);

		gbc.insets = new Insets(5, 0, 5, 0);

		// gbc.gridwidth = 1;
		add(rbtRank[0], gbc);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.NONE;
		add(rbtRank[1], gbc);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.NONE;

		JButtonPanel buttonPanel = new JButtonPanel();
		buttonPanel.setOpaque(false);
		confirmBtn.addActionListener(actionListener);
		confirmBtn.setOpaque(false);
		cancelBtn.addActionListener(actionListener);
		cancelBtn.setOpaque(false);
		
		buttonPanel.add(confirmBtn);
		buttonPanel.add(cancelBtn);

		gbc.anchor = GridBagConstraints.SOUTH;
		gbc.insets = new Insets(5, 0, 0, 0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = 4;
		
		add(buttonPanel, gbc);
	}

	private void initData(JFrame loginJFrame) {
		
		boy = new Boy();
		girl = new Girl();
	
		
		signInStrategy = new SignInStrategy();
		this.loginJFrame = loginJFrame;
		flag = new boolean[2];
		flag[0] = true;
		flag[1] = true;
		actionListener = new MyActionListener();
	}
	
	protected boolean analysisInput() {
		if (nameTxt.getText().length() == 0) {
			flag[0] = false;
			return false;
		}
		if (passwordTxt.getText().length() == 0) {
			flag[1] = false;
			return false;

		}
		return true;
	}

	private boolean checkNamePassword(Boy boy) {

		int flagId = signInStrategy.checkID(nameTxt.getText(),
				passwordTxt.getText());
		if (flagId == 0) {
			//System.out.println("1-"+boy.getBoy().getName());
			
			this.loginJFrame.dispose();// 男生窗口
			new HomeJFrame(boy.getBoy()).setVisible(true);
		} else if (flagId == -1) {
			JOptionPane.showMessageDialog(null, "用户名不存在", "note",
					JOptionPane.ERROR_MESSAGE);
		} else if (flagId == 1) {
			JOptionPane.showMessageDialog(null,"密码错误", "note",
					JOptionPane.ERROR_MESSAGE);
		}
		return true;
	}
	
	private boolean checkNamePassword(Girl girl) {

		int flagId = signInStrategy.checkID(nameTxt.getText(),
				passwordTxt.getText());
		if (flagId == 0) {
			this.loginJFrame.dispose();// 女生窗口
			new HomeJFrame(girl.getGirl()).setVisible(true);
		} else if (flagId == -1) {
			JOptionPane.showMessageDialog(null, "用户名不存在", "note",
					JOptionPane.ERROR_MESSAGE);
		} else if (flagId == 1) {
			JOptionPane.showMessageDialog(null,"密码错误", "note",
					JOptionPane.ERROR_MESSAGE);
		}
		return true;
	}

	class MyActionListener implements ActionListener {


		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == confirmBtn) {

				if (analysisInput()) {
					
						if (rbtRank[0].isSelected()) {

							signInStrategy.setSignInModel(boy);
							checkNamePassword(boy);
							
						} else if (rbtRank[1].isSelected()) {
							signInStrategy.setSignInModel(girl);
							checkNamePassword(girl);
						} 
				
					
					
				}

			} else if (e.getSource() == cancelBtn) {
				loginJFrame.dispose();
				new IndexJFrame().setVisible(true);
				//
				
			}
		}

	}
}