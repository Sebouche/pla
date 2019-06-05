package ricm3.interpreter;

public class Move extends IAction {
	Direction direction ;
	
	public Move(){
		 this.direction = Direction.FRONT;
	}
	
	Move(Direction direction){
		this.direction = direction ;
	}

	
/*	boolean exec(Entity e){
		e.move(this.direction) ;
	}*/
}