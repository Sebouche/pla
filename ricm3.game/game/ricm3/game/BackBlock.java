package ricm3.game;

import java.awt.image.BufferedImage;

import ricm3.interpreter.IAutomaton;

public class BackBlock extends Block {

	public BackBlock(Model model, int x, int y, int hp, BufferedImage[] sprites,IAutomaton automate) {
		super(model, x, y, hp, sprites,automate);
	}
}
