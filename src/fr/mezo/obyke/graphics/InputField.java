package fr.mezo.obyke.graphics;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InputField extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String libelle;
	private Component field;  
	
	public InputField(String leLib, Component leField) {
		//Libellé à gauche Input à droite
		super(new GridLayout(1,2));
		
		this.libelle=leLib;
		this.field=leField;
		
		JPanel p1=new JPanel(new FlowLayout());
		JPanel p2=new JPanel(new FlowLayout(FlowLayout.LEADING));
		
		this.setOpaque(false);
		p1.setOpaque(false);
		p2.setOpaque(false);
		
		JLabel lib=new JLabel(this.libelle);
		lib.setFont(Formulaire.getFontSize(17));
		
		p1.add(lib);
		p2.add(this.field);
		
		this.add(p1);
		this.add(p2);
		
	}
	
	public Component getComponent() {
		return this.field;
	}
	

}
