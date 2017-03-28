package org.xf.gamefly.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;



import org.xf.gamefly.bean.User;
import org.xf.gamefly.util.MyCompare;

/*
 * 未考虑为空的情况
 */
public class ObjectWriteHero {


	private static User[] emps;
	private static boolean hasFlag;

	public static void setObjectWriteUser(User user) {
		
		ArrayList<User> userList =  ObjectReaderHero.getObjectReaderUser();
		
		hasFlag =false;
		
		if(userList==null){
			emps = new User[1];
			//userList.toArray(emps);
			emps[0]=user;
		}else{
			for(int i =0;i<userList.size();i++){
				
				if(userList.get(i).getID().equals(user.getID())){
					userList.get(i).setHighestScore(user.getHighestScore());
					hasFlag = true;
					break;
				}
			}
			
			if(hasFlag) emps = new User[userList.size()];
			else{
				emps = new User[userList.size()+1];
			}
			userList.toArray(emps);
			
			if(!hasFlag) emps[userList.size()]=user;
		}
		//System.out.print(b);
		Arrays.sort(emps, new MyCompare()); 

		try {
			FileOutputStream fos = new FileOutputStream("db/top10.dat");

			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			for (int i = 0; i < emps.length && i < 10; i++){
				
				oos.writeObject(emps[i]);
			}
			oos.flush();
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
