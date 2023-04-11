package fr.mezo.obyke.workclass;

import java.sql.SQLException;

import fr.mezo.obyke.data.BD;
import fr.mezo.obyke.data.DateSimp;

public abstract class Materiel {
	private int id;
	private String coloris;
	private double prixVente;
	private DateSimp dateMisVente;
	private String categ;
	private DateSimp dateVendus;
	
	public Materiel(int id,String coloris,double prixVente,DateSimp dateMisVente,String categ,DateSimp dateVendus) {
		this.setId(id);
		this.setColoris(coloris);
		this.setPrixVente(prixVente);
		this.setDateMisVente(dateMisVente);
		this.setCateg(categ);
		this.setDateVendus(dateVendus);
	}

	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	public String getColoris() {
		return coloris;
	}

	public void setColoris(String NVcoloris) {
		this.coloris = NVcoloris;
		try {
			BD.MaterielData.Set(this.id,NVcoloris, this.prixVente, this.dateMisVente, this.categ, this.dateVendus);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public double getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(double NVprixVente) {
		this.prixVente = NVprixVente;
		try {
			BD.MaterielData.Set(this.id,this.coloris, NVprixVente, this.dateMisVente, this.categ, this.dateVendus);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public DateSimp getDateMisVente() {
		return dateMisVente;
	}

	public void setDateMisVente(DateSimp NVdateMisVente) {
		this.dateMisVente = NVdateMisVente;
		try {
			BD.MaterielData.Set(this.id,this.coloris, this.prixVente, NVdateMisVente, this.categ, this.dateVendus);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getCateg() {
		return categ;
	}

	public void setCateg(String NVcateg) {
		this.categ = NVcateg;
		try {
			BD.MaterielData.Set(this.id,this.coloris, this.prixVente, this.dateMisVente, NVcateg, this.dateVendus);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public DateSimp getDateVendus() {
		return dateVendus;
	}

	public void setDateVendus(DateSimp NVdateVendus) {
		this.dateVendus = NVdateVendus;
		try {
			BD.MaterielData.Set(this.id,this.coloris, this.prixVente, this.dateMisVente, this.categ, NVdateVendus);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
