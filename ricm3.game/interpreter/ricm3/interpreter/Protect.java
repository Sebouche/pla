package ricm3.interpreter;

import ricm3.game.GameEntity;

public class Protect extends IAction {

	Direction dir;

	public Protect() {
		this.dir = Direction.FRONT;
	}

	public Protect(String str) {
		this.dir = Direction.strToDir(str);
	}
	
	@Override
	public boolean exec(GameEntity e) {
		return e.protect(dir);
	}

}
