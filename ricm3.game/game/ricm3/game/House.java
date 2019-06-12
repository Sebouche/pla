package ricm3.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.blocks.*;
import ricm3.interpreter.IAutomaton;

public class House extends Ally {

	Block[] m_blocks = new Block[9];
	int m_size = 3;
	int HPmax = 10000;

	public House(Model model, int x, int y, int hp, BufferedImage[] sprites, World originWorld) {
		super(model, x, y, hp, sprites, null, originWorld);
		IAutomaton automate = new IAutomaton(Options.selectedAutomata.get(0));
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Block b;
				if (i == 2 && j == 1) {
					b = new Gate(m_model, (int) (j * Options.Entity_size * Options.Scale),
							(int) (i * Options.Entity_size * Options.Scale), 0, sprites, automate, originWorld);
				} else {
					b = new Block(m_model, (int) (j * Options.Entity_size * Options.Scale),
							(int) (i * Options.Entity_size * Options.Scale), 0, sprites, automate, originWorld);
				}
				b.m_idsprite = i * 3 + j;
				m_blocks[3*i+j] = b;
				originWorld.m_entities.add(b);
			}
		}
	}

	@Override
	public void step() {
		m_blocks[7].step();
		m_hp = HPmax;
		for(int i = 0; i<9; i++) {
			m_hp -= m_blocks[i].hps();
		}
		if(m_hp<=0) {
			m_model.endgame();
		}
	}

	@Override
	public void paint(Graphics g) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Graphics g_child = g.create(m_blocks[3 * i + j].x(), m_blocks[3 * i + j].y(),
						(int) (Options.Entity_size * Options.Scale), (int) (Options.Entity_size * Options.Scale));
				m_blocks[3 * i + j].paint(g_child);
				g_child.dispose();
			}
		}
	}

}
