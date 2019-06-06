package ricm3.interpreter;

import ricm3.game.GameEntity;

public class Kamikaze extends IAction{

	public Kamikaze() {
		
	}
	
	boolean exec(GameEntity e) {
		return e.kamikaze();
	}
	
}
