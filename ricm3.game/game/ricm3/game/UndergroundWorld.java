package ricm3.game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;


public class UndergroundWorld extends World{

	Player m_player;
	GameEntity[][] m_grid;
	Random m_rand=new Random();
	
	
	public UndergroundWorld(Model model) {
		super(model);
		m_grid=new GameEntity[20][60];
	}
	
	void generate_level() {
		int randint;
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 60; j++) {
				//m_grid[i][j] = new BackBlock(m_model,i*Options.Entity_size,j,0);
				m_grid[i][j]= null;
			}
		}
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 60; j++) {
				if (m_grid[i][j] instanceof FrontBlock) {
					randint = m_rand.nextInt(1001);
					if (randint >= 0 && randint < 900) {
						m_grid[i][j] = null;
					}
					if (randint >= 900 && randint < 950) {
						m_grid[i][j] = null;
						for (int k = -1; k <= 1; k ++) {
							int randint2 = m_rand.nextInt(10);
							if (randint2 <= 3 && i + k >= 0 && i + k < 20) {
								m_grid[i + k][j] = null;
							}
							randint2 = m_rand.nextInt(10);
							if (randint2 <= 3 && j + k >= 0 && j + k < 60) {
								m_grid[i][j+k] = null;
							}
						}
					}
					if (randint >= 950 && randint < 999) {
						m_grid[i][j] = null;
					}
					if (randint >= 999) {
						m_grid[i][j] = null;
					}
				}
			}
		}
	}
	
	@Override
	public void step() {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 60; j++) {
				m_grid[i][j].step();
			}
		}
		Iterator<GameEntity> iter=m_entities.iterator();
		GameEntity E;
		while(iter.hasNext()) {
			E=iter.next();
			E.step();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 60; j++) {
				//Graphics g_child = g.create(j * Options.Entity_size, i * Options.Entity_size, Options.Entity_size,
				//		Options.Entity_size);
				//m_grid[i][j].paint(g_child);
				//g_child.dispose();
			}
		}
	}
}


