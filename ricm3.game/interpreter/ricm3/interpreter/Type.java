package ricm3.interpreter;

public enum Type {

	ADVERSAIRE, DANGER, GATE, JUMPING_ELEM, MISSILE, OBSTACLE, PICKUP, TEAM, VOID, PLAYER, ANYTHING;

	public static Type strToType(String str) {
		switch (str) {
		case ("A"):
			return Type.ADVERSAIRE;
		case ("D"):
			return Type.DANGER;
		case ("G"):
			return Type.GATE;
		case ("J"):
			return Type.JUMPING_ELEM;
		case ("M"):
			return Type.MISSILE;
		case ("O"):
			return Type.OBSTACLE;
		case ("P"):
			return Type.PICKUP;
		case ("T"):
			return Type.TEAM;
		case ("V"):
			return Type.VOID;
		case ("@"):
			return Type.PLAYER;
		case ("_"):
			return Type.ANYTHING;
		default:
			return Type.ANYTHING;
		}
	}

}
