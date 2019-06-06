package ricm3.interpreter;

public class Jump extends IAction{

	Direction dir;
	
	public Jump() {
		this.dir = Direction.FRONT;
	}

	public Jump(String str) {
		this.dir = Direction.strToDir(str);
	}
}
