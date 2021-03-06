package game.blocks;

import java.awt.image.BufferedImage;

import ricm3.game.Model;
import ricm3.game.World;
import ricm3.interpreter.IAutomaton;

public class Iron extends Block {

	public Iron(Model model, int x, int y, int hp, BufferedImage[] sprites, IAutomaton automate, World originWorld) {
		super(model, x, y, hp, sprites, automate, originWorld);
		set_idsprite(5);
	}

}