package org.xf.gamefly.util;

import java.awt.Color;
import java.awt.Component;
import java.util.Enumeration;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ColorUIResource;


public class ColorUtil {
	public static void InitGlobalColor(Color color) {
		ColorUIResource colorRes = new ColorUIResource(color);
		for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys
				.hasMoreElements();) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof ColorUIResource) {
				UIManager.put(key, colorRes);
			}
		}
	}
	public static void setColor(String colorClass, Component c) {
		try {
			UIManager.setLookAndFeel(colorClass);
			SwingUtilities.updateComponentTreeUI(c);
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
