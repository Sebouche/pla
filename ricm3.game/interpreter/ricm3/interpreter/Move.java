package ricm3.interpreter;

public class Move extends IAction {
	Direction direction ;
	
	public Move(){
		 this.direction = Direction.FRONT;
	}
	
	public Move(String direction){
		this.direction = Direction.strToDir(direction) ;
	}


	
/*	boolean exec(Entity e){
		e.move(this.direction) ;
	}*/
}