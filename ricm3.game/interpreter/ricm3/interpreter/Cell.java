package ricm3.interpreter;

import java.util.Iterator;

import ricm3.game.GameEntity;

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
		if(1==1) {
		return true;
		}
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
				if (kind == f.type())
					return true;
			}
		}
		return false;
	}
}
