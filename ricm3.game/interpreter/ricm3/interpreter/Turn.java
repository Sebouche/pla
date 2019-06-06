package ricm3.interpreter;

public class Turn extends IAction {

	Direction dir;

	public Turn() {
		this.dir = Direction.FRONT;
	}

	public Turn(String str) {
		Direction tmp = Direction.strToDir(str);
		if (tmp != Direction.BACK && tmp != Direction.LEFT && tmp != Direction.RIGHT) {
			this.dir = tmp;
		} else {
			this.dir = Direction.FRONT;
		}
	}
}
