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
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import edu.ricm3.game.GameController;
import game.blocks.*;
import ricm3.interpreter.Direction;
import ricm3.interpreter.IAutomaton;
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
	Boolean bplayer2 = false;
	Enemy player2;
	Font m_f1, m_f2;
	JMenuItem m_quitbutton, m_m1_button_murs, m_m1_button_barbele, m_m1_button_tesla, m_m1_button_poteau, m_m2_validate;
	JPopupMenu fabricationSubMenu;
	JPanel m_inventoryPanel;
	JLabel in1, in2, in3, in4, in5, in6, in7, in8;

	GameEntity currentEntity;

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
		updateInventory();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (Options.ECHO_KEYBOARD)
			System.out.println("KeyPressed: " + e.getKeyChar() + " code=" + e.getKeyCode());
		Keys k = Keys.keyEventToKeys(e);
		if (!m_model.m_keys.contains(k)) {
			m_model.m_keys.add(k);
		}

		if (e.getKeyChar() == 'u') {
			m_model.m_player.blocs().increments(Ladder.class, 1);
			m_model.m_player.blocs().increments(Dirt.class, 1);
			m_model.m_player.blocs().increments(Stone.class, 1);
			m_model.m_player.blocs().increments(Copper.class, 1);
			m_model.m_player.blocs().increments(Iron.class, 1);
			m_model.m_player.blocs().increments(Coal.class, 1);
			m_model.m_player.blocs().increments(Uranium.class, 1);
		}
		if (e.getKeyChar() == 'y' && m_model.m_player.m_originWorld instanceof UndergroundWorld) {
			UndergroundWorld w = (UndergroundWorld) m_model.m_player.m_originWorld;
			int entity_size = (int) (Options.Scale * Options.Entity_size);
			int pos_x = (int) (m_model.m_player.m_x / entity_size);
			int pos_y = (int) (m_model.m_player.m_y / entity_size);
			w.m_grid[pos_y + 2][Math.floorMod((pos_x + 10), 60)] = new Water(m_model,
					(int) (Math.floorMod((pos_x + 10), 60) * (Options.Entity_size * Options.Scale)),
					(int) ((pos_y + 2) * (Options.Entity_size * Options.Scale)), 10, m_model.m_sprites.get("Block"),
					new IAutomaton(Options.Entities.get("Water")), w);
		}
		if (e.getKeyChar() == 't') {
			bplayer2 = true;
			player2 = new Mouse(m_model, m_model.m_player.m_x - 100, m_model.m_player.m_y - 100,
					m_model.m_sprites.get("Mouse"), new IAutomaton(Options.Entities.get("Player2")),
					m_model.m_surfaceworld, m_model.m_surfaceworld.m_allies);
			m_model.m_player.m_originWorld.m_entities.add(player2);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (Options.ECHO_KEYBOARD)
			System.out.println("KeyReleased: " + e.getKeyChar() + " code=" + e.getKeyCode());
		Keys k = Keys.keyEventToKeys(e);
		m_model.m_keys.remove(k);
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

	public void fabricationMenu() {
		if (m_model.fabricationMenu == null) {

			m_model.fabricationMenu = new JPopupMenu("Fabrication");

			JLabel m_m1_title = new JLabel("Fabrication");
			m_m1_title.setFont(m_f1);
			m_model.fabricationMenu.add(m_m1_title);

			m_m1_button_murs = new JMenuItem("Murs");
			m_m1_button_murs.setFont(m_f2);
			m_m1_button_murs.setIcon(new ImageIcon("sprites/icon_wall.png"));
			m_m1_button_murs.addActionListener(this);
			m_model.fabricationMenu.add(m_m1_button_murs);

			m_m1_button_barbele = new JMenuItem("Barbelés");
			m_m1_button_barbele.setFont(m_f2);
			m_m1_button_barbele.setIcon(new ImageIcon("sprites/icon_barbele.png"));
			m_m1_button_barbele.addActionListener(this);
			m_model.fabricationMenu.add(m_m1_button_barbele);

			m_m1_button_tesla = new JMenuItem("Tourelle Tesla");
			m_m1_button_tesla.setFont(m_f2);
			m_m1_button_tesla.setIcon(new ImageIcon("sprites/icon_tesla.png"));
			m_m1_button_tesla.addActionListener(this);
			m_model.fabricationMenu.add(m_m1_button_tesla);

			m_m1_button_poteau = new JMenuItem("Poteau électrique");
			m_m1_button_poteau.setFont(m_f2);
			m_m1_button_poteau.setIcon(new ImageIcon("sprites/icon_poteau.png"));
			m_m1_button_poteau.addActionListener(this);
			m_model.fabricationMenu.add(m_m1_button_poteau);

		}

	}

	public void fabricationSubMenu(GameEntity g) {

		currentEntity = g;

		fabricationSubMenu = new JPopupMenu("Fabrication");

		Enumeration<String> keys = g.m_recipe.keys();
		Iterator<Integer> values = g.m_recipe.values().iterator();

		JLabel Title = new JLabel("Matériaux nécessaires");
		Title.setFont(m_f1);
		fabricationSubMenu.add(Title);

		m_m2_validate = new JMenuItem("Valider");
		m_m2_validate.setFont(m_f2);
		m_m2_validate.addActionListener(this);
		m_m2_validate.setEnabled(true);

		Class c = null;
		while (values.hasNext()) {
			String k = keys.nextElement();
			int v = values.next();
			try {
				c = Class.forName("game.blocks." + k);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			if (m_model.m_player.blocs().get(c) < v) {
				m_m2_validate.setEnabled(false);
			}
			JLabel Mat = new JLabel(k + " x" + Integer.toString(v));
			Mat.setFont(m_f2);
			fabricationSubMenu.add(Mat);
		}

		fabricationSubMenu.add(m_m2_validate);

		keys = g.m_recipe.keys();
		values = g.m_recipe.values().iterator();

		fabricationSubMenu.show(m_view, (int) (m_view.getWidth() / 2 + Options.Entity_size * Options.Scale),
				m_view.getHeight() / 2);
	}

	public void inventory() {
		m_inventoryPanel = new JPanel();
		m_inventoryPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		m_inventoryPanel.setBackground(Color.WHITE);

		in1 = new JLabel("x" + m_model.m_player.blocs().get(Ladder.class));
		in1.setIcon(new ImageIcon(m_model.m_sprites.get("Block")[10]));
		in1.setFont(m_f1);
		m_inventoryPanel.add(in1);

		in2 = new JLabel("x" + m_model.m_player.blocs().get(Dirt.class));
		in2.setIcon(new ImageIcon(m_model.m_sprites.get("Block")[0]));
		in2.setFont(m_f1);
		m_inventoryPanel.add(in2);

		in3 = new JLabel("x" + m_model.m_player.blocs().get(Stone.class));
		in3.setIcon(new ImageIcon(m_model.m_sprites.get("Block")[2]));
		in3.setFont(m_f1);
		m_inventoryPanel.add(in3);

		in4 = new JLabel("x" + m_model.m_player.blocs().get(Coal.class));
		in4.setIcon(new ImageIcon(m_model.m_sprites.get("Block")[11]));
		in4.setFont(m_f1);
		m_inventoryPanel.add(in4);

		in5 = new JLabel("x" + m_model.m_player.blocs().get(Iron.class));
		in5.setIcon(new ImageIcon(m_model.m_sprites.get("Block")[5]));
		in5.setFont(m_f1);
		m_inventoryPanel.add(in5);

		in6 = new JLabel("x" + m_model.m_player.blocs().get(Copper.class));
		in6.setIcon(new ImageIcon(m_model.m_sprites.get("Block")[3]));
		in6.setFont(m_f1);
		m_inventoryPanel.add(in6);

		in7 = new JLabel("x" + m_model.m_player.blocs().get(Uranium.class));
		in7.setIcon(new ImageIcon(m_model.m_sprites.get("Block")[4]));
		in7.setFont(m_f1);
		m_inventoryPanel.add(in7);
	}
	
	public void updateInventory() {
		in1.setText("x" + m_model.m_player.blocs().get(Ladder.class));
		in2.setText("x" + m_model.m_player.blocs().get(Dirt.class));
		in3.setText("x" + m_model.m_player.blocs().get(Stone.class));
		in4.setText("x" + m_model.m_player.blocs().get(Coal.class));
		in5.setText("x" + m_model.m_player.blocs().get(Iron.class));
		in6.setText("x" + m_model.m_player.blocs().get(Copper.class));
		in7.setText("x" + m_model.m_player.blocs().get(Uranium.class));
	}

	public void endgameMenu() {
		m_f1 = new Font(Font.MONOSPACED, Font.BOLD, 32);
		m_model.endmenu = new JPopupMenu();

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.setBackground(Color.WHITE);

		JLabel titre = new JLabel("GAME OVER");
		titre.setFont(m_f1);
		panel.add(titre);

		m_quitbutton = new JMenuItem("Quitter");
		m_quitbutton.setFont(m_f2);
		m_quitbutton.addActionListener(this);

		m_model.endmenu.add(panel);
		m_model.endmenu.add(m_quitbutton);
	}

	public void notifyVisible() {
		m_f1 = new Font(Font.MONOSPACED, Font.BOLD, 32);
		m_f2 = new Font(Font.MONOSPACED, Font.PLAIN, 16);
		fabricationMenu();
		inventory();
		m_game.addSouth(m_inventoryPanel);
		endgameMenu();
		m_game.addNorth(m_model.m_timer);
	}

	public boolean free(GameEntity e) {
		Iterator<GameEntity> iter = m_model.m_surfaceworld.m_entities.iterator();
		while (iter.hasNext()) {
			GameEntity ge = iter.next();
			if (e.distance(ge) <= 0) {
				return false;
			}
		}
		return true;
	}

	public void create(Direction dir, String name) {
		int dx, dy;
		int ESize = (int) (Options.Entity_size * Options.Scale)+1;
		switch (Direction.entityDir(m_model.m_player, dir)) {
		case NORTH:
			dx = 0;
			dy = -ESize - 1;
			break;
		case SOUTH:
			dx = 0;
			dy = ESize + 1;
			break;
		case WEST:
			dx = -ESize - 1;
			dy = 0;
			break;
		case EAST:
			dx = ESize + 1;
			dy = 0;
			break;
		default:
			dx = 0;
			dy = 0;
			break;
		}
		switch (name) {
		case "Wall":
			Wall w = new Wall(m_model, m_model.m_player.m_x + dx, m_model.m_player.m_y + dy, Options.HP[4],
					m_model.m_sprites.get("Wall"), new IAutomaton(Options.Entities.get("Wall")),
					m_model.m_surfaceworld);
			fabricationSubMenu(w);
			break;
		case "Barbed":
			Barbed b = new Barbed(m_model, m_model.m_player.m_x + dx, m_model.m_player.m_y + dy, Options.HP[1],
					m_model.m_sprites.get("Barbed"), new IAutomaton(Options.Entities.get("Barbed")),
					m_model.m_surfaceworld);
			fabricationSubMenu(b);
			break;
		case "Tesla":
			Turret t = new Turret(m_model, m_model.m_player.m_x + dx, m_model.m_player.m_y + dy, Options.HP[1],
					m_model.m_sprites.get("Tesla"), new IAutomaton(Options.Entities.get("Tesla")),
					m_model.m_surfaceworld, m_model.m_surfaceworld.m_enemies);
			fabricationSubMenu(t);
			break;
		case "ElectricalPost":
			ElectricalPost p = new ElectricalPost(m_model, m_model.m_player.m_x + dx, m_model.m_player.m_y + dy,
					Options.HP[1], m_model.m_sprites.get("ElectricalPost"),
					new IAutomaton(Options.Entities.get("Wall")), m_model.m_surfaceworld);
			fabricationSubMenu(p);
			break;
		default:
			break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object s = e.getSource();
		if (s == m_m1_button_murs) {
			create(m_model.m_player.m_dir, "Wall");
		} else if (s == m_m1_button_barbele) {
			create(m_model.m_player.m_dir, "Barbed");
		} else if (s == m_m1_button_tesla) {
			create(m_model.m_player.m_dir, "Tesla");
		} else if (s == m_m1_button_poteau) {
			create(m_model.m_player.m_dir, "ElectricalPost");
		} else if (s == m_m2_validate) {
			if (free(currentEntity)) {
				Enumeration<String> keys = currentEntity.m_recipe.keys();
				Iterator<Integer> values = currentEntity.m_recipe.values().iterator();
				Class c = null;
				if (m_m2_validate.isEnabled()) {
					while (values.hasNext()) {
						String k = keys.nextElement();
						int v = values.next();
						try {
							c = Class.forName("game.blocks." + k);
						} catch (ClassNotFoundException ex) {
							ex.printStackTrace();
						}
						m_model.m_player.blocs().decrements(c, v);
					}
				}
				m_model.m_surfaceworld.m_entities.add(currentEntity);
				m_model.m_surfaceworld.m_allies.add((Ally) currentEntity);
			}
		} else if ((s == m_quitbutton)) {
			System.exit(0);
		}
	}

}
