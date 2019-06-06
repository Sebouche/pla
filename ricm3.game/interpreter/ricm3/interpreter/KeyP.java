package ricm3.interpreter;

import ricm3.game.GameEntity;

public class KeyP extends ICondition{
	
	Keys k;
	
	public KeyP(String k){
//		this.k = strToKeys(k);
	}
	
	@Override
	public boolean eval(GameEntity e) {
		return e.keys().contains(k);
	}
}

