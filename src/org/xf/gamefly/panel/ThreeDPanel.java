package org.xf.gamefly.panel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

import org.xf.gamefly.frame.IndexJFrame;
import org.xf.gamefly.graphics.BackgroundImageGroup;

public class ThreeDPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		Dimension sz = getSize();
		Image image = IndexJFrame.bgImage.getImage(BackgroundImageGroup.REG_IMAGE);
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
		//g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(6, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND));
		g2.setPaint(Color.BLACK);
		g2.draw3DRect(0, 0, sz.width-1, sz.height-1, false);
		//g2.drawLine(40, 40, 90, 90);
	}

}