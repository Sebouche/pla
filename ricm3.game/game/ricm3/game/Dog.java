package ricm3.game;

import java.awt.image.BufferedImage;
import java.util.List;

import ricm3.interpreter.Direction;
import ricm3.interpreter.IAutomaton;

public class Dog extends Enemy {
	int m_spritevariation = 6;

	public Dog(Model model, int x, int y, BufferedImage[] sprites,IAutomaton automate,World originWorld, List<Ally> t) {
		super(model, x, y, Options.HP[0], sprites,automate,originWorld, t);
	}
	public boolean move(Direction dir) {
		Direction absoldir= Direction.entityDir(this, dir);
		super.move(dir);
		if (absoldir != m_lastdir) {
			if (absoldir == Direction.EAST) {
				m_basesprite = 0;
			}
			if (absoldir == Direction.WEST) {
				m_basesprite = 6;
			}
			if (absoldir == Direction.NORTH) {
				m_basesprite = 18;
			}
			if (absoldir == Direction.SOUTH) {
				m_basesprite = 12;
			}
			m_lastdir = absoldir;
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
}
