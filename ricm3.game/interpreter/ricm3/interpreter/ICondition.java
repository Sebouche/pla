package ricm3.interpreter;

import ricm3.parser.Ast.*;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

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
