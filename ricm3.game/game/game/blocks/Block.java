package game.blocks;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Hashtable;

import ricm3.game.*;
import ricm3.interpreter.*;

public class Block extends GameEntity {

	public Block(Model model, int x, int y, int hp, BufferedImage[] sprites, IAutomaton automate, World originWorld) {
		super(model, x, y, hp, sprites, automate, originWorld);
		if (hp == -1) {
			m_breakable = false;
		}
		m_type = Type.OBSTACLE;
	}

	public void set_idsprite(int id) {
		m_idsprite = id;
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(this.m_sprites[m_idsprite], 0, 0, (int) Options.Scale * Options.Entity_size,
				(int) Options.Scale * Options.Entity_size, null);
	}

	@Override
	public boolean pop(Direction dir) {
		if (m_breakable) {
			Fabrication bloc_player = m_model.m_player.blocs();
			bloc_player.increments(this.getClass(), 1);
			int entity_size = (int) (Options.Scale * Options.Entity_size);
			int pos_x = (int) (x() / entity_size);
			int pos_y = (int) (y() / entity_size);
			if (m_originWorld instanceof UndergroundWorld) {
				((UndergroundWorld) m_originWorld).m_grid[pos_y][pos_x] = null;
			}
		}
		return true;
	}

	@Override
	public boolean wizz(Direction dir) {
		return true;
	}
}
