package ricm3.interpreter;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class IState {
	int id;
	public String name;
	// Le nom de l'état (Waiting, Hungry, Angry, ...) peut vous servir à adapter la
	// représentation de l'entité à son humeur.

	public IState(String name) {
		this.name = name;
	}

	public IState(IState state) {
		this.name = state.name;
	}
}
