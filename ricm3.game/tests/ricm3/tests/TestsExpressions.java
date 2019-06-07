package ricm3.tests;

import ricm3.game.*;
import ricm3.interpreter.*;

public class TestsExpressions {
	
	public static void ensure(boolean cond) {
		if (!cond)
			throw new RuntimeException("Failed assert.");
	}
	
	
	public static void main(String args[]) {
		testActions();
		testConditionsTrueMyDirGotPower();
		testConditionKey();
	//	testCondCell();
	}
	
	public static void testActions() {
		GameEntity e = new GameEntity(null, 0, 0, 0, null);
		IAction act = new Pop();
		act.act = new Wizz();
		ensure(act.exec(e));
	}
	
	public static void testConditionsTrueMyDirGotPower() {
		GameEntity e = new GameEntity(null, 0, 0, 0, null);
		ICondition cond = new ICondition();
		cond.op = new IOperator("&&");
		cond.expr1 = new True();
		cond.expr2 = new ICondition();
		cond.expr2.op = new IOperator("&&");
		ICondition cond2 = (ICondition) cond.expr2;
		cond2.expr1 = new ICondition();
		cond2.expr2 = new MyDir("N");
		ICondition cond3 = (ICondition) cond2.expr1;
		cond3.op = new IOperator ("&&");
		cond3.expr1 = new ICondition();
		cond3.expr2 = new ICondition();
		ICondition cond4 = (ICondition) cond3.expr2;
		cond4.op = new IOperator ("!");
		cond4.expr1 = new MyDir("S");
		ICondition cond5 = (ICondition) cond3.expr1;
		cond5.op = new IOperator ("!");
		cond5.expr1 = new GotPower();
		ensure(cond.eval(e));
	}
	
	public static void testConditionKey() {
		GameEntity e = new GameEntity(null, 0, 0, 0, null);
		ICondition cond = new KeyP("b");
		ICondition cond2 = new KeyP("a");
		ensure(!cond.eval(e) && cond2.eval(e));
	}
	
	/*public static void testCondCell() {
		GameEntity e = new GameEntity(null, 0, 0, 0, null);
		GameEntity e2 = new GameEntity(null, -15, -2, 0 ,null);
		GameEntity e3 = new GameEntity(null, -1, -1, 0, null);
		Cell cond = new Cell("O","A",10);
	//	ensure(cond.eval(e,e2) && !cond.eval(e, e3));
	}*/

}
