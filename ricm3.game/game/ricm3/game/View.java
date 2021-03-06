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
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
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

		int cam_x = m_model.m_camera.m_watched.m_x;
		int cam_y = m_model.m_camera.m_watched.m_y;
		m_model.m_width = getWidth() - (int) (Options.Entity_size * Options.Scale);
		m_model.m_height = getHeight() - (int) (Options.Entity_size * Options.Scale);

		// background
		if (m_model.m_currentworld instanceof SurfaceWorld) {
			for (int i = -(int) ((getWidth() / 2) / (Options.Entity_size * Options.Scale))
					- 2; i <= (int) ((getWidth() / 2) / (Options.Entity_size * Options.Scale)) + 1; i++) {
				for (int j = -(int) ((getHeight() / 2) / (Options.Entity_size * Options.Scale))
						- 2; j <= (int) ((getHeight() / 2) / (Options.Entity_size * Options.Scale)) + 1; j++) {
					g.drawImage(m_model.m_sprites.get("Grassbg")[0],
							(i * (int) (Options.Entity_size * Options.Scale))
									- (cam_x % (int) (Options.Entity_size * Options.Scale)) + m_model.m_width / 2,
							(j * (int) (Options.Entity_size * Options.Scale))
									- (cam_y % (int) (Options.Entity_size * Options.Scale)) + m_model.m_height / 2,
							(int) Options.Scale * Options.Entity_size, (int) Options.Scale * Options.Entity_size, null);
				}
			}
		}

		// foreground
		Graphics g_child;
		g_child = g.create(0, 0, getWidth(), getHeight());
		m_model.m_currentworld.paint(g_child);
		g_child.dispose();
		g_child = g.create(m_model.m_width / 2, m_model.m_height / 2, (int) (Options.Entity_size * Options.Scale),
				(int) (Options.Entity_size * Options.Scale));
		m_model.m_camera.m_watched.paint(g_child);
		g_child.dispose();

		// barre de vie

		g_child = g.create(m_model.m_width / 2 - 50, 20, 100, 20);
		BufferedImage heartsprite = m_model.m_sprites.get("Heart")[0];
		int hp;
		for (hp = 0; hp < m_model.m_player.m_hp; hp += 100) {
			g.drawImage(heartsprite, ((int) (0.4 * hp)) - 12, -12, 64, 64, null);
		}
		hp -= 100;
		g.setFont(new Font("HP", 1, 15));
		g.setColor(Color.white);
		if (m_model.m_player.m_hp - hp < 100) {
			g.drawString("  " + (m_model.m_player.m_hp - hp), ((int) (0.4 * hp)), 24);
		}
		g_child.dispose();

		if (m_model.m_currentworld instanceof SurfaceWorld) {
			BufferedImage rotate = m_model.m_sprites.get("arrow")[0];

			double angle = Math.atan2((m_model.m_camera.m_watched.m_y - m_model.m_arrow.m_watched.m_y),
					(m_model.m_camera.m_watched.m_x - m_model.m_arrow.m_watched.m_x));
			AffineTransform tx = new AffineTransform();
			tx.rotate(angle - Math.PI / 2, rotate.getWidth() / 2, rotate.getHeight() / 2);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
			rotate = op.filter(rotate, null);
			g_child = g.create(getWidth() - (int) (Options.Scale * Options.Entity_size * 1.5),
					getHeight() - (int) (Options.Scale * Options.Entity_size * 1.5), getWidth(), getHeight());
			g_child.drawImage(rotate, 0, 0, (int) (Options.Scale * Options.Entity_size * 1.5),
					(int) (Options.Scale * Options.Entity_size * 1.5), null);
			g_child.dispose();
		}
	}
}
