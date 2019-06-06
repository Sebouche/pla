package ricm3.game;

import java.awt.Graphics;
import java.util.LinkedList;

public class World {

	Model m_model;
	LinkedList<GameEntity> m_entities;
	
	
	public World(Model model) {
		m_model = model;
		m_entities = new LinkedList<GameEntity>();
	}

	public void step() {
	}

	public void paint(Graphics g) {
	}

}
