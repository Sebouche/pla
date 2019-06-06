package ricm3.interpreter;

import ricm3.game.GameEntity;

public class Closest extends ICondition{
	Type e;
	Direction dir;
	
	public Closest(String e, String dir){
		this.e = Type.strToType(e);
		this.dir = Direction.strToDir(dir);
	}
	
//	@Override	
//	public boolean eval(GameEntity e) {
//	return ;
//	}
}

