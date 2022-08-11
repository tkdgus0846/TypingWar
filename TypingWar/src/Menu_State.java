
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;



public class Menu_State extends Game_State {

	private BufferedImage sel;	
	private int currentChoice=0;
	private String[] options= {"Start","Description","Quit"};
	
	public Menu_State(Game_State_Handler gsh) {
		super(gsh);
		init();
		try {
			sel= ImageIO.read(
					getClass().getResourceAsStream("/Select.png")					
					).getSubimage(0, 0, 10, 10);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void init( ) {		
		if (gsh.BGMstate == true) {
			BGM.load("/menu.wav", "menu");
			BGM.loop("menu", 600, BGM.getFrames("menu") - 2200);
		}	
	}

	public void update() {
		handleInput();
	}

	public void draw(Graphics2D g) {
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0,0,1000, 800);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN,28));
		g.drawString("Typing War", 125, 90);
		
		g.setFont(new Font("Arial", Font.PLAIN, 14));
		g.drawString("Start", 180, 205);
		g.drawString("Description", 162, 225);
		g.drawString("Quit", 182, 245);
		g.setFont(new Font("±¼¸²", Font.PLAIN, 13));
		g.drawString("21611710 ±è»óÇö", 10, 275);
		
		if(currentChoice == 0) g.drawImage(sel, 150, 195, null);
		else if(currentChoice == 1) g.drawImage(sel, 150, 215, null);
		else if(currentChoice == 2) g.drawImage(sel, 150, 235, null);
	}
	
	private void select() {
		if(currentChoice == 0) {
			gsh.setState(Game_State_Handler.PLAYSTATE);
		}
		else if(currentChoice == 1) {
			gsh.setState(Game_State_Handler.DESCRIPTIONSTATE);
		}
		else if(currentChoice == 2) {
			System.exit(0);
		}
	}

	public void handleInput() { 
		if(Keys.isPressed(Keys.ENTER)) select();
		if(Keys.isPressed(Keys.UP)) {
			if(currentChoice > 0) {
				currentChoice--;
			}
		}
		if(Keys.isPressed(Keys.DOWN)) {
			if(currentChoice < options.length - 1) {
				currentChoice++;
			}
		}
		if(Keys.isPressed(Keys.ESCAPE)) {
			System.exit(0);
		}
		if(Keys.isPressed(Keys.F1)) {
			if (gsh.BGMstate == true) {
				BGM.stop("menu");
				gsh.BGMstate= false;
			}
			else {
				BGM.loop("menu", 600, BGM.getFrames("menu") - 2200);
				gsh.BGMstate= true;
			}
		}
		
	}
	
}
