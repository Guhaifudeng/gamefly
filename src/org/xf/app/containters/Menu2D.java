package org.xf.app.containters;

import java.awt.Image;

import org.xf.app.actor2D.Vector2D;

//
public class Menu2D extends Panel2D {
	
	// 
	// �жϲ˵�ֻ��չ���ڻ����ϻ�������������ѭ��
	protected boolean overlay;

	// ��ָ���ı���ͼƬ��λ�á���������ֵ����һ���µ�Menu2D����
	// 
	public Menu2D(Image bgImage, Vector2D p, boolean over) {
		
		super(bgImage, p);
		overlay = over;
	}

	// ��������˵��Ƿ���һ������ͼ
	public final boolean isOverlay() {
		return overlay;
	}
} // Menu2D