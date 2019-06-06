package ricm3.interpreter;

import ricm3.game.GameEntity;

public class MyDir extends ICondition {
	Direction direction;

	public MyDir(String dir) {
		this.direction = Direction.strToDir(dir);
	}

	boolean eval(GameEntity e) {
		return true;
	}
}
