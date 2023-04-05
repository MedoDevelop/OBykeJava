package fr.mezo.obyke.workclass;

import java.sql.SQLException;

import fr.mezo.obyke.data.BD;
import fr.mezo.obyke.data.DateSimp;

public class CommandeService {
	private int idComServ;
	private DateSimp dateDemande;
	private DateSimp datePrestation;
	private boolean valide;
	
	private int idService;
	private int idTech;
	private int idCentre;
	
	public CommandeService(int id, DateSimp dateDemande, DateSimp datePrestation, boolean valide,int idService, int idTech,int idCentre) {
		this.setIdComServ(id);
		this.setDateDemande(dateDemande);
		this.setDatePrestation(datePrestation);
		this.setValide(valide);
		this.setIdService(idService);
		this.setIdTech(idTech);
		this.setIdCentre(idCentre);
	}


	public DateSimp getDateDemande() {
		return dateDemande;
	}

	private void setDateDemande(DateSimp NVdateDemande) {
		this.dateDemande = NVdateDemande;
		try {
			BD.CommandeServiceData.Set(this.idComServ, NVdateDemande, this.datePrestation, this.valide, this.idService, this.idTech, this.idCentre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public DateSimp getDatePrestation() {
		return datePrestation;
	}

	public void setDatePrestation(DateSimp NVdatePrestation) {
		this.datePrestation = NVdatePrestation;
		try {
			BD.CommandeServiceData.Set(this.idComServ, this.dateDemande, NVdatePrestation, this.valide, this.idService, this.idTech, this.idCentre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isValide() {
		return valide;
	}

	public void setValide(boolean NVvalide) {
		this.valide = NVvalide;
		try {
			BD.CommandeServiceData.Set(this.idComServ, this.dateDemande, this.datePrestation, NVvalide, this.idService, this.idTech, this.idCentre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getIdTech() {
		return idTech;
	}

	public void setIdTech(int NVidTech) {
		this.idTech = NVidTech;
		try {
			BD.CommandeServiceData.Set(this.idComServ, this.dateDemande, this.datePrestation, this.valide, this.idService, NVidTech, this.idCentre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getIdService() {
		return idService;
	}

	public void setIdService(int NVidService) {
		this.idService = NVidService;
		try {
			BD.CommandeServiceData.Set(this.idComServ, this.dateDemande, this.datePrestation, this.valide, NVidService, this.idTech, this.idCentre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getIdCentre() {
		return idCentre;
	}

	private void setIdCentre(int idCentre) {
		this.idCentre = idCentre;
	}


	public int getIdComServ() {
		return idComServ;
	}


	private void setIdComServ(int idComServ) {
		this.idComServ = idComServ;
	}	
}
