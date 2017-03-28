package org.xf.gamefly.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.xf.gamefly.bean.Boy;
import org.xf.gamefly.bean.Girl;
import org.xf.gamefly.bean.User;
import org.xf.gamefly.util.StringSet;


public class OpIOSet {
	
	static ObjectOutputStream getOutputStream(String dbName) throws IOException {
		ObjectOutputStream oos = AppendObjectOutputStream.newInstance(new File(
				StringSet.dbPath + "/" + dbName));
		return oos;
	}
	static ObjectOutputStream getObjectOutputStream(String dbName) throws IOException {
		FileOutputStream fout=new FileOutputStream(new File(
				StringSet.dbPath + "/" + dbName));
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		return oos;
	}
	
	public static boolean insertDataToDb(Boy boy) {
		try {
			ObjectOutputStream oos = getOutputStream("user.dat");
			
			oos.writeObject((User)boy);
			oos.flush();
			oos.close();
			return true;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public static boolean insetDataToDb(Girl girl) {
		try {
			ObjectOutputStream oos = getOutputStream("user.dat");
			oos.writeObject((User)girl);
			
			oos.flush();
			oos.close();
			return true;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	 

	static ObjectInputStream getInputStream(String dbName)
			throws FileNotFoundException, IOException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				new File(StringSet.dbPath + "/" + dbName)));
		return ois;
	}
	

	public static int selectDataFromDb(Boy boy) {
		try {
			boolean flag = true;
			boolean rightId = false;
			ObjectInputStream ois = getInputStream("user.dat");
			User boyTemp = new Boy();

			while (flag) {
				try {
					boyTemp = (User) ois.readObject();
					if (boyTemp.getID().equals(boy.getID())) {
						rightId = true;
						break;
					}

					// ra.add(num++,raa);
				} catch (Exception e) {
					// throw e;
					flag = false;
				}
			}
			ois.close();
			if (rightId) {
				if (boyTemp.getPassword().equals(boy.getPassword())) {
				
					boy.setAge(boyTemp.getAge());//�ܷ�ı���--����
					boy.setID(boyTemp.getID());
					boy.setName(boyTemp.getName());
					boy.setHighestScore(boyTemp.getHighestScore());
					
					//System.out.println("1-"+boy.getName());
					System.out.println(01 + " " + boy.toString());
					return 0;// �û�����ȷ ������ȷ �����¼
				} else {
					System.out.println(11 + " " + boy.toString());
					return 1;// �û�����ȷ �������
				}
			} else {
				System.out.println(-11 + " " + boy.toString());
				return -1;// �û�������
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return -1;//��ʼ��Ϊ�գ��û���������
		}
	}

	public static int selectDataFromDb(Girl girl) {
		try {
			boolean flag = true;
			boolean rightId = false;
			ObjectInputStream ois = getInputStream("user.dat");
			User girlTemp = new Girl();

			while (flag) {
				try {
					girlTemp = (User) ois.readObject();
					
					if (girlTemp.getID().equals(girl.getID())) {
						rightId = true;
						break;
					}

					// ra.add(num++,raa);
				} catch (Exception e) {
					// throw e;
					flag = false;
				}
			}
			ois.close();
			if (rightId) {
				if (girlTemp.getPassword().equals(girl.getPassword())) {
					//girl=girlTemp;
					
					girl.setAge(girlTemp.getAge());//�ܷ�ı���--����
					girl.setID(girlTemp.getID());
					girl.setName(girlTemp.getName());
					girl.setHighestScore(girlTemp.getHighestScore());
					
					System.out.println(0 + " " + girlTemp.toString());
					return 0;// �û�����ȷ ������ȷ �����¼
				} else {
					System.out.println(1 + " " + girlTemp.toString());
					return 1;// �û�����ȷ �������
				}

			} else {
				System.out.println(-1 + " " + girlTemp.toString());
				return -1;// �û�������
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//��ʼ��Ϊ�գ��û���������
			return -1;
		}
	}
	
	
	public static void updateDataToDb(Boy boy) {
		try {
			boolean flag = true;
			ObjectInputStream ois = getInputStream("user.dat");
			User boyTemp;
			ArrayList<User> userList = new ArrayList<User> ();
			while (flag) {
				try {
					boyTemp = (User) ois.readObject();
					
					if (boyTemp.getID().equals(boy.getID())) {
						boyTemp.setHighestScore(boy.getHighestScore());
					}
					
					userList.add(boyTemp);

					// ra.add(num++,raa);
				} catch (Exception e) {
					// throw e;
					flag = false;
				}
			}
			ois.close();
			
			ObjectOutputStream oos = getObjectOutputStream("user.dat");
			for(int i =0;i<userList.size();i++){
				
				oos.writeObject(userList.get(i));
				
				
			}
			oos.flush();
			oos.close();
			
			
			
			
			
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ;//��ʼ��Ϊ�գ��û���������
		}
	}

	public static void updateDataToDb(Girl girl) {
		try {
			boolean flag = true;
			ObjectInputStream ois = getInputStream("user.dat");
			User girlTemp;
			ArrayList<User> userList = new ArrayList<User> ();
			while (flag) {
				try {
					girlTemp = (User) ois.readObject();
					
					if (girlTemp.getID().equals(girl.getID())) {
						girlTemp.setHighestScore(girl.getHighestScore());
					}
					
					userList.add(girlTemp);

					// ra.add(num++,raa);
				} catch (Exception e) {
					// throw e;
					flag = false;
				}
			}
			ois.close();
			
			ObjectOutputStream oos = getObjectOutputStream("user.dat");
			for(int i =0;i<userList.size();i++){
				
				oos.writeObject(userList.get(i));
				
				
			}
			oos.flush();
			oos.close();
			
			
			
			
			
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ;//��ʼ��Ϊ�գ��û���������
		}
	}

}
