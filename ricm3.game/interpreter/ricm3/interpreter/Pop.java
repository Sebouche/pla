package ricm3.interpreter;

public class Pop extends IAction{
	
	Direction dir;

	public Pop() {
		this.dir = Direction.FRONT;
	}

	public Pop(String dir) {
		this.dir = Direction.strToDir(dir);
	}
	
}
