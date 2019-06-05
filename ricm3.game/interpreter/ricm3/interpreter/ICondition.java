package ricm3.interpreter;

import ricm3.parser.Ast.Condition;
import ricm3.parser.Ast.*;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class ICondition {

	ICondition(){}
	boolean eval(Entity e) { return true; } // à redéfinir dans chaque sous-classe

	public class True extends ICondition {
		True(){}
/*		boolean eval(GameEntity e) { 
			return true; 
		}*/
	}
	
	public class KeyP extends ICondition{
		
		Key k;
		
		KeyP(Key k){
			this.k = k;
		}
		
/*		boolean eval(GameEntity e) {
			
		}*/
	}
	
	public class MyDir extends ICondition {
		Direction direction;
		
		MyDir(Direction dir){
			this.direction = dir;
		}
	}

	public class Cell extends ICondition {
		Direction direction ;
		Kind kind ;
		int distance ;
		
		Cell(Direction direction, Kind kind, int distance){
			this.direction = direction ;
			this.kind = kind ;
			this.distance = distance ;
		}
		
		Cell(Direction direction, Kind kind){
			this.direction = direction ;
			this.kind = kind ;
			this.distance = 1 ;
		}
		
//		boolean eval(GameEntity e) { 
//			return is_Kind(this.kind, this.direction, this.distance, e.position, e.map) ;
//		}
	}
	
	public class Closest extends ICondition{
		Entity e;
		Direction dir;
		
		Closest(Entity e, Direction dir){
			this.e = e;
			this.dir = dir;
		}
		
//		boolean eval(GameEntity e) {
//		return ;
//		}
	}
	
	public class GotPower extends ICondition {
		GotPower(){}
//		boolean eval(GameEntity e) {
//			return (e.power > 0) ;
//		}
	}
	
	public class GotStuff extends ICondition {
		GotStuff(){}
		
//		boolean eval(GameEntity e) {
//		return ;
//		}
	}

}
