package ricm3.interpreter;

import ricm3.game.GameEntity;

public class ICondition extends IExpression {

	public ICondition expr1;
	public ICondition expr2;
	public IOperator op;

	public ICondition() {
		expr1 = null;
		expr2 = null;
		op = null;
	}

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
