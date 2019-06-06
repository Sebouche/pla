package ricm3.interpreter;

import ricm3.game.GameEntity;

public class ICondition extends IExpression {

	public IExpression expr1;
	public IExpression expr2;
	public IOperator op;

	public ICondition() {
		expr1 = null;
		expr2 = null;
		op = null;
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
