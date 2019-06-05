package ricm3.interpreter;

public class Hit extends IAction {
	Direction direction;
	Integer power;

	public Hit(Direction direction, Integer power) {
		this.direction = direction;
		this.power = power;
	}

	public Hit(Direction direction) {
		this.direction = direction;
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