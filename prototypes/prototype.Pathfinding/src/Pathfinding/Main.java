package Pathfinding;

import java.util.Iterator;
import java.util.LinkedList;

public class Main {
	
	public static void main(String[] args) {
		LinkedList<Entity> t = new LinkedList<Entity>();
		t.add(new Entity(0, 0, 1, 1, new LinkedList<Entity>()));
		t.add(new Entity(12, 1, 1, 1, new LinkedList<Entity>()));
		Entity e = new Entity(6, 2, 1, 1, t);
		LinkedList<Point> l = e.aggro();
		Iterator<Point> iter = l.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next().toString());
		}
	}
	
}