package presentation;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import domaine.Client;
import domaine.CompteBancaire;
import service.ClientService;
import service.GestionCompteService;

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

	public List<Client> getListClients() {
		listClients = service.getAllClient();
		return listClients;
	}

	public void setListClients(List<Client> listClients) {
		this.listClients = listClients;
	}

	public Object modifierClient(Client client) {
		this.setClient(client);
		return "modifierClient";
	}

	public Object createClient() {
		if (service.createClient(client)) {
			return "operationreussie";
		}
		return "operationrate";
	}

	public Object updateClient() {
		Client test;
		test = service.updateClient(client);
		if (test != null) {
			return "operationreussie";
		}
		return "operationrate";

	}

	public Object deleteClient(Client client) {
		if (service.deleteClient(client)) {
			return "operationreussie";
		}
		return "operationrate";
	}

	public Object afficherClient(Client client) {
		this.setClient(client);
		return "afficherClient";
	}

	public Object virement(Client client) {
		this.setClient(client);
		return "virement";
	}

	public List<CompteBancaire> afficherListeComptes() {
		List<CompteBancaire> listeComptes = service.getAllCompteClient(client);
		return listeComptes;
	}

	public List<CompteBancaire> afficherItemsComptes() {
		return service.getAllCompteClient(client);
	}

	public List<CompteBancaire> afficherItemsAllComptes() {
		return serviceCompte.getAllCompte();
	}
}
