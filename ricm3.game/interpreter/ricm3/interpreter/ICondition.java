package ricm3.interpreter;

import java.util.LinkedList;
import java.util.List;

import ricm3.parser.Ast.*;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class ICondition {

	public List<IOperator> operators;
	public List<IFunCall> funcalls;
	
	public ICondition(){
		this.operators = new LinkedList<IOperator>();
		this.funcalls = new LinkedList<IFunCall>();
	}
	
	boolean eval(Entity e) { return true; } // à redéfinir dans chaque sous-classe
	
}


// Ajouter une classe IFunCall et IOperator
// Ajouter des champs list IFunCall et list IOperator dans ICondition
// ICondition n'extend plus true, cell, ...