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

	// ����Actor2D�������顢��̬
	protected Vector<Actor2D> actors;
	// ������ת�Ĳ�����
	protected Actor2D anchor;
	// �����ﵱǰλ��
	protected Vector2D anchorPos;

	public IsoScene(Rectangle2D v) {
		super(v, v);
		actors = new Vector<Actor2D>();
		anchor = null;
		anchorPos = null;
	}

	public void add(Actor2D a, boolean isAnchor) {
		// ֻ����Actor���ǲ����������²��ܰ����ӵ�������
		if (!isAnchor) {
			actors.add(a);
		} else {
			// �����ｫ���⴦��
			anchor = a;
			anchorPos = new Vector2D.Double(anchor.getX(), anchor.getY());
		}
	}

	public void update() {
		// ���²�����
		anchor.update();
		// ���ݲ�����λ�ø��»���ʣ�ಿ��
		// �ڲ������ƶ�������²Ÿ���
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
		// ���Ʋ�������������
		for (Enumeration e = actors.elements(); e.hasMoreElements();) {
			//System.out.print("-");
			((Actor2D) e.nextElement()).paint(g2d);
		}
		// ���Ʋ�����
		anchor.paint(g2d);
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		if (anchor == null)
			return;
		// ��������һ����СΪ1/4ש��ĺ㶨�ٶ�
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
		// ֻҪ�����ɿ�������ֹͣ�ƶ�������
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

