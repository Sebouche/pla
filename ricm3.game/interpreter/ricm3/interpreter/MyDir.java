package ricm3.interpreter;

import ricm3.game.GameEntity;

public class MyDir extends ICondition {
	Direction direction;

	public MyDir(String dir) {
		this.direction = Direction.strToDir(dir);
	}

	@Override
	public boolean eval(GameEntity e) {
		return e.dir() == direction;
	}
}
