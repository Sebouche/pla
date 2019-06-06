package ricm3.interpreter;

import ricm3.game.GameEntity;
import ricm3.parser.Ast.*;

public class ICondition extends IExpression {

	public ICondition expr1;
	public ICondition expr2;
	public IOperator op;

	public ICondition() {
		expr1 = null;
		expr2 = null;
		op = null;
	}

	boolean eval(GameEntity e) {
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
