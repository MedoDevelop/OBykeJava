package fr.mezo.obyke.workclass;

import java.sql.SQLException;

import fr.mezo.obyke.data.BD;
import fr.mezo.obyke.data.DateSimp;

public class MaterielOccasion extends Materiel {
	
	private DateSimp dateAchat;
	private double prixAchat;
	
	private MaterielOccasion(int id,String coloris,double prixVente,DateSimp dateMisVente,String categ,DateSimp dateVendus,DateSimp dateAchat,double prixAchat) {
		super(id,coloris,prixVente,dateMisVente,categ,dateVendus);
		this.setDateAchat(dateAchat);
		this.setPrixAchat(prixAchat);
	}

	public DateSimp getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(DateSimp dateAchat) {
		this.dateAchat = dateAchat;
		try {
			BD.MaterielData.MaterielOccasionData.Set(this.getId(), this.dateAchat, this.prixAchat);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public double getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(double prixAchat) {
		this.prixAchat = prixAchat;
	}
		
}
