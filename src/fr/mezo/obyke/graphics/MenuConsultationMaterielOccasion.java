package fr.mezo.obyke.graphics;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import fr.mezo.obyke.data.BD;
import fr.mezo.obyke.workclass.Centre;
import fr.mezo.obyke.workclass.Garantie;
import fr.mezo.obyke.workclass.LigneCommande;
import fr.mezo.obyke.workclass.MaterielOccasion;

public class MenuConsultationMaterielOccasion extends MenuConsultationDroit {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel tableModel;

	public MenuConsultationMaterielOccasion(int lineLeft,int lineRight) throws SQLException {
		super(7);
		setTable();
		
		
		JTextField centres = new JTextField();
		JTextField garanties = new JTextField();
		JTextField labPrixMat = new JTextField();
		JTextField labPrixGarantie = new JTextField();
		JTextField resumeFacuture = new JTextField();
		
		/*
		p1.add(labPrixMat);
		p2.add(labPrixGarantie);
		p3.add(resumeFacuture);
		
		p1.setOpaque(false);
		p2.setOpaque(false);
		p3.setOpaque(false);
		*/
		centres.setEnabled(false);
		garanties.setEnabled(false);
		labPrixMat.setEnabled(false);
		labPrixGarantie.setEnabled(false);
		resumeFacuture.setEnabled(false);
		
		labPrixMat.setPreferredSize(new Dimension(300,28));
		labPrixGarantie.setPreferredSize(new Dimension(300,28));
		resumeFacuture.setPreferredSize(new Dimension(300,28));
		
		this.addEspaceCentrale();
		this.addCentrale(new InputField("Centre acheteur :", centres));
		this.addCentrale(new InputField("Garantie :", garanties));
		this.addEspaceCentrale();
		this.addCentrale(new InputField("materiel :", labPrixMat));
		this.addCentrale(new InputField("garantie :", labPrixGarantie));
	}

	@Override
	public void setTable() throws SQLException {
		// TODO Auto-generated method stub
		this.tableModel = new DefaultTableModel();
		
		ArrayList<LigneCommande> arrMat = new ArrayList<LigneCommande>(BD.LigneCommandeData.GetAll());
		
		//Entêtes de la JTable
		String[] entetesMat= {"Id materiel","Id centre","Id garantie","dateCommande"};
		
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
	
	//Focntion qui permet d'ajouter les données de la ligne sélectionnée dans les champs
		public void setInput(JTextField jtf) {
			
			this.table.addMouseListener(new MouseAdapter() {
			private JTable table;

			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
				   this.table = (JTable)e.getSource();
				   int row = this.table.getSelectedRow();
				   int column = this.table.getSelectedColumn();
				   		
				     
				    }
				}
			});
		}
}
