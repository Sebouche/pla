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
		Iterator<Ally> iter;
		Ally e;
		boolean b = false;
		if (ge.m_originWorld instanceof SurfaceWorld) {
			iter = m_model.m_surfaceworld.m_allies.iterator();
			while (iter.hasNext()) {
				e = iter.next();
				if (e instanceof MovingEntity) {
					if (((MovingEntity) (e)).distance(ge) <= 1) {
						e.damage_hp(ge.m_dmg);
						if(e instanceof Player) {
							m_model.m_player.m_hit=true;
						}
						b = true;
					}
				}
			}
		} else {
			iter = m_model.m_undergroundworld.m_allies.iterator();
			while (iter.hasNext()) {
				e = iter.next();
				if (e instanceof MovingEntity) {
					if (((MovingEntity) (e)).distance(ge) <= 1) {
						e.damage_hp(ge.m_dmg);
						b = true;
					}
				}
			}
		}
		return b;
	}

}
