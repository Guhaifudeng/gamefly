package org.xf.gamefly.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import org.xf.gamefly.bean.User;
import org.xf.gamefly.frame.IndexJFrame;
import org.xf.gamefly.pattern.NoUser;
import org.xf.gamefly.pattern.RegStrategy;
import org.xf.gamefly.pattern.UserSimpleFactory;
import org.xf.gamefly.util.FontUtil;


public class RegJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mgPnl;
	private JPanel notePnl;
	private JLabel[] pvtLbl, noteLbl;
	private JTextField[] mgTxt;

	private JButton sendBtn;
	private JPanel sendPnl;
	private String[] inData, rightNote, outData, inNote, errorNote, finalNote;
	private MyActionListener myActionListener;
	private User user;
	private boolean[] dFlag;
	private Color[] colorNote;
	private JRadioButton boyRbt;
	private JRadioButton girlRbt;
	private ButtonGroup bg;
	private RegStrategy regStrategy;
	private JFrame regJFrame;

	public RegJPanel(JFrame regJFrame) {
		this.regJFrame = regJFrame;
		initNote();
		init();

	}

	public void init() {
		myActionListener = new MyActionListener();
		regStrategy = new RegStrategy();
		this.setSize(360, 270);
		this.setLocation(80, 80);
		// this.setBackground(Color.);
		this.setLayout(new BorderLayout());
		mgPnl = this.setMgPnl();
		mgPnl.setOpaque(false);
		notePnl = this.setNotePnl();
		notePnl.setOpaque(false);
		this.add(mgPnl, BorderLayout.CENTER);
		this.add(notePnl, BorderLayout.EAST);
		sendPnl = this.setSendPnl();
		sendPnl.setOpaque(false);
		this.add(sendPnl, BorderLayout.SOUTH);
		TitledBorder border = BorderFactory.createTitledBorder("�û�ע�ᴰ��");
		border.setTitleColor(new Color(221,228,251));
		this.setBorder(border);
		FontUtil.InitGlobalFont(new Font("�����п�", Font.PLAIN, 16));
		UIManager.put("TextField.font", new Font("����", Font.PLAIN, 12));

		// this.add(new JLabel("д����"));
	}
	
	private void initNote() {

		dFlag = new boolean[] { false, false, false, false, false, false };
		colorNote = new Color[] { Color.GREEN, Color.GREEN, Color.GREEN,
				Color.GREEN, Color.GREEN, Color.GREEN };
		inData = new String[6];
		inData[0] = "";
		inData[1] = "";
		inData[2] = "";
		inData[3] = "";
		inData[4] = "";
		inData[5] = "";

		outData = new String[6];
		outData[0] = "";
		outData[1] = "";
		outData[2] = "";
		outData[3] = "";
		outData[4] = "";
		outData[5] = "";
		rightNote = new String[6];
		rightNote[0] = "��";
		rightNote[1] = "��";
		rightNote[2] = "��";
		rightNote[3] = "��";
		rightNote[4] = "��";
		rightNote[5] = "��";

		errorNote = new String[6];
		errorNote[0] = "�û�ID(���֡���ĸ)������6-12";
		errorNote[1] = "�ǳ�(���֡���ĸ������)";
		errorNote[2] = "���볤��6-12";
		errorNote[3] = "�����������벻һ��";
		errorNote[4] = "email��ʽ�Ƿ�";
		errorNote[5] = "�绰��ʽ����ȷ";

		inNote = new String[6];
		inNote[0] = "*";
		inNote[1] = "*";
		inNote[2] = "*";
		inNote[3] = "*";
		inNote[4] = "*";
		inNote[5] = "*";

		finalNote = new String[6];
		finalNote[0] = "*";
		finalNote[1] = "*";
		finalNote[2] = "*";
		finalNote[3] = "*";
		finalNote[4] = "*";
		finalNote[5] = "*";

	}

	// send ���
	private JPanel setSendPnl() {
		sendPnl = new JPanel();
		sendBtn = new JButton("ȷ��");
		boyRbt = new JRadioButton("��", true);
		girlRbt = new JRadioButton("Ů");

		bg = new ButtonGroup();
		boyRbt.setOpaque(false);
		boyRbt.setForeground(new Color(221,228,251));
		girlRbt.setOpaque(false);
		girlRbt.setForeground(new Color(221,228,251));
		bg.add(boyRbt);
		bg.add(girlRbt);
		sendBtn.setOpaque(false);
		sendBtn.setForeground(new Color(221,228,251));
		sendBtn.addActionListener(myActionListener);
		// sendBtn.addWindowListener(myWindowListener);
		// sendPnl.setSize(300, 50);
		// sendPnl.setLocation(80, 200+80);
		sendPnl.setLayout(new FlowLayout());
		sendPnl.add(boyRbt);
		sendPnl.add(girlRbt);
		sendPnl.add(sendBtn);
		sendPnl.setBorder(BorderFactory.createTitledBorder(""));

		// sendBtn.addMouseListener(new MyMouseListener());
		// sendBtn.addMouseMotionListener(new MyMouseMotionListener());

		return sendPnl;
	}

	// message ���
	private JPanel setMgPnl() {
		mgPnl = new JPanel();
		// mgPnl.setSize(400, 200);
		// mgPnl.setLocation(80, 80);
		// mgPnl.setBackground(Color.BLACK);
		GridLayout glt = new GridLayout(6, 2, 10, 10);
		mgPnl.setLayout(glt);
		this.addToMgPml(mgPnl);
		return mgPnl;
	}

	// ��ʾ���
	private JPanel setNotePnl() {
		// note
		notePnl = new JPanel();
		noteLbl = new JLabel[6];
		Dimension sz1 = new Dimension(200, 22);// ���óߴ�

		for (int i = 0; i < 6; i++) {
			noteLbl[i] = new JLabel(inNote[i]);
			noteLbl[i].setForeground(colorNote[i]);
			noteLbl[i].setHorizontalAlignment(JLabel.LEFT);
			noteLbl[i].setPreferredSize(sz1);
		}
		GridLayout glt = new GridLayout(6, 1, 2, 2);
		notePnl.setLayout(glt);
		for (int i = 0; i < 6; i++) {
			notePnl.add(noteLbl[i]);
		}
		return notePnl;
	}

	// �������ӿؼ�
	private void addToMgPml(JPanel mgPnl) {

		pvtLbl = new JLabel[] { new JLabel("�û�ID:"), new JLabel("�ǳ�:"),
				new JLabel("����:"), new JLabel("ȷ������:"), new JLabel("email:"),
				new JLabel("�绰:"),

		};
		Dimension sz1 = new Dimension(100, 22);// ���óߴ�
		// property
		for (int i = 0; i < 6; i++) {
			pvtLbl[i].setForeground(new Color(221,228,251));
			pvtLbl[i].setHorizontalAlignment(JLabel.RIGHT);

		}
		// input
		mgTxt = new JTextField[6];
		mgTxt[0] = new JTextField("", 40);
		mgTxt[0].setHorizontalAlignment(JTextField.RIGHT);
		mgTxt[1] = new JTextField("", 40);
		mgTxt[1].setHorizontalAlignment(JTextField.RIGHT);
		mgTxt[2] = new JPasswordField("", 40);
		mgTxt[2].setHorizontalAlignment(JPasswordField.RIGHT);
		mgTxt[3] = new JPasswordField("", 40);
		mgTxt[3].setHorizontalAlignment(JPasswordField.RIGHT);
		mgTxt[4] = new JTextField("", 40);
		mgTxt[4].setHorizontalAlignment(JTextField.RIGHT);
		mgTxt[5] = new JTextField("", 40);
		mgTxt[5].setHorizontalAlignment(JTextField.RIGHT);

		for (int i = 0; i < 6; i++) {
			mgTxt[i].setPreferredSize(sz1);
			mgTxt[i].setBackground(Color.WHITE);
			mgTxt[i].setForeground(Color.BLACK);
			pvtLbl[i].setPreferredSize(sz1);
			mgPnl.add(pvtLbl[i]);
			mgPnl.add(mgTxt[i]);
		}

	}

	// ���bean Stu +bean Tech(�򵥹���ģʽ)
	private void checkUserIndata() {
		dFlag[0] = user.setID(mgTxt[0].getText());
		dFlag[1] = user.setName(mgTxt[1].getText());
		dFlag[2] = user.setPassword(mgTxt[2].getText());
		dFlag[3] = user.setRepass(mgTxt[3].getText());
		dFlag[4] = user.setEmail(mgTxt[4].getText());
		dFlag[5] = user.setPhone(mgTxt[5].getText());
	}

	// �趨��������
	private void setDataOut() {
		for (int i = 0; i < 6; i++) {
			if (dFlag[i]) {
				finalNote[i] = rightNote[i];
				outData[i] = mgTxt[i].getText();
				colorNote[i] = Color.GREEN;
			} else {
				finalNote[i] = errorNote[i];
				outData[i] = inData[i];
				colorNote[i] = Color.RED;
			}
		}
	}

	// ��Ϣ����
	private void setNoteInputView() {
		for (int i = 0; i < 6; i++) {
			mgTxt[i].setText(outData[i]);
			noteLbl[i].setText(finalNote[i]);
			noteLbl[i].setForeground(colorNote[i]);
		}
	}

	// ��Ϣ�ж��Ƿ�Ϊ��
	private boolean analysisInput() {
		boolean FLag = true;
		if (mgTxt[0].getText().length() == 0) {
			// dFlag[0]= false;
			finalNote[0] = "δ��д�û�ID";
			colorNote[0] = Color.RED;
			FLag = false;
		}
		outData[0] = mgTxt[0].getText();
		if (mgTxt[1].getText().length() == 0)

		{

			finalNote[1] = "δ��д�û���";
			colorNote[1] = Color.RED;
			FLag = false;

		}
		outData[1] = mgTxt[1].getText();
		if (mgTxt[2].getText().length() == 0) {
			// dFlag[0]= false;
			finalNote[2] = "����������";
			colorNote[2] = Color.RED;

			FLag = false;
		}
		outData[2] = mgTxt[2].getText();
		if (mgTxt[3].getText().length() == 0) {
			// dFlag[0]= false;
			finalNote[3] = "ȷ������ȱʧ";
			colorNote[3] = Color.RED;

			FLag = false;
		}
		outData[3] = mgTxt[3].getText();
		if (mgTxt[4].getText().length() == 0) {
			// dFlag[0]= false;
			finalNote[4] = "������email��ַ";
			colorNote[4] = Color.RED;

			FLag = false;
		}
		outData[4] = mgTxt[4].getText();
		if (mgTxt[5].getText().length() == 0) {
			// dFlag[0]= false;
			finalNote[5] = "�������ֻ���";
			colorNote[5] = Color.RED;

			FLag = false;
		}
		outData[5] = mgTxt[5].getText();
		return FLag;

	}

	class MyMouseListener implements MouseListener {
		@Override
		public void mouseEntered(MouseEvent e) {
			System.out.println("hello 1");
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("hello 2");
		}

		@Override
		public void mouseExited(MouseEvent e) {
			System.out.println("hello 33");
		}

		@Override
		public void mousePressed(MouseEvent e) {
			System.out.println("hello 4");
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			System.out.println("hello 5");
		}
	}

	class MyMouseMotionListener implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			System.out.println("hello2 5");

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			System.out.println("hello2 6");
		}

	}

	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == sendBtn) {
		

				// System.out.print("sgs");
				int rst = JOptionPane.showConfirmDialog(null, "�Ƿ�ȷ���ύ?",
						"Confirm", JOptionPane.OK_CANCEL_OPTION);
				if (rst == JOptionPane.OK_OPTION) {
					if (analysisInput()) {

						try {
							if (boyRbt.isSelected()) {
								user = UserSimpleFactory.createUser("boy");

							} else if (girlRbt.isSelected()) {
								user = UserSimpleFactory.createUser("girl");
							}
						} catch (NoUser e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						checkUserIndata();
						regStrategy.setRegModel(user);
						if (!regStrategy.checkID(user.getID()))
							JOptionPane.showMessageDialog(null, "�û����Ѵ���",
									"note", JOptionPane.ERROR_MESSAGE);

						else {
							setDataOut();
							setNoteInputView();
							if (dFlag[0] && dFlag[1] && dFlag[2] && dFlag[3]
									&& dFlag[4] && dFlag[5]) {
								
								if (regStrategy.insertData()) {

									int t = JOptionPane.showConfirmDialog(null,
											"ע��ɹ�", "note",
											JOptionPane.OK_CANCEL_OPTION);
									if (t == JOptionPane.OK_OPTION) {
										regJFrame.dispose();
										new IndexJFrame().setVisible(true);
										//new IndexJFrame().setVisible(true);
									} else if (t == JOptionPane.CANCEL_OPTION) {
										initNote();
										setNoteInputView();

									}
								} else {
									JOptionPane.showMessageDialog(null,
											"ϵͳ��æ���Ժ�ע�ᣨ����", "note",
											JOptionPane.ERROR_MESSAGE);
									setNoteInputView();
								}
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "��Ϣδ��д����", "note",
								JOptionPane.ERROR_MESSAGE);
						setNoteInputView();

					}

					initNote();
				}
			}
		}
	}
}