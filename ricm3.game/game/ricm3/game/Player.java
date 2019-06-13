package ricm3.game;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.RescaleOp;
import java.awt.image.WritableRaster;
import java.util.Hashtable;
import java.util.LinkedList;

import ricm3.interpreter.Direction;
import ricm3.interpreter.IAutomaton;
import ricm3.interpreter.Keys;
import ricm3.interpreter.Type;

public class Player extends Ally {
	int m_spritevariation = 8;
	Fabrication blocs;
	boolean m_hit = false;
	int m_length=0;
	public Player(Model model, int x, int y, int hp, BufferedImage[] sprites, IAutomaton automate, World originWorld) {
		super(model, x, y, hp, sprites, automate, originWorld);
		blocs = new Fabrication();
		m_type = Type.PLAYER;
	}

	@Override
	public boolean jump(Direction dir) {
		m_model.m_player.m_originWorld.changeWorld();
		return true;
	}

	@Override
	public void step() {
		m_automate.step(this);
	}

	@Override
	public boolean move(Direction dir) {
		Direction absoldir = Direction.entityDir(this, dir);
		super.move(dir);
		if (absoldir != m_lastdir) {
			if (absoldir == Direction.EAST) {
				m_basesprite = 18;
			}
			if (absoldir == Direction.WEST) {
				m_basesprite = 26;
			}
			if (absoldir == Direction.NORTH) {
				m_basesprite = 8;
			}
			if (absoldir == Direction.SOUTH) {
				m_basesprite = 0;
			}
			m_lastdir = absoldir;
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

	public Fabrication blocs() {
		return blocs;
	}

	@Override
	public boolean Throw(Direction dir) {
		m_model.m_keys = new LinkedList<Keys>();
		m_model.fabricationMenu.show(m_model.m_view,
				(int) (m_model.m_view.getWidth() / 2 + Options.Entity_size * Options.Scale),
				m_model.m_view.getHeight() / 2);
		return true;
	}

	@Override
	public boolean pick(Direction dir) {
		m_model.m_gameon = false;
		m_model.endgame();
		return true;
	}

	@Override
	public void paint(Graphics g) {
		if (m_hit && m_length<=30) {
		    BufferedImage hitted = new BufferedImage(m_sprites[m_idsprite].getWidth(), m_sprites[m_idsprite].getHeight(), m_sprites[m_idsprite].getType());
		    Graphics g2 = hitted.getGraphics();
		    g2.drawImage(m_sprites[m_idsprite], 0, 0, null);
		    g2.dispose();
			RescaleOp rescaleOp = new RescaleOp(2.0f, 40.0f, null);
			rescaleOp.filter(hitted, hitted);
			g.drawImage(hitted, 0, 0, (int) Options.Scale * Options.Entity_size,
					(int) Options.Scale * Options.Entity_size, null);
			m_length++;
		} else {
			m_length=0;
			m_hit = false;
			g.drawImage(m_sprites[m_idsprite], 0, 0, (int) Options.Scale * Options.Entity_size,
					(int) Options.Scale * Options.Entity_size, null);
		}
	}
}
