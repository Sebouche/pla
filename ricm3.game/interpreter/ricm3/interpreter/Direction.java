package ricm3.interpreter;

public enum Direction {

	NORTH, SOUTH, EAST, WEST, FRONT, BACK, LEFT, RIGHT;

	public static Direction strToDir(String str) {
		switch (str) {
		case ("N"):
			return Direction.NORTH;
		case ("S"):
			return Direction.SOUTH;
		case ("E"):
			return Direction.EAST;
		case ("W"):
			return Direction.WEST;
		case ("F"):
			return Direction.FRONT;
		case ("B"):
			return Direction.BACK;
		case ("L"):
			return Direction.LEFT;
		case ("R"):
			return Direction.RIGHT;
		default:
			return Direction.FRONT;
		}

	}

}
