package ricm3.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Hashtable;

import ricm3.interpreter.Direction;
import game.blocks.Copper;
import game.blocks.Iron;
import ricm3.interpreter.IAutomaton;

public class Turret extends Ally {

	List<Enemy> targets;
	Line blitz;
	Enemy target;
	double range = 250;
	long lastHit = 0;

	public Turret(Model model, int x, int y, int hp, BufferedImage[] sprites, IAutomaton automate, World originWorld,
			List<Enemy> t) {
		super(model, x, y, hp, sprites, automate, originWorld);
		m_recipe = new Hashtable<String, Integer>();
		m_recipe.put("Iron", 2);
		m_recipe.put("Copper", 1);
		targets = t;
		target = null;
		m_dmg = Options.TurretDamage;
	}

	@Override
	public boolean pop(Direction d) {
		if ((target == null) || (target.m_hp <= 0) || (distance(target) > range)) {
			target = null;
			Iterator<Enemy> iter = targets.iterator();
			Enemy e;
			double dist = 2 * range;
			double nd;
			while (iter.hasNext()) {
				e = iter.next();
				nd = distance(e);
				if (nd < dist) {
					if (nd <= range) {
						target = e;
					}
					dist = nd;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean hit(Direction d, int power) {
		if(System.currentTimeMillis()>lastHit+500) {
			lastHit = System.currentTimeMillis();
			if (target != null) {
				target.damage_hp(this.m_dmg);
				blitz = new Line(((int)(Options.Entity_size*Options.Scale))/2,0,target.m_x+((int)(Options.Entity_size*Options.Scale))-m_x,target.m_y+((int)(Options.Entity_size*Options.Scale))-m_y);
				return true;
			}	
		}
		return false;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if(blitz!=null) {
			g.setColor(Options.BlitzColor);
			g.drawLine(blitz.p1.x, blitz.p1.y, blitz.p2.x, blitz.p2.y);
		}
	}

}
