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
		if (act != null)
			return e.protect(dir) && act.exec(e);
		return e.protect(dir);
	}

}
