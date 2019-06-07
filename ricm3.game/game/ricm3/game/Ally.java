package ricm3.game;

import java.awt.image.BufferedImage;

import ricm3.interpreter.IAutomaton;
import ricm3.interpreter.Type;

public class Ally extends MovingEntity{

	public Ally(Model model,int x,int y,int hp,BufferedImage[] sprites,IAutomaton automate) {
		super(model,x,y,hp,sprites,automate);
		this.m_type = Type.TEAM;
	}
}
