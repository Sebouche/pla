package ricm3.game;

import java.awt.BorderLayout;
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
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.ricm3.game.GameUI;
import ricm3.interpreter.IAutomaton;
import ricm3.parser.Ast;
import ricm3.parser.AutomataParser;

public class GameLauncher implements ActionListener, ComponentListener {

	String m_AutomataPath, m_lastAutomataPath;
	ImagePanel m_StartingMenu;
	JFrame m_Launcher, m_OptionsFrame;
	JButton m_NewGame, m_Options, m_Quit, m_OptionsValidate, m_fileChooser;
	LinkedList<JComboBox<String>> m_AutomataComboBox;
	Font m_f1, m_f2, m_f3;


	public GameLauncher() {
		m_AutomataPath = "automata.txt";
		m_lastAutomataPath = "automata.txt";
		Options.Entities = new Hashtable<String, IAutomaton>();
		Launcher();
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
		m_Launcher.setBounds(200, 100, 1000, 800);
		m_Launcher.addComponentListener(this);

		m_StartingMenu = new ImagePanel(0, 0, m_Launcher.getWidth(), m_Launcher.getHeight(), "sprites/launcherbg.jpg");
		m_StartingMenu.setLayout(new BoxLayout(m_StartingMenu, BoxLayout.Y_AXIS));
		m_StartingMenu.setOpaque(false);

		m_f1 = new Font(Font.SERIF, Font.BOLD, 64);
		m_f2 = new Font(Font.MONOSPACED, Font.BOLD, 32);
		m_f3 = new Font(Font.MONOSPACED, Font.PLAIN, 16);

		JPanel Padding = new JPanel();
		Padding.setOpaque(false);
		m_StartingMenu.add(Padding);

		m_NewGame = new JButton("Nouvelle partie");
		m_NewGame.setFont(m_f2);
		m_NewGame.addActionListener(this);
		m_NewGame.setAlignmentX(Component.CENTER_ALIGNMENT);
		m_StartingMenu.add(m_NewGame);

		m_fileChooser = new JButton("Fichier d'automates");
		m_fileChooser.setFont(m_f2);
		m_fileChooser.addActionListener(this);
		m_fileChooser.setAlignmentX(Component.CENTER_ALIGNMENT);
		m_StartingMenu.add(m_fileChooser);

		m_Options = new JButton("Options");
		m_Options.setFont(m_f2);
		m_Options.addActionListener(this);
		m_Options.setAlignmentX(Component.CENTER_ALIGNMENT);
		m_StartingMenu.add(m_Options);

		m_Quit = new JButton("Quitter");
		m_Quit.setFont(m_f2);
		m_Quit.addActionListener(this);
		m_Quit.setAlignmentX(Component.CENTER_ALIGNMENT);
		m_StartingMenu.add(m_Quit);

		m_Launcher.add(m_StartingMenu, BorderLayout.CENTER);
		m_Launcher.setVisible(true);
		File file;
		file = new File("sprites/menumusic.wav");

		try {
			Options.bgm = new Music(file);
			Options.bgm.start();
		} catch (Exception ex) {

		}
		
		return;
	}

	public void Options() {
		Ast arbre;
		try {
			arbre = AutomataParser.from_file(m_AutomataPath);
			Options.Automata = (LinkedList<IAutomaton>) arbre.make();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if ((Options.Automata == null) || (Options.Automata.size() == 0))  {
			System.out.println("Pas d'automates exploitables");
			System.exit(0);
		}
		
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

		// Création des labels
		int i;
		for (i = 0; i < Options.EntitiesNames.length; i++) {
			JLabel EntitiesLabel = new JLabel(Options.EntitiesNames[i]);
			EntitiesLabel.setFont(m_f3);
			c.gridx = 0;
			c.gridy = i + 1;
			OptionsPanel.add(EntitiesLabel, c);
		}

		//Création liste de noms des automates
		LinkedList<String> AutomataNames_list = new LinkedList<String>();
		Iterator<IAutomaton> itr = Options.Automata.iterator();
		i = 0;
		while (itr.hasNext()) {
			IAutomaton Automaton = itr.next();
			AutomataNames_list.add(Automaton.name());
			i++;
		}
		String[] AutomataNames = AutomataNames_list.toArray(new String[0]);

		// Création des combo box
		m_AutomataComboBox = new LinkedList<JComboBox<String>>();
		itr = Options.Automata.iterator();
		for (i = 0; i < Options.EntitiesNames.length; i++) {
			int index = find(Options.EntitiesNames[i]);
			IAutomaton Automaton = Options.Automata.get(index);
			Options.Entities.put(Options.EntitiesNames[i], Automaton);
			JComboBox<String> ComboBox = new JComboBox<String>(AutomataNames);
			ComboBox.setSelectedIndex(index);
			ComboBox.addActionListener(this);
			m_AutomataComboBox.add(ComboBox);
			c.gridx = 1;
			c.gridy = i + 1;
			OptionsPanel.add(ComboBox, c);
		}

		OptionsNorth.add(OptionsTitle);

		m_OptionsFrame.add(OptionsNorth, BorderLayout.NORTH);
		m_OptionsFrame.add(OptionsPanel, BorderLayout.CENTER);
		m_OptionsFrame.add(m_OptionsValidate, BorderLayout.SOUTH);

		m_OptionsFrame.setBounds(300, 200, 800, 200 + 25 * (i+1));

		return;
	}
	
	int find(String name) {
		int i = 0;
		Iterator<IAutomaton> iter = Options.Automata.iterator();
		while (iter.hasNext()) {
			IAutomaton Automaton = iter.next();
			if (Automaton.name().equals(name)) {
				return i;
			}
			i++;
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {

		Object s = e.getSource();
		if (s == m_NewGame) {
			if (m_OptionsFrame == null) {
				Options();
			}
			Model model = new Model();
			View view = new View(model);
			model.m_view=view;
			Controller controller = new Controller(model, view);

			Dimension d = new Dimension(1024, 768);
			new GameUI(model, view, controller, d);
			m_Launcher.setVisible(false);
		} else if (s == m_Options) {
			if (m_AutomataPath != null) {
				if (m_OptionsFrame == null || !m_AutomataPath.equals(m_lastAutomataPath)) {
					m_lastAutomataPath = m_AutomataPath;
					Options();
				}
				m_OptionsFrame.setVisible(true);
			} else {
				System.out.println("Pas de fichier d'automates sélectionné");
			}
		} else if (s == m_Quit) {
			System.exit(0);
		} else if (s == m_OptionsValidate) {
			m_OptionsFrame.setVisible(false);
		} else if (s == m_fileChooser) {
			JFileChooser choice = new JFileChooser();
			int back = choice.showOpenDialog(m_OptionsFrame);
			if (back == JFileChooser.APPROVE_OPTION) {
				m_AutomataPath = choice.getSelectedFile().getAbsolutePath();
			}
		}
		if (m_OptionsFrame != null) {
			int i = 0;
			Iterator<JComboBox<String>> iter = m_AutomataComboBox.iterator();
			while (iter.hasNext()) {
				JComboBox<String> ComboBox = iter.next();
				if (s == ComboBox) {
					JComboBox<String> cb = (JComboBox<String>) s;
					Options.Entities.replace(Options.EntitiesNames[i], Options.Automata.get(cb.getSelectedIndex()));
				}
				i++;
			}
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
