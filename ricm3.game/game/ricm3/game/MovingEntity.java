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
		coll = false;
		double entity_size = Options.Scale * Options.Entity_size;
		if (!(this.m_y + y > ge.m_y + entity_size || this.m_y + entity_size + y < ge.m_y)) { // collision
			this.m_dy = 0;
			coll = true;
		}
		if (!(this.m_x + x > ge.m_x + entity_size || this.m_x + entity_size + x < ge.m_x)) {
			this.m_dx = 0;
			coll = true;
		}
		if (coll == true) {
			return true;
		}
		return false;
	}

	@Override
	public boolean move(Direction dir) {
		Iterator<GameEntity> iter = m_originWorld.m_entities.iterator();
		GameEntity E;
		while (iter.hasNext()) {
			E = iter.next();
			collision(E, m_dx, m_dy);
		}
		return true;
	}

	@Override
	public void step() {
	}
}
