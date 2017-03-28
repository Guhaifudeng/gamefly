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
 * actor ������
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
			//�����õ�
			mt.addImage(image, 0);
			try{
				//�ȴ�������ͼ��
				mt.waitForID(0);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
			//�õ�ͼ��Ŀ�͸�
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
	//ʹ��һ��ͼ���������ͼ���л�ȡһ����Ԫ
	public Image extractCell(int x,int y,int width,int height){
		//�õ���ͼ���ImageProducer
		ImageProducer sourceProducer = image.getSource();
		Image cell = pnl.createImage(new FilteredImageSource(sourceProducer,new CropImageFilter(x,y,width,height)));
		//�������Ԫ���Ƶ����⻺����
		buffer.getGraphics().drawImage(cell,0,0,pnl);
		return cell;
	}
	public Image extractCellScaled(int x,int y,int width,int height,int sw,int sh){
		//�õ���ͼ���ImageProducer
		ImageProducer sourceProducer = image.getSource();
		Image cell = pnl.createImage(new FilteredImageSource(sourceProducer,new CropImageFilter(x,y,width,height)));
		//�������Ԫ���Ƶ����⻺����
		buffer.getGraphics().drawImage(cell,0,0,pnl);
		return cell.getScaledInstance(sw, sh, Image.SCALE_SMOOTH);
				
	}//ImageLoader
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}//ImageGroup
