package fr.mezo.obyke.workclass;

import java.sql.SQLException;

import fr.mezo.obyke.data.BD;
import fr.mezo.obyke.data.DateSimp;

public class LigneCommande {
		
	private int idMateriel;
	private int idCentre;
	private int idGarantie;
	
	private DateSimp dateCommande;
	
	public LigneCommande(int materielID,int centreID,int garantieID,DateSimp dateCommande) {
		this.setIdMateriel(materielID);
		this.setIdCentre(centreID);
		this.setIdGarantie(garantieID);
		this.setDateCommande(dateCommande);
	}

	public int getIdMateriel() {
		return idMateriel;
	}

	private void setIdMateriel(int idMateriel) {
		this.idMateriel = idMateriel;
	}

	public int getIdCentre() {
		return this.idCentre;
	}

	private void setIdCentre(int idCentre) {
		this.idCentre = idCentre;
	}

	public int getIdGarantie() {
		return this.idGarantie;
	}

	private void setIdGarantie(int idGarantie) {
		this.idGarantie = idGarantie;
	}

	public DateSimp getDateCommande() {
		return this.dateCommande;
	}

	private void setDateCommande(DateSimp NVdateCommande) {
		this.dateCommande = NVdateCommande;
		try {
			BD.LigneCommandeData.Set(this.idMateriel, NVdateCommande, this.idCentre, this.idGarantie);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Centre getLeCentre() {
		try {
			return BD.CentreData.Get(this.idCentre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public Garantie getLaGarantie() {
		try {
			return BD.GarantieData.Get(this.idGarantie);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public Materiel getLeMateriel() {
		try {
			Materiel leMateriel = null;
			leMateriel = BD.MaterielData.MaterielNeufData.Get(this.idMateriel);
			if(leMateriel == null) {
				leMateriel = BD.MaterielData.MaterielOccasionData.Get(this.idMateriel);
			}
			return leMateriel;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
