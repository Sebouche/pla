package ricm3.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import ricm3.game.Options;

public class SurfaceWorld extends World {
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

	List<GameEntity> entities;
	List<ChunkList> chunklists;

	public SurfaceWorld(int radius, Model m) {
		super(m);
		entities = new LinkedList<GameEntity>();
		chunklists = new LinkedList<ChunkList>();
		add(new Chunk(this, 0, 0, 2));
		Random r = new Random();
		int y;
		int x;
		do {
			x = (r.nextInt() % (2 * radius + 1)) - radius;
			y = (r.nextInt() % (2 * radius + 1)) - radius;
		} while ((x == 0) && (y == 0));
		add(new Chunk(this, x, y, 1));
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
					add(new Chunk(this, i, j, -1));
				}
			}
		}
	}

	private class Spawner extends GameEntity {
		Chunk m_c;

		public Spawner(int x, int y, Chunk c) {
			super(c.world.m_model, x, y, 1);
			m_c = c;
			m_sprites = m_model.m_sprites.get("Spawner");
			c.world.entities.add(this);
		}

		@Override
		public boolean egg() {
			Random r = new Random();
			if ((r.nextInt() % 100) == 0) {
				int type;
				int i = 0;
				type = r.nextInt() % 100;
				while (type >= Options.spawnerProba[i]) {
					type -= Options.spawnerProba[i];
					i++;
				}
				GameEntity e;
				switch (Options.spawnerType[i]) {
				case "Dog":
					e = new Dog(m_model, m_x, m_y);
					break;
				case "Turtle":
					e = new Turtle(m_model, m_x, m_y);
					break;
				case "Mouse":
					e = new Mouse(m_model, m_x, m_y);
					break;
				case "Rabbit":
					e = new Rabbit(m_model, m_x, m_y);
					break;
				default:
					e = null;
					break;
				}
				if (e != null) {
					entities.add(e);
					return true;
				}
			}
			return false;
		}
	}

	private class Chunk {
		int m_x, m_y, m_size;
		int type; // 0 = vide, 1 = spawner, 2 = maison
		Spawner spawn;
		SurfaceWorld world;
		BufferedImage sprite;

		public Chunk(SurfaceWorld w, int x, int y, int t) {
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
			world = w;
			sprite = world.m_model.m_sprites.get("Chunk")[0];
		}
	}

	public SurfaceWorld(Model model) {
		super(model);
	}

	@Override
	public void step() {
	}

	@Override
	public void paint(Graphics g) {
	}
}
