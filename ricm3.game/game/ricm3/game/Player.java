package ricm3.game;

import java.awt.image.BufferedImage;
import java.util.Hashtable;

import ricm3.interpreter.Direction;
import ricm3.interpreter.IAutomaton;
import ricm3.interpreter.Type;

public class Player extends Ally {
	int m_spritevariation=8;
	Fabrication blocs;

	Fabrication blocs;
	
	public Player(Model model, int x, int y, int hp, BufferedImage[] sprites, IAutomaton automate, World originWorld) {
		super(model, x, y, hp, sprites, automate, originWorld);
		m_type = Type.PLAYER;
		blocs = new Fabrication();
	}

	@Override
	public boolean jump(Direction dir) {
		m_originWorld.changeWorld();
		return true;
	}

	@Override
	public void step() {
		m_automate.step(this);
	}

	@Override
	public boolean move(Direction dir) {
		Direction absoldir= Direction.entityDir(this, dir);
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
				m_spritechanger=(m_spritechanger+1)%m_spritevariation;
				m_idsprite=m_basesprite+m_spritechanger;
			}
			m_elapsed++;
		}
		return true;
	}
	
	@Override
	public boolean Throw(Direction dir) {
	//	m_model.menu_fabrication.show(m_view, (int) (m_view.getWidth() / 2 + Options.Entity_size * Options.Scale), m_view.getHeight() / 2);
		return true;
	}

   public Fabrication bloc(){
		return this.blocs;
	}
	
}
