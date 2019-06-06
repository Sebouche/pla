package ricm3.interpreter;

import ricm3.parser.Ast.*;

public class ICondition extends IExpression{

	public ICondition expr1;
	public ICondition expr2;
	public IOperator op;

	public ICondition() {
		expr1 = null;
		expr2 = null;
		op = null;
	}

	boolean eval(Entity e) {
		return true;
	} 
}
