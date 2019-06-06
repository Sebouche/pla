package ricm3.interpreter;

import ricm3.game.GameEntity;

public class Pick extends IAction {

	Direction dir;

	public Pick() {
		this.dir = Direction.FRONT;
	}

	public Pick(String str) {
		this.dir = Direction.strToDir(str);
	}
	
	boolean exec(GameEntity e) {
		return e.pick(dir);
	}
}
