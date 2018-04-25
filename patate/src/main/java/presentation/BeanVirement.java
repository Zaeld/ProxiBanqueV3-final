package presentation;

import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;

import domaine.Courant;
import domaine.Epargne;
import service.CourantService;
import service.EpargneService;
import service.GestionCompteService;

/**
 * Bean permettant de gérer la fonctionnalité de virement compte à compte en
 * récupérant les id des comptes concernés, en récupérant les comptes associés
 * dans la BDD et en appelant la méthode virement adéquate
 * 
 * @author ademolis
 *
 */
@ManagedBean(name = "beanvirement")
@SessionScoped
public class BeanVirement {
	private Integer idCompteDébiteur;
	private Integer idCompteCréditeur;
	private Integer sommeVirement;
	GestionCompteService service = new GestionCompteService();
	CourantService serviceCourant = new CourantService();
	EpargneService serviceEpargne = new EpargneService();

	public BeanVirement() {
	}

	public Integer getIdCompteDébiteur() {
		return idCompteDébiteur;
	}

	public void setIdCompteDébiteur(Integer idCompteDébiteur) {
		this.idCompteDébiteur = idCompteDébiteur;
	}

	public Integer getIdCompteCréditeur() {
		return idCompteCréditeur;
	}

	public void setIdCompteCréditeur(Integer idCompteCréditeur) {
		this.idCompteCréditeur = idCompteCréditeur;
	}

	public Integer getSommeVirement() {
		return sommeVirement;
	}

	public void setSommeVirement(Integer sommeVirement) {
		this.sommeVirement = sommeVirement;
	}

	public Object virementCompteACompte() {
		boolean verification = false;
		// Si les deux comptes sélectionnés renvoies aux même, le virement ne peut avoir
		// lieu
		if (idCompteDébiteur.equals(idCompteCréditeur)) {
			return "erreurVirement";
		}
		// On récupère de la BDD le compte courant associé à l'id du compte débiteur
		Courant compteCourantDébiteur = new Courant();
		compteCourantDébiteur.setIdCompte(idCompteDébiteur);
		compteCourantDébiteur = serviceCourant.getCourant(compteCourantDébiteur);

		// On test si le compte débiteur est courant
		if (compteCourantDébiteur == null) {

			// Si il ne l'est pas alors c'est un compte épargne
			Epargne compteEpargneDébiteur = new Epargne();
			compteEpargneDébiteur.setIdCompte(idCompteDébiteur);
			compteEpargneDébiteur = serviceEpargne.getEpargne(compteEpargneDébiteur);

			// On récupère de la BDD le compte courant associé à l'id du compte créditeur

			Courant compteCourantCréditeur = new Courant();
			compteCourantCréditeur.setIdCompte(idCompteCréditeur);
			compteCourantCréditeur = serviceCourant.getCourant(compteCourantCréditeur);

			// On test si le compte créditeur est courant

			if (compteCourantCréditeur == null) {

				// Si il ne l'est pas alors c'est un compte épargne
				Epargne compteEpargneCréditeur = new Epargne();
				compteEpargneCréditeur.setIdCompte(idCompteDébiteur);
				compteEpargneCréditeur = serviceEpargne.getEpargne(compteEpargneCréditeur);

				verification = service.virementCompteACompte(compteEpargneDébiteur, compteEpargneCréditeur,
						sommeVirement);

			} else {
				verification = service.virementCompteACompte(compteEpargneDébiteur, compteCourantCréditeur,
						sommeVirement);

			}
		} else {
			// Le compte débiteur est courant
			// On test si le compte créditeur est courant

			Courant compteCourantCréditeur = new Courant();
			compteCourantCréditeur.setIdCompte(idCompteCréditeur);
			compteCourantCréditeur = serviceCourant.getCourant(compteCourantCréditeur);

			if (compteCourantCréditeur == null) {

				// Si il ne l'est pas alors c'est un compte épargne
				Epargne compteEpargneCréditeur = new Epargne();
				compteEpargneCréditeur.setIdCompte(idCompteDébiteur);
				compteEpargneCréditeur = serviceEpargne.getEpargne(compteEpargneCréditeur);
				verification = service.virementCompteACompte(compteCourantDébiteur, compteEpargneCréditeur,
						sommeVirement);

			} else {
				verification = service.virementCompteACompte(compteCourantDébiteur, compteCourantCréditeur,
						sommeVirement);

			}
		}

		if (verification) {
			return "operationreussie";
		}
		return "operationrate";
	}
}
