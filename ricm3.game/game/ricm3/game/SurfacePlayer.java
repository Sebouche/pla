package ricm3.game;

import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;

import game.blocks.Copper;
import game.blocks.Dirt;
import game.blocks.Iron;
import game.blocks.Ladder;
import ricm3.interpreter.Direction;
import ricm3.interpreter.IAutomaton;
import ricm3.interpreter.Keys;

public class SurfacePlayer extends Player {

	boolean m_insideTurret;
	Turret m_controled;

	SurfacePlayer(Model model, int x, int y, int hp, BufferedImage[] sprites, IAutomaton automate, World originWorld) {
		super(model, x, y, hp, sprites, automate, originWorld);
		m_insideTurret = false;
	}

	@Override
	public boolean pop(Direction dir) {
		if (!m_insideTurret) {
			Ally a = null;
			IAutomaton moving = new IAutomaton (Options.Entities.get("Moving"));
			Iterator<Ally> iter = m_model.m_currentworld.m_allies.iterator();
			while (iter.hasNext()) {
				Ally tmp = iter.next();
				if (tmp instanceof Turret) {
					if (distance(tmp) <= 2) {
						a = tmp;
						m_insideTurret = true;
						a.m_automate = moving;
						m_controled = (Turret) a;
						m_model.m_camera.m_watched = a;
						return true;
					}
				}
			}
			return false;
		} else {
			m_controled.m_hp = Options.HP[1];
			m_model.m_camera.m_watched = this;
			m_controled.m_automate = new IAutomaton (Options.Entities.get("Tesla"));
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
		if (m_model.m_player.m_originWorld instanceof SurfaceWorld && blocs().Exist(Copper.class, 1) && blocs().Exist(Iron.class, 2)) {
			blocs().decrements(Copper.class, 1);
			blocs().decrements(Iron.class, 2);
			Turret t = new Turret(m_model, posTourX, posTourY, Options.HP[1], m_model.m_sprites.get("Tesla"),
					new IAutomaton(Options.Entities.get("Tesla")), m_originWorld, m_model.m_surfaceworld.m_enemies);
			t.m_collision = false;
			((SurfaceWorld) m_originWorld).m_tmpadd.add(t);
			m_originWorld.m_allies.add(t);
		}
		return true;
	}
}
