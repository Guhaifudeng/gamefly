package org.xf.app.actor2D;

import javax.swing.JPanel;

public abstract class Actor2DGroup  extends ImageGroup{
	//int ��float��Ĭ��ֵ��С
	protected static final int MAX_INT_UNBOUND = Integer.MAX_VALUE;
	protected static final int MIN_INT_UNBOUND = Integer.MIN_VALUE;

	protected static final  double MAX_DBL_UNBOUND = Double.MAX_VALUE;
	protected static final  double MIN_DBL_UNBOUND = Double.MAX_VALUE;
	//Actor2D ���Ծ߱���λ�ú��ٶȵ������Сֵ
	//���ǿ����ڹ��캯������init�ķ����иı���Щֵ
	public int MAX_X_POS = MAX_INT_UNBOUND;
	public int MAX_Y_POS = MAX_INT_UNBOUND;
	public int MIN_X_POS = MIN_INT_UNBOUND;
	public int MIN_Y_POS = MIN_INT_UNBOUND;
	
	
	public int MAX_X_VEL = MAX_INT_UNBOUND;
	//����һ���µ�ActorGroup2D����
	protected Actor2DGroup(){
		super();
	}
	//��ʼ�������Actor2D������
	public abstract void init(JPanel a);
}//ActorGroup2D
