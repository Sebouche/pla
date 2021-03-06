package ricm3.game;

import java.awt.Graphics;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import com.sun.tools.javac.util.ArrayUtils;

import game.blocks.*;
import ricm3.interpreter.*;

public class UndergroundWorld extends World {

	public GameEntity[][] m_grid;
	Random m_rand = new Random();

	public UndergroundWorld(Model model) {
		super(model);
		m_grid = new GameEntity[60][60];
		generate_level();
		m_bgmfile = new File("sprites/bgm_underground.wav");
		// m_entities
		// .add(new Turtle(m_model, 128, 640, m_model.m_sprites.get("turtle"),
		// m_model.m_automatons.get(1), this));

	}

	void generate_level() {
		int randint;
		// Creation tableau
		for (int i = 0; i < 60; i++) {
			for (int j = 0; j < 60; j++) {
				m_grid[i][j] = null;
			}
		}
		// Generation nuages
		for (int i = 0; i < 15; i++) {
			int rand_x = m_rand.nextInt(60);
			int rand_y = m_rand.nextInt(4);
			Block b = new Block(m_model, (int) (rand_x * Options.Entity_size * Options.Scale),
					(int) ((rand_y + 5) * Options.Entity_size * Options.Scale), -1, m_model.m_sprites.get("Block"),
					new IAutomaton(Options.Entities.get("Block")), this);
			b.set_idsprite(8);
			m_entities.add(b);
		}
		// Generation maison
		for (int i = 9; i <= 10; i++) {
			for (int j = 0; j <= 2; j++) {
				Block b;
				if (i == 10 && j == 2) {
					b = new Gate(m_model, (int) (j * Options.Entity_size * Options.Scale),
							(int) (i * Options.Entity_size * Options.Scale), -1, m_model.m_sprites.get("House"),
							new IAutomaton(Options.Entities.get("Block")), this);
				} else {
					b = new Block(m_model, (int) (j * Options.Entity_size * Options.Scale),
							(int) (i * Options.Entity_size * Options.Scale), -1, m_model.m_sprites.get("House"),
							new IAutomaton(Options.Entities.get("Block")), this);
				}
				b.set_idsprite((i - 6) * 3 + j);
				m_grid[i][j] = b;
				if (i == 10 && j == 1) {
					b.set_collision(false);
				}
			}
		}
		// Generation herbe
		for (int j = 0; j < 60; j++) {
			if (j <= 2) {
				Block b = new Block(m_model, (int) (j * Options.Entity_size * Options.Scale),
						(int) (11 * Options.Entity_size * Options.Scale), -1, m_model.m_sprites.get("Block"),
						new IAutomaton(Options.Entities.get("Block")), this);
				b.set_idsprite(6);
				m_grid[11][j] = b;
			} else {
				Block b = new Block(m_model, (int) (j * Options.Entity_size * Options.Scale),
						(int) (11 * Options.Entity_size * Options.Scale), -1, m_model.m_sprites.get("Block"),
						new IAutomaton(Options.Entities.get("Block")), this);
				b.set_idsprite(1);
				m_grid[11][j] = b;
			}
		}
		m_grid[11][1] = new Ladder(m_model, Math.floorMod((1), 60) * (int) (Options.Scale * Options.Entity_size),
				11 * (int) (Options.Scale * Options.Entity_size), 100, m_model.m_sprites.get("Block"),
				new IAutomaton(Options.Entities.get("Block")), this);
		// Generation minerais
		for (int i = 12; i < 60; i++) {
			for (int j = 0; j < 60; j++) {
				if (m_grid[i][j] == null) {
					randint = m_rand.nextInt(1001);
					if (randint >= 0 && randint < 850) {
						if (m_rand.nextInt(40) >= 20) {
							Block b = new Stone(m_model, (int) (j * Options.Entity_size * Options.Scale),
									(int) (i * Options.Entity_size * Options.Scale), 600,
									m_model.m_sprites.get("Block"), new IAutomaton(Options.Entities.get("Block")),
									this);
							m_grid[i][j] = b;
						} else {
							Block b = new Dirt(m_model, (int) (j * Options.Entity_size * Options.Scale),
									(int) (i * Options.Entity_size * Options.Scale), 300,
									m_model.m_sprites.get("Block"), new IAutomaton(Options.Entities.get("Block")),
									this);
							m_grid[i][j] = b;
						}
					}
					if (randint >= 850 && randint < 950) {
						int randint2 = m_rand.nextInt(3);
						Block b;
						if (randint2 == 2) {
							b = new Copper(m_model, (int) (j * Options.Entity_size * Options.Scale),
									(int) (i * Options.Entity_size * Options.Scale), 700,
									m_model.m_sprites.get("Block"), new IAutomaton(Options.Entities.get("Block")),
									this);
							m_grid[i][j] = b;
						} else if (randint2 == 1) {
							b = new Iron(m_model, (int) (j * Options.Entity_size * Options.Scale),
									(int) (i * Options.Entity_size * Options.Scale), 800,
									m_model.m_sprites.get("Block"), new IAutomaton(Options.Entities.get("Block")),
									this);
							m_grid[i][j] = b;
						} else {
							b = new Coal(m_model, (int) (j * Options.Entity_size * Options.Scale),
									(int) (i * Options.Entity_size * Options.Scale), 500,
									m_model.m_sprites.get("Block"), new IAutomaton(Options.Entities.get("Block")),
									this);
							m_grid[i][j] = b;
						}
					}
					if (randint >= 950 && randint < 999) {
						m_grid[i][j] = null;
					}
					if (randint >= 999 && i >= 30) {
						m_grid[i][j] = new Uranium(m_model, (int) (j * Options.Entity_size * Options.Scale),
								(int) (i * Options.Entity_size * Options.Scale), 900, m_model.m_sprites.get("Block"),
								new IAutomaton(Options.Entities.get("Block")), this);
					}
					if (randint >= 1000 && i <= 58) {
						Water w = new Water(m_model, (int) (j * (Options.Entity_size * Options.Scale)),
								(int) (i * (Options.Entity_size * Options.Scale)), m_rand.nextInt(12),
								m_model.m_sprites.get("Block"), Options.Entities.get("Water"), this);
						w.m_collision = false;
						m_grid[i][j] = w;

					}
				}
			}
		}
	}

