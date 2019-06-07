package ricm3.game;

import java.awt.image.BufferedImage;

import ricm3.interpreter.Direction;

public class MovingEntity extends GameEntity {

	int m_dx, m_dy;
	int m_damage;

	public MovingEntity(Model model, int x, int y, int hp, BufferedImage[] sprites) {
		super(model, x, y, hp, sprites);
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
		return true;
	}
	
	@Override
	public void step() {
	}
}
