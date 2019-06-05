package ricm3.interpreter;

import ricm3.parser.Ast.Direction;
import ricm3.parser.Ast.Entity;

public class Closest extends ICondition{
	Entity e;
	Direction dir;
	
	public Closest(Entity e, Direction dir){
		this.e = e;
		this.dir = dir;
	}
	
//	boolean eval(GameEntity e) {
//	return ;
//	}
}

