package org.xf.gamefly.bean;

import org.xf.gamefly.io.OpIOSet;


public class Boy extends User{

	private Boy boy;
	@Override
	public boolean insertData() {
		boy = new Boy();
		boy.setAge(age);
		boy.setEmail(email);
		boy.setID(ID);
		boy.setName(name);
		
		boy.setPassword(password);
		boy.setPhone(phone);
		if(OpIOSet.insertDataToDb(boy)) return true;
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int checkID(String ID, String password) {
		// TODO Auto-generated method stub
		boy = new Boy();
		boy.setID(ID);
		boy.setPassword(password);
		return OpIOSet.selectDataFromDb(boy);
	}
	@Override
	public boolean checkID(String ID) {
		boy = new Boy();
		boy.setID(ID);
		boy.setPassword("");
		if(OpIOSet.selectDataFromDb(boy)==-1)
			return true;
		else return false;
		// TODO Auto-generated method stub
	}
	@Override
	public void updateScore(User user) {
		boy = new Boy();
		boy.setID(user.getID());
		boy.setPassword(user.getPassword());
		boy.setHighestScore(user.getHighestScore());
		OpIOSet.updateDataToDb(boy);
		
	}
	public final Boy getBoy() {
		
		return boy;
	}

}
