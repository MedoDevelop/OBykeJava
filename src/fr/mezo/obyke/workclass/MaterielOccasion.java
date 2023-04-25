package fr.mezo.obyke.workclass;

import java.sql.SQLException;

import fr.mezo.obyke.data.BD;
import fr.mezo.obyke.data.DateSimp;

public class MaterielOccasion extends Materiel {

	public MaterielOccasion(int id,String coloris,double prixVente,DateSimp dateMisVente,String categ,DateSimp dateVendus,DateSimp dateAchat,double prixAchat, String societe,String annee) {
		super(id,coloris,prixVente,dateMisVente,categ,dateVendus,societe,prixAchat,dateAchat,annee);
	}	
}
