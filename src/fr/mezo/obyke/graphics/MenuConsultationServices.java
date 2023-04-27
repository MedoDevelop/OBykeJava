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

import fr.mezo.controller.DateKeyListener;
import fr.mezo.controller.MailKeyListener;
import fr.mezo.controller.NamesKeyListener;
import fr.mezo.controller.TelephoneKeyListener;
import fr.mezo.obyke.data.BD;
import fr.mezo.obyke.data.DateSimp;
import fr.mezo.obyke.workclass.Service;

public class MenuConsultationServices extends MenuConsultationDroit {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel tableModel;
	private int id;
	
	public MenuConsultationServices(int lineLeft,int lineRight) throws SQLException {
		super(lineLeft,lineRight);
		
		//Initialisation de la JTable
		setTable();
				
		//Ajout de la JTable au menu
		
		//Mise en place formulaire
		JComboBox<String> type=new JComboBox<String>(BD.GetTypeService());
		type.setPreferredSize(new Dimension(162,28));
		
		this.addTop(new InputField("Type Service : ",type));

		JTextField deno=new JTextField(15);
		
		JTextField nomDir=new JTextField(15);
		//nomDir.addKeyListener(new NamesKeyListener(nomDir));
		
		JTextField prenomDir=new JTextField(15);
		prenomDir.addKeyListener(new NamesKeyListener(prenomDir));
		
		JTextField tel=new JTextField(15);
		tel.addKeyListener(new TelephoneKeyListener(tel));
		
		JComboBox<String> categ=new JComboBox<String>(BD.GetMaterielCategorie());
		categ.setPreferredSize(new Dimension(162,28));
		
		JTextField marque=new JTextField(15);
		
		JTextField fournisseur=new JTextField(15);
		
		JTextField dateAchat=new JTextField(15);
		dateAchat.addKeyListener(new DateKeyListener(dateAchat));
		
		JTextField dateDepot=new JTextField(15);
		dateDepot.addKeyListener(new DateKeyListener(dateDepot));
		
		JTextField mail=new JTextField(15);
		mail.addKeyListener(new MailKeyListener(mail));

		this.addLeft(new InputField("Dénomination : ",deno));
		this.addLeft(new InputField("Nom du Directeur : ",nomDir));
		this.addLeft(new InputField("Prénom du Directeur : ",prenomDir));
		this.addLeft(new InputField("Téléphone : ",tel));
		this.addLeft(new InputField("Mail : ",mail));
		
		this.addRight(new InputField("Catégorie : ",categ));
		this.addRight(new InputField("Marque : ",marque));
		this.addRight(new InputField("Fournisseur : ",fournisseur));
		this.addRight(new InputField("Date Achat : ",dateAchat));
		this.addRight(new InputField("Date Dépôt : ",dateDepot));
		
				
		this.addFirstBottomSpace();
		this.addFirstBottomSpace();
		this.addFirstBottomSpace();

		JButton button1= new JButton("Modifier");
		JButton button2= new JButton("Supprimer");

		this.addSecondBottomSpace();
		this.addSecondBottom(button1);
		this.addSecondBottomSpace();
		this.addSecondBottom(button2);
		this.addSecondBottomSpace();
		
		button1.addActionListener((e) -> {
			try {
				EditConfirmation(type,deno,nomDir,prenomDir,tel,mail,categ,marque,fournisseur,dateAchat,dateDepot);
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
		
		setInput(type,deno,nomDir,prenomDir,tel,mail,categ,marque,fournisseur,dateAchat,dateDepot);
		
	}
	
	//Fonction qui permet d'ajouter les données de la ligne sélectionnée dans les champs
			public void setInput(JComboBox<String> type,JTextField deno,JTextField nomDir,JTextField prenomDir,JTextField tel,JTextField mail,JComboBox<String> categ,JTextField marque,JTextField fournisseur,JTextField dateAchat,JTextField dateDepot) {
				
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
					       	
					       	//On récupére la valeur de chaque colonnes de la ligne pour les insérer dans les champs correspondants
					       	type.getModel().setSelectedItem(table.getModel().getValueAt(row,1));
					       	deno.setText(table.getModel().getValueAt(row,2).toString());
							nomDir.setText(table.getModel().getValueAt(row,3).toString());
							prenomDir.setText(table.getModel().getValueAt(row,4).toString());
							tel.setText(table.getModel().getValueAt(row,5).toString());
							mail.setText(table.getModel().getValueAt(row,6).toString());
							categ.getModel().setSelectedItem(table.getModel().getValueAt(row,7));
							marque.setText(table.getModel().getValueAt(row,8).toString());
							fournisseur.setText(table.getModel().getValueAt(row,9).toString());
							dateAchat.setText(table.getModel().getValueAt(row,10).toString());
							dateDepot.setText(table.getModel().getValueAt(row,11).toString());
							
					     
					    }
					}
				});
				
			}
			
			public void EditConfirmation(JComboBox<String> type,JTextField deno,JTextField nomDir,JTextField prenomDir,JTextField tel,JTextField mail,JComboBox<String> categ,JTextField marque,JTextField fournisseur,JTextField dateAchat,JTextField dateDepot) throws SQLException {
				JTextField[] tab = {deno,nomDir,prenomDir,tel,mail};
				if(Main.AllFieldFilled(tab)) {//On vérifie qu'il n'a pas de champs vides
					int res = JOptionPane.showConfirmDialog(this,"Êtes-vous sûr de vouloir modifier cette ligne?");
				    
				    if(res == JOptionPane.YES_OPTION)
				    {
				      BD.ServiceData.Set(this.getId(),type.getItemAt(type.getSelectedIndex()),deno.getText(),nomDir.getText(),prenomDir.getText(),tel.getText(),mail.getText(),categ.getItemAt(categ.getSelectedIndex()),marque.getText(),fournisseur.getText(),DateSimp.of(dateAchat.getText()),DateSimp.of(dateDepot.getText()));
				      updateTable();
				    }
				}
			}
			
			public void DeleteConfirmation() throws SQLException {
				int res = JOptionPane.showConfirmDialog(this,"Êtes-vous sûr de vouloir supprimer cette ligne?");
			    
			    if(res == JOptionPane.YES_OPTION)
			    {
			    	BD.ServiceData.Delete(this.getId());
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
				String []entetes= {"Id","Type","Dénomination","Nom Directeur","Prénom Directeur","Téléphone","Mail","Catégorie","Marque","Fournisseur","Date d'achat","Date de dépôt"};
				//ajout des entêtes à la JTable
				for(String uneEntete : entetes) {
					this.tableModel.addColumn(uneEntete);
				
				}
				//Compteur pour l'ajout des lignes
				int i=0;
				
				//Récupération des données de la base que l'on ajoute dans une liste
				ArrayList<Service>services=new ArrayList<Service>(BD.ServiceData.GetAll());
				for(Service s : services) {
					this.tableModel.insertRow(i,new Object[]{s.getIdService(),s.getType(),s.getDenomination(),s.getNomDir(),s.getPrenomDir(),s.getTelephone(),s.getMail(),s.getCateg(),s.getMarque(),s.getFournisseur(),s.getDateAchat(),s.getDateDepot()});
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
				ArrayList<Service>services=new ArrayList<Service>(BD.ServiceData.GetAll());
				for(Service s : services) {
					this.tableModel.insertRow(i,new Object[]{s.getIdService(),s.getType(),s.getDenomination(),s.getNomDir(),s.getPrenomDir(),s.getTelephone(),s.getMail(),s.getCateg(),s.getMarque(),s.getFournisseur(),s.getDateAchat(),s.getDateDepot()});
					i++;
				}
				//Initialisation de la JTable
				this.table=new JTable(this.tableModel);
			}
			
			@Override
			public void update() throws SQLException {
				// TODO Auto-generated method stub
				this.tableModel.setRowCount(0);
				 
				//Compteur pour l'ajout des lignes
				int i=0;
				
				//Récupération des données de la base que l'on ajoute dans une liste
				ArrayList<Service> arr = new ArrayList<Service>(BD.ServiceData.GetAll());
				for(Service e : arr) {
					this.tableModel.insertRow(i,new Object[]{e.getIdService(),e.getType(),e.getDenomination(),e.getNomDir(),e.getPrenomDir(),e.getTelephone(),e.getMail(),e.getCateg(),e.getMarque(),e.getFournisseur(),e.getDateAchat(),e.getDateDepot()});
					i++;
				}
				//Initialisation de la JTable
				this.table=new JTable(this.tableModel);
				
			}

}
