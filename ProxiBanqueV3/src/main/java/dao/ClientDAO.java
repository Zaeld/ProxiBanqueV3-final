package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domaine.Courant;
import domaine.Epargne;

import domaine.CompteBancaire;

import dao.Connexion;
import domaine.Conseiller;

import domaine.Client;


public class ClientDAO {
	
	public boolean createClient(Client client) {
		int i = 0;
		boolean b = false;
		try {
			Statement stmt = Connexion.connexion().createStatement(); // Creation d'un objet de type Statement

			// Affectation a la chaine de caractere s de la requete SQL
			String s = "INSERT INTO `client`(`adresse`, `nom`, `prenom`, `codePostal`, `ville`, `email`, `idConseiller`, `telephone`, `soldeTotal`) VALUES ('"
					+ client.getAdresse() + "', '" + client.getNom() + "', '" + client.getPrenom() + "', '"
					+ client.getCodePostal() + "', '" + client.getVille() + "', '" + client.getEmail() + "', "
					+ client.getidConseiller() + ", '" + client.getTelephone() + "', " + client.getSoldeTotal() + ")";

			// execution de la requete
			i = stmt.executeUpdate(s);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Si l'operation est un succes, i est differend de 0 et la methode
		// retourne
		// true
		if (i != 0)
			b = true;
		return b;

	}

	// Methode permettant de recuperer les informations d'un client de la
	// table
	// 'client' en prenant comme parametre d'entree un objet client presentant
	// comme
	// attribut l'idClient du client a recuperer. La methode retourne un
	// objet
	// client presentants les informations recuperees.
	/**
	 * @param client
	 * @return
	 */
	public Client getClient(Client client) {
		try {
			Statement stmt = Connexion.connexion().createStatement(); // Creation d'un objet de type Statement

			// Affectation ï¿½ la chaine de caractï¿½re s de la requï¿½te SQL
			String s = "Select * from Client where idClient = " + client.getIdClient();

			// Exï¿½cution de la requï¿½te
			ResultSet rs = stmt.executeQuery(s);

			// Lecture des rï¿½sultats de la requï¿½te
			rs.next();
			client.setIdClient(rs.getInt("idClient"));
			client.setAdresse(rs.getString("adresse"));
			client.setNom(rs.getString("nom"));
			client.setPrenom(rs.getString("prenom"));
			client.setCodePostal(rs.getString("codePostal"));
			client.setVille(rs.getString("ville"));
			client.setEmail(rs.getString("email"));
			client.setidConseiller(rs.getInt("idConseiller"));
			client.setTelephone(rs.getString("telephone"));
			client.setSoldeTotal(rs.getInt("soldeTotal"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return client;

	}

	// Mï¿½thode permettant de modifier les informations d'un client de la table
	// 'client' en prenant comme paramï¿½tre d'entrï¿½e un objet client prï¿½sentant
	// les
	// nouvelles valeurs d'attributs . La mï¿½thode retourne l'objet client
	// rï¿½cupï¿½rï¿½
	// de la base de donnï¿½e avec les nouvelles valeurs d'attribut.
	/**
	 * @param client
	 * @return
	 */
	public Client updateClient(Client client) {
		try {
			Statement stmt = Connexion.connexion().createStatement(); // Crï¿½ation d'un objet de type Statement

			// Affectation ï¿½ la chaine de caractï¿½re s de la requï¿½te SQL
			String s = "UPDATE client set adresse = '" + client.getAdresse() + "', nom = '" + client.getNom()
					+ "', prenom = '" + client.getPrenom() + "', CodePostal = '" + client.getCodePostal()
					+ "', ville = '" + client.getVille() + "', email = '" + client.getEmail() + "', idConseiller = "
					+ client.getidConseiller() + ", telephone = '" + client.getTelephone() + "', soldeTotal = "
					+ client.getSoldeTotal() + " where idClient = " + client.getIdClient() + "";
			// exï¿½cution de la requï¿½te
			stmt.executeUpdate(s);

			// Affectation ï¿½ la chaine de caractï¿½re s de la requï¿½te SQL
			s = "Select * from client where idClient = " + client.getIdClient();
			ResultSet rs = stmt.executeQuery(s);
			// Lecture des rï¿½sultats de la requï¿½te
			rs.first();
			client.setIdClient(rs.getInt("idClient"));
			client.setAdresse(rs.getString("adresse"));
			client.setNom(rs.getString("nom"));
			client.setPrenom(rs.getString("prenom"));
			client.setCodePostal(rs.getString("codePostal"));
			client.setVille(rs.getString("ville"));
			client.setEmail(rs.getString("email"));
			client.setidConseiller(rs.getInt("idConseiller"));
			client.setTelephone(rs.getString("telephone"));
			client.setSoldeTotal(rs.getInt("soldeTotal"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return client;
	}

	// Mï¿½thode permettant de suppimer l'entrï¿½e de la table 'client' prï¿½sentant
	// le
	// mï¿½me idClient que l'objet client en paramï¿½tre d'entrï¿½e de mï¿½thode et
	// retournant un boolean de valeur true si
	// l'opï¿½ration a ï¿½tï¿½ un succï¿½s.
	/**
	 * @param client
	 * @return
	 */
	public boolean deleteClient(Client client) {

		int i;
		boolean b = false;
		try {

			Statement stmt = Connexion.connexion().createStatement(); // Crï¿½ation d'un objet de type Statement

			// Affectation ï¿½ la chaine de caractï¿½re s de la requï¿½te SQL, il est
			// nï¿½cessaire
			// de supprimer les comptes du client avant de le supprimer car la table
			// 'compte' est dï¿½pendante de la table 'client'

			String s = "DELETE FROM compte where idClient = " + client.getIdClient();

			// exï¿½cution de la requï¿½te
			i = stmt.executeUpdate(s);

			// Affectation ï¿½ la chaine de caractï¿½re s de la requï¿½te SQL
			s = "DELETE FROM client where idClient = " + client.getIdClient();

			// exï¿½cution de la requï¿½te
			i = stmt.executeUpdate(s);
			// Si l'opï¿½ration est un succï¿½s, i est diffï¿½rend de 0 et la mï¿½thode
			// retourne
			// true
			if (i != 0)
				b = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	// Mï¿½thode retournant une liste de toute les entrï¿½es de la table 'client' de
	// la
	// base de donnï¿½e.
	/**
	 * @return
	 */
	public List<Client> getAllClient() {
		List<Client> listClient = new ArrayList<Client>();

		try {
			Statement stmt = Connexion.connexion().createStatement(); // Crï¿½ation d'un objet de type Statement

			// Affectation ï¿½ la chaine de caractï¿½re s de la requï¿½te SQL
			String s = "SELECT * FROM client";

			// exï¿½cution de la requï¿½te
			ResultSet rs = stmt.executeQuery(s);
			// Lecture des rï¿½sultats de la requï¿½te et insertion dans la liste pour
			// chaque
			// boucle
			while (rs.next()) {
				Client client = new Client();
				client.setIdClient(rs.getInt("idClient"));
				client.setAdresse(rs.getString("adresse"));
				client.setNom(rs.getString("nom"));
				client.setPrenom(rs.getString("prenom"));
				client.setCodePostal(rs.getString("codePostal"));
				client.setVille(rs.getString("ville"));
				client.setEmail(rs.getString("email"));
				client.setidConseiller(rs.getInt("idConseiller"));
				client.setTelephone(rs.getString("telephone"));
				client.setSoldeTotal(rs.getInt("soldeTotal"));
				listClient.add(client);
			}
		} catch (SQLException e) {
			return null;
		}
		return listClient;
	}

	public List<Client> getAllClientConseiller(Conseiller conseiller) {
		List<Client> listClient = new ArrayList<Client>();

		try {
			Statement stmt = Connexion.connexion().createStatement(); // Crï¿½ation d'un objet de type Statement

			// Affectation ï¿½ la chaine de caractï¿½re s de la requï¿½te SQL
			String s = "SELECT * FROM client WHERE idConseiller = " + conseiller.getIdConseiller();

			// exï¿½cution de la requï¿½te
			ResultSet rs = stmt.executeQuery(s);
			// Lecture des rï¿½sultats de la requï¿½te et insertion dans la liste pour
			// chaque
			// boucle
			while (rs.next()) {
				Client client = new Client();
				client.setIdClient(rs.getInt("idClient"));
				client.setAdresse(rs.getString("adresse"));
				client.setNom(rs.getString("nom"));
				client.setPrenom(rs.getString("prenom"));
				client.setCodePostal(rs.getString("codePostal"));
				client.setVille(rs.getString("ville"));
				client.setEmail(rs.getString("email"));
				client.setidConseiller(rs.getInt("idConseiller"));
				client.setTelephone(rs.getString("telephone"));
				client.setSoldeTotal(rs.getInt("soldeTotal"));
				listClient.add(client);
			}
		} catch (SQLException e) {
			return null;
		}
		return listClient;
	}
	public List<CompteBancaire> getAllCompte(Client client){
		List<CompteBancaire> listCompte = new ArrayList<CompteBancaire>();
		listCompte.add(getCompteCourant(client));
		listCompte.add(getCompteEpargne(client));
		return listCompte;

	}
	public Courant getCompteCourant(Client client) {
		Courant compte = new Courant();
		try {
			Statement stmt = Connexion.connexion().createStatement(); // Crï¿½ation d'un objet de type Statement

			// Affectation ï¿½ la chaine de caractï¿½re s de la requï¿½te SQL
			String s = "Select * from compte inner Join client on compte.idClient = client.idClient where typeDeCompte = 'courant' && client.idClient = "
					+ client.getIdClient();

			// exï¿½cution de la requï¿½te
			ResultSet rs = stmt.executeQuery(s);
			// Lecture des rï¿½sultats de la requï¿½te
			rs.first();
			compte.setIdClient(rs.getInt("IdClient"));
			compte.setIdCompte(rs.getInt("IdCompte"));
			compte.setNumeroCompte(rs.getInt("numeroCompte"));
			compte.setDecouvertAutorise(rs.getDouble("decouvertAutorise"));
			compte.setSolde(rs.getDouble("solde"));
			compte.setTypeCarte(rs.getString("typeCarte"));
		} catch (SQLException e) {
			return null;
		}
		return compte;
	}

	public Epargne getCompteEpargne(Client client) {
		Epargne compte = new Epargne();

		try {
			Statement stmt = Connexion.connexion().createStatement(); // Crï¿½ation d'un objet de type Statement

			// Affectation ï¿½ la chaine de caractï¿½re s de la requï¿½te SQL
			String s = "Select * from compte inner Join client on compte.idClient = client.idClient where typeDeCompte = 'epargne' && client.idClient = "
					+ client.getIdClient();

			// exï¿½cution de la requï¿½te
			ResultSet rs = stmt.executeQuery(s);
			// Lecture des rï¿½sultats de la requï¿½te
			rs.next();
			compte.setIdClient(rs.getInt("IdClient"));
			compte.setIdCompte(rs.getInt("IdCompte"));
			compte.setNumeroCompte(rs.getInt("numeroCompte"));
			compte.setSolde(rs.getDouble("solde"));
			compte.setTauxInteret(rs.getDouble("tauxInteret"));

		} catch (SQLException e) {
			return null;
		}
		return compte;
	}
	

}