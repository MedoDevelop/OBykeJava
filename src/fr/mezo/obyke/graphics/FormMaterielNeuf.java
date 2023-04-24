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

public class FormMaterielNeuf extends Formulaire {
	
	public FormMaterielNeuf(int lineLeft,int lineRight,String title) {
		
		super(lineLeft,lineRight,title);
		
		//Mise en place formulaire
		this.addTopSpace();
		this.addTopSpace();
		
		JTextField societe=new JTextField(20);
		JTextField annee=new JTextField(20);
		JTextField prixAchat=new JTextField(20);
		JTextField dateAchat=new JTextField(20);
		
		JComboBox categ=new JComboBox();
		categ.setPreferredSize(new Dimension(212,28));
		
		JComboBox garantie=new JComboBox();
		garantie.setPreferredSize(new Dimension(212,28));
		
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
		
		/*this.addFirstBottomSpace();
		this.addFirstBottom(new InputField("Garantie : ",garantie));
		this.addFirstBottomSpace();*/
		
		JButton button1= new JButton("Annuler");
		JButton button2= new JButton("Autre Matériel");
		JButton button3= new JButton("Valider");
		
		button1.addActionListener((e) -> {
			this.Cancel(categ,societe,annee,prixAchat,dateAchat,coloris,prixVente,dateMiseVente,garantie);
		
		});
		
		button2.addActionListener((e) -> {
			this.SaveData(categ.getSelectedItem(),societe.getText(),annee.getText(),prixAchat.getText(),dateAchat.getText(),coloris.getText(),prixVente.getText(),dateMiseVente.getText(),garantie.getSelectedItem());
			this.Cancel(categ,societe,annee,prixAchat,dateAchat,coloris,prixVente,dateMiseVente,garantie);
		});
		
		button3.addActionListener((e) -> {
			this.SaveData(categ.getSelectedItem(),societe.getText(),annee.getText(),prixAchat.getText(),dateAchat.getText(),coloris.getText(),prixVente.getText(),dateMiseVente.getText(),garantie.getSelectedItem());
			this.Clear();
		});
		
		this.addSecondBottomSpace();
		this.addSecondBottom(button1);
		this.addSecondBottom(button2);
		this.addSecondBottom(button3);
		this.addSecondBottomSpace();
	}
	
	//Fonction de sauvegardes des données
	public void SaveData(Object categ,String societe,String annee,String prixAchat,String dateAchat,String coloris,String prixVente,String dateMiseVente,Object garantie) {
		this.Messages();
		
	}
	
	//Fonction qui supprime la valeur des champs
	public void Cancel(JComboBox categ,JTextField societe,JTextField annee,JTextField prixAchat,JTextField dateAchat,JTextField coloris,JTextField prixVente,JTextField dateMiseVente,JComboBox garantie) {
		categ.setSelectedItem(0);
		societe.setText("");
		annee.setText("");
		prixAchat.setText("");
		dateAchat.setText("");
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
