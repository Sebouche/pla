package ricm3.interpreter;

import ricm3.game.GameEntity;

public class IAction extends IExpression{
	

	public IExpression act;
	
	public IAction(){
		super();
		act = null;
	}

	@Override
	public boolean exec(GameEntity e) {
		return act.exec(e);
	}
		
}
