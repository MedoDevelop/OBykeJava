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
	private String societe;
	private String annee;
	
	private DateSimp dateAchat;
	private double prixAchat;
	
	public Materiel(int id,String coloris,double prixVente,DateSimp dateMisVente,String categ,DateSimp dateVendus,String societe,double prixAchat,DateSimp dateAchat,String annee) {
		this.setId(id);
		this.setColoris(coloris);
		this.setPrixVente(prixVente);
		this.setDateMisVente(dateMisVente);
		this.setCateg(categ);
		this.setAnnee(annee);
		this.setDateVendus(dateVendus);
		this.setSociete(societe);
		this.setDateAchat(dateAchat);
		this.setPrixAchat(prixAchat);
	}
	
	public String toString() {
		return "Id "+this.id+" Categorie : "+this.categ+" Annee: "+this.annee;
	}

	public int getId() {
		return this.id;
	}

	private void setId(int id) {
		this.id = id;
	}

	public String getColoris() {
		return this.coloris;
	}

	public void setColoris(String NVcoloris) {
		this.coloris = NVcoloris;
	}

	public double getPrixVente() {
		return this.prixVente;
	}

	public void setPrixVente(double NVprixVente) {
		this.prixVente = NVprixVente;
	}

	public DateSimp getDateMisVente() {
		return this.dateMisVente;
	}

	public void setDateMisVente(DateSimp NVdateMisVente) {
		this.dateMisVente = NVdateMisVente;
	}

	public String getCateg() {
		return this.categ;
	}

	public void setCateg(String NVcateg) {
		this.categ = NVcateg;
	}

	public DateSimp getDateVendus() {
		return this.dateVendus;
	}

	public void setDateVendus(DateSimp NVdateVendus) {
		this.dateVendus = NVdateVendus;
	}

	public String getSociete() {
		return societe;
	}

	public void setSociete(String NVsociete) {
		this.societe = NVsociete;
	}

	public String getAnnee() {
		return this.annee;
	}

	public void setAnnee(String NVannee) {
		this.annee = NVannee;
	}

	public double getPrixAchat() {
		return this.prixAchat;
	}

	public void setPrixAchat(double prixAchat) {
		this.prixAchat = prixAchat;
	}
	
	public DateSimp getDateAchat() {
		return this.dateAchat;
	}

	public void setDateAchat(DateSimp dateAchat) {
		this.dateAchat = dateAchat;
	}	
}
