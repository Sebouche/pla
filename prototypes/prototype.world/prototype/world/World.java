package prototype.world;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class World {
	private class ChunkList {
		List<Chunk> chunks;
		int m_y;

		ChunkList(int y_p) {
			chunks = new LinkedList<Chunk>();
			this.m_y = y_p;
		}

		void add(Chunk c) {
			chunks.add(c);
		}
	}

	List<ChunkList> chunklists;

	public World(int radius) {
		chunklists = new LinkedList<ChunkList>();
		add(new Chunk(0, 0, 2));
		Random r = new Random();
		int y;
		int x;
		do {
			x = (r.nextInt() % (2 * radius + 1)) - radius;
			y = (r.nextInt() % (2 * radius + 1)) - radius;
		} while ((x == 0) && (y == 0));
		add(new Chunk(x, y, 1));
		generateChunks(0, 0, radius);
	}

	public void add(Chunk c) {
		Iterator<ChunkList> iter = chunklists.iterator();
		ChunkList cl;
		while (iter.hasNext()) {
			cl = iter.next();
			if (cl.m_y == c.m_y) {
				cl.add(c);
				return;
			}
		}
		iter = null;
		cl = new ChunkList(c.m_y);
		chunklists.add(cl);
		cl.add(c);
	}

	public Chunk getChunk(int x, int y) {
		Iterator<ChunkList> iter1 = chunklists.iterator();
		ChunkList cl;
		Chunk c;
		while (iter1.hasNext()) {
			cl = iter1.next();
			if (cl.m_y == y) {
				Iterator<Chunk> iter2 = cl.chunks.iterator();
				while (iter2.hasNext()) {
					c = iter2.next();
					if (c.m_x == x) {
						return c;
					}
				}
			}
		}
		return null;
	}

	public void generateChunks(int x, int y, int dist) {
		for (int i = x - dist; i <= x + dist; i++) {
			for (int j = y - dist; j <= y + dist; j++) {
				if (getChunk(i, j) == null) {
					add(new Chunk(i, j, -1));
				}
			}
		}
	}
}
