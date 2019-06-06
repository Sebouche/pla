package ricm3.interpreter;

import ricm3.game.GameEntity;

public class Store extends IAction{

	public Store() {
		
	}
	
	boolean exec(GameEntity e) {
		return e.store();
	}
	
}
