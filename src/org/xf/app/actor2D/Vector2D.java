package org.xf.app.actor2D;
/*
 * actor 辅助类
 */
public abstract class Vector2D extends Object {
	public static final Vector2D.Double ZERO_VECTOR = new Vector2D.Double(0, 0);

	public static class Double extends Vector2D {
		// Vector2D.Double 对象的x y属性
		public double x;
		public double y;

		// 用值（0，0）创建一个默认的Vector2D对象
		public Double() {
			this(0.0, 0.0);
		}

		public Double(double m, double n) {
			setX(m);
			setY(n);

		}

		private Double(int m, int n) {
			setX((double) m);
			setY((double) n);
		}

		// x y属性的get/set方法
		public final void setX(double n) {
			x = n;
		}

		public final void setY(double n) {
			y = n;
		}

		public final double getX() {
			return x;
		}

		public final double getY() {
			return y;
		}
		
		public Vector2D plus(Vector2D v){
			return new Double(getX()+v.getX(),getY()+v.getY());
		}
		public Vector2D minus(Vector2D v){
			return new Double(getX()-v.getX(),getY()-v.getY());
		}
	}//Double
		
		
		public static class Integer extends Vector2D{
			//Vector2D.Integer 对象的x y属性
			
			public int x;
			public int y;
			public Integer(){
				this(0,0);
			}
			public Integer(int m, int n){
				setX(m);
				setY(n);
			}
			
			private Integer(double m, double n){
				setX((int)m);
				setY((int)n);
			}
			
			//x y 属性的get set方法
			public final void setX(double n){
				x = (int)n;
			}
			public final void setY(double n){
				y = (int)n;
			}
			
			public final double getX(){
				return (double)x;
			}
			public final double getY(){
				return (double) y;
			}
			
			public Vector2D plus(Vector2D v){
				return new Integer(getX()+v.getX(),getY()+v.getY());
			}
			
			public Vector2D minus(Vector2D v){
				return new Integer(getX()-v.getX(),getY()+v.getY());
			}
			
		}//Integer
		
		protected Vector2D(){}
		public abstract void setX(double n);
		public abstract void setY(double n);
		public abstract double getX();
		public abstract double getY();
		public abstract Vector2D plus(Vector2D v);
		public abstract Vector2D minus(Vector2D v);
		
		public boolean equals(Vector2D other){
			return (getX() == other.getX()&& getY()==other.getY());
		}
		
		public void normalize(){
			double len = length();
			setX(getX()/len);
			setY(getY()/len);
		}
		
		public void scale(double k){
			setX(k*getX());
			setY(k*getY());
		}
		//给定值平移Vector2D
		public void translate(double dx,double dy)
		{
			setX(getX()+dx);
			setY(getY()+dy);
		}
		//用给定的Vector2D值平移Vector2D
		public void translate(Vector2D v){
			setX(getX()+v.getX());
			setY(getY()+v.getY());
		}
		//点乘
		public double dot(Vector2D v){
			return getX()*v.getX()+getY()*v.getY();
		}
		//返回描述这个Vector2D的字符串
		public String toString(){
			return getClass().getName()+"(x="+getX()+",y="+getY()+")";
		}
		//返回矢量长度
		public double length(){
			return Math.sqrt(this.dot(this));
		}
}//Vector2D
