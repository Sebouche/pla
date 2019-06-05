package ricm3.interpreter;

import ricm3.parser.Ast.Direction;

public class MyDir extends IFunCall {
	Direction direction;
	
	public MyDir(Direction dir){
		this.direction = dir;
	}
	
//	boolean eval(GameEntity e) {
//	return ;
//	}
}

