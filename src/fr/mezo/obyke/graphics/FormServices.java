package fr.mezo.obyke.graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import fr.mezo.controller.DateKeyListener;
import fr.mezo.controller.MailKeyLister;
import fr.mezo.controller.NamesKeyLister;
import fr.mezo.controller.TelephoneKeyListerner;
import fr.mezo.obyke.data.BD;
import fr.mezo.obyke.data.DateSimp;

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
		nomDir.addKeyListener(new NamesKeyLister(nomDir));
		
		JTextField prenomDir=new JTextField(20);
		prenomDir.addKeyListener(new NamesKeyLister(prenomDir));
		
		JTextField tel=new JTextField(20);
		tel.addKeyListener(new TelephoneKeyListerner(tel));
		
		JComboBox<String> categ=new JComboBox<String>(BD.GetMaterielCategorie());
		categ.setPreferredSize(new Dimension(212,28));
		
		JTextField marque=new JTextField(20);
		JTextField fournisseur=new JTextField(20);
		JTextField dateAchat=new JTextField(20);
		dateAchat.addKeyListener(new DateKeyListener(dateAchat));
		
		JTextField dateDepot=new JTextField(20);
		dateDepot.addKeyListener(new DateKeyListener(dateDepot));
		
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
		mail.addKeyListener(new MailKeyLister(mail));
		
		this.addFirstBottomSpace();
		this.addFirstBottom(new InputField("Mail : ",mail));
		this.addFirstBottomSpace();

		JButton button1= new JButton("Annuler");
		JButton button2= new JButton("Autre Service");
		JButton button3= new JButton("Valider");
		
		//Ajout des évènements sur les boutons
		button1.addActionListener((e) -> {
			this.Cancel(type,deno,nomDir,prenomDir,tel,mail,categ,marque,fournisseur,dateAchat,dateDepot);
		
		});
		
		button2.addActionListener((e) -> {
			JTextField[] tab = {deno,nomDir,prenomDir,tel,marque,fournisseur,dateAchat,dateDepot};
			if(Main.AllFieldFilled(tab)) {
				try {
					this.SaveData(type.getItemAt(type.getSelectedIndex()),deno.getText(),nomDir.getText(),prenomDir.getText(),tel.getText(),mail.getText(),categ.getItemAt(categ.getSelectedIndex()),marque.getText(),fournisseur.getText(),dateAchat.getText(),dateDepot.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				this.Cancel(type,deno,nomDir,prenomDir,tel,mail,categ,marque,fournisseur,dateAchat,dateDepot);
			}
		});
		
		button3.addActionListener((e) -> {
			JTextField[] tab = {deno,nomDir,prenomDir,tel,marque,fournisseur,dateAchat,dateDepot};
			if(Main.AllFieldFilled(tab)) {
				try {
					this.SaveData(type.getItemAt(type.getSelectedIndex()),deno.getText(),nomDir.getText(),prenomDir.getText(),tel.getText(),mail.getText(),categ.getItemAt(categ.getSelectedIndex()),marque.getText(),fournisseur.getText(),dateAchat.getText(),dateDepot.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
	public void SaveData(String type,String deno,String nomDir,String prenomDir,String tel,String mail,String categ,String marque,String fournisseur,String dateAchat,String dateDepot) throws SQLException {
		
		BD.ServiceData.Add(type,deno, nomDir, prenomDir, tel, mail,categ,marque, fournisseur,DateSimp.of(dateAchat),DateSimp.of(dateDepot));
		this.Messages();
	}
	
	//Fonction qui supprime la valeur des champs
	public void Cancel(JComboBox<String> type,JTextField deno,JTextField nomDir,JTextField prenomDir,JTextField tel,JTextField mail,JComboBox<String> categ,JTextField marque,JTextField fournisseur,JTextField dateAchat,JTextField dateDepot) {
		type.setSelectedItem(0);
		deno.setText("");
		nomDir.setText("");
		prenomDir.setText("");
		tel.setText("");
		mail.setText("");
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
