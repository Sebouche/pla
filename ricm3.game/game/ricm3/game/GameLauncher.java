package ricm3.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.ricm3.game.GameUI;

public class GameLauncher implements ActionListener, ComponentListener {

	ImagePanel m_StartingMenu;
	JFrame m_Launcher, m_OptionsFrame;
	JButton m_NewGame, m_Options, m_Quit, m_OptionsValidate;
	JComboBox<String> m_AutomataComboBox_Player1, m_AutomataComboBox_Bat, m_AutomataComboBox_Block, m_AutomataComboBox_Dog, m_AutomataComboBox_House, m_AutomataComboBox_Mouse, m_AutomataComboBox_Rabbit, m_AutomataComboBox_Turtle;
	Font m_f1, m_f2, m_f3;

	public GameLauncher() {
		Launcher();
		OptionsMenu();
	}
	
	public class ImagePanel extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		BufferedImage m_bg;
		int m_x, m_y, m_w, m_h;

		public ImagePanel(int x, int y, int w, int h, String url) {
			m_x = x;
			m_y = y;
			m_w = w;
			m_h = h;
			try {
				m_bg = ImageIO.read(new File(url));
			} catch (IOException e) {
				System.out.println("Probleme de lecture d'image (launcher bg)");
			}
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(m_bg, m_x, m_y, m_w, m_h, null);
		}
		
