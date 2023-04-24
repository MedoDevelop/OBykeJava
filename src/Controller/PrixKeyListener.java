package Controller;

import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import fr.mezo.obyke.graphics.Main;

public class PrixKeyListener extends MainKeyListener {

	public PrixKeyListener(JTextField j) {
		super(j);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		char c = e.getKeyChar();
		
		if(!Character.isDigit(c) && c != '.') {
			e.consume();
		}
		
		if(c == '.' && this.contains(".")) {
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
