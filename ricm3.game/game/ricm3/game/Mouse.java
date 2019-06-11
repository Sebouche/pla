package ricm3.game;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

import ricm3.interpreter.Direction;
import ricm3.interpreter.IAutomaton;

public class Mouse extends Enemy {

	public Mouse(Model model, int x, int y, BufferedImage[] sprites, IAutomaton automate, World originWorld,
			List<Ally> t) {
		super(model, x, y, Options.HP[2], sprites, automate, originWorld, t);
	}

	@Override
	public boolean egg() {
		System.out.println("egg souris");
		Random r = new Random();
		if ((new Random().nextInt()) % 100000 == 0) {
			m_model.m_surfaceworld.m_entities
					.add(new Mouse(m_model, m_x, m_y, m_sprites, m_automate, m_originWorld, targets));
			return true;
		}
		return false;
	}

}
