package fr.mezo.controller;

import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class DateKeyListener extends MainKeyListener{

	public DateKeyListener(JTextField j) {
		super(j);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
		//Date au format jj/mm/aaaa 
		
		char c = e.getKeyChar();
		
		if(!Character.isDigit(c) && c != '/') {
			e.consume();
		}
		
		if(c == '/' && !(this.getTextSize() == 2 || this.getTextSize() == 5)) {
			e.consume();
		}
		
		if(c != '/' && (this.getTextSize() == 2 || this.getTextSize() == 5)) {
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
