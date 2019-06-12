/*
 * Educational software for a basic game development
 * Copyright (C) 2018  Pr. Olivier Gruber
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ricm3.game;

import java.awt.PopupMenu;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import javax.imageio.ImageIO;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;

import edu.ricm3.game.GameModel;
import ricm3.interpreter.IAutomaton;

public class Model extends GameModel {
	long m_lastStep;
	public Player m_player;
	SurfacePlayer m_surfaceplayer;
	UndergroundPlayer m_undergroundplayer;
	int m_width;
	int m_height;
	SurfaceWorld m_surfaceworld;
	UndergroundWorld m_undergroundworld;
	World m_currentworld;
	Camera m_camera;
	Camera m_arrow;
	Hashtable<String, BufferedImage[]> m_sprites = new Hashtable<String, BufferedImage[]>();
	View m_view;
	Timer m_timer;

	JPopupMenu fabricationMenu;

	public Model() {

		loadSprites();
		m_surfaceworld = new SurfaceWorld(2, this);
		m_undergroundworld = new UndergroundWorld(this);
		m_currentworld = m_surfaceworld;
		IAutomaton player_automate = new IAutomaton(Options.Entities.get("Player1"));
		m_surfaceplayer = new SurfacePlayer(this, 64, 193, 500, m_sprites.get("Scientist"),player_automate, m_surfaceworld);
		m_undergroundplayer =new UndergroundPlayer(this, 64, 640, 500, m_sprites.get("Scientist"),player_automate, m_undergroundworld);
		m_player = m_surfaceplayer;
		m_camera = new Camera(this, m_player);
		m_lastStep = 0;
		try {
			Options.bgm.stop();
			Options.bgm = new Music(m_currentworld.m_bgmfile);
			Options.bgm.start();
		} catch (Exception ex) {}
		m_surfaceworld.m_allies.add(m_surfaceplayer);
		m_undergroundworld.m_allies.add(m_undergroundplayer);
		m_timer = new Timer(2, 30);
	}

	@Override
	public void shutdown() {
	}

	/**
	 * Simulation step.
	 * 
	 * @param now is the current time in milliseconds.
	 */
	@Override
	public void step(long now) {
		m_timer.step(now);
		long elapsed = now - m_lastStep;
		if (elapsed >= 2L) {
			m_lastStep = now;
			m_player.step();
			m_undergroundworld.step();
			m_surfaceworld.step();
		}
	}

	private void loadSprites() {
		File imageFile;

		/*
		 * // Recopier ces ligne en remplacant par le sprite a importer imageFile = new
		 * File("game/sprites/.png"); try { BufferedImage m_spritename =
		 * ImageIO.read(imageFile); splitSprite("name",m_spritename,rows,cols); } catch
		 * (IOException ex) { ex.printStackTrace(); System.exit(-1); }
		 * 
		 */
		imageFile = new File("sprites/bat.png");
		try {
			BufferedImage spritename = ImageIO.read(imageFile);
			splitSprite("Bat", spritename, 3, 2);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("sprites/block.png");
		try {
			BufferedImage spritename = ImageIO.read(imageFile);
			splitSprite("Block", spritename, 4, 3);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("sprites/dirtbg.png");
		try {
			BufferedImage spritename = ImageIO.read(imageFile);
			splitSprite("Dirtbg", spritename, 1, 1);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("sprites/dog.png");
		try {
			BufferedImage spritename = ImageIO.read(imageFile);
			splitSprite("Dog", spritename, 5, 5);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("sprites/grassbg.png");
		try {
			BufferedImage spritename = ImageIO.read(imageFile);
			splitSprite("Grassbg", spritename, 1, 1);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("sprites/house.png");
		try {
			BufferedImage spritename = ImageIO.read(imageFile);
			splitSprite("House", spritename, 5, 3);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("sprites/mouse.png");
		try {
			BufferedImage spritename = ImageIO.read(imageFile);
			splitSprite("Mouse", spritename, 4, 4);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("sprites/rabbit.png");
		try {
			BufferedImage spritename = ImageIO.read(imageFile);
			splitSprite("Rabbit", spritename, 4, 5);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("sprites/scientist.png");
		try {
			BufferedImage spritename = ImageIO.read(imageFile);
			splitSprite("Scientist", spritename, 7, 6);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("sprites/spawner.png");
		try {
			BufferedImage spritename = ImageIO.read(imageFile);
			splitSprite("Spawner", spritename, 2, 2);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("sprites/turtle.png");
		try {
			BufferedImage spritename = ImageIO.read(imageFile);
			splitSprite("Turtle", spritename, 5, 4);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("sprites/heart.png");
		try {
			BufferedImage spritename = ImageIO.read(imageFile);
			splitSprite("Heart", spritename, 2, 2);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("sprites/tesla.png");
		try {
			BufferedImage spritename = ImageIO.read(imageFile);
			splitSprite("Tesla", spritename, 2, 2);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("sprites/wall.png");
		try {
			BufferedImage spritename = ImageIO.read(imageFile);
			splitSprite("Wall", spritename, 2, 1);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("sprites/barbele.png");
		try {
			BufferedImage spritename = ImageIO.read(imageFile);
			splitSprite("Barbed", spritename, 4, 4);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("sprites/poteau.png");
		try {
			BufferedImage spritename = ImageIO.read(imageFile);
			splitSprite("ElectricalPost", spritename, 2, 1);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}

	void splitSprite(String name, BufferedImage sprite, int rows, int cols) {
		int width = sprite.getWidth(null);
		int height = sprite.getHeight(null);
		BufferedImage[] sprites = new BufferedImage[rows * cols];
		int w = width / cols;
		int h = height / rows;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				int x = j * w;
				int y = i * h;
				sprites[(i * cols) + j] = sprite.getSubimage(x, y, w, h);
			}
		}
		m_sprites.put(name, sprites);
	}

}
