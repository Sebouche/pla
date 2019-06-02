package prototype.world;

public class Main {
	static int radius = 10;
	
	public static void main(String[] args) {
		long time = System.nanoTime();
		World w = new World(radius);
		w.generateChunks(1, 0, radius);
		time = System.nanoTime()- time;
		System.out.println(time/1000 + " µs");
		System.out.println(time/((2*radius+1)*(2*radius+1)*1000) + " µs/chunk");
	}
}
