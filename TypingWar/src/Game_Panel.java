import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game_Panel extends JPanel implements Runnable, KeyListener {
	
	//dimensions
	private static final int WIDTH=500;
	private static final int HEIGHT=400;
	private static final int SCALE=2;
	
	//image
	private BufferedImage image;
	private Graphics2D g;
	
	//game thread
	private Thread thread;
	private boolean running;
	private int FPS=60;
	private long targetTime;
	
	private Game_State_Handler gsh;
	
	public Game_Panel() {
		super();
		targetTime= 1000/FPS;
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		setFocusable(true);
		requestFocus();	
	}
	
	private void init() {
		image= new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g= (Graphics2D) image.getGraphics();
		running= true;
		gsh= new Game_State_Handler();
//		input= new JTextField();
//		input.setLocation(200, 200);
//		this.add(input);
	}
	
	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}
	
	public void run() {
		init();
		
		long start;
		long end;
		long wait;
		
		// loop
		while(running) {
			
			start= System.nanoTime();
			
			update();
			draw();
			drawScreen();
			
			end= System.nanoTime()-start;
			
			wait= targetTime - end/1000000;
			if(wait < 0) wait=5;
			
			try {
				Thread.sleep(wait);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void update() {
		gsh.update();
		Keys.update();		
	}
	
	private void draw() {
		gsh.draw(g);
	}
	
	private void drawScreen() {
		Graphics g2= getGraphics();
		g2.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		g2.dispose();
	}

	public void keyTyped(KeyEvent e) {}

	public void keyPressed(KeyEvent e) {
		Keys.keySet(e.getKeyCode(), true);
	}

	public void keyReleased(KeyEvent e) {
		Keys.keySet(e.getKeyCode(), false);
	}	

}
