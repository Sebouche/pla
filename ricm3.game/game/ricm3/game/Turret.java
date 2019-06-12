package ricm3.game;

import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.List;

import ricm3.interpreter.Direction;
import ricm3.interpreter.IAutomaton;

public class Turret extends Ally {

	List<Enemy> targets;
	Enemy target;
	double range;

	public Turret(Model model, int x, int y, int hp, BufferedImage[] sprites, IAutomaton automate, World originWorld,
			List<Enemy> t) {
		super(model, x, y, hp, sprites, automate, originWorld);
		targets = t;
		target = null;
	}

	@Override
	public boolean pop(Direction d) {
		if ((target == null) || (target.m_hp <= 0) || (distance(target) > range)) {
			target = null;
			Iterator<Enemy> iter = targets.iterator();
			Enemy e;
			double dist = 1000000;
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

	public boolean hit(Direction d) {
		if (target != null) {
			target.damage_hp(this.m_dmg);
			return true;
		}
		return false;
	}
	
	public boolean move(Direction dir) {
		super.move(dir);
		System.out.println("cc");
		return true;
	}
}
