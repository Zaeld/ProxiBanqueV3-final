package fr.gtm.service;

import fr.gtm.domaine.Courant;
import fr.gtm.domaine.Epargne;

/**
 * L'interface IVirement est implémentée par la classe GestionCompteService qui
 * gère la fonctionnalité virement
 * 
 * @author ademolis
 *
 */
public interface IVirement {
	public boolean virementCompteACompte(Courant courant, Epargne epargne, double somme);

	public boolean virementCompteACompte(Courant courant, Courant courant2, double somme);

	public boolean virementCompteACompte(Epargne epargne, Epargne epargne2, double somme);

	public boolean virementCompteACompte(Epargne epargne, Courant courant, double somme);

}