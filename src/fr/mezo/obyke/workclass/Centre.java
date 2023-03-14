package fr.mezo.obyke.workclass;

public class Centre {
	
	private int idCentre;
	private String denomination;
	private String nomDir;
	private String prenomDir;
	private String telephone;
	private String mail;
	private String typeCentre;
	private String login;
	private String mdp;
	
	public Centre(int id, String deno,String nomdir,String prenomdir, String tele,String mail, String type,String login,String mdp) {
		this.idCentre = id;
		this.denomination = deno;
		this.nomDir = nomdir;
		this.prenomDir = prenomdir;
		this.telephone = tele;
		this.mail = mail;
		this.typeCentre = type;
		this.login = login;
		this.mdp = mdp;
	}
	
	//Getters
	
	
	//Setters
	
	

}
