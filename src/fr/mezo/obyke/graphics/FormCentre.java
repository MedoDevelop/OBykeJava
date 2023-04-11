package fr.mezo.obyke.graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormCentre extends Formulaire {
	
	public FormCentre(int lineLeft,int lineRight,String title) {
		
		super(lineLeft,lineRight,title);
		
		JTextField deno=new JTextField(20);
		JTextField nomDir=new JTextField(20);
		JTextField prenomDir=new JTextField(20);
		JTextField tel=new JTextField(20);
		JTextField mail=new JTextField(20);
		
		JComboBox type=new JComboBox();
		type.setPreferredSize(new Dimension(212,28));

		this.addTopSpace();
		this.addTopSpace();
		
		this.addLeft(new InputField("Dénomination : ",deno));
		this.addLeft(new InputField("Nom du Directeur : ",nomDir));
		this.addLeft(new InputField("Prénom du Directeur : ",prenomDir));
		
		this.addRight(new InputField("Téléphone : ",tel));
		this.addRight(new InputField("Mail : ",mail));
		this.addRight(new InputField("Type : ",type));
		
		this.addFirstBottomSpace();
		this.addFirstBottomSpace();
		this.addFirstBottomSpace();

		JButton button1= new JButton("Annuler");
		JButton button2= new JButton("Autre Centre");
		JButton button3= new JButton("Valider");
		
		this.addSecondBottomSpace();
		this.addSecondBottom(button1);
		this.addSecondBottom(button2);
		this.addSecondBottom(button3);
		this.addSecondBottomSpace();
		
	}

}
