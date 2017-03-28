package org.xf.gamefly.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import org.xf.gamefly.actor.Hero;
import org.xf.gamefly.bean.User;

/*
 * 未考虑为空的情况
 */
public class ObjectReaderHero {
	public static ArrayList<User> getObjectReaderUser() {
		ArrayList<User>  heroObject = new ArrayList<User>();
		boolean flag = true;
		boolean dataFlag = false;
		int num = 0;
		User tmp;
		try {
			FileInputStream fis = new FileInputStream("db/top10.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);

			try {
				
				while (flag) {
					tmp = (User) ois.readObject();
					dataFlag = true;
					heroObject.add(tmp);
					//System.out.print(num++);
				}

			} catch (IOException | ClassNotFoundException e) {
				flag = false;
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		if(!dataFlag) return null;
		return heroObject;
	}
}
