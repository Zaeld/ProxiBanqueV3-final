package service;

import java.util.ArrayList;
import java.util.List;

import domaine.Conseiller;

import dao.ClientDAO;
import domaine.Client;
import domaine.CompteBancaire;

/**
 * @author Stagiaire
 *
 */
public class ClientService {

	// La classe ClientService permet de faire le lien entre la couche présentation
	// et la classe ClientDAO

	// Déclaration
	ClientDAO dao = new ClientDAO();

	/**
	 * @param client
	 * @return
	 */
	public boolean createClient(Client client) {
		return dao.createClient(client);
	}

	/**
	 * @param client
	 * @return
	 */
	public Client getClient(Client client) {
		return dao.getClient(client);
	}

	/**
	 * @param client
	 * @return
	 */
	public Client updateClient(Client client) {
		return dao.updateClient(client);
	}

	/**
	 * @param client
	 * @return
	 */
	public boolean deleteClient(Client client) {
		return dao.deleteClient(client);
	}

	/**
	 * @return
	 */
	public List<Client> getAllClient() {
		return dao.getAllClient();
	}
	public List<Client> getAllClientConseiller(Conseiller conseiller) {
		return dao.getAllClientConseiller(conseiller);
	}
	
	public List<CompteBancaire> getAllCompteClient(Client client){
		List<CompteBancaire> listeComptes = dao.getAllCompte(client);
		List<CompteBancaire> comptes = new ArrayList<CompteBancaire>();
		for(CompteBancaire compte : listeComptes){
			if (compte!=null)
			comptes.add(compte);	
	}
		return comptes;
}
}