	public void concatenate() {
		System.out.println("generate");
		int randint;
		GameEntity[][] grid2 = new GameEntity[60][60];
		for (int i = 0; i < 60; i++) {
			for (int j = 0; j < 60; j++) {
				grid2[i][j] = null;
			}
		}
		for (int i = 0; i < 60; i++) {
			for (int j = 0; j < 60; j++) {
				if (grid2[i][j] == null) {
					randint = m_rand.nextInt(1001);
					if (randint >= 0 && randint < 850) {
						if (m_rand.nextInt(40) >= 20) {
							Block b = new Stone(m_model,
(int) (Options.Entity_size * Options.Scale * (m_grid.length +j)),
									(int) (i * Options.Entity_size * Options.Scale), 600,
									m_model.m_sprites.get("Block"), new IAutomaton(Options.Entities.get("Block")),
									this);
							grid2[i][j] = b;
						} else {
							Block b = new Dirt(m_model,
									(int) (j * Options.Entity_size * Options.Scale)
											+ (int) (Options.Entity_size * Options.Scale * (m_grid.length +j)),
									(int) (i * Options.Entity_size * Options.Scale), 300,
									m_model.m_sprites.get("Block"), new IAutomaton(Options.Entities.get("Block")),
									this);
							grid2[i][j] = b;
						}
					}
					if (randint >= 850 && randint < 950) {
						int randint2 = m_rand.nextInt(3);
						Block b;
						if (randint2 == 2) {
							b = new Copper(m_model,
									(int) (j * Options.Entity_size * Options.Scale)
											+ (int) (Options.Entity_size * Options.Scale * (m_grid.length +j)),
									(int) (i * Options.Entity_size * Options.Scale), 700,
									m_model.m_sprites.get("Block"), new IAutomaton(Options.Entities.get("Block")),
									this);
							grid2[i][j] = b;
						} else if (randint2 == 1) {
							b = new Iron(m_model,
									(int) (j * Options.Entity_size * Options.Scale)
											+ (int) (Options.Entity_size * Options.Scale * (m_grid.length +j)),
									(int) (i * Options.Entity_size * Options.Scale), 800,
									m_model.m_sprites.get("Block"), new IAutomaton(Options.Entities.get("Block")),
									this);
							grid2[i][j] = b;
						} else {
							b = new Coal(m_model,
									(int) (j * Options.Entity_size * Options.Scale)
											+ (int) (Options.Entity_size * Options.Scale * (m_grid.length +j)),
									(int) (i * Options.Entity_size * Options.Scale), 500,
									m_model.m_sprites.get("Block"), new IAutomaton(Options.Entities.get("Block")),
									this);
							grid2[i][j] = b;
						}
					}
					if (randint >= 950 && randint < 999) {
						grid2[i][j] = null;
					}
					if (randint >= 999 && i >= 30) {
						grid2[i][j] = new Uranium(m_model,
								(int) (j * Options.Entity_size * Options.Scale)
										+ (int) (Options.Entity_size * Options.Scale * (m_grid.length +j)),
								(int) (i * Options.Entity_size * Options.Scale), 900, m_model.m_sprites.get("Block"),
								new IAutomaton(Options.Entities.get("Block")), this);
					}
					if (randint >= 1000 && i <= 58) {
						Water w = new Water(m_model,
								(int) (j * (Options.Entity_size * Options.Scale))
										+ (int) (Options.Entity_size * Options.Scale * (m_grid.length +j)),
								(int) (i * Options.Entity_size * Options.Scale), m_rand.nextInt(12),
								m_model.m_sprites.get("Block"), Options.Entities.get("Water"), this);
						w.m_collision = false;
						grid2[i][j] = w;

					}
				}
			}
		}
		GameEntity[][] output = new GameEntity[m_grid.length + 60][60];

		// copie du premier tableau
		System.arraycopy(m_grid, 0, output, 0, m_grid.length);
		// copie du deuxième tableau tableau
		System.arraycopy(grid2, 0, output, m_grid.length, grid2.length);
		m_grid = output;
	}

