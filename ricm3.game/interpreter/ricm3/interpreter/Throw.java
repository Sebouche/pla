package ricm3.interpreter;

public class Throw extends IAction{

	Direction dir;
	
	public Throw() {
		this.dir= Direction.FRONT;
	}

	public Throw(String str) {
		this.dir = Direction.strToDir(str);
	}
	
}
