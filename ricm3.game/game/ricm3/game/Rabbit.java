package ricm3.game;

import java.awt.image.BufferedImage;

public class Rabbit extends Enemy {

	public Rabbit(Model model, int x, int y, BufferedImage[] sprites) {
		super(model, x, y, Options.HP[3], sprites);
	}

}
