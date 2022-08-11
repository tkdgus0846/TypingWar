import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

public class Character { 
	
	private BufferedImage character;
	private int x,y;
	
	public Character() {
			
		try {
			character= ImageIO.read(
					getClass().getResourceAsStream(
							"/Character.png"
							)
					);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		Random rand= new Random();
		
		x= rand.nextInt(100);
		y= rand.nextInt(200);
		
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(character, x, y, null);
	}
	

}
