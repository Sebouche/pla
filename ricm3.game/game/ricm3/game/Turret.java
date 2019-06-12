package ricm3.game;

import java.awt.image.BufferedImage;
import java.util.Hashtable;

import game.blocks.Copper;
import game.blocks.Iron;
import ricm3.interpreter.IAutomaton;

public class Turret extends Ally {
	
	public Turret(Model model, int x, int y, int hp, BufferedImage[] sprites, IAutomaton automate, World originWorld) {
		super(model, x, y, hp, sprites, automate, originWorld);
		m_recipe = new Hashtable<String, Integer>();
		m_recipe.put("Iron", 2);
		m_recipe.put("Copper", 1);
	}
}
