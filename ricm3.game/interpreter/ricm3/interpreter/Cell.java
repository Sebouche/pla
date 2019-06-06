package ricm3.interpreter;

import java.util.Iterator;

import ricm3.game.GameEntity;
import ricm3.game.Options;

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

	boolean isInside(GameEntity e, int x, int y) {
		return e.x() < x && e.x() + Options.Entity_size * Options.Scale > x && e.y() < y
				&& e.y() + Options.Entity_size * Options.Scale > y;
	}

	@Override
	public boolean eval(GameEntity e) {
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
				if (e.type() == f.type())
					return true;
			}
		}
		return false;
	}
}
