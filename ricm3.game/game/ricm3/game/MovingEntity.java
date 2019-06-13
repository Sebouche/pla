package ricm3.game;

import java.awt.image.BufferedImage;
import ricm3.interpreter.Direction;
import ricm3.interpreter.IAutomaton;

public class MovingEntity extends GameEntity {
	Direction m_lastdir;
	Direction m_lastdir2;
	Direction m_lastdir3;
	int m_elapsed;
	int m_spritechanger;
	int m_basesprite=0;
	int m_spritevariation;




	public MovingEntity(Model model, int x, int y, int hp, BufferedImage[] sprites, IAutomaton automate,
			World originWorld) {
		super(model, x, y, hp, sprites, automate, originWorld);
	}

	boolean collision(GameEntity ge, int x, int y) {
		if (ge.m_collision) {
			boolean coll;
			coll = true;
			double entity_size = Options.Scale * Options.Entity_size;
			if ((this.m_y + y >= ge.m_y + entity_size || this.m_y + entity_size + y <= ge.m_y)) { // collision
				coll = false;
			}
			if ((this.m_x + x >= ge.m_x + entity_size || this.m_x + entity_size + x <= ge.m_x)) {
				coll = false;
			}
			if (coll == true) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean move(Direction dir) {
		switch (Direction.entityDir(this, dir)) {
		case NORTH:
			this.m_dir = Direction.NORTH;
			m_dy = -1;
			break;
		case SOUTH:
			this.m_dir = Direction.SOUTH;
			m_dy = 1;
			break;
		case WEST:
			this.m_dir = Direction.WEST;
			m_dx = -1;
			break;
		case EAST:
			this.m_dir = Direction.EAST;
			m_dx = 1;
			break;
		default:
			break;
		}
		m_x += m_dx;
		m_y += m_dy + m_grav;
		m_dx=0;
		m_dy=0;
		return true;
	}

}
