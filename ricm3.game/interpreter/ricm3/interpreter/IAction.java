package ricm3.interpreter;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

import ricm3.parser.Ast.*;

public class IAction extends IExpression{
	
	IAction(){}
	
	boolean exec(Entity e){
		return true;
	}
		
}
