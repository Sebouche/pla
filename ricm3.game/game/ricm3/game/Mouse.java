package ricm3.game;

import java.awt.image.BufferedImage;

public class Mouse extends Enemy {

	public Mouse(Model model, int x, int y, BufferedImage[] sprites) {
		super(model, x, y, Options.HP[2], sprites);
	}

}
