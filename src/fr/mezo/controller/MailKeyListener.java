package fr.mezo.controller;

import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class MailKeyListener extends MainKeyListener{
	
	//Controlle un champ de mail, le mail doit contenir un @
	
	public MailKeyListener(JTextField j) {
		super(j);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		char c = e.getKeyChar();
		//On controle qu'il est un seul @, si il a déja on ne le met pas
		if(c == '@' && this.contains("@")) {
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
