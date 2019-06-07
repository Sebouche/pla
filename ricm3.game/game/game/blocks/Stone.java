package game.blocks;

import java.awt.image.BufferedImage;

import ricm3.game.Model;
import ricm3.interpreter.IAutomaton;

public class Stone extends Block {

	public Stone(Model model, int x, int y, int hp, BufferedImage[] sprites, IAutomaton automate) {
		super(model, x, y, hp, sprites, automate);
		set_idsprite(2);
	}

}
