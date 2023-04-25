package fr.mezo.obyke.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import fr.mezo.obyke.data.BD;
import fr.mezo.obyke.data.DateSimp;
import fr.mezo.obyke.workclass.Technicien;

public class FormRdv extends Formulaire {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FormRdv(int lineLeft,int lineRight,String title) throws SQLException {
		
		super(lineLeft,lineRight,title);
		
		//Mise en place formulaire
		JTextField deno=new JTextField(20);
		JTextField nomDir=new JTextField(20);
		JTextField prenomDir=new JTextField(20);
		JTextField tel=new JTextField(20);
		JTextField mail=new JTextField(20);
		JTextField date=new JTextField(20);
		
		JComboBox<String>heure=new JComboBox<String>(BD.GetHeuresRdv());
		
		JComboBox<String> motif=new JComboBox<String>(BD.GetTypeService());
		
		
		ArrayList<Technicien>lesTech=new ArrayList<Technicien>(BD.TechnicienData.GetAll());
		Technicien[] lesTechArray= new Technicien[lesTech.size()];
		int i=0;
		for(Technicien unTech : lesTech) {
			lesTechArray[i]=unTech;
			i++;
		}
		JComboBox<Technicien> tech=new JComboBox<Technicien>(lesTechArray);
		
		heure.setPreferredSize(new Dimension(212,28));
		motif.setPreferredSize(new Dimension(212,28));
		tech.setPreferredSize(new Dimension(212,28));
	
		this.addTopSpace();
		this.addTopSpace();
		
		this.addLeft(new InputField("Dénomination : ",deno));
		this.addLeft(new InputField("Nom du Directeur : ",nomDir));
		this.addLeft(new InputField("Prénom du Directeur : ",prenomDir));
		this.addLeft(new InputField("Téléphone : ",tel));
		this.addLeft(new InputField("Mail : ",mail));
		
		this.addRight(new InputField("Date du RDV : ",date));
		this.addRight(new InputField("Heure du RDV : ",heure));
		this.addRight(new InputField("Motif : ",motif));
		this.addRight(new InputField("Technicien : ",tech));
		
		this.addFirstBottomSpace();
		this.addFirstBottomSpace();
		this.addFirstBottomSpace();

		JButton button1= new JButton("Annuler");
		JButton button2= new JButton("Autre Rendez-vous");
		JButton button3= new JButton("Valider");
		
		//Ajout des évènements sur les boutons
		button1.addActionListener((e) -> {
			this.Cancel(deno,nomDir,prenomDir,tel,mail,date,heure,motif,tech);
		
		});
		
		button2.addActionListener((e) -> {
			JTextField[] tab = {deno,nomDir,prenomDir,tel,mail,date};
			try {
				if(Main.AllFieldFilled(tab)) {
					this.SaveData(deno.getText(),nomDir.getText(),prenomDir.getText(),mail.getText(),tel.getText(),date.getText(),heure.getItemAt(motif.getSelectedIndex()),motif.getItemAt(motif.getSelectedIndex()),tech.getItemAt(tech.getSelectedIndex()).getId());
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.Cancel(deno,nomDir,prenomDir,tel,mail,date,heure,motif,tech);
		});
		
		button3.addActionListener((e) -> {
			JTextField[] tab = {deno,nomDir,prenomDir,tel,mail,date};
			try {
				if(Main.AllFieldFilled(tab)) {
					this.SaveData(deno.getText(),nomDir.getText(),prenomDir.getText(),mail.getText(),tel.getText(),date.getText(),heure.getItemAt(motif.getSelectedIndex()),motif.getItemAt(motif.getSelectedIndex()),tech.getItemAt(tech.getSelectedIndex()).getId());
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.Clear();
		});
		
		this.addSecondBottomSpace();
		this.addSecondBottom(button1);
		this.addSecondBottom(button2);
		this.addSecondBottom(button3);
		this.addSecondBottomSpace();
		
	}
	
	//Fonction de sauvegardes des données
	public void SaveData(String deno,String nomDir,String prenomDir,String tel,String mail,String date,String heure,String motif,int idTech) throws SQLException {
		this.Messages();
		BD.RDVData.Add(deno, nomDir, prenomDir, mail, tel, DateSimp.of(date),heure, motif, idTech);
	}
	
	//Fonction qui supprime la valeur des champs
	public void Cancel(JTextField deno,JTextField nomDir,JTextField prenomDir,JTextField tel,JTextField mail,JTextField date,JComboBox<String> heure,JComboBox<String> motif,JComboBox<Technicien> tech) {
		deno.setText("");
		nomDir.setText("");
		prenomDir.setText("");
		tel.setText("");
		mail.setText("");
		date.setText("");
		heure.setSelectedItem(0);
		motif.setSelectedItem(0);
		tech.setSelectedItem(0);
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
