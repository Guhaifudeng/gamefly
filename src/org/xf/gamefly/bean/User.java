package org.xf.gamefly.bean;

import java.io.Serializable;

import org.xf.gamefly.model.RegModel;
import org.xf.gamefly.model.ScoresUpdate;
import org.xf.gamefly.model.SignInModel;

abstract public class User implements SignInModel,RegModel,ScoresUpdate,Serializable{
	protected int age = 0;
	protected String ID = "" ;
	protected String name = "" ;
	protected String email = "";
	protected String password = "";
	protected String repass = "";
	protected String phone = "";
	protected int highestScore = 0;
	public final int getHighestScore() {
		return highestScore;
	}
	public final void setHighestScore(int highestScore) {
		this.highestScore = highestScore;
	}
	public int getAge() {
		return age;
	}
	public boolean setAge(String age) {
		//if(age.length()==0)return false;
		if(age.matches("^[1-9]\\d*|0$")){
			this.age =Integer.parseInt(age);
			return true;
		}
		else return false;
			
	}
	public void setAge(int age){
		this.age = age;
	}
	public String getID() {
		
		return ID;
	}
	

	public boolean setID(String ID) {
		if(ID.matches("[a-zA-Z0-9]*")){
			this.ID = ID;
			return true;
		}else return false;
	}
	public String getName() {
		return name;
	}
	public  boolean setName(String name) {
		
		if(name.matches("^[a-zA-Z0-9\u4e00-\u9fa5]+$")){
			this.name = name;
			return true;
		}else return false;
	}
	public String getEmail() {
		return email;
	}
	public  boolean setEmail(String email) {
		if(email.matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")){
			this.email = email; 
			
			return true;
		}else return false;
	}
	public String getPassword() {
		return password;
	}
	public  boolean setPassword(String password) {
		if(password.matches("[a-zA-Z0-9]*")){
			this.password = password;
			return true;
		}else return false;
	}
	public String getRepass() {
		return repass;
	}
	public  boolean setRepass(String repass) {
		if(repass.matches("[a-zA-Z0-9]*")&&repass.equals(password)){
			this.repass = repass;
			return true;
		}else return false;
	}
	public String getPhone() {
		return phone;
	}
	public  boolean setPhone(String phone) {
		if(phone.matches("^[1-9]*[1-9][0-9]*$")){
			if(phone.length()!=11) return false;
			this.phone = phone;
			return true;
		}else return false;
		

	} 
	
}
