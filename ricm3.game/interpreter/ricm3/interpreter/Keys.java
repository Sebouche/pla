package ricm3.interpreter;

import java.awt.event.KeyEvent;

public enum Keys {

	A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,N_1,N_2,N_3,N_4,N_5,N_6,N_7,N_8,N_9,N_0,UP,DOWN,RIGHT,LEFT,NONE;
	
	
	public static Keys keyEventToKeys(KeyEvent k) {
		switch(k.getKeyCode()) {
		case(KeyEvent.VK_A): return Keys.A;
		default: return Keys.NONE;
		}
	}
	
}
