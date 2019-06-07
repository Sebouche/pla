package ricm3.interpreter;

import ricm3.game.GameEntity;

public class Jump extends IAction{

	Direction dir;
	
	public Jump() {
		this.dir = Direction.FRONT;
	}

	public Jump(String str) {
		this.dir = Direction.strToDir(str);
	}
	
	@Override
	public boolean exec(GameEntity e) {
		return e.jump(dir);
	}
}
