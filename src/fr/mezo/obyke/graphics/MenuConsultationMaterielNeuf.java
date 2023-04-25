package fr.mezo.obyke.graphics;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

import fr.mezo.obyke.data.BD;
import fr.mezo.obyke.workclass.Garantie;

public class MenuConsultationMaterielNeuf extends MenuConsultationDroit {
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	private JTable table;

	public MenuConsultationMaterielNeuf(int lineLeft,int lineRight) {	
		super(lineLeft,lineRight);
	
		String []entetesMatNeuf= {"Catégorie","Société","Année","Prix d'achat","Date d'achat","Coloris","Prix de vente","Date de mise en vente","Garantie"};
		Object [][]matNeuf= {{"Aquabiking","Beauté Minceur","2020","574.95","20/06/2020","Rouge","520","11/04/2023","Normal"},{"Aquabiking","Body Minute","2020","574.95","20/06/2020","Rouge","520","11/04/2023","Normal"}};
		this.table = new JTable(matNeuf,entetesMatNeuf);
		MenuConsultation.addJTable(this.table);
		
		//Mise en place formulaire
		JButton button1= new JButton("Modifier");
		JButton button2= new JButton("Supprimer");
		
		//button1.addActionListener((e) -> EditConfirmation());
		//button2.addActionListener((e) -> DeleteConfirmation());

		this.addTopSpace();
		
		JTextField societe=new JTextField(15);
		//String value = table.getModel().getValueAt(row,0).toString();
		//societe.setText(value);
		JTextField annee=new JTextField(15);
		JTextField prixAchat=new JTextField(15);
		JTextField dateAchat=new JTextField(15);
		
		JComboBox categ=new JComboBox();
		categ.setPreferredSize(new Dimension(162,28));
		
		JComboBox garantie=new JComboBox();
		garantie.setPreferredSize(new Dimension(162,28));
		
		JTextField coloris=new JTextField(15);
		JTextField prixVente=new JTextField(15);
		JTextField dateMiseVente=new JTextField(15);
		
		this.addLeft(new InputField("Société : ",societe));
		this.addLeft(new InputField("Année : ",annee));
		this.addLeft(new InputField("Prix Achat : ",prixAchat));
		this.addLeft(new InputField("Date Achat : ",dateAchat));
		this.addLeft(new InputField("Garantie : ",garantie));
		
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
		
		setInput(categ,societe,annee,prixAchat,dateAchat,coloris,prixVente,dateMiseVente,garantie);

	}
	
	//Focntion qui permet d'ajouter les données de la ligne sélectionnée dans les champs
	public void setInput(JComboBox categ,JTextField societe,JTextField annee,JTextField prixAchat,JTextField dateAchat,JTextField coloris,JTextField prixVente,JTextField dateMiseVente,JComboBox garantie) {
		
		this.table.addMouseListener(new MouseAdapter() {
		private JTable table;

		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1) {
			      this.table = (JTable)e.getSource();
			      int row = this.table.getSelectedRow();
			       int column = this.table.getSelectedColumn();
			     
			        
			        categ.setSelectedItem(0);
					societe.setText(table.getModel().getValueAt(row,1).toString());
					annee.setText(table.getModel().getValueAt(row,2).toString());
					prixAchat.setText(table.getModel().getValueAt(row,3).toString());
					dateAchat.setText(table.getModel().getValueAt(row,4).toString());
					coloris.setText(table.getModel().getValueAt(row,5).toString());
					prixVente.setText(table.getModel().getValueAt(row,6).toString());
					dateMiseVente.setText(table.getModel().getValueAt(row,7).toString());
					garantie.setSelectedItem(0);
			     
			    }
			}
		});
	}
	        
}
