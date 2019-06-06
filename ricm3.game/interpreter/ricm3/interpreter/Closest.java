package ricm3.interpreter;

import java.util.Iterator;

import ricm3.game.GameEntity;

public class Closest extends ICondition {
	Type e;
	Direction dir;

	public Closest(String e, String dir) {
		this.e = Type.strToType(e);
		this.dir = Direction.strToDir(dir);
	}

/*	@Override
	public boolean eval(GameEntity e) {
		Iterator<GameEntity> iter = e.entities().iterator();
		while (iter.hasNext()) {
			GameEntity f = iter.next();
		}
		
		return;
	}*/
}


// vraie si la plus proche entité est dans la direction