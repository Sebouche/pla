package ricm3.interpreter;

import ricm3.game.GameEntity;

public class Closest extends ICondition{
	String e;
	Direction dir;
	
	public Closest(String e, String dir){
		this.e = e;
		this.dir = Direction.strToDir(dir);
	}
	
//	@Override	
//	public boolean eval(GameEntity e) {
//	return ;
//	}
}

