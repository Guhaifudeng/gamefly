package org.xf.app.actor2D;

import javax.swing.JPanel;

public abstract class Actor2DGroup  extends ImageGroup{
	//int 和float的默认值大小
	protected static final int MAX_INT_UNBOUND = Integer.MAX_VALUE;
	protected static final int MIN_INT_UNBOUND = Integer.MIN_VALUE;

	protected static final  double MAX_DBL_UNBOUND = Double.MAX_VALUE;
	protected static final  double MIN_DBL_UNBOUND = Double.MAX_VALUE;
	//Actor2D 可以具备的位置和速度的最大最小值
	//覆盖可以在构造函数或者init的方法中改变这些值
	public int MAX_X_POS = MAX_INT_UNBOUND;
	public int MAX_Y_POS = MAX_INT_UNBOUND;
	public int MIN_X_POS = MIN_INT_UNBOUND;
	public int MIN_Y_POS = MIN_INT_UNBOUND;
	
	
	public int MAX_X_VEL = MAX_INT_UNBOUND;
	//构造一个新的ActorGroup2D对象
	protected Actor2DGroup(){
		super();
	}
	//初始化共享的Actor2D的属性
	public abstract void init(JPanel a);
}//ActorGroup2D
