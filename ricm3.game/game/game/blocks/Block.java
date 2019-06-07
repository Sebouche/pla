package game.blocks;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ricm3.game.*;
import ricm3.interpreter.*;

public class Block extends GameEntity {

	public Block(Model model, int x, int y, int hp, BufferedImage[] sprites, IAutomaton automate) {
		super(model, x, y, hp, sprites, automate);
	}
	
	public void set_idsprite(int id) {
		m_idsprite = id;
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(this.m_sprites[m_idsprite], 0, 0, (int) Options.Scale * Options.Entity_size,
				(int) Options.Scale * Options.Entity_size, null);
	}
}
