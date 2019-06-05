package ElectricNetwork;

import java.util.*;

public class LinkedGraph {

	private class Cell {
		NetworkEntity e;
		List<Cell> connected;
		boolean mark = false;

		Cell(NetworkEntity ent) {
			e = ent;
			connected = new LinkedList<Cell>();
		}

		void connect(Cell c, double dmax) {
			if (((e.type == "Generator") || (c.e.type == "Generator") || (e.type == "Pylon") || (c.e.type == "Pylon"))
					&& (e.distance(c.e) <= dmax)) {
				connected.add(c);
				c.connected.add(this);
			}
		}
	}

	private List<Cell> nodes;
	private List<Cell> generators;
	double dmax;

	public LinkedGraph(float dist) {
		nodes = new LinkedList<Cell>();
		generators = new LinkedList<Cell>();
		dmax = dist;
	}

	public void add(NetworkEntity ent) {
		Cell n = new Cell(ent);
		if (ent.type == "Generator") {
			generators.add(n);
		}
		Iterator<Cell> iter = nodes.iterator();
		while (iter.hasNext()) {
			iter.next().connect(n, dmax);
		}
		iter = null;
		nodes.add(n);
	}

	public void remove(NetworkEntity ent) {
		Cell c;
		Iterator<Cell> iter;
		if (ent.type == "Generator") {
			iter = generators.iterator();
			while (iter.hasNext()) {
				c = iter.next();
				if (c.e.equals(ent)) {
					iter = null;
					generators.remove(c);
					break;
				}
			}
		}
		iter = nodes.iterator();
		while (iter.hasNext()) {
			c = iter.next();
			if (c.e.equals(ent)) {
				iter = c.connected.iterator();
				while (iter.hasNext()) {
					iter.next().connected.remove(c);
				}
				iter = null;
				c.connected = null;
				nodes.remove(c);
				return;
			}
		}
	}

	public boolean areConnected(NetworkEntity e1, NetworkEntity e2) {
		Iterator<Cell> iter = nodes.iterator();
		Cell c;
		while (iter.hasNext()) {
			c = iter.next();
			if ((c.e.equals(e1)) || (c.e.equals(e2))) {
				iter = c.connected.iterator();
				if (c.e.equals(e1)) {
					while (iter.hasNext()) {
						if (iter.next().e.equals(e2)) {
							return true;
						}
					}
				} else {
					while (iter.hasNext()) {
						if (iter.next().e.equals(e1)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public boolean hasPower(NetworkEntity ent) {
		Iterator<Cell> iter = nodes.iterator();
		Cell c;
		Cell r = null;
		while (iter.hasNext()) {
			c = iter.next();
			c.mark = false;
			if (c.e.equals(ent)) {
				r = c;
			}
		}
		if (r != null) {
			return Power(r);
		}
		return false;
	}

	private boolean Power(Cell ent) {
		if ((ent.e.type == "Generator") && (ent.e.load > 0)) {
			return true;
		} else {
			ent.mark = true;
			Iterator<Cell> iter = ent.connected.iterator();
			Cell c;
			while (iter.hasNext()) {
				c = iter.next();
				if (!c.mark) {
					c.mark = true;
					if (Power(c)) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
