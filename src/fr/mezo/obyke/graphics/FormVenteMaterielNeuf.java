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

public class FormVenteMaterielNeuf extends Formulaire {
	
	public FormVenteMaterielNeuf(int lineLeft,int lineRight,String title) {
		
		super(lineLeft,lineRight,title);
		
		//Mise en place formulaire
		this.addTopSpace();
		this.addTopSpace();
		
		JTextField coloris=new JTextField(20);
		JTextField dateMiseVente=new JTextField(20);
		JTextField prixVente=new JTextField(20);
		
		JComboBox categ=new JComboBox();
		categ.setPreferredSize(new Dimension(212,28));
		
		JComboBox garantie=new JComboBox();
		garantie.setPreferredSize(new Dimension(212,28));
		
		this.addLeft(new InputField("Catégorie : ",categ));
		this.addLeft(new InputField("Coloris : ",coloris));
		this.addLeft(new InputField("Prix Vente : ",prixVente));
		this.addLeft(new InputField("Date Mise en Vente : ",dateMiseVente));
		this.addLeft(new InputField("Garantie : ",garantie));
		
		this.addFirstBottomSpace();
		this.addFirstBottomSpace();
		this.addFirstBottomSpace();
		
		JButton button1= new JButton("Annuler");
		JButton button2= new JButton("Autre Matériel");
		JButton button3= new JButton("Valider");
		
		button1.addActionListener((e) -> {
			this.Cancel(categ,coloris,prixVente,dateMiseVente,garantie);
		
		});
		
		button2.addActionListener((e) -> {
			this.SaveData(categ.getSelectedItem(),coloris.getText(),prixVente.getText(),dateMiseVente.getText(),garantie.getSelectedItem());
			this.Cancel(categ,coloris,prixVente,dateMiseVente,garantie);
		});
		
		button3.addActionListener((e) -> {
			this.SaveData(categ.getSelectedItem(),coloris.getText(),prixVente.getText(),dateMiseVente.getText(),garantie.getSelectedItem());
			this.Clear();
		});
		
		this.addSecondBottomSpace();
		this.addSecondBottom(button1);
		this.addSecondBottom(button2);
		this.addSecondBottom(button3);
		this.addSecondBottomSpace();
	}
	
	//Fonction de sauvegardes des données
	public void SaveData(Object categ,String coloris,String prixVente,String dateMiseVente,Object garantie) {
		this.Messages();
		
	}
	
	//Fonction qui supprime la valeur des champs
	public void Cancel(JComboBox categ,JTextField coloris,JTextField prixVente,JTextField dateMiseVente,JComboBox garantie) {
		categ.setSelectedItem(0);
		coloris.setText("");
		prixVente.setText("");
		dateMiseVente.setText("");
		garantie.setSelectedItem(0);
		
	}
	
	//Fonction qui affiche le succès ou l'échec de l'ajout
	public void Messages() {
		JOptionPane.showMessageDialog(null, "Le matériel a été ajouté avec succès");
	}
	
	//Fonction qui supprime le formulaire
	public void Clear() {
		this.principal.setBorder(BorderFactory.createLineBorder(Color.GRAY,0));
		this.principal.removeAll();
		this.principal.repaint();
	}
}
