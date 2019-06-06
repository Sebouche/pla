package ricm3.interpreter;

import java.awt.event.KeyEvent;

public enum Keys {

	A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, N_1, N_2, N_3, N_4, N_5, N_6, N_7,
	N_8, N_9, N_0, UP, DOWN, RIGHT, LEFT, ENTER, SPACE, NONE;

	public static Keys keyEventToKeys(KeyEvent k) {
		switch (k.getKeyCode()) {
		case (KeyEvent.VK_A):
			return Keys.A;
		case (KeyEvent.VK_B):
			return Keys.B;
		case (KeyEvent.VK_C):
			return Keys.C;
		case (KeyEvent.VK_D):
			return Keys.D;
		case (KeyEvent.VK_E):
			return Keys.E;
		case (KeyEvent.VK_F):
			return Keys.F;
		case (KeyEvent.VK_G):
			return Keys.G;
		case (KeyEvent.VK_H):
			return Keys.H;
		case (KeyEvent.VK_I):
			return Keys.I;
		case (KeyEvent.VK_J):
			return Keys.J;
		case (KeyEvent.VK_K):
			return Keys.K;
		case (KeyEvent.VK_L):
			return Keys.L;
		case (KeyEvent.VK_M):
			return Keys.M;
		case (KeyEvent.VK_N):
			return Keys.N;
		case (KeyEvent.VK_O):
			return Keys.O;
		case (KeyEvent.VK_P):
			return Keys.P;
		case (KeyEvent.VK_Q):
			return Keys.Q;
		case (KeyEvent.VK_R):
			return Keys.R;
		case (KeyEvent.VK_S):
			return Keys.S;
		case (KeyEvent.VK_T):
			return Keys.T;
		case (KeyEvent.VK_U):
			return Keys.U;
		case (KeyEvent.VK_V):
			return Keys.V;
		case (KeyEvent.VK_W):
			return Keys.W;
		case (KeyEvent.VK_X):
			return Keys.X;
		case (KeyEvent.VK_Y):
			return Keys.Y;
		case (KeyEvent.VK_Z):
			return Keys.Z;
		case (KeyEvent.VK_0):
			return Keys.N_0;
		case (KeyEvent.VK_1):
			return Keys.N_1;
		case (KeyEvent.VK_2):
			return Keys.N_2;
		case (KeyEvent.VK_3):
			return Keys.N_3;
		case (KeyEvent.VK_4):
			return Keys.N_4;
		case (KeyEvent.VK_5):
			return Keys.N_5;
		case (KeyEvent.VK_6):
			return Keys.N_6;
		case (KeyEvent.VK_7):
			return Keys.N_7;
		case (KeyEvent.VK_8):
			return Keys.N_8;
		case (KeyEvent.VK_9):
			return Keys.N_9;
		case (KeyEvent.VK_UP):
			return Keys.UP;
		case (KeyEvent.VK_DOWN):
			return Keys.DOWN;
		case (KeyEvent.VK_RIGHT):
			return Keys.RIGHT;
		case (KeyEvent.VK_LEFT):
			return Keys.LEFT;
		case (KeyEvent.VK_ENTER):
			return Keys.ENTER;
		case (KeyEvent.VK_SPACE):
			return Keys.SPACE;
		default:
			return Keys.NONE;
		}
	}

	public static Keys strToKeys(String str) {
		switch (str) {
		case ("A"):
		case ("a"):
			return Keys.A;
		case ("b"):
		case ("B"):
			return Keys.B;
		case ("c"):
		case ("C"):
			return Keys.C;
		case ("d"):
		case ("D"):
			return Keys.D;
		case ("e"):
		case ("E"):
			return Keys.E;
		case ("f"):
		case ("F"):
			return Keys.F;
		case ("g"):
		case ("G"):
			return Keys.G;
		case ("h"):
		case ("H"):
			return Keys.H;
		case ("i"):
		case ("I"):
			return Keys.I;
		case ("j"):
		case ("J"):
			return Keys.J;
		case ("k"):
		case ("K"):
			return Keys.K;
		case ("l"):
		case ("L"):
			return Keys.L;
		case ("m"):
		case ("M"):
			return Keys.M;
		case ("n"):
		case ("N"):
			return Keys.N;
		case ("o"):
		case ("O"):
			return Keys.O;
		case ("p"):
		case ("P"):
			return Keys.P;
		case ("q"):
		case ("Q"):
			return Keys.Q;
		case ("r"):
		case ("R"):
			return Keys.R;
		case ("s"):
		case ("S"):
			return Keys.S;
		case ("t"):
		case ("T"):
			return Keys.T;
		case ("u"):
		case ("U"):
			return Keys.U;
		case ("v"):
		case ("V"):
			return Keys.V;
		case ("w"):
		case ("W"):
			return Keys.W;
		case ("x"):
		case ("X"):
			return Keys.X;
		case ("y"):
		case ("Y"):
			return Keys.Y;
		case ("z"):
		case ("Z"):
			return Keys.Z;
		case ("0"):
			return Keys.N_0;
		case ("1"):
			return Keys.N_1;
		case ("2"):
			return Keys.N_2;
		case ("3"):
			return Keys.N_3;
		case ("4"):
			return Keys.N_4;
		case ("5"):
			return Keys.N_5;
		case ("6"):
			return Keys.N_6;
		case ("7"):
			return Keys.N_7;
		case ("8"):
			return Keys.N_8;
		case ("9"):
			return Keys.N_9;
		case ("FU"):
			return Keys.UP;
		case ("FD"):
			return Keys.DOWN;
		case ("FR"):
			return Keys.RIGHT;
		case ("FL"):
			return Keys.LEFT;
		case ("ENTER"):
			return Keys.ENTER;
		case ("SPACE"):
			return Keys.SPACE;
		default:
			return Keys.NONE;
		}
	}

}