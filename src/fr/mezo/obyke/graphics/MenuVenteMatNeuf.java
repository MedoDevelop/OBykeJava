package fr.mezo.obyke.graphics;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import fr.mezo.obyke.workclass.Centre;

public class MenuVenteMatNeuf extends MenuConsultationDroit {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel tableModel;
	private int id;

	public MenuVenteMatNeuf(int lineLeft,int lineRight) throws SQLException {
		super(lineLeft,lineRight);
		
		//Initialisation de la JTable
		setTable();
				
		//Ajout de la JTable au menu
		MenuConsultation.addJTable(this.table);
		
		//Mise en place formulaire
		JButton button1= new JButton("Vendre");
		JButton button2= new JButton("Annuler");
		
		//button1.addActionListener((e) -> EditConfirmation());
		
		this.addTopSpace();
		
		JTextField coloris=new JTextField(15);
		JTextField prixAchat=new JTextField(15);
		JTextField prixVente=new JTextField(15);
		JTextField dateMiseVente=new JTextField(15);
		
		JComboBox<String> categ=new JComboBox<String>();
		categ.setPreferredSize(new Dimension(162,28));
		
		this.addLeft(new InputField("Catégorie : ",categ));
		this.addLeft(new InputField("Coloris : ",coloris));
		this.addLeft(new InputField("Prix Achat : ",prixAchat));
		this.addLeft(new InputField("Prix Vente : ",prixVente));
		this.addLeft(new InputField("Date Mise en Vente : ",dateMiseVente));
		
		
		this.addFirstBottomSpace();
		this.addFirstBottomSpace();
		this.addFirstBottomSpace();
		
		this.addSecondBottomSpace();
		this.addSecondBottom(button1);
		this.addSecondBottomSpace();
		this.addSecondBottom(button2);
		this.addSecondBottomSpace();
			
	}
	
	//Fonction qui permet d'ajouter les données de la ligne sélectionnée dans les champs
			public void setInput(JTextField deno,JTextField nomDir, JTextField prenomDir,JTextField tel,JTextField mail,JComboBox<String> type) {
				
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
							type.getModel().setSelectedItem(table.getModel().getValueAt(row,1));
					     
					    }
					}
				});
				
			}
			
			public void EditConfirmation(JTextField deno,JTextField nomDir,JTextField prenomDir,JTextField tel,JTextField mail,JComboBox<String> type) throws SQLException {
				JTextField[] tab = {deno,nomDir,prenomDir,tel,mail};
				if(Main.AllFieldFilled(tab)) {//On vérifie qu'il n'a pas de champs vident
					int res = JOptionPane.showConfirmDialog(this,"Êtes-vous sûr de vouloir modifier cette ligne?");
				    
				    if(res == JOptionPane.YES_OPTION)
				    {
				      BD.CentreData.Set(this.getId(),deno.getText(),nomDir.getText(),prenomDir.getText(),tel.getText(),mail.getText(),type.getItemAt(type.getSelectedIndex()).toString(),"");
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
				String []entetesCentre= {"Id","Catégorie","Dénomination","Nom Directeur","Prénom Directeur","Mail","Téléphone"};
				
				//ajout des entêtes à la JTable
				for(String uneEntete : entetesCentre) {
					this.tableModel.addColumn(uneEntete);
				
				}
				//Compteur pour l'ajout des lignes
				int i=0;
				
				//Récupération des données de la base que l'on ajoute dans une liste
				ArrayList<Centre>centres=new ArrayList<Centre>(BD.CentreData.GetAll());
				for(Centre c : centres) {
					this.tableModel.insertRow(i,new Object[]{c.getIdCentre(),c.getTypeCentre(),c.getDenomination(),c.getNomDir(),c.getPrenomDir(),c.getMail(),c.getTelephone()});
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
				ArrayList<Centre>centres=new ArrayList<Centre>(BD.CentreData.GetAll());
				for(Centre c : centres) {
					this.tableModel.insertRow(i,new Object[]{c.getIdCentre(),c.getTypeCentre(),c.getDenomination(),c.getNomDir(),c.getPrenomDir(),c.getMail(),c.getTelephone()});
					i++;
				}
				//Initialisation de la JTable
				this.table=new JTable(this.tableModel);
			}
	
	

}
