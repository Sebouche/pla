package world;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Chunk {
	int m_x, m_y, m_size;
	BufferedImage sprite;
	int type; // 0 = vide, 1 = spawner, 2 = maison
	Spawner spawn;

	public Chunk(int x, int y, int t) {
		type = t;
		if (type < 0) {
			Random r = new Random();
			type = (((r.nextInt()) % 10) + 1) / 10;
			if (type == 1) {
				spawn = new Spawner((r.nextInt() % (m_size - 64)) + 32, (r.nextInt() % (m_size - 64)) + 32, this);
			}
		}
		m_x = x;
		m_y = y;
	}
}
