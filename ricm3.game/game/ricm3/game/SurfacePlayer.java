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
		switch (Direction.entityDir(this, dir)) {
		case NORTH:
			posTourY -= Options.Entity_size * Options.Scale + 1;
			break;
		case SOUTH:
			posTourY += Options.Entity_size * Options.Scale + 1;
			break;
		case EAST:
			posTourX += Options.Entity_size * Options.Scale + 1;
			break;
		case WEST:
			posTourX -= Options.Entity_size * Options.Scale + 1;
			break;
		default:
			return false;
		}
/// RAJOUTER UN TEST DE SI ON A LES RESSOURCES POUR CONSTRUIRE LA TOURELLE  (GOTSTUFF maybe)
		Turret t = new Turret(m_model, posTourX, posTourY, Options.HP[1], m_model.m_sprites.get("tesla"), m_automate,
				m_originWorld);
		t.m_collision = false;
		m_originWorld.m_entities.add(t);
		return true;
	}
}
