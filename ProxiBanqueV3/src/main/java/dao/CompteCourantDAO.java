package dao;

import java.sql.ResultSet;



import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domaine.Courant;

/**
 * @author Stagiaire
 *
 */
public class CompteCourantDAO {
	// CompteCourantDAO est la classe dans la couche DAO qui permet d'acc�der aux
	// informations de la table 'Compte'de la base de donn�e sp�cifi�e dans la
	// classe Connexion.
	// Un compte courant est une entr�e de la table 'compte' avec en attribut
	// typeCompte = 'courant'.
	// Cette classe permet de cr�er, lire les informations, modifier, supprimer un
	// compte courant, et de r�cup�rer la liste de la totalit� des
	// comptes courant.

	// M�thode permettant d'ins�rer un compte en base de donn�e en prenant un objet
	// de type compte en entr�e et retournant un boolean de valeur true si
	// l'op�ration a �t� un succ�s.
	/**
	 * @param compte
	 * @return
	 */
	public boolean createCourant(Courant compte) {
		int i = 0;
		boolean b = false;
		try {
			// Affectation � la chaine de caract�re s de la requ�te SQL
			String s = "INSERT INTO `compte`(`numeroCompte`, `decouvertAutorise`, `solde`, `typeCarte`, `idClient`, `typeDeCompte`)"
					+ " VALUES ( " + compte.getNumeroCompte() + ", " + compte.getDecouvertAutorise() + ", "
					+ compte.getSolde() + ", '" + compte.getTypeCarte() + "', " + compte.getIdClient() + ", '"+compte.getTypeDeCompte()+")";
			Statement stmt = Connexion.connexion().prepareStatement(s); // Cr�ation d'un objet de type Statement
			// ex�cution de la requ�te
			i = stmt.executeUpdate(s);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Si l'op�ration est un succ�s, i est diff�rend de 0 et la m�thode retourne
		// true
		if (i != 0)
			b = true;
		return b;
	}

	// M�thode permettant de r�cup�rer les informations d'un compte de la table
	// 'compte' en prenant comme param�tre d'entr�e un objet de type Courant
	// pr�sentant comme
	// attribut l'idCompte du compte � r�cup�rer. La m�thode retourne un objet
	// compte pr�sentants les informations r�cup�r�es.
	/**
	 * @param compte
	 * @return
	 */
	public Courant getCourant(Courant compte) {
		try {
			Statement stmt = Connexion.connexion().createStatement(); // Cr�ation d'un objet de type Statement

			// Affectation � la chaine de caract�re s de la requ�te SQL
			String s = "Select * from compte where idCompte = " + compte.getIdCompte();
			// ex�cution de la requ�te
			ResultSet rs = stmt.executeQuery(s);
			// Lecture des r�sultats de la requ�te
			rs.next();
			compte.setIdClient(rs.getInt("IdClient"));
			compte.setIdCompte(rs.getInt("IdCompte"));
			compte.setNumeroCompte(rs.getInt("numeroCompte"));
			compte.setDecouvertAutorise(rs.getDouble("decouvertAutorise"));
			compte.setSolde(rs.getDouble("solde"));
			compte.setTypeCarte(rs.getString("typeCarte"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return compte;
	}

	// M�thode permettant de modifier les informations d'un client de la table
	// 'compte' en prenant comme param�tre d'entr�e un objet compte pr�sentant les
	// nouvelles valeurs d'attributs. La m�thode retourne l'objet compte r�cup�r�
	// de la base de donn�e avec les nouvelles valeurs d'attribut.
	/**
	 * @param compte
	 * @return
	 */
	public Courant updateCourant(Courant compte) {
		try {

			Statement stmt = Connexion.connexion().createStatement(); // Cr�ation d'un objet de type Statement

			// Affectation � la chaine de caract�re s de la requ�te SQL
			String s = "UPDATE compte set numeroCompte = '" + compte.getNumeroCompte() + "', decouvertAutorise = '"
					+ compte.getDecouvertAutorise() + "', solde = " + compte.getSolde() + ", typeCarte = '"
					+ compte.getTypeCarte() + "' where idcompte = " + compte.getIdCompte();
			// ex�cution de la requ�te
			stmt.executeUpdate(s);
			// Affectation � la chaine de caract�re s de la requ�te SQL
			s = "Select * from compte where idClient = " + compte.getIdCompte();
			// ex�cution de la requ�te
			ResultSet rs = stmt.executeQuery(s);
			// Lecture des r�sultats de la requ�te
			rs.first();
			compte.setIdClient(rs.getInt("IdClient"));
			compte.setIdCompte(rs.getInt("IdCompte"));
			compte.setNumeroCompte(rs.getInt("numeroCompte"));
			compte.setDecouvertAutorise(rs.getDouble("decouvertAutorise"));
			compte.setSolde(rs.getDouble("solde"));
			compte.setTypeCarte(rs.getString("typeCarte"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return compte;
	}

	// M�thode permettant de suppimer l'entr�e de la table 'compte' pr�sentant le
	// m�me idCompte que l'objet compte en param�tre d'entr�e de m�thode et
	// retournant un boolean de valeur true si
	// l'op�ration a �t� un succ�s.
	/**
	 * @param compte
	 * @return
	 */
	public boolean deleteCourant(Courant compte) {
		int i;
		boolean b = false;
		try {
			Statement stmt = Connexion.connexion().createStatement(); // Cr�ation d'un objet de type Statement

			// Affectation � la chaine de caract�re s de la requ�te SQL
			String s = "DELETE from compte where idCompte = " + compte.getIdCompte();
			// ex�cution de la requ�te
			i = stmt.executeUpdate(s);
			// Si l'op�ration est un succ�s, i est diff�rend de 0 et la m�thode retourne
			// true
			if (i != 0)
				b = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	// M�thode retournant une liste de toute les entr�es de la table 'compte' de la
	// base de donn�e avec en attribut
	// typeCompte = 'courant'.
	/**
	 * @return
	 */
	public List<Courant> getAllCourant() {
		List<Courant> listCCourant = new ArrayList<Courant>();
		try {
			Statement stmt = Connexion.connexion().createStatement(); // Cr�ation d'un objet de type Statement

			// Affectation � la chaine de caract�re s de la requ�te SQL
			String s = "Select * from compte where typeDeCompte = 'courant'";
			// ex�cution de la requete
			ResultSet rs = stmt.executeQuery(s);
			// Lecture des r�sultats de la requ�te et insertion dans la liste pour chaque
			// boucle
			while (rs.next()) {
				Courant compte = new Courant();
				compte.setIdClient(rs.getInt("IdClient"));
				compte.setIdCompte(rs.getInt("IdCompte"));
				compte.setNumeroCompte(rs.getInt("numeroCompte"));
				compte.setDecouvertAutorise(rs.getDouble("decouvertAutorise"));
				compte.setSolde(rs.getDouble("solde"));
				compte.setTypeCarte(rs.getString("typeCarte"));
				listCCourant.add(compte);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listCCourant;
	}
}
