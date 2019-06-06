package ricm3.interpreter;

import ricm3.game.GameEntity;

public class Wizz extends IAction{

	Direction dir;
	
	public Wizz() {
		this.dir = Direction.FRONT;
	}
	
	public Wizz(String str) {
		this.dir = Direction.strToDir(str);
	}
	
	public boolean exec(GameEntity e) {
		return e.wizz(dir);
	}
	
}
