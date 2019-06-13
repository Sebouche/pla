package ricm3.game;

import java.awt.image.BufferedImage;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import game.blocks.Stone;
import ricm3.interpreter.Direction;
import ricm3.interpreter.IAutomaton;
import ricm3.interpreter.Type;

public class Explosion extends GameEntity {
	int m_elapsed = 0;

	public Explosion(Model model, int x, int y, int hp, BufferedImage[] sprites, IAutomaton automate,
			World originWorld) {
		super(model, x, y, hp, sprites, automate, originWorld);
		m_hp = 7;
	}

	@Override
	public boolean wizz(Direction dir) {
		if (m_elapsed == 20) {
			m_hp--;
			m_idsprite++;
			m_elapsed=0;
		}
		m_elapsed++;
		if (m_hp<=0) {
			m_originWorld.m_entities.remove(this);
		}
		return true;
	}
}