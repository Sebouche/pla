package ricm3.interpreter;

import java.util.Iterator;

import ricm3.game.GameEntity;

public class Closest extends ICondition {
	Type type;
	Direction dir;

	public Closest(String e, String dir) {
		this.type = Type.strToType(e);
		this.dir = Direction.strToDir(dir);
	}
	
	Direction dirClosest(GameEntity e, GameEntity f) {
		int x = e.x()-f.x();
		int y = e.y()-f.y();
		if(Math.abs(x)>Math.abs(y)) {
			if (x > 0) return Direction.WEST;
			return Direction.EAST;
		}
		if (y>0) return Direction.SOUTH;
		return Direction.NORTH;
	}

	@Override
	public boolean eval(GameEntity e) {
		double min = -1;
		GameEntity close = null;
		Iterator<GameEntity> iter = e.entities().iterator();
		while (iter.hasNext()) {
			GameEntity f = iter.next();
			if (f.type() == type) {
				if (e.distance(f) < min || min < 0) {
					min = e.distance(f);
					close = f;
				}
			}
		}
		if (close == null) return false;
		
		
		

		return dir == dirClosest(e,close);
	}
}
