package ricm3.interpreter;

import ricm3.game.GameEntity;

public class IAction extends IExpression{
	
	public IAction(){
		super();
	}

	public boolean exec(GameEntity e) {
		return true;
	}
		
}