	@Override
	public void changeWorld() {
		// A appeller lorsque le joueur passe par la porte
		m_model.m_player.m_x = 64;
		m_model.m_player.m_y = 640;
		m_model.m_currentworld = m_model.m_surfaceworld;
		m_model.m_surfaceplayer.blocs = m_model.m_player.blocs;
		m_model.m_player = m_model.m_surfaceplayer;
		m_model.m_player.m_x = 64;
		m_model.m_player.m_y = 193;
		m_model.m_player.m_dy = 0;
		m_model.m_camera.m_watched = m_model.m_player;
		m_model.m_surfaceworld.m_allies.add(m_model.m_surfaceplayer);
		m_model.m_undergroundworld.m_allies.remove(m_model.m_undergroundplayer);
		super.changeWorld();
	}

	@Override
	public void step() {

		// step des blocs
		int entity_size = (int) (Options.Scale * Options.Entity_size);
		int distance = 20;
		int pos_x = (int) (m_model.m_player.m_x / entity_size);
		int pos_y = (int) (m_model.m_player.m_y / entity_size);
		if (pos_y + distance >= m_grid.length) {
			concatenate();
		}
		for (int i = -distance; i <= distance; i++) {
			for (int j = -distance; j <= distance; j++) {
				if ((pos_y + i) >= 0 && m_grid[pos_y + i][Math.floorMod((pos_x + j), 60)] != null) {
					m_grid[pos_y + i][Math.floorMod((pos_x + j), 60)].step();
				}
			}
		}

		// step des entit�s
		Iterator<GameEntity> iter = m_entities.iterator();
		GameEntity E;
		while (iter.hasNext()) {
			E = iter.next();
			E.step();
		}

		iter = m_tmprm.iterator();
		while (iter.hasNext()) {
			GameEntity e = iter.next();
			m_entities.remove(e);
		}
		m_tmprm = new LinkedList<GameEntity>();

		if (!(m_model.m_currentworld instanceof UndergroundWorld)) {
			if (m_entities.contains(m_model.m_player)) {
				m_entities.remove(m_model.m_player);
			}
		} else {
			if (!m_entities.contains(m_model.m_player)) {
				m_entities.add(m_model.m_player);
			}
		}

	}

