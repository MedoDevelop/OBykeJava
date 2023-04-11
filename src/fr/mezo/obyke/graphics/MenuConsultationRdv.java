package fr.mezo.obyke.graphics;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenuConsultationRdv extends MenuConsultationDroit {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MenuConsultationRdv(int lineLeft,int lineRight) {
		super(lineLeft,lineRight);
		
		//Mise en place formulaire
		JButton button1= new JButton("Modifier");
		JButton button2= new JButton("Supprimer");
		
		this.addTopSpace();
		
		button1.addActionListener((e) -> EditConfirmation());
		button2.addActionListener((e) -> DeleteConfirmation());
		
		JTextField deno=new JTextField(15);
		JTextField nomDir=new JTextField(15);
		JTextField prenomDir=new JTextField(15);
		JTextField tel=new JTextField(15);
		JTextField mail=new JTextField(15);
		JTextField date=new JTextField(15);
		JTextField heure=new JTextField(15);
		heure.setText("00:00");
		
		this.addLeft(new InputField("Dénomination : ",deno));
		this.addLeft(new InputField("Nom du Directeur : ",nomDir));
		this.addLeft(new InputField("Prénom du Directeur : ",prenomDir));
		this.addLeft(new InputField("Téléphone : ",tel));
		this.addLeft(new InputField("Mail : ",mail));
		
		this.addRight(new InputField("Date du RDV : ",date));
		this.addRight(new InputField("Heure du RDV : ",heure));
		
		this.addFirstBottomSpace();
		this.addFirstBottomSpace();
		this.addFirstBottomSpace();
		
		this.addSecondBottomSpace();
		this.addSecondBottom(button1);
		this.addSecondBottomSpace();
		this.addSecondBottom(button2);
		this.addSecondBottomSpace();
	
	}

}
