package fr.gtm.domaine;

/**
 * La classe Courant hérite de la classe abstraite CompteBancaire
 * 
 * @author Stagiaire
 *
 */
public class Courant extends CompteBancaire {

	// Declaration des attributs caracterisant un objet de type Courant
	private double decouvertAutorise;
	private String typeCarte;

	// Constructeur par defaut
	public Courant() {
		super();
		this.decouvertAutorise = 1000;
		this.setTypeDeCompte("courant");
		this.setTypeCarte("ELECTRON");
	}

	public Courant(double decouvertAutorise, String typeCarte) {
		super();
		this.decouvertAutorise = decouvertAutorise;
		this.typeCarte = typeCarte;
	}

	// Getters et Setters
	public double getDecouvertAutorise() {
		return decouvertAutorise;
	}

	public String getTypeCarte() {
		return typeCarte;
	}

	public void setTypeCarte(String typeCarte) {
		this.typeCarte = typeCarte;
	}

	public void setDecouvertAutorise(double d) {
		this.decouvertAutorise = d;
	}

	/*
	 * Redefinition de la methode toString()
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return super.toString();
	}
}
