package fr.mezo.obyke.graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormServices extends Formulaire {
	
	public FormServices(int lineLeft,int lineRight,String title) {
		
		super(lineLeft,lineRight,title);
		
		this.addTopSpace(new JPanel());
		JComboBox cb1=new JComboBox();
		cb1.setPreferredSize(new Dimension(212,28));
		this.addTop(new InputField("Type Service : ",cb1));
		this.addTopSpace(new JPanel());
		
		this.addLeft(new InputField("Dénomination : ",new JTextField(20)));
		this.addLeft(new InputField("Nom du Directeur : ",new JTextField(20)));
		this.addLeft(new InputField("Prénom du Directeur : ",new JTextField(20)));
		this.addLeft(new InputField("Téléphone : ",new JTextField(20)));
		
		JComboBox cb=new JComboBox();
		cb.setPreferredSize(new Dimension(212,28));
		this.addRight(new InputField("Catégorie : ",cb));
		this.addRight(new InputField("Marque : ",new JTextField(20)));
		this.addRight(new InputField("Fournisseur : ",new JTextField(20)));
		this.addRight(new InputField("Date Achat : ",new JTextField(20)));
		this.addRight(new InputField("Date Dépôt : ",new JTextField(20)));
		
		JPanel space1=new JPanel();
		JPanel space2=new JPanel();
		JPanel space3=new JPanel();
		JPanel space4=new JPanel();
		
		this.addFirstBottomSpace(space1);
		this.addFirstBottom(new InputField("Mail : ",new JTextField(20)));
		this.addFirstBottomSpace(space2);

		JButton button1= new JButton("Annuler");
		JButton button2= new JButton("Autre Service");
		JButton button3= new JButton("Valider");
		
		this.addSecondBottomSpace(space3);
		this.addSecondBottom(button1);
		this.addSecondBottom(button2);
		this.addSecondBottom(button3);
		this.addSecondBottomSpace(space4);
		
	}

}
