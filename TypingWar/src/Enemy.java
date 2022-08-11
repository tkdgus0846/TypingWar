import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Enemy { 
	
	private BufferedImage enemy;
	private int x,y;
	
	public Enemy() {
		
		try {
			enemy= ImageIO.read(
					getClass().getResourceAsStream(
							"/Enemy.png"
							)
					);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		Random rand= new Random();
		
		x= rand.nextInt(100)+250;
		y= rand.nextInt(200);
		
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(enemy, x, y, null);
	}
	
}
