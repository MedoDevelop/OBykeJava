package fr.mezo.obyke.graphics;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenuConsultationServices extends MenuConsultationDroit {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MenuConsultationServices(int lineLeft,int lineRight) {
		super(lineLeft,lineRight);
		
		//Mise en place formulaire
		JComboBox type=new JComboBox();
		type.setPreferredSize(new Dimension(162,28));
		
		this.addTop(new InputField("Type Service : ",type));

		JTextField deno=new JTextField(15);
		JTextField nomDir=new JTextField(15);
		JTextField prenomDir=new JTextField(15);
		JTextField tel=new JTextField(15);
		
		JComboBox categ=new JComboBox();
		categ.setPreferredSize(new Dimension(162,28));
		
		JTextField marque=new JTextField(15);
		JTextField fournisseur=new JTextField(15);
		JTextField dateAchat=new JTextField(15);
		JTextField dateDepot=new JTextField(15);
		JTextField mail=new JTextField(15);

		this.addLeft(new InputField("Dénomination : ",deno));
		this.addLeft(new InputField("Nom du Directeur : ",nomDir));
		this.addLeft(new InputField("Prénom du Directeur : ",prenomDir));
		this.addLeft(new InputField("Téléphone : ",tel));
		this.addLeft(new InputField("Mail : ",mail));
		
		this.addRight(new InputField("Catégorie : ",categ));
		this.addRight(new InputField("Marque : ",marque));
		this.addRight(new InputField("Fournisseur : ",fournisseur));
		this.addRight(new InputField("Date Achat : ",dateAchat));
		this.addRight(new InputField("Date Dépôt : ",dateDepot));
		
				
		this.addFirstBottomSpace();
		this.addFirstBottomSpace();
		this.addFirstBottomSpace();

		JButton button1= new JButton("Modifier");
		JButton button2= new JButton("Supprimer");

		this.addSecondBottomSpace();
		this.addSecondBottom(button1);
		this.addSecondBottomSpace();
		this.addSecondBottom(button2);
		this.addSecondBottomSpace();
	
		
		
	}

}
