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
import fr.mezo.obyke.workclass.Garantie;

public class MenuConsultationGarantie extends MenuConsultationDroit {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel tableModel;
	private int id;

	public MenuConsultationGarantie(int lineLeft,int lineRight) throws SQLException {
		super(lineLeft,lineRight);
		//Initialisation de la JTable
		setTable();
				
		//Ajout de la JTable au menu
		MenuConsultation.addJTable(this.table);
				
		//Mise en place formulaire
		JButton button1= new JButton("Modifier");
		JButton button2= new JButton("Supprimer");
		
		//button1.addActionListener((e) -> EditConfirmation());
		//button2.addActionListener((e) -> DeleteConfirmation());
		
<<<<<<< HEAD
		//Mise ajour de la table 
		try {
			this.setTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Fin mise ajour table
=======
>>>>>>> 29e9c88dbdbab1997281ecc908a5dbc48bea3bd3
		
		this.addTopSpace();
		
		JTextField lib=new JTextField(20);
		JTextField prix=new JTextField(20);
		JTextField duree=new JTextField(20);
	
		this.addLeft(new InputField("Libellé de la garantie : ",lib));
		this.addLeft(new InputField("Prix de la garantie : ",prix));
		this.addLeft(new InputField("Duréee de la garantie (en mois) : ",duree));
		
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
		
		setInput(lib,prix,duree);
		
		button1.addActionListener((e) -> {
			try {
				EditConfirmation(lib,prix,duree);
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
		
		
	}
	
	//Fonction qui permet d'ajouter les données de la ligne sélectionnée dans les champs
			public void setInput(JTextField lib,JTextField prix,JTextField duree) {
				
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
							lib.setText(table.getModel().getValueAt(row,1).toString());
							prix.setText(table.getModel().getValueAt(row,2).toString());
							duree.setText(table.getModel().getValueAt(row,3).toString());
		
					     
					    }
					}
				});
				
			}
			
			public void EditConfirmation(JTextField lib,JTextField prix,JTextField duree) throws SQLException {
				JTextField[] tab = {lib,prix,duree};
				if(Main.AllFieldFilled(tab)) {//On vérifie qu'il n'a pas de champs vides
					int res = JOptionPane.showConfirmDialog(this,"Êtes-vous sûr de vouloir modifier cette ligne?");
				    
				    if(res == JOptionPane.YES_OPTION)
				    {
				    	double newPrix =Double.parseDouble(prix.getText());
				    	int newDuree =Integer.parseInt(duree.getText());
				    	BD.GarantieData.Set(this.getId(),lib.getText(),newPrix,newDuree);
				    	updateTable();
				    }
				}
			}
			
			public void DeleteConfirmation() throws SQLException {
				int res = JOptionPane.showConfirmDialog(this,"Êtes-vous sûr de vouloir supprimer cette ligne?");
			    
			    if(res == JOptionPane.YES_OPTION)
			    {
			    	BD.GarantieData.Delete(this.getId());
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
				String[] entetes = {"Id","Libelle","Prix","Duree en mois"};
				
				//ajout des entêtes à la JTable
				for(String uneEntete : entetes) {
					this.tableModel.addColumn(uneEntete);
				
				}
				//Compteur pour l'ajout des lignes
				int i=0;
				
				//Récupération des données de la base que l'on ajoute dans une liste
				ArrayList<Garantie>garanties=new ArrayList<Garantie>(BD.GarantieData.GetAll());
				for(Garantie g : garanties) {
					this.tableModel.insertRow(i,new Object[]{g.getId(),g.getLibelle(),g.getPrix(),g.getDuree()});
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
				ArrayList<Garantie>garanties=new ArrayList<Garantie>(BD.GarantieData.GetAll());
				for(Garantie g : garanties) {
					this.tableModel.insertRow(i,new Object[]{g.getId(),g.getLibelle(),g.getPrix(),g.getDuree()});
					i++;
				}
				//Initialisation de la JTable
				this.table=new JTable(this.tableModel);
			}


	@Override
	public void setTable() throws SQLException {
		// TODO Auto-generated method stub
		String[] entete = {"Id","Libelle","Prix","Duree en mois"};
		ArrayList<Garantie> lesGaranties = new ArrayList<Garantie>();
		try {
			lesGaranties = BD.GarantieData.GetAll();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[][] datas = new String[lesGaranties.size()][4];
		for(int i=0;i<lesGaranties.size();i++) {
			datas[i][0] = String.valueOf(lesGaranties.get(i).getId());
			datas[i][1] = lesGaranties.get(i).getLibelle();
			datas[i][2] = String.valueOf(lesGaranties.get(i).getPrix());
			datas[i][3] = String.valueOf(lesGaranties.get(i).getDuree());
		}
		
		JTable table = new JTable(datas,entete);
		MenuConsultation.addJTable(table);
	}

}