		public void update(int x, int y, int w, int h) {
			m_x = x;
			m_y = y;
			m_w = w;
			m_h = h;
			repaint();
		}
	}

	public void Launcher() {

		m_Launcher = new JFrame();
		m_Launcher.setTitle("Super Automatak Defense (Launcher)");
		m_Launcher.setLayout(new BorderLayout());
		m_Launcher.setBounds(200, 100, 800, 600);
		m_Launcher.addComponentListener(this);

		m_StartingMenu = new ImagePanel(0, 0, m_Launcher.getWidth(), m_Launcher.getHeight(), "sprites/mdr.jpg");
		m_StartingMenu.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		m_StartingMenu.setOpaque(false);

		m_f1 = new Font(Font.SERIF, Font.BOLD, 64);
		m_f2 = new Font(Font.MONOSPACED, Font.BOLD, 32);
		m_f3 = new Font(Font.MONOSPACED, Font.PLAIN, 16);

		JLabel Title = new JLabel("Automatak");
		Title.setFont(m_f1);
		c.gridx = 0;
		c.gridy = 0;
		m_StartingMenu.add(Title, c);

		m_NewGame = new JButton("Nouvelle partie");
		m_NewGame.setFont(m_f2);
		m_NewGame.addActionListener(this);
		c.gridx = 0;
		c.gridy = 1;
		m_StartingMenu.add(m_NewGame, c);

		m_Options = new JButton("Options");
		m_Options.setFont(m_f2);
		m_Options.addActionListener(this);
		c.gridx = 0;
		c.gridy = 2;
		m_StartingMenu.add(m_Options, c);

		m_Quit = new JButton("Quitter");
		m_Quit.setFont(m_f2);
		m_Quit.addActionListener(this);
		c.gridx = 0;
		c.gridy = 3;
		m_StartingMenu.add(m_Quit, c);

		m_Launcher.add(m_StartingMenu, BorderLayout.CENTER);
		m_Launcher.setVisible(true);
		
		return;
	}

	public void OptionsMenu() {
		m_OptionsFrame = new JFrame();
		m_OptionsFrame.setTitle("Super Automatak Defense (Options)");
		m_OptionsFrame.setLayout(new BorderLayout());

		JPanel OptionsNorth = new JPanel();
		
		JLabel OptionsTitle = new JLabel("Choix des automates");
		OptionsTitle.setFont(m_f1);
		OptionsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel OptionsPanel = new JPanel();
		OptionsPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		OptionsPanel.setOpaque(false);
		
		m_OptionsValidate = new JButton("Valider");
		m_OptionsValidate.setFont(m_f2);
		m_OptionsValidate.addActionListener(this);

		String[] Automata = { "Player1", "Bat", "Block", "Dog", "House", "Mouse", "Rabbit", "Turtle" };
		LinkedList<JComboBox<String>> AutomataList = new LinkedList<JComboBox<String>>();
		
		m_AutomataComboBox_Player1 = new JComboBox<String>(Automata);
		m_AutomataComboBox_Player1.setSelectedIndex(0);
		m_AutomataComboBox_Player1.addActionListener(this);
		AutomataList.add(m_AutomataComboBox_Player1);

		m_AutomataComboBox_Bat = new JComboBox<String>(Automata);
		m_AutomataComboBox_Bat.setSelectedIndex(1);
		m_AutomataComboBox_Bat.addActionListener(this);
		AutomataList.add(m_AutomataComboBox_Bat);
		
		m_AutomataComboBox_Block = new JComboBox<String>(Automata);
		m_AutomataComboBox_Block.setSelectedIndex(2);
		m_AutomataComboBox_Block.addActionListener(this);
		AutomataList.add(m_AutomataComboBox_Block);
		
		m_AutomataComboBox_Dog = new JComboBox<String>(Automata);
		m_AutomataComboBox_Dog.setSelectedIndex(3);
		m_AutomataComboBox_Dog.addActionListener(this);
		AutomataList.add(m_AutomataComboBox_Dog);
		
		m_AutomataComboBox_House = new JComboBox<String>(Automata);
		m_AutomataComboBox_House.setSelectedIndex(4);
		m_AutomataComboBox_House.addActionListener(this);
		AutomataList.add(m_AutomataComboBox_House);
		
		m_AutomataComboBox_Mouse = new JComboBox<String>(Automata);
		m_AutomataComboBox_Mouse.setSelectedIndex(5);
		m_AutomataComboBox_Mouse.addActionListener(this);
		AutomataList.add(m_AutomataComboBox_Mouse);
		
		m_AutomataComboBox_Rabbit = new JComboBox<String>(Automata);
		m_AutomataComboBox_Rabbit.setSelectedIndex(6);
		m_AutomataComboBox_Rabbit.addActionListener(this);
		AutomataList.add(m_AutomataComboBox_Rabbit);
		
		m_AutomataComboBox_Turtle = new JComboBox<String>(Automata);
		m_AutomataComboBox_Turtle.setSelectedIndex(7);
		m_AutomataComboBox_Turtle.addActionListener(this);
		AutomataList.add(m_AutomataComboBox_Turtle);
		
		JLabel EntitiesTitle = new JLabel("Entite /");
		EntitiesTitle.setFont(m_f2);
		c.gridx = 0;
		c.gridy = 0;
		OptionsPanel.add(EntitiesTitle, c);
		
		JLabel AutomataTitle = new JLabel(" Automate");
		AutomataTitle.setFont(m_f2);
		c.gridx = 1;
		c.gridy = 0;
		OptionsPanel.add(AutomataTitle, c);	
		
		for (int i = 0; i < Automata.length; i++) {
			JLabel AutomataLabel = new JLabel(Automata[i]);
			AutomataLabel.setFont(m_f3);
			JComboBox<String> AutomataComboBox = AutomataList.get(i);
			c.gridx = 0;
			c.gridy = i + 1;
			OptionsPanel.add(AutomataLabel, c);
			c.gridx = 1;
			c.gridy = i + 1;
			OptionsPanel.add(AutomataComboBox, c);
		}
		
		OptionsNorth.add(OptionsTitle);
		
		m_OptionsFrame.add(OptionsNorth, BorderLayout.NORTH);
		m_OptionsFrame.add(OptionsPanel, BorderLayout.CENTER);
		m_OptionsFrame.add(m_OptionsValidate, BorderLayout.SOUTH);
		
		m_OptionsFrame.setBounds(300, 200, 800, 450);
		
		return;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object s = e.getSource();
		if (s == m_NewGame) {
			Model model = new Model();
			View view = new View(model);
			Controller controller = new Controller(model, view);

			Dimension d = new Dimension(1024, 768);
			new GameUI(model, view, controller, d);
			m_Launcher.setVisible(false);
		} else if (s == m_Options) {
			m_OptionsFrame.setVisible(true);
		} else if (s == m_AutomataComboBox_Player1) {
			JComboBox<String> cb = (JComboBox<String>) s;
			String automaton = (String) cb.getSelectedItem();
			System.out.println(automaton);
		} else if (s == m_AutomataComboBox_Bat) {
			JComboBox<String> cb = (JComboBox<String>) s;
			String automaton = (String) cb.getSelectedItem();
			System.out.println(automaton);
		} else if (s == m_AutomataComboBox_Block) {
			JComboBox<String> cb = (JComboBox<String>) s;
			String automaton = (String) cb.getSelectedItem();
			System.out.println(automaton);
		} else if (s == m_AutomataComboBox_Dog) {
			JComboBox<String> cb = (JComboBox<String>) s;
			String automaton = (String) cb.getSelectedItem();
			System.out.println(automaton);
		} else if (s == m_AutomataComboBox_House) {
			JComboBox<String> cb = (JComboBox<String>) s;
			String automaton = (String) cb.getSelectedItem();
			System.out.println(automaton);
		} else if (s == m_AutomataComboBox_Mouse) {
			JComboBox<String> cb = (JComboBox<String>) s;
			String automaton = (String) cb.getSelectedItem();
			System.out.println(automaton);
		} else if (s == m_AutomataComboBox_Rabbit) {
			JComboBox<String> cb = (JComboBox<String>) s;
			String automaton = (String) cb.getSelectedItem();
			System.out.println(automaton);
		} else if (s == m_AutomataComboBox_Turtle) {
			JComboBox<String> cb = (JComboBox<String>) s;
			String automaton = (String) cb.getSelectedItem();
			System.out.println(automaton);
		} else if (s == m_Quit) {
			System.exit(0);
		} else if (s == m_OptionsValidate) {
			m_OptionsFrame.setVisible(false);
		}
	}
	
	public void componentResized(ComponentEvent e) {
		 m_StartingMenu.update(0, 0, m_Launcher.getWidth(), m_Launcher.getHeight());
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}
}