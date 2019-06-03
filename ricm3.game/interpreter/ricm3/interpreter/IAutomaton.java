package ricm3.interpreter;
import java.util.List;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class IAutomaton {
	String name;
	IState current ;
	List<IBehaviour> behaviours ;
	
	IAutomaton(String name, IState initial, List<IBehaviour> behaviours){
		this.name = name;
		this.current = initial ;
		this.behaviours = behaviours ;
	}
	
	boolean step() {
		// - selectionne le comportement en fonction de l'état courant
		// - effectue une transition
		// - met à jour l'état courant
		// - gère l'exception "aucune transition possible"
		return false ; // true si une transition effectuée, false si aucune transition possible (=?= mort de l'automate ?) 
	}
}
