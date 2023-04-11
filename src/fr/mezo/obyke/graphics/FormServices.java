package fr.mezo.obyke.graphics;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class FormServices extends Formulaire {
	
	public FormServices(int lineLeft,int lineRight,String title) {
		
		super(lineLeft,lineRight,title);
		
		this.addTopSpace();
		
		JComboBox type=new JComboBox();
		type.setPreferredSize(new Dimension(212,28));
		
		this.addTop(new InputField("Type Service : ",type));
		
		this.addTopSpace();
		
		JTextField deno=new JTextField(20);
		JTextField nomDir=new JTextField(20);
		JTextField prenomDir=new JTextField(20);
		JTextField tel=new JTextField(20);
		
		JComboBox categ=new JComboBox();
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
		
		button1.addActionListener((e) -> {
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
		
		});
		
		button2.addActionListener((e) -> {
			this.SaveData(type.getSelectedItem(),deno.getText(),nomDir.getText(),prenomDir.getText(),tel.getText(),categ.getSelectedItem(),marque.getText(),fournisseur.getText(),dateAchat.getText(),dateDepot.getText());
		
		});
		
		this.addSecondBottomSpace();
		this.addSecondBottom(button1);
		this.addSecondBottom(button2);
		this.addSecondBottom(button3);
		this.addSecondBottomSpace();
		
	}
	
	public Object getData(JTextField aField) {
		
		Object fieldValue= aField.getText();
		return fieldValue;
	}
	
	public void SaveData(Object type,String deno,String nomDir,String prenomDir,String tel,Object categ,String marque,String fournisseur,String date,String dateDepo) {
		/*System.out.println(deno);
		System.out.println(nomDir);*/
	}
	
	public void SaveDataAndCreateAgain() {
		
	}
	
	public void Cancel() {
		
	}

}
