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

public class FormCentre extends Formulaire {
	
	public FormCentre(int lineLeft,int lineRight,String title) {
		
		super(lineLeft,lineRight,title);
		
		//Mise en place formulaire
		JTextField deno=new JTextField(20);
		JTextField nomDir=new JTextField(20);
		JTextField prenomDir=new JTextField(20);
		JTextField tel=new JTextField(20);
		JTextField mail=new JTextField(20);
		
		JComboBox type=new JComboBox();
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
			this.SaveData(type.getSelectedItem(),deno.getText(),nomDir.getText(),prenomDir.getText(),tel.getText(),mail.getText());
			this.Cancel(type,deno,nomDir,prenomDir,tel,mail);
		});
		
		button3.addActionListener((e) -> {
			this.SaveData(type.getSelectedItem(),deno.getText(),nomDir.getText(),prenomDir.getText(),tel.getText(),mail.getText());
			this.Clear();
		});
		
		this.addSecondBottomSpace();
		this.addSecondBottom(button1);
		this.addSecondBottom(button2);
		this.addSecondBottom(button3);
		this.addSecondBottomSpace();
		
		
	}
	
	//Fonction de sauvegardes des données
	public void SaveData(Object type,String deno,String nomDir,String prenomDir,String tel,String mail) {
		this.Messages();
		
	}
	
	//Fonction qui supprime la valeur des champs
	public void Cancel(JComboBox type,JTextField deno,JTextField nomDir,JTextField prenomDir,JTextField tel,JTextField mail) {
		type.setSelectedItem(0);
		deno.setText("");
		nomDir.setText("");
		prenomDir.setText("");
		tel.setText("");
		mail.setText("");
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
