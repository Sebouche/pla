package ricm3.game;

import java.awt.image.BufferedImage;
import java.util.Hashtable;

import game.blocks.Stone;
import ricm3.interpreter.IAutomaton;
import ricm3.interpreter.Type;

public class Wall extends Ally {
	
	public Wall(Model model, int x, int y, int hp, BufferedImage[] sprites, IAutomaton automate, World originWorld) {
		super(model, x, y, hp, sprites, automate, originWorld);
		m_recipe = new Hashtable<String, Integer>();
		m_recipe.put("Stone", 2);
		m_type = Type.OBSTACLE;
	}
}
