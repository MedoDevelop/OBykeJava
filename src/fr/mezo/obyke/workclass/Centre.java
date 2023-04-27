package fr.mezo.obyke.workclass;

import java.sql.SQLException;

import fr.mezo.obyke.data.BD;

public class Centre {
	
	private int idCentre;
	private String denomination;
	private String nomDir;
	private String prenomDir;
	private String telephone;
	private String mail;
	private String typeCentre;
	private String mdp;
	
	public Centre(int id, String deno,String nomdir,String prenomdir, String tel,String mail, String type, String mdp) {
		this.setIdCentre(id);
		this.setDenomination(deno);
		this.setNomDir(nomdir);
		this.setPrenomDir(prenomdir);
		this.setTelephone(tel);
		this.setMail(mail);
		this.setTypeCentre(type);
		this.setMdp(mdp);
	}

	public int getIdCentre() {
		return idCentre;
	}
	
	public String toString() {
		return this.typeCentre+" "+this.denomination;
	}

	private void setIdCentre(int idCentre) {
		this.idCentre = idCentre;
	}

	public String getDenomination() {
		return denomination;
	}

	public void setDenomination(String NVdenomination) {
		this.denomination = NVdenomination;
		try {
			BD.CentreData.Set(this.idCentre, NVdenomination, this.nomDir,this.prenomDir,this.telephone,this.mail,this.typeCentre,this.mdp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getNomDir() {
		return nomDir;
	}

	public void setNomDir(String NVnomDir) {
		this.nomDir = NVnomDir;
		try {
			BD.CentreData.Set(this.idCentre, this.denomination, NVnomDir,this.prenomDir,this.telephone,this.mail,this.typeCentre,this.mdp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getPrenomDir() {
		return prenomDir;
	}

	public void setPrenomDir(String NVprenomDir) {
		this.prenomDir = NVprenomDir;
		try {
			BD.CentreData.Set(this.idCentre, this.denomination, this.nomDir,NVprenomDir,this.telephone,this.mail,this.typeCentre,this.mdp);
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
			BD.CentreData.Set(this.idCentre, this.denomination, this.nomDir,this.prenomDir,this.telephone,NVmail,this.typeCentre,this.mdp);
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
			BD.CentreData.Set(this.idCentre, this.denomination, this.nomDir,this.prenomDir,NVtelephone,this.mail,this.typeCentre,this.mdp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getTypeCentre() {
		return typeCentre;
	}

	public void setTypeCentre(String NVtypeCentre) {
		this.typeCentre = NVtypeCentre;
		try {
			BD.CentreData.Set(this.idCentre, this.denomination, this.nomDir,this.prenomDir,this.telephone,this.mail,NVtypeCentre,this.mdp);
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
			BD.CentreData.Set(this.idCentre, this.denomination, this.nomDir,this.prenomDir,this.telephone,this.mail,this.typeCentre,NVmdp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
