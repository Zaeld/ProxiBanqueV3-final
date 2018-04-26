package presentation;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import domaine.Client;
import domaine.CompteBancaire;
import service.ClientService;
import service.GestionCompteService;

/**
 * Bean permettant de récupérer les attributs de l'objet client sur la vue de la
 * modification du client, de l'acceuil et des diverses opérations associés à un
 * client comme la suppresion, l'affichage ou le virement. Cette classe permet
 * aussi la redirection vers les vues réalisant ces fonctionnalités
 * 
 * @author ademolis
 *
 */
@ManagedBean(name = "beanclient")
@SessionScoped
public class BeanClient {

	private Client client = new Client();
	private List<Client> listClients;
	ClientService service = new ClientService();
	GestionCompteService serviceCompte = new GestionCompteService();
	CompteBancaire compte;

	public BeanClient() {
		super();
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * @return
	 */
	public List<Client> getListClients() {
		listClients = service.getAllClient();
		return listClients;
	}

	public void setListClients(List<Client> listClients) {
		this.listClients = listClients;
	}

	/**
	 * @param client
	 * @return
	 */
	public Object modifierClient(Client client) {
		this.setClient(client);
		return "modifierClient";
	}

	/**
	 * @return
	 */
	public Object createClient() {
		if (service.createClient(client)) {
			return "operationreussie";
		}
		return "operationrate";
	}

	/**
	 * @return
	 */
	public Object updateClient() {
		Client test;
		test = service.updateClient(client);
		if (test != null) {
			return "operationreussie";
		}
		return "operationrate";

	}

	/**
	 * @param client
	 * @return
	 */
	public Object deleteClient(Client client) {
		if (service.deleteClient(client)) {
			return "operationreussie";
		}
		return "operationrate";
	}

	/**
	 * @param client
	 * @return
	 */
	public Object afficherClient(Client client) {
		this.setClient(client);
		return "afficherClient";
	}

	/**
	 * @param client
	 * @return
	 */
	public Object virement(Client client) {
		this.setClient(client);
		return "virement";
	}

	/**
	 * @return
	 */
	public List<CompteBancaire> afficherListeComptes() {
		List<CompteBancaire> listeComptes = service.getAllCompteClient(client);
		return listeComptes;
	}

	/**
	 * @return
	 */
	public List<CompteBancaire> afficherItemsComptes() {
		return service.getAllCompteClient(client);
	}

	/**
	 * @return
	 */
	public List<CompteBancaire> afficherItemsAllComptes() {
		return serviceCompte.getAllCompte();
	}
}
