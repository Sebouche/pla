package ricm3.interpreter;

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

	/*
	 * boolean exec(Entity e){ e.hit(this.direction, max(e.power,this.power)); }
	 */
}