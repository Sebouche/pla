package game.blocks;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Hashtable;

import ricm3.game.*;
import ricm3.interpreter.*;

public class Block extends GameEntity {
	boolean m_breakable=true;


	public Block(Model model, int x, int y, int hp, BufferedImage[] sprites, IAutomaton automate, World originWorld) {
		super(model, x, y, hp, sprites, automate, originWorld);
		if(hp==-1) {
			m_breakable=false;
		}
	}
	
	public void set_idsprite(int id) {
		m_idsprite = id;
	}


	@Override
	public void paint(Graphics g) {
		g.drawImage(this.m_sprites[m_idsprite], 0, 0, (int) Options.Scale * Options.Entity_size,
				(int) Options.Scale * Options.Entity_size, null);
	}
	public void Pop() {
		
		Fabrication bloc_player = m_model.m_player.bloc();
    		bloc_player.put(this, bloc_player.get(this)+1);
    	
	}
    		
    	
	
	
	public void Wizz() {
		this.m_idsprite++;
		if (this.m_idsprite>=this.m_sprites.length) {
			this.m_idsprite=0;
		}
		
	}
}
