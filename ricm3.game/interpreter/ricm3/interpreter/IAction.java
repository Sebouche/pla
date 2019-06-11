package ricm3.interpreter;

import ricm3.game.GameEntity;

public class IAction extends IExpression {

	public IExpression act;

	public IAction() {
		super();
		act = null;
	}

	public IAction(IAction action) {
		super(action);
		if (action != null)
			act = action.act;
	}

	@Override
	public boolean exec(GameEntity e) {
		if (act != null)
			return act.exec(e);
		return true;
	}

}
