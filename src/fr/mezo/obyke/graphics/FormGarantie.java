package fr.mezo.obyke.graphics;

import java.awt.Color;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import fr.mezo.controller.DureeMoisKeyListener;
import fr.mezo.controller.PrixKeyListener;
import fr.mezo.obyke.data.BD;

public class FormGarantie extends Formulaire {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FormGarantie(int lineLeft,int lineRight,String title) {
		
		super(lineLeft,lineRight,title);
		
		//Mise en place formulaire
		JTextField lib=new JTextField(20);
		//lib.addKeyListener(new NamesKeyListener(lib));
		
		JTextField prix=new JTextField(20);
		prix.addKeyListener(new PrixKeyListener(prix));
		
		JTextField duree=new JTextField(20);
		duree.addKeyListener(new DureeMoisKeyListener(duree));
	
		this.addTopSpace();
		this.addTopSpace();
		
		this.addLeft(new InputField("Libellé de la garantie : ",lib));
		this.addLeft(new InputField("Prix de la garantie : ",prix));
		this.addLeft(new InputField("Duréee de la garantie (en mois) : ",duree));
		
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
			JTextField[] tab = {lib,prix,duree};
			if(Main.AllFieldFilled(tab)) {
				this.SaveData(lib.getText(),prix.getText(),duree.getText());
				this.Cancel(lib,prix,duree);
			}
		});
		
		button3.addActionListener((e) -> {
			JTextField[] tab = {lib,prix,duree};
			if(Main.AllFieldFilled(tab)) {
				this.SaveData(lib.getText(),prix.getText(),duree.getText());
				this.Clear();
			}
		});
		
		this.addSecondBottomSpace();
		this.addSecondBottom(button1);
		this.addSecondBottom(button2);
		this.addSecondBottom(button3);
		this.addSecondBottomSpace();
		
	}
	
	//Fonction de sauvegardes des données
	public void SaveData(String lib,String prix,String duree) {
		double prixG = Double.parseDouble(prix);
		int durreAn = Integer.parseInt(duree);
		try {
			BD.GarantieData.Add(lib, prixG, durreAn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
