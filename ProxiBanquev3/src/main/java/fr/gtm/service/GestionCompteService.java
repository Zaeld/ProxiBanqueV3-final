package fr.gtm.service;

import java.util.ArrayList;

import java.util.List;

import fr.gtm.dao.CompteCourantDAO;
import fr.gtm.dao.CompteEpargneDAO;
import fr.gtm.domaine.CompteBancaire;
import fr.gtm.domaine.Courant;
import fr.gtm.domaine.Epargne;

/**
 * La classe GestionCompteService regroupe la methode d'affichage de tout les
 * comptes ainsi que les methodes regissant les interactions entre comptes comme
 * le systÃ¨me de virement. Elle implemente l'interface IVirement
 * 
 * @author Stagiaire
 *
 */
public class GestionCompteService implements IVirement {

	// Declaration
	CompteCourantDAO courantDAO = new CompteCourantDAO();
	CompteEpargneDAO epargneDAO = new CompteEpargneDAO();

	// Methode retournant toutes les entrees de la table 'compte' de la
	// base de donnee.
	public List<CompteBancaire> getAllCompte() {

		// Dï¿½claration
		List<CompteBancaire> listCompte = new ArrayList<CompteBancaire>();
		ArrayList<CompteBancaire> comptes = new ArrayList<CompteBancaire>();

		// On ajoute la liste des comptes ï¿½pargnes et courant ï¿½ la liste de compte
		// bancaire
		listCompte.addAll(courantDAO.getAllCourant());
		listCompte.addAll(epargneDAO.getAllEpargne());
		for (CompteBancaire compte : listCompte) {
			if (compte != null)
				comptes.add(compte);
		}
		return comptes;
	}

	/**
	 * La methode debiter et crediter permet de modifier le solde d'un compte en
	 * paramÃ¨tre d'entree en fonction de la variable somme aussi en paramÃ¨tre
	 * d'entree de methode. Les methodes sont surchargees pour s'adapter au type de
	 * compte.
	 * 
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
	 * La methode debiter et crediter permet de modifier le solde d'un compte en
	 * paramÃ¨tre d'entree en fonction de la variable somme aussi en paramÃ¨tre
	 * d'entree de methode. Les methodes sont surchargees pour s'adapter au type de
	 * compte.
	 * 
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
	 * La methode debiter et crediter permet de modifier le solde d'un compte en
	 * paramÃ¨tre d'entree en fonction de la variable somme aussi en paramÃ¨tre
	 * d'entree de methode. Les methodes sont surchargees pour s'adapter au type de
	 * compte.
	 * 
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
	 * La methode debiter et crediter permet de modifier le solde d'un compte en
	 * paramÃ¨tre d'entree en fonction de la variable somme aussi en paramÃ¨tre
	 * d'entree de methode. Les methodes sont surchargees pour s'adapter au type de
	 * compte.
	 * 
	 * @param compte
	 * @param somme
	 * @return
	 */
	public Epargne crediter(Epargne compte, double somme) {
		compte.setSolde(compte.getSolde() + somme);
		epargneDAO.updateEpargne(compte);
		return compte;
	}

	/**
	 * La methode virementCompteACompte permet de faire un virement entre deux
	 * comptes en fonction de la variable somme. La methode est surchargee pour
	 * s'adapter au type de compte en paramÃ¨tre d'entree.
	 * 
	 * @param comptedebiteur
	 * @param comptecrediteur
	 * @param somme
	 * @return
	 */
	public boolean virementCompteACompte(Courant comptedebiteur, Courant comptecrediteur, double somme) {

		// On recupÃ¨re le solde de chaque compte avant le virement pour les tester plus
		// tard.
		double solde1avant = comptedebiteur.getSolde();

		double solde2avant = comptecrediteur.getSolde();

		// On effectue le virement en appelant les methodes debiter() et crediter()
		// correspondant et on recupÃ¨re les soldes finaux des comptes
		double solde1apre = this.debiter(comptedebiteur, somme).getSolde();
		courantDAO.updateCourant(comptedebiteur);

		double solde2apre = this.crediter(comptecrediteur, somme).getSolde();
		courantDAO.updateCourant(comptecrediteur);

		// Si les soldes des comptes ont ete modifie par le virement, la methode
		// retourne true, sinon elle retourne false
		if (solde1avant != solde1apre && solde2avant != solde2apre)
			return true;
		else
			return false;
	}

	/**
	 * La methode virementCompteACompte permet de faire un virement entre deux
	 * comptes en fonction de la variable somme. La methode est surchargee pour
	 * s'adapter au type de compte en paramÃ¨tre d'entree.
	 * 
	 * @param comptedebiteur
	 * @param comptecrediteur
	 * @param somme
	 * @return
	 */
	public boolean virementCompteACompte(Courant comptedebiteur, Epargne comptecrediteur, double somme) {

		// On recupÃ¨re le solde de chaque compte avant le virement pour les tester plus
		// tard.
		double solde1avant = comptedebiteur.getSolde();
		double solde2avant = comptecrediteur.getSolde();

		// On effectue le virement en appelant les methodes debiter() et crediter()
		// correspondant et on recupÃ¨re les soldes finaux des comptes
		double solde1apre = this.debiter(comptedebiteur, somme).getSolde();
		courantDAO.updateCourant(comptedebiteur);

		double solde2apre = this.crediter(comptecrediteur, somme).getSolde();
		epargneDAO.updateEpargne(comptecrediteur);

		// Si les soldes des comptes ont ete modifie par le virement, la methode
		// retourne true, sinon elle retourne false
		if (solde1avant != solde1apre && solde2avant != solde2apre)
			return true;
		else
			return false;
	}

