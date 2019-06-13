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
import java.util.Hashtable;
import java.util.LinkedList;

import ricm3.interpreter.IAutomaton;

public class Options {
	public static final boolean USE_DOUBLE_BUFFERING = true;
	public static final boolean ECHO_MOUSE = false;
	public static final boolean ECHO_MOUSE_MOTION = false;
	public static final boolean ECHO_KEYBOARD = false;

	public static int Entity_size = 32;
	public static double Scale = 2;

	public static int[] spawnerProba = { 60, 15, 5, 10 }; // Chien, Tortue, Souris, Lapin
	public static String[] spawnerType = { "Rabbit", "Turtle", "Mouse", "Rabbit" };
	public static int[] HP = { 25, 100, 2, 10 , 500, 250};

	public static boolean day = true; // indique si on est la journée ou pas
	
	public static LinkedList<IAutomaton> Automata;
	
	public static Hashtable<String, IAutomaton> Entities;
	public static String[] EntitiesNames = { "Player1", "Player2", "Barbed", "Bat", "Block", "Dog", "Mole", "Mouse", "Rabbit", "Spawner", "Tesla", "Turtle", "Wall", "Water" ,"Moving"};
	
	public static Music bgm;
	public static int[] damage = {10,3,1,2};
	public static int TurretDamage = 20;
	public static Color BlitzColor = new Color(0,150,200);
}
