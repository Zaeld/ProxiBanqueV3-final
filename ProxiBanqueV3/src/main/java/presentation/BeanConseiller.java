package presentation;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import domaine.Client;
import domaine.Conseiller;
import domaine.Login;
import service.ClientService;
import service.LoginService;

@ManagedBean(name = "beanconseiller")
@SessionScoped
public class BeanConseiller {
	private Login login = new Login();
	LoginService service = new LoginService();
	ClientService serviceClient = new ClientService();
	Client client = new Client();
	private Conseiller conseiller = new Conseiller();

	public BeanConseiller() {
		super();

	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Conseiller getConseiller() {
		return conseiller;
	}

	public void setConseiller(Conseiller conseiller) {
		this.conseiller = conseiller;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public LoginService getService() {
		return service;
	}

	public void setService(LoginService service) {
		this.service = service;
	}

	public Object verifLogin() {
		this.conseiller = service.verifLogin(login);

		if (conseiller != null) {
			return "listeClients";
		}
		return "acceuilLogin";
	}

	public List<Client> afficherListeClients() {
		List<Client> listeClients = serviceClient.getAllClientConseiller(this.conseiller);
		return listeClients;
	}
}
