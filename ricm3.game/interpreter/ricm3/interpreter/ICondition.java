package ricm3.interpreter;

import ricm3.game.GameEntity;
import ricm3.game.Options;

public class ICondition extends IExpression {

	public IExpression expr1;
	public IExpression expr2;
	public IOperator op;

	public ICondition() {
		expr1 = null;
		expr2 = null;
		op = null;
	}
	
	boolean isInside(GameEntity e, int x, int y) {
		return e.x() <= x && e.x() + Options.Entity_size * Options.Scale >= x && e.y() <= y
				&& e.y() + Options.Entity_size * Options.Scale >= y;
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
