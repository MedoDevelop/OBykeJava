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
import fr.mezo.obyke.workclass.Garantie;
import fr.mezo.obyke.workclass.MaterielNeuf;
import fr.mezo.obyke.workclass.Technicien;

public class MenuVenteMatNeuf extends MenuConsultationDroit {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel tableModel;
	private JComboBox<Garantie> garanties;
	private int Id;
	private double prixMat;
	private double prixGarantie;
	
	public MenuVenteMatNeuf(int lineLeft,int lineRight) throws SQLException {
		super(7);
		this.prixMat = 0;
		this.prixGarantie = 0;
		//Mise en place formulaire
		JButton button1= new JButton("Vendre");
		JButton button2= new JButton("Annuler");
		setTable();
		
		//button1.addActionListener((e) -> EditConfirmation());
		ArrayList<Centre> centreArr = new ArrayList<Centre>(BD.CentreData.GetAll());
		int n = centreArr.size();
		Centre[] tab = new Centre[n];
		for(int i=0;i<n;i++) {
			tab[i] = centreArr.get(i);
		}
		JComboBox<Centre> centres = new JComboBox<Centre>(tab);
		
		ArrayList<Garantie> garantieArr = new ArrayList<Garantie>(BD.GarantieData.GetAll());
		n = garantieArr.size();
		Garantie[] tabG = new Garantie[n];
		for(int i=0;i<n;i++) {
			tabG[i] = garantieArr.get(i);
		}
		this.garanties = new JComboBox<Garantie>(tabG);
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
		labPrixMat.setEnabled(false);
		labPrixGarantie.setEnabled(false);
		resumeFacuture.setEnabled(false);
		
		centres.setPreferredSize(new Dimension(300,28));
		this.garanties.setPreferredSize(new Dimension(300,28));
		labPrixMat.setPreferredSize(new Dimension(300,28));
		labPrixGarantie.setPreferredSize(new Dimension(300,28));
		resumeFacuture.setPreferredSize(new Dimension(300,28));
		
		this.addEspaceCentrale();
		this.addCentrale(new InputField("Centre acheteur :", centres));
		this.addCentrale(new InputField("Garantie :", this.garanties));
		this.addEspaceCentrale();
		this.addCentrale(new InputField("Prix du materiel :", labPrixMat));
		this.addCentrale(new InputField("Prix de la garantie :", labPrixGarantie));
		this.addCentrale(new InputField("Prix total : ", resumeFacuture));
		
		button1.addActionListener((e)->{
			int res = JOptionPane.showConfirmDialog(this,"Êtes-vous sûr de vouloir enregistrer cette vente?");
			if(res == JOptionPane.YES_OPTION)
				    {
				//Action du buton valider
				//Vendre le materiel et qui le formulaire 
				
				
				JTextField[] jtf = {labPrixGarantie,labPrixMat,resumeFacuture};
				if(Main.AllFieldFilled(jtf)) {
					int idMateriel = this.Id;
					int idGarantie = ((Garantie) this.garanties.getSelectedItem()).getId();
					int idCentre = ((Centre) centres.getSelectedItem()).getIdCentre();
					DateSimp today = DateSimp.now();
					try {
						BD.LigneCommandeData.Add(idMateriel, today, idCentre, idGarantie);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					erase(labPrixMat, labPrixGarantie, resumeFacuture);
					}
				    	
				    }
			
			
		});
		
		button2.addActionListener((e)->{
			//Action du buton annuler
			//Quitter le formulaire
			erase(labPrixMat, labPrixGarantie, resumeFacuture);
		});
		setInput(labPrixMat, labPrixGarantie, resumeFacuture);
		this.addFirstBottomSpace();
		this.addFirstBottomSpace();
		this.addFirstBottomSpace();
		
		this.addSecondBottomSpace();
		this.addSecondBottom(button1);
		this.addSecondBottomSpace();
		this.addSecondBottomSpace();
		this.addSecondBottom(button2);
		this.addSecondBottomSpace();
			
	}

	@Override
	public void setTable() throws SQLException {
		// TODO Auto-generated method stub
		this.tableModel = new DefaultTableModel();
		
		ArrayList<MaterielNeuf> arrMat = new ArrayList<MaterielNeuf>(BD.MaterielData.MaterielNeufData.GetAllAVentre());
		
		//Entêtes de la JTable
		String[] entetesMat= {"Id","Catégorie","Coloris","Année","Prix d'achat","Prix de vente"};
		
		//ajout des entêtes à la JTable
		for(String uneEntete : entetesMat) {
			this.tableModel.addColumn(uneEntete);
		
		}
		//Compteur pour l'ajout des lignes
		int i=0;
		
		//Récupération des données de la base que l'on ajoute dans une liste
		//sArrayList<MaterielNeuf> matNeuf=new ArrayList<MaterielNeuf>(arrMatneuf);
		for(MaterielNeuf m : arrMat) {
			this.tableModel.insertRow(i,new Object[]{m.getId(),m.getCateg(),m.getColoris(),m.getAnnee(),m.getPrixAchat(),m.getPrixVente()});
			i++;
		}
		//Initialisation de la JTable
		this.table=new JTable(this.tableModel);
		MenuConsultation.addJTable(this.table);
	}
	
	public void setId(int id) {
		this.Id = id;
	}
	
	public void erase(JTextField labMat,JTextField labGarantie,JTextField labResume) {
		labMat.setText("");
		labGarantie.setText("");
		labResume.setText("");
	} 
	
	public void updateMontant(JTextField labResume) {
		double mtt = this.prixMat+this.prixGarantie;
		labResume.setText("Le montant totale :"+mtt);
	}
	
	public void setInput(JTextField labMat,JTextField labGarantie,JTextField labResume) {
		
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
				       	try {
							prixMat = BD.MaterielData.MaterielNeufData.Get(newId).getPrixVente();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							prixMat = 0;
							e1.printStackTrace();
						}
				       	labMat.setText("Prix du materiel : "+prixMat);
				       	updateMontant(labResume);
				    }
				}
			});
			/*this.garanties.addPropertyChangeListener(new PropertyChangeListener() {
				
				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					// TODO Auto-generated method stub
					JComboBox<Garantie> gC = (JComboBox<Garantie>)evt.getSource();
					Garantie g = gC.getItemAt(gC.getSelectedIndex());
					prixGarantie = g.getPrix();
					System.out.println(g.getPrix());
					labGarantie.setText("Prix de la garantie : "+String.valueOf(prixGarantie));
					updateMontant(labResume);
				}			
				});*/
		this.garanties.addActionListener((e)->{
			@SuppressWarnings("unchecked")
			JComboBox<Garantie> jComboBox = (JComboBox<Garantie>)e.getSource();
			JComboBox<Garantie> gC = jComboBox;
			Garantie g = gC.getItemAt(gC.getSelectedIndex());
			prixGarantie = g.getPrix();
			System.out.println(g.getPrix());
			labGarantie.setText("Prix de la garantie : "+String.valueOf(prixGarantie));
			updateMontant(labResume);
		});
			
	}
	
	@Override
	public void update() throws SQLException {
		// TODO Auto-generated method stub
		this.tableModel.setRowCount(0);
		 
		//Compteur pour l'ajout des lignes
		int i=0;
		
		//Récupération des données de la base que l'on ajoute dans une liste
		ArrayList<MaterielNeuf> arr = new ArrayList<MaterielNeuf>(BD.MaterielData.MaterielNeufData.GetAllAVentre());
		for(MaterielNeuf m : arr) {
			this.tableModel.insertRow(i,new Object[]{m.getId(),m.getCateg(),m.getColoris(),m.getAnnee(),m.getPrixAchat(),m.getPrixVente()});
			i++;
		}
		//Initialisation de la JTable
		this.table=new JTable(this.tableModel);
		
		
	}

}
