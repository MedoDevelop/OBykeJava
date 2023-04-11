package fr.mezo.obyke.graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormMaterielNeuf extends Formulaire {
	
	public FormMaterielNeuf(int lineLeft,int lineRight,String title) {
		
		super(lineLeft,lineRight,title);
		this.addTopSpace();
		this.addTopSpace();
		
		JTextField societe=new JTextField(20);
		JTextField annee=new JTextField(20);
		JTextField prixAchat=new JTextField(20);
		JTextField dateAchat=new JTextField(20);
		
		JComboBox categ=new JComboBox();
		categ.setPreferredSize(new Dimension(212,28));
		
		JTextField coloris=new JTextField(20);
		JTextField prixVente=new JTextField(20);
		JTextField dateMiseVente=new JTextField(20);
		
		this.addLeft(new InputField("Société : ",societe));
		this.addLeft(new InputField("Année : ",annee));
		this.addLeft(new InputField("Prix Achat : ",prixAchat));
		this.addLeft(new InputField("Date Achat : ",dateAchat));
		
		this.addRight(new InputField("Catégorie : ",categ));
		this.addRight(new InputField("Coloris : ",coloris));
		this.addRight(new InputField("Prix Vente : ",prixVente));
		this.addRight(new InputField("Date Mise en Vente : ",dateMiseVente));
		
		JButton button1= new JButton("Annuler");
		JButton button2= new JButton("Autre Matériel");
		JButton button3= new JButton("Valider");
		
		this.addSecondBottomSpace();
		this.addSecondBottom(button1);
		this.addSecondBottom(button2);
		this.addSecondBottom(button3);
		this.addSecondBottomSpace();
		
	}
}
