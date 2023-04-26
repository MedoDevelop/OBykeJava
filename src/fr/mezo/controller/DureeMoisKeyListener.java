package fr.mezo.controller;

import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class DureeMoisKeyListener extends MainKeyListener{

	public DureeMoisKeyListener(JTextField j) {
		super(j);
		// TODO Auto-generated constructor stub
	}

	//Duree en nombre d'années, ne peut etre que des nomnbre et de longueur 2 max 
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		char c = e.getKeyChar();
		if(!Character.isDigit(c) || this.getTextSize() == 2) {
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
