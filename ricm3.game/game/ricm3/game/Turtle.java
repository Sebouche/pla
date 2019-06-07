package ricm3.game;

import java.awt.image.BufferedImage;

public class Turtle extends Enemy {

	public Turtle(Model model, int x, int y, BufferedImage[] sprites) {
		super(model, x, y, Options.HP[1], sprites);
	}

}
