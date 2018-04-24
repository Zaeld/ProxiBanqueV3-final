package domaine;

/**
 * @author Stagiaire
 *
 */
public class Courant extends CompteBancaire {
	// La classe Courant h�rite de la classe abstraite CompteBancaire

	// Declaration des attributs caracterisant un objet de type Courant
	private double decouvertAutorise;
	private String typeCarte;

	// Constructeur par defaut
	public Courant() {
		super();
		this.decouvertAutorise = 1000;
		this.setTypeDeCompte("courant");
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

	// Redefinition de la methode toString()
	public String toString() {
		return super.toString(this.getNumeroCompte()) + ", a un decouvert autorise de " + this.decouvertAutorise
				+ " et est associe a une carte " + this.getTypeCarte();
	}
}
