package ricm3.game;

import game.blocks.*;
import java.util.Hashtable;
import java.util.Set;

public class Fabrication {

	Hashtable<Class, Integer> ressource;

	public Fabrication() {

		ressource = new Hashtable<Class, Integer>();
		ressource.put(Coal.class, 0);
		ressource.put(Copper.class, 0);
		ressource.put(Iron.class, 0);
		ressource.put(Uranium.class, 0);
		ressource.put(Ladder.class, 0);
		ressource.put(Stone.class, 0);
		ressource.put(Dirt.class, 0);

	}

	public Hashtable<Class, Integer> ressource() {
		return ressource;
	}

	public void put(GameEntity cle, int quantity) {
		ressource.put(cle.getClass(), quantity);
	}

	public int get(GameEntity cle) {
		return ressource.get(cle.getClass());
	}

	public Set<Class> Quantity() {
		return ressource.keySet();
	}

	
	public boolean Exist(Class cle, int value) {
		if (ressource.containsKey(cle) && ressource.get(cle) >= value) {
			return true;
		} else
			return false;

	}

	public boolean allExist(Class cle[], int value[]) {
		for (int i = 0; i < cle.length; i++) {
			if (!Exist(cle[i], value[i])) {
				return false;
			}
		}
		return true;

	}

	public void construct( Class cle[], int value[]) {
		/* if the wanted construction doesn't exist in the resources table */
		if (allExist(cle, value)) {
			for (int i = 0; i < cle.length; i++) {
				/* if we have all resources needed we use them and subtract their quantity */
				if (Exist(cle[i], value[i])) {
					ressource.put(cle[i], (ressource.get(cle[i]) - value[i]));
				}

			}
		}

	}
}
