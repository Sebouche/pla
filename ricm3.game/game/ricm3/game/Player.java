package ricm3.game;

import java.awt.image.BufferedImage;

import ricm3.interpreter.Direction;
import ricm3.interpreter.IAutomaton;
import ricm3.interpreter.IState;

public class Player extends Ally {

	public Player(Model model, int x, int y, int hp, BufferedImage[] sprites, IAutomaton automate, World originWorld) {
		super(model, x, y, hp, sprites, automate, originWorld);
	}

	@Override
	public boolean jump(Direction dir) {
		m_originWorld.changeWorld();
		return true;
	}

	@Override
	public void step() {
		m_automate.step(this);
	}
}
