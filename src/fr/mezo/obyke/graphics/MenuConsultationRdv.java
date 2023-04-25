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

import fr.mezo.obyke.data.BD;
import fr.mezo.obyke.data.DateSimp;
import fr.mezo.obyke.workclass.Centre;
import fr.mezo.obyke.workclass.Rdv;
import fr.mezo.obyke.workclass.Technicien;

public class MenuConsultationRdv extends MenuConsultationDroit {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel tableModel;
	private int id;

	public MenuConsultationRdv(int lineLeft,int lineRight) throws SQLException {
		super(lineLeft,lineRight);
		
		//Initialisation de la JTable
		setTable();
				
		//Ajout de la JTable au menu
		MenuConsultation.addJTable(this.table);
		
		//Mise en place formulaire
		JButton button1= new JButton("Modifier");
		JButton button2= new JButton("Supprimer");
		
		this.addTopSpace();
		
		//button1.addActionListener((e) -> EditConfirmation());
		//button2.addActionListener((e) -> DeleteConfirmation());
		
		JTextField deno=new JTextField(15);
		JTextField nomDir=new JTextField(15);
		JTextField prenomDir=new JTextField(15);
		JTextField tel=new JTextField(15);
		JTextField mail=new JTextField(15);
		JTextField date=new JTextField(15);
		JTextField heure=new JTextField(15);
		JComboBox<String> motif=new JComboBox<String>(BD.GetTypeService());
		
		ArrayList<Technicien>lesTech=new ArrayList<Technicien>(BD.TechnicienData.GetAll());
		Technicien[] lesTechArray= new Technicien[lesTech.size()];
		int i=0;
		for(Technicien unTech : lesTech) {
			lesTechArray[i]=unTech;
			i++;
		}
		JComboBox<Technicien> tech=new JComboBox<Technicien>(lesTechArray);
		
		
		motif.setPreferredSize(new Dimension(162,28));
		tech.setPreferredSize(new Dimension(162,28));
		heure.setText("00:00");
		
		this.addLeft(new InputField("Dénomination : ",deno));
		this.addLeft(new InputField("Nom du Directeur : ",nomDir));
		this.addLeft(new InputField("Prénom du Directeur : ",prenomDir));
		this.addLeft(new InputField("Mail : ",mail));
		this.addLeft(new InputField("Téléphone : ",tel));
		
		
		this.addRight(new InputField("Date du RDV : ",date));
		this.addRight(new InputField("Heure du RDV : ",heure));
		this.addRight(new InputField("Motif : ",motif));
		this.addRight(new InputField("Technicien : ",tech));
		
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
				EditConfirmation(deno, nomDir, prenomDir,tel,mail, date,heure, motif, tech);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		
		button2.addActionListener((e) -> {
			try {
				DeleteConfirmation();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		setInput(deno, nomDir, prenomDir,tel,mail, date,heure, motif, tech);
	
	}
	
	//Fonction qui permet d'ajouter les données de la ligne sélectionnée dans les champs
			public void setInput(JTextField deno,JTextField nomDir, JTextField prenomDir,JTextField tel,JTextField mail,JTextField date,JTextField heure,JComboBox<String> motif,JComboBox<Technicien>tech) {
				
				this.table.addMouseListener(new MouseAdapter() {
				private JTable table;

				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 1) {
					      this.table = (JTable)e.getSource();
					      int row = this.table.getSelectedRow();
					       int column = this.table.getSelectedColumn();
					       
					       	//On récupère l'id de l'objet sélectionné, il sera utilisé pour la modification en base de donées
					       	String id=(table.getModel().getValueAt(row,0).toString());
					       	int newId =Integer.parseInt(id);
					       	setId(newId);
					       	
					       	//On récupére la valeur de chaque colonnes de la ligne pour les insérer dans les champs correspondants
							deno.setText(table.getModel().getValueAt(row,2).toString());
							nomDir.setText(table.getModel().getValueAt(row,3).toString());
							prenomDir.setText(table.getModel().getValueAt(row,4).toString());
							mail.setText(table.getModel().getValueAt(row,5).toString());
							tel.setText(table.getModel().getValueAt(row,6).toString());
							motif.getModel().setSelectedItem(table.getModel().getValueAt(row,1));
							tech.getModel().setSelectedItem(table.getModel().getValueAt(row,1));
					     
					    }
					}
				});
				
			}
			
			public void EditConfirmation(JTextField deno,JTextField nomDir,JTextField prenomDir,JTextField tel,JTextField mail,JTextField date,JTextField heure,JComboBox<String>motif,JComboBox<Technicien>tech) throws SQLException {
				JTextField[] tab = {deno,nomDir,prenomDir,tel,mail};
				if(Main.AllFieldFilled(tab)) {//On vérifie qu'il n'a pas de champs vides
					int res = JOptionPane.showConfirmDialog(this,"Êtes-vous sûr de vouloir modifier cette ligne?");
				    
				    if(res == JOptionPane.YES_OPTION)
				    {
				      //BD.RDVData.Set(this.getId(),deno.getText(),nomDir.getText(),prenomDir.getText(),tel.getText(),mail.getText(),type.getItemAt(type.getSelectedIndex()).toString(),"");
				      updateTable();
				    }
				}
			}
			
			public void DeleteConfirmation() throws SQLException {
				int res = JOptionPane.showConfirmDialog(this,"Êtes-vous sûr de vouloir supprimer cette ligne?");
			    
			    if(res == JOptionPane.YES_OPTION)
			    {
			    	BD.CentreData.Delete(this.getId());
			    	updateTable();
			    }
			}
		
			public int getId() {
				return this.id;
			}
		
			public void setId(int unId) {
				this.id=unId;
			}
			
			public void setTable() throws SQLException {
				//Mise en place de la JTable
				this.tableModel = new DefaultTableModel();
				
				//Entêtes de la JTable
				String []entetesRdv= {"Id","Dénomination","Nom Directeur","Prénom Directeur","Mail","Téléphone","Motif","Technicien"};
				
				//ajout des entêtes à la JTable
				for(String uneEntete : entetesRdv) {
					this.tableModel.addColumn(uneEntete);
				
				}
				//Compteur pour l'ajout des lignes
				int i=0;
				
				//Récupération des données de la base que l'on ajoute dans une liste
				ArrayList<Rdv>rdv=new ArrayList<Rdv>(BD.RDVData.GetAll());
				for(Rdv r : rdv) {
					this.tableModel.insertRow(i,new Object[]{r.getId(),r.getDenomination(),r.getNomDir(),r.getPrenomDir(),r.getMail(),r.getTelepone(),r.getMotif(),r.getTechId()});
					i++;
				}
				//Initialisation de la JTable
				this.table=new JTable(this.tableModel);
			}
			
			public void updateTable() throws SQLException {
				//On enlève toutes les lignes du tableau
				this.tableModel.setRowCount(0);
				 
				//Compteur pour l'ajout des lignes
				int i=0;
				
				//Récupération des données de la base que l'on ajoute dans une liste
				ArrayList<Rdv>rdv=new ArrayList<Rdv>(BD.RDVData.GetAll());
				for(Rdv r : rdv) {
					this.tableModel.insertRow(i,new Object[]{r.getId(),r.getDenomination(),r.getNomDir(),r.getPrenomDir(),r.getMail(),r.getTelepone(),r.getMotif(),r.getTechId()});
					i++;
				}
				//Initialisation de la JTable
				this.table=new JTable(this.tableModel);
			}

}
