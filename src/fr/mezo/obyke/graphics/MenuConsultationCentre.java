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

import fr.mezo.controller.MailKeyListener;
import fr.mezo.controller.NamesKeyListener;
import fr.mezo.controller.TelephoneKeyListener;
import fr.mezo.obyke.data.BD;
import fr.mezo.obyke.workclass.Centre;
import fr.mezo.obyke.workclass.MaterielNeuf;

public class MenuConsultationCentre extends MenuConsultationDroit {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel tableModel;
	private int id;
	
	public MenuConsultationCentre(int lineLeft,int lineRight) throws SQLException {
		super(lineLeft,lineRight);
		
		//Initialisation de la JTable
		setTable();
		
		//Ajout de la JTable au menu
		
		
		//Mise en place formulaire
		JButton button1= new JButton("Modifier");
		JButton button2= new JButton("Supprimer");
		
		this.addTopSpace();
		
		JTextField deno=new JTextField(15);
		
		JTextField nomDir=new JTextField(15);
		//nomDir.addKeyListener(new NamesKeyListener(nomDir));
		
		JTextField prenomDir=new JTextField(15);
		prenomDir.addKeyListener(new NamesKeyListener(prenomDir));
		
		JTextField tel=new JTextField(15);
		tel.addKeyListener(new TelephoneKeyListener(tel));
		
		JTextField mail=new JTextField(15);
		mail.addKeyListener(new MailKeyListener(mail));
		
		JComboBox<String> type=new JComboBox<String>(BD.GetTypesCentre());
		type.setPreferredSize(new Dimension(162,28));
		
		this.addLeft(new InputField("Dénomination : ",deno));
		this.addLeft(new InputField("Nom du Directeur : ",nomDir));
		this.addLeft(new InputField("Prénom du Directeur : ",prenomDir));
		
		this.addRight(new InputField("Téléphone : ",tel));
		this.addRight(new InputField("Mail : ",mail));
		this.addRight(new InputField("Type : ",type));
		
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
				EditConfirmation(deno,nomDir,prenomDir,tel,mail,type);
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
		
		setInput(deno,nomDir,prenomDir,tel,mail,type);
		
		
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
			if(Main.AllFieldFilled(tab)) {//On vérifie qu'il n'a pas de champs vides
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
			MenuConsultation.addJTable(this.table);
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

		@Override
		public void update() throws SQLException {
			// TODO Auto-generated method stub
			this.tableModel.setRowCount(0);
			 
			//Compteur pour l'ajout des lignes
			int i=0;
			
			//Récupération des données de la base que l'on ajoute dans une liste
			ArrayList<Centre> arr = new ArrayList<Centre>(BD.CentreData.GetAll());
			for(Centre e : arr) {
				this.tableModel.insertRow(i,new Object[]{e.getIdCentre(),e.getTypeCentre(),e.getDenomination(),e.getNomDir(),e.getPrenomDir(),e.getMail(),e.getTelephone()});
				i++;
			}
			//Initialisation de la JTable
			this.table=new JTable(this.tableModel);
			
		}

}
