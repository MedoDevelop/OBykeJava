package fr.mezo.obyke.graphics;

import java.awt.Color;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Controller.MailKeyListener;
import Controller.NamesKeyListener;
import Controller.TelephoneKeyListener;
import fr.mezo.obyke.data.BD;

public class FormTechnicien extends Formulaire {
	
	public FormTechnicien(int lineLeft,int lineRight,String title) {
		
		super(lineLeft,lineRight,title);
		
		//Mise en place formulaire
		this.addTopSpace();
		this.addTopSpace();
		
		JTextField nom=new JTextField(20);
		//nom.addKeyListener(new NamesKeyListener(nom));
		
		JTextField prenom=new JTextField(20);
		prenom.addKeyListener(new NamesKeyListener(prenom));
		
		JTextField mail=new JTextField(20);
		mail.addKeyListener(new MailKeyListener(mail));
		
		JTextField telephone=new JTextField(20);
		telephone.addKeyListener(new TelephoneKeyListener(telephone));
		
		this.addLeft(new InputField("Nom : ",nom));
		this.addLeft(new InputField("Prénom : ",prenom));
		this.addLeft(new InputField("Mail : ",mail));
		this.addLeft(new InputField("Téléphone : ",telephone));
		
		JButton button1= new JButton("Annuler");
		JButton button2= new JButton("Autre Technicien");
		JButton button3= new JButton("Valider");
		
		button1.addActionListener((e) -> {
			this.Cancel(nom,prenom,mail,telephone);
		
		});
		
		button2.addActionListener((e) -> {
			JTextField[] tab = {nom,prenom,mail,telephone};
			if(Main.AllFieldFilled(tab)) {
				this.SaveData(nom.getText(),prenom.getText(),mail.getText(),telephone.getText());
				this.Cancel(nom,prenom,mail,telephone);
			}
		});
		
		button3.addActionListener((e) -> {
			JTextField[] tab = {nom,prenom,mail,telephone};
			if(Main.AllFieldFilled(tab)) {
				this.SaveData(nom.getText(),prenom.getText(),mail.getText(),telephone.getText());
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
	public void SaveData(String nom,String prenom,String mail,String telephone) {
		
		try {
			
			BD.TechnicienData.Add(nom,prenom,mail,telephone,"");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.Messages();
	}
	
	//Fonction qui supprime la valeur des champs
	public void Cancel(JTextField nom,JTextField prenom,JTextField mail,JTextField telephone) {
		
		nom.setText("");
		prenom.setText("");
		mail.setText("");
		telephone.setText("");
		
		
	}
	
	//Fonction qui affiche le succès ou l'échec de l'ajout
	public void Messages() {
		JOptionPane.showMessageDialog(null, "Le technicien a été ajouté avec succès");
	}
	
	//Fonction qui supprime le formulaire
	public void Clear() {
		this.principal.setBorder(BorderFactory.createLineBorder(Color.GRAY,0));
		this.principal.removeAll();
		this.principal.repaint();
	}
}
