package ricm3.game;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

import ricm3.interpreter.Direction;
import ricm3.interpreter.IAutomaton;

public class Mouse extends Enemy {
	int m_spritevariation = 0;
	static Random r = new Random();

	public Mouse(Model model, int x, int y, BufferedImage[] sprites, IAutomaton automate, World originWorld,
			List<Ally> t) {
		super(model, x, y, Options.HP[2], sprites, automate, originWorld, t);
		model.m_surfaceworld.m_enemies.add(this);
	}

	public boolean move(Direction dir) {
		super.move(dir);
		if (dir != m_lastdir) {
			if (dir == Direction.EAST) {
				m_basesprite = 0;
			}
			if (dir == Direction.WEST) {
				m_basesprite = 5;
			}
			if (dir == Direction.NORTH) {
				m_basesprite = 10;
			}
			if (dir == Direction.SOUTH) {
				m_basesprite = 15;
			}
			m_lastdir = dir;
			m_elapsed = 0;
		} else {
			if (m_elapsed % 30 == 0) {
				m_spritechanger = (m_spritechanger + 1) % m_spritevariation;
				m_idsprite = m_basesprite + m_spritechanger;
			}
			m_elapsed++;
		}
		return true;
	}

	@Override
	public boolean egg() {
		System.out.println("egg souris");
		if ((r.nextInt()) % 100000 == 0) {
			m_model.m_surfaceworld.m_entities
					.add(new Mouse(m_model, m_x+(r.nextInt()%21)-10, m_y+(r.nextInt()%21)-10, m_sprites, m_automate, m_originWorld, targets));
			return true;
		}
		return false;
	}

}
