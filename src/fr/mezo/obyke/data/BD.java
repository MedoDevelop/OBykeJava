package fr.mezo.obyke.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

import fr.mezo.obyke.workclass.Centre;
import fr.mezo.obyke.workclass.Garantie;
import fr.mezo.obyke.workclass.LigneCommande;
import fr.mezo.obyke.workclass.MaterielNeuf;
import fr.mezo.obyke.workclass.MaterielOccasion;
import fr.mezo.obyke.workclass.PropositionAchat;
import fr.mezo.obyke.workclass.Rdv;
import fr.mezo.obyke.workclass.Service;
import fr.mezo.obyke.workclass.Technicien;

public abstract class BD {
	private static Connection c = null;
	
	
	private static int newIDTable(String tableName) throws Exception{
		Hashtable<String, String> tablesAndId = new Hashtable<String, String>();
		tablesAndId.put("Materiel","idMateriel");
		tablesAndId.put("Centre","idCentre");
		tablesAndId.put("Technicien","idTech");
		tablesAndId.put("Garantie","idGarantie");
		tablesAndId.put("Service","idService");
		tablesAndId.put("PropositionAchat","idProposition");
		tablesAndId.put("Rdv","idRdv");
		int id=0;
		Random random = new Random();
		boolean Autorise;
		if (tablesAndId.containsKey(tableName)){
			String sql = "SELECT "+tablesAndId.get(tableName)+" FROM "+tableName+";";
			ResultSet res;
			do {
				Autorise = false;
				id = random.nextInt(500000);
				res = BD.resultREQ(sql);
				while(res.next()) {
					if(res.getInt(1) != id) {
						Autorise = true;
						break;
					}
				}
			}while(id==0 && !Autorise);
			return id;
		}else {
			throw new Exception("La table demander ne peut être gérer");
		}
	}
	
	public static int newIDMateriel() throws Exception {
		return BD.newIDTable("Materiel");
	}
	
	public static int newIDCentre() throws Exception {
		return BD.newIDTable("Centre");
	}
	
	public static int newIDTechnicien() throws Exception {
		return BD.newIDTable("Technicien");
	}
	
	public static int newIDGarantie() throws Exception {
		return BD.newIDTable("Garantie");
	}
	
	public static int newIDService() throws Exception {
		return BD.newIDTable("Service");
	}
	
	public static int newIDPropositionAchat() throws Exception {
		return BD.newIDTable("PropositionAchat");
	}
	
	public static int newIDRdv() throws Exception {
		return BD.newIDTable("Rdv");
	}
	
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
				+ "   idMateriel INTEGER,\r\n"
				+ "   coloris TEXT,\r\n"
				+ "   prixVente REAL,\r\n"
				+ "   dateMisVente REAL,\r\n"
				+ "   categ TEXT,\r\n"
				+ "   dateVendus REAL,\r\n"
				+ "   PRIMARY KEY(idMateriel)\r\n"
				+ ");\r\n";
			String req2 = "CREATE TABLE IF NOT EXISTS Centre(\r\n"
				+ "   idCentre INTEGER,\r\n"
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
				+ "   idTech INTEGER,\r\n"
				+ "   nom TEXT,\r\n"
				+ "   prenom TEXT,\r\n"
				+ "   mail TEXT,\r\n"
				+ "   telephone TEXT,\r\n"
				+ "   mdp TEXT,\r\n"
				+ "   PRIMARY KEY(idTech)\r\n"
				+ ");\r\n";
			String req6 = "CREATE TABLE IF NOT EXISTS Garantie(\r\n"
				+ "   idGarantie INTEGER,\r\n"
				+ "   libelle TEXT,\r\n"
				+ "   prix INTEGER,\r\n"
				+ "   duree INTEGER,\r\n"
				+ "   PRIMARY KEY(idGarantie)\r\n"
				+ ");\r\n";
			String req7 = "CREATE TABLE IF NOT EXISTS Service(\r\n"
				+ "   idService INTEGER,\r\n"
				+ "   typeService TEXT,\r\n"
				+ "   denomination TEXT,\r\n"
				+ "   nomDir TEXT,\r\n"
				+ "	  prenomDir TEXT,\r\n"
				+ "	  telephone TEXT,\r\n"
				+ "	  mail TEXT,\r\n"
				+ "   categ TEXT,\r\n"
				+ "   marque TEXT,\r\n"
				+ "   fournisseur TEXT,\r\n"
				+ "   dateAchat REAL,\r\n"
				+ "   dateDepot REAL,\r\n"
				+ "   PRIMARY KEY(idService)\r\n"
				+ ");\r\n";
			String req8 = "CREATE TABLE IF NOT EXISTS PropositionAchat(\r\n"
				+ "   idProposition INTEGER,\r\n"
				+ "   anneeFabrication TEXT,\r\n"
				+ "   categ TEXT,\r\n"
				+ "   coloris TEXT,\r\n"
				+ "   idTech INTEGER NOT NULL,\r\n"
				+ "   idCentre INTEGER NOT NULL,\r\n"
				+ "   PRIMARY KEY(idProposition),\r\n"
				+ "   FOREIGN KEY(idTech) REFERENCES Technicien(idTech),\r\n"
				+ "   FOREIGN KEY(idCentre) REFERENCES Centre(idCentre)\r\n"
				+ ");\r\n";
			String req9 = "CREATE TABLE IF NOT EXISTS LigneCommande(\r\n"
				+ "   idMateriel INTEGER,\r\n"
				+ "   idCentre INTEGER NOT NULL,\r\n"
				+ "   idGarantie INTEGER NULL,\r\n"
				+ "   dateCommande REAL NOT NULL,\r\n"
				+ "   PRIMARY KEY(idMateriel),\r\n"
				+ "   FOREIGN KEY(idMateriel) REFERENCES MATERIEL(idMateriel),\r\n"
				+ "   FOREIGN KEY(idCentre) REFERENCES Centre(idCentre),\r\n"
				+ "   FOREIGN KEY(idGarantie) REFERENCES Garantie(idGarantie)\r\n"
				+ ");\r\n";
			
