package ricm3.interpreter;

import ricm3.game.GameEntity;

public class Egg extends IAction{

	public Egg() {
		
	}
	
	public boolean exec(GameEntity e) {
		return e.egg();
	}
	
}
