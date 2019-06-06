package ricm3.interpreter;

import ricm3.game.GameEntity;

public class True extends ICondition {
	public True() {
	}

	public boolean eval(GameEntity e) {
		return true;
	}
}
