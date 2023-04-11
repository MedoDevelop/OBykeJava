package fr.mezo.obyke.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormGarantie extends Formulaire {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FormGarantie(int lineLeft,int lineRight,String title) {
		
		super(lineLeft,lineRight,title);
		
		//Mise en place formulaire
		JTextField lib=new JTextField(20);
		JTextField prix=new JTextField(20);
		JTextField duree=new JTextField(20);
	
		this.addTopSpace();
		this.addTopSpace();
		
		this.addLeft(new InputField("Libellé de la garantie : ",lib));
		this.addLeft(new InputField("Prix de la garantie : ",prix));
		this.addLeft(new InputField("Duréee de la garantie : ",duree));
		
		this.addFirstBottomSpace();
		this.addFirstBottomSpace();
		this.addFirstBottomSpace();

		JButton button1= new JButton("Annuler");
		JButton button2= new JButton("Autre Garantie");
		JButton button3= new JButton("Valider");
		
		//Ajout des évènements sur les boutons
		button1.addActionListener((e) -> {
			this.Cancel(lib,prix,duree);
		
		});
		
		button2.addActionListener((e) -> {
			this.SaveData(lib.getText(),prix.getText(),duree.getText());
			this.Cancel(lib,prix,duree);
		});
		
		button3.addActionListener((e) -> {
			this.SaveData(lib.getText(),prix.getText(),duree.getText());
			this.Clear();
		});
		
		this.addSecondBottomSpace();
		this.addSecondBottom(button1);
		this.addSecondBottom(button2);
		this.addSecondBottom(button3);
		this.addSecondBottomSpace();
		
	}
	
	//Fonction de sauvegardes des données
	public void SaveData(String lib,String prix,String duree) {
		this.Messages();
		
	}
	
	//Fonction qui supprime la valeur des champs
	public void Cancel(JTextField lib,JTextField prix,JTextField duree) {
		lib.setText("");
		prix.setText("");
		duree.setText("");
	}
	
	//Fonction qui affiche le succès ou l'échec de l'ajout
	public void Messages() {
		JOptionPane.showMessageDialog(null, "La garantie a été ajoutée avec succès");
	}
	
	//Fonction qui supprime le formulaire
	public void Clear() {
		this.principal.setBorder(BorderFactory.createLineBorder(Color.GRAY,0));
		this.principal.removeAll();
		this.principal.repaint();
	}

}
