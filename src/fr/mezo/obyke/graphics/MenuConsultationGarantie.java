package fr.mezo.obyke.graphics;

import java.awt.Dimension;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import fr.mezo.obyke.data.BD;
import fr.mezo.obyke.workclass.Garantie;

public class MenuConsultationGarantie extends MenuConsultationDroit {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MenuConsultationGarantie(int lineLeft,int lineRight) {
		super(lineLeft,lineRight);
		
		//Mise en place formulaire
		JButton button1= new JButton("Modifier");
		JButton button2= new JButton("Supprimer");
		
		//button1.addActionListener((e) -> EditConfirmation());
		//button2.addActionListener((e) -> DeleteConfirmation());
		
		//Mise ajour de la table 
		try {
			this.setTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Fin mise ajour table
		
		this.addTopSpace();
		
		JTextField lib=new JTextField(20);
		JTextField prix=new JTextField(20);
		JTextField duree=new JTextField(20);
	
		this.addLeft(new InputField("Libellé de la garantie : ",lib));
		this.addLeft(new InputField("Prix de la garantie : ",prix));
		this.addLeft(new InputField("Duréee de la garantie : ",duree));
		
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
