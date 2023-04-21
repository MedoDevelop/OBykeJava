package fr.mezo.obyke.workclass;

import java.sql.SQLException;

import fr.mezo.obyke.data.BD;

public class PropositionAchat {

	private int id;
	private String anneeFabrication;
	private String categ;
	private String coloris;
	private int idTech;
	private int idCentre;
	
	public PropositionAchat(int id,String anneeFab,String categ,String coloris,int idTech,int idCentre){
		this.setId(id);
		this.setAnneeFabrication(anneeFab);
		this.setCateg(categ);
		this.setColoris(coloris);
		this.setIdTech(idTech);
		this.setIdCentre(idCentre);
	}

	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	public String getAnneeFabrication() {
		return anneeFabrication;
	}

	public void setAnneeFabrication(String NVanneeFabrication) {
		this.anneeFabrication = NVanneeFabrication;
		try {
			BD.PropositionAchatData.Set(this.id, NVanneeFabrication, this.categ, this.coloris, this.idTech, this.idCentre);
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
			BD.PropositionAchatData.Set(this.id, this.anneeFabrication, NVcateg, this.coloris, this.idTech, this.idCentre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getColoris() {
		return coloris;
	}

	public void setColoris(String NVcoloris) {
		this.coloris = NVcoloris;
		try {
			BD.PropositionAchatData.Set(this.id, this.anneeFabrication, this.categ, NVcoloris, this.idTech, this.idCentre);
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

	public int getIdTech() {
		return idTech;
	}

	public void setIdTech(int NVidTech) {
		this.idTech = NVidTech;
		try {
			BD.PropositionAchatData.Set(this.id, this.anneeFabrication, this.categ, this.coloris, NVidTech, this.idCentre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Technicien getLeTechnicien() {
		try {
			return BD.TechnicienData.Get(this.idTech);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public Centre getLeCentre() {
		try {
			return BD.CentreData.Get(this.idCentre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}
