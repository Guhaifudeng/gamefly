package org.xf.app.containters;

import java.awt.Image;

import org.xf.app.actor2D.Vector2D;

//
public class Menu2D extends Panel2D {
	
	// 
	// 判断菜单只是展现在画面上还是它决定绘制循环
	protected boolean overlay;

	// 用指定的背景图片、位置、覆盖属性值构建一个新的Menu2D对象
	// 
	public Menu2D(Image bgImage, Vector2D p, boolean over) {
		
		super(bgImage, p);
		overlay = over;
	}

	// 返回这个菜单是否是一个覆盖图
	public final boolean isOverlay() {
		return overlay;
	}
} // Menu2D