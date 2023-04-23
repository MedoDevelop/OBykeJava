package fr.mezo.obyke.graphics;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenuVenteMatNeuf extends MenuConsultationDroit {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MenuVenteMatNeuf(int lineLeft,int lineRight) {
		super(lineLeft,lineRight);
		
		//Mise en place formulaire
		JButton button1= new JButton("Vendre");
		JButton button2= new JButton("Annuler");
		
		//button1.addActionListener((e) -> EditConfirmation());
		
		this.addTopSpace();
		
		JTextField coloris=new JTextField(15);
		JTextField prixAchat=new JTextField(15);
		JTextField prixVente=new JTextField(15);
		JTextField dateMiseVente=new JTextField(15);
		
		JComboBox categ=new JComboBox();
		categ.setPreferredSize(new Dimension(162,28));
		
		JComboBox garantie=new JComboBox();
		garantie.setPreferredSize(new Dimension(162,28));
		
		this.addLeft(new InputField("Catégorie : ",categ));
		this.addLeft(new InputField("Coloris : ",coloris));
		this.addLeft(new InputField("Prix Achat : ",prixAchat));
		this.addLeft(new InputField("Prix Vente : ",prixVente));
		this.addLeft(new InputField("Date Mise en Vente : ",dateMiseVente));
		this.addLeft(new InputField("Nouvelle Garantie : ",garantie));
		
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
