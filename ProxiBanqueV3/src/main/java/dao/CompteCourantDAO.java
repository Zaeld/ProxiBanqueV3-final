package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
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
			// Cr�ation d'un objet de type Statement
			PreparedStatement stmt = Connexion.connexion().prepareStatement(
					"INSERT INTO `compte`(`numeroCompte`, `decouvertAutorise`, `solde`, `typeCarte`, `idClient`, `typeDeCompte`)"
							+ " VALUES (?, ?, ?, ?, ?, ?)");
			stmt.setInt(1, compte.getNumCompte());
			stmt.setDouble(2, compte.getDecouvertAutorise());
			stmt.setDouble(3, compte.getSolde());
			stmt.setString(4, compte.getTypeCarte());
			stmt.setInt(5, compte.getIdClient());
			stmt.setString(6, compte.getTypeDeCompte());

			// ex�cution de la requ�te
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

	// M�thode permettant de r�cup�rer les informations d'un compte de la table
	// 'compte' en prenant comme param�tre d'entr�e un objet de type Courant
	// pr�sentant comme
	// attribut l'idCompte du compte � r�cup�rer. La m�thode retourne un objet
	// compte pr�sentants les informations r�cup�r�es.
	/**
	 * @param compte
	 * @return
	 */
	public Courant getCourantNumCompte(Courant compte) {
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
			compte.setDecouvertAutorise(rs.getDouble("decouvertAutorise"));
			compte.setSolde(rs.getDouble("solde"));
			compte.setTypeCarte(rs.getString("typeCarte"));

		} catch (SQLException e) {
			return null;
		}
		return compte;
	}

	public Courant getCourant(Courant compte) {
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
			compte.setDecouvertAutorise(rs.getDouble("decouvertAutorise"));
			compte.setSolde(rs.getDouble("solde"));
			compte.setTypeCarte(rs.getString("typeCarte"));

		} catch (SQLException e) {
			return null;
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

			// Cr�ation d'un objet de type Statement
			PreparedStatement stmt = Connexion.connexion().prepareStatement(
					"UPDATE compte set numeroCompte = ?, decouvertAutorise = ?, solde = ?, typeCarte = ? where idcompte = ?");
			stmt.setInt(1, compte.getNumCompte());
			stmt.setDouble(2, compte.getDecouvertAutorise());
			stmt.setDouble(3, compte.getSolde());
			stmt.setString(4, compte.getTypeCarte());
			stmt.setInt(5, compte.getIdCompte());

			// ex�cution de la requ�te
			stmt.executeUpdate();

			// Affectation � la chaine de caract�re s de la requ�te SQL
			PreparedStatement preparedStatement = Connexion.connexion()
					.prepareStatement("Select * from compte where idCompte = ?"); // Creation d'un objet de type
																					// Statement
			preparedStatement.setInt(1, compte.getIdCompte());
			// ex�cution de la requ�te

			ResultSet rs = preparedStatement.executeQuery();

			// Lecture des r�sultats de la requ�te
			rs.next();
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

	// M�thode retournant une liste de toute les entr�es de la table 'compte' de la
	// base de donn�e avec en attribut
	// typeCompte = 'courant'.
	/**
	 * @return
	 */
	public List<Courant> getAllCourant() {
		List<Courant> listCCourant = new ArrayList<Courant>();
		try {
			// Cr�ation d'un objet de type Statement
			PreparedStatement preparedStatement = Connexion.connexion()
					.prepareStatement("Select * from compte where typeDeCompte = 'courant'");

			// ex�cution de la requete
			ResultSet rs = preparedStatement.executeQuery();
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
