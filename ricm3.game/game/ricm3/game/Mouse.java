package ricm3.game;

import java.awt.image.BufferedImage;
import java.util.List;

import ricm3.interpreter.Direction;
import ricm3.interpreter.IAutomaton;

public class Mouse extends Enemy {

	public Mouse(Model model, int x, int y, BufferedImage[] sprites,IAutomaton automate,World originWorld, List<GameEntity> t) {
		super(model, x, y, Options.HP[2], sprites,automate, originWorld, t);
	}
	
	public boolean hit() {
		return this.m_model.m_currentworld.Hit(this, this.m_dir);
	}
	
	@Override
	public boolean hit(Direction dir, int power) {
		switch (dir) {
		case NORTH:
		case SOUTH:
		case EAST:
		case WEST:
			return this.m_model.m_currentworld.Hit(this, dir);
		case FRONT:
			return this.m_model.m_currentworld.Hit(this, this.m_dir);
		case BACK:
			switch (this.m_dir) {
			case NORTH:
				return this.m_model.m_currentworld.Hit(this, Direction.SOUTH);
			case SOUTH:
				return this.m_model.m_currentworld.Hit(this, Direction.NORTH);
			case EAST:
				return this.m_model.m_currentworld.Hit(this, Direction.WEST);
			case WEST:
				return this.m_model.m_currentworld.Hit(this, Direction.EAST);
			default:
				return false;
			}
		case LEFT:
			switch (this.m_dir) {
			case NORTH:
				return this.m_model.m_currentworld.Hit(this, Direction.WEST);
			case SOUTH:
				return this.m_model.m_currentworld.Hit(this, Direction.EAST);
			case EAST:
				return this.m_model.m_currentworld.Hit(this, Direction.NORTH);
			case WEST:
				return this.m_model.m_currentworld.Hit(this, Direction.SOUTH);
			default:
				return false;
			}
		case RIGHT:
			switch (this.m_dir) {
			case NORTH:
				return this.m_model.m_currentworld.Hit(this, Direction.EAST);
			case SOUTH:
				return this.m_model.m_currentworld.Hit(this, Direction.WEST);
			case EAST:
				return this.m_model.m_currentworld.Hit(this, Direction.SOUTH);
			case WEST:
				return this.m_model.m_currentworld.Hit(this, Direction.NORTH);
			default:
				return false;
			}
		default:
			return false;
		}
	}

}
