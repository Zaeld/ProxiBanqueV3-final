package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Stagiaire
 *
 */
public class Connexion {
	// Classe appelant le driver et permettant la connexion avec la base de donn�e.
	/**
	 * @return
	 */
	public static Connection connexion() {
		Connection maConnection = null; // D�claration d'un objet de type Connection.
		try {
			// Importation du Driver permettant la liaison avec le serveur de Base de
			// donn�e.
			Class.forName("com.mysql.jdbc.Driver");
			// L'objet maConnection est configur�e avec les coordonn�es de la base de donn�e
			// de proxibanque
			maConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proxibanque", "root", "root");
			// maConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proxibanque?user=root");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// La m�thode retourne un objet de type Connection permettant aux m�thodes de la
		// couche DAO d'interargir avec la base de donn�e.
		return maConnection;
	}
}
