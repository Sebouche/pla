package ricm3.interpreter;

public class Wizz extends IAction{

	Direction dir;
	
	public Wizz() {
		this.dir = Direction.FRONT;
	}
	
	public Wizz(String str) {
		this.dir = Direction.strToDir(str);
	}
	
}
