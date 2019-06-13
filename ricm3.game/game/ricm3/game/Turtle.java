package ricm3.game;

import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.List;

import ricm3.interpreter.Direction;
import ricm3.interpreter.IAutomaton;

public class Turtle extends Enemy {

	public Turtle(Model model, int x, int y, BufferedImage[] sprites, IAutomaton automate, World originWorld,
			List<Ally> t) {
		super(model, x, y, Options.HP[1], sprites, automate, originWorld, t);
		model.m_surfaceworld.m_enemies.add(this);
		m_dmg = Options.damage[1];
	}

	public boolean move(Direction dir) {
		Direction absoldir= Direction.entityDir(this, dir);
		super.move(dir);
		if (absoldir != m_lastdir) {
			if (absoldir == Direction.EAST) {
				m_basesprite = 0;
				m_spritevariation = 5;
			}
			if (absoldir == Direction.WEST) {
				m_basesprite = 5;
				m_spritevariation = 5;
			}
			if (absoldir == Direction.NORTH) {
				m_basesprite = 14;
				m_spritevariation = 4;
			}
			if (absoldir == Direction.SOUTH) {
				m_basesprite = 10;
				m_spritevariation = 4;
			}
			m_lastdir = absoldir;
			m_elapsed = 0;
		} else {
			if (m_elapsed % 50 == 0) {
				m_spritechanger = (m_spritechanger + 1) % m_spritevariation;
				m_idsprite = m_basesprite + m_spritechanger;
			}
			m_elapsed++;
		}
		return true;
	}
	
	@Override
	public boolean get() {
		Iterator<Ally> iter = m_originWorld.m_allies.iterator();
		m_dmg=100;
		double dis;
		GameEntity e = null;
		while (iter.hasNext()) {
			e = iter.next();
			dis = distance(e);
			if (dis <= 50) {
				e.damage_hp(m_dmg);
				System.out.println(e.m_hp);
			}
		}
		super.get();
		Explosion expl = new Explosion(m_model,m_x,m_y,0, m_model.m_sprites.get("Explosion"),new IAutomaton(Options.Entities.get("Explosion")),m_model.m_surfaceworld);
		m_originWorld.m_tmpadd.add(expl);
		return true;
	}
}
