package ricm3.game;

import java.awt.image.BufferedImage;
import java.util.List;

import ricm3.interpreter.IAutomaton;

public class Rabbit extends Enemy {

	public Rabbit(Model model, int x, int y, BufferedImage[] sprites, IAutomaton automate,World originWorld, List<GameEntity> t) {
		super(model, x, y, Options.HP[3], sprites, automate, originWorld, t);
	}

}
