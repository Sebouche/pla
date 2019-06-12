package ricm3.game;

import java.awt.Graphics;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ricm3.interpreter.Direction;

public class World {

	Model m_model;
	LinkedList<GameEntity> m_entities;
	LinkedList<Ally> m_allies;
	LinkedList<Enemy> m_enemies;
	File m_bgmfile;
	LinkedList<GameEntity> m_tmpadd;
	LinkedList<GameEntity> m_tmprm;
	
	public World(Model model) {
		m_entities = new LinkedList<GameEntity>();
		m_allies = new LinkedList<Ally>();
		m_enemies = new LinkedList<Enemy>();
		m_tmpadd = new LinkedList<GameEntity>();
		m_tmprm = new LinkedList<GameEntity>();
		m_model = model;
	}

	public void changeWorld() {
		try {
			Options.bgm.stop();
			Options.bgm = new Music(m_model.m_currentworld.m_bgmfile);
			Options.bgm.start();
		} catch (Exception ex) {
		}
	}

	public void step() {
	}

	public void paint(Graphics g) {
	}

	public boolean Hit(GameEntity ge, Direction d) {
		Iterator<Ally> iter = m_allies.iterator();
		Ally e;
		int x, y;
		switch (d) {
		case EAST:
			x = 10;
			y = 0;
			break;
		case SOUTH:
			x = 0;
			y = 10;
			break;
		case WEST:
			x = -10;
			y = 0;
			break;
		case NORTH:
			x = 0;
			y = -10;
			break;
		case NONE:
			x = 0;
			y = 0;
			break;
		default:
			System.out.println("Mauvaise utilisation de Hit (world)");
			return false;
		}
		boolean b = false;
		while (iter.hasNext()) {
			e = iter.next();
			if (e instanceof MovingEntity) {
				if (((MovingEntity) (e)).collision(ge, x, y)) {
					e.damage_hp(ge.m_dmg);
					b = true;
				}
			}
		}
		return b;
	}

}
