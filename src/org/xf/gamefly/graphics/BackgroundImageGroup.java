package org.xf.gamefly.graphics;

import java.awt.Image;

import javax.swing.JPanel;
import org.xf.app.actor2D.ImageLoader;

public class BackgroundImageGroup {
	public static final int INDEX_IMAGE = 0;
	public static final int REG_IMAGE = 1;
	public static final int LOGIN_IMAGE = 2;
	public static final int HOME_IMAGE = 3;
	public static final int TOP_IMAGE =4;
	public static final int LABEL_IMAGE = 5;
	public static final int SHOW_IMAGE = 6;
	Image[] image;
	public BackgroundImageGroup(){
		image= new Image[10]; 
	}
	public void  init(JPanel a){
		ImageLoader loader;
		loader = new ImageLoader(a, "myimage/index.gif", true);
		image[INDEX_IMAGE] = loader.extractCell(0, 0,500,500);
		
		loader = new ImageLoader(a, "myimage/index.gif", true);
		image[REG_IMAGE] = loader.extractCell(0, 0,500,500);
		
		loader = new ImageLoader(a, "myimage/index.gif", true);
		image[LOGIN_IMAGE] = loader.extractCell(0, 0,500,500);
		
		loader = new ImageLoader(a, "myimage/home.gif", true);
		image[HOME_IMAGE] = loader.extractCell(0, 0,500,700);
		
		loader = new ImageLoader(a, "myimage/topbg.gif", true);
		image[TOP_IMAGE] = loader.extractCell(0, 0,300,500);
		
		loader = new ImageLoader(a, "myimage/label.gif", true);
		image[LABEL_IMAGE] = loader.extractCell(0, 0,150,30);
		
		loader = new ImageLoader(a, "myimage/show.jpg", true);
		image[SHOW_IMAGE] = loader.extractCellScaled(0, 0,loader.getImageWidth(),loader.getImageHeight(),550,600);
	}
	public Image getImage(int index){
		return image[index];
	}
}
