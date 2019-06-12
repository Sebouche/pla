package ricm3.interpreter;

import java.util.Iterator;

import ricm3.game.*;

public class Cell extends ICondition {
	Direction direction;
	Type kind;
	int distance;

	public Cell(String direction, String kind, int distance) {
		this.direction = Direction.strToDir(direction);
		this.kind = Type.strToType(kind);
		this.distance = distance;
	}

	public Cell(String string, String kind) {
		this.direction = Direction.strToDir(string);
		this.kind = Type.strToType(kind);
		this.distance = 1;
	}

	@Override
	public boolean eval(GameEntity e) {
		if (kind == Type.ANYTHING)
			return true;
		Direction dir = Direction.entityDir(e, direction);
		int cellx = e.x();
		int celly = e.y();
		switch (dir) {
		case NORTH:
			celly -= distance;
			break;
		case SOUTH:
			celly += distance;
			break;
		case WEST:
			cellx -= distance;
			break;
		case EAST:
			cellx += distance;
			break;
		default:
			break;
		}
		Iterator<GameEntity> iter = e.entities().iterator();
		while (iter.hasNext()) {
			GameEntity f = iter.next();
			if (isInside(f, cellx, celly)) {
				if (kind == Type.VOID) {
					if (kind != f.type())
						return false;
				} else {
					if (kind == f.type())
						return true;
				}
			}
		}
		if (e.m_originWorld instanceof UndergroundWorld) {
			int pos_x = (int) (e.x() / (Options.Entity_size * Options.Scale));
			int pos_y = (int) (e.y() / (Options.Entity_size * Options.Scale));
			switch (dir) {
			case NORTH:
				pos_y -= distance;
				break;
			case SOUTH:
				pos_y += distance;
				break;
			case WEST:
				pos_x -= distance;
				break;
			case EAST:
				pos_x += distance;
				break;
			default:
				break;
			}
			GameEntity f = ((UndergroundWorld) e.m_originWorld).m_grid[Math.floorMod(pos_y, 60)][Math.floorMod(pos_x,
					60)];
			if (f != null && f.m_collision && isInside(f, cellx, celly)) {
				if (kind == Type.VOID) {
					if (kind != f.type())
						return false;
				} else {
					if (kind == f.type())
						return true;
				}
			}

		}
		if (kind == Type.VOID)
			return true;
		return false;
	}
}
