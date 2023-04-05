package fr.mezo.obyke.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.mezo.obyke.workclass.Centre;
import fr.mezo.obyke.workclass.CommandeService;
import fr.mezo.obyke.workclass.Garantie;
import fr.mezo.obyke.workclass.LigneCommande;
import fr.mezo.obyke.workclass.MaterielNeuf;
import fr.mezo.obyke.workclass.MaterielOccasion;
import fr.mezo.obyke.workclass.PropositionAchat;
import fr.mezo.obyke.workclass.Service;
import fr.mezo.obyke.workclass.Technicien;

public abstract class BD {
	private static Connection c = null;
	
	
	
	public static void init() {
		//Il faut initiliser les tables si elles n'existe pas
		try {
	         Class.forName("org.sqlite.JDBC");
	         BD.c = DriverManager.getConnection("jdbc:sqlite:OByke.db");
	         BD.c.setAutoCommit(false);
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Opened database successfully");
		if(BD.c != null) {
			String req1 = ""
				+ "CREATE TABLE IF NOT EXISTS Materiel(\r\n"
				+ "   idMateriel INTEGER AUTOINCREMENT,\r\n"
				+ "   coloris TEXT,\r\n"
				+ "   prixVente REAL,\r\n"
				+ "   dateMisVente REAL,\r\n"
				+ "   categ TEXT,\r\n"
				+ "   dateVendus REAL,\r\n"
				+ "   PRIMARY KEY(idMateriel)\r\n"
				+ ");\r\n";
			String req2 = "CREATE TABLE IF NOT EXISTS Centre(\r\n"
				+ "   idCentre INTEGER AUTOINCREMENT,\r\n"
				+ "   denomination TEXT,\r\n"
				+ "   nomDir TEXT,\r\n"
				+ "   prenomDir TEXT,\r\n"
				+ "   telephone TEXT,\r\n"
				+ "   mail TEXT,\r\n"
				+ "   typeCentre TEXT,\r\n"
				+ "   mdp TEXT,\r\n"
				+ "   PRIMARY KEY(idCentre)\r\n"
				+ ");\r\n";
			String req3 = "\r\n"
				+ "CREATE TABLE IF NOT EXISTS MaterielOccasion(\r\n"
				+ "   idMateriel INTEGER,\r\n"
				+ "   dateAchat REAL,\r\n"
				+ "   prixAchat REAL,\r\n"
				+ "   PRIMARY KEY(idMateriel),\r\n"
				+ "   FOREIGN KEY(idMateriel) REFERENCES MATERIEL(idMateriel)\r\n"
				+ ");\r\n";
			String req4 = "CREATE TABLE IF NOT EXISTS MaterielNeuf(\r\n"
				+ "   idMateriel INTEGER,\r\n"
				+ "   PRIMARY KEY(idMateriel),\r\n"
				+ "   FOREIGN KEY(idMateriel) REFERENCES MATERIEL(idMateriel)\r\n"
				+ ");\r\n";
			String req5 = "CREATE TABLE IF NOT EXISTS Technicien(\r\n"
				+ "   idTech INTEGER AUTOINCREMENT,\r\n"
				+ "   nom TEXT,\r\n"
				+ "   prenom TEXT,\r\n"
				+ "   mail TEXT,\r\n"
				+ "   telephone TEXT,\r\n"
				+ "   mdp TEXT,\r\n"
				+ "   PRIMARY KEY(idTech)\r\n"
				+ ");\r\n";
			String req6 = "CREATE TABLE IF NOT EXISTS Garantie(\r\n"
				+ "   idGarantie INTEGER AUTOINCREMENT,\r\n"
				+ "   libelle TEXT,\r\n"
				+ "   prix INTEGER,\r\n"
				+ "   duree INTEGER,\r\n"
				+ "   PRIMARY KEY(idGarantie)\r\n"
				+ ");\r\n";
			String req7 = "CREATE TABLE IF NOT EXISTS Service(\r\n"
				+ "   idService INTEGER AUTOINCREMENT,\r\n"
				+ "   libelle TEXT,\r\n"
				+ "   prix REAL,\r\n"
				+ "   PRIMARY KEY(idService)\r\n"
				+ ");\r\n";
			String req8 = "CREATE TABLE IF NOT EXISTS PropositionAchat(\r\n"
				+ "   idProposition INTEGER AUTOINCREMENT,\r\n"
				+ "   anneeFabrication TEXT,\r\n"
				+ "   categ TEXT,\r\n"
				+ "   coloris TEXT,\r\n"
				+ "   idTech INTEGER NOT NULL,\r\n"
				+ "   idCentre INTEGER NOT NULL,\r\n"
				+ "   PRIMARY KEY(idProposition),\r\n"
				+ "   FOREIGN KEY(idTech) REFERENCES Technicien(idTech),\r\n"
				+ "   FOREIGN KEY(idCentre) REFERENCES Centre(idCentre)\r\n"
				+ ");\r\n";
			String req9 = "CREATE TABLE IF NOT EXISTS LigneComande(\r\n"
				+ "   idMateriel INTEGER,\r\n"
				+ "   idCentre INTEGER NOT NULL,\r\n"
				+ "   idGarantie INTEGER NULL,\r\n"
				+ "   dateComande REAL NOT NULL,\r\n"
				+ "   PRIMARY KEY(idMateriel),\r\n"
				+ "   FOREIGN KEY(idMateriel) REFERENCES MATERIEL(idMateriel),\r\n"
				+ "   FOREIGN KEY(idCentre) REFERENCES Centre(idCentre),\r\n"
				+ "   FOREIGN KEY(idGarantie) REFERENCES Garantie(idGarantie)\r\n"
				+ ");\r\n";
			String req10 = "CREATE TABLE IF NOT EXISTS CommandeService(\r\n"
				+ "   idComServ INTEGER AUTOINCREMENT,\r\n"
				+ "   dateDemande REAL,\r\n"
				+ "   datePrestation REAL,\r\n"
				+ "   valide INTEGER,\r\n"
				+ "   idService INTEGER NOT NULL,\r\n"
				+ "   idTech INTEGER,\r\n"
				+ "   idCentre INTEGER NOT NULL,\r\n"
				+ "   PRIMARY KEY(idComServ),\r\n"
				+ "   FOREIGN KEY(idService) REFERENCES Service(idService),\r\n"
				+ "   FOREIGN KEY(idTech) REFERENCES Technicien(idTech),\r\n"
				+ "   FOREIGN KEY(idCentre) REFERENCES Centre(idCentre)\r\n"
				+ ");\r\n";
			
			try {
				String[] requetes = {req1,req2,req3,req4,req5,req6,req7,req8,req9,req10};
				for(String requete : requetes) {
					BD.executeREQ(requete);
				}
			}catch (Exception e) {
				// TODO: handle exception
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		        System.exit(0);
			}
			
		}
	}
	
	
	public static abstract class MaterielData{
		
		//recupere un new ID
		public static void Set(int id,String coloris, double prixVente, DateSimp dateMisVente, String categ, DateSimp dateVendus) throws SQLException {
			String req = "UPDATE Materiel"
					+ "SET coloris=?,prixVente=?,dateMisVente=?,categ=?,dateVendus=? WHERE idMateriel=?;";
			
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setString(0, coloris);
			pstmt.setDouble(1, prixVente);
			pstmt.setLong(2, dateMisVente.getTimestamp());
			pstmt.setString(3,categ);
			pstmt.setLong(4,dateVendus.getTimestamp());
			pstmt.setInt(5, id);
			
			BD.executeREQ(pstmt);

		}
		
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
			
			public MaterielNeuf[] GetAll() {
				MaterielNeuf[] res = null;
				
				String req = "SELECT * FROM Materiel m JOIN MaterielNeuf mn ON m.idMateriel=mn.idMateriel";
				int id;
				String coloris, categ;
				double prixVente;
				long dateMisVente, dateVendus;
				ResultSet result = BD.resultREQ(req);
				try {
					res = new MaterielNeuf[result.getRow()];
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					int index = 0;
					while(result.next()) {
						id = result.getInt("idMateriel");
						coloris = result.getString("coloris");
						prixVente = result.getDouble("prixVente");
						dateMisVente = result.getLong("dateMisVente");
						categ = result.getString("categ");
						dateVendus = result.getLong("dateVendus");
						res[index] = new MaterielNeuf();
						index++;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return res;
			}
			
			public static MaterielNeuf Get(int id) throws SQLException {
				String req = "SELECT * FROM Materiel m JOIN MaterielNeuf mn ON m.idMateriel=mn.idMateriel WHERE m.idMateriel=?";
				String coloris, categ;
				double prixVente;
				long dateMisVente, dateVendus;
				PreparedStatement pstmt = BD.newPreparedSmt(req);
				
				pstmt.setInt(0, id);
				
				ResultSet result = BD.resultREQ(pstmt);
				if(result.next()) {
					coloris = result.getString("coloris");
					prixVente = result.getDouble("prixVente");
					dateMisVente = result.getLong("dateMisVente");
					categ = result.getString("categ");
					dateVendus = result.getLong("dateVendus");
					return new MaterielNeuf();
				}else {
					return null;
				}
			}
			
			public static void Add(int id,String coloris, double prixVente, DateSimp dateMisVente, String categ, DateSimp dateVendus) throws SQLException {
				String req = "INSERT INTO Materiel (idMateriel,coloris,prixVente,dateMisVente,categ,dateVendus) VALUES (?,?,?,?,?,?);";
				String req2 = "INSERT INTO MaterilNeuf (idMateriel) VALUES (?);";
				
				PreparedStatement pstmt = BD.newPreparedSmt(req);
				pstmt.setInt(0, id);
				pstmt.setString(1, coloris);
				pstmt.setDouble(2, prixVente);
				pstmt.setLong(3, dateMisVente.getTimestamp());
				pstmt.setString(4, categ);
				pstmt.setLong(5, dateVendus.getTimestamp());
				PreparedStatement pstmt2 = BD.newPreparedSmt(req2);
				pstmt2.setInt(0, id);
				
				BD.executeREQ(pstmt);
				BD.executeREQ(pstmt2);
			}
			
			public static void Delete(int id) throws SQLException {
				String req = "DELETE FROM Materiel WHERE idMateriel=?";
				
				PreparedStatement pstmt = BD.newPreparedSmt(req);
				pstmt.setInt(0, id);
				
				BD.executeREQ(pstmt);
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
			public static MaterielOccasion[] GetAll() {
				MaterielOccasion[] res = null;
				
				String req = "SELECT * FROM Materiel m JOIN MaterielOccasion mn ON m.idMateriel=mn.idMateriel";
				int id;
				String coloris, categ;
				double prixVente;
				long dateMisVente, dateVendus, dateAchat, prixAchat;
				ResultSet result = BD.resultREQ(req);
				try {
					res = new MaterielOccasion[result.getRow()];
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					int index = 0;
					while(result.next()) {
						id = result.getInt("idMateriel");
						coloris = result.getString("coloris");
						prixVente = result.getDouble("prixVente");
						dateMisVente = result.getLong("dateMisVente");
						categ = result.getString("categ");
						dateVendus = result.getLong("dateVendus");
						dateAchat = result.getLong("dateAchat");
						prixAchat = result.getLong("prixAchat");
						res[index] = new MaterielOccasion();
						index++;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return res;
			}
			
			public static MaterielOccasion Get(int id) throws SQLException {
				String req = "SELECT * FROM Materiel m JOIN MaterielNeuf mn ON m.idMateriel=mn.idMateriel WHERE m.idMateriel=?";
				String coloris, categ;
				double prixVente;
				long dateMisVente, dateVendus, dateAchat, prixAchat;
				PreparedStatement pstmt = BD.newPreparedSmt(req);
				
				pstmt.setInt(0, id);
				
				ResultSet result = BD.resultREQ(pstmt);
				if(result.next()) {
					coloris = result.getString("coloris");
					prixVente = result.getDouble("prixVente");
					dateMisVente = result.getLong("dateMisVente");
					categ = result.getString("categ");
					dateVendus = result.getLong("dateVendus");
					dateAchat = result.getLong("dateAchat");
					prixAchat = result.getLong("prixAchat");
					return new MaterielOccasion();
				}else {
					return null;
				}
			}
			
			public static void Add(int id,String coloris, double prixVente, DateSimp dateMisVente, String categ, DateSimp dateVendus,DateSimp dateAchat,double prixAchat) throws SQLException {
				String req = "INSERT INTO Materiel (idMateriel,coloris,prixVente,dateMisVente,categ,dateVendus) VALUES (?,?,?,?,?,?);";
				String req2 = "INSERT INTO MaterielOccasion (idMateriel,dateAchat,prixAchat) VALUES (?,?,?);";
				
				PreparedStatement pstmt = BD.newPreparedSmt(req);
				pstmt.setInt(0, id);
				pstmt.setString(1, coloris);
				pstmt.setDouble(2, prixVente);
				pstmt.setLong(3, dateMisVente.getTimestamp());
				pstmt.setString(4, categ);
				pstmt.setLong(5, dateVendus.getTimestamp());
				PreparedStatement pstmt2 = BD.newPreparedSmt(req2);
				pstmt2.setInt(0, id);
				pstmt2.setLong(1,dateAchat.getTimestamp());
				pstmt2.setDouble(2, prixAchat);
				
				BD.executeREQ(pstmt);
				BD.executeREQ(pstmt2);
			}
			
			public static void Set(int id/*,String coloris, double prixVente, DateSimp dateMisVente, String categ, DateSimp dateVendus*/,DateSimp dateAchat,double prixAchat) throws SQLException {
				/*String req = "UPDATE Materiel"
						+ "SET coloris=?,prixVente=?,dateMisVente=?,categ=?,dateVendus=? WHERE idMateriel=?;";*/
				String req2 = "UPDATE MaterielOccasion"
						+ "SET dateAchat=?,prixAchat=? WHERE idMateriel=?;";
				
				/*PreparedStatement pstmt = BD.newPreparedSmt(req);
				pstmt.setString(0, coloris);
				pstmt.setDouble(1, prixAchat);
				pstmt.setLong(2, dateMisVente.getTimestamp());
				pstmt.setString(3,categ);
				pstmt.setLong(4,dateVendus.getTimestamp());
				pstmt.setInt(5, id);*/
				PreparedStatement pstmt2 = BD.newPreparedSmt(req2);
				pstmt2.setLong(0, dateAchat.getTimestamp());
				pstmt2.setDouble(1, prixAchat);
				pstmt2.setInt(2, id);
				
				//BD.executeREQ(pstmt);
				BD.executeREQ(pstmt2);
			}
			
			public static void Delete(int id) throws SQLException {
				String req2 = "DELETE FROM Materiel WHERE idMateriel=?";
				String req = "DELETE FROM MaterielOccasion WHERE idMateriel=?";
				
				PreparedStatement pstmt = BD.newPreparedSmt(req);
				pstmt.setInt(0, id);
				PreparedStatement pstmt2 = BD.newPreparedSmt(req2);
				pstmt.setInt(0, id);
				
				BD.executeREQ(pstmt);
				BD.executeREQ(pstmt2);
			}
		}
		
		
		
		
	}
	
	public static abstract class CentreData{
		/*
		 * récuperer tout les Centre
		 * récuperer tout les Centre de type hotel
		 * récuperer tout les Centre de type instituts
		 * récuperer tout les Centre de type licentié
		 * recuperer un centre precis
		 * 
		 * Ajouter un centre
		 * 
		 * Mettre ajour les infos d'un centre
		 * 
		 * supprimer un centre
		 */
		
		public static Centre[] GetAll() throws SQLException {
			String req = "SELECT * FROM Centre";
			ResultSet result = BD.resultREQ(req);
			int idCentre;
			String denom,nomDir,prenomDir,telephone,mail,typeCentre,login,mdp;
			Centre[] res = new Centre[BD.getNbRow(result)];
			int i=0;
			while(result.next()) {
				idCentre = result.getInt("idCentre");
				denom = result.getString("denomination");
				nomDir = result.getString("nomDir");
				prenomDir = result.getString("prenomDir");
				telephone = result.getString("telephone");
				mail = result.getString("mail");
				typeCentre = result.getString("typeCentre");
				mdp = result.getString("mdp");
				res[i] = new Centre();
				i++;
			}
			return res;
		}
		
		public static Centre Get(int id) throws SQLException{
			String req = "SELECT * FROM Centre WHERE idCentre=?";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(0, id);
			ResultSet result = BD.resultREQ(pstmt);
			Centre centre = null;
			int idCentre;
			String denom,nomDir,prenomDir,telephone,mail,typeCentre,login,mdp;
			if(result.next()) {
				id = result.getInt("idCentre");
				denom = result.getString("denomination");
				nomDir = result.getString("nomDir");
				prenomDir = result.getString("prenomDir");
				telephone = result.getString("telephone");
				mail = result.getString("mail");
				typeCentre = result.getString("typeCentre");
				mdp = result.getString("mdp");
				centre = new Centre();
			}
			return centre;
		}
		
		private static Centre[] GetCentreByType(String type) throws SQLException{
			String req = "SELECT * FROM Centre WHERE typeCentre=?";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setString(0, type);
			ResultSet result = BD.resultREQ(pstmt);
			Centre[] res = new Centre[BD.getNbRow(result)];
			int id;
			String denom,nomDir,prenomDir,telephone,mail,typeCentre,login,mdp;
			int i=0;
			while(result.next()) {
				id = result.getInt("idCentre");
				denom = result.getString("denomination");
				nomDir = result.getString("nomDir");
				prenomDir = result.getString("prenomDir");
				telephone = result.getString("telephone");
				mail = result.getString("mail");
				typeCentre = result.getString("typeCentre");
				mdp = result.getString("mdp");
				res[i] = new Centre();
				i++;
			}
		}
		
		public static Centre[] GetCentreHotel() throws SQLException {
			return BD.CentreData.GetCentreByType("Licencié");
		}
		
		public static Centre[] GetCentreLicencie() throws SQLException {
			return BD.CentreData.GetCentreByType("Hotel");		
		}
		
		public static Centre[] GetCentreInstituts() throws SQLException {
			return BD.CentreData.GetCentreByType("Institut");
		}
		
		public static void Add(int id, String deno, String nomDir, String prenomDir, String telephone, String mail, String typeCentre, String mdp) throws SQLException {
			String req = "INSERT INTO Centre VALUES (?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(0, id);
			pstmt.setString(1, deno);
			pstmt.setString(2, nomDir);
			pstmt.setString(3, prenomDir);
			pstmt.setString(4, telephone);
			pstmt.setString(5, mail);
			pstmt.setString(6, typeCentre);
			pstmt.setString(7, mdp);
			BD.executeREQ(pstmt);
			
		}
		
		public static void Set(int id, String deno, String nomDir, String prenomDir, String telephone, String mail, String typeCentre, String mdp) throws SQLException {
			String req = "UPDATE Centre SET denomination=?, nomDir=?, prenomDir=?, telephone=?, mail=?, typeCentre=?, login=?, mdp=? WHERE idCentre=?";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(8, id);
			pstmt.setString(0, deno);
			pstmt.setString(1, nomDir);
			pstmt.setString(2, prenomDir);
			pstmt.setString(3, telephone);
			pstmt.setString(4, mail);
			pstmt.setString(5, typeCentre);
			pstmt.setString(6, mdp);
			BD.executeREQ(pstmt);
		}
		
		public static void Delete(int id ) throws SQLException {
			String req = "DELETE Centre WHERE idCentre=?";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(0, id);
			BD.executeREQ(pstmt);
		}
	}
	
	public static abstract class CommandeServiceData{
		/*
		 * récupérer les commande service en attente d'un technitien (qui non pas de technicien)
		 * recupérer les commande service en attente de prestation (qui on un technicien precis mais valide=false)
		 * recupérer une commande service précise
		 * 
		 * Ajouter une commande service
		 * 
		 * Mettre a jour les infos d'un commande service
		 * 
		 * supprimer une commandeService
		 */
		public static CommandeService[] GetAll() throws SQLException {
			String req = "SELECT * FROM CommandeService;";
			ResultSet result = BD.resultREQ(req);
			CommandeService[] cmdes = new CommandeService[BD.getNbRow(result)];
			int idCom,idService,idTech,idCentre;
			Long dateDemande, datePrestation;
			boolean valide;
			int i=0;
			while(result.next()) {
				idCom = result.getInt("idComServ");
				dateDemande = result.getLong("dateDemande");
				datePrestation = result.getLong("datePrestation");
				if(result.getInt("valide") == 0) {
					valide = false;
				}else {
					valide = true;
				}
				idService = result.getInt("idService");
				idTech = result.getInt("idTech");
				idCentre = result.getInt("idCentre");
				cmdes[i] = new CommandeService();
				i++;
 			}
			return cmdes;
		}
		
		public static CommandeService[] GetWaittingTech()  throws SQLException {
			String req = "SELECT * FROM CommandeService WHERE idTech IS NULL;";
			ResultSet result = BD.resultREQ(req);
			CommandeService[] cmdes = new CommandeService[BD.getNbRow(result)];
			int idCom,idService,idTech,idCentre;
			Long dateDemande, datePrestation;
			boolean valide;
			int i=0;
			while(result.next()) {
				idCom = result.getInt("idComServ");
				dateDemande = result.getLong("dateDemande");
				datePrestation = result.getLong("datePrestation");
				if(result.getInt("valide") == 0) {
					valide = false;
				}else {
					valide = true;
				}
				idService = result.getInt("idService");
				idTech = result.getInt("idTech");
				idCentre = result.getInt("idCentre");
				cmdes[i] = new CommandeService();
				i++;
 			}
			return cmdes;
		}
		
		public static CommandeService[] GetWaittingPrest() throws SQLException {
			String req = "SELECT * FROM CommandeService WHERE valide=0;";
			ResultSet result = BD.resultREQ(req);
			CommandeService[] cmdes = new CommandeService[BD.getNbRow(result)];
			int idCom,idService,idTech,idCentre;
			Long dateDemande, datePrestation;
			boolean valide;
			int i=0;
			while(result.next()) {
				idCom = result.getInt("idComServ");
				dateDemande = result.getLong("dateDemande");
				datePrestation = result.getLong("datePrestation");
				if(result.getInt("valide") == 0) {
					valide = false;
				}else {
					valide = true;
				}
				idService = result.getInt("idService");
				idTech = result.getInt("idTech");
				idCentre = result.getInt("idCentre");
				cmdes[i] = new CommandeService();
				i++;
 			}
			return cmdes;
		}
		
		public static CommandeService Get(int id) throws SQLException {
			String req = "SELECT * FROM CommandeService WHERE idComServ=?;";
			int idCom,idService,idTech,idCentre;
			Long dateDemande, datePrestation;
			boolean valide;
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(0, id);
			ResultSet result = BD.resultREQ(pstmt);
			if(result.next()) {
				idCom = result.getInt("idComServ");
				dateDemande = result.getLong("dateDemande");
				datePrestation = result.getLong("datePrestation");
				if(result.getInt("valide") == 0) {
					valide = false;
				}else {
					valide = true;
				}
				idService = result.getInt("idService");
				idTech = result.getInt("idTech");
				idCentre = result.getInt("idCentre");
				return new CommandeService();
			}else {return null;}
		}
		
		public static void Add(int id, DateSimp dateDemande, DateSimp datePrestation, int idService, int idCentre) throws SQLException {
			String req = "INSERT INTO CommandeService(idComServ,dateDemande,datePrestation,valide,idService,idCentre) VALUES (?,?,?,?,?,?);";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(0, id);
			pstmt.setLong(1, dateDemande.getTimestamp());
			pstmt.setLong(2, datePrestation.getTimestamp());
			pstmt.setInt(3,0);//Valide a false
			pstmt.setInt(4,idService);
			pstmt.setInt(5, idCentre);
			BD.executeREQ(pstmt);
		}
		
		public static void Set(int id, DateSimp dateDemande, DateSimp datePrestation, boolean valide, int idService, int idTech, int idCentre) throws SQLException {
			String req = "UPDATE CommandeService SET dateDemande=?, datePrestation=?,valide=?,idService=?,idTech=?,idCentre=? WHERE idComServ=?";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setLong(0, dateDemande.getTimestamp());
			pstmt.setLong(1, datePrestation.getTimestamp());
			pstmt.setInt(2,0);//Valide a false
			pstmt.setInt(3,idService);
			pstmt.setInt(4, idCentre);
			pstmt.setInt(5, id);
			BD.executeREQ(pstmt);
		}
		
		public static void Delete(int id) throws SQLException {
			String req = "DELETE CommandeService WHERE idComServ=?;";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(0,id);
			BD.executeREQ(pstmt);
		}
	}
	
	public static abstract class TechnicienData{
		/*
		 * recuperer tout les Technicien
		 * recuperer un Technicien précis
		 * 
		 * Ajouter un technicien
		 * 
		 * Mettre a jour un technicien
		 * 
		 * supprimer un technicien
		 */
		
		public static Technicien[] GetAll() throws SQLException {
			String req = "SELECT * FROM Technicien;";
			ResultSet result = BD.resultREQ(req);
			int id;
			String nom,prenom,mail,telephone,mdp;
			Technicien[] techs = new Technicien[BD.getNbRow(result)];
			int i = 0;
			while(result.next()) {
				id = result.getInt("idTech");
				nom = result.getString("nom");
				prenom = result.getString("prenom");
				mail = result.getString("mail");
				telephone = result.getString("telephone");
				mdp = result.getString("mdp");
				techs[i] = new Technicien();
				i++;
			}
			return techs;
		}
		
		public static Technicien Get(int id) throws SQLException {
			String req = "SELECT * FROM Technicien WHERE idTech=?;";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(0, id);
			ResultSet result = BD.resultREQ(pstmt);
			int idTech;
			String nom,prenom,mail,telephone,mdp;
			Technicien tech = null;
			if(result.next()) {
				idTech = result.getInt("idTech");
				nom = result.getString("nom");
				prenom = result.getString("prenom");
				mail = result.getString("mail");
				telephone = result.getString("telephone");
				mdp = result.getString("mdp");
				tech = new Technicien();
			}
			return tech;
		}
		
		public static void Add(int id, String nom, String prenom, String mail, String telephone, String mdp) throws SQLException {
			String req = "INSERT INTO Technicien VALUES (?,?,?,?,?,?);";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(0, id);
			pstmt.setString(1, nom);
			pstmt.setString(2, prenom);
			pstmt.setString(3, mail);
			pstmt.setString(4, telephone);
			pstmt.setString(5, mdp);
			BD.executeREQ(pstmt);
		}
		
		public static void Set(int id, String nom, String prenom, String mail, String telephone, String mdp) throws SQLException {
			String req = "UPDATE Technicien nom=?,prenom=?,mail=?,telephone=?,mdp=? WHERE idTech=?;";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setString(0, nom);
			pstmt.setString(1, prenom);
			pstmt.setString(2, mail);
			pstmt.setString(3, telephone);
			pstmt.setString(4, mdp);
			pstmt.setInt(5, id);
			BD.executeREQ(pstmt);
		}
		
		public static void Delete(int id) throws SQLException {
			String req = "DELETE Technicien WHERE idTech=?;";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(0, id);
			BD.executeREQ(pstmt);
		}
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
		
		public static Garantie[] GetAll() throws SQLException {
			String req = "SELECT * FROM Garantie;";
			ResultSet result = BD.resultREQ(req);
			int idGarantie,duree;//Duree en mois
			String libelle;
			double prix;
			Garantie[] res = new Garantie[result.getRow()];
			int i = 0;
			while(result.next()) {
				idGarantie = result.getInt("idGarantie");
				libelle = result.getString("libelle");
				prix = result.getDouble("prix");
				duree = result.getInt("duree");
				res[i] = new Garantie();
				i++;
			}
			return res;
		}
		
		public static Garantie Get(int id) throws SQLException {
			String req = "SELECT * FROM Garantie WHERE id=?;";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			ResultSet result = BD.resultREQ(pstmt);
			pstmt.setInt(0, id);
			int idGarantie,duree;//Duree en mois
			String libelle;
			double prix;
			if(result.next()) {
				idGarantie = result.getInt("idGarantie");
				libelle = result.getString("libelle");
				prix = result.getDouble("prix");
				duree = result.getInt("duree");
				return new Garantie();
			}else {
				return null;
			}
		}
		
		public static void Add(int id,String libelle,double prix,int duree) throws SQLException {
			String req = "INSERT INTO Garantie(idGarantie,libelle,prix,duree) VALUES (?,?,?,?);";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(0,id);
			pstmt.setString(1, libelle);
			pstmt.setDouble(2, prix);
			pstmt.setInt(3,duree);
			BD.executeREQ(pstmt);
		}
		
		public static void Set(int id,String libelle,double prix,int duree) throws SQLException {
			String req = "UPDATE Garantie SET libelle=?,prix=?,duree=? WHERE idGarantie=?;";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setString(0, libelle);
			pstmt.setDouble(1, prix);
			pstmt.setInt(2,duree);
			pstmt.setInt(3,id);
			BD.executeREQ(pstmt);
		}
		
		public static void Delete(int id) throws SQLException {
			String req = "DELETE Garantie WHERE idGarantie=?";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(0, id);
			BD.executeREQ(pstmt);
		}
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
		
		public static Service[] GetAll() throws SQLException {
			String req = "SELECT * FROM Service;";
			ResultSet result = resultREQ(req);
			int id;
			String libelle;
			double prix;
			Service[] res = new Service[BD.getNbRow(result)];
			int i = 0;
			while(result.next()) {
				id = result.getInt("idService");
				libelle = result.getString("libelle");
				prix = result.getDouble("prix");
				res[i] = new Service();
				i++;
			}
			
		}
		
		public static void Get(int id) throws SQLException {
			String req = "SELECT * FROM Service WHERE idService=?;";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(0,id);
			ResultSet result = BD.resultREQ(pstmt);
			String libelle;
			double prix;
			Service[] res = new Service[BD.getNbRow(result)];
			int i = 0;
			while(result.next()) {
				libelle = result.getString("libelle");
				prix = result.getDouble("prix");
				res[i] = new Service();
				i++;
			}
		}
		
		public static void Add(int id, String libelle, double prix) throws SQLException {
			String req = "INSERT INTO Service VALUES (?,?,?);";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(0, id);
			pstmt.setString(1, libelle);
			pstmt.setDouble(2, prix);
			BD.executeREQ(pstmt);
		}
		
		public static void Set(int id, String libelle, double prix) throws SQLException {
			String req = "UPDATE Service SET libelle=?, prix=? WHERE idService=?;";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(2, id);
			pstmt.setString(0, libelle);
			pstmt.setDouble(1, prix);
			BD.executeREQ(pstmt);
		}
		
		public static void Delete(int id) throws SQLException {
			String req = "DELETE Service WHERE idService=?;";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(0, id);
			BD.executeREQ(pstmt);
		}
		
	}
	
	public static abstract class PropositionAchatData{
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
		
		public static PropositionAchat[] GetAll() throws SQLException {
			String req = "SELECT * FROM PropositionAchat;";
			ResultSet result = BD.resultREQ(req);
			int id,idTech,idCentre;
			String anneeFab,categ,coloris;
			PropositionAchat[] res = new PropositionAchat[result.getRow()];
			int i = 0;
			while(result.next()) {
				id = result.getInt("idProposition");
				anneeFab = result.getString("aneeFabrication");
				categ = result.getString("categ");
				coloris = result.getString("coloris");
				idTech = result.getInt("idTech");
				idCentre = result.getInt("idCentre");
				res[i] = new PropositionAchat();
				i++;
			}
			return res;
		}
		
		public static PropositionAchat Get(int id) throws SQLException {
			String req = "SELECT * FROM PropositionAchat WHERE idProposition=?;";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(0, id);
			ResultSet result = BD.resultREQ(pstmt);
			int idTech,idCentre;
			String anneeFab,categ,coloris;
			PropositionAchat res = null;
			if(result.next()) {
				anneeFab = result.getString("aneeFabrication");
				categ = result.getString("categ");
				coloris = result.getString("coloris");
				idTech = result.getInt("idTech");
				idCentre = result.getInt("idCentre");
				res = new PropositionAchat();
			}
			return res;
		}
		
		public static void Add(int id, String anneeFab,String categ,String coloris,int idTech,int idCentre) throws SQLException {
			String req = "INSERT INTO PropositionAchat VALUES (?,?,?,?,?,?);";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(0, id);
			pstmt.setString(1, anneeFab);
			pstmt.setString(2,categ);
			pstmt.setString(3, coloris);
			pstmt.setInt(4, idTech);
			pstmt.setInt(5, idCentre);
			BD.executeREQ(pstmt);
		}
		
		public static void Set(int id, String anneeFab,String categ,String coloris,int idTech,int idCentre) throws SQLException  {
			String req = "UPDATE PropositionAchat SET anneFabrication=?,categ=?,coloris=?,idTech=?,idCentre? WHERE idProposition=?;";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setString(0, anneeFab);
			pstmt.setString(1,categ);
			pstmt.setString(2, coloris);
			pstmt.setInt(3, idTech);
			pstmt.setInt(4, idCentre);
			pstmt.setInt(5, id);
			BD.executeREQ(pstmt);
		}
		
		public static void Delete(int id) throws SQLException {
			String req = "DELETE PropositionAchat WHERE idProposition=?;";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(0, id);
			BD.executeREQ(pstmt);
		}
		
		
	}
	
	public static abstract class LigneCommandeData{
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
		
		public static LigneCommande[] GetAll() throws SQLException {
			String req = "SELECT * FROM LigneComande;";
			ResultSet result = BD.resultREQ(req);
			LigneCommande[] res = new LigneCommande[BD.getNbRow(result)];
			int idMateriel,idCentre,idGarantie;
			Long dateCommande;
			int i=0;
			while(result.next()) {
				idMateriel = result.getInt("idMateriel");
				idCentre = result.getInt("idCentre");
				idGarantie = result.getInt("idGarantie");
				dateCommande = result.getLong("dateCommande");
				res[i] = new LigneCommande();
				i++;
			}
			return res;
		}
		
		public static LigneCommande Get(int id) throws SQLException {
			String req = "SELECT * FROM LigneComande WHERE idMate;";
			ResultSet result = BD.resultREQ(req);
			LigneCommande res = null;
			int idMateriel,idCentre,idGarantie;
			Long dateCommande;
			while(result.next()) {
				idMateriel = result.getInt("idMateriel");
				dateCommande = result.getLong("dateCommande");
				idCentre = result.getInt("idCentre");
				idGarantie = result.getInt("idGarantie");
				res = new LigneCommande();
			}
			return res;
		}
		
		public static void Add(int idMateriel, DateSimp dateCommande, int idCentre, int idGarantie) throws SQLException {
			String req = "INSERT INTO LigneCommande(idMateriel,dateCommande,idCentre,idGarantie) VALUES (?,?,?,?)";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(0,idMateriel);
			pstmt.setLong(1, dateCommande.getTimestamp());
			pstmt.setInt(2, idCentre);
			pstmt.setInt(3, idGarantie);
			BD.executeREQ(pstmt);
		}
		
		public static void Set(int idMateriel, DateSimp dateCommande, int idCentre, int idGarantie) throws SQLException {
			String req = "UPDATE LigneCommande SET dateCommande=?,idCentre=?,idGarantie=? WHERE idMateriel=?";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(3,idMateriel);
			pstmt.setLong(0, dateCommande.getTimestamp());
			pstmt.setInt(1, idCentre);
			pstmt.setInt(2, idGarantie);
			BD.executeREQ(pstmt);
		}
		
		public static void Delete(int id) throws SQLException {
			String req = "DELETE LigneCommande WHERE idMateriel=?";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(0, id);
			BD.executeREQ(pstmt);
		}
		
	}
	
	public static int getNbRow(ResultSet rst) throws SQLException {
		rst.last();
		int res = rst.getRow();
		rst.absolute(0);
		return res;
	}
	
	public static String[] GetTypesCentre() {
		String[] types = {"Institut","Hotel","Licencié"};
		return types;
	}
	
	public static String[] GetMaterielCategorie() {
		String[] categs = {"Running","Bike"};
		return categs;
	}
	
	public static ResultSet resultREQ(PreparedStatement pstmt) {
		ResultSet res = null;
		try {
			res = pstmt.executeQuery();
			//pstmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static ResultSet resultREQ(String myQuery) {//requete ou l'on attend un je de résutltat
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
	
	public static PreparedStatement newPreparedSmt(String sql) {
		PreparedStatement pstmt = null;
		try {
			pstmt = BD.c.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pstmt;
	}
	
	//requete non preparer 
	public static void executeREQ(String myQuery) {//
		Statement stmt;
		try {
			stmt = BD.c.createStatement();
			stmt.execute(myQuery);
			//La requete execute on faire la statement
			stmt.close();
			BD.c.commit();//sauvegarde des modification
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void executeREQ(PreparedStatement pstmt) {
		try {
			pstmt.execute();
			pstmt.close();
			BD.c.commit();
		}catch(SQLException e) {
			e.printStackTrace();
		}
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
		BD.init();
	}

}
