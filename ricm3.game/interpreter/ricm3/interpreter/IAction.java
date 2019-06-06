package ricm3.interpreter;

import ricm3.game.GameEntity;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

import ricm3.parser.Ast.*;

public class IAction extends IExpression{
	
	IAction(){}
	
	boolean exec(Entity e){
		return true;
	}

	public boolean exec(GameEntity e) {
		return true;
	}
		
}
