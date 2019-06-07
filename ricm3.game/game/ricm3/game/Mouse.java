package ricm3.game;

import java.awt.image.BufferedImage;

import ricm3.interpreter.IAutomaton;

public class Mouse extends Enemy {

	public Mouse(Model model, int x, int y, BufferedImage[] sprites,IAutomaton automate,World originWorld) {
		super(model, x, y, Options.HP[2], sprites,automate, originWorld);
	}

}
