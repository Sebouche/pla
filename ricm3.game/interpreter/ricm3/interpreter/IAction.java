package ricm3.interpreter;

import ricm3.game.GameEntity;

public class IAction extends IExpression{
	
	IAction(){}

	public boolean exec(GameEntity e) {
		return true;
	}
		
}
