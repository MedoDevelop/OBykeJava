package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class TelephoneKeyListerner extends MainKeyListener{
	//Le champs doit etre de 10 chiffre
	
	public TelephoneKeyListerner(JTextField j) {
		super(j);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		char c = e.getKeyChar();
		if(!Character.isDigit(c)) {
			e.consume();
		}
		
		if(this.getTextSize() == 10) {
			e.consume();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
