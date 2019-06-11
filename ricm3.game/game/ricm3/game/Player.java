package ricm3.game;

import java.awt.image.BufferedImage;

import ricm3.interpreter.Direction;
import ricm3.interpreter.IAutomaton;

public class Player extends Ally {
	int m_spritevariation=8;

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

	@Override
	public boolean move(Direction dir) {
		super.move(dir);
		if (dir != m_lastdir) {
			if (dir == Direction.EAST) {
				m_basesprite = 18;
			}
			if (dir == Direction.WEST) {
				m_basesprite = 26;
			}
			if (dir == Direction.NORTH) {
				m_basesprite = 8;
			}
			if (dir == Direction.SOUTH) {
				m_basesprite = 0;
			}
			m_lastdir = dir;
			m_elapsed = 0;
		} else {
			if (m_elapsed % 30 == 0) {
				m_spritechanger=(m_spritechanger+1)%m_spritevariation;
				m_idsprite=m_basesprite+m_spritechanger;
			}
			m_elapsed++;
		}
		return true;
	}
}
