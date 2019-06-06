package ricm3.interpreter;

import ricm3.game.GameEntity;

public class Throw extends IAction {

	Direction dir;

	public Throw() {
		this.dir = Direction.FRONT;
	}

	public Throw(String str) {
		this.dir = Direction.strToDir(str);
	}

	@Override
	public boolean exec(GameEntity e) {
		return e.Throw(dir);
	}
	
}
