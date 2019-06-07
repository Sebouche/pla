package ricm3.interpreter;

import ricm3.game.GameEntity;

public class Kamikaze extends IAction {

	public Kamikaze() {

	}

	@Override
	public boolean exec(GameEntity e) {
		if (act != null)
			return e.kamikaze() && act.exec(e);
		return e.kamikaze();
	}

}
