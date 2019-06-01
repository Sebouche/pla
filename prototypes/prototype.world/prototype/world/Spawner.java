package prototype.world;

public class Spawner extends Entity {
	Chunk m_c;

	public Spawner(int x, int y, Chunk c) {
		super(x,y,1,1);
		m_c = c;
	}
}
