package fr.mezo.obyke.workclass;

public class CommandeService {
	private int idComServ;
	private long dateDemande;
	private long datePrestation;
	private boolean valide;
	private int idService;
	private int idTech;
	private int idCentre;
	
	public CommandeService(int id, long dateDemande, long datePrestation, boolean valide,int idService, int idTech,int idCentre) {
		this.idComServ = id;
		this.dateDemande = dateDemande;
		this.datePrestation = datePrestation;
		this.valide = valide;
		this.idService = idService;
		this.idTech = idTech;
		this.idCentre = idCentre;
	}
	
	//Getters
	
	//Setters
	
}
