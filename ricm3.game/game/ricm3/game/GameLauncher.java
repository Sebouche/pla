package ricm3.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.ricm3.game.GameUI;

public class GameLauncher implements ActionListener {
	
	JFrame Launcher;
	JButton NewGame, Options, Quit;
	JComboBox<String> AutomataComboBox;
	JPanel AutomataPanel;
	
	public GameLauncher() {
		Launch();
	}
	
	public void Launch() {

		Launcher = new JFrame();
		Launcher.setLayout(new BorderLayout());
		Launcher.setBackground(Color.RED);

		JPanel StartingMenu = new JPanel();
		StartingMenu.setLayout(new BoxLayout(StartingMenu, BoxLayout.Y_AXIS));
		StartingMenu.setOpaque(false);

		Font f1 = new Font(Font.SERIF, Font.BOLD, 64);
		Font f2 = new Font(Font.MONOSPACED, Font.BOLD, 32);
		Font f3 = new Font(Font.MONOSPACED, Font.BOLD, 16);

		JLabel Title = new JLabel("Automatak");
		Title.setFont(f1);
		Title.setAlignmentX(Component.CENTER_ALIGNMENT);
		StartingMenu.add(Title);

		NewGame = new JButton("Nouvelle partie");
		NewGame.setFont(f2);
		NewGame.addActionListener(this);
		NewGame.setAlignmentX(Component.CENTER_ALIGNMENT);
		StartingMenu.add(NewGame);

		JPanel OptionsPanel = new JPanel();
		OptionsPanel.setLayout(new FlowLayout());
		OptionsPanel.setOpaque(false);
		
		Options = new JButton("Options");
		Options.setFont(f2);
		Options.addActionListener(this);
		Options.setAlignmentX(Component.CENTER_ALIGNMENT);
		OptionsPanel.add(Options);

		AutomataPanel = new JPanel();
		AutomataPanel.setLayout(new BoxLayout(AutomataPanel, BoxLayout.Y_AXIS));
		AutomataPanel.setOpaque(false);
		JLabel AtomatonChoice = new JLabel("Choix de l'automate du joueur");
		AtomatonChoice.setFont(f3);
		AutomataPanel.add(AtomatonChoice);
		String[] Automata = { "Bat", "Dog", "Otto", "Rabbit", "Mouse" };
		AutomataComboBox = new JComboBox<String>(Automata);
		AutomataComboBox.setSelectedIndex(4);
		AutomataComboBox.addActionListener(this);
		AutomataPanel.add(AutomataComboBox);

		OptionsPanel.add(AutomataPanel);
		AutomataPanel.setVisible(false);
		StartingMenu.add(OptionsPanel);

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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object s = e.getSource();
		if (s == NewGame) {
			// construct the game elements: model, controller, and view.
		    Model model = new Model();
		    View view = new View(model);
		    Controller controller = new Controller(model,view);
		
		    Dimension d = new Dimension(1024, 768);
		    new GameUI(model,view,controller,d);
		    Launcher.setVisible(false);
		    
		    // notice that the main thread will exit here,
		    // but not your program... hence the hooking
		    // of the window events to System.exit(0) when
		    // the window is closed. See class WindowListener.
		
		    /*
		     * *** WARNING *** WARNING *** WARNING *** WARNING ***
		     * If you do something here, on this "main" thread,
		     * you will have parallelism and thus race conditions.
		     * 
		     *           ONLY FOR ADVANCED DEVELOPERS
		     *           
		     * *** WARNING *** WARNING *** WARNING *** WARNING ***
		     */
		} else if (s == Options) {
			AutomataPanel.setVisible(!AutomataPanel.isVisible());
		} else if (s == AutomataComboBox) {
			JComboBox<String> cb = (JComboBox<String>) s;
			String automaton = (String) cb.getSelectedItem();
			System.out.println(automaton);
		} else if (s == Quit) {
			System.exit(0);
		}
	}
}
