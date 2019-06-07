package ricm3.interpreter;

import ricm3.game.GameEntity;

public class Power extends IAction{

	public Power() {
		
	}
	
	@Override
	public boolean exec(GameEntity e) {
		return e.power();
	}
	
}
