package game.blocks;

import java.awt.image.BufferedImage;

import ricm3.game.*;
import ricm3.interpreter.*;

public class Dirt extends Block {

	public Dirt(Model model, int x, int y, int hp, BufferedImage[] sprites, IAutomaton automate, World originWorld) {
		super(model, x, y, hp, sprites, automate, originWorld);
		set_idsprite(0);
	}

}