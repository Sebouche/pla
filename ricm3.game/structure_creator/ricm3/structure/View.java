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
import java.util.Iterator;

import edu.ricm3.game.GameView;

public class View extends GameView {

	private static final long serialVersionUID = 1L;

	Color m_background = new Color(54, 188, 226);
	long m_last;
	int m_npaints;
	int m_fps;
	Model m_model;
	// Controller m_ctr;

	public View(Model m) {
		m_model = m;
		// m_ctr = c;
	}

	public void step(long now) {

	}

	private void computeFPS() {
		long now = System.currentTimeMillis();
		if (now - m_last > 1000L) {
			m_fps = m_npaints;
			m_last = now;
			m_npaints = 0;
		}
		m_game.setFPS(m_fps, null);
		// m_game.setFPS(m_fps, "npaints=" + m_npaints);
		m_npaints++;
	}

	@Override
	protected void _paint(Graphics g) {
		computeFPS();

		// erase background
		g.setColor(m_background);
		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(new Color(0, 0, 0));

		int camera_x, camera_y;
		if (m_model.m_camera.m_moving) {
			camera_x = m_model.m_camera.m_tmp_x;
			camera_y = m_model.m_camera.m_tmp_y;
		} else {
			camera_x = m_model.m_camera.m_x;
			camera_y = m_model.m_camera.m_y;
		}
		
		g.drawRect(camera_x, camera_y, Options.BLOCK_SIZE * m_model.m_structure.m_width,
				Options.BLOCK_SIZE * m_model.m_structure.m_height);
		for (int i = 0; i < m_model.m_structure.m_height; i++) {
			for (int j = 0; j < m_model.m_structure.m_width; j++) {
				g.drawRect(camera_x + j * Options.BLOCK_SIZE, camera_y + i * Options.BLOCK_SIZE, Options.BLOCK_SIZE,
						Options.BLOCK_SIZE);
			}
		}
		Graphics g_child = g.create(camera_x, camera_y, Options.BLOCK_SIZE * m_model.m_structure.m_width, Options.BLOCK_SIZE * m_model.m_structure.m_height);
		m_model.m_structure.paint(g_child);
		g_child.dispose();
	}
}
