package ricm3.game;

import java.awt.image.BufferedImage;

import ricm3.interpreter.Direction;
import ricm3.interpreter.IAutomaton;

public class Dog extends Enemy {
	int m_spritevariation = 6;

	public Dog(Model model, int x, int y, BufferedImage[] sprites,IAutomaton automate,World originWorld) {
		super(model, x, y, Options.HP[0], sprites,automate,originWorld);
	}
	public boolean move(Direction dir) {
		super.move(dir);
		if (dir != m_lastdir) {
			if (dir == Direction.EAST) {
				m_basesprite = 0;
			}
			if (dir == Direction.WEST) {
				m_basesprite = 6;
			}
			if (dir == Direction.NORTH) {
				m_basesprite = 12;
			}
			if (dir == Direction.SOUTH) {
				m_basesprite = 18;
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
}
