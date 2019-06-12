package ricm3.game;

import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ricm3.interpreter.Direction;
import ricm3.interpreter.IAutomaton;
import ricm3.interpreter.Type;

public class Enemy extends MovingEntity {

	List<Ally> targets;

	public Enemy(Model model, int x, int y, int hp, BufferedImage[] sprites, IAutomaton automate, World originWorld,
			List<Ally> t) {
		super(model, x, y, hp, sprites, automate, originWorld);
		this.m_type = Type.ADVERSAIRE;
		targets = t;
	}

	public boolean pop(Direction dir) {
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
					if ((dx == 0) && (dy == 0)) {
						this.m_dir = Direction.NONE;
					} else {
						this.m_dir = Direction.SOUTH;
					}
				}
			}
		}
		return true;
	}
	
	public boolean wizz(Direction dir) {
		this.m_idsprite++;
		if (this.m_idsprite >= this.m_sprites.length) {
			this.m_idsprite = 0;
		}
		return true;
	}

	LinkedList<Point> aggro() {
		Iterator<Ally> iter = targets.iterator();
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
		if (t != null) {
			l.add(new Point(t.m_x, t.m_y));
		} else {
			l.add(new Point(m_x, m_y));
		}
		return l;
	}

	public boolean hit() {
		return this.m_model.m_currentworld.Hit(this, this.m_dir);
	}

	@Override
	public boolean hit(Direction dir, int power) {
		switch (dir) {
		case NORTH:
		case SOUTH:
		case EAST:
		case WEST:
			return this.m_model.m_currentworld.Hit(this, dir);
		case FRONT:
			return this.m_model.m_currentworld.Hit(this, this.m_dir);
		case BACK:
			switch (this.m_dir) {
			case NORTH:
				return this.m_model.m_currentworld.Hit(this, Direction.SOUTH);
			case SOUTH:
				return this.m_model.m_currentworld.Hit(this, Direction.NORTH);
			case EAST:
				return this.m_model.m_currentworld.Hit(this, Direction.WEST);
			case WEST:
				return this.m_model.m_currentworld.Hit(this, Direction.EAST);
			default:
				return false;
			}
		case LEFT:
			switch (this.m_dir) {
			case NORTH:
				return this.m_model.m_currentworld.Hit(this, Direction.WEST);
			case SOUTH:
				return this.m_model.m_currentworld.Hit(this, Direction.EAST);
			case EAST:
				return this.m_model.m_currentworld.Hit(this, Direction.NORTH);
			case WEST:
				return this.m_model.m_currentworld.Hit(this, Direction.SOUTH);
			default:
				return false;
			}
		case RIGHT:
			switch (this.m_dir) {
			case NORTH:
				return this.m_model.m_currentworld.Hit(this, Direction.EAST);
			case SOUTH:
				return this.m_model.m_currentworld.Hit(this, Direction.WEST);
			case EAST:
				return this.m_model.m_currentworld.Hit(this, Direction.SOUTH);
			case WEST:
				return this.m_model.m_currentworld.Hit(this, Direction.NORTH);
			default:
				return false;
			}
		default:
			return false;
		}
	}

}
