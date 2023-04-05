package fr.mezo.obyke.graphics;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenuConsultationMaterielOccasion extends MenuConsultationDroit {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MenuConsultationMaterielOccasion(int lineLeft,int lineRight) {
		super(lineLeft,lineRight);
		JButton button1= new JButton("Modifier");
		JButton button2= new JButton("Supprimer");
		
		button1.addActionListener((e) -> EditConfirmation());
		button2.addActionListener((e) -> DeleteConfirmation());
		
		
		JPanel space1=new JPanel();
		JPanel space2=new JPanel();
		JPanel space3=new JPanel();
		space1.setOpaque(false);
		space2.setOpaque(false);
		space3.setOpaque(false);
		
		this.addLeft(new InputField("Société : ",new JTextField(15)));
		this.addLeft(new InputField("Année : ",new JTextField(15)));
		this.addLeft(new InputField("Prix Achat : ",new JTextField(15)));
		this.addLeft(new InputField("Date Achat : ",new JTextField(15)));
		
		JComboBox cb=new JComboBox();
		cb.setPreferredSize(new Dimension(162,28));
		this.addRight(new InputField("Catégorie : ",cb));
		this.addRight(new InputField("Coloris : ",new JTextField(15)));
		this.addRight(new InputField("Prix Vente : ",new JTextField(15)));
		this.addRight(new InputField("Date Mise en Vente : ",new JTextField(15)));
	
		this.addBottom(space1);
		this.addBottom(button1);
		this.addBottom(space2);
		this.addBottom(button2);
		this.addBottom(space3);
	
		
		
	}

}
