package fr.gtm.presentation;

import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;

import fr.gtm.service.CourantService;
import fr.gtm.service.EpargneService;
import fr.gtm.service.GestionCompteService;
import fr.gtm.service.Loggen;

/**
 * Bean permettant de gerer la fonctionnalite de virement compte Ã  compte en
 * recuperant les id des comptes concernes, en recuperant les comptes associes
 * dans la BDD et en appelant la methode virement adequate
 * 
 * @author ademolis
 *
 */
@ManagedBean(name = "beanvirement")
@SessionScoped
public class BeanVirement {
	private Integer idCompteDebiteur;
	private Integer idCompteCrediteur;
	private Integer sommeVirement;
	GestionCompteService service = new GestionCompteService();
	CourantService serviceCourant = new CourantService();
	EpargneService serviceEpargne = new EpargneService();

	public BeanVirement() {
	}

	public Integer getIdCompteDebiteur() {
		return idCompteDebiteur;
	}

	public void setIdCompteDebiteur(Integer idCompteDebiteur) {
		this.idCompteDebiteur = idCompteDebiteur;
	}

	public Integer getIdCompteCrediteur() {
		return idCompteCrediteur;
	}

	public void setIdCompteCrediteur(Integer idCompteCrediteur) {
		this.idCompteCrediteur = idCompteCrediteur;
	}

	public Integer getSommeVirement() {
		return sommeVirement;
	}

	public void setSommeVirement(Integer sommeVirement) {
		this.sommeVirement = sommeVirement;
	}

	/**
	 * La methode virementGestion permet d'envoyer les variables idCompteCrediteur,
	 * idCompteDebiteur et sommeVirement vers le methode virementGestion de la
	 * classe GestionCompteService afin de s'occuper du traitement. Si les deux id
	 * sont identiques, l'utilisateur est redirige vers une page d'erreur, idem si
	 * la somme du virement est nulle. En cas de réussite, l'utilisateur est
	 * redirige vers la page correspondante.
	 * 
	 * @return
	 */
	public Object virementGestion() {
		;// Si les deux comptes selectionnes renvoies aux mÃªme, le virement ne peut
			// avoir
			// lieu
		if (idCompteDebiteur.equals(idCompteCrediteur)) {
			Loggen.logger.info("Tentative rejetee de virement sur mÃªme compte");
			return "erreurVirement";

		}
		if (service.virementGestion(idCompteDebiteur, idCompteCrediteur, sommeVirement)) {
			Loggen.logger
					.info("Un virement de " + this.getSommeVirement() + " du compte id : " + this.getIdCompteDebiteur()
							+ " vers le compte id : " + this.getIdCompteCrediteur() + " a ete correctement effectue");

			return "operationreussie";
		}
		Loggen.logger.debug("Un virement de " + this.getSommeVirement() + "du compte id : " + this.getIdCompteDebiteur()
				+ " vers le compte id : " + this.getIdCompteCrediteur() + " n'a pas pu Ãªte correctement effectue");
		return "operationrate";
	}
}
