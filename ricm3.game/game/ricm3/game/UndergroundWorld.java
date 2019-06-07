package ricm3.game;

import java.awt.Graphics;
import java.io.File;
import java.util.Iterator;
import java.util.Random;

import ricm3.interpreter.IAutomaton;

public class UndergroundWorld extends World {

	Player m_player;
	GameEntity[][] m_grid;
	Random m_rand = new Random();

	public UndergroundWorld(Model model) {
		super(model);
		m_grid = new GameEntity[20][60];
		m_player = new Player(m_model, 10, 10, 9999, m_model.m_sprites.get("scientist"));
		generate_level();
		m_bgmfile = new File("sprites/bgm_underground.wav");
	}

	void generate_level() {
		int randint;
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 60; j++) {
				m_grid[i][j] = null;
			}
		}
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 60; j++) {
				if (m_grid[i][j] == null) {
					randint = m_rand.nextInt(1001);
					if (randint >= 0 && randint < 900) {
						Block b = new Block(m_model, (int) (j * Options.Entity_size * Options.Scale),
								(int) (i * Options.Entity_size * Options.Scale), 300, m_model.m_sprites.get("block"),new IAutomaton(m_model.m_automatons.get(0)));
						b.set_idsprite(0);
						m_grid[i][j] = b;
					}
					if (randint >= 900 && randint < 950) {
						Block b = new Block(m_model, (int) (j * Options.Entity_size * Options.Scale),
								(int) (i * Options.Entity_size * Options.Scale), 300, m_model.m_sprites.get("block"),new IAutomaton(m_model.m_automatons.get(0)));
						b.set_idsprite(5);
						m_grid[i][j] = b;
						for (int k = -1; k <= 1; k++) {
							int randint2 = m_rand.nextInt(10);
							if (randint2 <= 3 && i + k >= 0 && i + k < 20) {
								b = new Block(m_model, (int) (j * Options.Entity_size * Options.Scale),
										(int) ((i + k) * Options.Entity_size * Options.Scale), 300,
										m_model.m_sprites.get("block"),new IAutomaton(m_model.m_automatons.get(0)));
								b.set_idsprite(5);
								m_grid[i][j] = b;
							}
							randint2 = m_rand.nextInt(10);
							if (randint2 <= 3 && j + k >= 0 && j + k < 60) {
								b = new Block(m_model, (int) ((j + k) * Options.Entity_size * Options.Scale),
										(int) (i * Options.Entity_size * Options.Scale), 300,
										m_model.m_sprites.get("block"),new IAutomaton(m_model.m_automatons.get(0)));
								b.set_idsprite(5);
								m_grid[i][j] = b;
							}
						}
					}
					if (randint >= 950 && randint < 999) {
						m_grid[i][j] = null;
					}
					if (randint >= 999) {
						m_grid[i][j] = new Block(m_model, (int) (j * Options.Entity_size * Options.Scale),
								(int) (i * Options.Entity_size * Options.Scale), 300, m_model.m_sprites.get("grassbg"),new IAutomaton(m_model.m_automatons.get(0)));
					}
				}
			}
		}
	}

	@Override
	public void step() {
		gravity(m_player);
		
		int entity_size = (int) (Options.Scale * Options.Entity_size);
		int distance = 10;
		int pos_x = (int) (m_model.m_player.m_x / (Options.Entity_size * Options.Scale));
		int pos_y = (int) (m_model.m_player.m_y / (Options.Entity_size * Options.Scale));
		int pos_x_r = (int) ((m_model.m_player.m_x + entity_size) / (Options.Entity_size * Options.Scale));
		int pos_y_d = (int) ((m_model.m_player.m_y + entity_size) / (Options.Entity_size * Options.Scale));
		for (int i = -distance; i <= distance; i++) {
			for (int j = -distance; j <= distance; j++) {
				if (m_grid[Math.floorMod((pos_y + i), 20)][Math.floorMod((pos_x + j), 60)] != null) {
					m_grid[Math.floorMod((pos_y + i), 20)][Math.floorMod((pos_x + j), 60)].step();
				}
			}
		}
		
		//collision avec les blocs autour du joueur
		if (m_grid[pos_y - 1][pos_x] != null)
			m_player.collision(m_grid[pos_y - 1][pos_x], m_player.m_dx, m_player.m_dy);
		if (m_grid[pos_y - 1][pos_x_r] != null)
			m_player.collision(m_grid[pos_y - 1][pos_x_r], m_player.m_dx, m_player.m_dy);
		if (m_grid[pos_y][Math.floorMod((pos_x - 1), 60)] != null)
			m_player.collision(m_grid[pos_y][Math.floorMod((pos_x - 1), 60)], m_player.m_dx, m_player.m_dy);
		if (m_grid[pos_y][Math.floorMod((pos_x_r + 1), 60)] != null)
			m_player.collision(m_grid[pos_y][Math.floorMod((pos_x_r + 1), 60)], m_player.m_dx, m_player.m_dy);
		if (m_grid[pos_y + 1][Math.floorMod((pos_x - 1), 60)] != null)
			m_player.collision(m_grid[pos_y + 1][Math.floorMod((pos_x - 1), 60)], m_player.m_dx, m_player.m_dy);
		if (m_grid[pos_y + 1][Math.floorMod((pos_x_r + 1), 60)] != null)
			m_player.collision(m_grid[pos_y + 1][Math.floorMod((pos_x_r + 1), 60)], m_player.m_dx, m_player.m_dy);
		if (m_grid[pos_y_d + 1][pos_x] != null)
			m_player.collision(m_grid[pos_y_d + 1][pos_x], m_player.m_dx, m_player.m_dy);
		if (m_grid[pos_y_d + 1][pos_x_r] != null)
			m_player.collision(m_grid[pos_y_d + 1][pos_x_r], m_player.m_dx, m_player.m_dy);
		
		Iterator<GameEntity> iter = m_entities.iterator();
		GameEntity E;
		while (iter.hasNext()) {
			E = iter.next();
			E.step();
		}
		
	}

	void gravity(MovingEntity ge) {
		if (ge.m_dy < 20) {
			ge.m_dy++;
		}
	}

	@Override
	public void paint(Graphics g) {
		int distance = 10;
		int k = 0;
		int cam_x = m_model.m_camera.m_watched.m_x;
		int cam_y = m_model.m_camera.m_watched.m_y;
		int pos_x = (int) (m_model.m_player.m_x / (Options.Entity_size * Options.Scale));
		int pos_y = (int) (m_model.m_player.m_y / (Options.Entity_size * Options.Scale));
		for (int i = -distance; i <= distance; i++) {
			for (int j = -distance; j <= distance; j++) {
				k++;
				if (m_grid[Math.floorMod((pos_y + i), 20)][Math.floorMod((pos_x + j), 60)] != null) {
					Graphics g_child = g.create(k * (int) (Options.Entity_size * Options.Scale) - cam_x,
							m_grid[Math.floorMod((pos_y + i), 20)][Math.floorMod((pos_x + j), 60)].m_y - cam_y
									+ m_model.m_height / 2 + 2,
							(int) (Options.Entity_size * Options.Scale), (int) (Options.Entity_size * Options.Scale));
					m_grid[Math.floorMod((pos_y + i), 20)][Math.floorMod((pos_x + j), 60)].paint(g_child);
					g_child.dispose();
				}
			}
			k = 0;
		}
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
