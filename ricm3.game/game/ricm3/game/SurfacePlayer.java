package ricm3.game;

import java.awt.image.BufferedImage;

import ricm3.interpreter.Direction;
import ricm3.interpreter.IAutomaton;

public class SurfacePlayer extends Player {

	SurfacePlayer(Model model, int x, int y, int hp, BufferedImage[] sprites, IAutomaton automate, World originWorld) {
		super(model, x, y, hp, sprites, automate, originWorld);
	}

	@Override
	public boolean pop(Direction dir) {
		return true;
	}

	public boolean wizz(Direction dir) {
		int posTourX = this.m_x;
		int posTourY = this.m_y;
		switch (dir) {
		case NORTH:
			posTourY -= Options.Entity_size * Options.Scale;
			break;
		case SOUTH:
			posTourY += Options.Entity_size * Options.Scale;
			break;
		case EAST:
			posTourX += Options.Entity_size * Options.Scale;
			break;
		case WEST:
			posTourX -= Options.Entity_size * Options.Scale;
			break;
	/*	case FRONT:
		case LEFT:
		case RIGHT:
		case BACK:
		default:
			return false;*/
		}
		new Turret(m_model, posTourX, posTourY, Options.HP[1], m_sprites, m_automate, m_originWorld);
		return true;
	}
}
