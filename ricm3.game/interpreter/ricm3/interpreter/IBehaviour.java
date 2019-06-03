package ricm3.interpreter;
import java.util.List;


/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class IBehaviour {
	IState source ;
	List<ITransition> transitions ;
	
	IBehaviour(IState source, List<ITransition> transitions){
		this.source = source ; 
		this.transitions = transitions ;
	}
	
	IState step() {
		// - selectionne la première transition faisable
		// - lève une exception si aucune transition possible
		return null;// l'état cible de la transition choisie
	}
}
