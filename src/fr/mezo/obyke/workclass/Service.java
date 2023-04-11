package fr.mezo.obyke.workclass;

import java.sql.SQLException;

import fr.mezo.obyke.data.BD;

public class Service {
	private int idService;
	private String libelle;
	private double prix;
	
	public Service(int id,String lib,double prix) {
		this.setIdService(id);
		this.setLibelle(lib);
		this.setPrix(prix);
	}

	public int getIdService() {
		return idService;
	}

	private void setIdService(int idService) {
		this.idService = idService;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String NVlibelle) {
		this.libelle = NVlibelle;
		try {
			BD.ServiceData.Set(this.idService, NVlibelle, this.prix);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double NVprix) {
		this.prix = NVprix;
		try {
			BD.ServiceData.Set(this.idService, this.libelle, NVprix);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
