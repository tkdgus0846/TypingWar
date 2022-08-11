import java.awt.event.KeyEvent;

public class Keys {

	public static final int NUM_KEYS = 33;

	public static boolean keyState[] = new boolean[NUM_KEYS];
	public static boolean prevKeyState[] = new boolean[NUM_KEYS];

	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int ENTER = 2;
	public static final int ESCAPE = 3;
	public static final int A = 4;
	public static final int B = 5;
	public static final int C = 6;
	public static final int D = 7;
	public static final int E = 8;
	public static final int F = 9;
	public static final int G = 10;
	public static final int H = 11;
	public static final int I = 12;
	public static final int J = 13;
	public static final int K = 14;
	public static final int L = 15;
	public static final int M = 16;
	public static final int N = 17;
	public static final int O = 18;
	public static final int P = 19;
	public static final int Q = 20;
	public static final int R = 21;
	public static final int S = 22;
	public static final int T = 23;
	public static final int U = 24;
	public static final int V = 25;
	public static final int W = 26;
	public static final int X = 27;
	public static final int Y = 28;
	public static final int Z = 29;
	public static final int BACKSPACE= 30;
	public static final int F1= 31;
	public static final int F2= 32;

	public static void keySet(int i, boolean b) {
		if (i == KeyEvent.VK_UP)
			keyState[UP] = b;	
		else if (i == KeyEvent.VK_DOWN)
			keyState[DOWN] = b;
		else if (i == KeyEvent.VK_ENTER)
			keyState[ENTER] = b;
		else if (i == KeyEvent.VK_ESCAPE)
			keyState[ESCAPE] = b;
		else if (i == KeyEvent.VK_A)
			keyState[A] = b;
		else if (i == KeyEvent.VK_B)
			keyState[B] = b;
		else if (i == KeyEvent.VK_C)
			keyState[C] = b;
		else if (i == KeyEvent.VK_D)
			keyState[D] = b;
		else if (i == KeyEvent.VK_E)
			keyState[E] = b;
		else if (i == KeyEvent.VK_F)
			keyState[F] = b;
		else if (i == KeyEvent.VK_G)
			keyState[G] = b;
		else if (i == KeyEvent.VK_H)
			keyState[H] = b;
		else if (i == KeyEvent.VK_I)
			keyState[I] = b;
		else if (i == KeyEvent.VK_J)
			keyState[J] = b;
		else if (i == KeyEvent.VK_K)
			keyState[K] = b;
		else if (i == KeyEvent.VK_L)
			keyState[L] = b;
		else if (i == KeyEvent.VK_M)
			keyState[M] = b;
		else if (i == KeyEvent.VK_N)
			keyState[N] = b;
		else if (i == KeyEvent.VK_O)
			keyState[O] = b;
		else if (i == KeyEvent.VK_P)
			keyState[P] = b;
		else if (i == KeyEvent.VK_Q)
			keyState[Q] = b;
		else if (i == KeyEvent.VK_R)
			keyState[R] = b;
		else if (i == KeyEvent.VK_S)
			keyState[S] = b;
		else if (i == KeyEvent.VK_T)
			keyState[T] = b;
		else if (i == KeyEvent.VK_U)
			keyState[U] = b;
		else if (i == KeyEvent.VK_V)
			keyState[V] = b;
		else if (i == KeyEvent.VK_W)
			keyState[W] = b;
		else if (i == KeyEvent.VK_X)
			keyState[X] = b;
		else if (i == KeyEvent.VK_Y)
			keyState[Y] = b;
		else if (i == KeyEvent.VK_Z)
			keyState[Z] = b;
		else if (i == KeyEvent.VK_F1)
			keyState[F1] = b;
		else if (i == KeyEvent.VK_F2)
			keyState[F2] = b;
		else if (i == KeyEvent.VK_BACK_SPACE)
			keyState[BACKSPACE] = b;
		
	}

	public static void update() {
		for (int i = 0; i < NUM_KEYS; i++) {
			prevKeyState[i] = keyState[i];
		}
	}

	public static boolean isPressed(int i) {
		return keyState[i] && !prevKeyState[i];
	}
	
	public static boolean isReleased() {
		for (int i = 0; i < NUM_KEYS; i++) {
			if (keyState[i])
				return false;
		}
		return true;
	}

}
