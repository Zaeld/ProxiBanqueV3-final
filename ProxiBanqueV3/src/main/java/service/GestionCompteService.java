package service;

import java.util.ArrayList;
import java.util.List;

import dao.CompteCourantDAO;
import dao.CompteEpargneDAO;
import domaine.CompteBancaire;
import domaine.Courant;
import domaine.Epargne;

/**
 * @author Stagiaire
 *
 */
public class GestionCompteService {
	// La classe GestionCompteService regroupe la méthode d'affichage de tout les
	// comptes ainsi que les méthodes régissant les interactions entre comptes comme
	// le système de virement

	// Déclaration
	CompteCourantDAO courantDAO = new CompteCourantDAO();
	CompteEpargneDAO epargneDAO = new CompteEpargneDAO();

	// Méthode retournant toutes les entrées de la table 'compte' de la
	// base de donnée.
	public List<CompteBancaire> getAllCompte() {

		// D�claration
		List<CompteBancaire> listCompte = new ArrayList<CompteBancaire>();
		ArrayList<CompteBancaire> comptes = new ArrayList<CompteBancaire>();

		// On ajoute la liste des comptes �pargnes et courant � la liste de compte
		// bancaire
		listCompte.addAll(courantDAO.getAllCourant());
		listCompte.addAll(epargneDAO.getAllEpargne());
		for (CompteBancaire compte : listCompte) {
			if (compte != null)
				comptes.add(compte);
		}
		return comptes;
	}

	// La méthode débiter et créditer permet de modifier le solde d'un compte en
	// paramètre d'entrée en fonction de la varible somme aussi en paramètre
	// d'entrée de méthode. Les méthodes sont surchargées pour s'adapter au type de
	// compte.

	/**
	 * @param compte
	 * @param somme
	 * @return
	 */
	public Courant debiter(Courant compte, double somme) {
		compte.setSolde(compte.getSolde() - somme);
		courantDAO.updateCourant(compte);
		return compte;
	}

	/**
	 * @param compte
	 * @param somme
	 * @return
	 */
	public Courant crediter(Courant compte, double somme) {
		compte.setSolde(compte.getSolde() + somme);
		courantDAO.updateCourant(compte);
		return compte;
	}

	/**
	 * @param compte
	 * @param somme
	 * @return
	 */
	public Epargne debiter(Epargne compte, double somme) {
		compte.setSolde(compte.getSolde() - somme);
		epargneDAO.updateEpargne(compte);
		return compte;

	}

	/**
	 * @param compte
	 * @param somme
	 * @return
	 */
	public Epargne crediter(Epargne compte, double somme) {
		compte.setSolde(compte.getSolde() + somme);
		epargneDAO.updateEpargne(compte);
		return compte;
	}
	// La méthode virementCompteACompte permet de faire un virement entre deux
	// comptes en fonction de la variable somme. La méthode est surchargée pour
	// s'adapter au type de compte en paramètre d'entrée.

	/**
	 * @param comptedébiteur
	 * @param comptecréditeur
	 * @param somme
	 * @return
	 */
	public boolean virementCompteACompte(Courant comptedébiteur, Courant comptecréditeur, double somme) {

		// On récupère le solde de chaque compte avant le virement pour les tester plus
		// tard.
		double solde1avant = comptedébiteur.getSolde();
		double solde2avant = comptecréditeur.getSolde();

		// On effectue le virement en appelant les méthodes débiter() et créditer()
		// correspondant et on récupère les soldes finaux des comptes
		double solde1après = this.debiter(comptedébiteur, somme).getSolde();
		double solde2après = this.crediter(comptecréditeur, somme).getSolde();

		// Si les soldes des comptes ont été modifié par le virement, la méthode
		// retourne true, sinon elle retourne false
		if (solde1avant != solde1après && solde2avant != solde2après)
			return true;
		else
			return false;
	}

	/**
	 * @param comptedébiteur
	 * @param comptecréditeur
	 * @param somme
	 * @return
	 */
	public boolean virementCompteACompte(Courant comptedébiteur, Epargne comptecréditeur, double somme) {

		// On récupère le solde de chaque compte avant le virement pour les tester plus
		// tard.
		double solde1avant = comptedébiteur.getSolde();
		double solde2avant = comptecréditeur.getSolde();

		// On effectue le virement en appelant les méthodes débiter() et créditer()
		// correspondant et on récupère les soldes finaux des comptes
		double solde1après = this.debiter(comptedébiteur, somme).getSolde();
		double solde2après = this.crediter(comptecréditeur, somme).getSolde();

		// Si les soldes des comptes ont été modifié par le virement, la méthode
		// retourne true, sinon elle retourne false
		if (solde1avant != solde1après && solde2avant != solde2après)
			return true;
		else
			return false;
	}

	/**
	 * @param comptedébiteur
	 * @param comptecréditeur
	 * @param somme
	 * @return
	 */
	public boolean virementCompteACompte(Epargne comptedébiteur, Courant comptecréditeur, double somme) {

		// On récupère le solde de chaque compte avant le virement pour les tester plus
		// tard.
		double solde1avant = comptedébiteur.getSolde();
		double solde2avant = comptecréditeur.getSolde();

		// On effectue le virement en appelant les méthodes débiter() et créditer()
		// correspondant et on récupère les soldes finaux des comptes
		double solde1après = this.debiter(comptedébiteur, somme).getSolde();
		double solde2après = this.crediter(comptecréditeur, somme).getSolde();

		// Si les soldes des comptes ont été modifié par le virement, la méthode
		// retourne true, sinon elle retourne false
		if (solde1avant != solde1après && solde2avant != solde2après)
			return true;
		else
			return false;
	}

	/**
	 * @param comptedébiteur
	 * @param comptecréditeur
	 * @param somme
	 * @return
	 */
	public boolean virementCompteACompte(Epargne comptedébiteur, Epargne comptecréditeur, double somme) {

		// On récupère le solde de chaque compte avant le virement pour les tester plus
		// tard.
		double solde1avant = comptedébiteur.getSolde();
		double solde2avant = comptecréditeur.getSolde();

		// On effectue le virement en appelant les méthodes débiter() et créditer()
		// correspondant et on récupère les soldes finaux des comptes
		double solde1après = this.debiter(comptedébiteur, somme).getSolde();
		double solde2après = this.crediter(comptecréditeur, somme).getSolde();

		// Si les soldes des comptes ont été modifié par le virement, la méthode
		// retourne true, sinon elle retourne false
		if (solde1avant != solde1après && solde2avant != solde2après)
			return true;
		else
			return false;
	}
}