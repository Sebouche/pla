package ricm3.game;

import java.awt.image.BufferedImage;

import ricm3.interpreter.IAutomaton;

public class MovingEntity extends GameEntity{

	int m_speed;
	int m_damage;
	
	public MovingEntity(Model model,int x,int y,int hp,BufferedImage[] sprites,IAutomaton automate) {
		super(model,x,y,hp,sprites,automate);
	}
}
