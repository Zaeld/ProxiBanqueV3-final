package dao;

import java.sql.ResultSet;



import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domaine.Epargne;

/**
 * @author Stagiaire
 *
 */
public class CompteEpargneDAO {
	// CompteEpargneDAO est la classe dans la couche DAO qui permet d'acc�der aux
	// informations de la table 'Compte'de la base de donn�e sp�cifi�e dans la
	// classe Connexion.
	// Un compte �pargne est une entr�e de la table 'compte' avec en attribut
	// typeCompte = '�pargne'.
	// Cette classe permet de cr�er, lire les informations, modifier, supprimer un
	// compte �pargne, et de r�cup�rer la liste de la totalit� des
	// comptes �pargnes.

	// M�thode permettant d'ins�rer un compte en base de donn�e en prenant un objet
	// de type compte en entr�e et retournant un boolean de valeur true si
	// l'op�ration a �t� un succ�s.
	/**
	 * @param compte
	 * @return
	 */
	public boolean createEpargne(Epargne compte) {
		int i = 0;
		boolean b = false;
		try {
			// Affectation � la chaine de caract�re s de la requ�te SQL
			String s = "INSERT INTO `compte`(`numeroCompte`, `tauxInteret`, `dateOuverture`, `solde`, `idClient`, `typeDeCompte`)"
					+ " VALUES ( " + compte.getNumeroCompte() + ", " + compte.getTauxInteret() + ", "
					+ compte.getSolde() + ", " + compte.getIdClient() + ", '"+compte.getTypeDeCompte()+")";
			// Cr�ation d'un objet de type Statement
			Statement stmt = Connexion.connexion().prepareStatement(s);
			// ex�cution de la requete
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
	// 'compte' en prenant comme param�tre d'entr�e un objet de type Epargne
	// pr�sentant comme
	// attribut l'idCompte du compte � r�cup�rer. La m�thode retourne un objet
	// compte pr�sentants les informations r�cup�r�es.
	/**
	 * @param compte
	 * @return
	 */
	public Epargne getEpargne(Epargne compte) {
		try {
			Statement stmt = Connexion.connexion().createStatement(); // Cr�ation d'un objet de type Statement
			// Affectation � la chaine de caract�re s de la requ�te SQL
			String s = "Select * from compte where idCompte = " + compte.getIdCompte();
			// ex�cution de la requete
			ResultSet rs = stmt.executeQuery(s);
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

	// M�thode permettant de modifier les informations d'un client de la table
	// 'compte' en prenant comme param�tre d'entr�e un objet compte pr�sentant les
	// nouvelles valeurs d'attributs. La m�thode retourne l'objet compte r�cup�r�
	// de la base de donn�e avec les nouvelles valeurs d'attribut.
	/**
	 * @param compte
	 * @return
	 */
	public Epargne updateEpargne(Epargne compte) {
		try {

			Statement stmt = Connexion.connexion().createStatement(); // Cr�ation d'un objet de type Statement
			// Affectation � la chaine de caract�re s de la requ�te SQL
			String s = "UPDATE compte set numeroCompte = '" + compte.getNumeroCompte() + "', tauxInteret = "
					+ compte.getTauxInteret() + ", solde = " + compte.getSolde() + " where idcompte = "
					+ compte.getIdCompte();
			stmt.executeUpdate(s);
			// Affectation � la chaine de caract�re s de la requ�te SQL
			s = "Select * from compte where idClient = " + compte.getIdCompte();
			ResultSet rs = stmt.executeQuery(s);
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

	// M�thode permettant de suppimer l'entr�e de la table 'compte' pr�sentant le
	// m�me idCompte que l'objet compte en param�tre d'entr�e de m�thode et
	// retournant un boolean de valeur true si
	// l'op�ration a �t� un succ�s
	/**
	 * @param compte
	 * @return
	 */
	public boolean deleteEpargne(Epargne compte) {
		int i;
		boolean b = false;
		try {
			Statement stmt = Connexion.connexion().createStatement(); // Cr�ation d'un objet de type Statement
			// Affectation � la chaine de caract�re s de la requ�te SQL
			String s = "DELETE from compte where idCompte = " + compte.getIdCompte();
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
	// typeCompte = '�pargne'.
	/**
	 * @return
	 */
	public List<Epargne> getAllEpargne() {
		List<Epargne> listCEpargne = new ArrayList<Epargne>();
		try {
			Statement stmt = Connexion.connexion().createStatement(); // Cr�ation d'un objet de type Statement
			// Affectation � la chaine de caract�re s de la requ�te SQL
			String s = "Select * from compte where typeDeCompte = 'epargne'";
			// ex�cution de la requete
			ResultSet rs = stmt.executeQuery(s);
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
