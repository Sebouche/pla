package ricm3.interpreter;

import ricm3.game.GameEntity;

public class Get extends IAction {

	public Get() {
		
	}
	
	boolean exec(GameEntity e) {
		return e.get();
	}
	
}
