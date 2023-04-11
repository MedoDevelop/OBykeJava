package fr.mezo.obyke.graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormGarantie extends Formulaire {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FormGarantie(int lineLeft,int lineRight,String title) {
		
		super(lineLeft,lineRight,title);
		
		JTextField lib=new JTextField(20);
		JTextField prix=new JTextField(20);
		JTextField duree=new JTextField(20);
	
		this.addTopSpace();
		this.addTopSpace();
		
		this.addLeft(new InputField("Libellé de la garantie : ",lib));
		this.addLeft(new InputField("Prix de la garantie : ",prix));
		this.addLeft(new InputField("Duréee de la garantie : ",duree));
		
		this.addFirstBottomSpace();
		this.addFirstBottomSpace();
		this.addFirstBottomSpace();

		JButton button1= new JButton("Annuler");
		JButton button2= new JButton("Autre Garantie");
		JButton button3= new JButton("Valider");
		
		this.addSecondBottomSpace();
		this.addSecondBottom(button1);
		this.addSecondBottom(button2);
		this.addSecondBottom(button3);
		this.addSecondBottomSpace();
		
	}

}
