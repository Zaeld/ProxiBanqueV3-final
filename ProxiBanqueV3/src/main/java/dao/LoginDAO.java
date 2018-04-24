package dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domaine.Conseiller;
import domaine.Login;

/**
 * @author Stagiaire
 *
 */
public class LoginDAO {
	// LoginDAO est la classe comportant la m�thode Verification Login qui permet
	// de
	// v�rifier si les attributs Login correspondent � une entr�e de la table
	// login
	// de la base de donn�e afin d'autoriser la connexion du conseiller sur le
	// site
	// de ProxiBanque
	/**
	 * @param login
	 * @return
	 */
	public Conseiller VerificationLogin_Alex(Login login) {
		Conseiller conseiller = new Conseiller();

		try {
			// Creation d'un objet de type Statement
			Statement stmt = Connexion.connexion().createStatement();

			// Affectation a la chaine de caractere s de la requete SQL
			String s = "Select * from conseiller inner Join login on conseiller.idConseiller = login.idConseiller where login = '"
					+ login.getIdentifiant() + "' && motDePasse = '" + login.getMotDePasse() + "'";

			// execution de la requete
			ResultSet rs = stmt.executeQuery(s);

			// Lecture des resultats de la requete
			rs.next();
			conseiller.setIdConseiller(rs.getInt("idConseiller"));
			conseiller.setNom(rs.getString("nom"));
			conseiller.setPrenom(rs.getString("prenom"));

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return conseiller;
	}

	// get all : Login
	public ArrayList<Login> getAll() {
		ArrayList<Login> listLogin = new ArrayList<Login>();
		Statement stmt;
		try {
			stmt = Connexion.connexion().createStatement();
			String sql = "SELECT * FROM `login`;";
			ResultSet result = stmt.executeQuery(sql); // Exécution de la requête
			while (result.next()) {
				Login monLogin = new Login(); // Création de la variable de sortie
				monLogin.setIdLogin(result.getInt("idLogin"));
				monLogin.setIdentifiant(result.getString("login")); // Récupération des données
				monLogin.setMotDePasse(result.getString("motDePasse"));
				monLogin.setIdConseiller(result.getInt("idConseiller"));

				listLogin.add(monLogin);
			}
			return listLogin; // retour de la réponse

		} catch (SQLException e) {
			System.out.println("Problème lors de la methode getAll de la table 'login'!");
			e.printStackTrace();
			return null;
		}

	}
}
