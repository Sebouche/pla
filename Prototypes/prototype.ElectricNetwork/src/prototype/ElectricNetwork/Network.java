package prototype.ElectricNetwork;

public class Network {
	LinkedGraph g;

	public static void main(String[] args) {
		Network n = new Network(5);
		n.g.add(new NetworkEntity(0, 0, 0, 0, "Generator", 10F));
		NetworkEntity e1 = new NetworkEntity(0, 2, 0, 0, "Pylon", 0F);
		n.g.add(e1);
		n.g.add(new NetworkEntity(1, 2, 0, 0, "Pylon", 0F));
		n.g.remove(e1);
		NetworkEntity e2 = new NetworkEntity(0, 6, 0, 0, "Turret", 0F);
		n.g.add(e2);
		if (n.g.hasPower(e2)) {
			System.out.println("Oui");
		} else {
			System.out.println("Non");
		}
	}

	public Network(int d) {
		g = new LinkedGraph(d);
	}
}
