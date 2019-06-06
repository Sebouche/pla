package ricm3.interpreter;

import ricm3.game.GameEntity;

public class Wait extends IAction{

	
	public Wait() {
		
	}
	
	boolean exec(GameEntity e) {
		return e.Wait();
	}
	
}
