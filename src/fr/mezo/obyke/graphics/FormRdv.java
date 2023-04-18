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

public class FormRdv extends Formulaire {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FormRdv(int lineLeft,int lineRight,String title) {
		
		super(lineLeft,lineRight,title);
		
		//Mise en place formulaire
		JTextField deno=new JTextField(20);
		JTextField nomDir=new JTextField(20);
		JTextField prenomDir=new JTextField(20);
		JTextField tel=new JTextField(20);
		JTextField mail=new JTextField(20);
		JTextField date=new JTextField(20);
		JTextField heure=new JTextField(20);
		heure.setText("00:00");
		
	
		this.addTopSpace();
		this.addTopSpace();
		
		this.addLeft(new InputField("Dénomination : ",deno));
		this.addLeft(new InputField("Nom du Directeur : ",nomDir));
		this.addLeft(new InputField("Prénom du Directeur : ",prenomDir));
		this.addLeft(new InputField("Téléphone : ",tel));
		this.addLeft(new InputField("Mail : ",mail));
		
		this.addRight(new InputField("Date du RDV : ",date));
		this.addRight(new InputField("Heure du RDV : ",heure));
		
		this.addFirstBottomSpace();
		this.addFirstBottomSpace();
		this.addFirstBottomSpace();

		JButton button1= new JButton("Annuler");
		JButton button2= new JButton("Autre Rendez-vous");
		JButton button3= new JButton("Valider");
		
		//Ajout des évènements sur les boutons
		button1.addActionListener((e) -> {
			this.Cancel(deno,nomDir,prenomDir,tel,mail,date,heure);
		
		});
		
		button2.addActionListener((e) -> {
			this.SaveData(deno.getText(),nomDir.getText(),prenomDir.getText(),tel.getText(),mail.getText(),date.getText(),heure.getText());
			this.Cancel(deno,nomDir,prenomDir,tel,mail,date,heure);
		});
		
		button3.addActionListener((e) -> {
			this.SaveData(deno.getText(),nomDir.getText(),prenomDir.getText(),tel.getText(),mail.getText(),date.getText(),heure.getText());
			this.Clear();
		});
		
		this.addSecondBottomSpace();
		this.addSecondBottom(button1);
		this.addSecondBottom(button2);
		this.addSecondBottom(button3);
		this.addSecondBottomSpace();
		
	}
	
	//Fonction de sauvegardes des données
	public void SaveData(String deno,String nomDir,String prenomDir,String tel,String mail,String date,String heure) {
		this.Messages();
		
	}
	
	//Fonction qui supprime la valeur des champs
	public void Cancel(JTextField deno,JTextField nomDir,JTextField prenomDir,JTextField tel,JTextField mail,JTextField date,JTextField heure) {
		deno.setText("");
		nomDir.setText("");
		prenomDir.setText("");
		tel.setText("");
		mail.setText("");
		date.setText("");
		heure.setText("00:00");
	}
	
	//Fonction qui affiche le succès ou l'échec de l'ajout
	public void Messages() {
		JOptionPane.showMessageDialog(null, "Le rendez-vous a été ajouté avec succès");
	}
	
	//Fonction qui supprime le formulaire
	public void Clear() {
		this.principal.setBorder(BorderFactory.createLineBorder(Color.GRAY,0));
		this.principal.removeAll();
		this.principal.repaint();
	}

}
