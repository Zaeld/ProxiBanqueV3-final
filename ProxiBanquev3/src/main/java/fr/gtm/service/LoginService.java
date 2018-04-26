package fr.gtm.service;

import java.util.ArrayList;

import fr.gtm.dao.ConseillerDAO;
import fr.gtm.dao.LoginDAO;
import fr.gtm.domaine.Conseiller;
import fr.gtm.domaine.Login;

/**
 * La classe LoginService permet de faire le lien entre la couche présentation
 * et la classe LoginDAO
 * 
 * @author Stagiaire
 *
 */
public class LoginService {

	// D�claration
	LoginDAO loginDAO = new LoginDAO();
	ConseillerDAO conseillerDAO = new ConseillerDAO();

	/**
	 * Méthode métier permettant verifier si le login et password correspondent à un
	 * conseiller enregistré
	 * 
	 * @param loginFromConnexion
	 * @return
	 */
	public Conseiller verifLogin(Login loginFromConnexion) {

		Conseiller conseillerMatching = null;
		ArrayList<Login> listAllLogin;
		ArrayList<Conseiller> listAllConseiller;

		// Get tables 'conseiller' et 'login'
		listAllLogin = this.loginDAO.getAll();
		listAllConseiller = this.conseillerDAO.getAll();

		// login et password saisis par l'utilisateur
		String login = loginFromConnexion.getIdentifiant();
		String password = loginFromConnexion.getMotDePasse();

		// Trouver correspondence table login
		int idConseillerMatching = -1;
		for (Login l : listAllLogin) {

			if (login.equals(l.getIdentifiant())) {
				if (password.equals(l.getMotDePasse())) {
					idConseillerMatching = l.getIdConseiller();
				}
			}
		}

		// Si correspondence trouvée, recuperer entrée du conseiller
		if (idConseillerMatching != -1) {
			for (Conseiller c : listAllConseiller) {
				if (c.getIdConseiller() == idConseillerMatching) {
					conseillerMatching = c;
				}
			}

		}

		return conseillerMatching;
		// return loginDAO.VerificationLogin(login);
	}
}
