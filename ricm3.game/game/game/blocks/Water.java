package game.blocks;

import java.awt.image.BufferedImage;
import java.util.Random;
import ricm3.game.UndergroundWorld;
import ricm3.game.Model;
import ricm3.game.Mouse;
import ricm3.game.Options;
import ricm3.game.World;
import ricm3.interpreter.IAutomaton;

public class Water extends Block {

	public Water(Model model, int x, int y, int hp, BufferedImage[] sprites, IAutomaton automate, World originWorld) {
		super(model, x, y, hp, sprites, automate, originWorld);
		set_idsprite(9);
	}

	@Override
	public boolean egg() {
		if (hps() > 0) {
			int pos_x = (int) (x() / (Options.Entity_size * Options.Scale));
			int pos_y = (int) (y() / (Options.Entity_size * Options.Scale));
			UndergroundWorld w = (UndergroundWorld) world();
			if (w.m_grid[Math.floorMod((pos_y + 1), 60)][Math.floorMod((pos_x), 60)] == null) {
				Water wat=new Water(m_model,
						(int) (pos_x * (Options.Entity_size * Options.Scale)),
						(int) ((Math.floorMod((pos_y + 1), 60)) * (Options.Entity_size * Options.Scale)), hps(),
						m_sprites, m_automate, w);
				wat.m_collision=false;
				w.m_grid[Math.floorMod((pos_y + 1), 60)][Math.floorMod((pos_x), 60)] = wat;
				;
				w.m_grid[pos_y][Math.floorMod((pos_x), 60)] = null;
				return true;
			}
			if (w.m_grid[pos_y][Math.floorMod((pos_x - 1), 60)] == null) {
				Water wat=new Water(m_model,
						(int) ((pos_x - 1) * (Options.Entity_size * Options.Scale)),
						(int) (pos_y * (Options.Entity_size * Options.Scale)), hps() - 1, m_sprites, m_automate, w);
				wat.m_collision=false;
				w.m_grid[pos_y][Math.floorMod((pos_x - 1), 60)] = wat;
				return true;
			}
			if (w.m_grid[pos_y][Math.floorMod((pos_x + 1), 60)] == null) {
				Water wat=new Water(m_model,
						(int) ((pos_x + 1) * (Options.Entity_size * Options.Scale)),
						(int) (pos_y * (Options.Entity_size * Options.Scale)), hps() - 1, m_sprites, m_automate, w);
				wat.m_collision=false;
				w.m_grid[pos_y][Math.floorMod((pos_x + 1), 60)] = wat;
				return true;
			}
			if (w.m_grid[Math.floorMod((pos_y - 1), 60)][Math.floorMod((pos_x), 60)] == null) {
				Water wat =new Water(m_model,
						(int) (pos_x * (Options.Entity_size * Options.Scale)),
						(int) (Math.floorMod((pos_y - 1), 60) * (Options.Entity_size * Options.Scale)), hps() - 1,
						m_sprites, m_automate, w);
				wat.m_collision=false;
				w.m_grid[Math.floorMod((pos_y - 1), 60)][Math.floorMod((pos_x), 60)] = wat;
				return true;
			}
		}
		return false;
	}
}