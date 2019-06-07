package ricm3.game;

import java.awt.Graphics;
import java.io.File;
import java.util.LinkedList;

public class World {

	Model m_model;
	LinkedList<GameEntity> m_entities;
	File m_bgmfile;
	
	public World(Model model) {
		m_model = model;
		m_entities = new LinkedList<GameEntity>();
	}

	public void step() {
	}

	public void paint(Graphics g) {
	}

}
