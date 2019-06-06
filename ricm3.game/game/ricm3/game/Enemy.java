package ricm3.game;

import java.awt.image.BufferedImage;

public class Enemy extends MovingEntity{

	public Enemy(Model model,int x,int y,int hp,BufferedImage[] sprites) {
		super(model,x,y,hp,sprites);
	}
}
