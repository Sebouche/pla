package ricm3.game;

import java.awt.image.BufferedImage;

import ricm3.interpreter.Direction;
import ricm3.interpreter.IAutomaton;

public class UndergroundPlayer extends Player {

	UndergroundPlayer(Model model, int x, int y, int hp, BufferedImage[] sprites, IAutomaton automate,
			World originWorld) {
		super(model, x, y, hp, sprites, automate, originWorld);
	}

	@Override
	public boolean pop(Direction dir) {
		int entity_size = (int) (Options.Scale * Options.Entity_size);
		int pos_x = (int) (m_model.m_player.m_x / entity_size);
		int pos_y = (int) (m_model.m_player.m_y / entity_size);
		UndergroundWorld w = (UndergroundWorld) m_originWorld;
		switch (Direction.entityDir(this, dir)) {
		case NORTH:
			if (w.m_grid[pos_y - 1][pos_x] != null) {
				w.m_grid[pos_y - 1][pos_x].damage_hp(m_dmg);
			}
			break;
		case SOUTH:
			if (w.m_grid[pos_y + 1][pos_x] != null) {
				w.m_grid[pos_y + 1][pos_x].damage_hp(m_dmg);
			}
			break;
		case EAST:
			if (w.m_grid[pos_y][Math.floorMod((pos_x + 1), 60)] != null) {
				w.m_grid[pos_y][Math.floorMod((pos_x + 1), 60)].damage_hp(m_dmg);
			}
			break;
		case WEST:
			if (w.m_grid[pos_y - 1][Math.floorMod((pos_x - 1), 60)] != null) {
				w.m_grid[pos_y - 1][Math.floorMod((pos_x - 1), 60)].damage_hp(m_dmg);
			}
			break;
		default:
			return false;
		}
		return true;
	}

	public boolean wizz() {
		int entity_size = (int) (Options.Scale * Options.Entity_size);
		int pos_x = (int) (m_model.m_player.m_x / entity_size);
		int pos_y = (int) (m_model.m_player.m_y / entity_size);
		UndergroundWorld w = (UndergroundWorld) m_originWorld;
/// RAJOUTER UN TEST DE SI ON A LES RESSOURCES POUR CONSTRUIRE L echelle  (GOTSTUFF maybe)
		w.m_grid[pos_y][pos_x]=new Ladder(m_model, pos_x*entity_size, pos_y*entity_size, Options.HP[1],
				m_model.m_sprites.get("block"), m_automate, w);
		return true;
	}
}
