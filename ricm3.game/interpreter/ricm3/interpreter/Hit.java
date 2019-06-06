package ricm3.interpreter;

import ricm3.game.GameEntity;

public class Hit extends IAction {
	Direction direction;
	Integer power;

	public Hit(String direction, Integer power) {
		this.direction = Direction.strToDir(direction);
		this.power = power;
	}

	public Hit(String direction) {
		this.direction = Direction.strToDir(direction);
		this.power = 1;
	}

	public Hit() {
		this.direction = Direction.FRONT;
		this.power = 1;
	}

	boolean exec(GameEntity e) {
		return e.hit(this.direction, max(e.dmgs(), this.power));
	}

	private int max(int dmg, Integer power) {
		if (dmg >= power)
			return dmg;
		return power;
	}
}