			String req10 = "CREATE TABLE IF NOT EXISTS Rdv("
					+ "idRdv INTEGER,"
					+ "denomination TEXT,"
					+ "nomDir TEXT,"
					+ "prenomDir TEXT,"
					+ "telephone TEXT,"
					+ "mail TEXT,"
					+ "dateRdv REAL,"
					+ "heureRdv TEXT,"
					+ "motif TEXT,"
					+ "techId INTEGER,"
					+ "PRIMARY KEY(idRdv)"
					+ "FOREIGN KEY(techId) REFERENCES Technicien(idTech));";
			try {
				String[] requetes = {req1,req2,req3,req4,req5,req6,req7,req8,req9,req10};
				for(String requete : requetes) {
					BD.executeREQ(requete);
				}
			}catch (Exception e) {
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
			pstmt.setString(1, coloris);
			pstmt.setDouble(2, prixVente);
			pstmt.setLong(3, dateMisVente.getTimestamp());
			pstmt.setString(4,categ);
			pstmt.setLong(5,dateVendus.getTimestamp());
			pstmt.setInt(6, id);
			
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
			
			public ArrayList<MaterielNeuf> GetAll() {
				
				String req = "SELECT * FROM Materiel m JOIN MaterielNeuf mn ON m.idMateriel=mn.idMateriel";
				int id;
				String coloris, categ;
				double prixVente;
				long dateMisVente, dateVendus;
				ResultSet result = BD.resultREQ(req);
				ArrayList<MaterielNeuf> res = new ArrayList<MaterielNeuf>();
				try {
					while(result.next()) {
						id = result.getInt("idMateriel");
						coloris = result.getString("coloris");
						prixVente = result.getDouble("prixVente");
						dateMisVente = result.getLong("dateMisVente");
						categ = result.getString("categ");
						dateVendus = result.getLong("dateVendus");
						res.add(new MaterielNeuf(id,coloris,prixVente,DateSimp.of(dateMisVente),categ,DateSimp.of(dateVendus)));
					}
				} catch (SQLException e) {
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
				
				pstmt.setInt(1, id);
				
				ResultSet result = BD.resultREQ(pstmt);
				if(result.next()) {
					coloris = result.getString("coloris");
					prixVente = result.getDouble("prixVente");
					dateMisVente = result.getLong("dateMisVente");
					categ = result.getString("categ");
					dateVendus = result.getLong("dateVendus");
					return new MaterielNeuf(id,coloris,prixVente,DateSimp.of(dateMisVente),categ,DateSimp.of(dateVendus));
				}else {
					return null;
				}
			}
			
			public static void Add(String coloris, double prixVente, DateSimp dateMisVente, String categ, DateSimp dateVendus) throws SQLException {
				String req = "INSERT INTO Materiel (idMateriel,coloris,prixVente,dateMisVente,categ,dateVendus) VALUES (?,?,?,?,?,?);";
				String req2 = "INSERT INTO MaterielNeuf (idMateriel) VALUES (?);";
				int id = 0;
				try {
					id = BD.newIDMateriel();
				} catch (Exception e) {
					e.printStackTrace();
				}
				PreparedStatement pstmt = BD.newPreparedSmt(req);
				pstmt.setInt(1, id);
				pstmt.setString(2, coloris);
				pstmt.setDouble(3, prixVente);
				pstmt.setLong(4, dateMisVente.getTimestamp());
				pstmt.setString(5, categ);
				pstmt.setLong(6, dateVendus.getTimestamp());
				PreparedStatement pstmt2 = BD.newPreparedSmt(req2);
				pstmt2.setInt(1, id);
				
				BD.executeREQ(pstmt);
				BD.executeREQ(pstmt2);
			}
			
			public static void Delete(int id) throws SQLException {
				String req = "DELETE FROM Materiel WHERE idMateriel=?";
				String req2 = "DELETE FROM MaterielNeuf WHERE idMateriel=?";
				PreparedStatement pstmt = BD.newPreparedSmt(req);
				PreparedStatement pstmt2 = BD.newPreparedSmt(req2);
				pstmt.setInt(1, id);
				pstmt2.setInt(1, id);
				
				BD.executeREQ(pstmt2);
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
			public static ArrayList<MaterielOccasion> GetAll() {
				
				
				String req = "SELECT * FROM Materiel m JOIN MaterielOccasion mn ON m.idMateriel=mn.idMateriel";
				int id;
				String coloris, categ;
				double prixVente,prixAchat;
				long dateMisVente, dateVendus, dateAchat;
				ResultSet result = BD.resultREQ(req);
				ArrayList<MaterielOccasion> res = new ArrayList<MaterielOccasion>();
				try {
					while(result.next()) {
						id = result.getInt("idMateriel");
						coloris = result.getString("coloris");
						prixVente = result.getDouble("prixVente");
						dateMisVente = result.getLong("dateMisVente");
						categ = result.getString("categ");
						dateVendus = result.getLong("dateVendus");
						dateAchat = result.getLong("dateAchat");
						prixAchat = result.getDouble("prixAchat");
						res.add(new MaterielOccasion(id,coloris,prixVente,DateSimp.of(dateMisVente),categ,DateSimp.of(dateVendus),DateSimp.of(dateAchat),prixAchat));
					}
				} catch (SQLException e) {
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
				
				pstmt.setInt(1, id);
				
				ResultSet result = BD.resultREQ(pstmt);
				if(result.next()) {
					coloris = result.getString("coloris");
					prixVente = result.getDouble("prixVente");
					dateMisVente = result.getLong("dateMisVente");
					categ = result.getString("categ");
					dateVendus = result.getLong("dateVendus");
					dateAchat = result.getLong("dateAchat");
					prixAchat = result.getLong("prixAchat");
					return new MaterielOccasion(id,coloris,prixVente,DateSimp.of(dateMisVente),categ,DateSimp.of(dateVendus),DateSimp.of(dateAchat),prixAchat);
				}else {
					return null;
				}
			}
			
			public static void Add(String coloris, double prixVente, DateSimp dateMisVente, String categ, DateSimp dateVendus,DateSimp dateAchat,double prixAchat) throws SQLException {
				String req = "INSERT INTO Materiel (idMateriel,coloris,prixVente,dateMisVente,categ,dateVendus) VALUES (?,?,?,?,?,?);";
				String req2 = "INSERT INTO MaterielOccasion (idMateriel,dateAchat,prixAchat) VALUES (?,?,?);";
				int id = 0;
				try {
					id = BD.newIDMateriel();
				} catch (Exception e) {
					e.printStackTrace();
				}
				PreparedStatement pstmt = BD.newPreparedSmt(req);
				pstmt.setInt(1, id);
				pstmt.setString(2, coloris);
				pstmt.setDouble(3, prixVente);
				pstmt.setLong(4, dateMisVente.getTimestamp());
				pstmt.setString(5, categ);
				pstmt.setLong(6, dateVendus.getTimestamp());
				PreparedStatement pstmt2 = BD.newPreparedSmt(req2);
				pstmt2.setInt(1, id);
				pstmt2.setLong(2,dateAchat.getTimestamp());
				pstmt2.setDouble(3, prixAchat);
				
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
				pstmt2.setLong(1, dateAchat.getTimestamp());
				pstmt2.setDouble(2, prixAchat);
				pstmt2.setInt(3, id);
				
				//BD.executeREQ(pstmt);
				BD.executeREQ(pstmt2);
			}
			
			public static void Delete(int id) throws SQLException {
				String req2 = "DELETE FROM Materiel WHERE idMateriel=?";
				String req = "DELETE FROM MaterielOccasion WHERE idMateriel=?";
				
				PreparedStatement pstmt = BD.newPreparedSmt(req);
				pstmt.setInt(1, id);
				PreparedStatement pstmt2 = BD.newPreparedSmt(req2);
				pstmt.setInt(1, id);
				
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
		 * [(
		 * supprimer un centre
		 */
		
		public static ArrayList<Centre> GetAll() throws SQLException {
			String req = "SELECT * FROM Centre";
			ResultSet result = BD.resultREQ(req);
			int idCentre;
			String denom,nomDir,prenomDir,telephone,mail,typeCentre,mdp;
			ArrayList<Centre> res = new ArrayList<Centre>();
			while(result.next()) {
				idCentre = result.getInt("idCentre");
				denom = result.getString("denomination");
				nomDir = result.getString("nomDir");
				prenomDir = result.getString("prenomDir");
				telephone = result.getString("telephone");
				mail = result.getString("mail");
				typeCentre = result.getString("typeCentre");
				mdp = result.getString("mdp");
				res.add(new Centre(idCentre,denom,nomDir,prenomDir,telephone,mail,typeCentre,mdp));
			}
			return res;
		}
		
		public static Centre Get(int id) throws SQLException{
			String req = "SELECT * FROM Centre WHERE idCentre=?";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(1, id);
			ResultSet result = BD.resultREQ(pstmt);
			Centre centre = null;
			String denom,nomDir,prenomDir,telephone,mail,typeCentre,mdp;
			if(result.next()) {
				id = result.getInt("idCentre");
				denom = result.getString("denomination");
				nomDir = result.getString("nomDir");
				prenomDir = result.getString("prenomDir");
				telephone = result.getString("telephone");
				mail = result.getString("mail");
				typeCentre = result.getString("typeCentre");
				mdp = result.getString("mdp");
				centre = new Centre(id,denom,nomDir,prenomDir,telephone,mail,typeCentre,mdp);
			}
			return centre;
		}
		
		private static ArrayList<Centre> GetCentreByType(String type) throws SQLException{
			String req = "SELECT * FROM Centre WHERE typeCentre=?";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setString(1, type);
			ResultSet result = BD.resultREQ(pstmt);
			ArrayList<Centre> res = new ArrayList<Centre>();
			int id;
			String denom,nomDir,prenomDir,telephone,mail,typeCentre,mdp;
			while(result.next()) {
				id = result.getInt("idCentre");
				denom = result.getString("denomination");
				nomDir = result.getString("nomDir");
				prenomDir = result.getString("prenomDir");
				telephone = result.getString("telephone");
				mail = result.getString("mail");
				typeCentre = result.getString("typeCentre");
				mdp = result.getString("mdp");
				res.add(new Centre(id,denom,nomDir,prenomDir,telephone,mail,typeCentre,mdp));
			}
			return res;
		}
		
		public static ArrayList<Centre> GetCentreHotel() throws SQLException {
			return BD.CentreData.GetCentreByType("Licencié");
		}
		
		public static ArrayList<Centre> GetCentreLicencie() throws SQLException {
			return BD.CentreData.GetCentreByType("Hotel");		
		}
		
		public static ArrayList<Centre> GetCentreInstituts() throws SQLException {
			return BD.CentreData.GetCentreByType("Institut");
		}
		
		public static void Add(String deno, String nomDir, String prenomDir, String telephone, String mail, String typeCentre, String mdp) throws SQLException {
			String req = "INSERT INTO Centre VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			int id = 0;
			try {
				id = BD.newIDCentre();
			} catch (Exception e) {
				e.printStackTrace();
			}
			pstmt.setInt(1, id);
			pstmt.setString(2, deno);
			pstmt.setString(3, nomDir);
			pstmt.setString(4, prenomDir);
			pstmt.setString(5, telephone);
			pstmt.setString(6, mail);
			pstmt.setString(7, typeCentre);
			pstmt.setString(8, mdp);
			BD.executeREQ(pstmt);
			
		}
		
		public static void Set(int id, String deno, String nomDir, String prenomDir, String telephone, String mail, String typeCentre, String mdp) throws SQLException {
			String req = "UPDATE Centre SET denomination=?, nomDir=?, prenomDir=?, telephone=?, mail=?, typeCentre=?, mdp=? WHERE idCentre=?";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(8, id);
			pstmt.setString(1, deno);
			pstmt.setString(2, nomDir);
			pstmt.setString(3, prenomDir);
			pstmt.setString(4, telephone);
			pstmt.setString(5, mail);
			pstmt.setString(6, typeCentre);
			pstmt.setString(7, mdp);
			BD.executeREQ(pstmt);
		}
		
		public static void Delete(int id ) throws SQLException {
			String req = "DELETE FROM Centre WHERE idCentre=?";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(1, id);
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
		
		public static ArrayList<Technicien> GetAll() throws SQLException {
			String req = "SELECT * FROM Technicien;";
			ResultSet result = BD.resultREQ(req);
			int id;
			String nom,prenom,mail,telephone,mdp;
			ArrayList<Technicien> res = new ArrayList<Technicien>();
			while(result.next()) {
				id = result.getInt("idTech");
				nom = result.getString("nom");
				prenom = result.getString("prenom");
				mail = result.getString("mail");
				telephone = result.getString("telephone");
				mdp = result.getString("mdp");
				res.add(new Technicien(id,nom,prenom,mail,telephone,mdp));
			}
			return res;
		}
		
		public static Technicien Get(int id) throws SQLException {
			String req = "SELECT * FROM Technicien WHERE idTech=?;";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(1, id);
			ResultSet result = BD.resultREQ(pstmt);
			String nom,prenom,mail,telephone,mdp;
			Technicien tech = null;
			if(result.next()) {
				nom = result.getString("nom");
				prenom = result.getString("prenom");
				mail = result.getString("mail");
				telephone = result.getString("telephone");
				mdp = result.getString("mdp");
				tech = new Technicien(id,nom,prenom,mail,telephone,mdp);
			}
			return tech;
		}
		
		public static void Add(String nom, String prenom, String mail, String telephone, String mdp) throws SQLException {
			String req = "INSERT INTO Technicien VALUES (?,?,?,?,?,?);";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			int id = 0;
			try {
				id = BD.newIDTechnicien();
			} catch (Exception e) {
				e.printStackTrace();
			}
			pstmt.setInt(1, id);
			pstmt.setString(2, nom);
			pstmt.setString(3, prenom);
			pstmt.setString(4, mail);
			pstmt.setString(5, telephone);
			pstmt.setString(6, mdp);
			BD.executeREQ(pstmt);
		}
		
		public static void Set(int id, String nom, String prenom, String mail, String telephone, String mdp) throws SQLException {
			String req = "UPDATE Technicien nom=?,prenom=?,mail=?,telephone=?,mdp=? WHERE idTech=?;";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setString(1, nom);
			pstmt.setString(2, prenom);
			pstmt.setString(3, mail);
			pstmt.setString(4, telephone);
			pstmt.setString(5, mdp);
			pstmt.setInt(6, id);
			BD.executeREQ(pstmt);
		}
		
		public static void Delete(int id) throws SQLException {
			String req = "DELETE FROM Technicien WHERE idTech=?;";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(1, id);
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
		
		public static ArrayList<Garantie> GetAll() throws SQLException {
			String req = "SELECT * FROM Garantie;";
			ResultSet result = BD.resultREQ(req);
			int idGarantie,duree;//Duree en mois
			String libelle;
			double prix;
			ArrayList<Garantie> res = new ArrayList<Garantie>();
			while(result.next()) {
				idGarantie = result.getInt("idGarantie");
				libelle = result.getString("libelle");
				prix = result.getDouble("prix");
				duree = result.getInt("duree");
				res.add(new Garantie(idGarantie,libelle,prix,duree));
			}
			return res;
		}
		
		public static Garantie Get(int id) throws SQLException {
			String req = "SELECT * FROM Garantie WHERE id=?;";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			ResultSet result = BD.resultREQ(pstmt);
			pstmt.setInt(1, id);
			int idGarantie,duree;//Duree en mois
			String libelle;
			double prix;
			if(result.next()) {
				idGarantie = result.getInt("idGarantie");
				libelle = result.getString("libelle");
				prix = result.getDouble("prix");
				duree = result.getInt("duree");
				return new Garantie(idGarantie,libelle,prix,duree);
			}else {
				return null;
			}
		}
		
		public static void Add(String libelle,double prix,int duree) throws SQLException {
			String req = "INSERT INTO Garantie(idGarantie,libelle,prix,duree) VALUES (?,?,?,?);";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			int id = 0;
			try {
				id = BD.newIDGarantie();
			} catch (Exception e) {
				e.printStackTrace();
			}
			pstmt.setInt(1,id);
			pstmt.setString(2, libelle);
			pstmt.setDouble(3, prix);
			pstmt.setInt(4,duree);
			BD.executeREQ(pstmt);
		}
		
		public static void Set(int id,String libelle,double prix,int duree) throws SQLException {
			String req = "UPDATE Garantie SET libelle=?,prix=?,duree=? WHERE idGarantie=?;";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setString(1, libelle);
			pstmt.setDouble(2, prix);
			pstmt.setInt(3,duree);
			pstmt.setInt(4,id);
			BD.executeREQ(pstmt);
		}
		
		public static void Delete(int id) throws SQLException {
			String req = "DELETE FROM Garantie WHERE idGarantie=?";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(1, id);
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
		
		public static ArrayList<Service> GetAll() throws SQLException {
			String req = "SELECT * FROM Service;";
			ResultSet result = resultREQ(req);
			int id;
			String typeService, denomination,nomDir,prenomDir,telephone,mail,categ,marque,fournisseur;
			long dateAchat,dateDepot;
			ArrayList<Service> res = new ArrayList<Service>();
			while(result.next()) {
				id = result.getInt("idService");
				typeService = result.getString("typeService");
				denomination = result.getString("denomination");
				nomDir = result.getString("nomDir");
				prenomDir = result.getString("prenomDir");
				telephone = result.getString("telephone");
				mail = result.getString("mail");
				categ = result.getString("categ");
				marque = result.getString("marque");
				fournisseur = result.getString("fournisseur");
				dateAchat = result.getLong("dateAchat");
				dateDepot = result.getLong("dateDepot");
				res.add(new Service(id,typeService,denomination,nomDir,prenomDir,telephone,mail,categ,marque,fournisseur,DateSimp.of(dateAchat),DateSimp.of(dateDepot)));
			}
			return res;
		}
		
		public static Service Get(int id) throws SQLException {
			String req = "SELECT * FROM Service WHERE idService=?;";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(1,id);
			ResultSet result = BD.resultREQ(pstmt);
			String typeService, denomination,nomDir,prenomDir,telephone,mail,categ,marque,fournisseur;
			long dateAchat,dateDepot;
			Service res = null;
			if(result.next()) {
				typeService = result.getString("typeService");
				denomination = result.getString("denomination");
				nomDir = result.getString("nomDir");
				prenomDir = result.getString("prenomDir");
				telephone = result.getString("telephone");
				mail = result.getString("mail");
				categ = result.getString("categ");
				marque = result.getString("marque");
				fournisseur = result.getString("fournisseur");
				dateAchat = result.getLong("dateAchat");
				dateDepot = result.getLong("dateDepot");
				res = new Service(id,typeService,denomination,nomDir,prenomDir,telephone,mail,categ,marque,fournisseur,DateSimp.of(dateAchat),DateSimp.of(dateDepot));
			}
			return res;
		}
		
		public static void Add(String type,String denomination,String nomDir,String prenomDir,String telephone,String mail,String categ,String marque,String fournisseur,DateSimp dateAchat,DateSimp dateDepot) throws SQLException {
			String req = "INSERT INTO Service (idService,typeService,denomination,nomDir,prenomDir,telephone,mail,categ,marque,fournisseur,dateAchat,dateDepot) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			int id = 0;
			try {
				id = BD.newIDService();
			} catch (Exception e) {
				e.printStackTrace();
			}
			pstmt.setInt(1, id);
			pstmt.setString(2, type);
			pstmt.setString(3, denomination);
			pstmt.setString(4, nomDir);
			pstmt.setString(5, prenomDir);
			pstmt.setString(6, telephone);
			pstmt.setString(7, mail);
			pstmt.setString(8, categ);
			pstmt.setString(9, marque);
			pstmt.setString(10, fournisseur);
			pstmt.setLong(11, dateAchat.getTimestamp());
			pstmt.setLong(12, dateDepot.getTimestamp());
			BD.executeREQ(pstmt);
		}
		
		public static void Set(int id,String type,String denomination,String nomDir,String prenomDir,String telephone,String mail,String categ,String marque,String fournisseur,DateSimp dateAchat,DateSimp dateDepot) throws SQLException {
			String req = "UPDATE Service SET typeService=?, denomination=?, nomDir=?, prenomDir=?, telephone=?, mail=?, categ=?, marque=?, fournisseur=?, dateAchat=?, dateDepot=? WHERE idService=?;";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setString(1, type);
			pstmt.setString(2, denomination);
			pstmt.setString(3, nomDir);
			pstmt.setString(4, prenomDir);
			pstmt.setString(5, telephone);
			pstmt.setString(6, mail);
			pstmt.setString(7, categ);
			pstmt.setString(8, marque);
			pstmt.setString(9, fournisseur);
			pstmt.setLong(10, dateAchat.getTimestamp());
			pstmt.setLong(11, dateDepot.getTimestamp());
			pstmt.setInt(12, id);
			BD.executeREQ(pstmt);
		}
		
		public static void Delete(int id) throws SQLException {
			String req = "DELETE FROM Service WHERE idService=?;";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(1, id);
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
		
		public static ArrayList<PropositionAchat> GetAll() throws SQLException {
			String req = "SELECT * FROM PropositionAchat;";
			ResultSet result = BD.resultREQ(req);
			int id,idTech,idCentre;
			String anneeFab,categ,coloris;
			ArrayList<PropositionAchat> res = new ArrayList<PropositionAchat>();
			while(result.next()) {
				id = result.getInt("idProposition");
				anneeFab = result.getString("aneeFabrication");
				categ = result.getString("categ");
				coloris = result.getString("coloris");
				idTech = result.getInt("idTech");
				idCentre = result.getInt("idCentre");
				res.add(new PropositionAchat(id,anneeFab,categ,coloris,idTech,idCentre));
			}
			return res;
		}
		
		public static PropositionAchat Get(int id) throws SQLException {
			String req = "SELECT * FROM PropositionAchat WHERE idProposition=?;";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(1, id);
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
				res = new PropositionAchat(id,anneeFab,categ,coloris,idTech,idCentre);
			}
			return res;
		}
		
		public static void Add(String anneeFab,String categ,String coloris,int idTech,int idCentre) throws SQLException {
			String req = "INSERT INTO PropositionAchat VALUES (?,?,?,?,?,?);";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			int id = 0;
			try {
				id = BD.newIDPropositionAchat();
			} catch (Exception e) {
				e.printStackTrace();
			}
			pstmt.setInt(1, id);
			pstmt.setString(2, anneeFab);
			pstmt.setString(3,categ);
			pstmt.setString(4, coloris);
			pstmt.setInt(5, idTech);
			pstmt.setInt(6, idCentre);
			BD.executeREQ(pstmt);
		}
		
		public static void Set(int id, String anneeFab,String categ,String coloris,int idTech,int idCentre) throws SQLException  {
			String req = "UPDATE PropositionAchat SET anneFabrication=?,categ=?,coloris=?,idTech=?,idCentre? WHERE idProposition=?;";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setString(1, anneeFab);
			pstmt.setString(2,categ);
			pstmt.setString(3, coloris);
			pstmt.setInt(4, idTech);
			pstmt.setInt(5, idCentre);
			pstmt.setInt(6, id);
			BD.executeREQ(pstmt);
		}
		
		public static void Delete(int id) throws SQLException {
			String req = "DELETE FROM PropositionAchat WHERE idProposition=?;";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(1, id);
			BD.executeREQ(pstmt);
		}
		
		
	}
	
	public static abstract class RDVData{
		public static ArrayList<Rdv> GetAll() throws SQLException{
			ArrayList<Rdv> rdvList = new ArrayList<Rdv>();
			String req = "select * from Rdv;";
			ResultSet result = BD.resultREQ(req);
			String deno, nomDir, prenomDir, mail, telephone, heureRdv, motif;
			int id,tech;
			DateSimp dateRdv;
			while(result.next()) {
				id = result.getInt("idRdv");
				deno = result.getString("denomination");
				nomDir = result.getString("nomDir");
				prenomDir = result.getString("prenomDir");
 				mail = result.getString("mail");
				telephone = result.getString("telephone");
				heureRdv = result.getString("heureRdv");
				motif = result.getString("motif");
				dateRdv = DateSimp.of(result.getLong("dateRdv"));
				tech = result.getInt("techId");
				rdvList.add(new Rdv(id,deno,nomDir,prenomDir,telephone,mail,dateRdv,heureRdv,motif,tech));
			}
			return rdvList;
		}
		
		public static Rdv Get(int id) throws SQLException{
			Rdv rdv = null;
			String req = "select * from Rdv where idRdv=?;";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(1, id);
			ResultSet result = BD.resultREQ(pstmt);
			String deno, nomDir, prenomDir, mail, telephone, heureRdv, motif;
			int tech;
			DateSimp dateRdv;
			if(result.next()) {
				id = result.getInt("idRdv");
				deno = result.getString("denomination");
				nomDir = result.getString("nomDir");
				prenomDir = result.getString("prenomDir");
 				mail = result.getString("mail");
				telephone = result.getString("telephone");
				heureRdv = result.getString("heureRdv");
				motif = result.getString("motif");
				dateRdv = DateSimp.of(result.getLong("dateRdv"));
				tech = result.getInt("techId");
				rdv = new Rdv(id,deno,nomDir,prenomDir,telephone,mail,dateRdv,heureRdv,motif,tech);
			}
			return rdv;
		}
		
		public static void Add(String deno, String nomDir, String prenomDir, String mail, String telephone,DateSimp dateRdv,String heureRdv,String motif,int tech) throws SQLException {
			String req = "INSERT INTO Rdv VALUES (?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			try {
				pstmt.setInt(1, BD.newIDRdv());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pstmt.setString(2,deno);
			pstmt.setString(3,nomDir);
			pstmt.setString(4,prenomDir);
			pstmt.setString(5,telephone);
			pstmt.setString(6,mail);
			pstmt.setLong(7,dateRdv.getTimestamp());
			pstmt.setString(8,heureRdv);
			pstmt.setString(9,motif);
			pstmt.setInt(10,tech);
			BD.executeREQ(pstmt);
		}
		
		public static void Set(int id,String deno,String nomDir,String prenomDir,String telephone,String mail,DateSimp dateRdv,String heureRdv,String motif,int idTech) throws SQLException {
			String req = "UPDATE Rdv SET "
					+ "denomination=?,nomDir=?,prenomDir=?,telephone=?,mail=?,dateRdv=?,heureRdv=?,motif=?,techId=? "
					+ "WHERE idRdv=?;";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setString(1,deno);
			pstmt.setString(2,nomDir);
			pstmt.setString(3,prenomDir);
			pstmt.setString(4,telephone);
			pstmt.setString(5,mail);
			pstmt.setLong(6,dateRdv.getTimestamp());
			pstmt.setString(7,heureRdv);
			pstmt.setString(8,motif);
			pstmt.setInt(9, idTech);
			pstmt.setInt(10, id);
			BD.executeREQ(pstmt);
		}
		
		public static void Delete(int id) throws SQLException {
			String req = "DELETE FROM Rdv WHERE idRdv=?";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(1, id);
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
		
		public static ArrayList<LigneCommande> GetAll() throws SQLException {
			String req = "SELECT * FROM LigneCommande;";
			ResultSet result = BD.resultREQ(req);
			ArrayList<LigneCommande> res = new ArrayList<LigneCommande>();
			int idMateriel,idCentre,idGarantie;
			Long dateCommande;
			while(result.next()) {
				idMateriel = result.getInt("idMateriel");
				idCentre = result.getInt("idCentre");
				idGarantie = result.getInt("idGarantie");
				dateCommande = result.getLong("dateCommande");
				res.add(new LigneCommande(idMateriel,idCentre,idGarantie,DateSimp.of(dateCommande)));
			}
			return res;
		}
		
		public static LigneCommande Get(int id) throws SQLException {
			String req = "SELECT * FROM LigneCommande WHERE idMateriel=?;";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(1, id);
			ResultSet result = BD.resultREQ(pstmt);
			LigneCommande res = null;
			int idMateriel,idCentre,idGarantie;
			Long dateCommande;
			while(result.next()) {
				idMateriel = result.getInt("idMateriel");
				dateCommande = result.getLong("dateCommande");
				idCentre = result.getInt("idCentre");
				idGarantie = result.getInt("idGarantie");
				res = new LigneCommande(idMateriel,idCentre,idGarantie,DateSimp.of(dateCommande));
			}
			return res;
		}
		
		public static void Add(int idMateriel, DateSimp dateCommande, int idCentre, int idGarantie) throws SQLException {
			String req = "INSERT INTO LigneCommande(idMateriel,dateCommande,idCentre,idGarantie) VALUES (?,?,?,?)";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(1,idMateriel);
			pstmt.setLong(2, dateCommande.getTimestamp());
			pstmt.setInt(3, idCentre);
			pstmt.setInt(4, idGarantie);
			BD.executeREQ(pstmt);
		}
		
		public static void Set(int idMateriel, DateSimp dateCommande, int idCentre, int idGarantie) throws SQLException {
			String req = "UPDATE LigneCommande SET dateCommande=?,idCentre=?,idGarantie=? WHERE idMateriel=?";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(4,idMateriel);
			pstmt.setLong(1, dateCommande.getTimestamp());
			pstmt.setInt(2, idCentre);
			pstmt.setInt(3, idGarantie);
			BD.executeREQ(pstmt);
		}
		
		public static void Delete(int id) throws SQLException {
			String req = "DELETE FROM LigneCommande WHERE idMateriel=?";
			PreparedStatement pstmt = BD.newPreparedSmt(req);
			pstmt.setInt(1, id);
			BD.executeREQ(pstmt);
		}
		
	}
	
	public static int getNbRow(ResultSet rst) throws SQLException {
		int rowCount = 0;
		while (rst.next()) {
		    rowCount = rst.getRow();
		}
		rst.absolute(0);
		return rowCount;
	}
	
	public static String[] GetHeuresRdv() {
		String[] heures = {"08:00","08:30","09:00","09:30","10:00","10:30","11:00","11:30","12:00","14:00","14:30","15:00","15:30","16:00"};
		return heures;
	}
	
	public static String[] GetTypesCentre() {
		String[] types = {"Institut","Hotel","Licencié"};
		return types;
	}
	
	public static String[] GetMaterielCategorie() {
		String[] categs = {"Running","Bike"};
		return categs;
	}
	
	public static String[] GetTypeService() {
		String[] types = {"Estimation","Désinstallation","Révision complète"};
		return types;
	}
	
	public static String GetTypesCentre(int i) throws Exception{
		String[] types = {"Institut","Hotel","Licencié"};
		return types[i];
	}
	
	public static String GetMaterielCategorie(int i) throws Exception{
		String[] categs = {"Running","Bike"};
		return categs[i];
	}
	
	public static String GetTypeService(int i) throws Exception{
		String[] types = {"Estimation","Désinstallation","Révision complète"};
		return types[i];
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
		try {
			ArrayList<Centre> centres = BD.CentreData.GetAll();
			System.out.println(centres.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}