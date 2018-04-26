package fr.gtm.dao;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.gtm.domaine.Epargne;

/**
 * CompteEpargneDAO est la classe dans la couche DAO qui permet d'accéder aux
 * informations de la table 'Compte'de la base de donnée spécifiée dans la
 * classe Connexion. Un compte épargne est une entrée de la table 'compte' avec
 * en attribut typeCompte = 'épargne'. Cette classe permet de créer, lire les
 * informations, modifier, supprimer un compte épargne, et de récupérer la liste
 * de la totalité des comptes épargnes.
 * 
 * @author Stagiaire
 *
 */
public class CompteEpargneDAO {

	/**
	 * Méthode permettant d'insérer un compte en base de donn�e en prenant un objet
	 * de type compte en entrée et retournant un boolean de valeur true si
	 * l'opération a été un succes.
	 * 
	 * @param compte
	 * @return
	 */
	public boolean createEpargne(Epargne compte) {
		int i = 0;
		boolean b = false;
		try {
			// Cr�ation d'un objet de type Statement
			PreparedStatement stmt = Connexion.connexion().prepareStatement(
					"INSERT INTO `compte`(`numeroCompte`, `tauxInteret`, `solde`, `idClient`, `typeDeCompte`)"
							+ " VALUES (?, ?, ?, ?, ?)");
			stmt.setInt(1, compte.getNumCompte());
			stmt.setDouble(2, compte.getTauxInteret());
			stmt.setDouble(3, compte.getSolde());
			stmt.setInt(4, compte.getIdClient());
			stmt.setString(5, compte.getTypeDeCompte());

			// ex�cution de la requete
			i = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Si l'op�ration est un succ�s, i est diff�rend de 0 et la m�thode retourne
		// true
		if (i != 0)
			b = true;
		return b;

	}

	/**
	 * Méthode permettant de récupérer les informations d'un compte de la table
	 * 'compte' en prenant comme parametre d'entrée un objet de type Epargne
	 * présentant comme attribut le numéro de compte du compte à récupérer. La
	 * méthode retourne un objet compte présentants les informations récupérées.
	 * 
	 * @param compte
	 * @return
	 */
	public Epargne getEpargneNumCompte(Epargne compte) {
		try {
			PreparedStatement stmt = Connexion.connexion()
					.prepareStatement("Select * from compte where numeroCompte = ?");
			stmt.setInt(1, compte.getNumCompte());

			// ex�cution de la requ�te
			ResultSet rs = stmt.executeQuery();
			// Lecture des r�sultats de la requ�te
			rs.next();
			compte.setIdClient(rs.getInt("IdClient"));
			compte.setIdCompte(rs.getInt("IdCompte"));
			compte.setNumeroCompte(rs.getInt("numeroCompte"));
			compte.setTauxInteret(rs.getDouble("tauxInteret"));
			compte.setSolde(rs.getDouble("solde"));

		} catch (SQLException e) {
			return null;
		}
		return compte;
	}

	/**
	 * Méthode permettant de récupérer les informations d'un compte de la table
	 * 'compte' en prenant comme parametre d'entrée un objet de type Epargne
	 * présentant comme attribut l'idCompte du compte à récupérer. La méthode
	 * retourne un objet compte présentants les informations récupérées.
	 * 
	 * @param compte
	 * @return
	 */
	public Epargne getEpargne(Epargne compte) {
		try {
			PreparedStatement stmt = Connexion.connexion().prepareStatement("Select * from compte where idCompte = ?");
			stmt.setInt(1, compte.getIdCompte());

			// ex�cution de la requ�te
			ResultSet rs = stmt.executeQuery();
			// Lecture des r�sultats de la requ�te
			rs.next();
			compte.setIdClient(rs.getInt("IdClient"));
			compte.setIdCompte(rs.getInt("IdCompte"));
			compte.setNumeroCompte(rs.getInt("numeroCompte"));
			compte.setTauxInteret(rs.getDouble("tauxInteret"));
			compte.setSolde(rs.getDouble("solde"));

		} catch (SQLException e) {
			return null;
		}
		return compte;
	}

	/**
	 * Méthode permettant de modifier les informations d'un client de la table
	 * 'compte' en prenant comme param�tre d'entrée un objet compte présentant les
	 * nouvelles valeurs d'attributs. La méthode retourne l'objet compte récupéré de
	 * la base de donnée avec les nouvelles valeurs d'attribut.
	 * 
	 * @param compte
	 * @return
	 */
	public Epargne updateEpargne(Epargne compte) {
		try {
			// Cr�ation d'un objet de type Statement

			PreparedStatement stmt = Connexion.connexion().prepareStatement(
					"UPDATE compte set numeroCompte = ?, tauxInteret = ?, solde = ? where idcompte = ?");
			stmt.setInt(1, compte.getNumCompte());
			stmt.setDouble(2, compte.getTauxInteret());
			stmt.setDouble(3, compte.getSolde());
			stmt.setInt(4, compte.getIdCompte());

			stmt.executeUpdate();
			// ex�cution de la requ�te

			PreparedStatement preparedStatement = Connexion.connexion()
					.prepareStatement("Select * from compte where idCompte = ?"); // Creation d'un objet de type
																					// Statement
			preparedStatement.setInt(1, compte.getIdCompte());

			ResultSet rs = preparedStatement.executeQuery();

			// Lecture des r�sultats de la requ�te
			rs.next();
			compte.setIdClient(rs.getInt("IdClient"));
			compte.setIdCompte(rs.getInt("IdCompte"));
			compte.setNumeroCompte(rs.getInt("numeroCompte"));
			compte.setTauxInteret(rs.getDouble("tauxInteret"));
			compte.setSolde(rs.getDouble("solde"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return compte;
	}

	/**
	 * Méthode permettant de suppimer l'entrée de la table 'compte' présentant le
	 * meme idCompte que l'objet compte en parametre d'entree de methode et
	 * retournant un boolean de valeur true si l'operation a été un succes
	 * 
	 * @param compte
	 * @return
	 */
	public boolean deleteEpargne(Epargne compte) {
		int i;
		boolean b = false;
		try {
			// Cr�ation d'un objet de type Statement
			PreparedStatement preparedStatement = Connexion.connexion()
					.prepareStatement("DELETE from compte where idCompte = ?");
			preparedStatement.setInt(1, compte.getIdCompte());

			// ex�cution de la requ�te
			i = preparedStatement.executeUpdate();
			// Si l'op�ration est un succ�s, i est diff�rend de 0 et la m�thode retourne
			// true
			if (i != 0)
				b = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * Méthode retournant une liste de toute les entrées de la table 'compte' de la
	 * base de donnée avec en attribut typeCompte = 'épargne'
	 * 
	 * @return
	 */
	public List<Epargne> getAllEpargne() {
		List<Epargne> listCEpargne = new ArrayList<Epargne>();
		try {
			// Cr�ation d'un objet de type Statement
			PreparedStatement preparedStatement = Connexion.connexion()
					.prepareStatement("Select * from compte where typeDeCompte = 'epargne'");

			// ex�cution de la requete
			ResultSet rs = preparedStatement.executeQuery();
			// Lecture des r�sultats de la requ�te et insertion dans la liste pour chaque
			// boucle
			while (rs.next()) {
				Epargne compte = new Epargne();
				compte.setIdClient(rs.getInt("IdClient"));
				compte.setIdCompte(rs.getInt("IdCompte"));
				compte.setNumeroCompte(rs.getInt("numeroCompte"));
				compte.setTauxInteret(rs.getDouble("tauxInteret"));
				compte.setSolde(rs.getDouble("solde"));
				listCEpargne.add(compte);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listCEpargne;
	}
}
