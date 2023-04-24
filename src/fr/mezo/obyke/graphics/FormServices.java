package fr.mezo.obyke.graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import fr.mezo.obyke.data.BD;

public class FormServices extends Formulaire {
	
	public FormServices(int lineLeft,int lineRight,String title) {
		
		super(lineLeft,lineRight,title);
		
		//Mise en place formulaire
		this.addTopSpace();
		
		JComboBox<String> type=new JComboBox<String>(BD.GetTypeService());
		type.setPreferredSize(new Dimension(212,28));
		
		this.addTop(new InputField("Type Service : ",type));
		
		this.addTopSpace();
		
		JTextField deno=new JTextField(20);
		JTextField nomDir=new JTextField(20);
		JTextField prenomDir=new JTextField(20);
		JTextField tel=new JTextField(20);
		
		JComboBox<String> categ=new JComboBox<String>(BD.GetMaterielCategorie());
		categ.setPreferredSize(new Dimension(212,28));
		
		JTextField marque=new JTextField(20);
		JTextField fournisseur=new JTextField(20);
		JTextField dateAchat=new JTextField(20);
		JTextField dateDepot=new JTextField(20);
		
		this.addLeft(new InputField("Dénomination : ",deno));
		this.addLeft(new InputField("Nom du Directeur : ",nomDir));
		this.addLeft(new InputField("Prénom du Directeur : ",prenomDir));
		this.addLeft(new InputField("Téléphone : ",tel));
		
		this.addRight(new InputField("Catégorie : ",categ));
		this.addRight(new InputField("Marque : ",marque));
		this.addRight(new InputField("Fournisseur : ",fournisseur));
		this.addRight(new InputField("Date Achat : ",dateAchat));
		this.addRight(new InputField("Date Dépôt : ",dateDepot));
		
		JTextField mail=new JTextField(20);
		this.addFirstBottomSpace();
		this.addFirstBottom(new InputField("Mail : ",mail));
		this.addFirstBottomSpace();

		JButton button1= new JButton("Annuler");
		JButton button2= new JButton("Autre Service");
		JButton button3= new JButton("Valider");
		
		//Ajout des évènements sur les boutons
		button1.addActionListener((e) -> {
			this.Cancel(type,deno,nomDir,prenomDir,tel,categ,marque,fournisseur,dateAchat,dateDepot);
		
		});
		
		button2.addActionListener((e) -> {
			this.SaveData(type.getSelectedItem(),deno.getText(),nomDir.getText(),prenomDir.getText(),tel.getText(),categ.getSelectedItem(),marque.getText(),fournisseur.getText(),dateAchat.getText(),dateDepot.getText());
			this.Cancel(type,deno,nomDir,prenomDir,tel,categ,marque,fournisseur,dateAchat,dateDepot);
		});
		
		button3.addActionListener((e) -> {
			this.SaveData(type.getSelectedItem(),deno.getText(),nomDir.getText(),prenomDir.getText(),tel.getText(),categ.getSelectedItem(),marque.getText(),fournisseur.getText(),dateAchat.getText(),dateDepot.getText());
			this.Clear();
		});
		
		this.addSecondBottomSpace();
		this.addSecondBottom(button1);
		this.addSecondBottom(button2);
		this.addSecondBottom(button3);
		this.addSecondBottomSpace();
		
	}
	
	//Fonction de sauvegardes des données
	public void SaveData(Object type,String deno,String nomDir,String prenomDir,String tel,Object categ,String marque,String fournisseur,String dateAchat,String dateDepot) {
		
		
		
		this.Messages();
	}
	
	//Fonction qui supprime la valeur des champs
	public void Cancel(JComboBox type,JTextField deno,JTextField nomDir,JTextField prenomDir,JTextField tel,JComboBox categ,JTextField marque,JTextField fournisseur,JTextField dateAchat,JTextField dateDepot) {
		type.setSelectedItem(0);
		deno.setText("");
		nomDir.setText("");
		prenomDir.setText("");
		tel.setText("");
		categ.setSelectedItem(0);
		marque.setText("");
		fournisseur.setText("");
		dateAchat.setText("");
		dateDepot.setText("");
	}
	
	//Fonction qui affiche le succès ou l'échec de l'ajout
	public void Messages() {
		JOptionPane.showMessageDialog(null, "Le service a été ajouté avec succès");
	}
	
	//Fonction qui supprime le formulaire
	public void Clear() {
		this.principal.setBorder(BorderFactory.createLineBorder(Color.GRAY,0));
		this.principal.removeAll();
		this.principal.repaint();
	}
	

}
