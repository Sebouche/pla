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

import game.blocks.Block;

//import ricm3.structure.Controller;
//import ricm3.structure.Model;
//import ricm3.structure.View;

public class GameMain {

	public static void main(String[] args) {
		Class c;
		Block b = new Block(null, 0, 0, 0, null, null, null);
		c = b.getClass();
		if (c == Block.class) {
			System.out.println("true");
		}
		new GameLauncher();
	}
}
