package ricm3.interpreter;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ricm3.game.GameEntity;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class IBehaviour {
	public IState source;
	List<ITransition> transitions;

	public IBehaviour(IState source, List<ITransition> transitions) {
		this.source = source;
		this.transitions = transitions;
	}

	public IBehaviour(IBehaviour behaviour) {
		this.source = new IState(behaviour.source);
		this.transitions = new LinkedList<ITransition>();
		Iterator<ITransition> iter = behaviour.transitions.iterator();
		while (iter.hasNext()) {
			ITransition b = iter.next();
			this.transitions.add(new ITransition(b));
		}
	}

	IState step(GameEntity e) {
		// - selectionne la première transition faisable
		// - lève une exception si aucune transition possible
		Iterator<ITransition> iter = transitions.iterator();
		while (iter.hasNext()) {
			ITransition t = iter.next();
			if (t.feasible(e)) {
				t.exec(e);
				return t.target;
			}
		}
		return null;// l'état cible de la transition choisie
	}
}
