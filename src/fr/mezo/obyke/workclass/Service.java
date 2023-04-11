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
		try {
			BD.ServiceData.Set(this.idService,this.type, this.denomination, this.nomDir, this.prenomDir, telephone, this.mail, this.categ, this.marque, this.fournisseur, this.dateAchat, this.dateDepot);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
		try {
			BD.ServiceData.Set(this.idService,type, this.denomination, this.nomDir, this.prenomDir, this.telephone, this.mail, this.categ, this.marque, this.fournisseur, this.dateAchat, this.dateDepot);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getDenomination() {
		return denomination;
	}
	public void setDenomination(String denomination) {
		this.denomination = denomination;
		try {
			BD.ServiceData.Set(this.idService,this.type, denomination, this.nomDir, this.prenomDir, this.telephone, this.mail, this.categ, this.marque, this.fournisseur, this.dateAchat, this.dateDepot);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getPrenomDir() {
		return prenomDir;
	}
	public void setPrenomDir(String prenomDir) {
		this.prenomDir = prenomDir;
		try {
			BD.ServiceData.Set(this.idService,this.type, this.denomination, this.nomDir, prenomDir, this.telephone, this.mail, this.categ, this.marque, this.fournisseur, this.dateAchat, this.dateDepot);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getNomDir() {
		return nomDir;
	}
	public void setNomDir(String nomDir) {
		this.nomDir = nomDir;
		try {
			BD.ServiceData.Set(this.idService,this.type, this.denomination, nomDir, this.prenomDir, this.telephone, this.mail, this.categ, this.marque, this.fournisseur, this.dateAchat, this.dateDepot);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
		try {
			BD.ServiceData.Set(this.idService,this.type, this.denomination, this.nomDir, this.prenomDir, this.telephone, mail, this.categ, this.marque, this.fournisseur, this.dateAchat, this.dateDepot);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getCateg() {
		return categ;
	}
	public void setCateg(String categ) {
		this.categ = categ;
		try {
			BD.ServiceData.Set(this.idService,this.type, this.denomination, this.nomDir, this.prenomDir, this.telephone, this.mail, categ, this.marque, this.fournisseur, this.dateAchat, this.dateDepot);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
		try {
			BD.ServiceData.Set(this.idService,this.type, this.denomination, this.nomDir, this.prenomDir, this.telephone, this.mail, this.categ, marque, this.fournisseur, this.dateAchat, this.dateDepot);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getFournisseur() {
		return fournisseur;
	}
	public void setFournisseur(String fournisseur) {
		this.fournisseur = fournisseur;
		try {
			BD.ServiceData.Set(this.idService,this.type, this.denomination, this.nomDir, this.prenomDir, this.telephone, this.mail, this.categ, this.marque, fournisseur, this.dateAchat, this.dateDepot);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public DateSimp getDateAchat() {
		return dateAchat;
	}
	public void setDateAchat(DateSimp dateAchat) {
		this.dateAchat = dateAchat;
		try {
			BD.ServiceData.Set(this.idService,this.type, this.denomination, this.nomDir, this.prenomDir, this.telephone, this.mail, this.categ, this.marque, this.fournisseur, dateAchat, this.dateDepot);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public DateSimp getDateDepot() {
		return dateDepot;
	}
	public void setDateDepot(DateSimp dateDepot) {
		this.dateDepot = dateDepot;
		try {
			BD.ServiceData.Set(this.idService,this.type, this.denomination, this.nomDir, this.prenomDir, this.telephone, this.mail, this.categ, this.marque, this.fournisseur, this.dateAchat, dateDepot);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
