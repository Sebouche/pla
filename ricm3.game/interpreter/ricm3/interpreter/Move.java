package ricm3.interpreter;

import ricm3.game.GameEntity;

public class Move extends IAction {
	Direction direction ;
	
	public Move(){
		 this.direction = Direction.FRONT;
	}
	
	public Move(String direction){
		this.direction = Direction.strToDir(direction) ;
	}

	@Override
	public boolean exec(GameEntity e){
		return e.move(this.direction) ;
	}
}