package fr.mezo.obyke.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import fr.mezo.controller.MailKeyLister;
import fr.mezo.controller.NamesKeyLister;
import fr.mezo.controller.TelephoneKeyListerner;
import fr.mezo.obyke.data.BD;

public class FormCentre extends Formulaire {
	
	public FormCentre(int lineLeft,int lineRight,String title) {
		
		super(lineLeft,lineRight,title);
		
		//Mise en place formulaire
		JTextField deno=new JTextField(20);
		
		JTextField nomDir=new JTextField(20);
		nomDir.addKeyListener(new NamesKeyLister(nomDir));
		
		JTextField prenomDir=new JTextField(20);
		prenomDir.addKeyListener(new NamesKeyLister(prenomDir));
		
		JTextField tel=new JTextField(20);
		tel.addKeyListener(new TelephoneKeyListerner(tel));
		
		JTextField mail=new JTextField(20);
		mail.addKeyListener(new MailKeyLister(mail));
		
		JComboBox<String> type=new JComboBox<String>(BD.GetTypesCentre());
		type.setPreferredSize(new Dimension(212,28));

		this.addTopSpace();
		this.addTopSpace();
		
		this.addLeft(new InputField("Dénomination : ",deno));
		this.addLeft(new InputField("Nom du Directeur : ",nomDir));
		this.addLeft(new InputField("Prénom du Directeur : ",prenomDir));
		
		this.addRight(new InputField("Téléphone : ",tel));
		this.addRight(new InputField("Mail : ",mail));
		this.addRight(new InputField("Type : ",type));
		
		this.addFirstBottomSpace();
		this.addFirstBottomSpace();
		this.addFirstBottomSpace();

		JButton button1= new JButton("Annuler");
		JButton button2= new JButton("Autre Centre");
		JButton button3= new JButton("Valider");
		
		//Ajout des évènements sur les boutons
		button1.addActionListener((e) -> {
			this.Cancel(type,deno,nomDir,prenomDir,tel,mail);
		
		});
		
		button2.addActionListener((e) -> {
			JTextField[] tab = {deno,nomDir,prenomDir,tel,mail};
			if(Main.AllFieldFilled(tab)) {//On vérifie que les champs ne sont pas vide
				this.SaveData(type.getItemAt(type.getSelectedIndex()),deno.getText(),nomDir.getText(),prenomDir.getText(),tel.getText(),mail.getText());				
			}
			this.Cancel(type,deno,nomDir,prenomDir,tel,mail);
		});
		
		button3.addActionListener((e) -> {
			JTextField[] tab = {deno,nomDir,prenomDir,tel,mail};
			if(Main.AllFieldFilled(tab)) {
				this.SaveData(type.getItemAt(type.getSelectedIndex()),deno.getText(),nomDir.getText(),prenomDir.getText(),tel.getText(),mail.getText());
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
	public void SaveData(String type,String deno,String nomDir,String prenomDir,String tel,String mail) {
		
		try {
			BD.CentreData.Add(deno,nomDir,prenomDir,tel, mail,type,"");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.Messages();
		
		
	}
	
	//Fonction qui supprime la valeur des champs
	public void Cancel(JComboBox type,JTextField deno,JTextField nomDir,JTextField prenomDir,JTextField tel,JTextField mail) {
		type.setSelectedIndex(0);
		JTextField[] jtf = {deno,nomDir,prenomDir,tel,mail};
		for(JTextField j : jtf) {
			j.setText("");
		}
		/*deno.setText("");
		nomDir.setText("");
		prenomDir.setText("");
		tel.setText("");
		mail.setText("");*/
	}
	
	//Fonction qui affiche le succès ou l'échec de l'ajout
	public void Messages() {
		JOptionPane.showMessageDialog(null, "Le centre a été ajouté avec succès");
	}
	
	//Fonction qui supprime le formulaire
	public void Clear() {
		this.principal.setBorder(BorderFactory.createLineBorder(Color.GRAY,0));
		this.principal.removeAll();
		this.principal.repaint();
	}

}
