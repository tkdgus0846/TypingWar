
import javax.swing.JFrame;

public class TypingWarMain {

	public static void main(String[] args) {
		JFrame frame= new JFrame("[Typing War]");
		
		frame.add(new Game_Panel());
		frame.setSize(800,600);
		frame.setLocation(400,100);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}

}
