package fr.mezo.obyke.graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormMaterielOccasion extends Formulaire {
	
	public FormMaterielOccasion(int lineLeft,int lineRight,String title) {
		
		super(lineLeft,lineRight,title);
		JPanel space1=new JPanel();
		JPanel space2=new JPanel();
		this.addTopSpace(space1);
		this.addTopSpace(space2);
		
		this.addLeft(new InputField("Société : ",new JTextField(20)));
		this.addLeft(new InputField("Année : ",new JTextField(20)));
		this.addLeft(new InputField("Prix Achat : ",new JTextField(20)));
		this.addLeft(new InputField("Date Achat : ",new JTextField(20)));
		
		JComboBox cb=new JComboBox();
		cb.setPreferredSize(new Dimension(212,28));
		this.addRight(new InputField("Catégorie : ",cb));
		this.addRight(new InputField("Coloris : ",new JTextField(20)));
		this.addRight(new InputField("Prix Vente : ",new JTextField(20)));
		this.addRight(new InputField("Date Mise en Vente : ",new JTextField(20)));
		
		JButton button1= new JButton("Annuler");
		JButton button2= new JButton("Autre Matériel");
		JButton button3= new JButton("Valider");
		
		JPanel leftSpace=new JPanel(new BorderLayout());
		JPanel rightSpace=new JPanel(new BorderLayout());
		
		leftSpace.setOpaque(false);
		rightSpace.setOpaque(false);
		
		this.addSecondBottomSpace(leftSpace);
		this.addSecondBottom(button1);
		this.addSecondBottom(button2);
		this.addSecondBottom(button3);
		this.addSecondBottomSpace(rightSpace);
		
	}
}
