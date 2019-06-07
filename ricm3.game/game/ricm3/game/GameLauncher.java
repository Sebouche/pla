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

	ImagePanel StartingMenu;
	JFrame Launcher, OptionsFrame;
	JButton NewGame, Options, Quit, OptionsValidate;
	JComboBox<String> AutomataComboBox_Player1, AutomataComboBox_Bat, AutomataComboBox_Block, AutomataComboBox_Dog, AutomataComboBox_House, AutomataComboBox_Mouse, AutomataComboBox_Rabbit, AutomataComboBox_Turtle;
	Font f1, f2, f3;

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
				System.out.println("Problème de lecture d'image (launcher bg)");
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

		Launcher = new JFrame();
		Launcher.setTitle("Super Automatak Defense (Launcher)");
		Launcher.setLayout(new BorderLayout());
		Launcher.setBounds(200, 100, 800, 600);
		Launcher.addComponentListener(this);

		StartingMenu = new ImagePanel(0, 0, Launcher.getWidth(), Launcher.getHeight(), "sprites/mdr.jpg");
		StartingMenu.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		StartingMenu.setOpaque(false);

		f1 = new Font(Font.SERIF, Font.BOLD, 64);
		f2 = new Font(Font.MONOSPACED, Font.BOLD, 32);
		f3 = new Font(Font.MONOSPACED, Font.PLAIN, 16);

		JLabel Title = new JLabel("Automatak");
		Title.setFont(f1);
		c.gridx = 0;
		c.gridy = 0;
		StartingMenu.add(Title, c);

		NewGame = new JButton("Nouvelle partie");
		NewGame.setFont(f2);
		NewGame.addActionListener(this);
		c.gridx = 0;
		c.gridy = 1;
		StartingMenu.add(NewGame, c);

		Options = new JButton("Options");
		Options.setFont(f2);
		Options.addActionListener(this);
		c.gridx = 0;
		c.gridy = 2;
		StartingMenu.add(Options, c);

		Quit = new JButton("Quitter");
		Quit.setFont(f2);
		Quit.addActionListener(this);
		c.gridx = 0;
		c.gridy = 3;
		StartingMenu.add(Quit, c);

		Launcher.add(StartingMenu, BorderLayout.CENTER);
		Launcher.setVisible(true);
		
		return;
	}

	public void OptionsMenu() {
		OptionsFrame = new JFrame();
		OptionsFrame.setTitle("Super Automatak Defense (Options)");
		OptionsFrame.setLayout(new BorderLayout());

		JPanel OptionsNorth = new JPanel();
		
		JLabel OptionsTitle = new JLabel("Choix des automates");
		OptionsTitle.setFont(f1);
		OptionsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel OptionsPanel = new JPanel();
		OptionsPanel.setLayout(new BoxLayout(OptionsPanel, BoxLayout.Y_AXIS));
		OptionsPanel.setOpaque(false);
		
		OptionsValidate = new JButton("Valider");
		OptionsValidate.setFont(f2);
		OptionsValidate.addActionListener(this);

		String[] Automata = { "Player1", "Bat", "Block", "Dog", "House", "Mouse", "Rabbit", "Turtle" };
		LinkedList<JComboBox<String>> AutomataList = new LinkedList<JComboBox<String>>();
		
		AutomataComboBox_Player1 = new JComboBox<String>(Automata);
		AutomataComboBox_Player1.setSelectedIndex(0);
		AutomataComboBox_Player1.addActionListener(this);
		AutomataList.add(AutomataComboBox_Player1);

		AutomataComboBox_Bat = new JComboBox<String>(Automata);
		AutomataComboBox_Bat.setSelectedIndex(1);
		AutomataComboBox_Bat.addActionListener(this);
		AutomataList.add(AutomataComboBox_Bat);
		
		AutomataComboBox_Block = new JComboBox<String>(Automata);
		AutomataComboBox_Block.setSelectedIndex(2);
		AutomataComboBox_Block.addActionListener(this);
		AutomataList.add(AutomataComboBox_Block);
		
		AutomataComboBox_Dog = new JComboBox<String>(Automata);
		AutomataComboBox_Dog.setSelectedIndex(3);
		AutomataComboBox_Dog.addActionListener(this);
		AutomataList.add(AutomataComboBox_Dog);
		
		AutomataComboBox_House = new JComboBox<String>(Automata);
		AutomataComboBox_House.setSelectedIndex(4);
		AutomataComboBox_House.addActionListener(this);
		AutomataList.add(AutomataComboBox_House);
		
		AutomataComboBox_Mouse = new JComboBox<String>(Automata);
		AutomataComboBox_Mouse.setSelectedIndex(5);
		AutomataComboBox_Mouse.addActionListener(this);
		AutomataList.add(AutomataComboBox_Mouse);
		
		AutomataComboBox_Rabbit = new JComboBox<String>(Automata);
		AutomataComboBox_Rabbit.setSelectedIndex(6);
		AutomataComboBox_Rabbit.addActionListener(this);
		AutomataList.add(AutomataComboBox_Rabbit);
		
		AutomataComboBox_Turtle = new JComboBox<String>(Automata);
		AutomataComboBox_Turtle.setSelectedIndex(7);
		AutomataComboBox_Turtle.addActionListener(this);
		AutomataList.add(AutomataComboBox_Turtle);
		
		JLabel Title = new JLabel("Entité / Automate");
		Title.setFont(f2);
		Title.setAlignmentX(Component.CENTER_ALIGNMENT);
		OptionsPanel.add(Title);
		
		for (int i = 0; i < Automata.length; i++) {
			JLabel AutomataLabel = new JLabel(Automata[i]);
			AutomataLabel.setFont(f3);
			JComboBox<String> AutomataComboBox = AutomataList.get(i);
			JPanel AutomataPanel = new JPanel();
			AutomataPanel.add(AutomataLabel);
			AutomataPanel.add(AutomataComboBox);
			OptionsPanel.add(AutomataPanel);
		}
		
		OptionsNorth.add(OptionsTitle);
		
		OptionsFrame.add(OptionsNorth, BorderLayout.NORTH);
		OptionsFrame.add(OptionsPanel, BorderLayout.CENTER);
		OptionsFrame.add(OptionsValidate, BorderLayout.SOUTH);
		
		OptionsFrame.setBounds(300, 200, 800, 600);
		
		return;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object s = e.getSource();
		if (s == NewGame) {
			Model model = new Model();
			View view = new View(model);
			Controller controller = new Controller(model, view);

			Dimension d = new Dimension(1024, 768);
			new GameUI(model, view, controller, d);
			Launcher.setVisible(false);
		} else if (s == Options) {
			OptionsFrame.setVisible(true);
		} else if (s == AutomataComboBox_Player1) {
			JComboBox<String> cb = (JComboBox<String>) s;
			String automaton = (String) cb.getSelectedItem();
			System.out.println(automaton);
		} else if (s == AutomataComboBox_Bat) {
			JComboBox<String> cb = (JComboBox<String>) s;
			String automaton = (String) cb.getSelectedItem();
			System.out.println(automaton);
		} else if (s == AutomataComboBox_Block) {
			JComboBox<String> cb = (JComboBox<String>) s;
			String automaton = (String) cb.getSelectedItem();
			System.out.println(automaton);
		} else if (s == AutomataComboBox_Dog) {
			JComboBox<String> cb = (JComboBox<String>) s;
			String automaton = (String) cb.getSelectedItem();
			System.out.println(automaton);
		} else if (s == AutomataComboBox_House) {
			JComboBox<String> cb = (JComboBox<String>) s;
			String automaton = (String) cb.getSelectedItem();
			System.out.println(automaton);
		} else if (s == AutomataComboBox_Mouse) {
			JComboBox<String> cb = (JComboBox<String>) s;
			String automaton = (String) cb.getSelectedItem();
			System.out.println(automaton);
		} else if (s == AutomataComboBox_Rabbit) {
			JComboBox<String> cb = (JComboBox<String>) s;
			String automaton = (String) cb.getSelectedItem();
			System.out.println(automaton);
		} else if (s == AutomataComboBox_Turtle) {
			JComboBox<String> cb = (JComboBox<String>) s;
			String automaton = (String) cb.getSelectedItem();
			System.out.println(automaton);
		} else if (s == Quit) {
			System.exit(0);
		} else if (s == OptionsValidate) {
			OptionsFrame.setVisible(false);
		}
	}
	
	public void componentResized(ComponentEvent e) {
		 StartingMenu.update(0, 0, Launcher.getWidth(), Launcher.getHeight());
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
