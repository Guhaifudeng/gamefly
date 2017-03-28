package org.xf.app.actor2D;

import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
/*
 * actor 辅助类
 */
public class ImageLoader {
	protected JPanel pnl;
	protected Image image;
	protected int imageWidth;
	protected int imageHeight;
	
	protected static BufferedImage buffer = new BufferedImage(200,200,BufferedImage.TYPE_INT_BGR);
	public ImageLoader(JPanel a,String filePath,boolean wait) {
		this.pnl = a;
		try {
			File file = new File(filePath);
			image = ImageIO.read(file);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(wait){
			MediaTracker mt = new MediaTracker(this.pnl);
			//干嘛用的
			mt.addImage(image, 0);
			try{
				//等待加载主图像
				mt.waitForID(0);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
			//得到图像的宽和高
			imageWidth = image.getWidth(pnl);
			imageHeight = image.getHeight(pnl);
		//pnl.getGraphics().dra;
			
	}
	public Image getImage() {
		return image;
	}
	public int getImageWidth() {
		return imageWidth;
	}
	public int getImageHeight() {
		return imageHeight;
	}
	//使用一个图像过滤器从图像中获取一个单元
	public Image extractCell(int x,int y,int width,int height){
		//得到主图像的ImageProducer
		ImageProducer sourceProducer = image.getSource();
		Image cell = pnl.createImage(new FilteredImageSource(sourceProducer,new CropImageFilter(x,y,width,height)));
		//把这个单元绘制到屏外缓冲上
		buffer.getGraphics().drawImage(cell,0,0,pnl);
		return cell;
	}
	public Image extractCellScaled(int x,int y,int width,int height,int sw,int sh){
		//得到主图像的ImageProducer
		ImageProducer sourceProducer = image.getSource();
		Image cell = pnl.createImage(new FilteredImageSource(sourceProducer,new CropImageFilter(x,y,width,height)));
		//把这个单元绘制到屏外缓冲上
		buffer.getGraphics().drawImage(cell,0,0,pnl);
		return cell.getScaledInstance(sw, sh, Image.SCALE_SMOOTH);
				
	}//ImageLoader
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}//ImageGroup
