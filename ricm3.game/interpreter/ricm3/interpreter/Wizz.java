package ricm3.interpreter;

import ricm3.game.GameEntity;

public class Wizz extends IAction {

	Direction dir;

	public Wizz() {
		this.dir = Direction.FRONT;
	}

	public Wizz(String str) {
		this.dir = Direction.strToDir(str);
	}

	@Override
	public boolean exec(GameEntity e) {
		if (act != null)
			return e.wizz(dir) && act.exec(e);
		return e.wizz(dir);
	}

}
