package leapmotion.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KListener implements KeyListener {
	
	private static char c='/';

	@Override
	public void keyPressed(KeyEvent event) {
		// TODO Auto-generated method stub
		this.setC(event.getKeyChar());
	}

	@Override
	public void keyReleased(KeyEvent event) {
		// TODO Auto-generated method stub
		this.setC(event.getKeyChar());
	}

	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub
		this.setC(event.getKeyChar());
		System.out.println("Touche " + c + " appuyée");
	}

	public char getC() {
		return c;
	}

	public void setC(char c) {
		KListener.c = c;
	}
	
	

}
