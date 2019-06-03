package prototype.ElectricNetwork;

public class Entity {
	int x, y, w, h;

	public Entity(int x1, int y1, int w1, int h1) {
		x = x1;
		y = y1;
		w = w1;
		h = h1;
	}

	double distance(Entity e) {
		return Math.sqrt((x - e.x) * (x - e.x) + (y - e.y) * (y - e.y));
	}
}
