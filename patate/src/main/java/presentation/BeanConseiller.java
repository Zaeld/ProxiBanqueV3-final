package presentation;

import java.util.List;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import domaine.Client;
import domaine.Conseiller;
import domaine.Login;
import service.ClientService;
import service.LoginService;

/**
 * Ce bean permet de récupérer les attributs d'un objet conseiller, notament
 * lors de l'affichage des clients propre au conseiller et lors de la
 * vérification du login
 * 
 * @author ademolis
 *
 */
@ManagedBean(name = "beanconseiller")
@SessionScoped
public class BeanConseiller {
	private Login login = new Login();
	LoginService service = new LoginService();
	ClientService serviceClient = new ClientService();
	Client client = new Client();
	private Conseiller conseiller = new Conseiller();

	// Gestion erreurs
	String typeErreur = "";
	String messageErreur = "";

	public String getTypeErreur() {
		return typeErreur;
	}

	public void setTypeErreur(String typeErreur) {
		this.typeErreur = typeErreur;
	}

	public String getMessageErreur() {
		return messageErreur;
	}

	public void setMessageErreur(String messageErreur) {
		this.messageErreur = messageErreur;
	}

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

	/**
	 * @return
	 */
	public Object verifLogin() {
		this.conseiller = service.verifLogin(login);

		if (conseiller != null) {
			return "listeClients";
		}
		return this.erreur(1);
	}

	/**
	 * Methode permettant de dispatcher une page d'erreur personnalisée
	 * 
	 * @param codeErreur
	 * @return
	 */
	public Object erreur(int codeErreur) {

		String pageErreur;
		switch (codeErreur) {
		case 1:
			this.typeErreur = "Erreur d'identification";
			this.messageErreur = "Veuillez réessayer";
			pageErreur = "acceuilLogin";
			break;

		default:
			pageErreur = "fatalError";
			break;
		}
		return pageErreur;

	}

	/**
	 * @return
	 */
	public List<Client> afficherListeClients() {
		List<Client> listeClients = serviceClient.getAllClientConseiller(this.conseiller);
		return listeClients;
	}
}
