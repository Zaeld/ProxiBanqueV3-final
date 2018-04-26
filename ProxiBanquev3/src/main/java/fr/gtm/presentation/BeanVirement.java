package fr.gtm.presentation;

import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;

import fr.gtm.service.CourantService;
import fr.gtm.service.EpargneService;
import fr.gtm.service.GestionCompteService;
import fr.gtm.service.Loggen;

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

	public Object virementGestion() {
		;// Si les deux comptes sélectionnés renvoies aux même, le virement ne peut avoir
			// lieu
		if (idCompteDébiteur.equals(idCompteCréditeur)) {
			Loggen.logger.info("Tentative rejetée de virement sur même compte");
			return "erreurVirement";

		}
		if (service.virementGestion(idCompteDébiteur, idCompteCréditeur, sommeVirement)) {
			Loggen.logger
					.info("Un virement de " + this.getSommeVirement() + " du compte id : " + this.getIdCompteDébiteur()
							+ " vers le compte id : " + this.getIdCompteCréditeur() + " a été correctement effectué");

			return "operationreussie";
		}
		Loggen.logger
		.debug("Un virement de " + this.getSommeVirement() + "du compte id : " + this.getIdCompteDébiteur()
				+ " vers le compte id : " + this.getIdCompteCréditeur() + " n'a pas pu ête correctement effectué");
		return "operationrate";
	}
}
