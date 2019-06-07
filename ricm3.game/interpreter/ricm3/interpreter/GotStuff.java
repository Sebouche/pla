package ricm3.interpreter;

import ricm3.game.GameEntity;

public class GotStuff extends ICondition {
	public GotStuff(){}
	
	@Override
	public boolean eval(GameEntity e) {
		return true;
	}
}
