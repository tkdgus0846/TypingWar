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
		
		
		g.setFont(new Font("굴림", Font.PLAIN,14));
		g.setColor(Color.WHITE);
		g.drawString("<<게임설명>>", 150, 20);
		g.drawString("ENTER: 키 입력하기", 80, 40);
		g.drawString("ESC: 메뉴로 돌아오기", 80, 60);
		g.drawString("backspace: 글자 지우기 (한글자씩만 해당)", 80, 80);
		g.drawString("F1: BGM ON/OFF", 80, 100);
		g.drawString("F2: PAUSE/RESUME (게임 도중에만 가능)", 80, 120);
		g.drawString("10초 마다 아군 사과와 적군 애벌레의 숫자를 비교하여", 20, 160);
		g.drawString("많은 쪽이 그 수만큼 데미지를 입힙니다.", 20, 180);
		g.drawString("HP가 0 이 되면 GAME OVER 입니다.", 20, 200);
		g.setFont(new Font("굴림", Font.PLAIN,12));
		g.setColor(Color.WHITE);
		g.drawString("메뉴로 돌아가시려면 ESC키를 눌러주세요.", 80, 270);
		
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
