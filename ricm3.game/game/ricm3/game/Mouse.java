package ricm3.game;

import java.awt.image.BufferedImage;
import java.util.List;

import ricm3.interpreter.IAutomaton;

public class Mouse extends Enemy {

	public Mouse(Model model, int x, int y, BufferedImage[] sprites,IAutomaton automate,World originWorld, List<GameEntity> t) {
		super(model, x, y, Options.HP[2], sprites,automate, originWorld, t);
	}

}
