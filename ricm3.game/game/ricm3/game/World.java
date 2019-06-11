package ricm3.game;

import java.awt.Graphics;
import java.io.File;
import java.util.LinkedList;

public class World {

	Model m_model;
	LinkedList<GameEntity> m_entities;
	LinkedList<Ally> m_allies;
	File m_bgmfile;
	
	public World(Model model) {
		m_entities = new LinkedList<GameEntity>();
		m_allies = new LinkedList<Ally>();
		m_model = model;
	}
	
	public void changeWorld() {
		try {
			Options.m_bgm.stop();
			Options.m_bgm = new Music(m_model.m_currentworld.m_bgmfile);
			Options.m_bgm.start();
		} catch (Exception ex) {}
		
	}
	public void step() {
	}

	public void paint(Graphics g) {
	}

}
