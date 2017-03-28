package org.xf.gamefly.pattern;

import org.xf.gamefly.bean.Boy;
import org.xf.gamefly.bean.Girl;
import org.xf.gamefly.bean.User;




public class UserSimpleFactory {
	public static User createUser(String which) throws NoUser{
		if(which.equalsIgnoreCase("girl")){
			return new Girl();
		}
		else if(which.equalsIgnoreCase("boy")){
			return new Boy(); 
		}else{
			
				throw new NoUser("NO User");
			
		}
		
	}
	
	public static User createUser(String which,String ID) throws NoUser{
		if(which.equalsIgnoreCase("girl")){
			//根据ID 调用 girl IO,查询后将对象复出，未查到便为空
			return new Girl();
		}
		else if(which.equalsIgnoreCase("boy")){
			//根据ID 调用 boy IO,查询后将对象复出，未查到便为空
			return new Boy(); 
		}else{
			
				throw new NoUser("NO User");
			
		}
		
	}
	
	
}
