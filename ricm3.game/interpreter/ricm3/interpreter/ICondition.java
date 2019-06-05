package ricm3.interpreter;

import ricm3.parser.Ast.Condition;
import ricm3.parser.Ast.*;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class ICondition {

	ICondition(){}
	boolean eval(Entity e) { return true; } // à redéfinir dans chaque sous-classe
	
}
