package fr.mezo.obyke.graphics;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import fr.mezo.obyke.data.BD;
import fr.mezo.obyke.data.DateSimp;
import fr.mezo.obyke.workclass.LigneCommande;

public class MenuConsultationVenteMatOccas extends MenuConsultationDroit {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel tableModel;
	public MenuConsultationVenteMatOccas(int lineLeft,int lineRight) throws SQLException {
		super(7);
		//Mise en place formulaire
		setTable();
		
		//button1.addActionListener((e) -> EditConfirmation());
		
		JTextField mat = new JTextField();	
		JTextField centre = new JTextField();
		JTextField garantie = new JTextField();
		JTextField dateCommande = new JTextField();
		/*
		p1.add(labPrixMat);
		p2.add(labPrixGarantie);
		p3.add(resumeFacuture);
		
		p1.setOpaque(false);
		p2.setOpaque(false);
		p3.setOpaque(false);
		*/
		mat.setEnabled(false);
		centre.setEnabled(false);
		garantie.setEnabled(false);
		dateCommande.setEnabled(false);
		
		mat.setPreferredSize(new Dimension(300,28));
		centre.setPreferredSize(new Dimension(300,28));
		garantie.setPreferredSize(new Dimension(300,28));
		dateCommande.setPreferredSize(new Dimension(300,28));
		
		this.addEspaceCentrale();
		this.addCentrale(new InputField("Materiel acheté :", mat));
		this.addCentrale(new InputField("Centre acheteur :", centre));
		this.addEspaceCentrale();
		this.addCentrale(new InputField("Garantie :", garantie));
		this.addCentrale(new InputField("Date commande :", dateCommande));
		
		setInput(mat, centre, garantie, dateCommande);
		
	}

	@Override
	public void setTable() throws SQLException {
		// TODO Auto-generated method stub
		this.tableModel = new DefaultTableModel();
		
		ArrayList<LigneCommande> arrMat = new ArrayList<LigneCommande>(BD.LigneCommandeData.GetAllOcas());
		
		//Entêtes de la JTable
		String[] entetesMat= {"Id materiel","Id centre","Id grantie","Date commande"};
		
		//ajout des entêtes à la JTable
		for(String uneEntete : entetesMat) {
			this.tableModel.addColumn(uneEntete);
		
		}
		//Compteur pour l'ajout des lignes
		int i=0;
		
		//Récupération des données de la base que l'on ajoute dans une liste
		//sArrayList<MaterielNeuf> matNeuf=new ArrayList<MaterielNeuf>(arrMatneuf);
		for(LigneCommande l : arrMat) {
			this.tableModel.insertRow(i,new Object[]{l.getIdMateriel(),l.getIdCentre(),l.getIdGarantie(),l.getDateCommande()});
			i++;
		}
		//Initialisation de la JTable
		this.table=new JTable(this.tableModel);
		MenuConsultation.addJTable(this.table);
		
	}
	
	public void setInput(JTextField mat,JTextField centre,JTextField gar,JTextField date) {
			
			this.table.addMouseListener(new MouseAdapter() {
				private JTable table;
	
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 1) {
					      this.table = (JTable)e.getSource();
					      int row = this.table.getSelectedRow();
					       int column = this.table.getSelectedColumn();
					       
					       	//On récupère l'id de l'objet sélectionné, il sera utilisé pour la modification en base de donées
					       	int idMat,idCentre,idGarantie;
					       	idMat = (int) (table.getModel().getValueAt(row,0));
					       	idCentre = (int) (table.getModel().getValueAt(row,1));
					       	idGarantie = (int) (table.getModel().getValueAt(row,2));
					       	DateSimp dateCommande = (DateSimp)(table.getModel().getValueAt(row,3));
					       	
					       	try {
								mat.setText(BD.MaterielData.MaterielOccasionData.Get(idMat).toString());
								centre.setText(BD.CentreData.Get(idCentre).toString());
								gar.setText(BD.GarantieData.Get(idGarantie).toString());
								date.setText(dateCommande.toString());
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					       	
					    }
					}
				});
		
		
	}
	
	@Override
	public void update() throws SQLException {
		// TODO Auto-generated method stub
		/*this.tableModel.setRowCount(0);
		 
		//Compteur pour l'ajout des lignes
		int i=0;
		
		//Récupération des données de la base que l'on ajoute dans une liste
		ArrayList<LigneCommande> arr = new ArrayList<LigneCommande>(BD.LigneCommandeData.GetAllOcas());
		for(LigneCommande l : arr) {
			this.tableModel.insertRow(i,new Object[]{l.getIdMateriel(),l.getIdCentre(),l.getIdGarantie(),l.getDateCommande()});
			i++;
		}
		//Initialisation de la JTable
		this.table=new JTable(this.tableModel);
		*/
	}

}
