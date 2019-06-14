package ricm3.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.blocks.*;
import ricm3.interpreter.IAutomaton;
import ricm3.interpreter.Type;

public class House extends Ally {

	Block[] m_blocks = new Block[9];
	int m_size = 3;
	int HPmax = 10000;
	int previousHP;

	public House(Model model, int x, int y, int hp, BufferedImage[] sprites, World originWorld) {
		super(model, x, y, hp, sprites, null, originWorld);
		IAutomaton automate = new IAutomaton(Options.Entities.get("Wall"));
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Block b;
				if (i == 2 && j == 1) {
					b = new Gate(m_model, (int) (j * Options.Entity_size * Options.Scale) + x,
							(int) (i * Options.Entity_size * Options.Scale) + y, -1, sprites, automate, originWorld);
					m_model.m_arrow = new Camera(m_model, b);
				} else {
					b = new Block(m_model, (int) (j * Options.Entity_size * Options.Scale) + x,
							(int) (i * Options.Entity_size * Options.Scale) + y, -1, sprites, automate, originWorld);
					b.m_type = Type.PICKUP;
				}
				b.m_idsprite = i * 3 + j;
				m_blocks[3 * i + j] = b;
				originWorld.m_entities.add(b);

			}
		}
		originWorld.m_allies.add(this);
		originWorld.m_entities.add(this);
		m_type = Type.PICKUP;
	}

	@Override
	public void step() {
		m_blocks[7].step();
		if (m_hp <= 0) {
			m_model.m_gameon = false;
			m_model.endgame();
		}
	}

	@Override
	public double distance(GameEntity e) {
		double dx1, dx2, dy1, dy2;
		dx1 = m_x - (e.m_x + Options.Entity_size * Options.Scale);
		dx2 = e.m_x - (m_x + Options.Entity_size * Options.Scale * 3);
		dy1 = m_y - (e.m_y + Options.Entity_size * Options.Scale);
		dy2 = e.m_y - (m_y + Options.Entity_size * Options.Scale * 3);
		if ((dx1 <= 0) && (dx2 <= 0) && (dy1 <= 0) && (dy2 <= 0)) {
			return 0;
		}
		if ((dx1 <= 0) && (dx2 <= 0)) {
			return Math.max(dy1, dy2);
		} else if ((dy1 <= 0) && (dy2 <= 0)) {
			return Math.max(dx1, dx2);
		}
		if (dx1 > 0) {
			if (dy1 > 0) {
				return Math.sqrt(dx1 * dx1 + dy1 * dy1);
			} else if (dy2 > 0) {
				return Math.sqrt(dx1 * dx1 + dy2 * dy2);
			}
		} else if (dx2 > 0) {
			if (dy1 > 0) {
				return Math.sqrt(dx2 * dx2 + dy1 * dy1);
			} else if (dy2 > 0) {
				return Math.sqrt(dx2 * dx2 + dy2 * dy2);
			}
		}
		return -1;
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
		g.setColor(new Color(250, 2, 2));
		g.setFont(new Font("HOUSEHP", 1, 8));
		g.drawString("HP=" + m_hp, 10, 10);
	}

}
