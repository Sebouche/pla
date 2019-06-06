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

	@Override
	public boolean eval(GameEntity e) {
		Iterator<GameEntity> iter = e.entities().iterator();
		while (iter.hasNext()) {
			GameEntity f = iter.next();
			if(f.type() == type) {
				
			}
		}
		
		return false;
	}
}


// vraie si la plus proche entité est dans la direction