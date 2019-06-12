package ricm3.interpreter;

import ricm3.game.GameEntity;

public enum Direction {

	NORTH, SOUTH, EAST, WEST, FRONT, BACK, LEFT, RIGHT, NONE;

	public static Direction strToDir(String str) {
		switch (str) {
		case ("N"):
			return Direction.NORTH;
		case ("S"):
			return Direction.SOUTH;
		case ("E"):
			return Direction.EAST;
		case ("O"):
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
			return Direction.NONE;
		}

	}

	public static Direction entityDir(GameEntity e, Direction dir) {
		switch (dir) {
		case FRONT:
			return e.dir();
		case BACK:
			switch (e.dir()) {
			case NORTH:
				return Direction.SOUTH;
			case SOUTH:
				return Direction.NORTH;
			case EAST:
				return Direction.WEST;
			case WEST:
				return Direction.EAST;
			default:
				return Direction.NORTH;
			}
		case LEFT:
			switch (e.dir()) {
			case NORTH:
				return Direction.WEST;
			case SOUTH:
				return Direction.EAST;
			case EAST:
				return Direction.SOUTH;
			case WEST:
				return Direction.NORTH;
			default:
				return Direction.NORTH;
			}
		case RIGHT:
			switch (e.dir()) {
			case NORTH:
				return Direction.EAST;
			case SOUTH:
				return Direction.WEST;
			case EAST:
				return Direction.NORTH;
			case WEST:
				return Direction.SOUTH;
			default:
				return Direction.NORTH;
			}
		default:
			return dir;
		}

	}

}
