package ricm3.game;

import java.awt.image.BufferedImage;
import java.util.Hashtable;
import java.util.Iterator;
import ricm3.interpreter.Direction;
import ricm3.interpreter.IAutomaton;
import ricm3.interpreter.Type;

public class Barbed extends Ally {

	public Barbed(Model model, int x, int y, int hp, BufferedImage[] sprites, IAutomaton automate, World originWorld) {
		super(model, x, y, hp, sprites, automate, originWorld);
		m_type = Type.DANGER;
		m_dmg = 2;
		m_recipe = new Hashtable<String, Integer>();
		m_recipe.put("Iron", 2);
	}

	@Override
	public boolean pop(Direction dir) {
		if (System.currentTimeMillis() > m_lastHit + 200) {
			m_lastHit = System.currentTimeMillis();
			Iterator<Enemy> iter = m_originWorld.m_enemies.iterator();
			double dis;
			GameEntity e = null;
			while (iter.hasNext()) {
				e = iter.next();
				dis = distance(e);
				if (dis <= 0) {
					e.damage_hp(m_dmg);
					damage_hp(5);
				}
			}
		}
		return true;
	}

	public boolean wizz(Direction dir) {
		this.m_idsprite++;
		if (this.m_idsprite >= this.m_sprites.length) {
			this.m_idsprite = 0;
		}
		return true;
	}

}
