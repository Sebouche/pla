package ricm3.game;

public class MovingEntity extends GameEntity{

	int m_speed;
	int m_damage;
	
	public MovingEntity(Model model,int x,int y,int hp) {
		super(model,x,y,hp);
	}
}
