package fr.mezo.obyke.workclass;

import fr.mezo.obyke.data.DateSimp;

public class Rdv {
	
	private int id;
	private String denomination;
	private String nomDir;
	private String prenomDir;
	private String telepone;
	private String mail;
	private DateSimp dateRdv;
	private String heureRdv;
	private String motif;
	private int techId;
	
	public Rdv(int id,String deno,String nomDir,String prenomDir,String telephone,String mail,DateSimp dateRdv,String heureRdv,String motif,int idtech) {
		this.setId(id);
		this.setDenomination(deno);
		this.setNomDir(nomDir);
		this.setPrenomDir(prenomDir);
		this.setTelepone(telephone);
		this.setMail(mail);
		this.setDateRdv(dateRdv);
		this.setHeureRdv(heureRdv);
		this.setMotif(motif);
		this.setTechId(idtech);
	}

	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	public String getDenomination() {
		return denomination;
	}

	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}

	public String getNomDir() {
		return nomDir;
	}

	public void setNomDir(String nomDir) {
		this.nomDir = nomDir;
	}

	public String getPrenomDir() {
		return prenomDir;
	}

	public void setPrenomDir(String prenomDir) {
		this.prenomDir = prenomDir;
	}

	public String getTelepone() {
		return telepone;
	}

	public void setTelepone(String telepone) {
		this.telepone = telepone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public DateSimp getDateRdv() {
		return dateRdv;
	}

	public void setDateRdv(DateSimp dateRdv) {
		this.dateRdv = dateRdv;
	}

	public String getHeureRdv() {
		return heureRdv;
	}

	public void setHeureRdv(String heureRdv) {
		this.heureRdv = heureRdv;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public int getTechId() {
		return techId;
	}

	private void setTechId(int techId) {
		this.techId = techId;
	}

}
