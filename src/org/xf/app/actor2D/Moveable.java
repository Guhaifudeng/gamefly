package org.xf.app.actor2D;
import java.awt.geom.*;
public interface Moveable {
	public Rectangle2D getBounds();
	public boolean collidesWith(Moveable other);
	public void addCollision(Moveable other);
	public void processCollisions();
	public void update();
}
