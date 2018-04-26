package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.PropertyConfigurator;

import service.Loggen;

/**
 * Classe appelant le driver et permettant la connexion avec la base de donnée.
 * 
 * @author Stagiaire
 *
 */
public class Connexion {

	/**
	 * Méthode appelant le driver, établissant la connection et générant le fichier
	 * de propriétés des logs
	 * 
	 * @return
	 */
	public static Connection connexion() {
		Connection maConnection = null; // D�claration d'un objet de type Connection.
		try {
			PropertyConfigurator.configure(
					"C:/Users/Stagiaire/Desktop/proxibanque 26 04/ProxiBanqueV3/ProxiBanquev3/src/resources/java/log.properties");


			// Importation du Driver permettant la liaison avec le serveur de Base de
			// donn�e.
			Class.forName("com.mysql.jdbc.Driver");
			// L'objet maConnection est configur�e avec les coordonn�es de la base de donn�e
			// de proxibanque
			maConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proxibanquev3", "root", "");
			// maConnection =
			// DriverManager.getConnection("jdbc:mysql://localhost:3306/proxibanque?user=root");
		} catch (ClassNotFoundException e) {
			Loggen.logger.debug("Impossible de trouver le driver");
		} catch (SQLException e) {
			Loggen.logger.debug("Impossible de se connecter");
		}

		// La m�thode retourne un objet de type Connection permettant aux m�thodes de la
		// couche DAO d'interargir avec la base de donn�e.
		return maConnection;
	}
}
