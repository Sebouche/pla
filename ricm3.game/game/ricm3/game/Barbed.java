package ricm3.game;

import java.awt.image.BufferedImage;
import java.util.Hashtable;

import game.blocks.Iron;
import ricm3.interpreter.IAutomaton;

public class Barbed extends Ally {
	
	public Barbed(Model model, int x, int y, int hp, BufferedImage[] sprites, IAutomaton automate, World originWorld) {
		super(model, x, y, hp, sprites, automate, originWorld);
		m_recipe = new Hashtable<String, Integer>();
		m_recipe.put("Iron", 2);
	}
}
