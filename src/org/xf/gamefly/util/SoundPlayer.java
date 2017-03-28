package org.xf.gamefly.util;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayer {
	private Clip clip;
	//private AudioInputStream ais;

	public SoundPlayer(String soundPath){
		File soundFile = new File(soundPath);
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
			//获取一个剪辑对象
			clip = AudioSystem.getClip();
			//真正的打开音频
			clip.open(ais);
		
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loop(){
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void start(){
		clip.setMicrosecondPosition(0);
		clip.start();
		
	}
	public void stop(){
		clip.stop();
		clip.close();
	}
}
