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
		double entity_size = Options.Scale * Options.Entity_size;
		if ((y <= ge.y() + entity_size && entity_size + y >= ge.y()) && (x <= ge.x() + entity_size && entity_size + x >= ge.x())) {
			return true;
		}
		return false;
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
