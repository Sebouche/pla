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

import java.awt.Font;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import edu.ricm3.game.GameController;
import ricm3.interpreter.Keys;

/**
 * This class is to illustrate the most simple game controller. It does not
 * much, but it shows how to obtain the key strokes, mouse buttons, and mouse
 * moves.
 * 
 * With ' ', you see what you should never do, SLEEP. With '+' and '-', you can
 * add or remove some simulated overheads.
 * 
 * @author Pr. Olivier Gruber
 */

public class Controller extends GameController implements ActionListener {

	Model m_model;
	View m_view;

	JMenuItem m_m1_button_murs, m_m1_button_barbele, m_m1_button_tesla, m_m1_button_poteau;

	public Controller(Model model, View view) {
		m_model = model;
		m_view = view;
	}

	/**
	 * Simulation step. Warning: the model has already executed its step.
	 * 
	 * @param now is the current time in milliseconds.
	 */
	@Override
	public void step(long now) {
		m_model.step(now);
		m_view.step(now);

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (Options.ECHO_KEYBOARD)
			System.out.println("KeyPressed: " + e.getKeyChar() + " code=" + e.getKeyCode());
		Keys k = Keys.keyEventToKeys(e);
		if (!m_model.m_player.m_keys.contains(k)) {
			m_model.m_player.m_keys.add(k);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (Options.ECHO_KEYBOARD)
			System.out.println("KeyReleased: " + e.getKeyChar() + " code=" + e.getKeyCode());
		Keys k = Keys.keyEventToKeys(e);
		m_model.m_player.m_keys.remove(k);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (Options.ECHO_MOUSE)
			System.out.println("MouseClicked: (" + e.getX() + "," + e.getY() + ") button=" + e.getButton());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (Options.ECHO_MOUSE)
			System.out.println("MousePressed: (" + e.getX() + "," + e.getY() + ") button=" + e.getButton());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (Options.ECHO_MOUSE)
			System.out.println("MouseReleased: (" + e.getX() + "," + e.getY() + ") button=" + e.getButton());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (Options.ECHO_MOUSE_MOTION)
			System.out.println("MouseEntered: (" + e.getX() + "," + e.getY());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (Options.ECHO_MOUSE_MOTION)
			System.out.println("MouseExited: (" + e.getX() + "," + e.getY());
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (Options.ECHO_MOUSE_MOTION)
			System.out.println("MouseDragged: (" + e.getX() + "," + e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (Options.ECHO_MOUSE_MOTION)
			System.out.println("MouseMoved: (" + e.getX() + "," + e.getY());
	}

	public void menu_fabrication() {
		Font f1 = new Font(Font.SERIF, Font.BOLD, 64);
		Font f2 = new Font(Font.MONOSPACED, Font.BOLD, 32);
		Font f3 = new Font(Font.MONOSPACED, Font.PLAIN, 16);

		m_model.menu_fabrication = new JPopupMenu("Fabrication");
		
		JLabel m_m1_title = new JLabel("Fabrication");
		m_m1_title.setFont(f2);
		m_model.menu_fabrication.add(m_m1_title);
		
		m_m1_button_murs = new JMenuItem("Murs");
		m_m1_button_murs.setFont(f3);
		m_m1_button_murs.setIcon(new ImageIcon("sprites/icon_wall.png"));
		m_m1_button_murs.addActionListener(this);
		m_model.menu_fabrication.add(m_m1_button_murs);

		m_m1_button_barbele = new JMenuItem("Barbelés");
		m_m1_button_barbele.setFont(f3);
		m_m1_button_barbele.setIcon(new ImageIcon("sprites/icon_barbele.png"));
		m_m1_button_barbele.addActionListener(this);
		m_model.menu_fabrication.add(m_m1_button_barbele);
		
		m_m1_button_tesla = new JMenuItem("Tourelle Tesla");
		m_m1_button_tesla.setFont(f3);
		m_m1_button_tesla.setIcon(new ImageIcon("sprites/icon_tesla.png"));
		m_m1_button_tesla.addActionListener(this);
		m_model.menu_fabrication.add(m_m1_button_tesla);
		
		m_m1_button_poteau = new JMenuItem("Poteau électrique");
		m_m1_button_poteau.setFont(f3);
		m_m1_button_poteau.setIcon(new ImageIcon("sprites/icon_poteau.png"));
		m_m1_button_poteau.addActionListener(this);
		m_model.menu_fabrication.add(m_m1_button_poteau);

		m_model.menu_fabrication.show(m_view, (int) (m_view.getWidth() / 2 + Options.Entity_size * Options.Scale), m_view.getHeight() / 2);

	}

	public void notifyVisible() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object s = e.getSource();
		if (s == m_m1_button_murs) {
			Wall w=new Wall(m_model, m_model.m_player.m_x, m_model.m_player.m_y, Options.HP[1], m_model.m_sprites.get("wall"), Options.Entities.get("Wall"), m_model.m_surfaceworld);
			w.m_collision=false;
			m_model.m_surfaceworld.m_entities.add(w);
		} else if ((s == m_m1_button_barbele)) {
			
		} else if ((s == m_m1_button_tesla)) {
			Turret t=new Turret(m_model, m_model.m_player.m_x, m_model.m_player.m_y, Options.HP[1], m_model.m_sprites.get("tesla"), Options.Entities.get("Tesla"), m_model.m_surfaceworld);
			t.m_collision=false;
			m_model.m_surfaceworld.m_entities.add(t);
		} else if ((s == m_m1_button_poteau)) {
			
		}
	}

}
