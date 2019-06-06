package ricm3.interpreter;

public class Pick extends IAction {

	Direction dir;

	public Pick() {
		this.dir = Direction.FRONT;
	}

	public Pick(String str) {
		this.dir = Direction.strToDir(str);
	}
}
