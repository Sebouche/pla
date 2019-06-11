package ricm3.game;

import java.awt.Graphics;
import java.io.File;
import java.util.Iterator;
import java.util.Random;

import game.blocks.*;
import ricm3.interpreter.*;

public class UndergroundWorld extends World {

	Player m_player;
	GameEntity[][] m_grid;
	Random m_rand = new Random();

	public UndergroundWorld(Model model) {
		super(model);
		m_grid = new GameEntity[20][60];
		m_player = new Player(m_model, 10, 10, 9999, m_model.m_sprites.get("scientist"), null, this);
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
			int rand_x = m_rand.nextInt(4);
			int rand_y = m_rand.nextInt(60);
			Block b = new Block(m_model, (int) (rand_x * Options.Entity_size * Options.Scale),
					(int) (rand_y * Options.Entity_size * Options.Scale), -1, m_model.m_sprites.get("block"),
					new IAutomaton(m_model.m_automatons.get(0)), m_model.m_undergroundworld);
			b.set_idsprite(8);
			m_grid[rand_x + 4][rand_y] = b;
		}
		// Generation maison
		for (int i = 9; i <= 10; i++) {
			for (int j = 0; j <= 2; j++) {
				Block b;
				if (i == 10 && j == 2) {
					b = new Gate(m_model, (int) (j * Options.Entity_size * Options.Scale),
							(int) (i * Options.Entity_size * Options.Scale), -1, m_model.m_sprites.get("house"),
							new IAutomaton(m_model.m_automatons.get(0)), m_model.m_undergroundworld);
				} else {
					b = new Block(m_model, (int) (j * Options.Entity_size * Options.Scale),
							(int) (i * Options.Entity_size * Options.Scale), -1, m_model.m_sprites.get("house"),
							new IAutomaton(m_model.m_automatons.get(0)), m_model.m_undergroundworld);
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
						(int) (11 * Options.Entity_size * Options.Scale), -1, m_model.m_sprites.get("block"),
						new IAutomaton(m_model.m_automatons.get(0)), m_model.m_undergroundworld);
				b.set_idsprite(6);
				m_grid[11][j] = b;
			} else {
				Block b = new Block(m_model, (int) (j * Options.Entity_size * Options.Scale),
						(int) (11 * Options.Entity_size * Options.Scale), -1, m_model.m_sprites.get("block"),
						new IAutomaton(m_model.m_automatons.get(0)), m_model.m_undergroundworld);
				b.set_idsprite(1);
				m_grid[11][j] = b;
			}
		}
		m_grid[11][1] = null;

