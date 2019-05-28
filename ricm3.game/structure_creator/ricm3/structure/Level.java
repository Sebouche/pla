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
package ricm3.structure;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class Level {
	int[][] m_level;
	int m_width, m_height;
	Model m_model;
	Random m_rand=new Random();

	Level(Model model) {
		m_model = model;
		m_level = new int[90][90];
		generate_level();
		m_width = 90;
		m_height = 90;
	}

	void generate_level() {
		int randint;
		for (int i = 0; i < m_height; i++) {
			for (int j = 0; j < m_width; j++) {
				randint=m_rand.nextInt(1001);
				if(randint>=0 && randint<700) {
				m_level[i][j] = 1;
				}
				if(randint>=700 && randint<950) {
				m_level[i][j] = 2;
				}
				if(randint>=950 && randint<=999) {
				m_level[i][j] = 0;
				}
				if(randint>=1000) {
				m_level[i][j] = 3;
				}
			}
		}
	}


	/**
	 * Simulation step. This is essentially a finite state automaton. Here, you
	 * decide what happens as time flies.
	 * 
	 * @param now is the current time in milliseconds.
	 */
	void step(long now) {
	}

	void paint(Graphics g) {
		for (int i = 0; i < m_height; i++) {
			for (int j = 0; j < m_width; j++) {
				Graphics g_child = g.create(j * Options.BLOCK_SIZE, i * Options.BLOCK_SIZE, Options.BLOCK_SIZE,
						Options.BLOCK_SIZE);
				if (m_level[i][j] != 0) {
					if(m_level[i][j]==1) {
						g_child.setColor(new Color(0,0,0));
					}
					if(m_level[i][j]==2) {
						g_child.setColor(new Color(255,0,0));
					}
					if(m_level[i][j]==3) {
						g_child.setColor(new Color(0,255,0));
					}
					g_child.fillRect(0, 0, Options.BLOCK_SIZE, Options.BLOCK_SIZE);
				}
				g_child.dispose();
			}
		}
	}
}
