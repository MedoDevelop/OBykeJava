package fr.mezo.obyke.graphics;

import java.awt.Dimension;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;

import fr.mezo.obyke.data.BD;
import fr.mezo.obyke.workclass.Centre;
import fr.mezo.obyke.workclass.Garantie;

public class MenuConsultationVenteMatNeuf extends MenuConsultationDroit {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MenuConsultationVenteMatNeuf(int lineLeft,int lineRight) {
		super(lineLeft,lineRight);
		
		//Mise en place formulaire
		JButton button1= new JButton("Modifier");
		JButton button2= new JButton("Supprimer");
		
		//button1.addActionListener((e) -> EditConfirmation());
		//button2.addActionListener((e) -> DeleteConfirmation());	
		
		ArrayList<Centre> centre = new ArrayList<Centre>();
		try {
			centre = BD.CentreData.GetAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Centre[] tab = new Centre[centre.size()];
		for(int i=0;i<centre.size();i++) {
			tab[i] = centre.get(i);
		}
		JComboBox<Centre> centreSel= new JComboBox<Centre>(tab);
		centreSel.setPreferredSize(new Dimension(162,28));
		
		
		JComboBox<Garantie> garantie=new JComboBox();
		garantie.setPreferredSize(new Dimension(162,28));
		

		this.addTop(new InputField("Centre : ",centreSel));
		
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
		
	}
	
	

}
