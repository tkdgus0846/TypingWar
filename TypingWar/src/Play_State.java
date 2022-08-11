
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Play_State extends Game_State{ 
	                                          
	private final int WORDS_SIZE=7; 
	private final int wordsPosition[]= new int[WORDS_SIZE];
	private final int GENE_ENEMY_NUM=6;
		
	private int charChoice=0;
	private boolean drawAlphabet=false; 
	private boolean eraseAlphabet=false;
	private boolean win=false;
	private boolean lose=false;
	private boolean pauseState=false;
	private long attackCycle= 10;
	
	private ArrayList <Character> characters= new ArrayList<>();
	private ArrayList <Enemy> enemies= new ArrayList<>();
	private ArrayList <String> words= new ArrayList<>();
	private ArrayList <String> displayWords= new ArrayList<>();
	
	private String keyInputString="";
	private double start,end,pauseStart,pauseEnd;
	private double time;	
	private int userHP= 50;
	private int enemyHP= 50;
	private int successNum= 0;
	
	private int timeSpace=193;
	private int space=195;
	
	public Play_State(Game_State_Handler gsh) {
		super(gsh);	
		init();	
	}

	public void init() {
		
		start= System.currentTimeMillis();
			
		geneEnemy();
		Dictionary.setWords(words);
		
		int yPos= 10;
		for (int i=0; i<WORDS_SIZE; i++) {
			wordsPosition[i]=  (yPos+=30);
		}
		BGM.load("/play.wav", "play");
		BGM.load("/keyTyping.wav", "keyTyping");
		BGM.load("/attack.wav", "attack");
		BGM.load("/win.wav", "win");
		BGM.load("/lose.wav", "lose");
		BGM.stop("menu");
		
		if (gsh.BGMstate == true) {						
			BGM.loop("play", 600, BGM.getFrames("play") - 2200);			
		}	
				
	}

	public void update() {
		handleInput();
	}

	public void draw(Graphics2D g) {
		
		if (pauseState == true) {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, 1000, 800);
			g.setFont(new Font("Arial",Font.BOLD,30));
			g.setColor(Color.BLACK);
			g.drawString("PAUSE", 144, 140);
			g.setFont(new Font("굴림",Font.PLAIN,12));
			g.drawString("게임을 재개 하시려면 F2를 눌러주세요", 95, 230);
		}
		else {
			if (win == false && lose == false) {
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, 1000, 800);
				
				g.setFont(new Font("Arial", Font.PLAIN,14));
				g.setColor(Color.BLACK);
						
				drawTyp();              
				eraseTyp();             
				g.drawString(keyInputString,space,260);
				drawNums(g);
				drawCenterLines(g);
				drawWords(g);
				drawObject(g);			
			}
			
			if (win == true) {
				
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, 1000, 800);
				g.setFont(new Font("Arial",Font.BOLD,30));
				g.setColor(Color.BLACK);
				g.drawString("YOU WIN", 127, 140);
				
				g.setFont(new Font("굴림",Font.PLAIN,11));
				g.setColor(Color.BLACK);
				g.drawString("기록: "+String.format("%.0f", time)+"초", 220, 160);
				g.drawString("단어 수: "+successNum+"개", 205, 175);
				
				g.setFont(new Font("굴림",Font.PLAIN,12));
				g.setColor(Color.BLACK);
				g.drawString("돌아가시려면 ESC키를 눌러주세요", 105, 230);
				
			}
			
			if (lose == true) {
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, 1000, 800);
				g.setFont(new Font("Arial",Font.BOLD,30));
				g.setColor(Color.BLACK);
				g.drawString("YOU LOSE", 120, 140);
				
				g.setFont(new Font("굴림",Font.PLAIN,11));
				g.setColor(Color.BLACK);
				g.drawString("기록: "+String.format("%.0f", time)+"초", 220, 160);
				g.drawString("단어 수: "+successNum+"개", 205, 175);
				
				g.setFont(new Font("굴림",Font.PLAIN,12));
				g.setColor(Color.BLACK);
				g.drawString("돌아가시려면 ESC키를 눌러주세요", 105, 230);
			}
		}				
	}
	
	private void eraseTyp() {
		if(eraseAlphabet) {
			if(keyInputString.length()!=0 ) {
				keyInputString=keyInputString.substring(0, keyInputString.length()-1); 
				space+=3;
				}
			eraseAlphabet= false;
		}		
	}
	
	private void drawTyp() {
		if(drawAlphabet==true && charChoice!=0) {
			keyInputString+=(char)(charChoice+96);                    
			drawAlphabet= false;
			space-=3;
			}
	}
	
	private void drawNums(Graphics2D g) {
		end= System.currentTimeMillis();
		time= (end-start)/1000;
		setTimeSpace(time);
		
		if(Math.floor(time)>=attackCycle) {
			if(characters.size()>enemies.size()) {
				enemyHP-=characters.size()-enemies.size();
			}
			else {
				userHP-=enemies.size()-characters.size();
			}
						
			geneEnemy();
				
			attackCycle+=10;
					
			if (userHP <=0) lose=true;
			if (enemyHP <= 0) win= true;
			
			if (win == false && lose == false)
				BGM.play("attack");
			}
		
		if (gsh.BGMstate == true) {
			if (win == true) {
				BGM.stop("play");
				BGM.play("win");
				}
			if (lose == true) {
				BGM.stop("play");
				BGM.play("lose");
				}
		}
		
		
		g.drawString("num: "+ String.valueOf(characters.size()), 5, 260);
		g.drawString("HP: "+userHP, 65, 260);
		g.drawString("num: "+ String.valueOf(enemies.size()), 275, 260);
		g.drawString("HP: "+enemyHP, 335, 260);
		g.drawString( String.format("%.0f", time), timeSpace, 10);		
		
	}
	
	private void setTimeSpace(double time) {
	    if (time>=10.0 && time<100) {
	    	timeSpace=189;
		}
	    if (time>=100) {
	    	timeSpace=185;
		}
	}
	
	private void drawCenterLines(Graphics2D g) {
		
		g.drawLine(145, 0, 145, 240);
		g.drawLine(0, 0, 145, 0);
		g.drawLine(0, 240, 145, 240);
		
		g.drawLine(145, 240, 145, 300);
		g.drawLine(145, 240, 250, 240);
		g.drawLine(250, 240, 250, 300);
		
		g.drawLine(250, 0, 250, 240);
		g.drawLine(250, 0, 400, 0);
		g.drawLine(250, 240, 400, 240);
	}
	
	private void drawWords(Graphics2D g) {
		Random rand= new Random();
		
		if (displayWords.size() < 7) 
			for (int i= displayWords.size(); i<WORDS_SIZE; i++) {
				displayWords.add(words.get(rand.nextInt(words.size())));
			}		
		
		for (int i=0; i<WORDS_SIZE; i++) {
			g.drawString(displayWords.get(i), 170, wordsPosition[i]);
		}
		
	}
	
	private void geneEnemy() {
		for (int i=0; i<GENE_ENEMY_NUM; i++) {
			Enemy enemy= new Enemy();
			enemies.add(enemy);		
		}
	}
	
	private void drawObject(Graphics2D g) {		
		if (characters.isEmpty() == false) {
			for (int i=0; i<characters.size(); i++) {
				characters.get(i).draw(g);
			}
		}
		
		if (enemies.isEmpty() == false) {
			for (int i=0; i<enemies.size(); i++) {
				enemies.get(i).draw(g);
			}
		}	
	}
	
	public void handleInput() {
		
		if(Keys.isPressed(Keys.ESCAPE)) {
			gsh.setState(Game_State_Handler.MENUSTATE);
			BGM.stop("win");
			BGM.stop("lose");
			BGM.stop("play");
		}		
		if(Keys.isPressed(Keys.F1)) {
			if (gsh.BGMstate == true) {
				BGM.stop("play");
				BGM.stop("win");
				BGM.stop("lose");
				gsh.BGMstate= false;
			}
			else {
				if (win == false && lose == false && pauseState == false)
					BGM.loop("play", 600, BGM.getFrames("play") - 2200);
				if (win == true)
					BGM.play("win");
				if (lose == true)
					BGM.play("lose");
				
				gsh.BGMstate= true;
			}
		}		
		if (Keys.isPressed(Keys.F2)) {
			if(win == false && lose == false) {
				if (pauseState == false) {
					pauseStart= System.currentTimeMillis();
					pauseState= true;
					BGM.stop("play");
					}
				else if (pauseState == true) {		
					pauseEnd= System.currentTimeMillis();
					start= start+pauseEnd-pauseStart;
					pauseState= false;
					
					if(gsh.BGMstate == true)
						BGM.loop("play", 600, BGM.getFrames("play") - 2200);
				}
			}						
		}			
		
		if(win == false && lose == false && pauseState == false) {
			if(Keys.isPressed(Keys.BACKSPACE)) {
				eraseAlphabet=true;
			}
			if(Keys.isPressed(Keys.ENTER)) {
				
				if (displayWords.contains(keyInputString)) {
					displayWords.remove(keyInputString);
					Character charac= new Character();
					characters.add(charac);
					keyInputString= "";
					space= 195;
					successNum++;
					}
				
				space+=(keyInputString.length()*3);
				keyInputString="";
			}
			if(Keys.isReleased()) {
			}
			if(Keys.isPressed(Keys.A)) {
				BGM.play("keyTyping");
				charChoice=1;
				drawAlphabet=true;
			}
			if(Keys.isPressed(Keys.B)) {
				BGM.play("keyTyping");
				charChoice=2;
				drawAlphabet=true;
			}
			if(Keys.isPressed(Keys.C)) {
				BGM.play("keyTyping");
				charChoice=3;
				drawAlphabet=true;
			}
			if(Keys.isPressed(Keys.D)) {
				BGM.play("keyTyping");
				charChoice=4;
				drawAlphabet=true;
			}
			if(Keys.isPressed(Keys.E)) {
				BGM.play("keyTyping");
				charChoice=5;
				drawAlphabet=true;
			}
			if(Keys.isPressed(Keys.F)) {
				BGM.play("keyTyping");
				charChoice=6;
				drawAlphabet=true;
			}
			if(Keys.isPressed(Keys.G)) {
				BGM.play("keyTyping");
				charChoice=7;
				drawAlphabet=true;
			}
			if(Keys.isPressed(Keys.H)) {
				BGM.play("keyTyping");
				charChoice=8;
				drawAlphabet=true;
			}
			if(Keys.isPressed(Keys.I)) {
				BGM.play("keyTyping");
				charChoice=9;
				drawAlphabet=true;
			}
			if(Keys.isPressed(Keys.J)) {
				BGM.play("keyTyping");
				charChoice=10;
				drawAlphabet=true;
			}
			if(Keys.isPressed(Keys.K)) {
				BGM.play("keyTyping");
				charChoice=11;
				drawAlphabet=true;
			}
			if(Keys.isPressed(Keys.L)) {
				BGM.play("keyTyping");
				charChoice=12;
				drawAlphabet=true;
			}
			if(Keys.isPressed(Keys.M)) {
				BGM.play("keyTyping");
				charChoice=13;
				drawAlphabet=true;
			}
			if(Keys.isPressed(Keys.N)) {
				BGM.play("keyTyping");
				charChoice=14;
				drawAlphabet=true;
			}
			if(Keys.isPressed(Keys.O)) {
				BGM.play("keyTyping");
				charChoice=15;
				drawAlphabet=true;
			}
			if(Keys.isPressed(Keys.P)) {
				BGM.play("keyTyping");
				charChoice=16;
				drawAlphabet=true;
			}
			if(Keys.isPressed(Keys.Q)) {
				BGM.play("keyTyping");
				charChoice=17;
				drawAlphabet=true;
			}
			if(Keys.isPressed(Keys.R)) {
				BGM.play("keyTyping");
				charChoice=18;
				drawAlphabet=true;
			}
			if(Keys.isPressed(Keys.S)) {
				BGM.play("keyTyping");
				charChoice=19;
				drawAlphabet=true;
			}
			if(Keys.isPressed(Keys.T)) {
				BGM.play("keyTyping");
				charChoice=20;
				drawAlphabet=true;
			}
			if(Keys.isPressed(Keys.U)) {
				BGM.play("keyTyping");
				charChoice=21;
				drawAlphabet=true;
			}
			if(Keys.isPressed(Keys.V)) {
				BGM.play("keyTyping");
				charChoice=22;
				drawAlphabet=true;
			}
			if(Keys.isPressed(Keys.W)) {
				BGM.play("keyTyping");
				charChoice=23;
				drawAlphabet=true;
			}
			if(Keys.isPressed(Keys.X)) {
				BGM.play("keyTyping");
				charChoice=24;
				drawAlphabet=true;
			}
			if(Keys.isPressed(Keys.Y)) {
				BGM.play("keyTyping");
				charChoice=25;
				drawAlphabet=true;
			}
			if(Keys.isPressed(Keys.Z)) {
				BGM.play("keyTyping");
				charChoice=26;
				drawAlphabet=true;
			}		
		}
	}

}
