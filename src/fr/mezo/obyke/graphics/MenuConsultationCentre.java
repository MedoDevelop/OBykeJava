package fr.mezo.obyke.graphics;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenuConsultationCentre extends MenuConsultationDroit {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MenuConsultationCentre(int lineLeft,int lineRight) {
		super(lineLeft,lineRight);
		JButton button1= new JButton("Modifier");
		JButton button2= new JButton("Supprimer");
		
		button1.addActionListener((e) -> EditConfirmation());
		button2.addActionListener((e) -> DeleteConfirmation());
		
		this.addTopSpace();
		
		JTextField deno=new JTextField(15);
		JTextField nomDir=new JTextField(15);
		JTextField prenomDir=new JTextField(15);
		JTextField tel=new JTextField(15);
		JTextField mail=new JTextField(15);
		
		JComboBox type=new JComboBox();
		type.setPreferredSize(new Dimension(162,28));
		
		this.addLeft(new InputField("Dénomination : ",deno));
		this.addLeft(new InputField("Nom du Directeur : ",nomDir));
		this.addLeft(new InputField("Prénom du Directeur : ",prenomDir));
		
		this.addRight(new InputField("Téléphone : ",tel));
		this.addRight(new InputField("Mail : ",mail));
		this.addRight(new InputField("Type : ",type));
		
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
