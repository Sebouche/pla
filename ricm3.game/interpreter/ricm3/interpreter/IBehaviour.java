package ricm3.interpreter;
import java.util.Iterator;
import java.util.List;


/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class IBehaviour {
	IState source ;
	List<ITransition> transitions ;
	
	public IBehaviour(IState source, List<ITransition> transitions){
		this.source = source ; 
		this.transitions = transitions ;
	}
	
	public IBehaviour(IBehaviour behaviour) {
		this.source=new IState(behaviour.source);
		Iterator<ITransition> iter = behaviour.transitions.iterator();
		while (iter.hasNext()) {
			ITransition b = iter.next();
			this.transitions.add(new ITransition(b));
		}
	}
	
	IState step() {
		// - selectionne la première transition faisable
		// - lève une exception si aucune transition possible
		return null;// l'état cible de la transition choisie
	}
}
