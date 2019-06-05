package ricm3.interpreter;

import ricm3.parser.Ast.Direction;

public class Hit extends IAction {
	Direction direction ;
	Integer power;
	
	Hit(Direction direction, Integer power){
		this.direction = direction ;
		this.power = power;
	}
	Hit(Direction direction){
		this.direction = direction ;
		this.power = 1 ; // valeur par défaut
	}
	Hit(){
	//	this.direction = Direction.Front // Front par défaut
		this.power = 1 ; // puissance par défaut
	}
	
/*		boolean exec(Entity e){
		e.hit(this.direction, max(e.power,this.power));
	}*/
}