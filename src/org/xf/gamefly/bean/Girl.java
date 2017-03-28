package org.xf.gamefly.bean;

import org.xf.gamefly.io.OpIOSet;


public class Girl extends User{
	private Girl girl;
	@Override
	public boolean insertData() {
		// TODO Auto-generated method stub
		girl = new Girl();
		girl.setID(ID);
		girl.setAge(age);
		girl.setEmail(email);
		girl.setName(name);
		girl.setPassword(password);
		girl.setPhone(phone);
		if(OpIOSet.insetDataToDb(girl)) return true;
		
		return false;
	}
	@Override
	public int checkID(String ID, String password) {
		// TODO Auto-generated method stub
		girl = new Girl();
		girl.setID(ID);
		girl.setPassword(password);
		return OpIOSet.selectDataFromDb(girl);
	}
	@Override
	public boolean checkID(String ID) {
		girl = new Girl();
		girl.setID(ID);
		girl.setPassword("");
		if(OpIOSet.selectDataFromDb(girl)==-1)
			return true;
		else return false;
		
	}
	public final Girl getGirl() {
		return girl;
	}
	@Override
	public void updateScore(User user) {
		girl = new Girl();
		girl.setID(user.getID());
		girl.setPassword(user.getPassword());
		girl.setHighestScore(user.getHighestScore());
		OpIOSet.updateDataToDb(girl);
	}


	
}
