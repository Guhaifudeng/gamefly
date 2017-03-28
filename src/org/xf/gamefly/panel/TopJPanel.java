package org.xf.gamefly.panel;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;




import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.xf.gamefly.bean.User;
import org.xf.gamefly.contorl.RounRectJLabel;
import org.xf.gamefly.frame.IndexJFrame;
import org.xf.gamefly.graphics.BackgroundImageGroup;
import org.xf.gamefly.io.ObjectReaderHero;


public class TopJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User[] emps;
	ArrayList<User> userList;
	public TopJPanel(JFrame indexFrm) {
		setBounds(0, 0, indexFrm.getWidth(), indexFrm.getHeight());
		setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		setBorder(BorderFactory.createEtchedBorder());
		initData();
		init();
	}

	void initData() {
	userList =  ObjectReaderHero.getObjectReaderUser();
	emps = new User[userList.size()];
	userList.toArray(emps);
	}

	void init() {
		RounRectJLabel[] topLbl = new RounRectJLabel[userList.size()];
		//RounRectJLabel[] topLbl = new RounRectJLabel[3];
		for (int i = 0; i < userList.size()&&i<8; i++) {
			if (emps[i] != null) {
				System.out.println(emps[i].getName());
				topLbl[i] = new RounRectJLabel((i + 1) + "  "+ emps[i].getName() +"  "+ emps[i].getHighestScore());
				//topLbl[i].setForeground(Color.RED);
				Dimension preferredSize = new Dimension(150, 30);
				// topLbl[i].setForeground(Color.GREEN);

				topLbl[i].setPreferredSize(preferredSize);
				add(topLbl[i]);
			} else
				break;
		}

	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Image image = IndexJFrame.bgImage.getImage(BackgroundImageGroup.TOP_IMAGE);
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}
