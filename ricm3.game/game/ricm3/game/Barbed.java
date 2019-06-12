package ricm3.game;

import java.awt.image.BufferedImage;

import ricm3.interpreter.IAutomaton;

public class Barbed extends Ally {
	public Barbed(Model model, int x, int y, int hp, BufferedImage[] sprites, IAutomaton automate, World originWorld) {
		super(model, x, y, hp, sprites, automate, originWorld);
	}
}
