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

/**
 * This class is to illustrate a simple animated character. It does not much,
 * but it shows how to load an animation sprite and how to cycle through the
 * entire animation.
 * 
 * Pay attention to the finite state automaton, that is, the entire behavior
 * behaves as an automaton that progress a step at a time. Look at the method
 * step(long).
 * 
 * About the behavior of the cowboy. The cowboy can rotate around, that is the
 * animation. But since using arms may be dangerous, his gun may explode.
 * 
 * @author Pr. Olivier Gruber
 */

public class Structure {
	int[][] m_structure;
	int m_width, m_height;
	Model m_model;

	Structure(Model model) {
		m_model = model;
		m_structure = new int[0][0];
		m_width = 0;
		m_height = 0;
	}

	void change_size(int width, int height) {
		int[][] tmp = new int[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				tmp[i][j] = 0;
			}
		}
		if (m_structure.length >= 1 && height >= 1) {
			for (int i = 0; i < Math.min(m_structure.length, tmp.length); i++) {
				if (m_structure[0].length >= 1 && width >= 1) {
					for (int j = 0; j < Math.min(m_structure[0].length, tmp[0].length); j++) {
						tmp[i][j] = m_structure[i][j];
					}
				}
			}
		}
		m_structure = tmp;
		m_width = width;
		m_height = height;
	}

	void add_object(int posX, int posY, int Id) {
		if (posX >= 0 && posX < m_width && posY >= 0 && posY < m_height) {
			m_structure[posY][posX] = Id;
		} else {
			System.out.printf("Out of bounds\n");
		}
	}

	void remove_object(int posX, int posY) {
		if (posX >= 0 && posX < m_width && posY >= 0 && posY < m_height) {
			m_structure[posY][posX] = 0;
		} else {
			System.out.printf("Out of bounds\n");
		}
	}

	void save(String file_name) {
		FileWriter filewriter;
		try {
			filewriter = new FileWriter("structures/"+file_name);
			PrintWriter printwriter =new PrintWriter(filewriter);	
			printwriter.printf("%d %d\n", m_width, m_height);
			for (int i = 0; i < m_height; i++) {
				for (int j = 0; j < m_width; j++) {
					printwriter.printf("%d ", m_structure[i][j]);
				}
				printwriter.printf("\n");
			}
			printwriter.close();
		} catch (IOException e) {
			e.printStackTrace();
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
				Graphics g_child = g.create(j * Options.BLOCK_SIZE, i * Options.BLOCK_SIZE, Options.BLOCK_SIZE, Options.BLOCK_SIZE);
				if(m_structure[i][j]!=0) {
					g_child.fillRect(0, 0, Options.BLOCK_SIZE, Options.BLOCK_SIZE);
				}
				g_child.dispose();
			}
		}
	}
}
