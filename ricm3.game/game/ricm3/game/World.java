package ricm3.game;

import java.awt.Graphics;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

import ricm3.interpreter.Direction;

public class World {

	Model m_model;
	LinkedList<GameEntity> m_entities;
	LinkedList<Ally> m_allies;
	File m_bgmfile;
	LinkedList<Ally> m_allies;
	
	public World(Model model) {
		m_entities = new LinkedList<GameEntity>();
		m_allies = new LinkedList<Ally>();
		m_model = model;
	}
	
	public void changeWorld() {
		try {
			Options.m_bgm.stop();
			Options.m_bgm = new Music(m_model.m_currentworld.m_bgmfile);
			Options.m_bgm.start();
		} catch (Exception ex) {}
		
	}
	public void step() {
	}

	public void paint(Graphics g) {
	}
	
	public boolean Hit(GameEntity ge, Direction d) {
		Iterator<GameEntity> iter = m_entities.iterator();
		GameEntity e;
		int x,y;
		switch(d) {
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
		default:
			System.out.println("Mauvaise utilisation de Hit (world)");
			return false;
		}
		boolean b = false;
		while(iter.hasNext()) {
			e = iter.next();
			if(((MovingEntity)(e)).collision(ge, x, y)) {
				ge.damage_hp(e.m_dmg);
				b = true;
			}
		}
		return b;
	}

}
