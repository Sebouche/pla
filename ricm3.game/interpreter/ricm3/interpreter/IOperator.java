package ricm3.interpreter;

public class IOperator {

	String operator;

	public IOperator(String op) {
		if (op == "!") {
			this.operator = op;
		}
		if (op == "&") {
			this.operator = "&&";
		}
		if (op == "/") {
			this.operator = "||";
		}
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
