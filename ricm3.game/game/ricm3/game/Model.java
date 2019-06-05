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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;

import edu.ricm3.game.GameModel;


public class Model extends GameModel {
	Player m_player;
	SurfaceWorld m_surfaceworld;
	UndergroundWorld m_undergroundworld;
	World m_currentworld;
	Camera m_camera;
	BufferedImage[][] m_sprites=new BufferedImage[20][20];
	
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
		m_player.step();
		m_undergroundworld.step();
		m_surfaceworld.step();
	}

	private void loadSprites() {
		File imageFile;
		
		/*
		 * Recopier ces ligne en remplacant par le sprite a importer
		imageFile = new File("game/sprites/.png");
		try {
			m_spritename = ImageIO.read(imageFile);
			splitSprite(m_spritename,rows,cols,id);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		*/
	}
	
	void splitSprite(BufferedImage sprite,int rows,int cols,int id) {
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
		m_sprites[id]=sprites;
	}
	
}
