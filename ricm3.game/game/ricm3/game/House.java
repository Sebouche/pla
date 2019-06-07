package ricm3.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;

public class House extends Ally {

	LinkedList<Block> m_blocks;
	int m_size=3;

	public House(Model model, int x, int y, int hp, BufferedImage[] sprites) {
		super(model, x, y, hp, sprites);
		m_blocks = new LinkedList<Block>();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Block b = new Block(m_model, (int) (j * Options.Entity_size * Options.Scale),
						(int) (i * Options.Entity_size * Options.Scale), 0, sprites);
				b.m_idsprite = i * 3 + j;
				m_blocks.add(b);
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		Iterator<Block> iter = m_blocks.iterator();
		while (iter.hasNext()) {
			Block b = iter.next();
			Graphics g_child = g.create(b.m_x, b.m_y, (int) (Options.Entity_size * Options.Scale),
					(int) (Options.Entity_size * Options.Scale));
			b.paint(g_child);
			g_child.dispose();
		}
	}

}
