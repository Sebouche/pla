package ricm3.interpreter;

import java.util.LinkedList;

import ricm3.game.GameEntity;

public class IExpression {

	public IExpression expr1;
	public IExpression expr2;
	public IOperator op;
	public IExpression act;

	public IExpression() {
		expr1 = null;
		expr2 = null;
		op = null;
		act = null;
	}

	public IExpression(IExpression expression) {
		if (expression != null) {
			expr1 = new IExpression(expression.expr1);
			expr2 = new IExpression(expression.expr2);
			op = new IOperator(expression.op);
			act = new IExpression(expression.act);
		}
		else {
			expr1 = null;
			expr2 = null;
			op = null;
			act = null;			
		}
	}

	public boolean eval(GameEntity e) {
		return true;
	}

}
