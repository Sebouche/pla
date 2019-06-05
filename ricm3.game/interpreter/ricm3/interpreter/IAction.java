package ricm3.interpreter;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

import ricm3.parser.Ast.*;

public class IAction {
	
	IAction(){}
	
	boolean exec(Entity e){
		return true;
	}
	
	
	
	
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
	
}
