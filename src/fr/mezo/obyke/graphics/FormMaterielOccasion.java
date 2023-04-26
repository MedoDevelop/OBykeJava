package fr.mezo.obyke.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.mezo.controller.AnneeKeyListener;
import fr.mezo.controller.DateKeyListener;
import fr.mezo.controller.PrixKeyListener;
import fr.mezo.obyke.data.BD;
import fr.mezo.obyke.data.DateSimp;

public class FormMaterielOccasion extends Formulaire {
	
	public FormMaterielOccasion(int lineLeft,int lineRight,String title) {
		
		super(lineLeft,lineRight,title);
		
		//Mise en place formulaire
		this.addTopSpace();
		this.addTopSpace();
		
		JTextField societe=new JTextField(20);
		
		JTextField annee=new JTextField(20);
		annee.addKeyListener(new AnneeKeyListener(annee));
		
		JTextField prixAchat=new JTextField(20);
		prixAchat.addKeyListener(new PrixKeyListener(prixAchat));
		
		JTextField dateAchat=new JTextField(20);
		dateAchat.addKeyListener(new DateKeyListener(dateAchat));
		
		JComboBox<String> categ=new JComboBox<String>(BD.GetMaterielCategorie());
		categ.setPreferredSize(new Dimension(212,28));
		
		/*JComboBox garantie=new JComboBox();
		garantie.setPreferredSize(new Dimension(212,28));*/
		
		JTextField coloris=new JTextField(20);
		JTextField prixVente=new JTextField(20);
		prixVente.addKeyListener(new PrixKeyListener(prixVente));
		
		JTextField dateMiseVente=new JTextField(20);
		dateMiseVente.addKeyListener(new DateKeyListener(dateMiseVente));
		
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

		//Ajout des évènements sur les boutons
				button1.addActionListener((e) -> {
					this.Cancel(categ,societe,annee,prixAchat,dateAchat,coloris,prixVente,dateMiseVente);
				
				});
				
				button2.addActionListener((e) -> {
					JTextField[] tab = {societe,annee,prixAchat,dateAchat,coloris,prixVente,dateMiseVente};
					if(Main.AllFieldFilled(tab)) {
						this.SaveData(categ.getItemAt(categ.getSelectedIndex()),societe.getText(),annee.getText(),prixAchat.getText(),dateAchat.getText(),coloris.getText(),prixVente.getText(),dateMiseVente.getText());
						this.Cancel(categ,societe,annee,prixAchat,dateAchat,coloris,prixVente,dateMiseVente);
					}
				});
				
				button3.addActionListener((e) -> {
					JTextField[] tab = {societe,annee,prixAchat,dateAchat,coloris,prixVente,dateMiseVente};
					if(Main.AllFieldFilled(tab)) {
						this.SaveData(categ.getItemAt(categ.getSelectedIndex()),societe.getText(),annee.getText(),prixAchat.getText(),dateAchat.getText(),coloris.getText(),prixVente.getText(),dateMiseVente.getText());
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
			public void SaveData(String categ,String societe,String annee,String prixAchat,String dateAchat,String coloris,String prixVente,String dateMiseVente) {
				
				//Add(String coloris, double prixVente, DateSimp dateMisVente, String categ, DateSimp dateVendus,DateSimp dateAchat,double prixAchat)
				double prixV = Double.parseDouble(prixVente);
				double prixA = Double.parseDouble(prixAchat);
				try {
					BD.MaterielData.MaterielOccasionData.Add(coloris, prixV, DateSimp.of(dateMiseVente), categ, DateSimp.now(), DateSimp.of(dateAchat), prixA,societe,annee);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.Messages();
			}
			
			//Fonction qui supprime la valeur des champs
			public void Cancel(JComboBox categ,JTextField societe,JTextField annee,JTextField prixAchat,JTextField dateAchat,JTextField coloris,JTextField prixVente,JTextField dateMiseVente) {
				categ.setSelectedItem(0);
				societe.setText("");
				annee.setText("");
				prixAchat.setText("");
				dateAchat.setText("");
				coloris.setText("");
				prixVente.setText("");
				dateMiseVente.setText("");
				//garantie.setSelectedItem(0);
				
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