
import java.awt.Graphics2D;

public abstract class Game_State  {
	
	protected Game_State_Handler gsh;	
	
	public Game_State(Game_State_Handler gsh) {
		this.gsh= gsh;
	}
	
	public abstract void init();
	public abstract void update();
	public abstract void draw(Graphics2D g);
	public abstract void handleInput();

}
