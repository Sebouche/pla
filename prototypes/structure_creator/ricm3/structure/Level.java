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
	Random m_rand = new Random();

	Level(Model model) {
		m_model = model;
		m_level = new int[90][90];
		m_width = 60;
		m_height = 90;
		generate_level();
	}

	void generate_level() {
		int randint;
		for (int i = 0; i < m_height; i++) {
			for (int j = 0; j < m_width; j++) {
				m_level[i][j] = 0;
			}
		}
		for (int i = 0; i < m_height; i++) {
			for (int j = 0; j < m_width; j++) {
				if (m_level[i][j] == 0) {
					randint = m_rand.nextInt(1001);
					if (randint >= 0 && randint < 900) {
						m_level[i][j] = 1;
					}
					if (randint >= 900 && randint < 950) {
						m_level[i][j] = 2;
						for (int k = -1; k <= 1; k ++) {
							int randint2 = m_rand.nextInt(10);
							if (randint2 <= 3 && i + k >= 0 && i + k < m_height) {
								m_level[i + k][j] = 2;
							}
							randint2 = m_rand.nextInt(10);
							if (randint2 <= 3 && j + k >= 0 && j + k < m_width) {
								m_level[i][j+k] = 2;
							}
						}
					}
					if (randint >= 950 && randint < 999) {
						m_level[i][j] = 0;
					}
					if (randint >= 999) {
						m_level[i][j] = 3;
					}
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
					if (m_level[i][j] == 1) {
						g_child.setColor(new Color(0, 0, 0));
					}
					if (m_level[i][j] == 2) {
						g_child.setColor(new Color(255, 0, 0));
					}
					if (m_level[i][j] == 3) {
						g_child.setColor(new Color(0, 255, 0));
					}
					g_child.fillRect(0, 0, Options.BLOCK_SIZE, Options.BLOCK_SIZE);
				}
				g_child.dispose();
			}
		}
	}
}
