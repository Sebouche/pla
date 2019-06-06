package ricm3.interpreter;

import ricm3.game.GameEntity;

public class GotPower extends ICondition {
	
	public GotPower() {
	}
	
	@Override
	public boolean eval(GameEntity e) {
		return (e.hps() > 0) ;
	}
}
