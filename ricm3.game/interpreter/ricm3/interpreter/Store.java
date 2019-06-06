package ricm3.interpreter;

import ricm3.game.GameEntity;

public class Store extends IAction{

	public Store() {
		
	}
	
	@Override
	public boolean exec(GameEntity e) {
		return e.store();
	}
	
}
