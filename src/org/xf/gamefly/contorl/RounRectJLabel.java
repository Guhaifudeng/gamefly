package org.xf.gamefly.contorl;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JLabel;

import org.xf.gamefly.frame.IndexJFrame;
import org.xf.gamefly.graphics.BackgroundImageGroup;

public class RounRectJLabel extends JLabel {
	private static final long serialVersionUID = 1L;
	private String lbl;

	public RounRectJLabel(String lbl) {
		this.lbl = lbl;
		Dimension size = getPreferredSize();
		setPreferredSize(size);

	}

	public void paintComponent(Graphics g) {
		// g.setColor(Color.RED);
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		Image image = IndexJFrame.bgImage
				.getImage(BackgroundImageGroup.LABEL_IMAGE);

		g2.drawImage(image, 0, 0, this);
		
		//g2.setStroke(new BasicStroke(5,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		g2.setColor(new Color(179,251,157));
		g2.setFont(new Font("¿¬Ìå",Font.BOLD,15));
		g2.drawString(lbl,(int)(getSize().width*0.18),(int)(getSize().height*0.8));


	}

	public void paintBorder(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(getForeground());

		// g2.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND,
		// BasicStroke.JOIN_ROUND));
		// g.drawRo/undRect(0, -2, getSize().width - 1, getSize().height+2,
		// 20, 20);
	}
}
