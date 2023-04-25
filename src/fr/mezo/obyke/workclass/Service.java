package fr.mezo.obyke.workclass;

import java.sql.SQLException;

import fr.mezo.obyke.data.BD;
import fr.mezo.obyke.data.DateSimp;

public class Service {
	private int idService;
	private String type;
	private String denomination;
	private String nomDir;
	private String prenomDir;
	private String telephone;
	private String mail;
	private String categ;
	private String marque;
	private String fournisseur;
	private DateSimp dateAchat;
	private DateSimp dateDepot;
	
	public Service(int id,String type,String denomination,String nomDir,String prenomDir,String telephone,String mail,String categ,String marque,String fournisseur, DateSimp dateAchat,DateSimp dateDepot) {
		 this.setIdService(id);
		 this.setType(type);
		 this.setDenomination(denomination);
		 this.setNomDir(nomDir);
		 this.setPrenomDir(prenomDir);
		 this.setTelephone(telephone);
		 this.setMail(mail);
		 this.setCateg(categ);
		 this.setMarque(marque);
		 this.setFournisseur(fournisseur);
		 this.setDateAchat(dateAchat);
		 this.setDateDepot(dateDepot);
	}
	
	public int getIdService() {
		return idService;
	}
	private void setIdService(int idService) {
		this.idService = idService;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDenomination() {
		return denomination;
	}
	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}
	public String getPrenomDir() {
		return prenomDir;
	}
	public void setPrenomDir(String prenomDir) {
		this.prenomDir = prenomDir;
	}
	public String getNomDir() {
		return nomDir;
	}
	public void setNomDir(String nomDir) {
		this.nomDir = nomDir;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getCateg() {
		return categ;
	}
	public void setCateg(String categ) {
		this.categ = categ;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public String getFournisseur() {
		return fournisseur;
	}
	public void setFournisseur(String fournisseur) {
		this.fournisseur = fournisseur;
	}
	public DateSimp getDateAchat() {
		return dateAchat;
	}
	public void setDateAchat(DateSimp dateAchat) {
		this.dateAchat = dateAchat;
	}
	public DateSimp getDateDepot() {
		return dateDepot;
	}
	public void setDateDepot(DateSimp dateDepot) {
		this.dateDepot = dateDepot;
	
	}
	
	
	
}
