package presentation;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import domaine.CompteBancaire;
import domaine.Courant;
import domaine.Epargne;
import service.GestionCompteService;

@ManagedBean(name = "beanvirement")
@SessionScoped
public class BeanVirement { 
	private CompteBancaire compteDébiteur;
	private CompteBancaire compteCréditeur;
	private Integer sommeVirement;
	GestionCompteService service = new GestionCompteService();

	public BeanVirement() {
	}

	public CompteBancaire getCompteDébiteur() {
		return compteDébiteur;
	}

	public void setCompteDébiteur(CompteBancaire compteDébiteur) {
		this.compteDébiteur = compteDébiteur;
	}
	public void setCompteDébiteur(Courant compteDébiteur) {
		this.compteDébiteur = compteDébiteur;
	}
	public void setCompteDébiteur(Epargne compteDébiteur) {
		this.compteDébiteur = compteDébiteur;
	}

	public CompteBancaire getCompteCréditeur() {
		return compteCréditeur;
	}

	public void setCompteCréditeur(CompteBancaire compteCréditeur) {
		this.compteCréditeur = compteCréditeur;
	}
	public void setCompteCréditeur(Courant compteCréditeur) {
		this.compteCréditeur = compteCréditeur;
	}
	public void setCompteCréditeur(Epargne compteCréditeur) {
		this.compteCréditeur = compteCréditeur;
	}
	public Integer getSommeVirement() {
		return sommeVirement;
	}

	public void setSommeVirement(Integer sommeVirement) {
		this.sommeVirement = sommeVirement;
	}

	public Object virementCompteACompte() {
		boolean verification=false;
		Courant compteCourantDébiteur = new Courant();
		Courant compteCourantCréditeur = new Courant();
		Epargne compteEpargneDébiteur = new Epargne();
		Epargne compteEpargneCréditeur = new Epargne();

		if (compteDébiteur.getTypeDeCompte() == "courant") {
			compteCourantDébiteur = (Courant) compteDébiteur;

			if (compteCréditeur.getTypeDeCompte() == "Courant") {
				compteCourantCréditeur = (Courant) compteCréditeur;
				verification=service.virementCompteACompte(compteCourantDébiteur, compteCourantCréditeur, sommeVirement);
			}

			else {
				compteEpargneCréditeur = (Epargne) compteCréditeur;
			}
			verification=service.virementCompteACompte(compteCourantDébiteur, compteEpargneCréditeur, sommeVirement);

		} else {
			compteEpargneDébiteur = (Epargne) compteDébiteur;
			if (compteCréditeur.getTypeDeCompte() == "Courant") {
				compteCourantCréditeur = (Courant) compteCréditeur;
				verification=service.virementCompteACompte(compteEpargneDébiteur, compteCourantCréditeur, sommeVirement);
			}

			else {
				compteEpargneCréditeur = (Epargne) compteCréditeur;
				verification=service.virementCompteACompte(compteEpargneDébiteur, compteEpargneCréditeur, sommeVirement);
			}

		}
	if (verification) {
		return "operationreussie";
	}
	return "operationrate";
}
}
