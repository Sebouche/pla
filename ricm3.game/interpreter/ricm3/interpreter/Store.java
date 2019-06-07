package ricm3.interpreter;

import ricm3.game.GameEntity;

public class Store extends IAction {

	public Store() {

	}

	@Override
	public boolean exec(GameEntity e) {
		if (act != null)
			return e.store() && act.exec(e);
		return e.store();
	}

}
