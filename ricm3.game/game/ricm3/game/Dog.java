package ricm3.game;

import java.awt.image.BufferedImage;

import ricm3.interpreter.IAutomaton;

public class Dog extends Enemy {

	public Dog(Model model, int x, int y, BufferedImage[] sprites,IAutomaton automate) {
		super(model, x, y, Options.HP[0], sprites,automate);
	}

}
