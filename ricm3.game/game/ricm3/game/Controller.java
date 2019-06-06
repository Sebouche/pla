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

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

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

	JPanel m1_automata_panel;
	JLabel m1_title;
	JButton Non, m1_button1, m1_button2, m1_button3;
	JComboBox m1_combo1;
	MenuItem m2_button1;

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

	public void notifyVisible() {
		// Menu de démarrage
		JPanel North = new JPanel();
		North.setLayout(new FlowLayout(FlowLayout.CENTER));
		North.setBackground(m_view.m_background);

		m_model.starting_menu = new JPanel();
		m_model.starting_menu.setLayout(new BoxLayout(m_model.starting_menu, BoxLayout.Y_AXIS));
		m_model.starting_menu.setBackground(m_view.m_background);

		Font f1 = new Font(Font.SERIF, Font.BOLD, 64);
		Font f2 = new Font(Font.MONOSPACED, Font.BOLD, 32);
		Font f3 = new Font(Font.MONOSPACED, Font.BOLD, 16);

		Component PaddingUp = Box.createRigidArea(new Dimension(0, m_view.getHeight() / 2 - 150));

		m_model.starting_menu.add(PaddingUp);

		m1_title = new JLabel("Automatak");
		m1_title.setFont(f1);
		m1_title.setAlignmentX(m_model.starting_menu.CENTER_ALIGNMENT);
		m_model.starting_menu.add(m1_title);

		m_model.starting_menu.add(Box.createRigidArea(new Dimension(0, 50)));

		m1_button1 = new JButton("Nouvelle partie");
		m1_button1.setFont(f2);
		m1_button1.addActionListener(this);
		m1_button1.setAlignmentX(m_model.starting_menu.CENTER_ALIGNMENT);
		m_model.starting_menu.add(m1_button1);

		m_model.starting_menu.add(Box.createRigidArea(new Dimension(0, 10)));

		JPanel m1_options_panel = new JPanel();
		m1_options_panel.setLayout(new FlowLayout());
		m1_options_panel.setBackground(m_view.m_background);
		m1_button2 = new JButton("Options");
		m1_button2.setFont(f2);
		m1_button2.addActionListener(this);
		m1_button2.setAlignmentX(m_model.starting_menu.CENTER_ALIGNMENT);
		m1_options_panel.add(m1_button2);
		
		m1_automata_panel = new JPanel();
		m1_automata_panel.setLayout(new BoxLayout(m1_automata_panel, BoxLayout.Y_AXIS));
		m1_automata_panel.setBackground(m_view.m_background);
		
		JLabel choose_your_automaton = new JLabel("Choix de l'automate du joueur");
		choose_your_automaton.setFont(f3);
		m1_automata_panel.add(choose_your_automaton);
		
		String[] automata = { "Bat", "Dog", "Otto", "Rabbit", "Mouse" };
		m1_combo1 = new JComboBox(automata);
		m1_combo1.setSelectedIndex(4);
		m1_combo1.addActionListener(this);
		m1_automata_panel.add(m1_combo1);

		m1_options_panel.add(m1_automata_panel);
		m1_automata_panel.setVisible(false);
		m_model.starting_menu.add(m1_options_panel);

		m_model.starting_menu.add(Box.createRigidArea(new Dimension(0, 10)));

		m1_button3 = new JButton("Quitter");
		m1_button3.setFont(f2);
		m1_button3.addActionListener(this);
		m1_button3.setAlignmentX(m_model.starting_menu.CENTER_ALIGNMENT);
		m_model.starting_menu.add(m1_button3);
		
		
		North.add(m_model.starting_menu);
		m_game.addNorth(North);
		
		
		
		
		// Menu popup
		/*m_model.options_menu = new PopupMenu();

		Font f3 = new Font(Font.SERIF, Font.BOLD, 32);
		Font f4 = new Font(Font.MONOSPACED, Font.BOLD, 16);

		MenuItem m2_label1 = new MenuItem("Oui (label)");
		m2_label1.setFont(f4);
		m_model.options_menu.add(m2_label1);

		m2_button1 = new MenuItem("Non (button)");
		m2_button1.setFont(f4);
		m2_button1.addActionListener(this);
		m_model.options_menu.add(m2_button1);

		m_model.options_menu.setLabel("Choix automate (?)");
		m_model.options_menu.setFont(f3);
		m_model.options_menu.setEnabled(true);

		m_view.add(m_model.options_menu);
		
		m_model.options_menu.show(m_view, 0, 0);*/

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object s = e.getSource();
		if (s == m1_button1) {
			Options.begin = true;
			m_model.starting_menu.setVisible(false);
		} else if (s == m1_button2) {
			m1_automata_panel.setVisible(!m1_automata_panel.isVisible());
		} else if (s == m1_button3) {
			System.exit(0);
		} else if (s == m2_button1) {
			System.out.println("NYI");
		}
	}

}
