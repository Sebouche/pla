package ricm3.game;

import java.awt.image.BufferedImage;
import java.util.Iterator;

import ricm3.interpreter.Direction;
import ricm3.interpreter.IAutomaton;

public class MovingEntity extends GameEntity {

	int m_dx, m_dy;

	public MovingEntity(Model model,int x,int y,int hp,BufferedImage[] sprites,IAutomaton automate, World originWorld) {
		super(model,x,y,hp,sprites,automate,originWorld);
	}

	boolean collision(GameEntity ge, int x, int y) {
		boolean coll;
		coll = true;
		double entity_size = Options.Scale * Options.Entity_size;
		if ((this.m_y + y > ge.m_y + entity_size || this.m_y + entity_size + y < ge.m_y)) { // collision
			coll = false;
		}
		if ((this.m_x + x > ge.m_x + entity_size || this.m_x + entity_size + x < ge.m_x)) {
			coll = false;
		}
		if (coll == true) {
			return true;
		}
		return false;
	}

	@Override
	public boolean move(Direction dir) {
		m_dx = 0;
		m_dy = 0;
		switch(Direction.entityDir(this, dir)) {
		case NORTH:
			m_dy = -1;
			break;
		case SOUTH:
			m_dy = 1;
			break;
		case WEST:
			m_dx = -1;
			break;
		case EAST:
			m_dx = 1;
			break;
		default:
			break;
		}
		boolean coll = false;
		Iterator<GameEntity> iter = m_originWorld.m_entities.iterator();
		GameEntity E;
		while (iter.hasNext()) {
			E = iter.next();
			if (collision(E, m_dx, 0)) {
				m_dx = 0;
				coll = true;
			}
			if (collision(E, 0, m_dy)) {
				m_dy = 0;
				coll = true;
			}
		}
		m_x += m_dx;
		m_y += m_dy;
		m_dx = 0;
		m_dy = 0;
		return !coll;
	}


}
