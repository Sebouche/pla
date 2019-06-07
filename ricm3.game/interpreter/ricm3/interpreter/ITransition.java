package ricm3.interpreter;

import ricm3.game.GameEntity;
import ricm3.parser.Ast.Entity;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class ITransition {
	ICondition condition ;
	IAction action ;
	IState target ;
	
	public ITransition(ICondition condition, IAction action, IState target){
		this.condition = condition ;
		this.action = action ;
		this.target = target ;
	}
	
	public ITransition(ITransition transition) {
		this.condition=new ICondition(transition.condition);
		this.action=new IAction(transition.action);
		this.target=new IState(transition.target);
	}
	

	boolean feasible(GameEntity e) {
		// teste si la condition de la transition est satisfaite
		return condition.eval(e) ;
	}
	
	IState exec(GameEntity e) {
		// execute l'action
		// return l'état cible de la transition 
		action.exec(e);
		return target ;
	}
}
