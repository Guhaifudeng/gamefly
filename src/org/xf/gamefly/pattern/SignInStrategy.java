package org.xf.gamefly.pattern;

import org.xf.gamefly.model.SignInModel;



public class SignInStrategy {
	private SignInModel signInModel;
	public int checkID(String ID,String password){
		return signInModel.checkID(ID,password);
	}
	public void setSignInModel(SignInModel signInModel){
		this.signInModel = signInModel;
	}
	public SignInModel getSignInModel(){
		return this.signInModel;
	}

}
