package fr.mezo.obyke.workclass;

import java.sql.SQLException;

import fr.mezo.obyke.data.BD;

public class Garantie {
	private int id;
	private String libelle;
	private double prix;
	private int duree;
	
	public Garantie(int id,String libelle,double prix,int duree) {
		this.setId(id);
		this.setLibelle(libelle);
		this.setPrix(prix);
		this.setDuree(duree);
	}

	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String NVlibelle) {
		this.libelle = NVlibelle;
		try {
			BD.GarantieData.Set(this.id, NVlibelle, this.prix, this.duree);
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
			BD.GarantieData.Set(this.id, this.libelle, NVprix, this.duree);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int NVduree) {
		this.duree = NVduree;
		try {
			BD.GarantieData.Set(this.id, this.libelle, this.prix, NVduree);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
