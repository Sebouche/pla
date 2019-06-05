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

public class Camera {
	int m_x, m_y;
	int m_tmp_x, m_tmp_y;
	boolean m_moving = false;
	Model m_model;

	Camera(Model model) {
		m_model = model;
		m_x = 0;
		m_y = 0;
	}

	void moving(int x, int y) {
		m_moving = true;
		m_tmp_x = m_x - x;
		m_tmp_y = m_y - y;
	}

	void move() {
		m_moving = false;
		m_x = m_tmp_x;
		m_y = m_tmp_y;
	}

}
