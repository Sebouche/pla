package ricm3.interpreter;

import ricm3.game.GameEntity;

public class Cell extends ICondition {
	Direction direction;
	Type kind;
	int distance;

	public Cell(String direction, String kind, int distance) {
		this.direction = Direction.strToDir(direction);
		this.kind = Type.strToType(kind);
		this.distance = distance;
	}

	public Cell(String string, String kind) {
		this.direction = Direction.strToDir(string);
		this.kind = Type.strToType(kind);
		this.distance = 1;
	}

//	boolean eval(GameEntity e) { 
//		return is_Kind(this.kind, this.direction, this.distance, e.position, e.map) ;
//	}
}
