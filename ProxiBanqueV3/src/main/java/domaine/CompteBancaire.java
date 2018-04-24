package domaine;

/**
 * @author Stagiaire
 *
 */
public abstract class CompteBancaire {
	// Compte Bancaire est une classe abstraite dont vont herites les classes
	// Courant et Epargne

	// Declaration des attributs caracterisant un objet heritant de la classe
	// CompteBancaire
	private int idCompte;
	private int numCompte;
	private double solde;
	private int idClient;
	private String typeDeCompte;

	// Constructeur par defaut
	public CompteBancaire() {
		this.numCompte = (int) (Math.random() * 10000);
		this.solde = 12;
		this.idCompte=1;
	}

	public int getNumCompte() {
		return numCompte;
	}

	public void setNumCompte(int numCompte) {
		this.numCompte = numCompte;
	}

	public String getTypeDeCompte() {
		return typeDeCompte;
	}

	public void setTypeDeCompte(String typeDeCompte) {
		this.typeDeCompte = typeDeCompte;
	}

	// Getters et Setters
	public int getNumeroCompte() {
		return numCompte;
	}

	public void setNumeroCompte(int numeroCompte) {
		this.numCompte = numeroCompte;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public int getIdCompte() {
		return idCompte;
	}

	public void setIdCompte(int idCompte) {
		this.idCompte = idCompte;
	}

	// Redefinition de la methode toString()
	public String toString(int numeroCompte) {
		return "Le compte de numero " + this.numCompte + " a pour solde " + this.solde + " euros.";
	}
}
