package ricm3.game;

import java.awt.image.BufferedImage;

import ricm3.interpreter.IAutomaton;

public class Construction extends Ally {

	public Construction(Model model, int x, int y, int hp, BufferedImage[] sprites, IAutomaton automate,World originWorld) {
		super(model, x, y, hp, sprites, automate, originWorld);
	}
}
