package ricm3.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import ricm3.game.Options;
import ricm3.interpreter.IAutomaton;

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

	List<ChunkList> chunklists;

	public SurfaceWorld(int radius, Model m) {
		super(m);
		chunklists = new LinkedList<ChunkList>();
		add(new Chunk(this, 0, 0, 2));
		new House(m_model, 64, 64, 2000, m_model.m_sprites.get("house"), this);
		Random r = new Random();
		int y;
		int x;
		do {
			x = (r.nextInt() % (2 * radius + 1)) - radius;
			y = (r.nextInt() % (2 * radius + 1)) - radius;
		} while ((x == 0) && (y == 0));
		add(new Chunk(this, x, y, 1));
		generateChunks(0, 0, radius);
		m_bgmfile = new File("sprites/bgm_surface.wav");
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

		public Spawner(int x, int y, Chunk c, BufferedImage[] sprites, World originWorld) {
			super(c.world.m_model, x, y, 100, sprites, null, originWorld);
			m_c = c;
			this.m_automate=new IAutomaton(m_model.m_automatons.get(0));
			m_c.world.m_entities.add(this);
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
					e = new Dog(m_model, m_x, m_y, m_model.m_sprites.get("dog"),
							new IAutomaton(m_model.m_automatons.get(0)), m_model.m_surfaceworld);
					break;
				case "Turtle":
					e = new Turtle(m_model, m_x, m_y, m_model.m_sprites.get("turtle"),
							new IAutomaton(m_model.m_automatons.get(0)), m_model.m_surfaceworld);
					break;
				case "Mouse":
					e = new Mouse(m_model, m_x, m_y, m_model.m_sprites.get("mouse"),
							new IAutomaton(m_model.m_automatons.get(0)), m_model.m_surfaceworld);
					break;
				case "Rabbit":
					e = new Rabbit(m_model, m_x, m_y, m_model.m_sprites.get("rabbit"),
							new IAutomaton(m_model.m_automatons.get(0)), m_model.m_surfaceworld);
					break;
				default:
					e = null;
					break;
				}
				if (e != null) {
					m_entities.add(e);
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
			m_x = x;
			m_y = y;
			world = w;
			sprite = world.m_model.m_sprites.get("grassbg")[0];
			if (type < 0) {
				Random r = new Random();
				type = (((r.nextInt()) % 10) + 1) / 10;
				if (type == 1) {
					spawn = new Spawner((r.nextInt() % (m_size - 64)) + 32 + m_x * 2048,
							(r.nextInt() % (m_size - 64)) + 32 + m_y * 2048, this, m_model.m_sprites.get("spawner"), world);
				}
			}

		}
	}

	public SurfaceWorld(Model model) {
		super(model);
	}

	@Override
	public void changeWorld() {
		m_model.m_currentworld = m_model.m_undergroundworld;
		m_model.m_player=m_model.m_undergroundplayer;
		m_model.m_player.m_x = 64;
		m_model.m_player.m_y = 640;
		m_model.m_player.m_originWorld = m_model.m_undergroundworld;
	}

	@Override
	public void step() {
		Iterator<GameEntity> iter = m_entities.iterator();
		while (iter.hasNext()) {
			GameEntity e = iter.next();
			e.step();
		}
	}

	@Override
	public void paint(Graphics g) {
		int cam_x = m_model.m_camera.m_watched.m_x;
		int cam_y = m_model.m_camera.m_watched.m_y;
		Iterator<GameEntity> iter = m_entities.iterator();
		while (iter.hasNext()) {
			GameEntity e = iter.next();
			Graphics g_child;
			g_child = g.create(e.m_x - cam_x + m_model.m_width / 2, e.m_y - cam_y + m_model.m_height / 2,
					(int) (Options.Entity_size * Options.Scale), (int) (Options.Entity_size * Options.Scale));
			e.paint(g_child);
			g_child.dispose();
		}
	}
}
