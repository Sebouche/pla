package ricm3.game;

import java.awt.image.BufferedImage;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import game.blocks.Stone;
import ricm3.interpreter.Direction;
import ricm3.interpreter.IAutomaton;
import ricm3.interpreter.Type;

public class Wall extends Ally {


	public Wall(Model model, int x, int y, int hp, BufferedImage[] sprites, IAutomaton automate, World originWorld) {
		super(model, x, y, hp, sprites, automate, originWorld);
		m_recipe = new Hashtable<String, Integer>();
		m_recipe.put("Stone", 2);
		m_type = Type.OBSTACLE;
		m_dmg = 500;
	}


	@Override
	public boolean wizz(Direction dir) {
		Iterator<Enemy> iter = m_originWorld.m_enemies.iterator();
		double dis;
		GameEntity e = null;
		while (iter.hasNext()) {
			e = iter.next();
			dis = distance(e);
			if (dis <= 10) {
				e.damage_hp(m_dmg);
			}
		}
		return true;
	}

}
