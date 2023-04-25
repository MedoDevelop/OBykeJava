package fr.mezo.obyke.graphics;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import fr.mezo.obyke.data.BD;
import fr.mezo.obyke.workclass.Centre;
import fr.mezo.obyke.workclass.Garantie;
import fr.mezo.obyke.workclass.MaterielNeuf;

public class MenuConsultationMaterielNeuf extends MenuConsultationDroit {
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	private JTable table;
	private DefaultTableModel tableModel;

	public MenuConsultationMaterielNeuf(int lineLeft,int lineRight) throws SQLException {	
		super(lineLeft,lineRight);
	
		
		this.setTable();
		MenuConsultation.addJTable(this.table);
		//Mise en place formulaire
		JButton button1= new JButton("Modifier");
		JButton button2= new JButton("Supprimer");
		
		//button1.addActionListener((e) -> EditConfirmation());
		//button2.addActionListener((e) -> DeleteConfirmation());

		this.addTopSpace();
		
		JTextField societe=new JTextField(15);
		//String value = table.getModel().getValueAt(row,0).toString();
		//societe.setText(value);
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
		
		setInput(categ,societe,annee,prixAchat,dateAchat,coloris,prixVente,dateMiseVente,garantie);

	}
	
	//Focntion qui permet d'ajouter les données de la ligne sélectionnée dans les champs
	public void setInput(JComboBox categ,JTextField societe,JTextField annee,JTextField prixAchat,JTextField dateAchat,JTextField coloris,JTextField prixVente,JTextField dateMiseVente,JComboBox garantie) {
		
		this.table.addMouseListener(new MouseAdapter() {
		private JTable table;

		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1) {
			   this.table = (JTable)e.getSource();
			   int row = this.table.getSelectedRow();
			   int column = this.table.getSelectedColumn();
			   
			   societe.setText(table.getModel().getValueAt(row,2).toString());
			   categ.getModel().setSelectedItem(table.getModel().getValueAt(row,1));
			   annee.setText(table.getModel().getValueAt(row,3).toString());
				prixAchat.setText(table.getModel().getValueAt(row,4).toString());
				dateAchat.setText(table.getModel().getValueAt(row,5).toString());
					coloris.setText(table.getModel().getValueAt(row,6).toString());
					prixVente.setText(table.getModel().getValueAt(row,7).toString());
					dateMiseVente.setText(table.getModel().getValueAt(row,8).toString());
			     
			    }
			}
		});
	}

	public void setTable() throws SQLException {
		// TODO Auto-generated method stub
		this.tableModel = new DefaultTableModel();
		
		ArrayList<MaterielNeuf> arrMatneuf = new ArrayList<MaterielNeuf>(BD.MaterielData.MaterielNeufData.GetAll());
		
		//Entêtes de la JTable
		String[] entetesMatNeuf= {"Id","Catégorie","Société","Année","Prix d'achat","Date d'achat","Coloris","Prix de vente","Date de mise en vente"};
		
		//ajout des entêtes à la JTable
		for(String uneEntete : entetesMatNeuf) {
			this.tableModel.addColumn(uneEntete);
		
		}
		//Compteur pour l'ajout des lignes
		int i=0;
		
		//Récupération des données de la base que l'on ajoute dans une liste
		//sArrayList<MaterielNeuf> matNeuf=new ArrayList<MaterielNeuf>(arrMatneuf);
		for(MaterielNeuf m : arrMatneuf) {
			this.tableModel.insertRow(i,new Object[]{m.getId(),m.getCateg(),m.getSociete(),m.getAnnee(),m.getPrixAchat(),m.getDateAchat(),m.getColoris(),m.getPrixVente(),m.getDateMisVente()});
			i++;
		}
		//Initialisation de la JTable
		this.table=new JTable(this.tableModel);
	}
	        
}
