package org.xf.app.scene.iso;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.Enumeration;
import java.util.Vector;

import org.xf.app.actor2D.Actor2D;
import org.xf.app.actor2D.Vector2D;
import org.xf.app.scene.Scene;

public class IsoScene extends Scene implements KeyListener {

	// 背景Actor2D对象数组、动态
	protected Vector<Actor2D> actors;
	// 画面旋转的参照物
	protected Actor2D anchor;
	// 参照物当前位置
	protected Vector2D anchorPos;

	public IsoScene(Rectangle2D v) {
		super(v, v);
		actors = new Vector<Actor2D>();
		anchor = null;
		anchorPos = null;
	}

	public void add(Actor2D a, boolean isAnchor) {
		// 只有在Actor不是参照物的情况下才能把它加到数组中
		if (!isAnchor) {
			actors.add(a);
		} else {
			// 参照物将另外处理
			anchor = a;
			anchorPos = new Vector2D.Double(anchor.getX(), anchor.getY());
		}
	}

	public void update() {
		// 更新参照物
		anchor.update();
		// 根据参照物位置更新画面剩余部分
		// 在参照物移动的情况下才更新
		if (!anchor.getVel().equals(Vector2D.ZERO_VECTOR)) {
			double x = 0.0;
			double y = 0.0;
			
			x = -anchor.getVel().getX();
			y = -anchor.getVel().getY();
			for (Enumeration e = actors.elements(); e.hasMoreElements();) {
				((Actor2D) e.nextElement()).moveBy(x, y);
				((Actor2D) e.nextElement()).update();
			}
		}
	}

	public void paint(Graphics2D g2d) {
		// 绘制参照物以外的组件
		for (Enumeration e = actors.elements(); e.hasMoreElements();) {
			//System.out.print("-");
			((Actor2D) e.nextElement()).paint(g2d);
		}
		// 绘制参照物
		anchor.paint(g2d);
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		if (anchor == null)
			return;
		// 给参照物一个大小为1/4砖块的恒定速度
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			anchor.setVel(-IsoTileGroup.TILE_WIDTH / 2,
					-IsoTileGroup.TILE_HEIGHT / 2);
			break;
		case KeyEvent.VK_DOWN:
			anchor.setVel(+IsoTileGroup.TILE_WIDTH / 2,
					+IsoTileGroup.TILE_HEIGHT / 2);
			break;
		case KeyEvent.VK_LEFT:
			anchor.setVel(-IsoTileGroup.TILE_WIDTH / 2,
					+IsoTileGroup.TILE_HEIGHT / 2);
			break;
		case KeyEvent.VK_RIGHT:
			anchor.setVel(+IsoTileGroup.TILE_WIDTH / 2,
					-IsoTileGroup.TILE_HEIGHT / 2);
			break;
		default:
			break;
		}
	}

	public void keyReleased(KeyEvent e) {
		if (anchor == null)
			return;
		// 只要键被松开，马上停止移动参照物
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_RIGHT:
			anchor.setVel(0, 0);
			break;
		default:
			break;
		}
	}
} // IsoScene

