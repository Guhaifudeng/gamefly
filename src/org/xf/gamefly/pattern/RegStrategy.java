package org.xf.gamefly.pattern;

import org.xf.gamefly.model.RegModel;



public class RegStrategy {
	private RegModel regModel;
	public boolean insertData(){
		return regModel.insertData();
	}
	public void setRegModel(RegModel regModel){
		this.regModel = regModel;
	}
	public boolean checkID(String ID){
		return regModel.checkID(ID);
	}
}
