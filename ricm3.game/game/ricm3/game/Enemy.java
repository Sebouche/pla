package ricm3.game;

import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ricm3.interpreter.Direction;
import ricm3.interpreter.IAutomaton;
import ricm3.interpreter.Type;

public class Enemy extends MovingEntity {

	List<GameEntity> targets;

	public Enemy(Model model, int x, int y, int hp, BufferedImage[] sprites, IAutomaton automate, World originWorld,
			List<GameEntity> t) {
		super(model, x, y, hp, sprites, automate, originWorld);
		this.m_type = Type.ADVERSAIRE;
		targets = t;
	}

	public void Pop() {
		Point p = aggro().getFirst();
		double dx = p.x - this.m_x;
		double dy = p.y - this.m_y;
		if (dx < 0) {
			if (dy < 0) {
				if (dx < dy) {
					this.m_dir = Direction.WEST;
				} else {
					this.m_dir = Direction.NORTH;
				}
			} else {
				if (-dx > dy) {
					this.m_dir = Direction.WEST;
				} else {
					this.m_dir = Direction.SOUTH;
				}
			}
		} else {
			if (dy < 0) {
				if (dx > -dy) {
					this.m_dir = Direction.EAST;
				} else {
					this.m_dir = Direction.NORTH;
				}
			} else {
				if (dx > dy) {
					this.m_dir = Direction.EAST;
				} else {
					this.m_dir = Direction.SOUTH;
				}
			}
		}
	}
	
	public void Wizz() {
		this.m_idsprite++;
		if(this.m_idsprite>=this.m_sprites.length) {
			this.m_idsprite = 0;
		}
	}

	LinkedList<Point> aggro() {
		Iterator<GameEntity> iter = targets.iterator();
		double de, dt = -1;
		GameEntity e, t = null;
		while (iter.hasNext()) {
			e = iter.next();
			de = distance(e);
			if ((de < dt) || (dt < 0)) {
				t = e;
				dt = de;
			}
		}
		LinkedList<Point> l = new LinkedList<Point>();
		l.add(new Point(t.m_x, t.m_y));
		return l;
	}

}
