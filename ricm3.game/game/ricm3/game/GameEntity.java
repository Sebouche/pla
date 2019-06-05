package ricm3.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class GameEntity {

	int m_x, m_y;
	BufferedImage[] m_sprites;
	Model m_model;
	// Automate m_automate;
	// State m_state;
	// m_sound;
	int m_hp;
	int m_idsprite;

	public GameEntity(Model model, int x, int y, int hp) {
		m_model = model;
		m_x = x;
		m_y = y;
		m_hp = hp;
	}

	public void pop() {
	}

	public void wizz() {
	}

	public void move() {
	}

	public void hit() {
	}

	public void step() {
	}

	public void egg() {
	}

	public void paint(Graphics g) {
		g.drawImage(m_sprites[m_idsprite], 0, 0, null);
	}

	//////////////////////

	double distance(GameEntity e) {
		return Math.sqrt((m_x - e.m_x) * (m_x - e.m_x) + (m_y - e.m_y) * (m_y - e.m_y));
	}

}
