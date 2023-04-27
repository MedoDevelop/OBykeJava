package fr.mezo.obyke.graphics;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import fr.mezo.controller.AnneeKeyListener;
import fr.mezo.controller.DateKeyListener;
import fr.mezo.controller.PrixKeyListener;
import fr.mezo.obyke.data.BD;
import fr.mezo.obyke.data.DateSimp;
import fr.mezo.obyke.workclass.MaterielOccasion;

public class MenuConsultationMaterielOccasion extends MenuConsultationDroit {
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	private JTable table;
	private DefaultTableModel tableModel;
	private int id;
	
	public MenuConsultationMaterielOccasion(int lineLeft,int lineRight) throws SQLException {	
		super(lineLeft,lineRight);
	
		
		this.setTable();
		//Mise en place formulaire
		JButton button1= new JButton("Modifier");
		JButton button2= new JButton("Supprimer");
		
		//button1.addActionListener((e) -> EditConfirmation());
		//button2.addActionListener((e) -> DeleteConfirmation());

		this.addTopSpace();
		
		JTextField societe=new JTextField(15);
		
		JTextField annee=new JTextField(15);
		annee.addKeyListener(new AnneeKeyListener(annee));
		
		JTextField prixAchat=new JTextField(15);
		prixAchat.addKeyListener(new PrixKeyListener(prixAchat));
		
		JTextField dateAchat=new JTextField(15);
		dateAchat.addKeyListener(new DateKeyListener(dateAchat));
		
		JComboBox<String> categ=new JComboBox<String>(BD.GetMaterielCategorie());
		categ.setPreferredSize(new Dimension(162,28));
		
		JTextField coloris=new JTextField(15);
		
		JTextField prixVente=new JTextField(15);
		prixVente.addKeyListener(new PrixKeyListener(prixVente));
		
		JTextField dateMiseVente=new JTextField(15);
		dateMiseVente.addKeyListener(new DateKeyListener(dateMiseVente));
		
		this.addLeft(new InputField("Société : ",societe));
		this.addLeft(new InputField("Année : ",annee));
		this.addLeft(new InputField("Prix Achat : ",prixAchat));
		this.addLeft(new InputField("Date Achat : ",dateAchat));
		
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
		
		button1.addActionListener((e) -> {
			try {
				EditConfirmation(categ,societe,annee,prixAchat,dateAchat,coloris,prixVente,dateMiseVente);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		
		button2.addActionListener((e) -> {
			try {
				DeleteConfirmation(categ,societe,annee,prixAchat,dateAchat,coloris,prixVente,dateMiseVente);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		setInput(categ,societe,annee,prixAchat,dateAchat,coloris,prixVente,dateMiseVente);

	}
	
	//Focntion qui permet d'ajouter les données de la ligne sélectionnée dans les champs
	public void setInput(JComboBox<String> categ,JTextField societe,JTextField annee,JTextField prixAchat,JTextField dateAchat,JTextField coloris,JTextField prixVente,JTextField dateMiseVente) {
		
		this.table.addMouseListener(new MouseAdapter() {
		private JTable table;

		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1) {
			   this.table = (JTable)e.getSource();
			   int row = this.table.getSelectedRow();
			   
			   //On récupère l'id de l'objet sélectionné, il sera utilisé pour la modification en base de donées
		       String id=(table.getModel().getValueAt(row,0).toString());
		       int newId =Integer.parseInt(id);
		       setId(newId);
		       
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
		

		
		
		//Entêtes de la JTable

		//Entêtes de la JTable
		String[] entetesMatOccas= {"Id","Catégorie","Société","Année","Prix d'achat","Date d'achat","Coloris","Prix de vente","Date de mise en vente"};

		
		//ajout des entêtes à la JTable
		for(String uneEntete : entetesMatOccas) {
			this.tableModel.addColumn(uneEntete);
		}
		//Compteur pour l'ajout des lignes
		int i=0;
		
		//Récupération des données de la base que l'on ajoute dans une liste

		//sArrayList<MaterielNeuf> matNeuf=new ArrayList<MaterielNeuf>(arrMatneuf);
		
		ArrayList<MaterielOccasion> arrMat = new ArrayList<MaterielOccasion>(BD.MaterielData.MaterielOccasionData.GetAll());
		for(MaterielOccasion m : arrMat) {
			this.tableModel.insertRow(i,new Object[]{m.getId(),m.getCateg(),m.getSociete(),m.getAnnee(),m.getPrixAchat(),m.getDateAchat(),m.getColoris(),m.getPrixVente(),m.getDateMisVente()});
			i++;
		}
		//Initialisation de la JTable
		this.table=new JTable(this.tableModel);
		MenuConsultation.addJTable(this.table);
	}
	
	public void updateTable() throws SQLException {
		//On enlève toutes les lignes du tableau
		this.tableModel.setRowCount(0);
		 
		//Compteur pour l'ajout des lignes
		int i=0;
		
		//Récupération des données de la base que l'on ajoute dans une liste
		ArrayList<MaterielOccasion> arrMatOccas = new ArrayList<MaterielOccasion>(BD.MaterielData.MaterielOccasionData.GetAll());
		for(MaterielOccasion m : arrMatOccas) {
			this.tableModel.insertRow(i,new Object[]{m.getId(),m.getCateg(),m.getSociete(),m.getAnnee(),m.getPrixAchat(),m.getDateAchat(),m.getColoris(),m.getPrixVente(),m.getDateMisVente()});
			i++;
		}
		//Initialisation de la JTable
		this.table=new JTable(this.tableModel);
	}
	
	public void EditConfirmation(JComboBox<String> categ,JTextField societe,JTextField annee,JTextField prixAchat,JTextField dateAchat,JTextField coloris,JTextField prixVente,JTextField dateMiseVente) throws SQLException {
		
		JTextField[] tab = {societe,annee,prixAchat,dateAchat,coloris,prixVente,dateMiseVente};
		if(Main.AllFieldFilled(tab)) {//On vérifie qu'il n'a pas de champs vides
			int res = JOptionPane.showConfirmDialog(this,"Êtes-vous sûr de vouloir modifier cette ligne?");
		    
		    if(res == JOptionPane.YES_OPTION)
		    {
		    	double newPrixVente=Double.parseDouble(prixVente.getText());
		    	double newPrixAchat=Double.parseDouble(prixAchat.getText());
		    	
		      BD.MaterielData.Set(this.id,coloris.getText(),newPrixVente,DateSimp.of(dateMiseVente.getText()),categ.getItemAt(categ.getSelectedIndex()).toString(),DateSimp.now(),societe.getText(),newPrixAchat,DateSimp.of(dateAchat.getText()),annee.getText());
		      updateTable();
		    }
		}
	}
	
	public void DeleteConfirmation(JComboBox<String> categ,JTextField societe,JTextField annee,JTextField prixAchat,JTextField dateAchat,JTextField coloris,JTextField prixVente,JTextField dateMiseVente) throws SQLException {
		int res = JOptionPane.showConfirmDialog(this,"Êtes-vous sûr de vouloir supprimer cette ligne?");
	    
	    if(res == JOptionPane.YES_OPTION)
	    {
	    	BD.MaterielData.MaterielOccasionData.Delete(this.getId());
	    	updateTable();
	    	Cancel(categ,societe,annee,prixAchat,dateAchat,coloris,prixVente,dateMiseVente);
	    }
	}

	public int getId() {
		return this.id;
	}

	public void setId(int unId) {
		this.id=unId;
	}
	
	//Fonction qui supprime la valeur des champs
		public void Cancel(JComboBox<String> categ,JTextField societe,JTextField annee,JTextField prixAchat,JTextField dateAchat,JTextField coloris,JTextField prixVente,JTextField dateMiseVente) {
			categ.setSelectedItem(0);
			societe.setText("");
			annee.setText("");
			prixAchat.setText("");
			dateAchat.setText("");
			coloris.setText("");
			prixVente.setText("");
			dateMiseVente.setText("");
			
		}

		@Override
		public void update() throws SQLException {
			// TODO Auto-generated method stub
			this.tableModel.setRowCount(0);
			 
			//Compteur pour l'ajout des lignes
			int i=0;
			
			//Récupération des données de la base que l'on ajoute dans une liste
			ArrayList<MaterielOccasion> arr = new ArrayList<MaterielOccasion>(BD.MaterielData.MaterielOccasionData.GetAll());
			for(MaterielOccasion m : arr) {
				this.tableModel.insertRow(i,new Object[]{m.getId(),m.getCateg(),m.getSociete(),m.getAnnee(),m.getPrixAchat(),m.getDateAchat(),m.getColoris(),m.getPrixVente(),m.getDateMisVente()});
				i++;
			}
			//Initialisation de la JTable
			this.table=new JTable(this.tableModel);
		}
}
