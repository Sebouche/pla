package ricm3.interpreter;

public class IOperator {

	String operator;

	public IOperator(String op) {
		if (op.equals("!")) {
			this.operator = op;
		}
		else if (op.equals("&")) {
			this.operator = "&&";
		}
		else if (op.equals("/")) {
			this.operator = "||";
		}
		else this.operator = op;
	}

	public IOperator(IOperator operator) {
		if (operator != null) {
			this.operator = operator.operator;
		}
	}

	
	public String Op() {
		return operator;
	}

}
