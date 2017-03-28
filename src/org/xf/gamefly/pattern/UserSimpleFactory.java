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
			//����ID ���� girl IO,��ѯ�󽫶��󸴳���δ�鵽��Ϊ��
			return new Girl();
		}
		else if(which.equalsIgnoreCase("boy")){
			//����ID ���� boy IO,��ѯ�󽫶��󸴳���δ�鵽��Ϊ��
			return new Boy(); 
		}else{
			
				throw new NoUser("NO User");
			
		}
		
	}
	
	
}
