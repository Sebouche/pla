package ricm3.interpreter;

import ricm3.game.GameEntity;
import ricm3.game.Options;

public class ICondition extends IExpression {

	public IExpression expr1;
	public IExpression expr2;

	public ICondition() {
		super();
		expr1 = null;
		expr2 = null;
	}

	public ICondition(ICondition condition) {
		super(condition);
		expr1 = condition.expr1;
		expr2 = condition.expr2;
	}

	boolean isInside(GameEntity ge, int x, int y) {
		return ge.distance(new GameEntity(null, x, y, 0, null, null, null)) <= 0;
	}

	@Override
	public boolean eval(GameEntity e) {
		if (op == null) {
			return expr1.eval(e);
		}
		if (op.operator == "!") {
			return !expr1.eval(e);
		}
		if (op.operator == "&&") {
			return expr1.eval(e) && expr2.eval(e);
		}
		if (op.operator == "||") {
			return expr1.eval(e) || expr2.eval(e);
		}
		return true;
	}
}
