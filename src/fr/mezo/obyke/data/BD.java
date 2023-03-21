package fr.mezo.obyke.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class BD {
	private static Connection c = null;
	
	
	
	public static void init() {
		//Il faut initiliser les tables si elles n'existe pas
		try {
	         Class.forName("org.sqlite.JDBC");
	         BD.c = DriverManager.getConnection("jdbc:sqlite:OByke.db");
	         BD.c.setAutoCommit(true);
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Opened database successfully");
		if(BD.c != null) {
			String req = ""
				+ "CREATE TABLE IF NOT EXISTS Materiel(\r\n"
				+ "   idMateriel INTEGER,\r\n"
				+ "   coloris TEXT,\r\n"
				+ "   prixVente REAL,\r\n"
				+ "   dateMisVente REAL,\r\n"
				+ "   categ TEXT,\r\n"
				+ "   dateVendus REAL,\r\n"
				+ "   PRIMARY KEY(idMateriel)\r\n"
				+ ");\r\n"
				+ "\r\n"
				+ "CREATE TABLE IF NOT EXISTS Centre(\r\n"
				+ "   idCentre INTEGER,\r\n"
				+ "   denomination TEXT,\r\n"
				+ "   nomDir TEXT,\r\n"
				+ "   prenomDir TEXT,\r\n"
				+ "   telephone TEXT,\r\n"
				+ "   mail TEXT,\r\n"
				+ "   typeCentre TEXT,\r\n"
				+ "   login TEXT,\r\n"
				+ "   mdp TEXT,\r\n"
				+ "   PRIMARY KEY(idCentre)\r\n"
				+ ");\r\n"
				+ "\r\n"
				+ "CREATE TABLE IF NOT EXISTS MaterielOccasion(\r\n"
				+ "   idMateriel INTEGER,\r\n"
				+ "   dateAchat REAL,\r\n"
				+ "   prixAchat REAL,\r\n"
				+ "   PRIMARY KEY(idMateriel),\r\n"
				+ "   FOREIGN KEY(idMateriel) REFERENCES MATERIEL(idMateriel)\r\n"
				+ ");\r\n"
				+ "\r\n"
				+ "CREATE TABLE IF NOT EXISTS MaterielNeuf(\r\n"
				+ "   idMateriel INTEGER,\r\n"
				+ "   PRIMARY KEY(idMateriel),\r\n"
				+ "   FOREIGN KEY(idMateriel) REFERENCES MATERIEL(idMateriel)\r\n"
				+ ");\r\n"
				+ "\r\n"
				+ "CREATE TABLE IF NOT EXISTS Technicien(\r\n"
				+ "   idTech INTEGER,\r\n"
				+ "   nom TEXT,\r\n"
				+ "   prenom TEXT,\r\n"
				+ "   mail TEXT,\r\n"
				+ "   telephone TEXT,\r\n"
				+ "   login TEXT,\r\n"
				+ "   mdp TEXT,\r\n"
				+ "   PRIMARY KEY(idTech)\r\n"
				+ ");\r\n"
				+ "\r\n"
				+ "CREATE TABLE IF NOT EXISTS Garantie(\r\n"
				+ "   idGarantie INTEGER,\r\n"
				+ "   libelle TEXT,\r\n"
				+ "   prix INTEGER,\r\n"
				+ "   duree INTEGER,\r\n"
				+ "   PRIMARY KEY(idGarantie)\r\n"
				+ ");\r\n"
				+ "\r\n"
				+ "CREATE TABLE IF NOT EXISTS Service(\r\n"
				+ "   idService INTEGER,\r\n"
				+ "   libelle TEXT,\r\n"
				+ "   prix REAL,\r\n"
				+ "   PRIMARY KEY(idService)\r\n"
				+ ");\r\n"
				+ "\r\n"
				+ "CREATE TABLE IF NOT EXISTS PropositionAchat(\r\n"
				+ "   idProposition INTEGER,\r\n"
				+ "   anneeFabrication TEXT,\r\n"
				+ "   categ TEXT,\r\n"
				+ "   coloris TEXT,\r\n"
				+ "   idTech INTEGER NOT NULL,\r\n"
				+ "   idCentre INTEGER NOT NULL,\r\n"
				+ "   PRIMARY KEY(idProposition),\r\n"
				+ "   FOREIGN KEY(idTech) REFERENCES Technicien(idTech),\r\n"
				+ "   FOREIGN KEY(idCentre) REFERENCES Centre(idCentre)\r\n"
				+ ");\r\n"
				+ "\r\n"
				+ "CREATE TABLE IF NOT EXISTS LigneComande(\r\n"
				+ "   idMateriel INTEGER,\r\n"
				+ "   idCommande INTEGER,\r\n"
				+ "   idCentre INTEGER NOT NULL,\r\n"
				+ "   idGarantie INTEGER NOT NULL,\r\n"
				+ "   PRIMARY KEY(idMateriel, idCommande),\r\n"
				+ "   FOREIGN KEY(idMateriel) REFERENCES MATERIEL(idMateriel),\r\n"
				+ "   FOREIGN KEY(idCentre) REFERENCES Centre(idCentre),\r\n"
				+ "   FOREIGN KEY(idGarantie) REFERENCES Garantie(idGarantie)\r\n"
				+ ");\r\n"
				+ "\r\n"
				+ "CREATE TABLE IF NOT EXISTS CommandeService(\r\n"
				+ "   idComServ INTEGER,\r\n"
				+ "   dateDemande REAL,\r\n"
				+ "   datePrestation REAL,\r\n"
				+ "   valide NUMERIC,\r\n"
				+ "   idService INTEGER NOT NULL,\r\n"
				+ "   idTech INTEGER NOT NULL,\r\n"
				+ "   idCentre INTEGER NOT NULL,\r\n"
				+ "   PRIMARY KEY(idComServ),\r\n"
				+ "   FOREIGN KEY(idService) REFERENCES Service(idService),\r\n"
				+ "   FOREIGN KEY(idTech) REFERENCES Technicien(idTech),\r\n"
				+ "   FOREIGN KEY(idCentre) REFERENCES Centre(idCentre)\r\n"
				+ ");\r\n"
				+ "";
			
			try {
				Statement stmt = BD.c.createStatement();
				stmt.execute(req);
			}catch (Exception e) {
				// TODO: handle exception
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		        System.exit(0);
			}
			
		}
	}
	
	public static abstract class Materiel{
		
		public static abstract class MaterielNeufData{
			/*
			 * recuperer tout les materiel neuf
			 * recuperer un materiel neuf precis
			 * 
			 * ajouter un materiel neuf
			 * 
			 * mettre a jour un materiel neuf
			 * 
			 * suprimmer un materiel neuf 
			 */
			
			public fr.mezo.obyke.workclass.MaterielNeuf[] getAll() {
				fr.mezo.obyke.workclass.MaterielNeuf [] res =;
				
				
				
				return res;
			}
		}
		
		public static abstract class MaterielOccasionData{
			/*
			 * recuperer tout les materiel occasion
			 * recuperer un materiel occasion precis
			 * 
			 * ajouter un materiel occasion
			 * 
			 * mettre a jour un materiel occasion
			 * 
			 * suprimmer un materiel occasion 
			 */
		}
	}
	
	public static abstract class CentreData{
		/*
		 * récuperer tout les Centre
		 * récuperer tout les Centre de type hotel
		 * récuperer tout les Centre de type instituts
		 * récuperer tout les Centre de type licentié
		 * recuperer les types Centre possible [Hotel,Institut,Licencié]
		 * recuperer un centre precis
		 * recuperer un centre par login et mdp
		 * 
		 * Ajouter un centre
		 * 
		 * Mettre ajour les infos d'un centre
		 * 
		 * supprimer un centre
		 */
		
	}
	
	public static abstract class CommandeServiceData{
		/*
		 * récupérer les commande service en attente (qui non pas de technicien)
		 * recupérer les commande service en attente d'un technitien (qui on un technicien precis mais valide=false)
		 * reucpérer une comande service précis
		 * 
		 * Ajouter une commande service
		 * 
		 * Mettre a jour les infos d'un commande service
		 * 
		 * supprimer une commandeService
		 */
	}
	
	public static abstract class TechnicienData{
		/*
		 * recuperer tout les Technicien
		 * recuperer un Technicien précis
		 * recuperer un Technicien par login et mdp
		 * 
		 * Ajouter un technicien
		 * 
		 * Mettre a jour un technicien
		 * 
		 * supprimer un technicien
		 */
	}
	
	public static abstract class GarantieData{
		/*
		 * recuper toute les Garantie
		 * recuper une Garantie precise
		 * 
		 * Ajouter un Garantie
		 *
		 * Mettre a jour
		 * 
		 * supprimer une garantie
		 */
	}
	
	public static abstract class ServiceData{
		/*
		 * Recuper tout les Service
		 * Recuper un service precis
		 * 
		 * Ajouter un service
		 * 
		 * Mettre a jour un service
		 * 
		 * Supprimer un service
		 */
	}
	
	public static abstract class PropositionAchat{
		/*
		 * Recuperer toute les propositionAchat
		 * Recuperer une proposition achat precis
		 * 
		 * Ajouter une propositioon achat
		 * 
		 * Mettre a jour une proposition achat
		 * 
		 * Supprimer une porposition Achat
		 */
		
	}
	
	public static abstract class LigneCommande{
		/*
		 * Recuperer toute les lignes commandes
		 * Recuperer une ligne commandes precis
		 * 
		 * Ajouter une ligne comamande
		 * 
		 * Mettre a jour une ligne commande
		 * 
		 * Supprimer une ligne commande 
		 */
	}
	
	public ResultSet resultREQ(PreparedStatement pstmt) {
		ResultSet res = null;
		try {
			res = pstmt.executeQuery();
			//pstmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public ResultSet resultREQ(String myQuery) {//requete ou l'on attend un je de résutltat
		ResultSet res = null;
		Statement stmt;
		try {
			stmt = c.createStatement();
			res = stmt.executeQuery(myQuery); //on récupère un jeu de résultat de type ResulSet;
			//La requete execute on faire la statement
			//stmt.close(); ne peut être fermé car on a besoin de recupérer le resultat
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static void main(String[] args) {
	      /*// Créer un objet Date avec la date courante
	      Date date = new Date();

	      // Créer un objet Calendar à partir de l'objet Date
	      Calendar cal = Calendar.getInstance();
	      cal.setTime(date);

	      // Récupérer le jour, le mois et l'année
	      int jour = cal.get(Calendar.DAY_OF_MONTH);
	      int mois = cal.get(Calendar.MONTH) + 1;
	      int annee = cal.get(Calendar.YEAR);

	      // Afficher le jour, le mois et l'année
	      System.out.println("Jour : " + jour);
	      System.out.println("Mois : " + mois);
	      System.out.println("Année : " + annee);*/
	}

}
