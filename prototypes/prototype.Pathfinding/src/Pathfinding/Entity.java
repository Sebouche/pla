package Pathfinding;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Entity {
	double m_x, m_y, m_w, m_h;
	List<Entity> targets;

	public Entity(double x, double y, double w, double h, List<Entity> t) {
		m_x = x;
		m_y = y;
		m_w = w;
		m_h = h;
		targets = t;
	}

	LinkedList<Point> aggro() {
		Iterator<Entity> iter = targets.iterator();
		double de, dt = -1;
		Entity e, t = null;
		while (iter.hasNext()) {
			e = iter.next();
			de = distance(e);
			if ((de < dt) || (dt < 0)) {
				t = e;
				dt = de;
			}
		}
		LinkedList<Point> l = new LinkedList<Point>();
		l.add(new Point(t.m_x,t.m_y));
		return l;
	}

	double distance(Entity e) {
		double dx1, dx2, dy1, dy2;
		dx1 = m_x - (e.m_x + e.m_w);
		dx2 = e.m_x - (m_x + m_w);
		dy1 = m_y - (e.m_y + e.m_h);
		dy2 = e.m_y - (m_y + m_h);
		if ((dx1 <= 0) && (dx2 <= 0) && (dy1 <= 0) && (dy2 <= 0)) {
			return 0;
		}
		if ((dx1 <= 0) && (dx2 <= 0)) {
			return Math.max(dy1, dy2);
		} else if ((dy1 <= 0) && (dy2 <= 0)) {
			return Math.max(dx1, dx2);
		}
		if (dx1 > 0) {
			if (dy1 > 0) {
				return Math.sqrt(dx1*dx1+dy1*dy1);
			} else if (dy2 > 0) {
				return Math.sqrt(dx1*dx1+dy2*dy2);
			}
		} else if (dx2 > 0) {
			if (dy1 > 0) {
				return Math.sqrt(dx2*dx2+dy1*dy1);
			} else if (dy2 > 0) {
				return Math.sqrt(dx2*dx2+dy2*dy2);
			}
		}
		return -1;
	}
}