	/**
	 * La methode virementCompteACompte permet de faire un virement entre deux
	 * comptes en fonction de la variable somme. La methode est surchargee pour
	 * s'adapter au type de compte en paramÃ¨tre d'entree.
	 * 
	 * @param comptedebiteur
	 * @param comptecrediteur
	 * @param somme
	 * @return
	 */
	public boolean virementCompteACompte(Epargne comptedebiteur, Courant comptecrediteur, double somme) {

		// On recupÃ¨re le solde de chaque compte avant le virement pour les tester plus
		// tard.
		double solde1avant = comptedebiteur.getSolde();
		double solde2avant = comptecrediteur.getSolde();

		// On effectue le virement en appelant les methodes debiter() et crediter()
		// correspondant et on recupÃ¨re les soldes finaux des comptes
		double solde1apre = this.debiter(comptedebiteur, somme).getSolde();
		epargneDAO.updateEpargne(comptedebiteur);
		double solde2apre = this.crediter(comptecrediteur, somme).getSolde();
		courantDAO.updateCourant(comptecrediteur);

		// Si les soldes des comptes ont ete modifie par le virement, la methode
		// retourne true, sinon elle retourne false
		if (solde1avant != solde1apre && solde2avant != solde2apre)
			return true;
		else
			return false;
	}

	/**
	 * La methode virementCompteACompte permet de faire un virement entre deux
	 * comptes en fonction de la variable somme. La methode est surchargee pour
	 * s'adapter au type de compte en paramÃ¨tre d'entree.
	 * 
	 * @param comptedebiteur
	 * @param comptecrediteur
	 * @param somme
	 * @return
	 */
	public boolean virementCompteACompte(Epargne comptedebiteur, Epargne comptecrediteur, double somme) {

		// On recupÃ¨re le solde de chaque compte avant le virement pour les tester plus
		// tard.
		double solde1avant = comptedebiteur.getSolde();
		double solde2avant = comptecrediteur.getSolde();

		// On effectue le virement en appelant les methodes debiter() et crediter()
		// correspondant et on recupÃ¨re les soldes finaux des comptes
		double solde1apre = this.debiter(comptedebiteur, somme).getSolde();
		epargneDAO.updateEpargne(comptedebiteur);
		double solde2apre = this.crediter(comptecrediteur, somme).getSolde();
		epargneDAO.updateEpargne(comptecrediteur);

		// Si les soldes des comptes ont ete modifie par le virement, la methode
		// retourne true, sinon elle retourne false
		if (solde1avant != solde1apre && solde2avant != solde2apre)
			return true;
		else
			return false;
	}

	/**
	 * La classe virementGestion permet de récupérer les variables idCompteDebiteur
	 * et idCompteCrediteur du bean BeanVirement afin de generer les comptes
	 * associés en testant leur type. Une fois que les comptes sont correctement
	 * récupérés de la BDD et leur statut de debiteur/crediteur connu, la methode de
	 * virement correspondante est appelee et prend comme parametre les comptes et
	 * le montant du virement.
	 * 
	 * @param idCompteDebiteur
	 * @param idCompteCrediteur
	 * @param sommeVirement
	 * @return
	 */
	public boolean virementGestion(Integer idCompteDebiteur, Integer idCompteCrediteur, Integer sommeVirement) {
		boolean verification = false;

		// On recupÃ¨re de la BDD le compte courant associe Ã  l'id du compte debiteur
		Courant compteCourantDebiteur = new Courant();
		compteCourantDebiteur.setIdCompte(idCompteDebiteur);
		compteCourantDebiteur = courantDAO.getCourant(compteCourantDebiteur);

		// On test si le compte debiteur est courant
		if (compteCourantDebiteur == null) {

			// Si il ne l'est pas alors c'est un compte epargne
			Epargne compteEpargneDebiteur = new Epargne();
			compteEpargneDebiteur.setIdCompte(idCompteDebiteur);
			compteEpargneDebiteur = epargneDAO.getEpargne(compteEpargneDebiteur);

			// On recupÃ¨re de la BDD le compte courant associe Ã  l'id du compte crediteur

			Courant compteCourantCrediteur = new Courant();
			compteCourantCrediteur.setIdCompte(idCompteCrediteur);
			compteCourantCrediteur = courantDAO.getCourant(compteCourantCrediteur);

			// On test si le compte crediteur est courant

			if (compteCourantCrediteur == null) {

				// Si il ne l'est pas alors c'est un compte epargne
				Epargne compteEpargneCrediteur = new Epargne();
				compteEpargneCrediteur.setIdCompte(idCompteDebiteur);
				compteEpargneCrediteur = epargneDAO.getEpargne(compteEpargneCrediteur);

				verification = this.virementCompteACompte(compteEpargneDebiteur, compteEpargneCrediteur, sommeVirement);

			} else {
				verification = this.virementCompteACompte(compteEpargneDebiteur, compteCourantCrediteur, sommeVirement);

			}
		} else {
			// Le compte debiteur est courant
			// On test si le compte crediteur est courant

			Courant compteCourantCrediteur = new Courant();
			compteCourantCrediteur.setIdCompte(idCompteCrediteur);
			compteCourantCrediteur = courantDAO.getCourant(compteCourantCrediteur);

			if (compteCourantCrediteur == null) {

				// Si il ne l'est pas alors c'est un compte epargne
				Epargne compteEpargneCrediteur = new Epargne();
				compteEpargneCrediteur.setIdCompte(idCompteDebiteur);
				compteEpargneCrediteur = epargneDAO.getEpargne(compteEpargneCrediteur);
				verification = this.virementCompteACompte(compteCourantDebiteur, compteEpargneCrediteur, sommeVirement);

			} else {
				verification = this.virementCompteACompte(compteCourantDebiteur, compteCourantCrediteur, sommeVirement);

			}
		}
		return verification;

	}
}