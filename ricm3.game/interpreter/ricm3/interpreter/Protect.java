package ricm3.interpreter;

public class Protect extends IAction {

	Direction dir;

	public Protect() {
		this.dir = Direction.FRONT;
	}

	public Protect(String str) {
		this.dir = Direction.strToDir(str);
	}

}
