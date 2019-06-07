package ricm3.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.ricm3.game.GameUI;

public class GameLauncher implements ActionListener {

	JFrame Launcher, OptionsFrame;
	JButton NewGame, Options, Quit, OptionsValidate;
	JComboBox<String> AutomataComboBox_Player1, AutomataComboBox_Bat, AutomataComboBox_Block, AutomataComboBox_Dog, AutomataComboBox_House, AutomataComboBox_Mouse, AutomataComboBox_Rabbit, AutomataComboBox_Turtle;
	Font f1, f2, f3;

	public GameLauncher() {
		Launcher();
		OptionsMenu();
	}

	public void Launcher() {

		Launcher = new JFrame();
		Launcher.setLayout(new BorderLayout());
		Launcher.setBackground(Color.RED);

		JPanel StartingMenu = new JPanel();
		StartingMenu.setLayout(new BoxLayout(StartingMenu, BoxLayout.Y_AXIS));
		StartingMenu.setOpaque(false);

		f1 = new Font(Font.SERIF, Font.BOLD, 64);
		f2 = new Font(Font.MONOSPACED, Font.BOLD, 32);
		f3 = new Font(Font.MONOSPACED, Font.PLAIN, 16);

		JLabel Title = new JLabel("Automatak");
		Title.setFont(f1);
		Title.setAlignmentX(Component.CENTER_ALIGNMENT);
		StartingMenu.add(Title);

		NewGame = new JButton("Nouvelle partie");
		NewGame.setFont(f2);
		NewGame.addActionListener(this);
		NewGame.setAlignmentX(Component.CENTER_ALIGNMENT);
		StartingMenu.add(NewGame);

		Options = new JButton("Options");
		Options.setFont(f2);
		Options.addActionListener(this);
		Options.setAlignmentX(Component.CENTER_ALIGNMENT);
		StartingMenu.add(Options);

		Quit = new JButton("Quitter");
		Quit.setFont(f2);
		Quit.addActionListener(this);
		Quit.setAlignmentX(Component.CENTER_ALIGNMENT);
		StartingMenu.add(Quit);

		Launcher.add(StartingMenu, BorderLayout.CENTER);
		Launcher.setBounds(200, 200, 800, 600);
		Launcher.setVisible(true);
		
		return;
	}

	public void OptionsMenu() {
		OptionsFrame = new JFrame();
		OptionsFrame.setLayout(new BorderLayout());

		JPanel OptionsNorth = new JPanel();
		
		JLabel OptionsTitle = new JLabel("Choix des automates");
		OptionsTitle.setFont(f1);
		OptionsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel OptionsPanel = new JPanel();
		OptionsPanel.setLayout(new FlowLayout());

		JPanel EntitiesPanel = new JPanel();
		EntitiesPanel.setLayout(new BoxLayout(EntitiesPanel, BoxLayout.Y_AXIS));

		JPanel AutomataPanel = new JPanel();
		AutomataPanel.setLayout(new BoxLayout(AutomataPanel, BoxLayout.Y_AXIS));
		
		OptionsValidate = new JButton("Valider");
		OptionsValidate.setFont(f2);
		OptionsValidate.addActionListener(this);

		String[] Automata = { "Player1", "Bat", "Block", "Dog", "House", "Mouse", "Rabbit", "Turtle" };

		AutomataComboBox_Player1 = new JComboBox<String>(Automata);
		AutomataComboBox_Player1.setSelectedIndex(0);
		AutomataComboBox_Player1.addActionListener(this);
		
		AutomataComboBox_Bat = new JComboBox<String>(Automata);
		AutomataComboBox_Bat.setSelectedIndex(1);
		AutomataComboBox_Bat.addActionListener(this);
		
		AutomataComboBox_Block = new JComboBox<String>(Automata);
		AutomataComboBox_Block.setSelectedIndex(2);
		AutomataComboBox_Block.addActionListener(this);
		
		AutomataComboBox_Dog = new JComboBox<String>(Automata);
		AutomataComboBox_Dog.setSelectedIndex(3);
		AutomataComboBox_Dog.addActionListener(this);
		
		AutomataComboBox_House = new JComboBox<String>(Automata);
		AutomataComboBox_House.setSelectedIndex(4);
		AutomataComboBox_House.addActionListener(this);
		
		AutomataComboBox_Mouse = new JComboBox<String>(Automata);
		AutomataComboBox_Mouse.setSelectedIndex(5);
		AutomataComboBox_Mouse.addActionListener(this);
		
		AutomataComboBox_Rabbit = new JComboBox<String>(Automata);
		AutomataComboBox_Rabbit.setSelectedIndex(6);
		AutomataComboBox_Rabbit.addActionListener(this);
		
		AutomataComboBox_Turtle = new JComboBox<String>(Automata);
		AutomataComboBox_Turtle.setSelectedIndex(7);
		AutomataComboBox_Turtle.addActionListener(this);
		
		JLabel EntitiesPanelTitle = new JLabel("Entités");
		EntitiesPanelTitle.setFont(f2);
		EntitiesPanelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		EntitiesPanel.add(EntitiesPanelTitle);
		EntitiesPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		for (int i = 0; i < Automata.length; i++) {
			JLabel AutomataLabel = new JLabel(Automata[i]);
			AutomataLabel.setFont(f3);
			AutomataLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			EntitiesPanel.add(AutomataLabel);
			EntitiesPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		}
		
		JLabel AutomataPanelTitle = new JLabel("Automates");
		AutomataPanelTitle.setFont(f2);
		AutomataPanel.add(AutomataPanelTitle);
	
		AutomataPanel.add(AutomataComboBox_Player1);
		AutomataPanel.add(AutomataComboBox_Bat);
		AutomataPanel.add(AutomataComboBox_Block);
		AutomataPanel.add(AutomataComboBox_Dog);
		AutomataPanel.add(AutomataComboBox_House);
		AutomataPanel.add(AutomataComboBox_Mouse);
		AutomataPanel.add(AutomataComboBox_Rabbit);
		AutomataPanel.add(AutomataComboBox_Turtle);
		
		OptionsPanel.add(EntitiesPanel);
		OptionsPanel.add(AutomataPanel);
		
		OptionsNorth.add(OptionsTitle);
		
		OptionsFrame.add(OptionsNorth, BorderLayout.NORTH);
		OptionsFrame.add(OptionsPanel, BorderLayout.CENTER);
		OptionsFrame.add(OptionsValidate, BorderLayout.SOUTH);
		
		OptionsFrame.setBounds(400, 400, 400, 400);

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
}
