package ricm3.interpreter;

import ricm3.game.GameEntity;

public class Pop extends IAction {

	Direction dir;

	public Pop() {
		this.dir = Direction.FRONT;
	}

	public Pop(String dir) {
		this.dir = Direction.strToDir(dir);
	}

	@Override
	public boolean exec(GameEntity e) {
		if (act != null)
			return e.pop(dir) && act.exec(e);
		return e.pop(dir);
	}

}
