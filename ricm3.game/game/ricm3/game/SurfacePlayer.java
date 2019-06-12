package ricm3.game;

import java.awt.image.BufferedImage;
import java.util.Iterator;

import ricm3.interpreter.Direction;
import ricm3.interpreter.IAutomaton;

public class SurfacePlayer extends Player {
	
	boolean m_insideTurret;
	Turret m_controled;

	SurfacePlayer(Model model, int x, int y, int hp, BufferedImage[] sprites, IAutomaton automate, World originWorld) {
		super(model, x, y, hp, sprites, automate, originWorld);
		m_insideTurret = false;
	}

	@Override
	public boolean pop(Direction dir) {	
		if(!m_insideTurret) {
			IAutomaton moving = Options.Entities.get("Moving");
			Iterator <Ally> iter = m_model.m_currentworld.m_allies.iterator();
			while(iter.hasNext()) {
				Ally a = iter.next();
				if(a instanceof Turret) {
					if(distance(a)<=0) {
						a.m_automate = moving;
						m_insideTurret = true;
						m_controled = (Turret)a;
						m_model.m_camera.m_watched = a;
						return true;
					}
				}
			}
			return false;
		}
		else {
			m_controled.m_hp = Options.HP[1];
			m_model.m_camera.m_watched = this;
			m_controled.m_automate = Options.Entities.get("tesla");
			m_insideTurret = false;
			return true;
		}
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

		Turret t = new Turret(m_model, posTourX, posTourY, Options.HP[1], m_model.m_sprites.get("tesla"), Options.Entities.get("tesla"),
				m_originWorld, m_model.m_surfaceworld.m_enemies);
		t.m_collision = false;
		((SurfaceWorld)m_originWorld).m_tmp_ent.add(t);
		m_originWorld.m_allies.add(t);
		return true;
	}
}
