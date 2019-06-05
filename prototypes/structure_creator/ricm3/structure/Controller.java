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

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Iterator;

import javax.swing.BoxLayout;

import edu.ricm3.game.GameController;

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
	TextField m_width_field, m_height_field, m_name;
	Button m_next, m_prev, m_save;
	int m_dragged_x, m_dragged_y;
	int m_button;
	Checkbox m_erase;

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
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (Options.ECHO_KEYBOARD)
			System.out.println("KeyReleased: " + e.getKeyChar() + " code=" + e.getKeyCode());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (Options.ECHO_MOUSE)
			System.out.println("MouseClicked: (" + e.getX() + "," + e.getY() + ") button=" + e.getButton());
		if (e.getButton() == 1) {
			if (!m_erase.getState()) {
				m_model.m_structure.add_object((int) ((e.getX() - m_model.m_camera.m_x) / Options.BLOCK_SIZE),
						(int) ((e.getY() - m_model.m_camera.m_y) / Options.BLOCK_SIZE), 1);
			} else {
				m_model.m_structure.remove_object((int) ((e.getX() - m_model.m_camera.m_x) / Options.BLOCK_SIZE),
						(int) ((e.getY() - m_model.m_camera.m_y) / Options.BLOCK_SIZE));
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (Options.ECHO_MOUSE)
			System.out.println("MousePressed: (" + e.getX() + "," + e.getY() + ") button=" + e.getButton());
		m_dragged_x = e.getX();
		m_dragged_y = e.getY();
		m_button = e.getButton();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (Options.ECHO_MOUSE)
			System.out.println("MouseReleased: (" + e.getX() + "," + e.getY() + ") button=" + e.getButton());
		if (m_button == 3) {
			m_model.m_camera.move();
		}
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
		if (m_button == 1) {
			if (!m_erase.getState()) {
				m_model.m_structure.add_object((int) ((e.getX() - m_model.m_camera.m_x) / Options.BLOCK_SIZE),
						(int) ((e.getY() - m_model.m_camera.m_y) / Options.BLOCK_SIZE), 1);
			} else {
				m_model.m_structure.remove_object((int) ((e.getX() - m_model.m_camera.m_x) / Options.BLOCK_SIZE),
						(int) ((e.getY() - m_model.m_camera.m_y) / Options.BLOCK_SIZE));
			}
		}
		if (m_button == 3) {
			m_model.m_camera.moving(m_dragged_x - e.getX(), m_dragged_y - e.getY());
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (Options.ECHO_MOUSE_MOTION)
			System.out.println("MouseMoved: (" + e.getX() + "," + e.getY());
	}

	public void notifyVisible() {
		Container cont = new Container();
		Container sub_cont;
		Container sub_sub_cont;
		cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));

		m_erase = new Checkbox();
		m_erase.setLabel("Erase mode");
		cont.add(m_erase);

		sub_cont = new Container();
		sub_cont.setLayout(new BoxLayout(sub_cont, BoxLayout.Y_AXIS));
		sub_sub_cont = new Container();
		sub_sub_cont.setLayout(new FlowLayout());
		sub_sub_cont.add(new Label("Width : "));
		m_width_field = new TextField("0");
		m_width_field.addActionListener(this);
		sub_sub_cont.add(m_width_field);
		sub_cont.add(sub_sub_cont);
		sub_sub_cont = new Container();
		sub_sub_cont.setLayout(new FlowLayout());
		sub_sub_cont.add(new Label("Height : "));
		m_height_field = new TextField("0");
		m_height_field.addActionListener(this);
		sub_sub_cont.add(m_height_field);
		sub_cont.add(sub_sub_cont);
		cont.add(sub_cont);

		sub_cont = new Container();
		sub_cont.setLayout(new BoxLayout(sub_cont, BoxLayout.Y_AXIS));
		sub_cont.add(new Label("Test"));
		sub_sub_cont = new Container();
		sub_sub_cont.setLayout(new FlowLayout());
		sub_sub_cont.add(new Button("<-"));
		sub_sub_cont.add(new Button("->"));
		sub_cont.add(sub_sub_cont);
		cont.add(sub_cont);

		sub_cont = new Container();
		sub_cont.setLayout(new BoxLayout(sub_cont, BoxLayout.Y_AXIS));
		sub_sub_cont = new Container();
		sub_sub_cont.setLayout(new FlowLayout());
		sub_sub_cont.add(new Label("File name : "));
		m_name = new TextField("Structure_i.txt");
		sub_sub_cont.add(m_name);
		sub_cont.add(sub_sub_cont);
		m_save = new Button("Save");
		sub_cont.add(m_save);
		m_save.addActionListener(this);
		cont.add(sub_cont);

		m_game.addWest(cont);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String string_width = m_width_field.getText();
		String string_height = m_height_field.getText();
		int width;
		int height;
		if (e.getSource() == m_width_field || e.getSource() == m_height_field) {
			try {
				width = Integer.parseInt(string_width);
				height = Integer.parseInt(string_height);
				m_model.m_structure.m_width = width;
				m_model.m_structure.m_height = height;
				m_model.m_structure.change_size(width, height);
			} catch (NumberFormatException exc) {
				System.out.printf("You need to write a number!");
			}
		}
		if (e.getSource() == m_prev) {

		}
		if (e.getSource() == m_next) {

		}
		if (e.getSource() == m_save) {
			m_model.m_structure.save(m_name.getText());
		}
	}

}
