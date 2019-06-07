
import java.util.Hashtable;
import java.util.Set;

public class Resource {

	Hashtable<Integer, Integer> ressource;

	public Resource() {

		ressource = new Hashtable<Integer, Integer>();
		ressource.put(1, 4);
		ressource.put(2, 10);
		ressource.put(3, 2);
		ressource.put(4, 5);
		ressource.put(5, 3);
	}

	public Set<Integer> Quantity() {
		return ressource.keySet();
	}

	public void Add(int key, int value) {
		if (ressource.containsKey(key))
			ressource.put(key, ressource.get(key) + value);

		else if (!(ressource.containsKey(key)))
			ressource.put(key, value);
	}

	public boolean Exist(int cle, int value) {
		if (ressource.containsKey(cle) && ressource.get(cle) >= value) {
			return true;
		}
		else
		return false;

	}
	 public boolean allExist(int cle[],int value[]) {
		 for(int i = 0; i<cle.length;i++) {
			 if (!Exist(cle[i],value[i])) {
			 return false;
			 } 
		 }
		return true;
		 
	 }

	public void construct(int toConstruct, int cle[], int value[]) {
		/* if the wanted construction doesn't exist in the resources table */
		if (allExist(cle, value)) {
			for (int i = 0; i < cle.length; i++) {
				/* if we have all resources needed we use them and subtract their quantity */
				if (Exist(cle[i], value[i])) {
					ressource.put(cle[i], (ressource.get(cle[i]) - value[i]));
}
				if (ressource.get(cle[i])==0)
					ressource.remove(cle[i]);
			}
			/* we add the new construction to the table if doesn't exist */
			if (!(ressource.containsKey(toConstruct))) {
			Add(toConstruct, 1);

		}

			else if (ressource.containsKey(toConstruct)) {
			ressource.put(toConstruct, (ressource.get(toConstruct) + 1));
		}

	}
	}
}