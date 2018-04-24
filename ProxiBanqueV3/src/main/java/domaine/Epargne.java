package domaine;

/**
 * @author Stagiaire
 *
 */
public class Epargne extends CompteBancaire {

	// La classe Epargne herite de la classe abstraite CompteBancaire

	// Declaration des attributs caracterisant un objet de type Epargne
	private double tauxInteret;

	// Constructeur par defaut
	public Epargne() {
		super();
		this.tauxInteret = 3.00;
		this.setTypeDeCompte("epargne");
	}

	// Getters et Setters
	public double getTauxInteret() {
		return tauxInteret;
	}

	public void setTauxInteret(double taux) {
		this.tauxInteret = taux;
	}

	// Redefinition de la methode toString()
	public String toString() {
		return super.toString(this.getNumeroCompte()) + " et a un taux d'interet de " + this.tauxInteret + "%";
	}
}
