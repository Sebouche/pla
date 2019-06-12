package ricm3.game;

import java.awt.image.BufferedImage;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import game.blocks.Stone;
import ricm3.interpreter.Direction;
import ricm3.interpreter.IAutomaton;

public class Wall extends Ally {

	List<Ally> target;

	public Wall(Model model, int x, int y, int hp, BufferedImage[] sprites, IAutomaton automate, World originWorld) {
		super(model, x, y, hp, sprites, automate, originWorld);
		m_recipe = new Hashtable<String, Integer>();
		m_recipe.put("Stone", 2);
		m_dmg = 4;
	}


	@Override
	public boolean wizz(Direction dir) {
		Iterator<Ally> iter = target.iterator();
		double dis;
		GameEntity e = null;
		while (iter.hasNext()) {
			e = iter.next();
			dis = distance(e);
			if (dis <= 10) {
				damage_hp(m_dmg);
			}
		}
		return true;
	}

}
