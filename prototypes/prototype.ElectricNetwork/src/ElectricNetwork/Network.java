package ElectricNetwork;

public class Network {
	LinkedGraph g;

	public static void main(String[] args) {
		Network n = new Network(5);
		NetworkEntity g1 = new NetworkEntity(0, 0, 0, 0, "Generator", 10F);
		n.g.add(g1);
		NetworkEntity g2 = new NetworkEntity(3, 5, 0, 0, "Generator", 10F);
		n.g.add(g2);
		NetworkEntity p1 = new NetworkEntity(5, 0, 0, 0, "Pylon", 0F);
		n.g.add(p1);
		NetworkEntity p2 = new NetworkEntity(0, 5, 0, 0, "Pylon", 0F);
		n.g.add(p2);
		NetworkEntity p3 = new NetworkEntity(5, 5, 0, 0, "Pylon", 0F);
		n.g.add(p3);
		NetworkEntity p4 = new NetworkEntity(7, 2, 0, 0, "Pylon", 0F);
		n.g.add(p4);
		NetworkEntity e1 = new NetworkEntity(12, 2, 0, 0, "Turret", 0F);
		n.g.add(e1);
		n.g.remove(p1);
		n.g.remove(p3);
		n.g.remove(g2);
		if (n.g.hasPower(e1)) {
			System.out.println("Oui");
		} else {
			System.out.println("Non");
		}
	}

	public Network(int d) {
		g = new LinkedGraph(d);
	}
}
