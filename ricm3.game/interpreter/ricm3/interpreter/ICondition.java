package ricm3.interpreter;

import ricm3.parser.Ast.Condition;
import ricm3.parser.Ast.*;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class ICondition {

	ICondition(){}
	boolean eval(Entity e) { return true; } // à redéfinir dans chaque sous-classe

	public class True extends ICondition {
		True(){}
		boolean eval(Entity e) { return true; }
	}

	public class Cell extends ICondition {
		Direction direction ;
		Kind kind ;
		Distance distance ;
		
		Cell(Direction direction, Kind kind, Distance distance){
			this.direction = direction ;
			this.kind = kind ;
			this.distance = distance ;
		}
		
		Cell(Direction direction, Kind kind){
			this.direction = direction ;
			this.kind = kind ;
			this.distance = 1 ;
		}
		
		boolean eval(Entity e) { 
			return is_Kind(this.kind, this.direction, this.distance, e.position, e.map) ;
		}
	}
	
	public class GotPower extends ICondition {
		GotPower(){}
		boolean eval(Entity e) {
			return (e.power > 0) ;
		}
	}

}