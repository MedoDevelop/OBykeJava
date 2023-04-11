package fr.mezo.obyke.graphics;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenuConsultationGarantie extends MenuConsultationDroit {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MenuConsultationGarantie(int lineLeft,int lineRight) {
		super(lineLeft,lineRight);
		JButton button1= new JButton("Modifier");
		JButton button2= new JButton("Supprimer");
		
		button1.addActionListener((e) -> EditConfirmation());
		button2.addActionListener((e) -> DeleteConfirmation());
		
		this.addTopSpace();
		
		JTextField lib=new JTextField(20);
		JTextField prix=new JTextField(20);
		JTextField duree=new JTextField(20);
	
		this.addLeft(new InputField("Libellé de la garantie : ",lib));
		this.addLeft(new InputField("Prix de la garantie : ",prix));
		this.addLeft(new InputField("Duréee de la garantie : ",duree));
		
		this.addFirstBottomSpace();
		this.addFirstBottomSpace();
		this.addFirstBottomSpace();
		
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
