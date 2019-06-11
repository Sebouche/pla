package ricm3.game;

import java.awt.image.BufferedImage;
import java.util.List;

import ricm3.interpreter.IAutomaton;

public class Turtle extends Enemy {

	public Turtle(Model model, int x, int y, BufferedImage[] sprites, IAutomaton automate, World originWorld, List<GameEntity> t) {
		super(model, x, y, Options.HP[1], sprites, automate, originWorld, t);
	}

}
