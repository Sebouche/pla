package ricm3.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import ricm3.interpreter.IAutomaton;

public class Block extends GameEntity {

	public Block(Model model, int x, int y, int hp, BufferedImage[] sprites,IAutomaton automate,World originWorld) {
		super(model, x, y, hp, sprites,automate,originWorld);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(m_sprites[m_idsprite], 0, 0, (int) Options.Scale * Options.Entity_size,
				(int) Options.Scale * Options.Entity_size, null);
	}
	
	public void set_idsprite(int id) {
		m_idsprite=id;
	}
}
