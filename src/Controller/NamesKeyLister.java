package Controller;

import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class NamesKeyLister extends MainKeyListener{

	//Controle les nom et prenom, pas de chifre pas d'espaces pas de tiret du 8
	
	public NamesKeyLister(JTextField j) {
		super(j);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		char c = e.getKeyChar();
		
		// Si c n'est pas une lettre && n'est pas un - alors e.consume()
		if(!Character.isLetter(c) && c != '-') {
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
