
public class Game_State_Handler { 

	private Game_State[] gameStates;
	private int currentState;

	public static final int NUMGAMESTATES = 3;
	public static final int MENUSTATE = 0;
	public static final int DESCRIPTIONSTATE= 1;
	public static final int PLAYSTATE = 2;
	
	public boolean BGMstate= true;
	
	public Game_State_Handler() {
		
		BGM.init();
		
		gameStates = new Game_State[NUMGAMESTATES];

		currentState = MENUSTATE;
		loadState(currentState);

	}

	private void loadState(int state) {
		if (state == MENUSTATE)
			gameStates[state] = new Menu_State(this);
		else if (state == PLAYSTATE)
			gameStates[state] = new Play_State(this);
		else if (state == DESCRIPTIONSTATE)
			gameStates[state] = new Description_State(this);
	}
	
	private void unloadState(int state) {
		gameStates[state]= null;
	}
	
	public void setState(int state) {
		unloadState(currentState);
		currentState= state;
		loadState(currentState);
	}
	
	public void update() {
		if(gameStates[currentState] != null) gameStates[currentState].update();
	}
	
	public void draw(java.awt.Graphics2D g) {
		if(gameStates[currentState] != null) {gameStates[currentState].draw(g);}
		else {		
			g.setColor(java.awt.Color.BLACK);
			g.fillRect(0, 0, 1000, 800);
		}
	}

}
