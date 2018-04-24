package service;

import dao.ClientDAO;

import domaine.Client;
import domaine.Courant;
import domaine.Epargne;

/**
 * @author Stagiaire
 *
 */
public class GestionClientService implements ICalculSommeTotal {

	// La classe GestionClientService est compos�e de la méthode calculSommeTotal
	// qui permet de modifier sous conditions l'attributs soldeTotal d'un objet de
	// type Client

	// D�claration
	ClientDAO dao = new ClientDAO();
	Courant compteCourant = new Courant();
	Epargne compteEpargne = new Epargne();

	// La m�thode calculSommeTotal prend un objet de type Client en param�tre
	// d'entr�e et calcul son solde total
	/**
	 * @param client
	 * @return
	 */
	public Client calculSommeTotal(Client client) {

		// D�claration
		double soldeTotal = 0;

		// On r�cup�re dans la base de donn�e les comptes associ�s au client

		compteCourant = dao.getCompteCourant(client);
		compteEpargne = dao.getCompteEpargne(client);

		// Si le client poss�de un compte courant on ajoute son solde � la variable
		// soldeTotal
		if (compteCourant != null) {
			soldeTotal += compteCourant.getSolde();
		}
		// Si le client poss�de un compte �pargne on ajoute son solde � la variable
		// soldeTotal
		if (compteEpargne != null) {
			soldeTotal += compteEpargne.getSolde();
		}
		// On set le solde total du client
		client.setSoldeTotal(soldeTotal);
		dao.updateClient(client);

		return client;

	}
}
