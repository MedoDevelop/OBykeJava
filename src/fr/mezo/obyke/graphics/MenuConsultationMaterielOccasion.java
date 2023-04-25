package fr.mezo.obyke.graphics;

import java.awt.Dimension;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import fr.mezo.obyke.data.BD;
import fr.mezo.obyke.data.BD.MaterielData.MaterielOccasionData;
import fr.mezo.obyke.workclass.MaterielNeuf;
import fr.mezo.obyke.workclass.MaterielOccasion;

public class MenuConsultationMaterielOccasion extends MenuConsultationDroit {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel tableModel;

	public MenuConsultationMaterielOccasion(int lineLeft,int lineRight) throws SQLException {
		super(lineLeft,lineRight);
		
		//Mise en place formulaire
		JButton button1= new JButton("Modifier");
		JButton button2= new JButton("Supprimer");
		
		//button1.addActionListener((e) -> EditConfirmation());
		//button2.addActionListener((e) -> DeleteConfirmation());
		this.setTable();
		MenuConsultation.addJTable(this.table);
		
		this.addTopSpace();
		
		JTextField societe=new JTextField(15);
		JTextField annee=new JTextField(15);
		JTextField prixAchat=new JTextField(15);
		JTextField dateAchat=new JTextField(15);
		
		JComboBox categ=new JComboBox();
		categ.setPreferredSize(new Dimension(162,28));
		
		JComboBox garantie=new JComboBox();
		garantie.setPreferredSize(new Dimension(162,28));
		
		JTextField coloris=new JTextField(15);
		JTextField prixVente=new JTextField(15);
		JTextField dateMiseVente=new JTextField(15);
		
		this.addLeft(new InputField("Société : ",societe));
		this.addLeft(new InputField("Année : ",annee));
		this.addLeft(new InputField("Prix Achat : ",prixAchat));
		this.addLeft(new InputField("Date Achat : ",dateAchat));
		this.addLeft(new InputField("Garantie : ",garantie));
		
		this.addRight(new InputField("Catégorie : ",categ));
		this.addRight(new InputField("Coloris : ",coloris));
		this.addRight(new InputField("Prix Vente : ",prixVente));
		this.addRight(new InputField("Date Mise en Vente : ",dateMiseVente));
		
		this.addFirstBottomSpace();
		this.addFirstBottomSpace();
		this.addFirstBottomSpace();
	
		this.addSecondBottomSpace();
		this.addSecondBottom(button1);
		this.addSecondBottomSpace();
		this.addSecondBottom(button2);
		this.addSecondBottomSpace();
	
		
		
	}

	@Override
	public void setTable() throws SQLException {
		// TODO Auto-generated method stub
		this.tableModel = new DefaultTableModel();
		
		ArrayList<MaterielOccasion> arrMat = new ArrayList<MaterielOccasion>(BD.MaterielData.MaterielOccasionData.GetAll());
		
		//Entêtes de la JTable
		String[] entetesMat= {"Id","Catégorie","Société","Année","Prix d'achat","Date d'achat","Coloris","Prix de vente","Date de mise en vente"};
		
		//ajout des entêtes à la JTable
		for(String uneEntete : entetesMat) {
			this.tableModel.addColumn(uneEntete);
		
		}
		//Compteur pour l'ajout des lignes
		int i=0;
		
		//Récupération des données de la base que l'on ajoute dans une liste
		//sArrayList<MaterielNeuf> matNeuf=new ArrayList<MaterielNeuf>(arrMatneuf);
		for(MaterielOccasion m : arrMat) {
			this.tableModel.insertRow(i,new Object[]{m.getId(),m.getCateg(),m.getSociete(),m.getAnnee(),m.getPrixAchat(),m.getDateAchat(),m.getColoris(),m.getPrixVente(),m.getDateMisVente()});
			i++;
		}
		//Initialisation de la JTable
		this.table=new JTable(this.tableModel);
	}

}
