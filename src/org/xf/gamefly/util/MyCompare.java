package org.xf.gamefly.util;

import java.util.Comparator;
import org.xf.gamefly.bean.User;

public class MyCompare implements Comparator<User> {

	@Override
	public int compare(User p1, User p2) {
		// TODO Auto-generated method stub
		if (p1.getHighestScore()> p2.getHighestScore()) {
			return -1;
		} else if (p1.getHighestScore()< p2.getHighestScore()) {
			return 1;
		} else {
			return p1.getName().compareTo(p2.getName());
		}
	}

}
 