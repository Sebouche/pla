package ricm3.interpreter;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ricm3.game.GameEntity;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class IAutomaton {
	String name;
	IState current;
	List<IBehaviour> behaviours;

	public IAutomaton(String name, IState initial, List<IBehaviour> behaviours) {
		this.name = name;
		this.current = initial;
		this.behaviours = behaviours;
	}

	public IAutomaton(IAutomaton automaton) {
		this.name = automaton.name;
		this.current = new IState(automaton.current);
		this.behaviours = new LinkedList<IBehaviour>();
		Iterator<IBehaviour> iter = automaton.behaviours.iterator();
		while (iter.hasNext()) {
			IBehaviour b = iter.next();
			this.behaviours.add(new IBehaviour(b));
		}
	}

	public boolean step(GameEntity e) {
		// - selectionne le comportement en fonction de l'état courant
		// - effectue une transition
		// - met à jour l'état courant
		// - gère l'exception "aucune transition possible"
		Iterator<IBehaviour> iter=behaviours.iterator();
		while(iter.hasNext()) {
			IBehaviour b=iter.next();
			if(b.source.name.equals(current.name)) {
					IState target=b.step(e);
					if(target!=null) {
						current=target;
						return true;
					}
				}
			}
		return false; // true si une transition effectuée, false si aucune transition possible (=?=
						// mort de l'automate ?)
	}
	
	public String name() {
		return this.name;
	}
}
