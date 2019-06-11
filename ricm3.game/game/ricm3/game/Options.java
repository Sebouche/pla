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

import java.util.List;

import ricm3.interpreter.IAutomaton;

public class Options {
	public static final boolean USE_DOUBLE_BUFFERING = true;
	public static final boolean ECHO_MOUSE = false;
	public static final boolean ECHO_MOUSE_MOTION = false;
	public static final boolean ECHO_KEYBOARD = false;

	public static int Entity_size = 32;
	public static double Scale = 2;

	public static int[] spawnerProba = { 60, 25, 5, 10 }; // Chien, Tortue, Souris, Lapin
	public static String[] spawnerType = { "Dog", "Turtle", "Mouse", "Rabbit" };
	public static int[] HP = { 25, 100, 2, 10 };

	public static boolean day = true; // indique si on est la journée ou pas
	
	public static List<IAutomaton> m_automata;
	
	public static IAutomaton Player1_Automaton = null;
	public static IAutomaton Bat_Automaton = null;
	public static IAutomaton Block_Automaton = null;
	public static IAutomaton Dog_Automaton = null;
	public static IAutomaton Wall_Automaton = null;
	public static IAutomaton Mouse_Automaton = null;
	public static IAutomaton Rabbit_Automaton = null;
	public static IAutomaton Turtle_Automaton = null;
	public static IAutomaton Spawner_Automaton = null;
	public static Music m_bgm;
}
