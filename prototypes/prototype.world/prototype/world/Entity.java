package prototype.world;

public class Entity {
	int m_x, m_y, m_w, m_h;

	public Entity(int x, int y, int w, int h) {
		m_x = x;
		m_y = y;
		m_w = w;
		m_h = h;
	}

	double distance(Entity e) {
		return Math.sqrt((m_x - e.m_x) * (m_x - e.m_x) + (m_y - e.m_y) * (m_y - e.m_y));
	}
}
