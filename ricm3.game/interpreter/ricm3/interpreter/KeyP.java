package ricm3.interpreter;

import ricm3.game.GameEntity;

public class KeyP extends ICondition{
	
	Keys k;
	
	public KeyP(String k){
		this.k = Keys.strToKeys(k);
	}
	
	@Override
	public boolean eval(GameEntity e) {
		if(e.m_model.m_keys.contains(k)) {
		return true;}
		return false;
	}
}

