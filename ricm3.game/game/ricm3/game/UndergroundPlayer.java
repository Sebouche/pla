package ricm3.game;

import java.awt.image.BufferedImage;
import game.blocks.*;
import ricm3.interpreter.Direction;
import ricm3.interpreter.IAutomaton;
import java.util.Set;

public class UndergroundPlayer extends Player {

	UndergroundPlayer(Model model, int x, int y, int hp, BufferedImage[] sprites, IAutomaton automate,
			World originWorld) {
		super(model, x, y, hp, sprites, automate, originWorld);
		m_dmg = 5;
	}

	@Override
	public boolean pop(Direction dir) {
		int entity_size = (int) (Options.Scale * Options.Entity_size);
		int pos_x = (int) (m_model.m_player.m_x / entity_size);
		int pos_y = (int) (m_model.m_player.m_y / entity_size);
		UndergroundWorld w = (UndergroundWorld) m_originWorld;
		switch (Direction.entityDir(this, dir)) {
		case NORTH:
			if (w.m_grid[pos_y - 1][pos_x] != null && w.m_grid[pos_y - 1][pos_x].m_breakable) {
				w.m_grid[pos_y - 1][pos_x].damage_hp(m_dmg);
			}
			break;
		case SOUTH:
			if (w.m_grid[pos_y + 1][pos_x] != null && w.m_grid[pos_y + 1][pos_x].m_breakable) {
				w.m_grid[pos_y + 1][pos_x].damage_hp(m_dmg);
			}
			break;
		case EAST:
			if (w.m_grid[pos_y][Math.floorMod((pos_x + 1), 60)] != null
					&& w.m_grid[pos_y][Math.floorMod((pos_x + 1), 60)].m_breakable) {
				w.m_grid[pos_y][Math.floorMod((pos_x + 1), 60)].damage_hp(m_dmg);
			}
			break;
		case WEST:
			if (w.m_grid[pos_y][Math.floorMod((pos_x - 1), 60)] != null
					&& w.m_grid[pos_y][Math.floorMod((pos_x - 1), 60)].m_breakable) {
				w.m_grid[pos_y][Math.floorMod((pos_x - 1), 60)].damage_hp(m_dmg);
			}
			break;
		default:
			return false;
		}
		return true;
	}

	@Override
	public boolean wizz(Direction dir) {
		int entity_size = (int) (Options.Scale * Options.Entity_size);
		int pos_x = (int) (m_model.m_player.m_x / entity_size);
		int pos_y = (int) (m_model.m_player.m_y / entity_size);
		UndergroundWorld w = (UndergroundWorld) m_originWorld;
		if (w.m_grid[pos_y][Math.floorMod((pos_x), 60)] == null && blocs().Exist(Dirt.class, 1)) {
			blocs().increments(Ladder.class, 1);
			blocs().decrements(Dirt.class, 1);
			Ladder L=new Ladder(m_model, Math.floorMod((pos_x), 60) * entity_size,
					pos_y * entity_size, 100, m_model.m_sprites.get("Block"),
					new IAutomaton(Options.Entities.get("Block")), w);
			L.m_collision=false;
			w.m_grid[pos_y][Math.floorMod((pos_x), 60)] = L;
		}
		return true;
	}
}
