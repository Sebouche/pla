package ricm3.interpreter;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ricm3.game.GameEntity;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class IAutomaton {
	public String name;
	public IState current;
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
		IBehaviour b;
		while (iter.hasNext()) {
			b = iter.next();
			this.behaviours.add(new IBehaviour(b));
		}
	}

	public boolean step(GameEntity e) {
		// - selectionne le comportement en fonction de l'état courant
		// - effectue une transition
		// - met à jour l'état courant
		// - gère l'exception "aucune transition possible"
		IBehaviour b;
		IState target;
		Iterator<IBehaviour> iter=behaviours.iterator();
		while(iter.hasNext()) {
			b=iter.next();
			if(b.source.name.equals(this.current.name)) {
					target=b.step(e);
					if(target!=null) {
						this.current=target;
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
