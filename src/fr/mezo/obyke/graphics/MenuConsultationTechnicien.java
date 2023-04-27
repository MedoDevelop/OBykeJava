package fr.mezo.obyke.graphics;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import fr.mezo.controller.MailKeyListener;
import fr.mezo.controller.NamesKeyListener;
import fr.mezo.controller.TelephoneKeyListener;
import fr.mezo.obyke.data.BD;
import fr.mezo.obyke.workclass.Technicien;

public class MenuConsultationTechnicien extends MenuConsultationDroit {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel tableModel;
	private int id;

	public MenuConsultationTechnicien(int lineLeft,int lineRight) throws SQLException {
		super(lineLeft,lineRight);
		
		//Initialisation de la JTable
		setTable();
				
		//Ajout de la JTable au menu
		
		//Mise en place formulaire
		JButton button1= new JButton("Modifier");
		JButton button2= new JButton("Supprimer");
		
		this.addTopSpace();
		
		JTextField nom=new JTextField(15);
		//nom.addKeyListener(new NamesKeyListener(nom));
		
		JTextField prenom=new JTextField(15);
		prenom.addKeyListener(new NamesKeyListener(prenom));
		
		JTextField mail=new JTextField(15);
		mail.addKeyListener(new MailKeyListener(mail));
		
		JTextField tel=new JTextField(15);
		tel.addKeyListener(new TelephoneKeyListener(tel));
	
		this.addLeft(new InputField("Nom : ",nom));
		this.addLeft(new InputField("Prénom : ",prenom));
		this.addLeft(new InputField("Mail : ",mail));
		this.addLeft(new InputField("Téléphone : ",tel));
		
		this.addFirstBottomSpace();
		this.addFirstBottomSpace();
		this.addFirstBottomSpace();
		
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
				EditConfirmation(nom,prenom,mail,tel);
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
		
		setInput(nom,prenom,mail,tel);
		
	}
	
	//Fonction qui permet d'ajouter les données de la ligne sélectionnée dans les champs
			public void setInput(JTextField nom,JTextField prenom, JTextField mail,JTextField tel) {
				
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
							nom.setText(table.getModel().getValueAt(row,1).toString());
							prenom.setText(table.getModel().getValueAt(row,2).toString());
							mail.setText(table.getModel().getValueAt(row,3).toString());
							tel.setText(table.getModel().getValueAt(row,4).toString());
					     
					    }
					}
				});
				
			}
			
			public void EditConfirmation(JTextField nom,JTextField prenom,JTextField mail,JTextField tel) throws SQLException {
				JTextField[] tab = {nom,prenom,tel,mail};
				if(Main.AllFieldFilled(tab)) {//On vérifie qu'il n'y a pas de champs vides
					int res = JOptionPane.showConfirmDialog(this,"Êtes-vous sûr de vouloir modifier cette ligne?");
				    
				    if(res == JOptionPane.YES_OPTION)
				    {
				      BD.TechnicienData.Set(this.getId(),nom.getText(),prenom.getText(),mail.getText(),tel.getText(),"");
				      updateTable();
				    }
				}
			}
			
			public void DeleteConfirmation() throws SQLException {
				int res = JOptionPane.showConfirmDialog(this,"Êtes-vous sûr de vouloir supprimer cette ligne?");
			    
			    if(res == JOptionPane.YES_OPTION)
			    {
			    	BD.TechnicienData.Delete(this.getId());
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
				String []entetesTech= {"Id","Nom","Prénom","Mail","Téléphone"};
				
				//ajout des entêtes à la JTable
				for(String uneEntete : entetesTech) {
					this.tableModel.addColumn(uneEntete);
				
				}
				//Compteur pour l'ajout des lignes
				int i=0;
				
				//Récupération des données de la base que l'on ajoute dans une liste
				ArrayList<Technicien>lesTech=new ArrayList<Technicien>(BD.TechnicienData.GetAll());
				for(Technicien t : lesTech) {
					this.tableModel.insertRow(i,new Object[]{t.getId(),t.getNom(),t.getPrenom(),t.getMail(),t.getTelephone()});
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
				ArrayList<Technicien>lesTech=new ArrayList<Technicien>(BD.TechnicienData.GetAll());
				for(Technicien t : lesTech) {
					this.tableModel.insertRow(i,new Object[]{t.getId(),t.getNom(),t.getPrenom(),t.getMail(),t.getTelephone()});
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
				ArrayList<Technicien> arr = new ArrayList<Technicien>(BD.TechnicienData.GetAll());
				for(Technicien t : arr) {
					this.tableModel.insertRow(i,new Object[]{t.getId(),t.getNom(),t.getPrenom(),t.getMail(),t.getTelephone()});
					i++;
				}
				//Initialisation de la JTable
				this.table=new JTable(this.tableModel);
				
			}

}
