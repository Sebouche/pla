package prototype.ElectricNetwork;

public class NetworkEntity extends Entity {
	String type;
	float load = 0;
	
	public NetworkEntity(int x, int y, int w, int h, String t, float l) {
		super(x,y,w,h);
		type = t;
		load = l;
	}
}
