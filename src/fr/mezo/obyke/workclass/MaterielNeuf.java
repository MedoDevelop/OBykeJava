package fr.mezo.obyke.workclass;

import fr.mezo.obyke.data.DateSimp;

public class MaterielNeuf extends Materiel{
	
	public MaterielNeuf(int id,String coloris,double prixVente,DateSimp dateMisVente,String categ,DateSimp dateVendus,String societe,double prixAchat,DateSimp dateAchat,String annee) {
		super(id,coloris,prixVente,dateMisVente,categ,dateVendus,societe,prixAchat,dateAchat,annee);
	}

}
