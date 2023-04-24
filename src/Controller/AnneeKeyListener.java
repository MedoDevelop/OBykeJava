package Controller;

import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class AnneeKeyListener extends MainKeyListener{

	public AnneeKeyListener(JTextField j) {
		super(j);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		char c = e.getKeyChar();
		
		if(!Character.isDigit(c)) {
			e.consume();
		}
		
		if(this.getTextSize() == 4) {
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
