package ricm3.interpreter;

import ricm3.parser.Ast.Direction;

public class Move extends IAction {
	Direction direction ;
	
	Move(){
		// this.direction = ;// Front par défaut
	}
	
	Move(Direction direction){
		this.direction = direction ;
	}

	
/*	boolean exec(Entity e){
		e.move(this.direction) ;
	}*/
}