		// Generation minerais
		for (int i = 12; i < 60; i++) {
			for (int j = 0; j < 60; j++) {
				if (m_grid[i][j] == null) {
					randint = m_rand.nextInt(1001);
					if (randint >= 0 && randint < 850) {
						Block b = new Dirt(m_model, (int) (j * Options.Entity_size * Options.Scale),
								(int) (i * Options.Entity_size * Options.Scale), 300, m_model.m_sprites.get("block"),
								new IAutomaton(m_model.m_automatons.get(0)), m_model.m_undergroundworld);
						m_grid[i][j] = b;
					}
					if (randint >= 850 && randint < 950) {
						Block b = new Copper(m_model, (int) (j * Options.Entity_size * Options.Scale),
								(int) (i * Options.Entity_size * Options.Scale), 600, m_model.m_sprites.get("block"),
								new IAutomaton(m_model.m_automatons.get(0)), m_model.m_undergroundworld);
						m_grid[i][j] = b;
						for (int k = -1; k <= 1; k++) {
							int randint2 = m_rand.nextInt(10);
							if (randint2 <= 3 && i + k >= 0 && i + k < 20) {
								b = new Copper(m_model, (int) (j * Options.Entity_size * Options.Scale),
										(int) ((i + k) * Options.Entity_size * Options.Scale), 600,
										m_model.m_sprites.get("block"), new IAutomaton(m_model.m_automatons.get(0)),
										m_model.m_undergroundworld);
								m_grid[i][j] = b;
							}
							randint2 = m_rand.nextInt(10);
							if (randint2 <= 3 && j + k >= 0 && j + k < 60) {
								b = new Copper(m_model, (int) ((j + k) * Options.Entity_size * Options.Scale),
										(int) (i * Options.Entity_size * Options.Scale), 600,
										m_model.m_sprites.get("block"), new IAutomaton(m_model.m_automatons.get(0)),
										this);
								b.set_idsprite(5);
								m_grid[i][j] = b;
							}
						}
					}
					if (randint >= 950 && randint < 999) {
						m_grid[i][j] = null;
					}
					if (randint >= 999 && i >= 30) {
						m_grid[i][j] = new Uranium(m_model, (int) (j * Options.Entity_size * Options.Scale),
								(int) (i * Options.Entity_size * Options.Scale), 900, m_model.m_sprites.get("block"),
								new IAutomaton(m_model.m_automatons.get(0)), m_model.m_undergroundworld);
					}
				}
			}
		}
	}

	@Override
	public void changeWorld() {
		// A appeller lorsque le joueur passe par la porte
		m_model.m_currentworld = m_model.m_surfaceworld;
		m_model.m_player=m_model.m_surfaceplayer;
		m_model.m_player.m_x = 64;
		m_model.m_player.m_y = 192;
		m_model.m_player.m_dy = 0;
		m_model.m_player.m_originWorld = m_model.m_surfaceworld;
	}

	@Override
	public void step() {
		// application de la gravit� au joueur
		if (m_player.m_originWorld instanceof UndergroundWorld) {
			gravity(m_model.m_player);
		}

		// step des blocs
		int entity_size = (int) (Options.Scale * Options.Entity_size);
		int distance = 10;
		int pos_x = (int) (m_model.m_player.m_x / entity_size);
		int pos_y = (int) (m_model.m_player.m_y / entity_size);
		for (int i = -distance; i <= distance; i++) {
			for (int j = -distance; j <= distance; j++) {
				if (m_grid[Math.floorMod((pos_y + i), 60)][Math.floorMod((pos_x + j), 60)] != null) {
					m_grid[Math.floorMod((pos_y + i), 60)][Math.floorMod((pos_x + j), 60)].step();
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
		
	}

	// Renvoi true si il y a collision avec un des blocs adjacent a l'entit�
	boolean adjacent_collision(MovingEntity E, int dx, int dy) {
		int entity_size = (int) (Options.Scale * Options.Entity_size);
		int pos_x = (int) (E.m_x / entity_size);
		int pos_y = (int) (E.m_y / entity_size);
		int pos_x_r = (int) ((E.m_x + entity_size) / entity_size);
		int pos_y_d = (int) ((E.m_y + entity_size) / entity_size);
		if (m_grid[pos_y - 1][pos_x] != null && m_grid[pos_y - 1][pos_x].m_collision)
			if (E.collision(m_grid[pos_y - 1][pos_x], dx, dy)) {
				return true;
			}
		if (m_grid[pos_y - 1][pos_x_r] != null && m_grid[pos_y - 1][pos_x_r].m_collision)
			if (E.collision(m_grid[pos_y - 1][pos_x_r], dx, dy)) {
				return true;
			}
		if (m_grid[pos_y][Math.floorMod((pos_x - 1), 60)] != null
				&& m_grid[pos_y][Math.floorMod((pos_x - 1), 60)].m_collision)
			if (E.collision(m_grid[pos_y][Math.floorMod((pos_x - 1), 60)], dx, dy)) {
				return true;
			}
		if (m_grid[pos_y][Math.floorMod((pos_x_r + 1), 60)] != null
				&& m_grid[pos_y][Math.floorMod((pos_x_r + 1), 60)].m_collision)
			if (E.collision(m_grid[pos_y][Math.floorMod((pos_x_r + 1), 60)], dx, dy)) {
				return true;
			}
		if (m_grid[pos_y + 1][Math.floorMod((pos_x - 1), 60)] != null
				&& m_grid[pos_y + 1][Math.floorMod((pos_x - 1), 60)].m_collision)
			if (E.collision(m_grid[pos_y + 1][Math.floorMod((pos_x - 1), 60)], dx, dy)) {
				return true;
			}
		if (m_grid[pos_y + 1][Math.floorMod((pos_x_r + 1), 60)] != null
				&& m_grid[pos_y + 1][Math.floorMod((pos_x_r + 1), 60)].m_collision)
			if (E.collision(m_grid[pos_y + 1][Math.floorMod((pos_x_r + 1), 60)], dx, dy)) {
				return true;
			}
		if (m_grid[pos_y_d + 1][pos_x] != null && m_grid[pos_y_d + 1][pos_x].m_collision)
			if (E.collision(m_grid[pos_y_d + 1][pos_x], dx, dy)) {
				return true;
			}
		if (m_grid[pos_y_d + 1][pos_x_r] != null && m_grid[pos_y_d + 1][pos_x_r].m_collision)
			if (E.collision(m_grid[pos_y_d + 1][pos_x_r], dx, dy)) {
				return true;
			}
		return false;
	}

	void gravity(MovingEntity ge) {
		if (ge.m_dy < 20) {
			ge.m_dy++;
		}
	}

	@Override
	public void paint(Graphics g) {
		int distance = 20;
		int cam_x = m_model.m_camera.m_watched.m_x;
		int cam_y = m_model.m_camera.m_watched.m_y;
		int pos_x = (int) (m_model.m_player.m_x / (Options.Entity_size * Options.Scale));
		int pos_y = (int) (m_model.m_player.m_y / (Options.Entity_size * Options.Scale));

		// Affichage des blocs
		for (int i = -distance; i <= distance; i++) {
			for (int j = -distance; j <= distance; j++) {
				if (m_grid[Math.floorMod((pos_y + i), 60)][Math.floorMod((pos_x + j), 60)] != null) {
					Graphics g_child = g.create(
							(j * (int) (Options.Entity_size * Options.Scale))
									- (cam_x % (int) (Options.Entity_size * Options.Scale)) + m_model.m_width / 2,
							(i * (int) (Options.Entity_size * Options.Scale))
									- (cam_y % (int) (Options.Entity_size * Options.Scale)) + m_model.m_height / 2,
							(int) (Options.Entity_size * Options.Scale), (int) (Options.Entity_size * Options.Scale));
					m_grid[Math.floorMod((pos_y + i), 60)][Math.floorMod((pos_x + j), 60)].paint(g_child);
					g_child.dispose();
				}
			}
			k = 0;
		}

		// Affichage des entit�s
		Iterator<GameEntity> iter = m_entities.iterator();
		GameEntity E;
		while (iter.hasNext()) {
			E = iter.next();
			Graphics g_child = g.create(E.m_x - cam_x + m_model.m_width / 2, E.m_y - cam_y + m_model.m_height / 2,
					(int) (Options.Entity_size * Options.Scale), (int) (Options.Entity_size * Options.Scale));
			E.paint(g_child);
			g_child.dispose();
		}
	}

}
