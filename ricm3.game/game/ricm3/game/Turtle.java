package ricm3.game;

import java.awt.image.BufferedImage;

import ricm3.interpreter.Direction;
import ricm3.interpreter.IAutomaton;

public class Turtle extends Enemy {
	int m_spritevariation=5;
	public Turtle(Model model, int x, int y, BufferedImage[] sprites, IAutomaton automate, World originWorld) {
		super(model, x, y, Options.HP[1], sprites, automate, originWorld);
	}
	public boolean move(Direction dir) {
		super.move(dir);
		if (dir != m_lastdir) {
			if (dir == Direction.EAST) {
				m_basesprite = 0;
				m_spritevariation=5;
			}
			if (dir == Direction.WEST) {
				m_basesprite = 5;
				m_spritevariation=5;
			}
			if (dir == Direction.NORTH) {
				m_basesprite = 10;
				m_spritevariation=4;
			}
			if (dir == Direction.SOUTH) {
				m_basesprite = 15;
				m_spritevariation=4;
			}
			m_lastdir = dir;
			m_elapsed = 0;
		} else {
			if (m_elapsed % 50 == 0) {
				m_spritechanger=(m_spritechanger+1)%m_spritevariation;
				m_idsprite=m_basesprite+m_spritechanger;
			}
			m_elapsed++;
		}
		return true;
	}
}
