package ricm3.game;

import java.awt.image.BufferedImage;

public class Dog extends Enemy {

	public Dog(Model model, int x, int y, BufferedImage[] sprites) {
		super(model, x, y, Options.HP[0], sprites);
	}

}
