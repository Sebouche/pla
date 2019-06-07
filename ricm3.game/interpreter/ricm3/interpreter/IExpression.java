package ricm3.interpreter;


import ricm3.game.GameEntity;

public class IExpression {
	
	public IOperator op;
	
	public IExpression() {
		op = null;
	}

	public IExpression(IExpression expression) {
		if (expression != null) {
			op=expression.op;		
		}
	}

	public boolean eval(GameEntity e) {
		return true;
	}
	
	public boolean exec(GameEntity e) {
		return true;
	}

}
