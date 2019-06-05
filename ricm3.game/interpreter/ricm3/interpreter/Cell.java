package ricm3.interpreter;

import ricm3.parser.Ast.Direction;

public class Cell extends ICondition {
	Direction direction ;
	String kind ;
	int distance ;
	
	public Cell(Direction direction, String kind, int distance){
		this.direction = direction ;
		this.kind = kind ;
		this.distance = distance ;
	}
	
	public Cell(Direction direction, String kind){
		this.direction = direction ;
		this.kind = kind ;
		this.distance = 1 ;
	}
	
//	boolean eval(GameEntity e) { 
//		return is_Kind(this.kind, this.direction, this.distance, e.position, e.map) ;
//	}
}
