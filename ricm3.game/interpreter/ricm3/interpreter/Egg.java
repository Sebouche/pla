package ricm3.interpreter;

import ricm3.game.GameEntity;

public class Egg extends IAction {

	public Egg() {

	}

	@Override
	public boolean exec(GameEntity e) {
		if (act != null)
			return e.egg() && act.exec(e);
		return e.egg();
	}

}
