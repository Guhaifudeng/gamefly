package org.xf.gamefly.util;

import java.awt.Component;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SkinUtil {
	public static void setSkin(String skinClass, Component c) {
		try {
			UIManager.setLookAndFeel(skinClass);
			SwingUtilities.updateComponentTreeUI(c);
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
