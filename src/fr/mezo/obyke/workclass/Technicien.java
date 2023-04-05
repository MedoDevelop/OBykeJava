package fr.mezo.obyke.workclass;

import java.sql.SQLException;

import fr.mezo.obyke.data.BD;

public class Technicien {
	private int id;
	private String nom;
	private String prenom;
	private String mail;
	private String telephone;
	private String mdp;
	
	public Technicien(int id,String nom,String prenom,String mail,String telephone,String mdp) {
		this.setId(id);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setMail(mail);
		this.setTelephone(telephone);
		this.setMdp(mdp);
	}
	
	public int getId() {
		return id;
	}
	
	private void setId(int id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String NVnom) {
		this.nom = NVnom;
		try {
			BD.TechnicienData.Set(this.id, NVnom, this.prenom, this.mail, this.telephone, this.mdp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String NVmail) {
		this.mail = NVmail;
		try {
			BD.TechnicienData.Set(this.id, this.nom, this.prenom, NVmail, this.telephone, this.mdp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String NVprenom) {
		this.prenom = NVprenom;
		try {
			BD.TechnicienData.Set(this.id, this.nom, NVprenom, this.mail, this.telephone, this.mdp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getTelephone() {
		return telephone;
	}
	
	public void setTelephone(String NVtelephone) {
		this.telephone = NVtelephone;
		try {
			BD.TechnicienData.Set(this.id, this.nom, this.prenom, this.mail, NVtelephone, this.mdp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getMdp() {
		return mdp;
	}
	
	public void setMdp(String NVmdp) {
		this.mdp = NVmdp;
		try {
			BD.TechnicienData.Set(this.id, this.nom, this.prenom, this.mail, this.telephone, NVmdp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
