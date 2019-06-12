package ricm3.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;

import game.blocks.*;
import ricm3.interpreter.IAutomaton;

public class House extends Ally {

	LinkedList<Block> m_blocks;
	int m_size = 3;

	public House(Model model, int x, int y, int hp, BufferedImage[] sprites, World originWorld) {
		super(model, x, y, hp, sprites, null, originWorld);
		IAutomaton automate = new IAutomaton(Options.selectedAutomata.get(0));
		m_blocks = new LinkedList<Block>();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Block b;
				if (i == 2 && j == 1) {
					b = new Gate(m_model, (int) (j * Options.Entity_size * Options.Scale),
							(int) (i * Options.Entity_size * Options.Scale), 0, sprites, automate, originWorld);
					m_model.m_arrow = new Camera(m_model, b);
				} else {
					b = new Block(m_model, (int) (j * Options.Entity_size * Options.Scale),
							(int) (i * Options.Entity_size * Options.Scale), 0, sprites, automate, originWorld);
				}
				b.m_idsprite = i * 3 + j;
				originWorld.m_entities.add(b);
			}
		}
	}

	@Override
	public void step() {
		m_blocks.get(0).step();
	}

	@Override
	public void paint(Graphics g) {
		Iterator<Block> iter = m_blocks.iterator();
		while (iter.hasNext()) {
			Block b = iter.next();
			Graphics g_child = g.create(b.x(), b.y(), (int) (Options.Entity_size * Options.Scale),
					(int) (Options.Entity_size * Options.Scale));
			b.paint(g_child);
			g_child.dispose();
		}
	}

}
