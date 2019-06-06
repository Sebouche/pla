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

import java.awt.Color;
import java.awt.PopupMenu;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import edu.ricm3.game.GameModel;


public class Model extends GameModel {
	Player m_player;
	SurfaceWorld m_surfaceworld;
	UndergroundWorld m_undergroundworld;
	World m_currentworld;
	Camera m_camera;
	Hashtable<String,BufferedImage[]> m_sprites=new Hashtable<String,BufferedImage[]>();
	
	JPanel starting_menu;
	JPanel options_menu;
	
	public Model() {
		loadSprites();
		m_surfaceworld=new SurfaceWorld(this);
		m_undergroundworld=new UndergroundWorld(this);
		m_currentworld=m_surfaceworld;
		m_player=new Player(this,0,0,9999);
		m_camera=new Camera(this,m_player);
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
		if (Options.begin) {
			m_player.step();
			m_undergroundworld.step();
			m_surfaceworld.step();
		}
	}

	private void loadSprites() {
		File imageFile;
		
		/*
		// Recopier ces ligne en remplacant par le sprite a importer
		imageFile = new File("game/sprites/.png");
		try {
			BufferedImage m_spritename = ImageIO.read(imageFile);
			splitSprite("name",m_spritename,rows,cols);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		
		*/
		imageFile = new File("sprites/bat.png");
		try {
			BufferedImage m_spritename = ImageIO.read(imageFile);
			splitSprite("bat",m_spritename,3,2);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("sprites/block.png");
		try {
			BufferedImage m_spritename = ImageIO.read(imageFile);
			splitSprite("block",m_spritename,3,3);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("sprites/dirtbg.png");
		try {
			BufferedImage m_spritename = ImageIO.read(imageFile);
			splitSprite("dirtbg",m_spritename,1,1);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("sprites/dog.png");
		try {
			BufferedImage m_spritename = ImageIO.read(imageFile);
			splitSprite("dog",m_spritename,5,5);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("sprites/grassbg.png");
		try {
			BufferedImage m_spritename = ImageIO.read(imageFile);
			splitSprite("grassbg",m_spritename,1,1);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("sprites/house.png");
		try {
			BufferedImage m_spritename = ImageIO.read(imageFile);
			splitSprite("house",m_spritename,5,3);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("sprites/mouse.png");
		try {
			BufferedImage m_spritename = ImageIO.read(imageFile);
			splitSprite("mouse",m_spritename,4,4);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("sprites/rabbit.png");
		try {
			BufferedImage m_spritename = ImageIO.read(imageFile);
			splitSprite("rabbit",m_spritename,4,5);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("sprites/scientist.png");
		try {
			BufferedImage m_spritename = ImageIO.read(imageFile);
			splitSprite("scientist",m_spritename,7,6);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("sprites/spawner.png");
		try {
			BufferedImage m_spritename = ImageIO.read(imageFile);
			splitSprite("spawner",m_spritename,2,2);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("sprites/turtle.png");
		try {
			BufferedImage m_spritename = ImageIO.read(imageFile);
			splitSprite("turtle",m_spritename,5,4);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}
	
	void splitSprite(String name,BufferedImage sprite,int rows,int cols) {
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
