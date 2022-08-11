import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Description_State extends Game_State{
	
	public Description_State(Game_State_Handler gsh) {
		super(gsh);
		init();
	}

	public void init() {
	}

	public void update() {
		handleInput();
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0,0,1000, 800);
		
		
		g.setFont(new Font("����", Font.PLAIN,14));
		g.setColor(Color.WHITE);
		g.drawString("<<���Ӽ���>>", 150, 20);
		g.drawString("ENTER: Ű �Է��ϱ�", 80, 40);
		g.drawString("ESC: �޴��� ���ƿ���", 80, 60);
		g.drawString("backspace: ���� ����� (�ѱ��ھ��� �ش�)", 80, 80);
		g.drawString("F1: BGM ON/OFF", 80, 100);
		g.drawString("F2: PAUSE/RESUME (���� ���߿��� ����)", 80, 120);
		g.drawString("10�� ���� �Ʊ� ����� ���� �ֹ����� ���ڸ� ���Ͽ�", 20, 160);
		g.drawString("���� ���� �� ����ŭ �������� �����ϴ�.", 20, 180);
		g.drawString("HP�� 0 �� �Ǹ� GAME OVER �Դϴ�.", 20, 200);
		g.setFont(new Font("����", Font.PLAIN,12));
		g.setColor(Color.WHITE);
		g.drawString("�޴��� ���ư��÷��� ESCŰ�� �����ּ���.", 80, 270);
		
	}

	public void handleInput() {
		if(Keys.isPressed(Keys.ESCAPE)) {
			gsh.setState(Game_State_Handler.MENUSTATE);
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