	@Override
	public void paint(Graphics g) {
		int distance = 20;
		int cam_x = m_model.m_camera.m_watched.m_x;
		int cam_y = m_model.m_camera.m_watched.m_y;
		int pos_x = (int) (m_model.m_player.m_x / (Options.Entity_size * Options.Scale));
		int pos_y = (int) (m_model.m_player.m_y / (Options.Entity_size * Options.Scale));
		if (pos_y + distance >= m_grid.length) {
			concatenate();
		}
		// Affichage des blocs
		for (int i = -distance; i <= distance; i++) {
			for (int j = -distance; j <= distance; j++) {
				if ((pos_y + i) < 0 || Math.floorMod((pos_y + i), m_grid.length) <= 10) {
					g.drawImage(m_model.m_sprites.get("Block")[7],
							(j * (int) (Options.Entity_size * Options.Scale))
									- (cam_x % (int) (Options.Entity_size * Options.Scale)) + m_model.m_width / 2,
							(i * (int) (Options.Entity_size * Options.Scale))
									- (cam_y % (int) (Options.Entity_size * Options.Scale)) + m_model.m_height / 2,
							(int) Options.Scale * Options.Entity_size, (int) Options.Scale * Options.Entity_size, null);
				} else {
					g.drawImage(m_model.m_sprites.get("Dirtbg")[0],
							(j * (int) (Options.Entity_size * Options.Scale))
									- (cam_x % (int) (Options.Entity_size * Options.Scale)) + m_model.m_width / 2,
							(i * (int) (Options.Entity_size * Options.Scale))
									- (cam_y % (int) (Options.Entity_size * Options.Scale)) + m_model.m_height / 2,
							(int) Options.Scale * Options.Entity_size, (int) Options.Scale * Options.Entity_size, null);
				}
				if ((pos_y + i) >= 0
						&& m_grid[Math.floorMod((pos_y + i), m_grid.length)][Math.floorMod((pos_x + j), 60)] != null) {
					Graphics g_child = g.create(
							(j * (int) (Options.Entity_size * Options.Scale))
									- (cam_x % (int) (Options.Entity_size * Options.Scale)) + m_model.m_width / 2,
							(i * (int) (Options.Entity_size * Options.Scale))
									- (cam_y % (int) (Options.Entity_size * Options.Scale)) + m_model.m_height / 2,
							(int) (Options.Entity_size * Options.Scale), (int) (Options.Entity_size * Options.Scale));
					m_grid[Math.floorMod((pos_y + i), m_grid.length)][Math.floorMod((pos_x + j), 60)].paint(g_child);
					g_child.dispose();
				}
			}
		}

		// Affichage des entit�s
		Iterator<GameEntity> iter = m_entities.iterator();
		GameEntity E;
		while (iter.hasNext()) {
			E = iter.next();
			Graphics g_child = g.create((E.m_x - cam_x + m_model.m_width / 2), E.m_y - cam_y + m_model.m_height / 2,
					(int) (Options.Entity_size * Options.Scale), (int) (Options.Entity_size * Options.Scale));
			E.paint(g_child);
			g_child.dispose();
		}
	}

}
