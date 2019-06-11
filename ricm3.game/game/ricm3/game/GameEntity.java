package ricm3.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import ricm3.interpreter.*;

public class GameEntity {

	int m_x, m_y;
	public BufferedImage[] m_sprites;
	public Model m_model;
	IAutomaton m_automate;
	IState m_state;
	// m_sound;
	World m_originWorld;
	int m_hp;
	public int m_idsprite;
	int m_dmg;
	Direction m_dir;
	Type m_type;
	LinkedList<Keys> m_keys;
	boolean m_collision = true;

	public GameEntity(Model model, int x, int y, int hp, BufferedImage[] sprites, IAutomaton automate,
			World originWorld) {
		m_model = model;
		m_x = x;
		m_y = y;
		m_originWorld = originWorld;
		m_hp = hp;
		m_idsprite = 0;
		m_sprites = sprites;
		m_keys = new LinkedList<Keys>();
		m_automate = automate;
		m_dir = Direction.NORTH;

	}

	///////////////////////////

	public int x() {
		return this.m_x;
	}

	public int y() {
		return this.m_y;
	}

	public int hps() {
		return this.m_hp;
	}

	public int dmgs() {
		return this.m_dmg;
	}

	public Direction dir() {
		return this.m_dir;
	}

	public Type type() {
		return this.m_type;
	}

	public LinkedList<Keys> keys() {
		return this.m_keys;
	}

	public LinkedList<GameEntity> entities() {
		return this.m_model.m_currentworld.m_entities;
	}

	///////////////////////////

	public boolean Wait() {
		return true;
	}

	public boolean pop(Direction dir) {
		return true;
	}

	public boolean wizz(Direction dir) {
		return true;
	}

	public boolean move(Direction dir) {
		return true;
	}

	public boolean jump(Direction dir) {
		return true;
	}

	public boolean turn(Direction dir) {
		System.out.printf("test\n");
		return true;
	}

	public boolean hit(Direction dir, int power) {
		return true;
	}

	public boolean protect(Direction dir) {
		return true;
	}

	public boolean pick(Direction dir) {
		return true;
	}

	public boolean Throw(Direction dir) {
		return true;
	}

	public boolean store() {
		return true;
	}

	public boolean get() {
		return true;
	}

	public boolean power() {
		return true;
	}

	public boolean kamikaze() {
		return true;
	}

	public boolean egg() {
		return true;
	}

	////////////////////////////

	public void set_collision(boolean coll) {
		m_collision = coll;
	}

	public void paint(Graphics g) {
		g.drawImage(m_sprites[m_idsprite], 0, 0, (int) Options.Scale * Options.Entity_size,
				(int) Options.Scale * Options.Entity_size, null);
	}

	public void step() {
		if (m_automate != null)
			m_automate.step(this);
	}

	//////////////////////

	public double distance(GameEntity e) {
		return Math.sqrt((m_x - e.m_x) * (m_x - e.m_x) + (m_y - e.m_y) * (m_y - e.m_y));
	}

}
